package test.hello.world.notifications;

import android.app.Activity;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class TestNotificationExpandedMessage extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setText("oh yeah you are notified, now you can click me to dismiss the notification");
		setContentView(textView);
		Toast.makeText(this, "I'm a toast from a separate activity.", Toast.LENGTH_SHORT).show();
		textView.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				notificationManager.cancel(TestNotifications.NOTIFICATION_FIRST);
			}
		});
	}
	
}

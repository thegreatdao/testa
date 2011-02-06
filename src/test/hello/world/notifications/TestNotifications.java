package test.hello.world.notifications;

import test.hello.world.R;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class TestNotifications extends Activity
{
	public static final int NOTIFICATION_FIRST = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		int icon = R.drawable.gcm;
		String tickerText = "You hit the Jackpot!";
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);
		
		//notification's expanded message
		Context context = getApplicationContext();
		String contentTitle = "Congrats!";
		String contentText = "You can live your dream now!";
		Intent contentIntent = new Intent(this, TestNotificationExpandedMessage.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, contentIntent, 0);
		notification.setLatestEventInfo(context, contentTitle, contentText, pendingIntent);
		// add sound
//		notification.defaults |= Notification.DEFAULT_SOUND;
		// add vibration
//		notification.defaults |= Notification.DEFAULT_VIBRATE;
		//add light
//		notification.defaults |= Notification.DEFAULT_LIGHTS;
//		notification.vibrate = new long[]{0, 100, 200, 300};
		
		NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_FIRST, notification);
	}
	
}

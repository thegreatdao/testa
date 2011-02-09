package test.hello.world.broadcast;

import test.hello.world.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver
{

	@Override
	public void onReceive(Context context, Intent intent)
	{
		int icon = R.drawable.gcm;
		String tickerText = "Broacast received!";
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);
		
		//notification's expanded message
		String contentTitle = "signal received!";
		String contentText = "yo sup!";
		Intent contentIntent = new Intent(context, TestBroadcastReceviers.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, contentIntent, 0);
		notification.setLatestEventInfo(context, contentTitle, contentText, pendingIntent);
		
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(1, notification);
	}

}

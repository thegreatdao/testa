package test.hello.world.services;

import java.util.Timer;
import java.util.TimerTask;

import test.hello.world.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class NotificationService extends Service
{

	private NotificationManager notificationManager;
	private Timer timer = new Timer();
	
	@Override
	public IBinder onBind(Intent arg0)
	{
		return null;
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notifyUser();
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		if(timer != null)
		{
			timer.cancel();
		}
	}

	private void notifyUser()
	{

		final Notification notification = new Notification(R.drawable.gcm, "How are you", System.currentTimeMillis());
		final PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, TestLocalService.class), 0);

		notification.setLatestEventInfo(this, "How's Android", "Android is all the rage!", contentIntent);
		
		notificationManager.notify(1, notification);
		timer.schedule(new TimerTask()
		{
			
			@Override
			public void run()
			{				
				NotificationService.this.notificationManager.notify(1, notification);
				notification.setLatestEventInfo(NotificationService.this, "How's Android" + System.currentTimeMillis(), "Android is all the rage!", contentIntent);
			}
		}, 0, 5000);
	}
}

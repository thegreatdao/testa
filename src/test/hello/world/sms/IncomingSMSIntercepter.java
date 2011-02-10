package test.hello.world.sms;

import test.hello.world.R;
import test.hello.world.notifications.TestNotificationExpandedMessage;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;

public class IncomingSMSIntercepter extends BroadcastReceiver
{

	private static final String ACTION_TO_CAPTURE = "android.provider.Telephony.SMS_RECEIVED";

	@Override
	public void onReceive(Context context, Intent intent)
	{
		if (intent != null && intent.getAction() != null && ACTION_TO_CAPTURE.compareToIgnoreCase(intent.getAction()) == 0)
		{
			Object[] pduArray = (Object[]) intent.getExtras().get("pdus");
			SmsMessage[] messages = new SmsMessage[pduArray.length];
			for (int i = 0; i < pduArray.length; i++)
			{
				messages[i] = SmsMessage.createFromPdu((byte[]) pduArray[i]);
			}
			notifyUser(context);
		}
	}

	private void notifyUser(Context context)
	{

		int icon = R.drawable.gcm;
		String tickerText = "You'v got a message!";
		long when = System.currentTimeMillis();
		Notification notification = new Notification(icon, tickerText, when);
		
		String contentTitle = "Message received!";
		String contentText = "there is not much about the message!";
		Intent contentIntent = new Intent(context, TestNotificationExpandedMessage.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, contentIntent, 0);
		notification.setLatestEventInfo(context, contentTitle, contentText, pendingIntent);
		
		NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(1, notification);
	}
}

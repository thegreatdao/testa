package test.hello.world;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;

public class CounterRemoteService extends Service
{
	private int counter;
	private Handler handler;
	private Task task;

	@Override
	public IBinder onBind(Intent intent)
	{
		return stub;
	}

	private final ICounterService.Stub stub = new ICounterService.Stub()
	{

		@Override
		public int getCounter() throws RemoteException
		{
			return counter;
		}

	};

	@Override
	public void onCreate()
	{
		super.onCreate();
	}

	@Override
	public void onStart(Intent intent, int startId)
	{
		super.onStart(intent, startId);
		task = new Task();
		handler = new Handler();
		handler.postDelayed(task, 2000);
	}

	@Override
	public void onDestroy()
	{
		super.onDestroy();
		handler.removeCallbacks(task);
		handler = null;
	}

	class Task implements Runnable
	{
		public void run()
		{
			++counter;
			handler.postDelayed(this, 2000);
		}
	}
}

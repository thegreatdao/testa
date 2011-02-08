package test.hello.world;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RemoteServiceClient extends Activity {
    
	private IMyRemoteService remoteService;
	private boolean started = false;
	private RemoteServiceConnection conn = null;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setGravity(1);
		Button startButton = new Button(this);
		startButton.setText("Start");
		Button stopButton = new Button(this);
		stopButton.setText("Stop");
		Button bindButton = new Button(this);
		bindButton.setText("Bind");
		Button releaseButton = new Button(this);
		releaseButton.setText("Release");
		Button invokeButton = new Button(this);
		invokeButton.setText("Invoke");

		linearLayout.addView(startButton);
		linearLayout.addView(stopButton);
		linearLayout.addView(bindButton);
		linearLayout.addView(releaseButton);
		linearLayout.addView(invokeButton);
		setContentView(linearLayout);
        
        startButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		startService();
        	}
        });
        
        stopButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		stopService();
        	}
        });       
        
        bindButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		bindService();
        	}
        });  
        
        releaseButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		releaseService();
        	}
        });          
        
        invokeButton.setOnClickListener(new OnClickListener() {
        	public void onClick(View v){
        		invokeService();
        	}
        });          

   
    }
    
    private void startService()
	{
		if (started)
		{
			Toast.makeText(RemoteServiceClient.this, "Service already started",
					Toast.LENGTH_SHORT).show();
		} else
		{
			Intent i = new Intent("test.hello.world.services.CounterRemoteService");
			startService(i);
			started = true;
			updateServiceStatus();
			Log.d(getClass().getSimpleName(), "startService()");
		}

	}

	private void stopService()
	{
		if (!started)
		{
			Toast.makeText(RemoteServiceClient.this, "Service not yet started",
					Toast.LENGTH_SHORT).show();
		} else
		{
			Intent i = new Intent("test.hello.world.services.CounterRemoteService");
			stopService(i);
			started = false;
			updateServiceStatus();
			Log.d(getClass().getSimpleName(), "stopService()");
		}
	}

	private void bindService()
	{
		if (conn == null)
		{
			conn = new RemoteServiceConnection();
			Intent i = new Intent("test.hello.world.services.CounterRemoteService");
			bindService(i, conn, Context.BIND_AUTO_CREATE);
			updateServiceStatus();
		} else
		{
			Toast.makeText(RemoteServiceClient.this, "Cannot bind - service already bound", Toast.LENGTH_SHORT)
					.show();
		}
	}

	private void releaseService()
	{
		if (conn != null)
		{
			unbindService(conn);
			conn = null;
			updateServiceStatus();
		}
		else
		{
			Toast.makeText(RemoteServiceClient.this, "Cannot unbind - service not bound", Toast.LENGTH_SHORT).show();
		}
	}

	private void invokeService()
	{
		if (conn == null)
		{
			Toast.makeText(RemoteServiceClient.this,"Cannot invoke - service not bound", Toast.LENGTH_SHORT).show();
		} else
		{
			try
			{
				int counter = remoteService.getCounter();
				Toast.makeText(RemoteServiceClient.this, "Counter is " + counter, Toast.LENGTH_LONG).show();
			} catch (RemoteException re)
			{
				Log.e(getClass().getSimpleName(), "RemoteException");
			}
		}
	}

	class RemoteServiceConnection implements ServiceConnection {
        public void onServiceConnected(ComponentName className, 
			IBinder boundService ) {
          remoteService = IMyRemoteService.Stub.asInterface((IBinder)boundService);
          Log.d( getClass().getSimpleName(), "onServiceConnected()" );
        }

        public void onServiceDisconnected(ComponentName className) {
          remoteService = null;
		   updateServiceStatus();
		   Log.d( getClass().getSimpleName(), "onServiceDisconnected" );
        }
    };

	private void updateServiceStatus()
	{
		String bindStatus = conn == null ? "unbound" : "bound";
		String startStatus = started ? "started" : "not started";
		String statusText = "Service status: " + bindStatus + "," + startStatus;
		Toast.makeText(RemoteServiceClient.this, bindStatus + " " + startStatus + " " + statusText, Toast.LENGTH_LONG);
	}

	protected void onDestroy()
	{
		super.onDestroy();
		releaseService();
	}
}
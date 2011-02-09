package test.hello.world.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestBroadcastReceviers extends Activity
{
	private static final String NOTIFICATION_FIRED = "test.hello.world.broadcast.FIRE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Button startButton = new Button(this);
		startButton.setText("Broadcast");
		startButton.setTextSize(60);
		setContentView(startButton);
		startButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				sendBroadcast(new Intent(NOTIFICATION_FIRED));
			}
		});
	}

}

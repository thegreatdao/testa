package test.hello.world.services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class TestLocalService extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setGravity(1);
		Button startButton = new Button(this);
		startButton.setText("start");
		Button stopButton = new Button(this);
		stopButton.setText("stop");
		linearLayout.addView(startButton);
		linearLayout.addView(stopButton);
		setContentView(linearLayout);
		startButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				startService(new Intent(TestLocalService.this, NotificationService.class));
			}
		});
		stopButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View arg0)
			{
				stopService(new Intent(TestLocalService.this, NotificationService.class));
			}
		});
	}

}

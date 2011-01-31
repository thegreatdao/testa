package test.hello.world.stateManagement;

import test.hello.world.R;
import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TestOnConfigurationChanged extends Activity
{
	private boolean isVisible;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		init();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		int orientation = newConfig.orientation;
		if (orientation == Configuration.ORIENTATION_LANDSCAPE)
		{
			Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
		}
		else if (orientation == Configuration.ORIENTATION_PORTRAIT)
		{
			Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
		}
		init();
	}

	private void init()
	{
		setContentView(R.layout.rotation_handling);
		Button button = (Button) findViewById(R.id.rotation_button);
		final TextView secret = (TextView) findViewById(R.id.secret);
		if (isVisible)
		{
			secret.setVisibility(View.VISIBLE);
		}
		button.setOnClickListener(new View.OnClickListener()
		{

			@Override
			public void onClick(View arg0)
			{
				secret.setVisibility(View.VISIBLE);
				isVisible = true;
			}
		});
	}

}

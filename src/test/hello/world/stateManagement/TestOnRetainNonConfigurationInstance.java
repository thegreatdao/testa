package test.hello.world.stateManagement;

import test.hello.world.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TestOnRetainNonConfigurationInstance extends Activity
{
	private boolean isVisible;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rotation_handling);
		Button button = (Button) findViewById(R.id.rotation_button);
		final TextView secret = (TextView) findViewById(R.id.secret);
		Boolean visible = (Boolean) getLastNonConfigurationInstance();
		if(visible != null)
		{
			isVisible = visible;
		}
		if(isVisible)
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

	@Override
	public Boolean onRetainNonConfigurationInstance()
	{
		return isVisible;
	}
}

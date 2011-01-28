package test.hello.world.color;

import test.hello.world.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class TestColorStateList extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.color_state_list);
		Button button = (Button) findViewById(R.id.Button01);
		button.setBackgroundResource(R.drawable.insets);
	}
	
}

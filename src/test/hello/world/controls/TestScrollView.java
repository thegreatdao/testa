package test.hello.world.controls;

import test.hello.world.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TestScrollView extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scroll_view);
		TextView textView = (TextView) findViewById(R.id.txt);
		String text = getString(R.string.scroll_text);
		textView.setText(text);
	}
	
}

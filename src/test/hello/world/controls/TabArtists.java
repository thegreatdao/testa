package test.hello.world.controls;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TabArtists extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		TextView textview = new TextView(this);
		textview.setText("Artists");
		setContentView(textview);
	}
}

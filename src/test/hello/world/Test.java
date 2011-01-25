package test.hello.world;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class Test extends Activity implements OnClickListener
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public void onClick(View arg0)
	{
		Toast.makeText(this, "Hello,World", Toast.LENGTH_SHORT).show();
	}
}
package test.hello.world.menu;

import test.hello.world.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class TestInflatingMenu extends Activity
{
	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.inflating_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
}

package test.hello.world.controls;

import test.hello.world.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TestSlidingDrawer extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding_drawer);
		GridView gridview = (GridView) findViewById(R.id.content);
		ImageAdapter imageAdapter = new ImageAdapter(this);
		gridview.setAdapter(imageAdapter);

		gridview.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id)
			{
				Toast.makeText(TestSlidingDrawer.this, "" + position, Toast.LENGTH_SHORT).show();
			}
		});
	}
}

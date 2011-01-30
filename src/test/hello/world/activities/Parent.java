package test.hello.world.activities;

import test.hello.world.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Parent extends Activity
{
	protected static final int SUB_ACTIVITY_REQUEST_CODE = 100;

	TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.parent);
		textView = (TextView) findViewById(R.id.text);
		textView.setText("TextView element");
		Button b = (Button) findViewById(R.id.button);
		b.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(Parent.this, Child.class);
				startActivityForResult(intent, SUB_ACTIVITY_REQUEST_CODE);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == SUB_ACTIVITY_REQUEST_CODE)
		{
			Bundle b = data.getExtras();
			textView.setText(b.getString("TEXT"));
		}
	}

}

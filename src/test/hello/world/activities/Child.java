package test.hello.world.activities;

import test.hello.world.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Child extends Activity
{
	public final static int SUCCESS_RETURN_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.child);
		final EditText editView = (EditText) findViewById(R.id.edit);
		editView.setText("SubActivity");
		Button button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("TEXT", editView.getText().toString());
				intent.putExtras(bundle);
				setResult(SUCCESS_RETURN_CODE, intent);
				finish();
			}
		});
	}

}

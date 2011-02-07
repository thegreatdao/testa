package test.hello.world.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestImplicitIntent extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Button button = new Button(this);
		button.setText("click to get contacts list");
		setContentView(button);
		button.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				Intent contacts = new Intent();
				contacts.setAction(android.content.Intent.ACTION_VIEW);
				contacts.setData(android.provider.ContactsContract.Contacts.CONTENT_URI);
				startActivity(contacts);
			}
		});
	}

}

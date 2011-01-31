package test.hello.world.popup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TestPopUp extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		TextView textView = new TextView(this);
		textView.setText("press me :)");
		textView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				AlertDialog.Builder builder = new AlertDialog.Builder(TestPopUp.this);
				builder.setMessage("Are you sure you want to exit?")
						.setCancelable(false).setPositiveButton("Yes",
								new DialogInterface.OnClickListener()
								{
									public void onClick(DialogInterface dialog, int id)
									{
										TestPopUp.this.finish();
									}
								}).setNegativeButton("Toast",  new DialogInterface.OnClickListener()
								{
									
									@Override
									public void onClick(DialogInterface arg0, int arg1)
									{
										Toast.makeText(TestPopUp.this, "a toast from Anroid", Toast.LENGTH_SHORT).show();
									}
								}).setNeutralButton("No",
								new DialogInterface.OnClickListener()
								{
									public void onClick(DialogInterface dialog, int id)
									{
										dialog.cancel();
									}
								});
				builder.create();
				builder.show();
			}
		});
		setContentView(textView);
	}
}

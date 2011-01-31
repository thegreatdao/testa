package test.hello.world.popup;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class TestAlertDialogWithAListOfSingleItems extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		final String[] countries = { "China", "Indian", "Russia", "Korea", "U.S" };

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Select a country");
		builder.setSingleChoiceItems(countries, -1, new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int item)
			{
				Toast.makeText(TestAlertDialogWithAListOfSingleItems.this, countries[item], Toast.LENGTH_SHORT).show();
			}
		});
		builder.create().show();
	}
}

package test.hello.world.popup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

public class TestProgressBars extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ProgressDialog.show(TestProgressBars.this, "", "Loading. Please wait...", true);
		/*ProgressDialog progressBarDialog = new ProgressDialog(this);
		progressBarDialog.setMessage("Loading. Please wait...");
		progressBarDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressBarDialog.show();*/
	}
}

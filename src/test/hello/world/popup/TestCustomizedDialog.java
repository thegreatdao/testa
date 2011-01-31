package test.hello.world.popup;

import test.hello.world.R;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

public class TestCustomizedDialog extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.customized_dialog);
		dialog.setTitle("Customized dialog");
		dialog.show();
	}
}

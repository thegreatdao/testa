package test.hello.world.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class TestReadAndWriteFiles extends Activity
{
	private final static String FILE = "file.txt";
	private EditText editText;

	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		editText = new EditText(this);
		setContentView(editText);
	}

	public void onResume()
	{
		super.onResume();
		try
		{
			InputStream inputStream = openFileInput(FILE);
			if (inputStream != null)
			{
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				String string;
				StringBuilder stringBuilder = new StringBuilder();
				while ((string = bufferedReader.readLine()) != null)
				{
					stringBuilder.append(string + "\n");
				}
				inputStream.close();
				editText.setText(stringBuilder.toString());
			}
		}
		catch (Throwable throwable)
		{
			Log.e("", throwable.toString());
		}
	}

	public void onPause()
	{
		super.onPause();
		try
		{
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(FILE, 0));
			outputStreamWriter.write(editText.getText().toString());
			outputStreamWriter.close();
		}
		catch (Throwable throwable)
		{
			Log.e("", throwable.toString());
		}
	}
}

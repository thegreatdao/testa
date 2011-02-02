package test.hello.world.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import test.hello.world.R;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class TestReadROF extends Activity
{
	private TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		textView = new TextView(this);
		setContentView(textView);
		readFile();
	}
	
	private void readFile()
	{
		InputStream countriesRaw = getResources().openRawResource(R.raw.countries);
		try
		{
			InputStreamReader inputStreamReader = new InputStreamReader(countriesRaw);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String string;
			StringBuilder stringBuilder = new StringBuilder();
			while ((string = bufferedReader.readLine()) != null)
			{
				stringBuilder.append(string + "\n");
			}
			countriesRaw.close();
			textView.setText(stringBuilder.toString());
		}
		catch(Exception e)
		{
			Log.e("", e.getMessage());
		}
	}
}

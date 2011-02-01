package test.hello.world.preferences;

import test.hello.world.R;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class TestPreferences extends PreferenceActivity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		menu.add(Menu.NONE, 0, 0, "Show current settings");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case 0:
				showSettings();
				return true;
			default:
				return false;
		}
	}

	private void showSettings()
	{
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(sharedPrefs.getBoolean("enable_locale", false));
		stringBuilder.append("\n" + sharedPrefs.getString("current_locale", "China"));
		stringBuilder.append("\n" + sharedPrefs.getString("welcome_message", ""));
		stringBuilder.append("\n" + sharedPrefs.getString("ringtone", "DEFAULT"));
		Toast.makeText(this, stringBuilder.toString(), Toast.LENGTH_LONG).show();
	}

}

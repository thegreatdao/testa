package test.hello.world.menu;

import test.hello.world.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TestBasicMenu extends Activity
{
	public static final int CHINESE = Menu.FIRST + 1;
	public static final int JAPANESE = CHINESE + 1;
	public static final int KOREAN = JAPANESE + 1;
	public static final int INDIAN = KOREAN + 1;
	public static final int THAILAND = INDIAN + 1;
	public static final int MALAYSIAN = THAILAND + 1;
	public static final int IRANIAN = MALAYSIAN + 1;

	@Override
	public void onCreate(Bundle icicle)
	{
		super.onCreate(icicle);
		setContentView(R.layout.basic_menu);
		TextView textView = (TextView)findViewById(R.id.basic_menu_text);
		registerForContextMenu(textView);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
	{
		initMenu(menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		initMenu(menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		toastMenuItem(item);
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item)
	{
		toastMenuItem(item);
		return (super.onContextItemSelected(item));
	}

	private void initMenu(Menu menu)
	{
		menu.add(Menu.NONE, CHINESE, Menu.NONE, "Chinese");
		menu.add(Menu.NONE, JAPANESE, Menu.NONE, "Japanese");
		menu.add(Menu.NONE, KOREAN, Menu.NONE, "Korean");
		menu.add(Menu.NONE, INDIAN, Menu.NONE, "Indian");
		menu.add(Menu.NONE, THAILAND, Menu.NONE, "Thailand");
		menu.add(Menu.NONE, MALAYSIAN, Menu.NONE, "Malaysian");
		menu.add(Menu.NONE, IRANIAN, Menu.NONE, "Iranian");
	}
	
	private void toastMenuItem(MenuItem menuItem)
	{
		Toast.makeText(this, menuItem.getTitle(), Toast.LENGTH_SHORT).show();
	}

}

package test.hello.world;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class TestTab extends TabActivity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab);

		Resources res = getResources();
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent = new Intent().setClass(this, TabArtists.class);

		spec = tabHost.newTabSpec("artists").setIndicator("Artists", res.getDrawable(R.drawable.tab_list_icons)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, TabAlbums.class);
		spec = tabHost.newTabSpec("albums").setIndicator("Albums", res.getDrawable(R.drawable.tab_list_icons)).setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, TabSongs.class);
		spec = tabHost.newTabSpec("songs").setIndicator("Songs", res.getDrawable(R.drawable.tab_list_icons)).setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}
}

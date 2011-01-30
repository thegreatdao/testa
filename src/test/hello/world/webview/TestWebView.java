package test.hello.world.webview;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

public class TestWebView extends TabActivity
{
	public static final String URL = "url";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		TabHost host = getTabHost();

		Intent googleIntent = new Intent(this, WebViewActivity.class);
		googleIntent.putExtra(URL, "http://www.google.com");
		host.addTab(host.newTabSpec("one").setIndicator("GOOGLE").setContent(
				googleIntent));
		Intent yahooIntent = new Intent(this, WebViewActivity.class);
		yahooIntent.putExtra(URL, "http://www.yahoo.com");
		host.addTab(host.newTabSpec("two").setIndicator("YAHOO").setContent(
				yahooIntent));
	}
}

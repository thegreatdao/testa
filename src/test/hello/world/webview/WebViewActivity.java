package test.hello.world.webview;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity
{
	private WebView browser;

	@Override
	public void onCreate(Bundle bundle)
	{
		super.onCreate(bundle);
		browser = new WebView(this);
		browser.getSettings().setJavaScriptEnabled(true);
		browser.setWebViewClient(new TestWebViewClient());
		String url = (String)getIntent().getExtras().get(TestWebView.URL);
		setContentView(browser);
		browser.loadUrl(url);
	}
	
	private class TestWebViewClient extends WebViewClient
	{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			view.loadUrl(url);
			return true;
		}
	}
}

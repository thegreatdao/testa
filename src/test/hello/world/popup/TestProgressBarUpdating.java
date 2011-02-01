package test.hello.world.popup;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TestProgressBarUpdating extends Activity
{
	private static final int DEFAULT_DIALOG = 0;
	private TextView textView;
	private TestProgressBarUpdatingThread testProgressBarUpdatingThread;
	private ProgressDialog progressDialog;
	private final static String TOTAL = "total";
	private Handler handler;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		handler = new Handler()
		{
			public void handleMessage(Message msg)
			{
				int total = msg.getData().getInt(TOTAL);
				progressDialog.setProgress(total);
				if (total >= 100)
				{
					dismissDialog(DEFAULT_DIALOG);
				}
			}
		};
		textView = new Button(this);
		textView.setText("click");
		textView.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				showDialog(DEFAULT_DIALOG);
			}
		});
		setContentView(textView);
	}

	protected Dialog onCreateDialog(int id)
	{
		switch (id)
		{
		case DEFAULT_DIALOG:
			progressDialog = new ProgressDialog(TestProgressBarUpdating.this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setMessage("Loading...");
			testProgressBarUpdatingThread = new TestProgressBarUpdatingThread(handler);
			testProgressBarUpdatingThread.start();
			return progressDialog;
		default:
			return null;
		}
	}

	private class TestProgressBarUpdatingThread extends Thread
	{
		private Handler handler;
		private boolean done;
		private int total;

		TestProgressBarUpdatingThread(Handler handler)
		{
			this.handler = handler;
		}

		public void run()
		{
			while (!done)
			{
				try
				{
					Thread.sleep(100);
				} catch (InterruptedException e)
				{
					Log.e("ERROR", e.getMessage());
				}
				Message message = handler.obtainMessage();
				Bundle bundle = new Bundle();
				bundle.putInt(TOTAL, total);
				message.setData(bundle);
				handler.sendMessage(message);
				if(total >= 100)
				{
					done = true;
				}
				total++;
			}
		}
	}
}

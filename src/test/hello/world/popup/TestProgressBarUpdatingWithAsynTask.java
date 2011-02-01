package test.hello.world.popup;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class TestProgressBarUpdatingWithAsynTask extends Activity
{
	private static final int DEFAULT_DIALOG = 0;
	private TextView textView;
	private ProgressDialog progressDialog;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
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
			progressDialog = new ProgressDialog(TestProgressBarUpdatingWithAsynTask.this);
			progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			progressDialog.setMessage("Loading...");
			new TestProgressBarUpdatingAsyntask().execute();
			return progressDialog;
		default:
			return null;
		}
	}

	private class TestProgressBarUpdatingAsyntask extends AsyncTask<Void, Integer, Void>
	{
		@Override
		protected Void doInBackground(Void... arg0)
		{
			for(int i=0; i<100; i++)
			{
				publishProgress(i);
				try
				{
					Thread.sleep(100);
				} catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			super.onPostExecute(result);
			TestProgressBarUpdatingWithAsynTask.this.progressDialog.dismiss();
		}

		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Integer... values)
		{
			super.onProgressUpdate(values);
			TestProgressBarUpdatingWithAsynTask.this.progressDialog.setProgress(values[0]);
		}
		
		
	}

}

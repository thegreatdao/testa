package test.hello.world;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class TestGridView extends Activity
{
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_view);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(new ImageAdapter(this));

		gridview.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id)
			{
				Toast.makeText(TestGridView.this, "" + position, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private class ImageAdapter extends BaseAdapter
	{
		private Context mContext;

		public ImageAdapter(Context c)
		{
			mContext = c;
		}

		public int getCount()
		{
			return mThumbIds.length;
		}

		public Object getItem(int position)
		{
			return null;
		}

		public long getItemId(int position)
		{
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent)
		{
			ImageView imageView;
			if (convertView == null)
			{
				imageView = new ImageView(mContext);
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
			} else
			{
				imageView = (ImageView) convertView;
			}

			imageView.setImageResource(mThumbIds[position]);
			return imageView;
		}

		private Integer[] mThumbIds = {R.drawable.dun, R.drawable.feng, R.drawable.fou, R.drawable.fu, R.drawable.gen, R.drawable.ge, R.drawable.dun, R.drawable.feng, R.drawable.fou, R.drawable.fu, R.drawable.gen, R.drawable.ge, R.drawable.dun, R.drawable.feng, R.drawable.fou, R.drawable.fu, R.drawable.gen, R.drawable.ge, R.drawable.dun, R.drawable.feng, R.drawable.fou, R.drawable.fu, R.drawable.gen, R.drawable.ge};
	}
}

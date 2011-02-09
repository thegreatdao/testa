package test.hello.world.multimedia;

import test.hello.world.R;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class TestVideo extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		VideoView videoView = new VideoView(this);
		MediaController mediaController = new MediaController(this);
		videoView.setMediaController(mediaController);
		Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.monster);
		videoView.setVideoURI(uri);
		videoView.start();
		setContentView(videoView);
	}
	
}

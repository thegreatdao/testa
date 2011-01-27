package test.hello.world.animations;

import test.hello.world.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class TestAnimation extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation);
		ImageView greenCheckMark = (ImageView)findViewById(R.id.animation);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.hyperspace_jump);
		greenCheckMark.startAnimation(animation);
	}

}

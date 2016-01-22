package comapps.com.lakewoodsmokehouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.TransitionInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

/**
 * Created by me on 10/13/2015.
 */
public class Splash extends AppCompatActivity implements Animation.AnimationListener  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        setupWindowAnimations();

        ViewGroup mRoot = (ViewGroup) findViewById(R.id.splash);

        Animation a = AnimationUtils
                .loadAnimation(this, R.anim.startanimation);
        a.setAnimationListener(this);

        ImageView iv = (ImageView) findViewById(R.id.enter);
        //	iv.clearAnimation();
        iv.startAnimation(a);


    }

    @Override
    public void onAnimationEnd(Animation animation) {


        Intent mainIntent = new Intent().setClass(Splash.this,
                MainActivity.class);

        setupWindowAnimations();

        startActivity(mainIntent);

        finish();

        // TODO Auto-generated method stub

    }

    private void setupWindowAnimations() {
        Fade fade = (Fade) TransitionInflater.from(this).inflateTransition(R.transition.transistionfade);
        getWindow().setEnterTransition(fade);
    }



    @Override
    public void onAnimationRepeat(Animation animation) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onAnimationStart(Animation animation) {
        // TODO Auto-generated method stub

    }
}
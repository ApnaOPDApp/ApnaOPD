import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.knstech.apnaopd.R;

public class AnimationUI{

    View view;
    Context context;

    public AnimationUI(View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void clockwise(View view){
            ImageView image = (ImageView)view.findViewById(R.id.imageView);
            Animation animation = AnimationUtils.loadAnimation(context,
                    R.anim.myanimation);
            image.startAnimation(animation);
        }

        public void zoom(View view){
            ImageView image = (ImageView)view.findViewById(R.id.imageView);
            Animation animation1 = AnimationUtils.loadAnimation(context,
                    R.anim.clockwise);
            image.startAnimation(animation1);
        }

        public void fade(View view){
            ImageView image = (ImageView)view.findViewById(R.id.imageView);
            Animation animation1 =
                    AnimationUtils.loadAnimation(context,
                            R.anim.fade);
            image.startAnimation(animation1);
        }

        public void blink(View view){
            ImageView image = (ImageView)view.findViewById(R.id.imageView);
            Animation animation1 =
                    AnimationUtils.loadAnimation(context,
                            R.anim.blink);
            image.startAnimation(animation1);
        }

        public void move(View view){
            ImageView image = (ImageView)view.findViewById(R.id.imageView);
            Animation animation1 =
                    AnimationUtils.loadAnimation(context, R.anim.move);
            image.startAnimation(animation1);
        }

        public void slide(View view){
            ImageView image = (ImageView)view.findViewById(R.id.imageView);
            Animation animation1 =
                    AnimationUtils.loadAnimation(context, R.anim.slide);
            image.startAnimation(animation1);
        }
}



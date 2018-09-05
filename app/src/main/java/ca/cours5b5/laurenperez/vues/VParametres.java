package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;

import ca.cours5b5.laurenperez.activites.AParametres;

public class VParametres extends Vue{

    static{
        Log.d("atelier04", AParametres.class.getSimpleName()+ ":static");
    }


    public VParametres(Context context) {
        super(context);
    }

    public VParametres(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VParametres(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate(){
        super.onFinishInflate();
        creerLog("onFinishInflate");

    }
}

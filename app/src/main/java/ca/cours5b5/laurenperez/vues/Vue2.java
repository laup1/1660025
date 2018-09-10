package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


import ca.cours5b5.laurenperez.R;

public abstract class Vue2 extends ConstraintLayout {
    public Vue2(Context context) {
        super(context);
    }

    public Vue2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Vue2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void creerLog(String nomMethode){
        Log.d("atelier04", this.getClass().getSimpleName() + ":"+ nomMethode);
    }


}

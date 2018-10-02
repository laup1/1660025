package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.TextView;

public class VCase extends AppCompatButton {

    public TextView getCases() {
        return cases;
    }

    public TextView cases;
    public VCase(Context context) {
        super(context);
    }

    public VCase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VCase(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public VCase(Context context, int rangee, int colonne) {
        super(context);

        cases = new TextView(context );
        cases.setText(rangee + "," + colonne);


    }

    public void initialiser(){

    }
}

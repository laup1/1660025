package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.TextView;

public class VCase extends AppCompatButton {

    public Button getCases() {
        return cases;
    }

    public Button cases;
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

        cases = new Button(context );
        cases.setText(rangee + "," + colonne);


    }

    public void initialiser(){



    }
}

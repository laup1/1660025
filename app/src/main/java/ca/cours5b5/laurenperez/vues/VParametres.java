package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import ca.cours5b5.laurenperez.R;
import ca.cours5b5.laurenperez.activites.AParametres;
import ca.cours5b5.laurenperez.modeles.MParametres;

public class VParametres  extends Vue {


    private Spinner spinnerHauteur, spinnerLargeur, spinnerPourGagner ;

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
    //regler


        spinnerHauteur = (Spinner) findViewById(R.id.spinnerHauteur);
        spinnerLargeur = (Spinner) findViewById(R.id.spinnerLargeur);
        spinnerPourGagner = (Spinner) findViewById(R.id.spinnerPourGagner);
        MParametres parametres = new MParametres();

        ArrayAdapter<Integer> adapterLargeur = new ArrayAdapter<Integer>(super.getContext(), android.R.layout.simple_spinner_dropdown_item);
        spinnerLargeur.setAdapter(adapterLargeur);

        spinnerLargeur.setSelection(3);




        ArrayAdapter<Integer> adapterPourGagner = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_dropdown_item);
        spinnerPourGagner.setAdapter(adapterPourGagner);
        adapterPourGagner.addAll(3, 4);
        spinnerPourGagner.setSelection(1);


    }




    static{
        Log.d("atelier04", AParametres.class.getSimpleName()+ ":static");
    }

}

package ca.cours5b5.laurenperez.vues;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import ca.cours5b5.laurenperez.R;

import ca.cours5b5.laurenperez.modeles.MParametres;
import ca.cours5b5.laurenperez.vues.Vue;



import android.content.Context;
import android.util.AttributeSet;

public class VParametres extends Vue {
    private Spinner spinnerHauteur, spinnerLargeur, spinnerPourGagner;

    static {
        Log.d("atelier04", ca.cours5b5.laurenperez.vues.VParametres.class.getSimpleName() + ":static");
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
    protected void onFinishInflate() {
        super.onFinishInflate();

        Log.d("Atelier05", ca.cours5b5.laurenperez.vues.VParametres.class.getSimpleName() + "ONFINISH" );
        spinnerHauteur = (Spinner) findViewById(R.id.spinnerHauteur);
        spinnerLargeur = (Spinner) findViewById(R.id.spinnerLargeur);
        spinnerPourGagner = (Spinner) findViewById(R.id.spinnerPourGagner);

        ArrayAdapter<Integer> adapterLargeur = new ArrayAdapter<Integer>(super.getContext(), android.R.layout.simple_spinner_dropdown_item);
        spinnerLargeur.setAdapter(adapterLargeur);
        ArrayAdapter<Integer> adapterHauteur = new ArrayAdapter<Integer>(super.getContext(), android.R.layout.simple_spinner_dropdown_item);
        spinnerHauteur.setAdapter(adapterHauteur);
        ArrayAdapter<Integer> adapterPourGagner = new ArrayAdapter<Integer>(super.getContext(), android.R.layout.simple_spinner_dropdown_item);
        spinnerPourGagner.setAdapter(adapterPourGagner);

        refreshSpinner(spinnerHauteur, MParametres.instance.hauteur, MParametres.instance.getChoixLargeur());
        refreshSpinner(spinnerLargeur, MParametres.instance.largeur, MParametres.instance.getChoixHauteur());
        refreshSpinner(spinnerPourGagner, MParametres.instance.pourGagner, MParametres.instance.getChoixPourGagner());


        spinnerHauteur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MParametres.instance.hauteur = (Integer) parent.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        spinnerLargeur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MParametres.instance.largeur = (Integer) parent.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });

        spinnerPourGagner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                MParametres.instance.pourGagner = (Integer) parent.getAdapter().getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });
    }

    private void refreshSpinner(Spinner spinner, int choixActuel, List<Integer> lesChoix) {
        ArrayAdapter<Integer> adapteur = (ArrayAdapter) spinner.getAdapter();
        int choixTemp;

        for (int i = 0; i < lesChoix.size(); i++) {
            choixTemp = lesChoix.get(i);
            adapteur.add(Integer.valueOf(choixTemp));
            if (choixActuel == choixTemp) {
                spinner.setSelection(i);
            }
        }
    }
}

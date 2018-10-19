package ca.cours5b5.laurenperez.modeles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.laurenperez.Serialisation.AttributSerialisable;
import ca.cours5b5.laurenperez.controleurs.ControleurAction;
import ca.cours5b5.laurenperez.controleurs.interfaces.Fournisseur;
import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerFournisseur;
import ca.cours5b5.laurenperez.exceptions.ErreurDeSerialisation;
import ca.cours5b5.laurenperez.global.GCommande;
import ca.cours5b5.laurenperez.global.GCouleur;

public class MPartie extends Modele implements Fournisseur{

    @AttributSerialisable
    public MParametresPartie parametres;
    private final String __parametres = "parametres";

    @AttributSerialisable
    public List<Integer> coups;
    private final String __coups = "coups";

    private MGrille grille;
    private GCouleur couleurCourante;

    public MPartie(MParametresPartie parametres){

        this.parametres = parametres;
        grille = new MGrille(parametres.getLargeur());
        initialiserCouleurCourante();
        fournirActionPlacerJeton();

    }

    public MParametresPartie getParametres() {
        return parametres;
    }

    public MGrille getGrille() {
        return grille;
    }

    private void initialiserCouleurCourante(){

        couleurCourante = GCouleur.ROUGE;



    }

    public void fournirActionPlacerJeton(){

        ControleurAction.fournirAction(this, GCommande.JOUER_COUP_ICI, new ListenerFournisseur() {
            @Override
            public void executer(Object... args) {


                jouerCoup((int)args[0]);

            }

        });




    }

    protected void jouerCoup(int colonne){

        grille.placerJeton(colonne ,couleurCourante);
        prochaineCouleurCourante();


    }

    private void prochaineCouleurCourante(){

        if(couleurCourante.equals(GCouleur.ROUGE)){
            couleurCourante= GCouleur.JAUNE;
        }else{
            couleurCourante = GCouleur.ROUGE;
        }

    }

    @Override
    public void aPartirObjetJson(Map<String, Object> objetJson) throws ErreurDeSerialisation{


        List<String> json = new ArrayList<>();
        for (Map.Entry<String, Object> entry : objetJson.entrySet()) {
            String cle = entry.getKey();
            Object valeur = entry.getValue();

            if (cle.equals(__parametres)) {
                parametres.aPartirObjetJson((Map<String, Object>) valeur);
            } else if(cle.equals(__coups)){
                json = (List<String>)valeur;

            }
        }

        this.grille = new MGrille(parametres.getLargeur());
        this.initialiserCouleurCourante();
        rejouterLesCoups(listeCoupsAPartirJson(json));



    }

    private void rejouterLesCoups(List<Integer> coupsARejouter){

        for (int coups : coupsARejouter ) {

            jouerCoup(coups);

        }

    }

    private List<Integer> listeCoupsAPartirJson(List<String> listeCoupsObjetJson){

        List<Integer> listCoups = new ArrayList<>();

        for (String coups: listeCoupsObjetJson) {

            listCoups.add(Integer.parseInt(coups));

        }

        return listCoups;



    }

    private List<String> listeCoupsEnObjetJson(List<Integer> listeCoups){

        List<String> listCoups = new ArrayList<>();

        for (int coups: listeCoups) {

            listCoups.add(Integer.toString(coups));

        }

        return listCoups;



    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurDeSerialisation {

        Map<String, Object> objetJson = new HashMap<>();
        objetJson.put(__parametres, parametres.toString());
        objetJson.put(__coups, listeCoupsEnObjetJson(coups));

        return objetJson;
    }
}

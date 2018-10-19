package ca.cours5b5.laurenperez.modeles;

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
        //RIEN CODER ICI POUR L'INSTANT
    }

    @Override
    public Map<String, Object> enObjetJson() throws ErreurDeSerialisation {

        //RIEN CODER ICI POUR L'INSTANT
        return null;
    }
}

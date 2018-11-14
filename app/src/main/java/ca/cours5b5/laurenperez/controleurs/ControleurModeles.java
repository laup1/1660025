package ca.cours5b5.laurenperez.controleurs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.cours5b5.laurenperez.controleurs.interfaces.Fournisseur;
import ca.cours5b5.laurenperez.controleurs.interfaces.ListenerGetModele;
import ca.cours5b5.laurenperez.donnees.ListenerChargement;
import ca.cours5b5.laurenperez.donnees.Serveur;
import ca.cours5b5.laurenperez.donnees.SourceDeDonnees;
import ca.cours5b5.laurenperez.exceptions.ErreurModele;
import ca.cours5b5.laurenperez.modeles.MParametres;
import ca.cours5b5.laurenperez.modeles.MParametresPartie;
import ca.cours5b5.laurenperez.modeles.MPartie;
import ca.cours5b5.laurenperez.modeles.Modele;
import ca.cours5b5.laurenperez.donnees.Disque;
import ca.cours5b5.laurenperez.usagers.UsagerCourant;

public final class ControleurModeles {

    private ControleurModeles(){}

    private static Map<String, Modele> modelesEnMemoire;

    private static SourceDeDonnees[] sequenceDeChargement;

    private static List<SourceDeDonnees> listeDeSauvegardes;

    static {

        modelesEnMemoire = new HashMap<>();

        listeDeSauvegardes = new ArrayList<>();
        listeDeSauvegardes.add(Disque.getInstance());
        listeDeSauvegardes.add(Serveur.getInstance());

    }

    public static void setSequenceDeChargement(SourceDeDonnees... sequenceDeChargement){

        ControleurModeles.sequenceDeChargement = sequenceDeChargement;

    }

    public static void sauvegarderModeleDansCetteSource(String nomModele, SourceDeDonnees sourceDeDonnees) {

        Modele modele = modelesEnMemoire.get(nomModele);


        if(modele != null){

            Map<String, Object> objetJson = modele.enObjetJson();

            sourceDeDonnees.sauvegarderModele(nomModele, objetJson);

        }
    }

    static Modele getModele(String nomModele, ListenerGetModele listenerGetModele){

        Modele modele = modelesEnMemoire.get(nomModele);


        if(modele == null){

          //verifier
            creerModeleEtChargerDonnees(nomModele, listenerGetModele);









            modele =  chargerViaSequenceDeChargement(nomModele);



        }else{


        }

        return modele;
    }


    private static Modele chargerViaSequenceDeChargement(final String nomModele){

        Modele modele = creerModeleSelonNom(nomModele);

        modelesEnMemoire.put(nomModele, modele);

        for(SourceDeDonnees sourceDeDonnees : sequenceDeChargement){

            Map<String, Object> objetJson = sourceDeDonnees.chargerModele(nomModele);

            if(objetJson != null){

                modele.aPartirObjetJson(objetJson);
                break;

            }

        }

        return modele;
    }

    public static void sauvegarderModele(String nomModele) throws ErreurModele {

        for(SourceDeDonnees source : listeDeSauvegardes){

            sauvegarderModeleDansCetteSource(nomModele, source);

        }

    }


    private static Modele creerModeleSelonNom(String nomModele, final ListenerGetModele listenerGetModele) throws ErreurModele{

        if(nomModele.equals(MParametres.class.getSimpleName())){

            return new MParametres();

        }else if(nomModele.equals(MPartie.class.getSimpleName())){

            MParametres mParametres = (MParametres) getModele(MParametres.class.getSimpleName(), listenerGetModele);

            return new MPartie(mParametres.getParametresPartie().cloner());

        }else{

            throw new ErreurModele("Modèle inconnu: " + nomModele);

        }
    }

    public static void detruireModele(String nomModele) {

        Modele modele = modelesEnMemoire.get(nomModele);

        if(modele != null){

            modelesEnMemoire.remove(nomModele);

            ControleurObservation.detruireObservation(modele);

            if(modele instanceof Fournisseur){

                ControleurAction.oublierFournisseur((Fournisseur) modele);

            }
        }
    }

   private static String getCheminSauvegarde(String nomModele){

        return nomModele + "/" + UsagerCourant.getId();
   }

    public static void effacer(String nomModele){

        modelesEnMemoire.remove(nomModele);
        detruireModele(nomModele);

        for(SourceDeDonnees source : listeDeSauvegardes){

            effacerModeleSource(nomModele, source);

        }

    }

    public static void effacerModeleSource(String nomModele, SourceDeDonnees sourceDeDonnees) {

        sourceDeDonnees.detruireSauvegarde(getCheminSauvegarde(nomModele));


    }





    private static void creerModeleEtChargerDonnees(final String nomModele, final ListenerGetModele listenerGetModele){

       Modele modele = creerModeleSelonNom(nomModele, listenerGetModele);

       listenerGetModele.reagirAuModele(modele);

        modelesEnMemoire.put(nomModele, modele);

        chargerDonnees(modele, nomModele, listenerGetModele);




    }

    private static void chargerDonnees(Modele modele, String nomModele, ListenerGetModele listenerGetModele){



        for(int i = 0; i <  sequenceDeChargement.length; )

        if ( sequenceDeChargement.length == 1){
            chargementViaSequence(modele, getCheminSauvegarde(nomModele), listenerGetModele, i );
            terminerChargement(modele, listenerGetModele);
            listenerGetModele.reagirAuModele(modele);
        } else{

            chargementViaSourceCouranteOuSuivante(modele, getCheminSauvegarde(nomModele), listenerGetModele, i);


        }





    }

    private static void chargementViaSequence(Modele modele, String cheminSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante ){

    }

    private static void chargementViaSourceCouranteOuSuivante(final Modele modele, final String cheminSauvegarde, final ListenerGetModele listenerGetModele, final int indiceSourceCourante ){

     sequenceDeChargement[indiceSourceCourante].chargerModele(cheminSauvegarde, new ListenerChargement() {
         @Override
         public void reagirSucces(Map<String, Object> objetJson) {

             terminerChargementAvecDonnes(objetJson, modele, listenerGetModele);

         }

         @Override
         public void reagirErreur(Exception e) {

             chargementViaSourceSuivante(modele,cheminSauvegarde, listenerGetModele, indiceSourceCourante);

         }
     });
    }

    private static void terminerChargementAvecDonnes(Map<String, Object> objetJson, Modele modele, ListenerGetModele listenerGetModele){

        terminerChargement(modele, listenerGetModele);
    }
    private static void terminerChargement(Modele modele, ListenerGetModele listenerGetModele){

        

    }

    private static void chargementViaSourceSuivante(Modele modele, String cheminSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante){



        chargementViaSequence(modele,cheminSauvegarde, listenerGetModele, indiceSourceCourante + 1 );

    }


}

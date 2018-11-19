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
import ca.cours5b5.laurenperez.modeles.Identifiable;
import ca.cours5b5.laurenperez.modeles.MParametres;
import ca.cours5b5.laurenperez.modeles.MParametresPartie;
import ca.cours5b5.laurenperez.modeles.MPartie;
import ca.cours5b5.laurenperez.modeles.MPartieReseau;
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

            sourceDeDonnees.sauvegarderModele(getCheminSauvegarde(nomModele), objetJson);

        }
    }

    static void getModele(String nomModele, final  ListenerGetModele listenerGetModele){

        Modele modele = modelesEnMemoire.get(nomModele);


        if(modele == null){

          //verifier
            //creerModeleEtChargerDonnees(nomModele, listenerGetModele);

            creerModeleEtChargerDonnees(nomModele, listenerGetModele);// {

                //@Override
                //public void reagirAuModele(Modele modele) {

                   // listenerGetModele.reagirAuModele(modele);

              //  }

           // });

       /* }else if(nomModele.equals(MPartieReseau.class.getSimpleName())){
            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
                @Override
                public void reagirAuModele(Modele modele) {
                    MParametres mParametres = (MParametres) modele;
                    MPartieReseau mPartieReseau = new MPartieReseau(mParametres.getParametresPartie().cloner());
                    listenerGetModele.reagirAuModele(mPartieReseau);
                }
            });*/
        }else{
            listenerGetModele.reagirAuModele(modele);

        }


    }


  //  private static Modele chargerViaSequenceDeChargement(final String nomModele){

        //Modele modele = creerModeleSelonNom(nomModele);

       // modelesEnMemoire.put(nomModele, modele);

      //  for(SourceDeDonnees sourceDeDonnees : sequenceDeChargement){

          //  Map<String, Object> objetJson = sourceDeDonnees.chargerModele(nomModele);

          //  if(objetJson != null){

               // modele.aPartirObjetJson(objetJson);
             //   break;

          //  }

       // }

       // return modele;


      //  return null;
   // }

    public static void sauvegarderModele(String nomModele) throws ErreurModele {

        for(SourceDeDonnees source : listeDeSauvegardes){

            sauvegarderModeleDansCetteSource(nomModele, source);

        }

    }


    private static void creerModeleSelonNom(String nomModele, final ListenerGetModele listenerGetModele) throws ErreurModele{

        if(nomModele.equals(MParametres.class.getSimpleName())){

             listenerGetModele.reagirAuModele( new MParametres() );


        }else if(nomModele.equals(MPartie.class.getSimpleName())){

            //return new MPartie(mParametres.getParametresPartie().cloner());


            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
                @Override
                public void reagirAuModele(Modele modele) {

                    MParametres mParametres = (MParametres) modele;
                    MPartie mPartie = new MPartie(mParametres.getParametresPartie().cloner());
                    listenerGetModele.reagirAuModele(mPartie);
                }
            });
        }else if(nomModele.equals(MPartieReseau.class.getSimpleName())){
            getModele(MParametres.class.getSimpleName(), new ListenerGetModele() {
                @Override
                public void reagirAuModele(Modele modele) {

                    MParametres mParametres = (MParametres) modele;

                    MPartieReseau mPartieReseau = new MPartieReseau(mParametres.getParametresPartie().cloner());

                    listenerGetModele.reagirAuModele(mPartieReseau);
                }
            });

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

        Modele modele = modelesEnMemoire.get(nomModele);
        String cheminSauvegarde = nomModele;

        if(modele instanceof Identifiable){
            cheminSauvegarde += "/" +((Identifiable) modele).getId();
        } else{

            cheminSauvegarde +=  "/" + UsagerCourant.getId();

        }

        return cheminSauvegarde;
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

       //Modele modele = creerModeleSelonNom(nomModele, listenerGetModele);

       //listenerGetModele.reagirAuModele(modele);


        creerModeleSelonNom(nomModele, new ListenerGetModele() {

            @Override
            public void reagirAuModele(Modele modele) {


                modelesEnMemoire.put(nomModele, modele);

                chargerDonnees(modele, nomModele, listenerGetModele);

            }
        });




    }

    private static void chargerDonnees(Modele modele, String nomModele, ListenerGetModele listenerGetModele){


//verifier
        int i = 0;


        chargementViaSequence(modele, getCheminSauvegarde(nomModele), listenerGetModele, i );







    }

    private static void chargementViaSequence(Modele modele, String cheminSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante ){


        if (indiceSourceCourante >= sequenceDeChargement.length){

            terminerChargement(modele, listenerGetModele);
        } else{

            chargementViaSourceCouranteOuSuivante(modele, cheminSauvegarde, listenerGetModele, indiceSourceCourante);

        }
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

        modele.aPartirObjetJson(objetJson);
        terminerChargement(modele, listenerGetModele);
    }
    private static void terminerChargement(Modele modele, ListenerGetModele listenerGetModele){

        listenerGetModele.reagirAuModele(modele);

    }

    private static void chargementViaSourceSuivante(Modele modele, String cheminSauvegarde, ListenerGetModele listenerGetModele, int indiceSourceCourante){



        chargementViaSequence(modele,cheminSauvegarde, listenerGetModele, (indiceSourceCourante + 1 ));

    }


}

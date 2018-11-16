package ca.cours5b5.laurenperez.proxy;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.List;

import ca.cours5b5.laurenperez.controleurs.Action;
import ca.cours5b5.laurenperez.controleurs.interfaces.Fournisseur;
import ca.cours5b5.laurenperez.global.GCommande;

public class ProxyListe  extends Proxy implements Fournisseur{

    private ChildEventListener childEventListener;
    private Query requete;
    private Action actionNouvelItem;

    private List<DatabaseReference> noeudAjoutes;




    public ProxyListe(String cheminServeur) {
        super(cheminServeur);
    }

    public void setActionNouvelItem(GCommande commande){

    }

    public void ajouterValeur(Object valeur){

    }


    @Override
    public void connecterAuServeur(){

    }
    @Override
    public void deconnecterAuServeur(){

    }


    @Override
    public void detruireValeurs() {

    }

    private void creerListener(){

    }

    protected Query getRequete(){

    }
}

package entites;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by tgaudry on 02/10/15.
 */
public class Slide implements Comparable<Slide> {

    private Evenement majeur;

    private List<Evenement> listeMineur;

    private Date date;

    public Slide(Evenement majeur, List<Evenement> listeMineur, Date date) {
        this.majeur = majeur;
        this.listeMineur = listeMineur;
        this.date = date;
    }

    public Slide(Collection<Evenement> listEvent){
        this.listeMineur = new ArrayList<Evenement>();
        if(listEvent != null && !listEvent.isEmpty()){
            for(Evenement evt : listEvent){
                if(evt.getType().matches("QUE|MES|INF")){
                    this.listeMineur.add(evt);
                }
                else{
                    this.majeur = evt;
                    this.date = evt.getDate();
                }
            }
        }
    }

    public Evenement getMajeur() {
        return majeur;
    }

    public List<Evenement> getListeMineur() {
        return listeMineur;
    }

    public Date getDate() {
        return date;
    }

    public void setMajeur(Evenement majeur) {
        this.majeur = majeur;
    }

    public void setListeMineur(List<Evenement> listeMineur) {
        this.listeMineur = listeMineur;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(Slide another) {
        int ret = 0;
        if(this.getDate() != null && another.getDate() != null){
            ret = this.getDate().compareTo(another.getDate());
        }
        return ret;
    }
}

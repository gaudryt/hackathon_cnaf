package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import entites.Evenement;
import entites.Slide;

import org.alexd.jsonrpc.*;


/**
 * Created by tgaudry on 02/10/15.
 */
public class ServiceJSON {

    public List<Evenement> getListeEntite(String num) throws Exception {
        ArrayList<Evenement> liste = new ArrayList<Evenement>(10);
        try {
            JSONRPCClient client = JSONRPCClient.create("http://service/uri");
            client.setConnectionTimeout(2000);
            client.setSoTimeout(2000);
            String rep = "";
            /*try
            {*/
                rep = "0;INF;Ceci est un test visible.;Ceci est un test caché;01/01/2010\n";
                rep = rep + "1;QUE;Quelle est la couleur du cheval blanc d'Henry 4 ?;Blanc;01/02/2010\n";
                rep = rep + "2;DEM;Rouen;Paris;01/03/2010\n";
                rep = rep + "3;MES;Le saviez-vous ? Aujourd'hui a lieu le hackathon de la cnaf !;nill;01/04/2010\n";
                rep = rep + "4;INF;Vous pouvez recevoir 1000 euros pour cette année;nill;01/05/2010\n";
                rep = rep + "5;QUE;Qu'est ce qui est petit, jaune et qui fait peur ?;Un poussin avec une mitraillette;01/06/2010\n";
                rep = rep + "6;DEM;Terre;Mars;01/07/2010\n";
                rep = rep + "7;MES;Le saviez vous ? 1/2 des français ont droit à la CAF;Ca fait beaucoup;01/08/2010\n";
                rep = rep + "8;INF;Votre fiston fête ses 3 ans;Kado;01/09/2010\n";
                rep = rep + "9;QUE;Quel est l'age de la branche famille des allocations ?;60;01/10/2010\n";
                rep = rep + "10;QUE;Quelle est la couleur du cheval blanc d'Henry 4 ?;Blanc;01/02/2010\n";
                rep = rep + "11;QUE;Quelle est la couleur du cheval blanc d'Henry 4 ?;Blanc;01/02/2010\n";
                rep = rep + "12;DEM;Rouen;Paris;01/03/2010\n";
                rep = rep + "13;MES;Le saviez-vous ? Aujourd'hui a lieu le hackathon de la cnaf !;nill;01/04/2010\n";
                rep = rep + "14;INF;Vous pouvez recevoir 1000 euros pour cette année;nill;01/05/2010\n";
                rep = rep + "15;QUE;Qu'est ce qui est petit, jaune et qui fait peur ?;Un poussin avec une mitraillette;01/06/2010\n";
                rep = rep + "16;DEM;Terre;Mars;01/07/2010\n";
                rep = rep + "17;MES;Le saviez vous ? 1/2 des français ont droit à la CAF;Ca fait beaucoup;01/08/2010\n";
                rep = rep + "18;INF;Votre fiston fête ses 3 ans;Kado;01/09/2010\n";
                rep = rep + "19;QUE;Quel est l'age de la branche famille des allocations ?;60;01/10/2010\n";
            /*}
            catch (JSONRPCException e)
            {
                e.printStackTrace();
            }*/
            if(rep != ""){
                String[] format = rep.split("\n");
                for(int i = 0; i <= 9; i++){
                    String[] ligne = format[i].split(";");
                    String dtStart = ligne[4];
                    Date date = new Date();
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/aaaa");
                        date = dateFormat.parse("12/02/2009");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    liste.add(new Evenement(Long.parseLong(ligne[0]), ligne[1], ligne[2], ligne[3], date));
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return liste;
    }

    public List<Slide> getSlide(String num) throws Exception {
        List<Slide> slides = new ArrayList<Slide>();
        List<Evenement> event = new ArrayList<Evenement>();
        event = getListeEntite(num);
        Collections.sort(event);
        int year = 0;
        HashMap<Integer, List<Evenement>> map = new HashMap<Integer, List<Evenement>>();
        for(Evenement evt : event){
            if(map.get(new Integer(evt.getDate().getYear())) != null){
                map.get(new Integer(evt.getDate().getYear())).add(evt);
            }
            else {
                List<Evenement> temp = new ArrayList<Evenement>();
                temp.add(evt);
                map.put(new Integer(evt.getDate().getYear()), temp);
            }
        }
        for(Integer annee : map.keySet()){
            slides.add(new Slide(map.get(annee)));
        }
        return slides;
    }
}

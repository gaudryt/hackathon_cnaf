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
                rep = "0;Votre naissance allocataire;Ceci est un test visible.;Ceci est un test caché;01/01/2008\n";
                rep = rep + "1;Sans changement majeur;Ceci est un test visible.;Ceci est un test caché;01/01/2009\n";
                rep = rep + "2;Naissance de votre premier enfant et votre arrivée dans la ville de : Lille;Ceci est un test visible.;Ceci est un test caché;01/01/2012\n";
                rep = rep + "3;Naissance de votre deuxième enfant;Ceci est un test caché;01/01/2015\n";

            /*}
            catch (JSONRPCException e)
            {
                e.printStackTrace();
            }*/
            if(rep != ""){
                String[] format = rep.split("\n");
                for(int i = 0; i <= 3; i++){
                    String[] ligne = format[i].split(";");
                    String dtStart = ligne[4];
                    Date date = new Date();
                    try {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/aaaa");
                        date = dateFormat.parse(dtStart);
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

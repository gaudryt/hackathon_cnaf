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
            try
            {
                rep = client.callString("getListeObjets",  num);
            }
            catch (JSONRPCException e)
            {
                e.printStackTrace();
            }
            if(rep != ""){
                String[] format = rep.split("\n");
                for(int i = 0; i <= 9; i++){
                    String[] ligne = format[i].split(";");
                    String dtStart = ligne[4];
                    Date date = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/aaaa");
                    try {
                        date = dateFormat.parse(dtStart);
                        System.out.println(date);
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

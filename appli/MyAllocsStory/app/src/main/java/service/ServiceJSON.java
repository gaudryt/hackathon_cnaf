package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entites.Evenement;

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
}

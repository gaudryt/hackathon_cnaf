package com.caf.myallocsstroy.myallocsstory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import entites.Evenement;
import service.ServiceJSON;

/**
 * Created by tgaudry on 02/10/2015.
 */
public class ScreenSlidePageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = null;
        List<Evenement> evenementList = getDataTemp();
        int slideNum = getArguments().getInt("slideNum");
        if (slideNum % 2 == 0){
            //slide year
            v = inflater.inflate(R.layout.fragment_screen_slide_year_page, container, false);
            TextView textView = (TextView) v.findViewById(R.id.yearLabel);
            textView.setText(""+getYear(slideNum/2));
            v.setBackgroundColor(Color.rgb(23, 144, 135));

            //animation de l'année
            Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.animate_year);
            a.reset();
            textView.clearAnimation();
            textView.startAnimation(a);

            String yearMessage = "";
            if (slideNum == 0){
                //slide number one
                yearMessage = "Tout commence !";
            } else if(getYear(slideNum/2) == 2015){
                //slide final
                yearMessage = "Ajourd'hui";
            } else{
                //slide intérmidiaire
                yearMessage = "L'hsitoire continue ...";
            }
            TextView textView2 = (TextView) v.findViewById(R.id.yearMessage);
            textView2.setText(yearMessage);
            Animation b = AnimationUtils.loadAnimation(getContext(), R.anim.animate_year_message);
            b.reset();
            textView2.clearAnimation();
            textView2.startAnimation(b);
        }else{
            //slide data
            v = inflater.inflate(R.layout.fragment_screen_slide_data_page, container, false);
            TextView textView = (TextView) v.findViewById(R.id.evenementMajeurLabel);

            Evenement evenementMajeur = getSlideData(slideNum / 2);

            textView.setText("En "+getYear(slideNum/2)+", "+ evenementMajeur.getInfos());
            v.setBackgroundColor(Color.rgb(208, 38, 97));

            //animation du slide des données
            Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.scale_event);
            a.reset();
            textView.clearAnimation();
            textView.startAnimation(a);
            TextView textViewDetails = (TextView) v.findViewById(R.id.autresEvenementLabel);
            if (getYear(slideNum/2) == 2008) {
                textViewDetails.setText("Mon premier versement des APL\n\nMon premier versement du RMI\n\nMa CAF de Rattachement : CAF de Paris\n\nLe saviez-vous ? En 2008 il y avait 1.13 millions de bénéficiaires du RMI\n\nLe saviez-vous ? EN 2008, il y avait 395 172 foyers allocataires rattachés à la CAF de Paris");
            } else if (getYear(slideNum/2) == 2009){
                textViewDetails.setText("Mon premier versement des APL\n\nMon premier versement du RMI\n\nMa CAF de Rattachement : CAF de Paris\n\nLe saviez-vous ? En 2008 il y avait 1.13 millions de bénéficiaires du RMI\n\nLe saviez-vous ? EN 2008, il y avait 395 172 foyers allocataires rattachés à la CAF de Paris");
            }else if (getYear(slideNum/2) == 2012){
                textViewDetails.setText("Mon premier versement des APL\n\nMon premier versement du RMI\n\nMa CAF de Rattachement : CAF de Paris\n\nLe saviez-vous ? En 2008 il y avait 1.13 millions de bénéficiaires du RMI\n\nLe saviez-vous ? EN 2008, il y avait 395 172 foyers allocataires rattachés à la CAF de Paris");
            }else if (getYear(slideNum/2) == 2015){
                textViewDetails.setText("Mon premier versement des APL\n\nMon premier versement du RMI\n\nMa CAF de Rattachement : CAF de Paris\n\nLe saviez-vous ? En 2008 il y avait 1.13 millions de bénéficiaires du RMI\n\nLe saviez-vous ? EN 2008, il y avait 395 172 foyers allocataires rattachés à la CAF de Paris");
            }
        }
        return v;
    }

    public static ScreenSlidePageFragment newInstance(int slideNum) {
        ScreenSlidePageFragment f = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt("slideNum", slideNum);
        f.setArguments(args);
        return f;
    }

    //renvoit l'évènement à afficher
    private Evenement getSlideData(int evenementIndex){
        return getDataTemp().get(evenementIndex);
    }
    //renvoit l'année en partant d'une Date
    private int getYear(int evenementIndex){
        Date date = getDataTemp().get(evenementIndex).getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    //renvoit la couleur du layout correspondante à l'évènement
    /*private int getColor(String eventType){
        int color;
        switch (eventType) {
            case "mar":
                color = Color.rgb(255, 228, 225);
                break;
            case "nai":
                color = Color.rgb(175,238,238);
                break;
            case "dem":
                color = Color.rgb(229,255,204) ;
                break;
            default:
                color = 0;
                break;
        }
        return color;
    }*/
    private List<Evenement> getDataTemp(){
        List<Evenement> result = new ArrayList<Evenement>();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date date2008 = formatter.parse("12/02/2008");
            result.add(new Evenement((long) 0, "Votre naissance allocataire", "infosCachees", "mar", date2008));

            Date date2009 = formatter.parse("12/02/2009");
            result.add(new Evenement((long) 1, "Pas de changement majeur", "infosCachees1", "nai", date2009));

            Date date2012 = formatter.parse("12/02/2012");
            result.add(new Evenement((long) 2, "Naissance de votre premier enfant et votre arrivée dans la ville de : Lille", "infosCachees2", "dem", date2012));

            Date date2015 = formatter.parse("12/02/2015");
            result.add(new Evenement((long) 2, "Naissance de votre deuxième enfant", "infosCachees2", "dem", date2015));
        }catch (ParseException e)
            {
                e.printStackTrace();
            }
        return result;
        /*
        rep = "0;Votre naissance allocataire;Ceci est un test visible.;Ceci est un test caché;01/01/2008\n";
                rep = rep + "1;Sans changement majeur;Ceci est un test visible.;Ceci est un test caché;01/01/2009\n";
                rep = rep + "2;Naissance de votre premier enfant et votre arrivée dans la ville de : Lille;Ceci est un test visible.;Ceci est un test caché;01/01/2012\n";
                rep = rep + "3;Naissance de votre deuxième enfant;Ceci est un test caché;01/01/2015\n";ServiceJSON serviceJSON = new ServiceJSON();

        try {
            result = serviceJSON.getListeEntite("02030201023");
        } catch(Exception e) {
            e.printStackTrace();
        }

        return result;*/
    }

}

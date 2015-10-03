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
            v.setBackgroundColor(Color.LTGRAY);

            //animation de l'année
            Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.animate_year);
            a.reset();
            textView.clearAnimation();
            textView.startAnimation(a);
        }else{
            //slide data
            v = inflater.inflate(R.layout.fragment_screen_slide_data_page, container, false);
            TextView textView = (TextView) v.findViewById(R.id.evenementMajeurLabel);

            Evenement evenementMajeur = getSlideData(slideNum / 2);
            textView.setText("En "+getYear(slideNum/2)+", "+ evenementMajeur.getInfos());
            v.setBackgroundColor(getColor(evenementMajeur.getType()));

            //animation du slide des données
            Animation a = AnimationUtils.loadAnimation(getContext(), R.anim.scale_event);
            a.reset();
            textView.clearAnimation();
            textView.startAnimation(a);
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
    private int getColor(String eventType){
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
    }
    private List<Evenement> getDataTemp(){
        List<Evenement> result = new ArrayList<Evenement>();
        /*SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date date2009 = formatter.parse("12/02/2009");
            result.add(new Evenement((long) 0, "vous vous êtes marié", "infosCachees", "mar", date2009));

            Date date2011 = formatter.parse("12/02/2011");
            result.add(new Evenement((long) 1, "vous avez eu un enfant ", "infosCachees1", "nai", date2011));

            Date date2013 = formatter.parse("12/02/2013");
            result.add(new Evenement((long) 2, "vous avez déménagé", "infosCachees2", "dem", date2013));
        }catch (ParseException e)
            {
                e.printStackTrace();
            }
        return result;*/
        ServiceJSON serviceJSON = new ServiceJSON();

        try {
            result = serviceJSON.getListeEntite("02030201023");
        } catch(Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}

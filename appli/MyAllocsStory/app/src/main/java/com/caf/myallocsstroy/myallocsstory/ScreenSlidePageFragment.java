package com.caf.myallocsstroy.myallocsstory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by tgaudry on 02/10/2015.
 */
public class ScreenSlidePageFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_screen_slide_page, container, false);
        TextView textView = (TextView) v.findViewById(R.id.myTextFromActivity);
        textView.setText("je suis sur la page " + getArguments().getInt("slideNum"));

        return v;
    }

    public static ScreenSlidePageFragment newInstance(int slideNum) {
        ScreenSlidePageFragment f = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt("slideNum", slideNum);
        f.setArguments(args);
        return f;
    }



}

package com.hao.turing.Splash;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hao.turing.Main.LoginActivity;
import com.hao.turing.Main.MainActivity;
import com.hao.turing.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SplashThreeFragment extends Fragment {

    Button startButton;

    public SplashThreeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_splash_three, container, false);
        startButton = view.findViewById(R.id.splash_fragment_start_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*直接跳转到MainActivity*/
                Intent intent = new Intent();
                intent.setClass(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }

}

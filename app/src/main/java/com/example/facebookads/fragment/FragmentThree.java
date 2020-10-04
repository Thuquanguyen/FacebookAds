package com.example.facebookads.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.facebookads.R;
import com.example.facebookads.RegisterActivity;
import com.example.facebookads.WebviewActivity;


public class FragmentThree extends Fragment {
    private View view;
    private Button btContinueFb;

    public FragmentThree() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_there,container,false);
        btContinueFb= (Button) view.findViewById(R.id.continue_fb);
        btContinueFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
package com.example.facebookads.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.facebookads.R;
import com.example.facebookads.WebviewActivity;


public class FragmentTwo extends Fragment {
    private View view;
    private ImageView imClose;

    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_two,container,false);
        anhXa();
        imClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WebviewActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    private void anhXa(){
        imClose = (ImageView) view.findViewById(R.id.img_close);
    }
}
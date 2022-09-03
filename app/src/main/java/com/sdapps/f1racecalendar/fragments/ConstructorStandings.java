package com.sdapps.f1racecalendar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sdapps.f1racecalendar.R;


public class ConstructorStandings extends Fragment {


    public ConstructorStandings() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_constructor_standings, container, false);
    }
}
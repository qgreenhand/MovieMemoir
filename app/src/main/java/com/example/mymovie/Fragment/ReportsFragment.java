package com.example.mymovie.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mymovie.R;

public class ReportsFragment extends Fragment {
    private TextView textView;

    public ReportsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the View for this fragment
        View view = inflater.inflate(R.layout.reports_fragement, container, false);
        textView=view.findViewById(R.id.tvreport);
        textView.setText("This is reports");
        return view;

    }
}
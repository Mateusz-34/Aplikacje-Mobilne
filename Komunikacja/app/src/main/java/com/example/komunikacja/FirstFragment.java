package com.example.komunikacja;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    private TextView textView;

    public FirstFragment() {
        super(R.layout.fragment_first);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        textView = view.findViewById(R.id.textViewFragment);
    }

    public void updateText(String newText) {
        textView.setText(newText);
    }
}
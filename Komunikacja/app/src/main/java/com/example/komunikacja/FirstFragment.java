package com.example.komunikacja;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {

    interface OnButtonClickedListener {
        void onButtonClick();
    }

    OnButtonClickedListener listener;

    public FirstFragment() {
        super(R.layout.fragment_first);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        listener = (OnButtonClickedListener) context;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button btn = view.findViewById(R.id.btnInsideFragment);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onButtonClick();
            }
        });
    }
}
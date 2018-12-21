package com.example.actionviewsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.actionview.ActionView;
import com.example.actionview.ActionClickListener;

public class MainActivity extends AppCompatActivity {

    private ActionView actionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionView = findViewById(R.id.actionView);

        actionView.setActionImage(R.drawable.ic_no_internet)
                .setActionImageTint(getResources().getColor(R.color.white))
                .setActionImageSize(200)
                .setActionTitle("Oops...!!")
                .setActionTitleColor(getResources().getColor(R.color.white))
                .setActionSubtitle("Looks like you don't have internet connection. Please try again later")
                .setActionSubtitleColor(getResources().getColor(R.color.white))
                .setActionText("Retry")
                .setActionListener(new ActionClickListener() {
                    @Override
                    public void onAction() {
                    // TODO: Do whatever you want
                    }
                });


    }
}

package com.dmt.dangtus.kiemtra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.dmt.dangtus.kiemtra.adapter.CongAnAdapter;
import com.dmt.dangtus.kiemtra.model.CongAn;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
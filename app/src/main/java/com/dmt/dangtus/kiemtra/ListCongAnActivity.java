package com.dmt.dangtus.kiemtra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import com.dmt.dangtus.kiemtra.adapter.CongAnAdapter;
import com.dmt.dangtus.kiemtra.model.CongAn;

import java.util.ArrayList;
import java.util.List;

public class ListCongAnActivity extends AppCompatActivity {

    private ListView lvCongAn;
    private List<CongAn> congAnList;
    private CongAnAdapter adapter;
    private ImageButton logout;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cong_an);

        anhXa();

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        adapter = new CongAnAdapter(this, R.layout.item_cong_an, congAnList);
        lvCongAn.setAdapter(adapter);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListCongAnActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        lvCongAn = findViewById(R.id.congan_listview);
        logout = findViewById(R.id.logout_button);

        congAnList = new ArrayList<>();
        congAnList.add(new CongAn("Nguyễn Văn Công An 1", "", "Đại tá", "Hà Nội", "Việt Nam", 5));
        congAnList.add(new CongAn("Nguyễn Văn Công An 2", "", "Đại úy", "Đà Nẵng", "Việt Nam", 5));
        congAnList.add(new CongAn("Nguyễn Văn Công An 3", "", "Trung úy", "Hà Nội", "Việt Nam", 5));
        congAnList.add(new CongAn("Nguyễn Văn Công An 4", "", "Thượng úy", "TP Hồ Chí Minh", "Việt Nam", 5));
        congAnList.add(new CongAn("Nguyễn Văn Công An 5", "", "Trung tá", "Hà Nội", "Việt Nam", 5));
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
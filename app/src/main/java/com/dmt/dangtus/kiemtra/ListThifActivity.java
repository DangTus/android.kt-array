package com.dmt.dangtus.kiemtra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dmt.dangtus.kiemtra.adapter.ThifAdapter;
import com.dmt.dangtus.kiemtra.model.Thif;

import java.util.ArrayList;
import java.util.List;

public class ListThifActivity extends AppCompatActivity {
    private ListView lvPhepTinh;
    private ThifAdapter adapter;
    private List<Thif> thifList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pheptinh);

        anhXa();

        thifList = new ArrayList<>();
        thifList.add(new Thif("1", "Thì hiện tại đơn", "S + V(e/es) + 0"));
        thifList.add(new Thif("2", "Thì hiện tại tiếp diễn", "S + V-ing + 0"));
        thifList.add(new Thif("3", "Thì quá khứ đơn", "S + V2/V-ed + 0"));
        thifList.add(new Thif("4", "Thì quá khứ tiếp diễn", "S + was/were + V-ing + 0"));
        thifList.add(new Thif("5", "Thì quá khứ hoàn thành", "S + had + V3 + 0"));
        thifList.add(new Thif("6", "Thì tương lai đơn", "S + will + V + 0"));

        adapter = new ThifAdapter(this, R.layout.item_thif, thifList);
        lvPhepTinh.setAdapter(adapter);

        lvPhepTinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListThifActivity.this, DetailActivity.class);
                intent.putExtra("data", thifList.get(i));
                startActivity(intent);
            }
        });
    }

    private void anhXa() {
        lvPhepTinh = findViewById(R.id.pheptinh_listview);
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}
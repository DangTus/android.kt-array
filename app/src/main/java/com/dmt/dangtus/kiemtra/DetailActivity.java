package com.dmt.dangtus.kiemtra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dmt.dangtus.kiemtra.model.Thif;

public class DetailActivity extends AppCompatActivity {
    private TextView txtTenThif, txtCongThuc, txtVidu;
    private EditText edtVidu;
    private Button btnLuuViDu;
    private Thif thif;
    private SharedPreferences sharedPreferences;
    private String listViDuDaLuu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        anhXa();

        Intent intent = getIntent();
        if (intent != null) {
            thif = (Thif) intent.getSerializableExtra("data");
            txtTenThif.setText(thif.getTen().toUpperCase());
            txtCongThuc.setText("Công thức: " + thif.getCongThuc());
        }

        sharedPreferences = getSharedPreferences("dataViDu", MODE_PRIVATE);
        listViDuDaLuu = sharedPreferences.getString(thif.getId(), "");
        txtVidu.setText(listViDuDaLuu);

        btnLuuViDu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String viDu = edtVidu.getText().toString().trim();
                
                if(viDu.isEmpty()) {
                    Toast.makeText(DetailActivity.this, "Vui lòng nhập", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    String viDuMoi = viDu + "\n" + listViDuDaLuu;
                    editor.remove(thif.getId());
                    editor.putString(thif.getId(), viDuMoi);
                    editor.commit();

                    listViDuDaLuu = viDuMoi;
                    txtVidu.setText(listViDuDaLuu);

                    Toast.makeText(DetailActivity.this, "Thêm ví dụ thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void anhXa() {
        txtVidu = findViewById(R.id.vidudaluu_textview);
        edtVidu = findViewById(R.id.vidu_edittext);
        btnLuuViDu = findViewById(R.id.luuvidu_button);
        txtTenThif = findViewById(R.id.tenthi_textview);
        txtCongThuc = findViewById(R.id.congThuc_textview);
    }
}
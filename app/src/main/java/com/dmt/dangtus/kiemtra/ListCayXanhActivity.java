package com.dmt.dangtus.kiemtra;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.dmt.dangtus.kiemtra.adapter.CayXanhAdapter;
import com.dmt.dangtus.kiemtra.model.CayXanh;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListCayXanhActivity extends AppCompatActivity {
    private ListView lvCayXanh;
    private List<CayXanh> cayXanhList;
    private CayXanhAdapter adapter;
    private ImageButton logout, dialogAdd;
    private FirebaseDatabase database;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_cayxanh);
        database = FirebaseDatabase.getInstance();

        anhXa();

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        cayXanhList = new ArrayList<>();
        adapter = new CayXanhAdapter(this, R.layout.item_cayxanh, cayXanhList);
        lvCayXanh.setAdapter(adapter);
        getData();

        dialogAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogAdd();
            }
        });

        lvCayXanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showDialogDetail(i);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListCayXanhActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getData() {
        DatabaseReference mRef = database.getReference("data");

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {
                    CayXanh cayXanh = data.getValue(CayXanh.class);
                    cayXanhList.add(cayXanh);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ListCayXanhActivity.this, "Lỗi kết nối intenet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDialogAdd() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add);
        dialog.setCanceledOnTouchOutside(false);

        //anh xa
        final EditText edtTenThuongGoi = dialog.findViewById(R.id.tenthuonggoi_textview);
        final EditText edtTenKhoaHoc = dialog.findViewById(R.id.tenkhoahoc_textview);
        final EditText edtDacTinh = dialog.findViewById(R.id.dactinh_textview);
        final EditText edtMauLa = dialog.findViewById(R.id.maula_textview);
        final EditText edtLinkHinhAnh = dialog.findViewById(R.id.linkhinhanh_textview);
        final Button btnHuy = dialog.findViewById(R.id.huy_button);
        final Button btnThem = dialog.findViewById(R.id.them_button);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CayXanh cayXanh = new CayXanh();

                //set new id
                int newID;
                if (cayXanhList.size() == 0) {
                    newID = 0;
                } else {
                    newID = cayXanhList.get(cayXanhList.size() - 1).getId() + 1;
                }
                cayXanh.setId(newID);

                cayXanh.setTenThuongGoi(edtTenThuongGoi.getText().toString().trim());
                cayXanh.setTenKhoaHoc(edtTenKhoaHoc.getText().toString().trim());
                cayXanh.setDacTinh(edtDacTinh.getText().toString().trim());
                cayXanh.setMauLa(edtMauLa.getText().toString().trim());
                cayXanh.setHinhAnh(edtLinkHinhAnh.getText().toString().trim());

                editData(cayXanh);

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showDialogDetail(int pos) {
        CayXanh cayXanh = cayXanhList.get(pos);

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_detail);
        dialog.setCanceledOnTouchOutside(false);

        //anh xa
        final EditText edtTenThuongGoi = dialog.findViewById(R.id.tenthuonggoi_textview);
        final EditText edtTenKhoaHoc = dialog.findViewById(R.id.tenkhoahoc_textview);
        final EditText edtDacTinh = dialog.findViewById(R.id.dactinh_textview);
        final EditText edtMauLa = dialog.findViewById(R.id.maula_textview);
        final EditText edtLinkHinhAnh = dialog.findViewById(R.id.linkhinhanh_textview);
        final Button btnxoa = dialog.findViewById(R.id.xoa_button);
        final Button btnHuy = dialog.findViewById(R.id.huy_button);
        final Button btnSua = dialog.findViewById(R.id.sua_button);

        //set du lieu
        edtTenThuongGoi.setText(cayXanh.getTenThuongGoi());
        edtTenKhoaHoc.setText(cayXanh.getTenKhoaHoc());
        edtDacTinh.setText(cayXanh.getDacTinh());
        edtMauLa.setText(cayXanh.getMauLa());
        edtLinkHinhAnh.setText(cayXanh.getHinhAnh());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cayXanh.setTenThuongGoi(edtTenThuongGoi.getText().toString().trim());
                cayXanh.setTenKhoaHoc(edtTenKhoaHoc.getText().toString().trim());
                cayXanh.setDacTinh(edtDacTinh.getText().toString().trim());
                cayXanh.setMauLa(edtMauLa.getText().toString().trim());
                cayXanh.setHinhAnh(edtLinkHinhAnh.getText().toString().trim());

                editData(cayXanh);

                dialog.dismiss();
            }
        });

        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData(cayXanh.getId());
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void editData(CayXanh cayXanh) {
        DatabaseReference mRef = database.getReference("data/" + cayXanh.getId());

        mRef.setValue(cayXanh, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(ListCayXanhActivity.this, "Thành công", Toast.LENGTH_SHORT).show();

                cayXanhList.clear();
                getData();
            }
        });
    }

    private void deleteData(int pos) {
        DatabaseReference mRef = database.getReference("data/" + pos);
        mRef.removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(ListCayXanhActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();

                cayXanhList.clear();
                getData();
            }
        });
    }

    private void anhXa() {
        lvCayXanh = findViewById(R.id.cayxanh_listview);
        logout = findViewById(R.id.logout_button);
        dialogAdd = findViewById(R.id.dialog_add_button);
    }

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }
}
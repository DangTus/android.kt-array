package com.dmt.dangtus.kiemtra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private EditText edUserName, edPassword;
    private Button btnLogin;
    private ImageButton imbEye;
    private SharedPreferences sharedPreferences;
    private CheckBox cbRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        anhXa();

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        edUserName.setText(sharedPreferences.getString("userName", ""));
        edPassword.setText(sharedPreferences.getString("password", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("remember", false));

        //bat su kien click vao nut dang nhap
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = edUserName.getText().toString().trim();
                String password = edPassword.getText().toString().trim();

                if(userName.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
                } else {
                    if(userName.equals("admin") && password.equals("admin")) {
                        if(cbRemember.isChecked()) {
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString("userName", userName);
                            editor.putString("password", password);
                            editor.putBoolean("remember", true);

                            editor.commit();
                        }

                        Intent intent = new Intent(LoginActivity.this, ListCongAnActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không đúng, vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //su kien hidden or show password
        imbEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((Integer) imbEye.getTag() == R.drawable.ic_eye_hide) {
                    imbEye.setImageResource(R.drawable.ic_eye);
                    imbEye.setTag(R.drawable.ic_eye);

                    //129 la kieu password
                    edPassword.setInputType(129);
                } else if ((Integer) imbEye.getTag() == R.drawable.ic_eye) {
                    imbEye.setImageResource(R.drawable.ic_eye_hide);
                    imbEye.setTag(R.drawable.ic_eye_hide);

                    edPassword.setInputType(InputType.TYPE_CLASS_TEXT);
                }

                //dua vi tri con tro ve phia cuoi van ban
                int position = edPassword.length();
                Editable etext = edPassword.getText();
                Selection.setSelection(etext, position);
            }
        });
    }

    private void anhXa() {
        btnLogin = findViewById(R.id.loginButton);

        edUserName = findViewById(R.id.userNameEditText);
        edPassword = findViewById(R.id.passwordEditText);

        imbEye = findViewById(R.id.eyeIMB);
        imbEye.setTag(R.drawable.ic_eye);

        cbRemember = findViewById(R.id.remember_checkbox);
    }
}
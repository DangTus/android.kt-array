package com.dmt.dangtus.kiemtra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText edUsername, edPassword;
    private Button btnLogin;
    private ImageButton imbEye;
    private SharedPreferences sharedPreferences;
    private CheckBox cbRemember;
    private TextView txtSignupLayout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        anhXa();

        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        edUsername.setText(sharedPreferences.getString("userName", ""));
        edPassword.setText(sharedPreferences.getString("password", ""));
        cbRemember.setChecked(sharedPreferences.getBoolean("remember", false));

        //bat su kien click vao nut dang nhap
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        //su kien hidden or show password
        imbEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEyePassword();
            }
        });

        //su kien khi nhan nut done tren ban phim
        edPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE) {
                    login();
                    return true;
                }
                return false;
            }
        });

        //su kien chuyen sang trang sign up
        txtSignupLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void login() {
        String user = edUsername.getText().toString().trim();
        String pass = edPassword.getText().toString().trim();
        if(user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập email và password", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if(cbRemember.isChecked()) {
                        editor.putString("userName", user);
                        editor.putString("password", pass);
                        editor.putBoolean("remember", true);
                    } else {
                        editor.remove("userName");
                        editor.remove("password");
                        editor.remove("remember");
                    }
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this, ListCayXanhActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Vui lòng đăng nhập lại", Toast.LENGTH_SHORT).show();
                    edPassword.setText("");
                }
            }
        });
    }

    private void setEyePassword() {
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

    private void anhXa() {
        btnLogin = findViewById(R.id.loginButton);

        edUsername = findViewById(R.id.userNameEditText);
        edPassword = findViewById(R.id.passwordEditText);

        imbEye = findViewById(R.id.eyeIMB);
        imbEye.setTag(R.drawable.ic_eye);

        cbRemember = findViewById(R.id.remember_checkbox);

        txtSignupLayout = findViewById(R.id.signup_layout);
    }
}
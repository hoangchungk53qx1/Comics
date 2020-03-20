package com.example.comics.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.comics.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {
private EditText fullNameRegister,emailRegister,edtPasswordRegister,edtRePasswordRegister;
private Button btnRegister;
private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
private ProgressBar progessBarRegis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initData();

    }

    private void initData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fullNameRegister.getText().toString().trim();
                String email= emailRegister.getText().toString().trim();
                String edtPass =  edtPasswordRegister.getText().toString();
                String edtRePass = edtRePasswordRegister.getText().toString();

                if(TextUtils.isEmpty(name)){
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập tên", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edtPass)){
                    Toast.makeText(RegisterActivity.this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (edtPass.length() < 6){
                    Toast.makeText(RegisterActivity.this, "Mật khẩu quá ngắn", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!edtRePass.equals(edtPass)){
                    Toast.makeText(RegisterActivity.this, "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                return;
                }
//                progessBarRegis.setVisibility(View.VISIBLE);

                if (firebaseAuth.getCurrentUser() !=null){
                    Toast.makeText(RegisterActivity.this, "Đã tạo tài khoản", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),HouseActivity.class));
                    finish();
                }
                firebaseAuth.createUserWithEmailAndPassword(email,edtPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Tài khoản đã được tạo", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        }
                        else {
                            Toast.makeText(RegisterActivity.this, "Có lỗi đã xảy ra "+ Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }

    private void initView() {
        fullNameRegister = findViewById(R.id.fullNameRegister);
        emailRegister    = findViewById(R.id.emailRegister);
        edtPasswordRegister = findViewById(R.id.edtPasswordRegister);
        edtRePasswordRegister = findViewById(R.id.edtRePasswordRegister);
        btnRegister = findViewById(R.id.btnRegister);

    }
}

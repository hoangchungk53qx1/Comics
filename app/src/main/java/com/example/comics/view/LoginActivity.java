package com.example.comics.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.comics.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
private EditText edtEmail,edtPassword;
private  Button btnLogin;
private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        intData();
    }

    private void intData() {
 btnLogin.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View v) {
         String email= edtEmail.getText().toString().trim();
         String edtPass =  edtPassword.getText().toString();

         if(TextUtils.isEmpty(email)){
             Toast.makeText(LoginActivity.this, "Bạn chưa nhập Email", Toast.LENGTH_SHORT).show();
             return;
         }
         if (TextUtils.isEmpty(edtPass)){
             Toast.makeText(LoginActivity.this, "Bạn chưa nhập mật khẩu", Toast.LENGTH_SHORT).show();
             return;
         }
         if (edtPass.length() < 6){
             Toast.makeText(LoginActivity.this, "Mật khẩu quá ngắn", Toast.LENGTH_SHORT).show();
             return;
         }

         firebaseAuth.signInWithEmailAndPassword(email,edtPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if(task.isSuccessful()){
                     Toast.makeText(LoginActivity.this, "Đăng nhập thành công !", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(getApplicationContext(),HouseActivity.class));
                 }
                 else {
                     Toast.makeText(LoginActivity.this, "Đăng nhập thất bại ", Toast.LENGTH_SHORT).show();
                 }
             }
         });
     }
 });
        
        
        
        
    }
    private void initView() {
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }
    public void clickRegister(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
}

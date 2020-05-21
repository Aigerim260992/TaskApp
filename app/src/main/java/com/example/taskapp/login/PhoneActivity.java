package com.example.taskapp.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.taskapp.MainActivity;
import com.example.taskapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneActivity extends AppCompatActivity {

    private EditText editPhone, editCode;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    LinearLayout numberWindow, codeWindow;
    String codeVerificator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        editPhone = findViewById(R.id.editPhone);
        editCode = findViewById(R.id.editCode);
        numberWindow = findViewById(R.id.numberWindow);
        codeWindow = findViewById(R.id.codeWindow);
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                Log.e("Phone","onVerificationCompleted");
                signIn(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.e("Phone","onVerificationFailed" +e.getMessage());
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                Log.e("Phone", "onCodeSent");
                codeVerificator = s;
            }
        };
    }

    private void signIn(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(PhoneActivity.this, MainActivity.class));
                    finish();
                } else{
                    Log.e("Phone", "Error = " + task.getException().getMessage());
                    Toast.makeText(PhoneActivity.this, "Ошибка авторизации", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

        public void onclickNumberWindow(View view) {
            String phone = editPhone.getText().toString().trim();
            PhoneAuthProvider.getInstance().verifyPhoneNumber(phone,
                    60, TimeUnit.SECONDS, this, callbacks);
            codeWindow.setVisibility(View.VISIBLE);
            numberWindow.setVisibility(View.GONE);

    }

    public void onClickCodeWindow(View view) {
        String code = editCode.getText().toString().trim();
        if(TextUtils.isEmpty(code)){
            return;
        }
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeVerificator, code);
        signIn(credential);

    }

}

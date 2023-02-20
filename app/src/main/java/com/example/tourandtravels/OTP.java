package com.example.tourandtravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class OTP extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Button verify;
    TextView resend;
    EditText one;
    EditText two;
    EditText three;
    EditText four;
    EditText five;
    EditText six;

    TextView tv_msg;

    String s;

    String name, email, password, phone;

    FirebaseAuth auth;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;

    String code;

    String otp;

    FirebaseDatabase fd;
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

         fd = FirebaseDatabase.getInstance();
        dr = fd.getReference("register");

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
//        username = intent.getStringExtra("username");
        email = intent.getStringExtra("email");
        password = intent.getStringExtra("password");
        phone = intent.getStringExtra("phone");

        one = findViewById(R.id.editTextTextPersonName6);
        two = findViewById(R.id.editTextTextPersonName7);
        three = findViewById(R.id.editTextTextPersonName8);
        four = findViewById(R.id.editTextTextPersonName9);
        five = findViewById(R.id.editTextTextPersonName11);
        six = findViewById(R.id.editTextTextPersonName12);

        six.setOnKeyListener(this);
        five.setOnKeyListener(this);
        four.setOnKeyListener(this);
        three.setOnKeyListener(this);
        two.setOnKeyListener(this);

        verify = findViewById(R.id.button4);
        resend = findViewById(R.id.textView18);

        tv_msg = findViewById(R.id.textView19);

        one.requestFocus();
        one.setCursorVisible(false);


        one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                s=one.getText().toString();

                if(s.length()==1)
                {
                    one.clearFocus();
                    two.requestFocus();
                    two.setCursorVisible(false);
                }
            }
        });

        two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                s=two.getText().toString();

                if(s.length()==1)
                {
                    two.clearFocus();
                    three.requestFocus();
                    three.setCursorVisible(false);
                }

            }
        });

        three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                s=three.getText().toString();

                if(s.length()==1)
                {
                    three.clearFocus();
                    four.requestFocus();
                    four.setCursorVisible(false);
                }

            }
        });

        four.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                s=four.getText().toString();

                if(s.length()==1)
                {
                    four.clearFocus();
                    five.requestFocus();
                    five.setCursorVisible(false);
                }

            }
        });

        five.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                s=five.getText().toString();

                if(s.length()==1)
                {
                    five.clearFocus();
                    six.requestFocus();
                    six.setCursorVisible(false);
                }

            }
        });


        verify.setOnClickListener(this);

        StartFireBaseLogin();

        sendVerificationCode("+91"+phone);

    }


    private void sendVerificationCode(String phone)
    {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(callbacks)
                .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void StartFireBaseLogin(){
        auth = FirebaseAuth.getInstance();
        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken){
                super.onCodeSent(s, forceResendingToken);

                tv_msg.setText("code sent");

                code = s;
            }
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                final String code = phoneAuthCredential.getSmsCode();

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e)
            {
                Toast.makeText(OTP.this, "error in sending OTP", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        };
    }

    void resendCode(String phone)
    {

        Log.e("code send", "code resend...........");
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(phone)
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(callbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void verifyCode(String otp){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(code, otp);
        signInWithCredential(credential);
    }
    private void signInWithCredential(PhoneAuthCredential credential)
    {
        auth.signInWithCredential(credential).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            tv_msg.setText("verified");


                            DatabaseReference dr2 = dr.child(phone);
                            dr2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    dr2.child("name").setValue(name);
//                                    dr2.child("Username").setValue(username);
                                    dr2.child("email").setValue(email);
                                    dr2.child("password").setValue(password);
                                    dr2.child("mobile").setValue(phone);

                                    tv_msg.setText("data saved");

                                    Toast.makeText(OTP.this, "saved....", Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                    Toast.makeText(OTP.this, "db error", Toast.LENGTH_SHORT).show();

                                    Log.e("db error", error.getMessage());
                                }
                            });

                        }
                        else
                        {
                            tv_msg.setText("incorrect OTP");
                        }
                    }
                }
        );
    }

    @Override
    public void onClick(View view)
    {
        if(view==verify)
        {
            otp=one.getText().toString();
            otp=otp+two.getText().toString();
            otp=otp+three.getText().toString();
            otp=otp+four.getText().toString();
            otp=otp+five.getText().toString();
            otp=otp+six.getText().toString();

            verifyCode(otp);
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (view == six){
            s = six.getText().toString().trim();
            if (keyEvent.getAction() == keyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_DEL){
                if (s.length() == 0){
                    five.setText("");
                    six.clearFocus();
                    five.requestFocus();
                    five.setCursorVisible(false);
                }
            }
        }
        else if (view == five){
            s = five.getText().toString().trim();
            if (keyEvent.getAction() == keyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_DEL){
                if (s.length() == 0){
                    four.setText("");
                    five.clearFocus();
                    four.requestFocus();
                    four.setCursorVisible(false);
                }
            }
        }
        else if (view == four){
            s = four.getText().toString().trim();
            if (keyEvent.getAction() == keyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_DEL){
                if (s.length() == 0){
                    three.setText("");
                    four.clearFocus();
                    three.requestFocus();
                    three.setCursorVisible(false);
                }
            }
        }
        else if (view == three){
            s = three.getText().toString().trim();
            if (keyEvent.getAction() == keyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_DEL){
                if (s.length() == 0){
                    two.setText("");
                    three.clearFocus();
                    two.requestFocus();
                    two.setCursorVisible(false);
                }
            }
        }
        else if (view == two){
            s = two.getText().toString().trim();
            if (keyEvent.getAction() == keyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_DEL){
                if (s.length() == 0){
                    one.setText("");
                    two.clearFocus();
                    one.requestFocus();
                    one.setCursorVisible(false);
                }
            }
        }

        return false;
    }
}
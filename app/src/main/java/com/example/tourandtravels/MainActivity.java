package com.example.tourandtravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView su;
    EditText phone;
    EditText password;
    DatabaseReference dr;
    FirebaseDatabase fd;
    Button login;

    String sphone, spassword;
    String spassword2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fd = FirebaseDatabase.getInstance();
        dr = fd.getReference("register");

        su = findViewById(R.id.textView6);
        phone = findViewById(R.id.editTextTextPersonName);
        password = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.button);
        su.setOnClickListener(this);
        login.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == su){
            startActivity(new Intent(MainActivity.this,registerActivity.class));
        }
        if (view == login){
            sphone=phone.getText().toString();
            spassword=password.getText().toString();

            DatabaseReference dr2 = dr.child(sphone);

            dr2.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if(snapshot.exists())
                    {
                        spassword2=snapshot.child("password").getValue(String.class);
                        
                        if(spassword.equals(spassword2))
                        {
                            startActivity(new Intent(MainActivity.this,Mainpage.class));
                        }
                        else
                        {
                            Toast.makeText(MainActivity.this, "incorrect pwd", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "phone not registered.. signup first", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Log.e("db error", error.getMessage());

                    Toast.makeText(MainActivity.this, "db error", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
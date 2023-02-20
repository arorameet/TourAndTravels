package com.example.tourandtravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registerActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name;
//    EditText username;
    EditText password;
    EditText phone;
    Button su;
    EditText email;
    EditText confirm;
    String a_name;
//    String b_username;
    String c_email;
    String d_password;
    String e_phone;
    String f_confirm;
    String MobilePattern = "[0-9]{10}";
    //String email1 = email.getText().toString().trim();
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    FirebaseDatabase fd;
    DatabaseReference dr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.editTextTextPersonName2);
//        username = findViewById(R.id.editTextTextPersonName4);
        password = findViewById(R.id.editTextTextPassword2);
        phone = findViewById(R.id.editTextNumber2);
        su = findViewById(R.id.button2);
        email = findViewById(R.id.editTextTextEmailAddress);
        confirm = findViewById(R.id.editTextTextPassword3);
        su.setOnClickListener(this);

        TextView log = findViewById(R.id.textView7);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(registerActivity.this,MainActivity.class));
            }
        });
    }


    public boolean validPassword(final String pwd){
        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.[0-9])(?=.[a-z])(?=.[A-Z])(?=.[@#$%^&+=])(?=\\S+$).{4,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(pwd);
        return matcher.matches();
    }

    @Override
    public void onClick(View view) {

        if (view == su){
            a_name = name.getText().toString().trim();
//            b_username = username.getText().toString();
            c_email = email.getText().toString().trim();
            d_password = password.getText().toString().trim();
            e_phone = phone.getText().toString().trim();
            f_confirm = confirm.getText().toString().trim();

            if (a_name.length() != 0) {
                if (c_email.matches(emailPattern)) {
                    if (e_phone.length() == 10) {
                        if (e_phone.matches(MobilePattern)) {
                            if (d_password.length() >= 8) {
                                if (validPassword(d_password)) {
                                    if (d_password.equals(f_confirm))
                                    {
                                        Intent intent = new Intent(this, OTP.class);
                                        intent.putExtra("name",a_name);
//            intent.putExtra("username",b_username);
                                        intent.putExtra("email",c_email);
                                        intent.putExtra("password",d_password);
                                        intent.putExtra("phone",e_phone);


                                        startActivity(intent);

                                    } else {
                                        confirm.setError("Password doesn't match");
                                    }
                                } else {
                                    password.setError("Enter a Strong Password");
                                }
                            } else {
                                password.setError("Password length should be more than 8");
                            }
                        } else {
                            phone.setError("Enter a valid 10 digit mobile number");

                        }
                    } else {
                        phone.setError("Enter a valid 10 digit mobile number");

                    }
                } else {
                    email.setError("Enter a valid email address");
                }
            } else {
                name.setError("Please enter your name");

            }
        }

//            if(email.isEmpty())
//            {
//                username.setError("Email is empty");
//                username.requestFocus();
//                return;
//            }
//            if(!Patterns.EMAIL_ADDRESS.matcher((CharSequence) email).matches())
//            {
//                username.setError("Enter the valid email address");
//                username.requestFocus();
//                return;
//            }
//            if(password.isEmpty())
//            {
//                password.setError("Enter the password");
//                password.requestFocus();
//                return;
//            }
//            if(password.length()<6)
//            {
//                password.setError("Length of the password should be more than 6");
//                password.requestFocus();
//                return;
//            }



/*            DatabaseReference dr2 = dr.child(a);
            dr2.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    dr2.child("name").setValue(a);
                    dr2.child("Username").setValue(b);
                    dr2.child("email id").setValue(c);
                    dr2.child("password").setValue(d);
                    dr2.child("mobile no.").setValue(e);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

*/
        }

    }

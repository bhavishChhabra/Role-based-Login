package com.example.rolebasedlogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button l;
    Switch active;
    EditText u, p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        active = findViewById(R.id.switch1);
        l = findViewById(R.id.login);
        u = findViewById(R.id.user);
        p = findViewById(R.id.pass);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("login");
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String s1 = u.getText().toString();
                        String s2 = p.getText().toString();
                        if(snapshot.child(s1).exists()){
                            if (snapshot.child(s1).child("password").getValue(String.class).equals(s2)){
                                if(active.isChecked()){
                                    if(snapshot.child("as").getValue(String.class).equals("admin")){
                                        prefrences.setDataAs(MainActivity.this,"admin");
                                        prefrences.setDataLogin(MainActivity.this,true);
                                        startActivity(new Intent(MainActivity.this,admin.class));

                                    }else if(snapshot.child("as").getValue(String.class).equals("user")){
                                        prefrences.setDataAs(MainActivity.this,"user");
                                        prefrences.setDataLogin(MainActivity.this,true);
                                        startActivity(new Intent(MainActivity.this,user.class));

                                    }
                                }
                            }else{
                                if(snapshot.child("as").getValue(String.class).equals("admin")){
                                    prefrences.setDataLogin(MainActivity.this,false);
                                }else if(snapshot.child("as").getValue(String.class).equals("user")){
                                    prefrences.setDataLogin(MainActivity.this,false);
                                }
                                Toast.makeText(MainActivity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Incorrect Username", Toast.LENGTH_SHORT).show();
                        }

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }


        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(prefrences.setDataLogin(MainActivity.this)){
            if(prefrences.setDataAs(this).equals("admin")){
                startActivity(new Intent(MainActivity.this,admin.class));
            }
            else{

                startActivity(new Intent(MainActivity.this,user.class));
                finish();
            }
        }
    }
}

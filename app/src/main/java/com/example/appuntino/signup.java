package com.example.appuntino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity {
    private EditText signupemail;
    private EditText signuppass;
    private Button signup;
    private TextView gologin;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);
        getSupportActionBar().hide();
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        signupemail=findViewById(R.id.signupemail);
        signuppass=findViewById(R.id.signuppassword);
        signup=findViewById(R.id.Signupbutton);
        gologin=findViewById(R.id.gotologin);
        firebaseAuth = FirebaseAuth.getInstance();

        gologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signup.this,MainActivity.class);
                startActivity(intent);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=signupemail.getText().toString().trim();
                String pass=signuppass.getText().toString().trim();
                if(mail.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Inserire i dati per continuare",Toast.LENGTH_SHORT).show();
                }
                else if(pass.length()<7){
                    Toast.makeText(getApplicationContext(),"La password deve essere superiore a 7 caratteri",Toast.LENGTH_SHORT).show();
                }
                else  {
                    firebaseAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Registrazione effettuata", Toast.LENGTH_SHORT).show();
                                sendEmailVerification();
                            }else{
                                    Toast.makeText(getApplicationContext(),"Registrazione fallita", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


    }
    private void sendEmailVerification(){
        FirebaseUser firebaseUser =firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getApplicationContext(),"Verifica effettuata, procedere con il login", Toast.LENGTH_SHORT).show();
                    firebaseAuth.signOut();
                    finish();
                    startActivity(new Intent(signup.this,MainActivity.class));
                }
            });

        }else{
            Toast.makeText(getApplicationContext(),"Invio della verifica fallito", Toast.LENGTH_SHORT).show();
        }
    }
}
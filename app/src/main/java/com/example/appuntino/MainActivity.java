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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private EditText loginemail;
    private EditText loginpass;
    private Button login;
    private Button signup;
    private TextView nonsignup;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        loginemail=findViewById(R.id.loginemail);
        loginpass=findViewById(R.id.loginpassword);
        login=findViewById(R.id.Loginbutton);
        signup=findViewById(R.id.Registerbutton);
        nonsignup=findViewById(R.id.nonseiid);
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser firebaseUser= firebaseAuth.getCurrentUser();
        if(firebaseUser!=null){
            finish();
            startActivity(new Intent(MainActivity.this,notesactivity.class));
        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,signup.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail=loginemail.getText().toString().trim();
                String pass=loginpass.getText().toString().trim();
                if(mail.isEmpty() || pass.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Inserire i dati per continuare",Toast.LENGTH_SHORT).show();
                }
                else if(pass.length()<7){
                    Toast.makeText(getApplicationContext(),"La password deve essere superiore a 7 caratteri",Toast.LENGTH_SHORT).show();
                }
                else  {
                    //login the user
                    firebaseAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                CheckMailVerification();
                            }else{
                                Toast.makeText(getApplicationContext(),"Account non esistente",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
    private void CheckMailVerification(){
        FirebaseUser firebaseUser=firebaseAuth.getCurrentUser();
        if(firebaseUser.isEmailVerified()==true){
            Toast.makeText(getApplicationContext(),"Accesso effettuato",Toast.LENGTH_SHORT).show();
            finish();
            startActivity(new Intent(MainActivity.this,notesactivity.class));
        }else {
            Toast.makeText(getApplicationContext(),"Verifica la tua email per continuare",Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
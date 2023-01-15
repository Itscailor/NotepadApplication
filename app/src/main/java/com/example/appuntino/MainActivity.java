package com.example.appuntino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText loginemail;
    private EditText loginpass;
    private Button login;
    private Button signup;
    private TextView nonsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginemail=findViewById(R.id.loginemail);
        loginpass=findViewById(R.id.loginpassword);
        login=findViewById(R.id.Loginbutton);
        signup=findViewById(R.id.Registerbutton);
        nonsignup=findViewById(R.id.nonseiid);

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
                }
            }
        });
    }
}
package com.example.appuntino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class createnote extends AppCompatActivity {
EditText createtitleofnotebutt;
EditText createcontentofnotebutt;
FloatingActionButton savenotebutt;
FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createnote);

        savenotebutt=findViewById(R.id.savenotefab);
       createtitleofnotebutt=findViewById(R.id.createtitlenote);
        createcontentofnotebutt=findViewById(R.id.createcontentnote);
        Toolbar toolbar = findViewById(R.id.toolbarcreatenote);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        firebaseAuth = firebaseAuth.getInstance();
        firebaseFirestore=firebaseFirestore.getInstance();
        firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        savenotebutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=createtitleofnotebutt.getText().toString();
                String content=createcontentofnotebutt.getText().toString();
                if(title.isEmpty() || content.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Inserire titolo e contenuto", Toast.LENGTH_SHORT).show();
                }else{
                    DocumentReference documentReference = firebaseFirestore.collection("notes").document(firebaseUser.getUid()).collection("myNotes").document();
                    Map<String, Object> note = new HashMap<>();
                    note.put("title",title);
                    note.put("content",content);
                    documentReference.set(note).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(getApplicationContext(), "Nota creata", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(createnote.this,notesactivity.class));
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), "Creazione della nota fallito", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
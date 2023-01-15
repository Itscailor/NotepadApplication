package com.example.appuntino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class notesactivity extends AppCompatActivity {
    FloatingActionButton createnotebutt;
    private FirebaseAuth firebaseAuth;

    RecyclerView mrecyclerView;
    StaggeredGridLayoutManager staggeredGridLayoutManager;
    FirebaseUser firebaseUser;
    FirebaseFirestore firebaseFirestore;
    FirestoreRecyclerAdapter<firebasemodel,NoteViewHolder> noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notesactivity);
        createnotebutt=findViewById(R.id.createnotefab);
        firebaseAuth = firebaseAuth.getInstance();

        firebaseUser=firebaseAuth.getInstance().getCurrentUser();
        firebaseFirestore=firebaseFirestore.getInstance();

        getSupportActionBar().setTitle("Note");

        createnotebutt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(notesactivity.this,createnote.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.logout:
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(notesactivity.this,MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
package com.example.appuntino;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class notedetails extends AppCompatActivity {
    private TextView contentnoteedit;
    private TextView titlenoteedit;
    FloatingActionButton gotoeditnote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notedetails);
        Toolbar toolbar = findViewById(R.id.toolbarnotedetail);
        setSupportActionBar(toolbar);
        contentnoteedit=findViewById(R.id.contentnotedetail);
        titlenoteedit=findViewById(R.id.titlenotedetail);
        gotoeditnote=findViewById(R.id.gotoedit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);
        Intent data=getIntent();
        gotoeditnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),editnoteactivity.class);
                intent.putExtra("title",data.getStringExtra("title"));
                intent.putExtra("content",data.getStringExtra("content"));
                intent.putExtra("noteID",data.getStringExtra("noteID"));

            }
        });

        contentnoteedit.setText(data.getStringExtra("content"));
        titlenoteedit.setText(data.getStringExtra("title"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
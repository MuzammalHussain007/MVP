package com.be.notesappmvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NotePresenter.NotePresentation {
    private NotePresenter notePresenter ;
    private DatabaseReference reference ;
    private EditText editText ;
    private Button insertRecord ;
    private RecyclerView recycler;
    private List<NoteModal> noteModals ;
    private TextView textView ;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu_item,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.full_detail:
            {

                break;
            }
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniit();
        insertRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notePresenter.setinsertNoteData(editText.getText().toString());
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DetailActivity.class));
            }
        });
    }

    private void iniit() {
        recycler = findViewById(R.id.recyclarView);
        insertRecord = findViewById(R.id.nore_insert);
        editText = findViewById(R.id.note_detail);
        reference = FirebaseDatabase.getInstance().getReference("User Notes");
        notePresenter = new NotePresenter(this,getApplicationContext());
        noteModals = new ArrayList<>();
        textView = findViewById(R.id.detail);

    }

    @Override
    public void showRecord(String record, String time) {
        NoteModal noteModal = new NoteModal(record,time);
        noteModals.add(noteModal);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(MainActivity.this);
        recycler.setLayoutManager(linearLayoutManager);
        linearLayoutManager.scrollToPositionWithOffset(noteModals.size()-1,noteModals.size()-2);


        recycler.setAdapter(new NoteAdapter(this,noteModals));



    }

    @Override
    public void showAllrecord(List<NoteModal> list) {

    }


}
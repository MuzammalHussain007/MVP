package com.be.notesappmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class DetailActivity extends AppCompatActivity  implements NotePresenter.NotePresentation {
    private RecyclerView recyclerView ;
    private NotePresenter notePresenter ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initvariable();
        notePresenter.setShowData();
     }

    public void initvariable()
    {
           recyclerView = findViewById(R.id.detail_recyclar_view);
           notePresenter = new NotePresenter(this,this);
    }

    @Override
    public void showRecord(String record, String time) {

    }

    @Override
    public void showAllrecord(List<NoteModal> list) {
          recyclerView.setLayoutManager(new LinearLayoutManager(DetailActivity.this));
          if (list.size()!=0)
          {
              recyclerView.setAdapter(new NoteAdapter(this,list));
          }
    }


}
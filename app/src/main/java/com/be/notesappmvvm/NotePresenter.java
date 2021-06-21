package com.be.notesappmvvm;

import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotePresenter {
    private NoteModal noteModal ;
    private NotePresentation notePresentation ;
    private DatabaseReference reference ;
    private Context context ;
    private List<NoteModal> noteModalList ;

    public NotePresenter(NotePresentation notePresentation ,Context context ) {
        this.noteModal = new NoteModal();
        this.notePresentation = notePresentation;
        reference = FirebaseDatabase.getInstance().getReference("User Notes");
        this.context = context;
        noteModalList = new ArrayList<>();

    }

    public void setinsertNoteData(String nore)
    {
        noteModal.setNoteDetail(nore);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            noteModal.setDate(String.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime())));
        }
        reference.child(reference.push().getKey()).setValue(noteModal).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull  Task<Void> task) {
                if (task.isSuccessful())
                {
                    Toast.makeText(context,"Records Inserted...",Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull  Exception e) {
                Toast.makeText(context,""+e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        notePresentation.showRecord(noteModal.getNoteDetail(),noteModal.getDate());
    }

    public void setShowData() {
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded( DataSnapshot snapshot, @Nullable String previousChildName) {
                  NoteModal noteModal = snapshot.getValue(NoteModal.class);
                  noteModalList.add(noteModal);
                  notePresentation.showAllrecord(noteModalList);

            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved( DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved( DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }

        });
        notePresentation.showAllrecord(noteModalList);
    }


    interface NotePresentation
    {
        void showRecord(String record , String time);

        void showAllrecord(List<NoteModal> list);
    }
}

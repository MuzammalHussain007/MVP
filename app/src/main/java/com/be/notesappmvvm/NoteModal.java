package com.be.notesappmvvm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NoteModal {
    private String noteDetail,date;
    private DatabaseReference databaseReference ;
    private List<NoteModal> noteModals ;
    private NoteModal noteModal;

    public String getNoteDetail() {
        return noteDetail;
    }

    public void setNoteDetail(String noteDetail) {
        this.noteDetail = noteDetail;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public NoteModal(String noteDetail, String date) {
        this.noteDetail = noteDetail;
        this.date = date;
        databaseReference = FirebaseDatabase.getInstance().getReference("User Notes");
        noteModals = new ArrayList<>();
    }
    public NoteModal()
    {

    }


}

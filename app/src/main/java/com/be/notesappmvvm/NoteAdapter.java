package com.be.notesappmvvm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private Context context ;
    private List<NoteModal> noteModals ;

    public NoteAdapter(Context context, List<NoteModal> noteModals) {
        this.context = context;
        this.noteModals = noteModals;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.custom_nore_layout,parent,false);
        return  new NoteHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull   NoteAdapter.NoteHolder holder, int position) {
        holder.note_detail.setText(noteModals.get(position).getNoteDetail());
        holder.note_date.setText(noteModals.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return noteModals.size();
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        private TextView note_detail , note_date ;
        private ImageView imageView ;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            note_detail = itemView.findViewById(R.id.note_text);
            note_date = itemView.findViewById(R.id.note_date);
            imageView = itemView.findViewById(R.id.menu_id);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  PopupMenu popupMenu = new PopupMenu(v.getContext(),v);
                  popupMenu.inflate(R.menu.menue);
                  popupMenu.show();
                  popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                      @Override
                      public boolean onMenuItemClick(MenuItem item) {
                          switch (item.getItemId())
                          {
                              case R.id.delete:
                              {

                                  break;
                              }
                              case R.id.update:
                              {

                                  break;
                              }
                          }
                          return true;
                      }
                  });
                }
            });
        }
    }
}

package com.dhana.fisherman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class NoteAdapter extends FirestoreRecyclerAdapter<Savework,NoteAdapter.NoteViewlder> {
    Context context;


    public NoteAdapter(@NonNull FirestoreRecyclerOptions<Savework> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewlder holder, int position, @NonNull Savework savework) {
        holder.titleTextView.setText(savework.title);
        holder.contentTextView.setText(savework.content);
        holder.timestampTextView.setText(Utility.timestampToString(savework.timestamp));
    }

    @NonNull
    @Override
    public NoteViewlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclernoteitem,parent,false);
        return new NoteViewlder(view);
    }

    class NoteViewlder extends RecyclerView.ViewHolder {

        TextView titleTextView,contentTextView,timestampTextView;

        public NoteViewlder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.txtTitle);
            contentTextView = itemView.findViewById(R.id.txtContent);
            timestampTextView = itemView.findViewById(R.id.txtTimestamp);

        }
    }
}

package com.dhana.fisherman;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.Query;

public class Work extends Fragment {
    FloatingActionButton workAdd;
    RecyclerView recyclerView;
    NoteAdapter noteAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.work_fragment,container,false);

        workAdd = view.findViewById(R.id.btnWorkAdd);
        recyclerView = view.findViewById(R.id.recylerView);
        workAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Add_work.class);
                startActivity(intent);
            }
        });
        setupRecyclerView();
        return view;
    }

    void setupRecyclerView(){
        Query query = Utility.getCollectionReferenceForWork().orderBy("timestamp", Query.Direction.DESCENDING);
        FirestoreRecyclerOptions<Savework> options = new FirestoreRecyclerOptions.Builder<Savework>().setQuery(query,Savework.class).build();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        noteAdapter = new NoteAdapter(options,getActivity());
        recyclerView.setAdapter(noteAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        noteAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        noteAdapter.stopListening();
    }

    @Override
    public void onResume() {
        super.onResume();
        noteAdapter.notifyDataSetChanged();
    }

    //    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//
//        workAdd.setOnClickListener((v) -> startActivity(new Intent(Work.this,Add_work.class)));
//    }
}

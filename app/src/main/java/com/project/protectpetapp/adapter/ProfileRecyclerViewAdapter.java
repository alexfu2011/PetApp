package com.project.protectpetapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.protectpetapp.R;
import com.project.protectpetapp.model.Pet;

import java.util.ArrayList;

public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Pet> petArrayList = new ArrayList<>();
    private Context context;

    public ProfileRecyclerViewAdapter(ArrayList<Pet> pets, Context context) {
        this.context = context;
        this.petArrayList = pets;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_pet_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return petArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView pid, name, birth, breeds;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pid = itemView.findViewById(R.id.item_profile_tv_pet_id);
            name = itemView.findViewById(R.id.item_profile_tv_pet_name);
            birth = itemView.findViewById(R.id.item_profile_tv_pet_birth);
            breeds = itemView.findViewById(R.id.item_profile_tv_pet_breeds);
        }
    }
}

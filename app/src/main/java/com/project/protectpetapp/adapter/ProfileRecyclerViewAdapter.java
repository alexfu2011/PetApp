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

public class ProfileRecyclerViewAdapter extends RecyclerView.Adapter<ProfileRecyclerViewAdapter.ViewHolder> {

    ArrayList<Pet> petArrayList = null;
    private Context context;

    public ProfileRecyclerViewAdapter(ArrayList<Pet> pets, Context context) {
        this.context = context;
        this.petArrayList = pets;
    }

    @NonNull
    @Override
    public ProfileRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_pet_list, parent, false);
        ProfileRecyclerViewAdapter.ViewHolder viewHolder = new ProfileRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileRecyclerViewAdapter.ViewHolder holder, int position) {
        Pet pet = this.petArrayList.get(position);

        holder.pid.setText(pet.petId);
        holder.name.setText(pet.name);
        holder.birth.setText(pet.birth);
        holder.breeds.setText(pet.breeds);
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

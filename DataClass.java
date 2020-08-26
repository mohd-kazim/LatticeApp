package com.example.latticeapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class DataClass extends RecyclerView.Adapter<DataClass.DcViewHolder> {
    private final Context context;
    ArrayList<PersonData> mData;
    public DataClass(Context context,ArrayList<PersonData> mData) {
        this.context = context;
        this.mData= mData;
    }
    public class DcViewHolder extends RecyclerView.ViewHolder {
        ImageView ibLocation;
        TextView tvName1, tvPhone1, tvEmail1,tvAddress1,tvPassword1,tvLocation1;
        public DcViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName1 = itemView.findViewById(R.id.tvName1);
            tvPhone1= itemView.findViewById(R.id.tvPhone1);
            tvEmail1 = itemView.findViewById(R.id.tvEmail1);
            tvAddress1= itemView.findViewById(R.id.tvAddress1);
            tvPassword1 = itemView.findViewById(R.id.tvPassword1);
            tvLocation1= itemView.findViewById(R.id.tvLocation1);
            ibLocation = itemView.findViewById(R.id.ibLocation);
            ibLocation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+tvLocation1.getText().toString()));
                    context.startActivity( intent);
                }
            });

        }
    }

    @NonNull
    @Override
    public DataClass.DcViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_layout, parent, false);
        return new DcViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DcViewHolder holder, int position) {
        final PersonData personData = mData.get(position);
        holder.tvName1.setText(personData.getName());
        holder.tvPhone1.setText(personData.getPhone());
        holder.tvEmail1.setText(personData.getEmail());
        holder.tvAddress1.setText(personData.getAddress());
        holder.tvPassword1.setText(personData.getPassword());
        holder.tvLocation1.setText(personData.getLocation());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}

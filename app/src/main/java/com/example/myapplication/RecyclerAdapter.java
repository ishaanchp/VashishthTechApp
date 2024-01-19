package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<carModel> cars_array;
    private OnItemClickListener itemClickListener;


    public interface OnItemClickListener{
        void onItemClick(carModel position);


    }

    public void addCar(carModel newCar) {
        cars_array.add(newCar);
        notifyDataSetChanged(); // Notify the adapter that the dataset has changed
    }
    RecyclerAdapter (Context context, ArrayList<carModel> cars_array){
        this.context = context;
        this.cars_array = cars_array;
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.itemClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_row,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


            carModel current_position = cars_array.get(position);
            holder.txtCarname.setText(current_position.car_name);
            holder.txtCarname.setText(current_position.car_name);
            holder.txtnumber.setText(current_position.rc_number);
            holder.txtmodel.setText(current_position.model);
            holder.txtowner.setText(current_position.owner);
            holder.txtchassis.setText(current_position.chassis);



        holder.l1row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int current_position = holder.getAdapterPosition();
                Dialog dialog  = new Dialog(context);
                dialog.setContentView(R.layout.app_update);
                carModel clickedCar = cars_array.get(current_position);

                }
        });


        holder.l1row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int current_position = holder.getAdapterPosition();

                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("Delete Car")
                            .setMessage("Are you sure you want to delete?")
                            .setIcon(R.drawable.baseline_delete_24)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    cars_array.remove(current_position);
                                    notifyItemRemoved(current_position);
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                    builder.show();


                return true ;
            }
        });

    }

    @Override
    public int getItemCount() {
        if(cars_array != null) {

            return cars_array.size();
        }else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtCarname, txtnumber, txtmodel,txtowner, txtchassis,Expiry, Color, Fuel_type;
        LinearLayout l1row;
        public ViewHolder(View itemView){
            super(itemView);
            txtCarname = itemView.findViewById(R.id.txtCarname);
            txtnumber  = itemView.findViewById(R.id.txtnumber);
            txtmodel = itemView.findViewById(R.id.txtmodel);
            l1row = itemView.findViewById(R.id.l1row);
            txtowner = itemView.findViewById(R.id.txtowner);
            txtchassis = itemView.findViewById(R.id.txtchassis);
            Expiry = itemView.findViewById(R.id.txtExpiry);
            Color = itemView.findViewById(R.id.txtColor);
            Fuel_type = itemView.findViewById(R.id.txtFuel);

        }
    }
}

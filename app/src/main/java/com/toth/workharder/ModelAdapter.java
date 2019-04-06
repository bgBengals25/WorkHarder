package com.toth.workharder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;



public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ViewHolder> {


    public ArrayList<Model> exercise_list;


    public ModelAdapter(ArrayList<Model> arrayList) {

        exercise_list = arrayList;
    }

    @Override
    public ModelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item, null);

        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ModelAdapter.ViewHolder holder, int position) {

        final int pos = position;

        holder.item_name.setText(exercise_list.get(position).getName());
        holder.item_time.setText(""+exercise_list.get(position).getSeconds()+" seconds");

        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                deleteItemFromList(v, pos);

            }
        });

    }

    @Override
    public int getItemCount() {
        return exercise_list.size();
    }


    // confirmation dialog box to delete an unit
    private void deleteItemFromList(View v, final int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        //builder.setTitle("Dlete ");
        builder.setMessage("Delete Item ?")
                .setCancelable(false)
                .setPositiveButton("CONFIRM",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                exercise_list.remove(position);
                                notifyDataSetChanged();

                            }
                        })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {


                    }
                });

        builder.show();

    }

    public void addItemToList(Model m){
        exercise_list.add(m);
        notifyDataSetChanged();
    }

    public Model[] getItems() {

        Model[] models = new Model[exercise_list.size()];

        for(int i = 0; i < exercise_list.size(); i++){
            models[i] = exercise_list.get(i);
        }

        return models;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView item_name;
        public ImageButton btn_delete;
        public TextView item_time;


        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);

            item_name = (TextView) itemLayoutView.findViewById(R.id.txt_name);
            btn_delete = (ImageButton) itemLayoutView.findViewById(R.id.btn_delete_unit);
            item_time = (TextView) itemLayoutView.findViewById(R.id.txt_time);

        }
    }
}
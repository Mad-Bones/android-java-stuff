package com.bns.herramientacovid19.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bns.herramientacovid19.R;
import com.bns.herramientacovid19.database.Conteo;
import com.bns.herramientacovid19.database.Objeto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ObjetosAdapter extends RecyclerView.Adapter<ObjetosAdapter.ObjectViewHolder> implements Filterable {

    private Context context;

    private ArrayList<Conteo> mDataset;
    private ArrayList<Conteo> mDatasetFilter;

    public ObjetosAdapter(ArrayList<Conteo> mDataset) {
        this.mDataset = mDataset;
        this.mDatasetFilter = mDataset;

    }

    @NonNull
    @Override
    public ObjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_lista, parent, false);
        ObjectViewHolder vh = new ObjectViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ObjectViewHolder holder, final int position) {
        final Conteo object = mDatasetFilter.get(position);
        holder.setObject(mDatasetFilter.get(position));

    }


    @Override
    public int getItemCount() {
        return mDatasetFilter.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    class ObjectViewHolder extends RecyclerView.ViewHolder {
        TextView provincia, total;

        ObjectViewHolder(@NonNull View itemView) {
            super(itemView);
            provincia = itemView.findViewById(R.id.provincia);
            total = itemView.findViewById(R.id.total);

        }
        @SuppressLint("SetTextI18n")
        void setObject(Conteo object) {


            total.setText(Integer.toString(object.getCasos()));
            provincia.setText(object.getNombre());
        }
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mDatasetFilter = mDataset;
                } else {
                    ArrayList<Conteo> filteredList = new ArrayList<>();
                    for (Conteo row : mDataset) {
                        if (row.getNombre().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    mDatasetFilter = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mDatasetFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDatasetFilter = (ArrayList<Conteo>) filterResults.values;

                notifyDataSetChanged();
            }
        };

    }

}

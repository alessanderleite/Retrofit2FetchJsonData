package br.com.alessanderleite.retrofit2fetchjsondata.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.alessanderleite.retrofit2fetchjsondata.model.Item;
import br.com.alessanderleite.retrofit2fetchjsondata.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    ArrayList<Item> itemArrayList;

    public ItemAdapter(ArrayList<Item> itemArrayList) {
        this.itemArrayList = itemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemArrayList.get(position);

        holder.text_name.setText(item.getName());
        holder.text_email.setText(item.getEmail());
    }

    @Override
    public int getItemCount() {
        return itemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView text_name;
        private TextView text_email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_name = (TextView) itemView.findViewById(R.id.txt_name);
            text_email = (TextView) itemView.findViewById(R.id.txt_email);
        }
    }
}

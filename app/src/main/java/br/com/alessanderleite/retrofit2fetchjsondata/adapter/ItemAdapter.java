package br.com.alessanderleite.retrofit2fetchjsondata.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.alessanderleite.retrofit2fetchjsondata.R;
import br.com.alessanderleite.retrofit2fetchjsondata.controller.DetailActivity;
import br.com.alessanderleite.retrofit2fetchjsondata.model.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> implements Filterable {

    private List<Item> itemList;
    private List<Item> itemListFull;
    private Context context;

    public ItemAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.itemListFull = new ArrayList<>(itemList);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = this.itemList.get(position);

        holder.txtLogin.setText(item.getLogin());
        holder.txtHtmlUrl.setText(item.getHtmlUrl());

        Picasso.get()
                .load(item.getAvatarUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Item> fiItems = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                fiItems.addAll(itemListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Item item : itemListFull) {
                    if (item.getLogin().toLowerCase().contains(filterPattern)) {
                        fiItems.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = fiItems;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            itemList.clear();
            itemList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtLogin;
        private TextView txtHtmlUrl;
        private ImageView imageView;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            txtLogin = (TextView) itemView.findViewById(R.id.txt_login);
            txtHtmlUrl = (TextView) itemView.findViewById(R.id.txt_html_url);
            imageView = (ImageView) itemView.findViewById(R.id.img_avatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Item clickedItem = itemList.get(position);
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("login", clickedItem.getLogin());
                        intent.putExtra("html_url", clickedItem.getHtmlUrl());
                        intent.putExtra("avatar_url", clickedItem.getAvatarUrl());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedItem.getLogin(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

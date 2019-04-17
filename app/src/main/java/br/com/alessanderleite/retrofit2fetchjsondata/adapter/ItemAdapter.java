package br.com.alessanderleite.retrofit2fetchjsondata.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import br.com.alessanderleite.retrofit2fetchjsondata.R;
import br.com.alessanderleite.retrofit2fetchjsondata.activity.DetailActivity;
import br.com.alessanderleite.retrofit2fetchjsondata.model.Item;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<Item> itemArrayList;
    private Context context;

    public ItemAdapter(ArrayList<Item> itemArrayList, Context context) {
        this.itemArrayList = itemArrayList;
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
        Item item = this.itemArrayList.get(position);

        holder.txtLogin.setText(item.getLogin());
        holder.txtHtmlUrl.setText(item.getHtmlUrl());

        Glide.with(context)
                .load(item.getAvatarUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return this.itemArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtLogin;
        private TextView txtHtmlUrl;
        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtLogin = (TextView) itemView.findViewById(R.id.txt_login);
            txtHtmlUrl = (TextView) itemView.findViewById(R.id.txt_html_url);
            imageView = (ImageView) itemView.findViewById(R.id.img_avatar);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Item clickedDataItem = itemArrayList.get(position);
                        Intent intent = new Intent(context, DetailActivity.class);
                        context.startActivity(intent);
                        Toast.makeText(v.getContext(), "You clicked " + clickedDataItem.getLogin(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

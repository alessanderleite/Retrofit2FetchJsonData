package br.com.alessanderleite.retrofit2fetchjsondata.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.alessanderleite.retrofit2fetchjsondata.adapter.ItemAdapter;
import br.com.alessanderleite.retrofit2fetchjsondata.api.Service;
import br.com.alessanderleite.retrofit2fetchjsondata.model.Item;
import br.com.alessanderleite.retrofit2fetchjsondata.R;
import br.com.alessanderleite.retrofit2fetchjsondata.api.Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getItemsApi();
    }

    private void getItemsApi() {
        try {
            Service api = Client.getRetrofitInstance().create(Service.class);

            Call<List<Item>> call = api.getItems();
            call.enqueue(new Callback<List<Item>>() {
                @Override
                public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                    itemList = response.body();
                    String[] items = new String[itemList.size()];
                    for (int i = 0; i < itemList.size(); i++) {
                        items[i] = itemList.get(i).getName();
                    }

                    generateItemList();
                }

                @Override
                public void onFailure(Call<List<Item>> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void generateItemList(ArrayList<Item> items) {
        if (items != null) {
            mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(MainActivity.this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new ItemAdapter(items);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}

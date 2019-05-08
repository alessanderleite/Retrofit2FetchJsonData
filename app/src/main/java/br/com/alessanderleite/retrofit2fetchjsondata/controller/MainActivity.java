package br.com.alessanderleite.retrofit2fetchjsondata.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import java.util.List;

import br.com.alessanderleite.retrofit2fetchjsondata.adapter.ItemAdapter;
import br.com.alessanderleite.retrofit2fetchjsondata.api.Service;
import br.com.alessanderleite.retrofit2fetchjsondata.model.Item;
import br.com.alessanderleite.retrofit2fetchjsondata.R;
import br.com.alessanderleite.retrofit2fetchjsondata.api.Client;
import br.com.alessanderleite.retrofit2fetchjsondata.model.ItemResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getItemsApi();
    }

    private void getItemsApi() {
        try {
            Service service = Client.getRetrofitInstance().create(Service.class);
            Call<ItemResponse> call = service.getItems();
            call.enqueue(new Callback<ItemResponse>() {
                @Override
                public void onResponse(Call<ItemResponse> call, Response<ItemResponse> response) {
                    itemList = response.body().getItemArrayList();
                    getRecyclerViewInstance();
                }

                @Override
                public void onFailure(Call<ItemResponse> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Something went wrong... Please try later", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void getRecyclerViewInstance() {

            mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
            mRecyclerView.setHasFixedSize(true);
            mLayoutManager = new LinearLayoutManager(MainActivity.this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mAdapter = new ItemAdapter(itemList,this);
            mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_section);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}

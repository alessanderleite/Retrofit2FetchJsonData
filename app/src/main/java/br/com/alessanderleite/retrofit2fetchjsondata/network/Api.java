package br.com.alessanderleite.retrofit2fetchjsondata.network;

import java.util.List;

import br.com.alessanderleite.retrofit2fetchjsondata.model.Item;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("posts/1/comments")
    Call<List<Item>> getItems();
}
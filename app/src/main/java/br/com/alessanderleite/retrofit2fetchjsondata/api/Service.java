package br.com.alessanderleite.retrofit2fetchjsondata.api;

import br.com.alessanderleite.retrofit2fetchjsondata.model.ItemResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("/search/users?q=language:java+location:brazil")
    Call<ItemResponse> getItems();
}

package com.example.alena_adm.cafesapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Alena_Adm on 26.05.2018.
 */

public interface CafeService {

    @GET("/show")
    Call<List<Cafe>> listRepos();
}

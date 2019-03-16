package com.example.myapplication;


import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Body;


interface GitHubService {
    @GET("tracks")
    Call<ArrayList<track>> tracks();

    /*@GET("tracks/{owner}/{repo}/contributors")
    Call<ArrayList<Contributor>> repoContributors(
            @Path("owner") String owner,
            @Path("repo") String repo);
*/
    public static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://147.83.7.203:8080/dsaApp/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    @GET("tracks/{id}")
    Call<track> Cancion( @Path("id") String id);

    /**@POST("tracks")
    Call<tracks> createTrack(@Body tracks tracks);

     */

    /**
     @POST("tracks")
     Call<tracks> createTrack(@Field("singer") String singer, @Field("title") String title);
     */
    /**
     @POST("traks")
     Call<tracks> createTrack(@Path("title") String title, @Path("single") String single);
     */

    @POST("tracks")
    Call<track> createTrack(@Body track tracks);

    @DELETE("tracks/{id}")
    Call<track> borrarTrack(@Path("id") String id);

}




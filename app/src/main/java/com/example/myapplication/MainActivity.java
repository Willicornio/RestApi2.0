package com.example.myapplication;


import android.content.Intent;
import android.media.audiofx.LoudnessEnhancer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.log4j.Logger;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final static Logger log = Logger.getLogger("Logger de Ejemplo");


    private Adapter mAdapter;
    Button button3;
    Button button4;
    Button button2;
    private track tracks;
    private GitHubService GitHubService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button button = (Button) findViewById(R.id.button);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);

        final Adapter mAdapter = new Adapter();
        mAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(MainActivity.this,SegundoActivity.class);

                startActivity(miIntent);
            }
        });
        mRecyclerView.setAdapter(mAdapter);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
                Call<ArrayList<track>> call = gitHubService.tracks();

                call.enqueue(new Callback<ArrayList<track>>() {
                    @Override
                    public void onResponse(Call<ArrayList<track>> call, Response<ArrayList<track>> response) {

                        ArrayList<track> canciones = response.body();
                        mAdapter.setDataSet(canciones);

                    }
                    @Override
                    public void onFailure(Call<ArrayList<track>> call, Throwable t) {

                        Toast.makeText(getApplicationContext(),"Fallo con la petición de información",Toast.LENGTH_SHORT);
                    }
                });
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
                Call<track> call =  gitHubService.Cancion( "cbobewu2847birw2");

                call.enqueue(new Callback<track>() {
                    @Override
                    public void onResponse(Call<track> call, Response<track> response) {
                        /**track cancion = response.body();

                        mAdapter.setDataSet(cancion);
                         */
                    }
                    @Override
                    public void onFailure(Call<track> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Fallo",Toast.LENGTH_SHORT);
                    }
                });
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
                track cancion = new track("Gato", "Gatos...");
                String title = "lololo";
                String singer = "laalalla";


                Call<track> call = gitHubService.createTrack(cancion);
                call.enqueue(new Callback<track>() {
                    @Override
                    public void onResponse(Call<track> call, Response<track> response) {
                        Toast.makeText(getApplicationContext(),"Añadido correctamente",Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onFailure(Call<track> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Fallo con la petición de información",Toast.LENGTH_SHORT);
                    }
                });
            }
        });


        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);

                Call<track> call = gitHubService.borrarTrack("rge59M581085643762957");
                call.enqueue(new Callback<track>() {
                    @Override
                    public void onResponse(Call<track> call, Response<track> response) {
                        Toast.makeText(getApplicationContext(),"Borrado very good",Toast.LENGTH_SHORT);

                    }

                    @Override
                    public void onFailure(Call<track> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"No se hizo",Toast.LENGTH_SHORT);
                }
                });
            }
        });



    }
}

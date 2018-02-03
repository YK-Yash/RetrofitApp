package com.example.dell.retrofitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<RecyclerModel> list = new ArrayList<>();
    RecyclerModel r;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recyy);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        Api api = retrofit.create(Api.class);

        Call<List<Hero>> call = api.getHeroes();

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                List<Hero> h = response.body();
                Toast.makeText(getBaseContext(),h.get(0).getName()+h.size(),Toast.LENGTH_LONG).show();
                String herosList[] = new String[h.size()];


                for(int i=0;i<h.size();i++){

                    r = new RecyclerModel();
                    r.setHeroName(h.get(i).getName());
                    list.add(r);
                }

                RecyclerAdapter adapter = new RecyclerAdapter(list,MainActivity.this);
                RecyclerView.LayoutManager manager = new GridLayoutManager(MainActivity.this,1);
                recyclerView.setLayoutManager(manager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {

                Toast.makeText(getBaseContext(),"API call failed", Toast.LENGTH_LONG).show();

            }
        });

    }
}

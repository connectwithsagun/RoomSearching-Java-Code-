package com.example.room.Remote;

import com.example.room.Adapter.AllPropertyAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

        private static String baseUrl="http://room.oxfordcollege.edu.np/api/";
        private  static Retrofit retrofit;
        private static Gson gson;

        public static Retrofit getRetrofitInstance(){
            if(retrofit==null){
                gson=new GsonBuilder()
                        .setLenient()
                        .create();
                retrofit=new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

            }
            return retrofit;
    }
}

package com.example.room.Remote;

import static android.content.Context.MODE_PRIVATE;

import android.app.Application;
import android.content.SharedPreferences;

import com.example.room.Adapter.AllPropertyAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
//import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

       // private static String baseUrl="http://room.oxfordcollege.edu.np/api/";
        private static String baseUrl="http://192.168.100.67/api/";
        private  static Retrofit retrofit;
        private static Gson gson;


    private String token;
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor()           {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest  = chain.request().newBuilder()
                    .addHeader("Authorization", " Bearer " + token)
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();

        public static Retrofit getRetrofitInstance(){
            if(retrofit==null){
                gson=new GsonBuilder()
                        .setLenient()
                        .create();
                retrofit=new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                      //  .addConverterFactory(ScalarsConverterFactory.create())
                        .build();

            }
            return retrofit;
    }
}

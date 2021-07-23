package com.example.room.Remote;

import com.example.room.Model.PropertyModel;

import java.util.Date;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //for Login
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> userLogin(@Field("email") String email, @Field("password") String password);

    //For Register
    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> userRegister(@Field("name") String name, @Field("email") String email,@Field("password") String password,@Field("phone_number") String phone
                     ,@Field("address") String address);
    //for Verification
    @FormUrlEncoded
    @POST("verifyotp")
    Call<ResponseBody> userVerify(@Field("email") String email,@Field("otp_code") String otp_code);

    //for available property
    @GET("property/detail/list")
    Call<List<PropertyModel>> getProperty();

//    @Headers("Content-Type: application/json")
    //@FormUrlEncoded
    @Multipart
    @POST("add/property")
    Call<ResponseBody> propertyAdd(
//            @Header("Authorization") String token,
            @Part("uid") Integer uid,
            @Part("email") String email,
            @Part("name") String Name,
            @Part("property_type") String propType,
            @Part("location") String propLocation,
            @Part("size") String propSize,
            @Part("rent") String propRent,
            @Part("date") String propDate,
            @Part("furniture") String furnitureType,
            @Part MultipartBody.Part file,
//            @Part String file,
            @Part("bathrooms") String bathrooms,
            @Part("bedrooms") String bedrooms
    );

    @GET("property/{location}")
    Call<List<PropertyModel>> getSearch(
            @Path("location") String location
    );
    @POST("searchproperty")
    Call<List<PropertyModel>> getData(
            @Query("searchquery") String query
    );




}

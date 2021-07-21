package com.example.room.Remote;

import com.example.room.Model.PropertyModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

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

    @FormUrlEncoded
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("addproperty")
    Call<PropertyModel> propertyAdd(
            @Header("token") String token,
            @Field("name") String Name,
            @Field("property_type") String propType,
            @Field("location") String propLocation,
            @Field("size") String propSize,
            @Field("rent") String propRent,
            @Field("date") String propDate,
            @Field("furniture") String furnitureType,
            @Field("image") String image,
            @Field("bathrooms") String bathrooms,
            @Field("bedrooms") String bedrooms
    );

}

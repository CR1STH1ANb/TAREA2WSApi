package com.example.tarea2.Retrofit;
import java.util.List;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.Call;

public interface Conexion {

    @GET("bankList")
    Call<List<Banco>> getBanco(@Header("Public-Merchant-Id")String id);
}

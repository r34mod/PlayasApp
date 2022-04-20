package com.example.playasapp.ModelsGPS;


import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class PlayasApi {

    public static FourSquareService fourSquareService=null;
    public static FourSquareService getFourSquareService(){


        OkHttpClient.Builder okhttp = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor httpLoggingInterceptor1 = loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okhttp.addInterceptor(loggingInterceptor);

        if(fourSquareService == null) {
            Retrofit.Builder retrofit = new Retrofit.Builder().
                    baseUrl("https://api.foursquare.com/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okhttp.build());

            fourSquareService = retrofit.build().create(FourSquareService.class);
        }

        return fourSquareService;
    }


    public interface FourSquareService{
        @GET("venues/explore/")
        Call<FourSquareResource> getFourSquareResponse(
                @Query("client_id") String client_id,
            @Query("client_secret") String client_secret,
            @Query("v") String v,
            @Query("loca") String loca,
            @Query("query") String query);
        }
    }


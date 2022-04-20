package com.example.playasapp.ModelsGPS;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.ClipData;
import android.location.Location;
import android.location.LocationListener;
import android.net.sip.SipSession;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.playasapp.Peds.Playa;
import com.example.playasapp.fragmentos.Playas;
import com.google.android.gms.common.api.Response;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit2.Call;

public class ModelsLocation extends AndroidViewModel implements LocationListener {
    public final LocationRepositorio locationRepositorio;

    String client_id = "BMRA0OSIBFAMQ3BB253NLRK24HMER2PDWMO4BWKKKKJGOD1E";
    String client_secret = "T3K2YNMKHVWGAKGAJNTP44P0STB1IQRON50JDAKYY0ABPYA1";

    MutableLiveData<String> latlon = new MutableLiveData<>();
    LiveData<List<Playa>> playasLocation;


    public ModelsLocation(@NonNull Application application){
        super(application);
        locationRepositorio = new LocationRepositorio(application);
        playasLocation = Transformations.switchMap(latlon, new Function<String, LiveData<List<Playa>>>() {
            @Override
            public LiveData<List<Playa>> apply(String input) {
                return locationRepositorio.getAllModel(input);
            }
        });

        }

    public void update(Playas playasModels){
        locationRepositorio.update(playasModels);
    }

    //te localiza al momento y te saca las playas mas cercanas a tu posicion
    public LiveData<List<Playa>> getPlayasLocation(){
        return playasLocation;
    }

    @SuppressLint("MissingPermission")
    public void getLocation(){
        try{
            FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(getApplication());
            fusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>(){
                @Override
                public void onSuccess(Location location){
                    if(location != null){
                        String loca = location.getLatitude() + ","+ location.getLongitude();
                        getData(loca);
                       latlon.postValue(loca);
                    }else{
                        String locaNull="44.00092, 22.10938";
                        latlon.postValue(locaNull);
                    }

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //fourSquare Api
    public void getData(final String loca){
        final PlayasApi.FourSquareService lista = PlayasApi.getFourSquareService();

        Call<FourSquareResource> call = lista.getFourSquareResponse(client_id, client_secret, "20220412", loca, "playas");

        call.enqueue(new Callback<FourSquareResource>(){
            //TERMINAR ESTE
           @Override
           public void onResponse(Call<FourSquareResource> call, Response<FourSquareResource> response){
               FourSquareResource resource = response.body();
               List<ClipData.Item> item = resource.response.groups.get(0).items;
           }
        });
    }

}

package com.example.playasapp.ModelsGPS;

import android.annotation.SuppressLint;
import android.app.Application;
import android.location.Location;
import android.location.LocationListener;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;


import com.example.playasapp.PlayasDB.Beachs;
import com.example.playasapp.Repositorio.BeachRepositorio;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import retrofit2.Call;
import retrofit2.Callback;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * Clase ModelsLocation que implementamos la clase LocationListener
 *
 * En esta clase encontramos la conexion con la API a traves de laas keys de client
 *
 *
 *
 */

public class ModelsLocation extends AndroidViewModel implements LocationListener {
    public final BeachRepositorio locationRepositorio;

    String client_id = "BMRA0OSIBFAMQ3BB253NLRK24HMER2PDWMO4BWKKKKJGOD1E";
    String client_secret = "T3K2YNMKHVWGAKGAJNTP44P0STB1IQRON50JDAKYY0ABPYA1";


    /**
     *
     * Creamos dos arrayList para obtener los sitios recomendados en la localizacion que
     * tengamos en el momento
     *
     * Este proceso se hace en ese momento y va cambiando si nosotros cambiamos de localizacion
     *
     *
     */
    MutableLiveData<String> latlon = new MutableLiveData<>();
    LiveData<List<Beachs>> playasLocation;


    public ModelsLocation(@NonNull Application application){
        super(application);
        locationRepositorio = new BeachRepositorio(application);
        playasLocation = Transformations.switchMap(latlon, new Function<String, LiveData<List<Beachs>>>() {
            @Override
            public LiveData<List<Beachs>> apply(String input) {
                return locationRepositorio.getAllModel(input);
            }
        });

        }

        //Actualiza la informacion obtenida al cambiar de posicion
    public void update(Beachs playasModels){
        locationRepositorio.update(playasModels);
    }

    //te localiza al momento y te saca las playas mas cercanas a tu posicion
    public LiveData<List<Beachs>> getPlayasLocation(){
        return playasLocation;
    }


    /**
     *
     *  Metodo que se ejecuta siempre que estemos en la pantalla para obtener la ubicacion nuestra
     *
     */

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

    /**
     *
     * Se conecta a la API y va obteniendo los sitios recomendados mendiante el objeto creada Beach que
     * lo tenemos en una base de datos interna al movil (DAO)
     *
     * @param loca
     */
    public void getData(final String loca){
        final PlayasApi.FourSquareService lista = PlayasApi.getFourSquareService();

        Call<FourSquareResource> call = lista.getFourSquareResponse(client_id, client_secret, "20220412", loca, "playas");

        call.enqueue(new Callback<FourSquareResource>(){
            @Override
            public void onResponse(Call<FourSquareResource> call, retrofit2.Response<FourSquareResource> response) {
                FourSquareResource resource = response.body();
                List<Item> item = resource.response.groups.get(0).items;
                final List<Beachs> listaPlaya = new ArrayList<>();
                for(int i = 0; i<item.size(); i++){
                    Beachs playa1 = new Beachs();
                    playa1.name = item.get(i).venue.name;
                    playa1.beachsId = item.get(i).venue.id;
                    playa1.address = item.get(i).venue.location.address;
                    playa1.latlon = loca;
                    listaPlaya.add(playa1);
                }
                if(response.isSuccessful()){
                    locationRepositorio.insert(listaPlaya);
                }
            }

            @Override
            public void onFailure(Call<FourSquareResource> call, Throwable t) {
                Log.d("API", " error"+ t.getMessage());
            }

        });
    }


    /**
     *
     * Se ejecuta siempre para obtener la ubicacion exacta
     *
     * @param location
     */
    @Override
    public void onLocationChanged(@NonNull Location location) {
        double lat = location.getLatitude();
        double lon = location.getLongitude();

        String latlon1 = lat + "," +lon;
        getData(latlon1);
        latlon.postValue(latlon1);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider){

    }

    @Override
    public void onProviderDisabled(@NonNull String provider){

    }

}

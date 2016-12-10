package com.dawidj.weatherforecastapp.api;

import com.dawidj.weatherforecastapp.models.Weather.City;
import com.dawidj.weatherforecastapp.models.autocomplete.CityID;
import com.dawidj.weatherforecastapp.models.details.CityLatLng;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by djamrozy on 02.12.2016.
 */

public interface WeatherApi {

//    @GET("{lat},{lng}?lang=pl&exclude=flags,alerts,minutely&units=ca")
//    Call<City> getCity(@Path("lat") String lat,
//                       @Path("lng") String lng);

    @GET("{lat},{lng}?lang=pl&exclude=flags,alerts,minutely&units=ca")
    Observable<City> getCity(@Path("lat") String lat,
                             @Path("lng") String lng);

    @GET("json?")
    Observable<CityID> getCityName(@Query("input") String city,
                                   @Query("types") String types,
                                   @Query("key") String key);

    @GET("json?")
    Observable<CityLatLng> getCityLatLng(@Query("placeid") String placeID,
                                         @Query("key") String key);


}

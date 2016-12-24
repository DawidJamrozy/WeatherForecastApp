package com.dawidj.weatherforecastapp.api;

import com.dawidj.weatherforecastapp.models.autocomplete.CityID;
import com.dawidj.weatherforecastapp.models.darksky.City;
import com.dawidj.weatherforecastapp.models.details.CityLatLng;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by djamrozy on 02.12.2016.
 */

public interface WeatherApi {


    @GET("{lat},{lng}?")
    Observable<City> getCity(@Path("lat") String lat,
                             @Path("lng") String lng,
                             @Query("lang") String lang,
                             @Query("exclude") String exclude,
                             @Query("units") String units);

    @GET("json?")
    Observable<CityID> getCityName(@Query("input") String city,
                                   @Query("types") String types,
                                   @Query("language") String language,
                                   @Query("key") String key);

    @GET("json?")
    Observable<CityLatLng> getCityLatLng(@Query("placeid") String placeID,
                                         @Query("language") String language,
                                         @Query("key") String key);


}

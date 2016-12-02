package com.dawidj.weatherforecastapp.api;

import com.dawidj.weatherforecastapp.models.City;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by djamrozy on 02.12.2016.
 */

public interface WeatherAPI {

    @GET("{lat},{lng}?lang=pl&exclude=flags,alerts&units=ca")
    Call<City> getCity(@Path("lat") String lat,
                       @Path("lng") String lng);

}

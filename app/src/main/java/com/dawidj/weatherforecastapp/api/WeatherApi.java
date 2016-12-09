package com.dawidj.weatherforecastapp.api;

import com.dawidj.weatherforecastapp.models.City;
import com.dawidj.weatherforecastapp.models.CityID;
import com.dawidj.weatherforecastapp.models.CityLatLng;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by djamrozy on 02.12.2016.
 */

public interface WeatherApi {

    @GET("{lat},{lng}?lang=pl&exclude=flags,alerts,minutely&units=ca")
    Call<City> getCity(@Path("lat") String lat,
                       @Path("lng") String lng);

    @GET("{lat},{lng}?lang=pl&exclude=flags,alerts,minutely&units=ca")
    Observable<City> getCityO(@Path("lat") String lat,
                             @Path("lng") String lng);

    @GET("json?input={city}&types=(cities)&key=AIzaSyAuZlLTq6aP5EXbPbLmhVBlFHXdMG_aUJM")
    Observable<CityID> getCityName(@Path("city") String cityName);

    @GET("json?placeid={placeID}&key=AIzaSyBQG9eG_ynd0kMJuAGA4JIV2rrFvRVZMiE")
    Observable<CityLatLng> getCityLatLng (@Path("placeID") String placeID);



}

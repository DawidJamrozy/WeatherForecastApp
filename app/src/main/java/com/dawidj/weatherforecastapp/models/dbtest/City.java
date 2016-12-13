package com.dawidj.weatherforecastapp.models.dbtest;

import android.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Dawidj on 24.10.2016.
 */
@Entity
public class City extends BaseObservable {

    @Id
    private Long id;

    private Long cityID;

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("currently")
    @Expose
    @ToOne(joinProperty = "cityID")
    private Currently currently;

    @SerializedName("hourly")
    @Expose
    @ToOne(joinProperty = "cityID")
    private Hourly hourly;

    @SerializedName("daily")
    @Expose
    @ToOne(joinProperty = "cityID")
    private Daily daily;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 448079911)
    private transient CityDao myDao;
    @Generated(hash = 2116550664)
    public City(Long id, Long cityID, String name, Double latitude,
            Double longitude, String timezone) {
        this.id = id;
        this.cityID = cityID;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
    }
    @Generated(hash = 750791287)
    public City() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCityID() {
        return this.cityID;
    }
    public void setCityID(Long cityID) {
        this.cityID = cityID;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Double getLatitude() {
        return this.latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return this.longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public String getTimezone() {
        return this.timezone;
    }
    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }
    @Generated(hash = 172526688)
    private transient Long currently__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1695167837)
    public Currently getCurrently() {
        Long __key = this.cityID;
        if (currently__resolvedKey == null
                || !currently__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CurrentlyDao targetDao = daoSession.getCurrentlyDao();
            Currently currentlyNew = targetDao.load(__key);
            synchronized (this) {
                currently = currentlyNew;
                currently__resolvedKey = __key;
            }
        }
        return currently;
    }

    public Currently getCurrentylWithoutId() {
        return currently;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1388942052)
    public void setCurrently(Currently currently) {
        synchronized (this) {
            this.currently = currently;
            cityID = currently == null ? null : currently.getId();
            currently__resolvedKey = cityID;
        }
    }
    @Generated(hash = 502481813)
    private transient Long hourly__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 718557327)
    public Hourly getHourly() {
        Long __key = this.cityID;
        if (hourly__resolvedKey == null || !hourly__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            HourlyDao targetDao = daoSession.getHourlyDao();
            Hourly hourlyNew = targetDao.load(__key);
            synchronized (this) {
                hourly = hourlyNew;
                hourly__resolvedKey = __key;
            }
        }
        return hourly;
    }
    public Hourly getHourlyWithoutId() {
        return hourly;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 444633793)
    public void setHourly(Hourly hourly) {
        synchronized (this) {
            this.hourly = hourly;
            cityID = hourly == null ? null : hourly.getId();
            hourly__resolvedKey = cityID;
        }
    }
    @Generated(hash = 2017667076)
    private transient Long daily__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 910336100)
    public Daily getDaily() {
        Long __key = this.cityID;
        if (daily__resolvedKey == null || !daily__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DailyDao targetDao = daoSession.getDailyDao();
            Daily dailyNew = targetDao.load(__key);
            synchronized (this) {
                daily = dailyNew;
                daily__resolvedKey = __key;
            }
        }
        return daily;
    }

    public Daily getDailyWithoutId() {
        return  daily;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2137349473)
    public void setDaily(Daily daily) {
        synchronized (this) {
            this.daily = daily;
            cityID = daily == null ? null : daily.getId();
            daily__resolvedKey = cityID;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 293508440)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCityDao() : null;
    }

}

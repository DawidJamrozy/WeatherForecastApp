package com.dawidj.weatherforecastapp.models.dbtest;

import android.databinding.BaseObservable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToOne;

/**
 * Created by Dawidj on 24.10.2016.
 */
@Entity
public class City extends BaseObservable implements Parcelable {

    @Id
    private Long id;

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
    @ToOne(joinProperty = "id")
    private Currently currently;

    @SerializedName("hourly")
    @Expose
    @ToOne(joinProperty = "id")
    private Hourly hourly;

    @SerializedName("daily")
    @Expose
    @ToOne(joinProperty = "id")
    private Daily daily;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 448079911)
    private transient CityDao myDao;

    @Generated(hash = 1339016982)
    public City(Long id, String name, Double latitude, Double longitude, String timezone) {
        this.id = id;
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
    @Generated(hash = 1087511340)
    public Currently getCurrently() {
        Long __key = this.id;
        if (currently__resolvedKey == null || !currently__resolvedKey.equals(__key)) {
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 893711170)
    public void setCurrently(Currently currently) {
        synchronized (this) {
            this.currently = currently;
            id = currently == null ? null : currently.getId();
            currently__resolvedKey = id;
        }
    }

    @Generated(hash = 502481813)
    private transient Long hourly__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1721955440)
    public Hourly getHourly() {
        Long __key = this.id;
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1280890718)
    public void setHourly(Hourly hourly) {
        synchronized (this) {
            this.hourly = hourly;
            id = hourly == null ? null : hourly.getId();
            hourly__resolvedKey = id;
        }
    }

    @Generated(hash = 2017667076)
    private transient Long daily__resolvedKey;

    /** To-one relationship, resolved on first access. */
    @Generated(hash = 595147050)
    public Daily getDaily() {
        Long __key = this.id;
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

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1533481190)
    public void setDaily(Daily daily) {
        synchronized (this) {
            this.daily = daily;
            id = daily == null ? null : daily.getId();
            daily__resolvedKey = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.latitude);
        dest.writeValue(this.longitude);
        dest.writeString(this.timezone);
        dest.writeParcelable(this.currently, flags);
        dest.writeParcelable(this.hourly, flags);
        dest.writeParcelable(this.daily, flags);
    }

    protected City(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
        this.timezone = in.readString();
        this.currently = in.readParcelable(Currently.class.getClassLoader());
        this.hourly = in.readParcelable(Hourly.class.getClassLoader());
        this.daily = in.readParcelable(Daily.class.getClassLoader());
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };

    public Currently getCurrentylWithoutId() {
        return currently;
    }

    public Daily getDailyWithoutId() {
        return daily;
    }

    public Hourly getHourlyWithoutId() {
        return hourly;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 293508440)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCityDao() : null;
    }
}

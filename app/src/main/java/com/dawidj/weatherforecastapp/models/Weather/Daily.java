package com.dawidj.weatherforecastapp.models.Weather;

import android.databinding.BaseObservable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Dawidj on 24.10.2016.
 */
@Entity
public class Daily extends BaseObservable {

    @Id
    private Long id;

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("data")
    @Expose
    @ToMany(referencedJoinProperty = "dailyDataID")
    private List<DailyData> data = new ArrayList<DailyData>();

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 870068004)
    private transient DailyDao myDao;

    @Generated(hash = 965242085)
    public Daily(Long id, String summary, String icon) {
        this.id = id;
        this.summary = summary;
        this.icon = icon;
    }

    @Generated(hash = 2135515054)
    public Daily() {
    }

    /**
     *
     * @return
     * The summary
     */
    @Keep
    public String getSummary() {
        return summary;
    }

    /**
     *
     * @param summary
     * The summary
     */
    @Keep
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     *
     * @return
     * The icon
     */
    @Keep
    public String getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     * The icon
     */
    @Keep
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     *
     * @return
     * The data
     */
    @Keep
    public List<DailyData> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    @Keep
    public void setData(List<DailyData> data) {
        this.data = data;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1283600904)
    public synchronized void resetData() {
        data = null;
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
    @Generated(hash = 445972336)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDailyDao() : null;
    }
}

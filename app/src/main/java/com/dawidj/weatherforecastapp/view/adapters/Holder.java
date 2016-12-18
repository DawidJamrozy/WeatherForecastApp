package com.dawidj.weatherforecastapp.view.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

/**
 * Created by Dawidj on 18.12.2016.
 */

public class Holder extends RecyclerView.ViewHolder {

    @BindView(R.id.delete)
    ImageView imageView;

    @Inject
    Realm realm;

    private ViewDataBinding binding;

    public Holder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        App.getApplication().getWeatherComponent().inject(this);
        ButterKnife.bind(this, itemView);

        imageView.setOnClickListener(view -> {

        });
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}

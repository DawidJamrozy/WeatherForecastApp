package com.dawidj.weatherforecastapp.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dawidj.weatherforecastapp.R;
import com.dawidj.weatherforecastapp.app.App;
import com.dawidj.weatherforecastapp.databinding.CityFragmentBinding;
import com.dawidj.weatherforecastapp.viewModel.CityViewModel;

import butterknife.ButterKnife;

/**
 * Created by Dawidj on 30.11.2016.
 */

public class CityViewFragment extends Fragment {

    private CityFragmentBinding binding;
    private CityViewModel cityViewModel;

    public CityViewFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.city_fragment, container, false);
        View view = binding.getRoot();
        ButterKnife.bind(this, view);
        cityViewModel = new CityViewModel(getActivity());
        binding.setCity(cityViewModel);
        App.getApplication().getWeatherComponent().inject(cityViewModel);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}

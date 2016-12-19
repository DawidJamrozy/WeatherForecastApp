package com.dawidj.weatherforecastapp.utils;

/**
 * Created by djamrozy on 19.12.2016.
 */

public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}

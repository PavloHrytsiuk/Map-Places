package org.hrytsiuk.mapmarkers.base.mvp;


import android.support.v7.app.ActionBar;
import android.support.annotation.NonNull;

import org.hrytsiuk.mapmarkers.network.rx.RetrofitException;

public interface BaseView {

    /**
     * This is base method used to show/hide progress during some background task
     */
    void showProgress(final boolean visible);

    /**
     * This is base method used to notify user about some http request error
     */
    void httpError(@NonNull final RetrofitException e);

    /**
     * This is base method used to show Toast message to user
     */
    void showToast(@NonNull final String message);

    void setTitle(ActionBar actionBar, String text);
}

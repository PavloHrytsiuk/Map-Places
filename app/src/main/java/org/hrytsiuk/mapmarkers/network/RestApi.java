package org.hrytsiuk.mapmarkers.network;

import android.support.annotation.NonNull;

import org.hrytsiuk.mapmarkers.network.rx.PlaceApi;

/**
 * RestApi for retrieving data from the network.
 */
public final class RestApi {

    private static final class RestApiHolder {
        private static final RestApi INSTANCE = new RestApi();
    }

    private RestApi() {}

    @NonNull
    public static RestApi getInstance() {
        return RestApiHolder.INSTANCE;
    }

    @NonNull
    public PlaceApi createPlaceApi() {
        return RetrofitManager.retrofit.create(PlaceApi.class);
    }
}

package org.hrytsiuk.mapmarkers.network.rx;

import org.hrytsiuk.mapmarkers.places.PlacesWrapper;

import retrofit2.http.GET;
import rx.Observable;

public interface PlaceApi {

    @GET("response.json")
    Observable<PlacesWrapper> fetchPlaces();
}

package org.hrytsiuk.mapmarkers.places.interactor;

import org.hrytsiuk.mapmarkers.places.Place;
import org.hrytsiuk.mapmarkers.places.PlacesWrapper;
import org.hrytsiuk.mapmarkers.network.RestApi;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public final class PlaceInteractorImpl implements PlaceInteractor {

    @Override
    public Observable<List<Place>> fetchPlaces() {
        return RestApi.getInstance().createPlaceApi().fetchPlaces().map(new Func1<PlacesWrapper, List<Place>>() {
            @Override
            public List<Place> call(final PlacesWrapper placesWrapper) {
                return placesWrapper.getPlaces();
            }
        });
    }
}

package org.hrytsiuk.mapmarkers.places.interactor;

import org.hrytsiuk.mapmarkers.base.mvp.BaseInteractor;
import org.hrytsiuk.mapmarkers.places.Place;

import java.util.List;

import rx.Observable;

public interface PlaceInteractor extends BaseInteractor {

    Observable<List<Place>> fetchPlaces();
}

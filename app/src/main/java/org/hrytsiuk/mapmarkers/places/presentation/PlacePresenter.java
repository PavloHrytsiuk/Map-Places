package org.hrytsiuk.mapmarkers.places.presentation;

import org.hrytsiuk.mapmarkers.base.mvp.BasePresenter;

public interface PlacePresenter extends BasePresenter<PlaceView> {

    void fetchPlaces();
}

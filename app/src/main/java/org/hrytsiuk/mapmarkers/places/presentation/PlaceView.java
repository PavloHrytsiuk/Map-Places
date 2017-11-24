package org.hrytsiuk.mapmarkers.places.presentation;

import org.hrytsiuk.mapmarkers.base.mvp.BaseView;
import org.hrytsiuk.mapmarkers.places.Place;

import java.util.List;

public interface PlaceView extends BaseView {

    void loadPlaces(List<Place> places);
}

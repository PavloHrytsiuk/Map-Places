package org.hrytsiuk.mapmarkers.places;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class PlacesWrapper {

    @SerializedName("Status")
    private int status;
    @SerializedName("Result")
    private List<Place> places;

    public PlacesWrapper() {
    }

    public int getStatus() {
        return status;
    }

    public List<Place> getPlaces() {
        return places;
    }
}

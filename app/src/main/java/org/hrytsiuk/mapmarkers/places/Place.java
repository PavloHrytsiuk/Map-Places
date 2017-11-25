package org.hrytsiuk.mapmarkers.places;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public final class Place implements Parcelable {

    @SerializedName("ObjectId")
    private int objectId;
    @SerializedName("Title")
    private String title;
    @SerializedName("Description")
    private String description;
    @SerializedName("ImageUrl")
    private String imageUrl;
    @SerializedName("Latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;

    public Place() {

    }

    protected Place(final Parcel in) {
        objectId = in.readInt();
        title = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(final Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(final int size) {
            return new Place[size];
        }
    };

    public int getObjectId() {
        return objectId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel parcel, final int i) {
        parcel.writeInt(objectId);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(imageUrl);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }
}

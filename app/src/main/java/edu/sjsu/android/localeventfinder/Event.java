package edu.sjsu.android.localeventfinder;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Event implements Parcelable {

    private final int imageID;
    private final int eventNameID;
    private final int locationID;
    private final int dateID;
    private final int descriptionID;

    public Event(int imageID, int eventNameID, int locationID, int dateID, int descriptionID) {
        this.imageID = imageID;
        this.eventNameID = eventNameID;
        this.locationID = locationID;
        this.dateID = dateID;
        this.descriptionID = descriptionID;
    }

    protected Event(Parcel in) {
        imageID = in.readInt();
        eventNameID = in.readInt();
        locationID = in.readInt();
        dateID = in.readInt();
        descriptionID = in.readInt();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public int getImageID() {
        return imageID;
    }

    public int getEventNameID() {
        return eventNameID;
    }

    public int getLocationID() {
        return locationID;
    }

    public int getDateID() {
        return dateID;
    }

    public int getDescriptionID() {
        return descriptionID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(imageID);
        parcel.writeInt(eventNameID);
        parcel.writeInt(locationID);
        parcel.writeInt(dateID);
        parcel.writeInt(descriptionID);
    }
}

package edu.sjsu.android.localeventfinder;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {

    private final String imageUrl; // Change to String for URL
    private final String eventName;
    private final String location;
    private final String date;
    private final String description;
    private double latitude;
    private double longitude;
    private boolean isFavorite;



    public Event(String imageUrl, String eventName, String location, String date, String description, double latitude, double longitude) {
        this.imageUrl = imageUrl;
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Event(Parcel in) {
        imageUrl = in.readString();
        eventName = in.readString();
        location = in.readString();
        date = in.readString();
        description = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
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

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEventName() {
        return eventName;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
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

    public boolean isFavorite() {return isFavorite;}

    public void setFavorite(boolean favorite) {isFavorite = favorite;}

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
        parcel.writeString(eventName);
        parcel.writeString(location);
        parcel.writeString(date);
        parcel.writeString(description);
        parcel.writeDouble(latitude);
        parcel.writeDouble(longitude);
    }
}

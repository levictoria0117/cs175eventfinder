package edu.sjsu.android.localeventfinder;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    private long id;
    private String imageUrl;
    private String eventName;
    private String location;
    private String date;
    private String description;
    private double latitude;
    private double longitude;
    private boolean favorite;
    private boolean registered;

    public Event(String imageUrl, String eventName, String location, String date,
                 String description, double latitude, double longitude,
                 boolean favorite, boolean registered) {
        this.imageUrl = imageUrl;
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.favorite = favorite;
        this.registered = registered;
    }

    protected Event(Parcel in) {
        id = in.readLong();
        imageUrl = in.readString();
        eventName = in.readString();
        location = in.readString();
        date = in.readString();
        description = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        favorite = in.readByte() != 0;
        registered = in.readByte() != 0;
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

    // Getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getImageUrl() { return imageUrl; }
    public String getEventName() { return eventName; }
    public String getLocation() { return location; }
    public String getDate() { return date; }
    public String getDescription() { return description; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
    public boolean isFavorite() { return favorite; }
    public void setFavorite(boolean favorite) { this.favorite = favorite; }
    public boolean isRegistered() { return registered; }
    public void setRegistered(boolean registered) { this.registered = registered; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(imageUrl);
        dest.writeString(eventName);
        dest.writeString(location);
        dest.writeString(date);
        dest.writeString(description);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeByte((byte) (favorite ? 1 : 0));
        dest.writeByte((byte) (registered ? 1 : 0));
    }
}

package edu.sjsu.android.localeventfinder;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events")
public class Event implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String imageUrl; // Change to String for URL
    private String name;
    private String location;
    private String date;
    private String description;
    private double latitude;
    private double longitude;
    private boolean isFavorite;

    public Event(String imageUrl, String name, String location, String date, String description, double latitude, double longitude) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.location = location;
        this.date = date;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.isFavorite = false;
    }

    protected Event(Parcel in) {
        id = in.readInt();
        imageUrl = in.readString(); // Change here
        name = in.readString();
        location = in.readString();
        date = in.readString();
        description = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        isFavorite = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(imageUrl); // Change here
        dest.writeString(name);
        dest.writeString(location);
        dest.writeString(date);
        dest.writeString(description);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
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
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}





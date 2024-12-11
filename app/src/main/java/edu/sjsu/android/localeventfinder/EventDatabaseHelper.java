package edu.sjsu.android.localeventfinder;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class EventDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "EventsDB";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_EVENTS = "events";

    // Column names
    private static final String KEY_ID = "id";
    private static final String KEY_IMAGE_URL = "image_url";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_DATE = "date";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    private static final String KEY_FAVORITE = "favorite";
    private static final String KEY_REGISTERED = "registered";

    public EventDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_EVENTS_TABLE = "CREATE TABLE " + TABLE_EVENTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_IMAGE_URL + " TEXT,"
                + KEY_NAME + " TEXT,"
                + KEY_LOCATION + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT,"
                + KEY_LATITUDE + " REAL,"
                + KEY_LONGITUDE + " REAL,"
                + KEY_FAVORITE + " INTEGER DEFAULT 0,"
                + KEY_REGISTERED + " INTEGER DEFAULT 0"
                + ")";
        db.execSQL(CREATE_EVENTS_TABLE);

        // Insert predefined events
        insertPredefinedEvents(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENTS);
        onCreate(db);
    }

    private void insertPredefinedEvents(SQLiteDatabase db) {
        List<Event> events = new ArrayList<>();
        events.add(new Event("https://sanjoseholidayparade.com/wp-content/uploads/2019/08/SJ-Holiday-Parade-2006-358.jpg",  "San Jose Holiday Parade", "San Jose, CA", "2024-12-15", "Join us for the annual San Jose Holiday Parade with floats, music, and festive fun!", 37.3382, -121.8863, false, false));
        events.add(new Event("https://images.squarespace-cdn.com/content/v1/651075c1ac6d252b427acf9a/93eddb7f-28c2-4554-a579-2c7b4f1a295c/13-min.jpg", "Downtown Ice Skating", "San Jose, CA", "2024-12-16", "Outdoor ice skating rink in the heart of downtown San Jose. Perfect for the whole family.", 37.3382, -121.8853, false, false));
        events.add(new Event("https://www.metrosiliconvalley.com/wp-content/uploads/sites/16/2019/12/san-jose-christmas-in-the-park-world-record-trees-1-1024x703-1-1024x580.jpg", "Christmas in the Park", "San Jose, CA", "2024-12-17", "Experience the magic of Christmas with thousands of lights, holiday displays, and seasonal activities.", 37.3376, -121.8882, false, false));
        events.add(new Event("https://cdn.kqed.org/wp-content/uploads/sites/2/2018/02/La_Misa_Negra_PREFERRED_sop01_rgb_md-800x401.jpg", "San Jose Jazz Winter Fest", "San Jose, CA", "2024-12-18", "A cozy celebration of live jazz music, local artists, and holiday cheer.", 37.3352, -121.8815, false, false));
        events.add(new Event("https://media.licdn.com/dms/image/v2/C561BAQGT5vzAKJm6qg/company-background_10000/company-background_10000/0/1585488328418/santa_clara_county_fairgrounds_cover?e=2147483647&v=beta&t=MBSzJgUZbbFE3_kxlHzMd8z007JRTNCh1yZRJEIZess", "Santa Clara County Fairgrounds Holiday Market", "San Jose, CA", "2024-12-19", "Shop for unique gifts, artisanal foods, and crafts at the Holiday Market.", 37.3163, -121.9467, false, false));
        events.add(new Event("https://www.bothman.com/wp-content/uploads/2023/10/Avaya-Stadium-1.jpg", "San Jose Earthquakes Soccer Game", "San Jose, CA", "2024-12-20", "Cheer on the San Jose Earthquakes in their last match of the season at PayPal Park.", 37.3328, -121.9008, false, false));
        events.add(new Event("https://www.thepierce.com/wp-content/uploads/2016/12/pierce_lights.jpg", "Winter Wonderland at Vasona Lake", "Los Gatos, CA", "2024-12-21", "Celebrate the season at Vasona Lake with a winter light display, train rides, and family-friendly activities.", 37.2391, -121.9718, false, false));
        events.add(new Event("https://streamline.imgix.net/a772b068-76bf-4e49-880d-090dc5c1d0e8/722b388f-0e54-4041-8b70-176147dc192c/IMG_5061.JPG?ixlib=rb-1.1.0&w=2000&h=2000&fit=max&or=0&s=6355c16e24c1d58347c29f32348280f9", "Holiday Craft Fair", "San Jose, CA", "2024-12-22", "Explore handmade gifts, crafts, and delicious food at the San Jose Holiday Craft Fair.", 37.3342, -121.8893, false, false));
        events.add(new Event("https://www.sjpl.org/wp-content/uploads/sites/142/2024/04/chiavari-1024x640.png", "Cirque du Soleil - OVO", "San Jose, CA", "2024-12-23", "A thrilling acrobatic performance filled with humor and amazing circus acts.", 37.3395, -121.8877, false, false));
        events.add(new Event("https://i.ytimg.com/vi/YZ1o0dm-HsY/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLA0D-OEYF-pWKvizVwqZ9LHDhDC6Q", "Christmas Eve Service at Willow Glen Baptist Church", "San Jose, CA", "2024-12-24", "A peaceful Christmas Eve service with candlelight, music, and communion.", 37.2954, -121.8981, false, false));

        for (Event event : events) {
            ContentValues values = new ContentValues();
            values.put(KEY_IMAGE_URL, event.getImageUrl());
            values.put(KEY_NAME, event.getEventName());
            values.put(KEY_LOCATION, event.getLocation());
            values.put(KEY_DATE, event.getDate());
            values.put(KEY_DESCRIPTION, event.getDescription());
            values.put(KEY_LATITUDE, event.getLatitude());
            values.put(KEY_LONGITUDE, event.getLongitude());
            values.put(KEY_FAVORITE, event.isFavorite() ? 1 : 0);
            values.put(KEY_REGISTERED, event.isRegistered() ? 1 : 0);

            db.insert(TABLE_EVENTS, null, values);
        }
    }

    // Add an event to the database
    public long addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_IMAGE_URL, event.getImageUrl());
        values.put(KEY_NAME, event.getEventName());
        values.put(KEY_LOCATION, event.getLocation());
        values.put(KEY_DATE, event.getDate());
        values.put(KEY_DESCRIPTION, event.getDescription());
        values.put(KEY_LATITUDE, event.getLatitude());
        values.put(KEY_LONGITUDE, event.getLongitude());
        values.put(KEY_FAVORITE, event.isFavorite() ? 1 : 0);
        values.put(KEY_REGISTERED, event.isRegistered() ? 1 : 0);

        long id = db.insert(TABLE_EVENTS, null, values);
        db.close();
        return id;
    }

    // Get all events
    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EVENTS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndexOrThrow(KEY_ID);
            int imageUrlIndex = cursor.getColumnIndexOrThrow(KEY_IMAGE_URL);
            int nameIndex = cursor.getColumnIndexOrThrow(KEY_NAME);
            int locationIndex = cursor.getColumnIndexOrThrow(KEY_LOCATION);
            int dateIndex = cursor.getColumnIndexOrThrow(KEY_DATE);
            int descriptionIndex = cursor.getColumnIndexOrThrow(KEY_DESCRIPTION);
            int latitudeIndex = cursor.getColumnIndexOrThrow(KEY_LATITUDE);
            int longitudeIndex = cursor.getColumnIndexOrThrow(KEY_LONGITUDE);
            int favoriteIndex = cursor.getColumnIndexOrThrow(KEY_FAVORITE);
            int registeredIndex = cursor.getColumnIndexOrThrow(KEY_REGISTERED);

            do {
                Event event = new Event(
                        cursor.getString(imageUrlIndex),
                        cursor.getString(nameIndex),
                        cursor.getString(locationIndex),
                        cursor.getString(dateIndex),
                        cursor.getString(descriptionIndex),
                        cursor.getDouble(latitudeIndex),
                        cursor.getDouble(longitudeIndex),
                        cursor.getInt(favoriteIndex) == 1,
                        cursor.getInt(registeredIndex) == 1
                );
                event.setId(cursor.getLong(idIndex));
                eventList.add(event);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eventList;
    }

    // Update favorite status
    public int updateFavoriteStatus(long eventId, boolean isFavorite) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_FAVORITE, isFavorite ? 1 : 0);

        return db.update(TABLE_EVENTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(eventId)});
    }

    // Update registered status
    public int updateRegisteredStatus(long eventId, boolean isRegistered) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_REGISTERED, isRegistered ? 1 : 0);

        return db.update(TABLE_EVENTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(eventId)});
    }

    // Get favorite events
    @SuppressLint("Range")
    public List<Event> getFavoriteEvents() {
        List<Event> eventList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EVENTS + " WHERE " + KEY_FAVORITE + " = 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Event event = new Event(
                        cursor.getString(cursor.getColumnIndex(KEY_IMAGE_URL)),
                        cursor.getString(cursor.getColumnIndex(KEY_NAME)),
                        cursor.getString(cursor.getColumnIndex(KEY_LOCATION)),
                        cursor.getString(cursor.getColumnIndex(KEY_DATE)),
                        cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)),
                        cursor.getDouble(cursor.getColumnIndex(KEY_LATITUDE)),
                        cursor.getDouble(cursor.getColumnIndex(KEY_LONGITUDE)),
                        true,
                        cursor.getInt(cursor.getColumnIndex(KEY_REGISTERED)) == 1
                );
                event.setId(cursor.getLong(cursor.getColumnIndex(KEY_ID)));
                eventList.add(event);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return eventList;
    }
}
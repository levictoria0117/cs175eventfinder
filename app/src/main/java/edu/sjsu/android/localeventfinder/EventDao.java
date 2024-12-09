package edu.sjsu.android.localeventfinder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {
    @Query("SELECT * FROM events")
    LiveData<List<Event>> getAllEvents();

    @Insert
    void insertEvent(Event event);

    @Query("SELECT * FROM events WHERE isFavorite = 1")
    LiveData<List<Event>> getFavoriteEvents();

    @Update
    void updateEvent(Event event);

    @Delete
    void deleteEvent(Event event);
}



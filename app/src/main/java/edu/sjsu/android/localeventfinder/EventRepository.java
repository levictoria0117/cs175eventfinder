package edu.sjsu.android.localeventfinder;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executors;

public class EventRepository {

    private final EventDao eventDao;

    public EventRepository(Application application) {
        EventDatabase database = EventDatabase.getInstance(application);
        eventDao = database.eventDao();
    }

    public LiveData<List<Event>> getFavoriteEvents() {
        return eventDao.getFavoriteEvents();
    }

    public void insertEvent(Event event) {
        Executors.newSingleThreadExecutor().execute(() -> eventDao.insertEvent(event));
    }

    public void updateEvent(Event event) {
        Executors.newSingleThreadExecutor().execute(() -> eventDao.updateEvent(event));
    }

    public void deleteEvent(Event event) {
        Executors.newSingleThreadExecutor().execute(() -> eventDao.deleteEvent(event));
    }
}


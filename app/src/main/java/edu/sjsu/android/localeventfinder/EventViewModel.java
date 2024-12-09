package edu.sjsu.android.localeventfinder;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class EventViewModel extends AndroidViewModel {

    private final EventRepository eventRepository;
    private final LiveData<List<Event>> events;

    public EventViewModel(Application application) {
        super(application);
        eventRepository = new EventRepository(application);
        events = eventRepository.getFavoriteEvents();
    }

    public LiveData<List<Event>> getEvents() {
        return events;
    }

    public void addEvent(Event event) {
        eventRepository.insertEvent(event);
    }

    public void updateEvent(Event event) {
        eventRepository.updateEvent(event);
    }

    public void deleteEvent(Event event) {
        eventRepository.deleteEvent(event);
    }

    public LiveData<List<Event>> getFavoriteEvents() {
        return eventRepository.getFavoriteEvents();
    }
}




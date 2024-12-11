package edu.sjsu.android.localeventfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {

    private final ArrayList<Event> eventList;
    private OnEventCardClickedListener clickListener;

    public TicketAdapter(ArrayList<Event> eventList) {
        this.eventList = eventList;
    }

    public void setOnEventCardClickedListener(OnEventCardClickedListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ticket, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = eventList.get(position);

        // Set image URL for the event image (use an image loading library like Glide or Picasso)
        // Assuming you are using an image loading library like Glide or Picasso
        Glide.with(holder.itemView.getContext())
                .load(event.getImageUrl())
                .into(holder.eventImage);

        // Set text fields for event name, location, and date
        holder.eventName.setText(event.getEventName());
        holder.eventLocation.setText(event.getLocation());
        holder.eventDate.setText(event.getDate());

        holder.itemView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView eventImage;
        public final TextView eventName;
        public final TextView eventLocation;
        public final TextView eventDate;

        public ViewHolder(View view) {
            super(view);
            eventImage = view.findViewById(R.id.event_image);
            eventName = view.findViewById(R.id.event_name);
            eventLocation = view.findViewById(R.id.event_location);
            eventDate = view.findViewById(R.id.event_date);
        }
    }
}

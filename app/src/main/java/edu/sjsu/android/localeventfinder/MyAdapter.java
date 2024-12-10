package edu.sjsu.android.localeventfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final ArrayList<Event> eventList;
    private OnEventCardClickedListener clickListener;
    private boolean isLargeCard = false;

    public MyAdapter(ArrayList<Event> eventList, boolean isLargeCard) {
        this.eventList = eventList;
        this.isLargeCard = isLargeCard;
    }

    public void setOnEventCardClickedListener(OnEventCardClickedListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(isLargeCard ? R.layout.large_event_card_layout : R.layout.small_event_card_layout,
                        parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Event event = eventList.get(position);

        if (holder.eventImage != null) {
            Glide.with(holder.eventImage.getContext())
                    .load(event.getImageUrl())
                    .into(holder.eventImage);
        }

        if (holder.eventName != null) {
            holder.eventName.setText(event.getEventName());
        }

        if (holder.eventLocation != null) {
            holder.eventLocation.setText(event.getLocation());
        }

        if (holder.eventDate != null) {
            holder.eventDate.setText(event.getDate());
        }

        if (holder.eventDescription != null) {
            holder.eventDescription.setVisibility(View.GONE);
        }

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
        public final TextView eventDescription;

        public ViewHolder(View view) {
            super(view);
            eventImage = view.findViewById(R.id.event_image);
            eventName = view.findViewById(R.id.event_name);
            eventLocation = view.findViewById(R.id.event_location);
            eventDate = view.findViewById(R.id.event_date);
            eventDescription = view.findViewById(R.id.event_description);

            if (eventName == null || eventLocation == null || eventDate == null || eventDescription == null) {
                throw new NullPointerException("Missing TextViews in the layout XML file.");
            }
        }
    }
}



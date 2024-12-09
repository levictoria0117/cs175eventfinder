package edu.sjsu.android.localeventfinder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Event> eventList;
    private boolean isLargeCard;
    private OnItemClickListener onItemClickListener;

    public MyAdapter(List<Event> eventList, boolean isLargeCard) {
        this.eventList = eventList;
        this.isLargeCard = isLargeCard;
    }

    public void updateData(List<Event> newEvents) {
        this.eventList = newEvents;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
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

        Glide.with(holder.itemView.getContext())
                .load(event.getImageUrl())
                .placeholder(R.drawable.event_temp_background)
                .error(R.drawable.event_temp_background)
                .into(holder.eventImage);

        holder.eventName.setText(event.getName());
        holder.eventLocation.setText(event.getLocation());
        holder.eventDate.setText(event.getDate());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(event);
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

    public interface OnItemClickListener {
        void onItemClick(Event event);
    }
}



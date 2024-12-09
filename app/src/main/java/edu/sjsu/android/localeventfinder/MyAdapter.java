package edu.sjsu.android.localeventfinder;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Event> events;
    private boolean isLargeCard;
    private EventRepository eventRepository;
    private Context context;
    private OnItemClickListener onItemClickListener;
    private OnFavoriteClickListener onFavoriteClickListener;  // Add the favorite listener

    public MyAdapter(List<Event> events, boolean isLargeCard, Context context) {
        this.events = events;
        this.isLargeCard = isLargeCard;
        this.context = context;
        this.eventRepository = new EventRepository((Application) context.getApplicationContext());
    }

    @Override
    public int getItemViewType(int position) {
        return isLargeCard ? 1 : 0;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = (viewType == 1) ? R.layout.large_event_card_layout : R.layout.small_event_card_layout;
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Event event = events.get(position);
        holder.bind(event);

        holder.favoriteButton.setOnClickListener(v -> {
            event.setFavorite(!event.isFavorite());
            eventRepository.updateEvent(event);
            notifyItemChanged(position);
            if (onFavoriteClickListener != null) {
                onFavoriteClickListener.onFavoriteClick(event, event.isFavorite());
            }
        });

        if (event.isFavorite()) {
            holder.favoriteButton.setImageResource(R.drawable.rounded_bookmark_heart_24_selected);
        } else {
            holder.favoriteButton.setImageResource(R.drawable.rounded_bookmark_heart_24);
        }

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(event);
            }
        });
    }


    @Override
    public int getItemCount() {
        return events.size();
    }

    public void updateData(List<Event> newEvents) {
        events.clear();
        events.addAll(newEvents);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView eventName, eventLocation, eventDate;
        ImageView eventImage;
        ImageButton favoriteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.event_name);
            eventLocation = itemView.findViewById(R.id.event_location);
            eventDate = itemView.findViewById(R.id.event_date);
            eventImage = itemView.findViewById(R.id.event_image);
            favoriteButton = itemView.findViewById(R.id.favorite_button);
        }

        public void bind(Event event) {
            eventName.setText(event.getName());
            eventLocation.setText(event.getLocation());
            eventDate.setText(event.getDate());
            Glide.with(itemView.getContext())
                    .load(event.getImageUrl())
                    .into(eventImage);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Event event);
    }

    public interface OnFavoriteClickListener {
        void onFavoriteClick(Event event, boolean isFavorite);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnFavoriteClickListener(OnFavoriteClickListener listener) {  // Add this method
        this.onFavoriteClickListener = listener;
    }
}

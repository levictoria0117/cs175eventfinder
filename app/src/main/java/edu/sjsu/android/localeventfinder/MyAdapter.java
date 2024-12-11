package edu.sjsu.android.localeventfinder;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final List<Event> eventList;
    private final List<Event> favoritesList = new ArrayList<>();
    private OnEventCardClickedListener clickListener;
    private final boolean isLargeCard;

    public MyAdapter(List<Event> eventList, boolean isLargeCard) {
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

        Glide.with(holder.eventImage.getContext())
                .load(event.getImageUrl())
                .into(holder.eventImage);

        holder.eventName.setText(event.getEventName());
        holder.eventLocation.setText(event.getLocation());
        holder.eventDate.setText(event.getDate());

//        holder.favoriteButton.setColorFilter(event.isFavorite() ? Color.parseColor("#FF9800") : Color.parseColor("#B0BEC5"));
//
//        holder.favoriteButton.setOnClickListener(v -> {
//            event.setFavorite(!event.isFavorite());
//            if (event.isFavorite()) {
//                holder.favoriteButton.setColorFilter(Color.parseColor("#FF9800"));
//                if (!favoritesList.contains(event)) {
//                    favoritesList.add(event);
//                }
//            } else {
//                holder.favoriteButton.setColorFilter(Color.parseColor("#B0BEC5"));
//                favoritesList.remove(event);
//            }
//        });

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

    public List<Event> getFavoritesList() {
        return new ArrayList<>(favoritesList);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final ImageView eventImage;
        public final TextView eventName;
        public final TextView eventLocation;
        public final TextView eventDate;
//        public final ImageButton favoriteButton;

        public ViewHolder(View view) {
            super(view);
            eventImage = view.findViewById(R.id.event_image);
            eventName = view.findViewById(R.id.event_name);
            eventLocation = view.findViewById(R.id.event_location);
            eventDate = view.findViewById(R.id.event_date);
//            favoriteButton = view.findViewById(R.id.favorite_button);
        }
    }
}

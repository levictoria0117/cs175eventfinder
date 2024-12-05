package edu.sjsu.android.localeventfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
        holder.eventImage.setImageResource(event.getImageID());
        holder.eventName.setText(event.getEventNameID());
        holder.eventLocation.setText(event.getLocationID());
        holder.eventDate.setText(event.getDateID());

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
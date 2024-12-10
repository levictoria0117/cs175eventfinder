package edu.sjsu.android.localeventfinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
                .inflate(R.layout.item_ticket,
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
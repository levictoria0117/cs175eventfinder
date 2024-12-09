package edu.sjsu.android.localeventfinder;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.sjsu.android.localeventfinder.databinding.FragmentTicketDetailBinding;

public class TicketDetailFragment extends Fragment {

    private Event event;


    public TicketDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Event ticket = getArguments().getParcelable(getString(R.string.argument_key));
            Log.d("TicketDetailFragment", "Received ticket: " + (ticket != null ? ticket.toString() : "null"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentTicketDetailBinding binding = FragmentTicketDetailBinding.inflate(inflater);
        binding.eventImageView.setImageResource(event.getImageID());
        binding.eventNameTextView.setText(event.getEventNameID());
        binding.dateTextView.setText(event.getDateID());
        return binding.getRoot();
    }
}
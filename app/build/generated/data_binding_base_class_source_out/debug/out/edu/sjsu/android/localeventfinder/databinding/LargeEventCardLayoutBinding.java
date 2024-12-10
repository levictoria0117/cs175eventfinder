// Generated by view binder compiler. Do not edit!
package edu.sjsu.android.localeventfinder.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import edu.sjsu.android.localeventfinder.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class LargeEventCardLayoutBinding implements ViewBinding {
  @NonNull
  private final MaterialCardView rootView;

  @NonNull
  public final TextView eventDate;

  @NonNull
  public final ImageView eventImage;

  @NonNull
  public final TextView eventLocation;

  @NonNull
  public final TextView eventName;

  @NonNull
  public final ImageButton favoriteButton;

  private LargeEventCardLayoutBinding(@NonNull MaterialCardView rootView,
      @NonNull TextView eventDate, @NonNull ImageView eventImage, @NonNull TextView eventLocation,
      @NonNull TextView eventName, @NonNull ImageButton favoriteButton) {
    this.rootView = rootView;
    this.eventDate = eventDate;
    this.eventImage = eventImage;
    this.eventLocation = eventLocation;
    this.eventName = eventName;
    this.favoriteButton = favoriteButton;
  }

  @Override
  @NonNull
  public MaterialCardView getRoot() {
    return rootView;
  }

  @NonNull
  public static LargeEventCardLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static LargeEventCardLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.large_event_card_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static LargeEventCardLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.event_date;
      TextView eventDate = ViewBindings.findChildViewById(rootView, id);
      if (eventDate == null) {
        break missingId;
      }

      id = R.id.event_image;
      ImageView eventImage = ViewBindings.findChildViewById(rootView, id);
      if (eventImage == null) {
        break missingId;
      }

      id = R.id.event_location;
      TextView eventLocation = ViewBindings.findChildViewById(rootView, id);
      if (eventLocation == null) {
        break missingId;
      }

      id = R.id.event_name;
      TextView eventName = ViewBindings.findChildViewById(rootView, id);
      if (eventName == null) {
        break missingId;
      }

      id = R.id.favorite_button;
      ImageButton favoriteButton = ViewBindings.findChildViewById(rootView, id);
      if (favoriteButton == null) {
        break missingId;
      }

      return new LargeEventCardLayoutBinding((MaterialCardView) rootView, eventDate, eventImage,
          eventLocation, eventName, favoriteButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
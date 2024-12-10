// Generated by view binder compiler. Do not edit!
package edu.sjsu.android.localeventfinder.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import edu.sjsu.android.localeventfinder.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityEventDetailBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView eventDate;

  @NonNull
  public final TextView eventDescription;

  @NonNull
  public final ImageView eventImage;

  @NonNull
  public final TextView eventLocation;

  @NonNull
  public final TextView eventName;

  private ActivityEventDetailBinding(@NonNull LinearLayout rootView, @NonNull TextView eventDate,
      @NonNull TextView eventDescription, @NonNull ImageView eventImage,
      @NonNull TextView eventLocation, @NonNull TextView eventName) {
    this.rootView = rootView;
    this.eventDate = eventDate;
    this.eventDescription = eventDescription;
    this.eventImage = eventImage;
    this.eventLocation = eventLocation;
    this.eventName = eventName;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityEventDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityEventDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_event_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityEventDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.event_date;
      TextView eventDate = ViewBindings.findChildViewById(rootView, id);
      if (eventDate == null) {
        break missingId;
      }

      id = R.id.event_description;
      TextView eventDescription = ViewBindings.findChildViewById(rootView, id);
      if (eventDescription == null) {
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

      return new ActivityEventDetailBinding((LinearLayout) rootView, eventDate, eventDescription,
          eventImage, eventLocation, eventName);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
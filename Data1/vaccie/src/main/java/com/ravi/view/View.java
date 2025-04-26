package com.ravi.view;

public interface View {
    // Common method to get view type/name
    String getViewType();

    // Common method to check if view is empty
    boolean isEmpty();

    // Common method to get view description
    String getDescription();

    // Common method to get timestamp of when the view was created
    Long getTimestamp();
}

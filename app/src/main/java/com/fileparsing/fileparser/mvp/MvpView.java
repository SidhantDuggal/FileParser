package com.fileparsing.fileparser.mvp;

/* Any class that wants to act as a View in the MVP pattern will implement this class.
    An interface can extend this interface and provide specific functionality before being implemented
    by an Activity or Fragment.
 */
public interface MvpView {

    // Stub method definition. Relevant and necessary sigantures can be added in this interface
    void showMessage(String message);

}

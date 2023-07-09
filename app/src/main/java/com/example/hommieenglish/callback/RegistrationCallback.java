package com.example.hommieenglish.callback;

import android.content.Context;

public interface RegistrationCallback {
    void onRegistrationComplete(Context ctx, String message, Boolean success);
}

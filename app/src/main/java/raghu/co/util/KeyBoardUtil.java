package raghu.co.util;

import android.content.Context;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

public class KeyBoardUtil {

    public static void hideKeyboard(AppCompatActivity activity) {
        InputMethodManager inputManager = (InputMethodManager)
                activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        IBinder currentFocus = (null == activity.getCurrentFocus()) ?
                null : activity.getCurrentFocus().getWindowToken();
        inputManager.hideSoftInputFromWindow(currentFocus, InputMethodManager.HIDE_NOT_ALWAYS);
    }
}

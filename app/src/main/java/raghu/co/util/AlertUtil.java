package raghu.co.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.widget.Toast;

import raghu.co.R;

public class AlertUtil {

    private static Toast toast = null;

    public static void displayError(Activity context, String errorMsg) {
        if (context == null || context.isFinishing()) {
            //no-op
            return;
        }

        if (errorMsg == null || errorMsg.isEmpty() || errorMsg.equalsIgnoreCase("unavailable")) {
            errorMsg = context.getString(R.string.generic_network_error);
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context)
                .setMessage(errorMsg)
                .setPositiveButton("Okay",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                // do nothing
                            }
                        });

        alertDialogBuilder.create().show();
    }

    public static void toastUser(Context context, String message) {
        if (message == null || message.isEmpty()) {
            return;
        }

        toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.show();
    }

    public static void dismissToast(Context context) {
        if (toast != null) {
            toast.cancel();
        }
    }
}

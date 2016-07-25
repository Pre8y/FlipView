package com.learncode.flipview.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Preeti on 25/07/16.
 */
public class Fliputils {

    private static ProgressDialog mProgress  ;

    public static void showProgressDialog(final Context ctx, String msg) {
        // if (mProgressHUD == null) {
        hideProgressDialog(ctx);
        mProgress = ProgressDialog.show(ctx, "loading", msg);
        // }
    }


    public static void hideProgressDialog(Context ctx) {
        try {
            if (mProgress != null && mProgress.isShowing()) {
                mProgress.dismiss();
                mProgress = null;
            }
        } catch (Exception e) {
            handleException(e);
        }
    }

    public static void handleException(Exception e)
    {
        e.printStackTrace();
    }

}

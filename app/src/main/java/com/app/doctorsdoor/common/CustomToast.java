package com.app.doctorsdoor.common;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.app.doctorsdoor.R;


/**
 * Created by PurleDocs on 26-11-2016.
 */
public class CustomToast {


    public static void SingleToast(Activity activity, String message) {

        Log.e("SingleToast",activity.getClass().getSimpleName());


        LayoutInflater li = activity.getLayoutInflater();
        //Getting the View object as defined in the customtoast.xml file
        View layout = li.inflate(R.layout.custom_tost,
                (ViewGroup) activity.findViewById(R.id.lin_view_toast));

        TextView txt_disp_toasst = (TextView) layout.findViewById(R.id.txt_disp_toasst);
        txt_disp_toasst.setText(message);

        //Creating the Toast object
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }


    public static void SingleToastLong(Activity activity, String message) {

        Log.e("SingleToastLong",activity.getClass().getSimpleName());
        LayoutInflater li = activity.getLayoutInflater();
        //Getting the View object as defined in the customtoast.xml file
        View layout = li.inflate(R.layout.custom_tost,
                (ViewGroup) activity.findViewById(R.id.lin_view_toast));

        TextView txt_disp_toasst = (TextView) layout.findViewById(R.id.txt_disp_toasst);
        txt_disp_toasst.setText(message);

        //Creating the Toast object
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_LONG);

        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }
    public static void SingleToastShortContext(Context activity, String message) {

        Log.e("SingleToastContext",activity.getClass().getSimpleName());
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE );

        //Getting the View object as defined in the customtoast.xml file
        View layout = inflater.inflate(R.layout.custom_tost, null);

        TextView txt_disp_toasst = (TextView) layout.findViewById(R.id.txt_disp_toasst);
        txt_disp_toasst.setText(message);

        //Creating the Toast object
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }
    public static void SingleToastLongContext(Context activity, String message) {

        Log.i("SingleToastContext",activity.getClass().getSimpleName());
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE );

        //Getting the View object as defined in the customtoast.xml file
        View layout = inflater.inflate(R.layout.custom_tost, null);

        TextView txt_disp_toasst = (TextView) layout.findViewById(R.id.txt_disp_toasst);
        txt_disp_toasst.setText(message);

        //Creating the Toast object
        Toast toast = new Toast(activity);
        toast.setDuration(Toast.LENGTH_LONG);

        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }
}

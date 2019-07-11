package com.app.doctorsdoor.web;

import com.app.doctorsdoor.web.model.PDResponse;

;

/**
 * Created by Pulah on 06-09-2018 10:56 AM.
 */
public interface WebResponsible {

    void onWebResponseReceived(int requestCode, int resultCode, PDResponse data);
}

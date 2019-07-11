package com.app.doctorsdoor.web;

import com.android.volley.Request;
import com.app.doctorsdoor.web.exception.PDException;
import com.app.doctorsdoor.web.model.PDWebRequest;


/**
 * Created by Pulah on 06-09-2018 9:55 AM.
 */
public class RequestManager {
    private static RequestManager requestManager;

    private VolleyRequester requester;

    private RequestManager() {
        requester = new VolleyRequester();
    }

    private synchronized static RequestManager getRequestManager() {
        if (requestManager == null) {
            requestManager = new RequestManager();
        }
        return requestManager;
    }


    private void jsonRequest(int method, PDWebRequest request, WebResponsible responsible) {
        ResponseHandler responseHandler = new ResponseHandler(request.getRequestCode(), responsible);
        PDJsonWebRequest jsonWebRequest = new PDJsonWebRequest(method, request, responseHandler);
        if (request.getRetryPolicy() != null) {
            jsonWebRequest.setRetryPolicy(request.getRetryPolicy());
        }
        requester.addToRequestQueue(jsonWebRequest);
    }


    private static void isValidRequest(PDWebRequest request, WebResponsible responsible,
                                       boolean isGetRequest) throws PDException {
        if (request == null) {
            throw new PDException("Null Pointer Exception: PDWebRequest can never be null.");
        }

        if (request.getUrl() == null) {
            throw new PDException("Null URL Found Exception");
        }

        if (request.getRequestCode() == 0) {
            throw new PDException("Request code Can't be zero");
        }

        if (isGetRequest && request.getJsonParams() != null) {
            throw new PDException("Get Request Can't have Params in json format");
        }

        if (responsible == null) {
            throw new PDException("Null Pointer Exception : No Responsible found Which leads to no Use of request.");
        }
    }

    public static void jsonGetRequest(PDWebRequest request,
                                      WebResponsible responsible) throws PDException {
        //if not valid request then it will throw an exception
        isValidRequest(request, responsible, true);
        RequestManager.getRequestManager().jsonRequest(Request.Method.GET, request, responsible);
    }

    public static void jsonPostRequest(PDWebRequest request,
                                       WebResponsible responsible) throws PDException {
        //if not valid request then it will throw an exception
        isValidRequest(request, responsible, false);
        RequestManager.getRequestManager().jsonRequest(Request.Method.POST, request, responsible);
    }

    public static void jsonPutRequest(PDWebRequest request,
                                      WebResponsible responsible) throws PDException {
        //if not valid request then it will throw an exception
        isValidRequest(request, responsible, false);
        RequestManager.getRequestManager().jsonRequest(Request.Method.PUT, request, responsible);
    }

    public static void jsonDeleteRequest(PDWebRequest request,
                                         WebResponsible responsible) throws PDException {
        //if not valid request then it will throw an exception
        isValidRequest(request, responsible, false);
        RequestManager.getRequestManager().jsonRequest(Request.Method.DELETE, request, responsible);
    }
}
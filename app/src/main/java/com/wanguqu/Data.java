package com.wanguqu;

/**
 * Created by sun on 2016/11/2.
 */

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by lhy on 16-5-24.
 */
public class Data {
    public static final String TAG = "liuhaiyong";
    public static final String DATA_JSON_SIMPLE = "{\"name\":\"lhy\",\"height\":\"177cm\",\"weight\":\"55kg\"}";
    public static final String DATA_JSON_ARRAY = "[{\"last\":\"PHICOMM\",\"now\":\"huiye\"},{\"school\":\"SHU\"}]";
    public static final String DATA_JSON_ARRAY_ = "[\"name\",\"height\",\"weight\"]";
    public static final String DATA_JSON_ARRAY_INNER = "{\"name\":\"lhy\",\"age\":26,\"resume\":[{\"last\":\"PHICOMM\",\"now\":\"huiye\"},{\"school\":\"SHU\"}]}";
    public static final String DATA_JSON_ARRAY_ARRAY_INNER = "[{\"last\":\"PHICOMM\",\"now\":\"huiye\"},{\"school\":[{\"gaozhong\":\"xinhai\"},{\"daxue\":\"SHU\"}]}]";

    public static JSONObject getJsonSimple() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name", "lhy");
            jsonObject.put("height", "177cm");
            jsonObject.put("weight", "55kg");
        } catch (JSONException e) {
            throw new RuntimeException("getJsonSimple JSONException:" + e);
        }
        Log.d(TAG, "getJsonSimple jsonObject:" + jsonObject.toString());
        Log.e(TAG, "getJsonSimple jsonObject:" + DATA_JSON_SIMPLE);
        return jsonObject;
    }

    public static JSONArray getJsonArray() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        try {
            jsonObject1.put("last", "PHICOMM");
            jsonObject1.put("now", "huiye");
            jsonObject2.put("school", "SHU");
            jsonArray.put(jsonObject1).put(jsonObject2);
        } catch (JSONException e) {
            throw new RuntimeException("getJsonArray JSONException:" + e);
        }
        Log.d(TAG, "getJsonArray JSONArray:" + jsonArray.toString());
        Log.e(TAG, "getJsonArray JSONArray:" + DATA_JSON_ARRAY);
        return jsonArray;
    }

    public static JSONObject getJsonArrayInner() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArrayInner = new JSONArray();
        try {
            jsonObject.put("name", "lhy");
            jsonObject.put("age", 26);

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("last", "PHICOMM0").put("now", "huiye");
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("school", "SHU");
            jsonArrayInner.put(jsonObject1).put(jsonObject2);
            jsonObject.put("resume", jsonArrayInner);
        } catch (JSONException e) {
            throw new RuntimeException("getJsonArrayInner JSONException:" + e);
        }
        Log.d(TAG, "getJsonArrayInner JSONObject:" + jsonObject.toString());
        Log.e(TAG, "getJsonArrayInner JSONObject:" + DATA_JSON_ARRAY_INNER);
        return jsonObject;
    }

    public static JSONArray getJsonArrayArrayInner() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        JSONArray jsonArrayInner = new JSONArray();
        JSONObject jsonObjectInner1 = new JSONObject();
        JSONObject jsonObjectInner2 = new JSONObject();

        try {
            jsonObject1.put("last", "PHICOMM").put("now", "huiye");
            jsonArray.put(jsonObject1);

            jsonObjectInner1.put("gaozhong", "xinhai");
            jsonArrayInner.put(jsonObjectInner1);
            jsonObjectInner2.put("daxue", "SHU");
            jsonArrayInner.put(jsonObjectInner2);
            jsonObject2.put("school", jsonArrayInner);
            jsonArray.put(jsonObject2);
        } catch (JSONException e) {
            throw new RuntimeException("getJsonArrayArrayInner JSONException:" + e);
        }
        Log.d(TAG, "getJsonArrayArrayInner JSONArray:" + jsonArray.toString());
        Log.e(TAG, "getJsonArrayArrayInner JSONArray:" + DATA_JSON_ARRAY_ARRAY_INNER);
        return jsonArray;
    }

}

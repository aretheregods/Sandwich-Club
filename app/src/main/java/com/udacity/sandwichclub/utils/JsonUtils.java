package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * Makes a sandwich object from supplied json
     * @param json
     * @return object
     */
    public static Sandwich parseSandwichJson(String json) {

        List<String> akas;
        List<String> ingredients;

        try {
            JSONObject sandwichData = new JSONObject(json);

            /* Get the string properties */
            JSONObject nameObject = sandwichData.getJSONObject("name");
            String name = nameObject.getString("mainName");
            String description = sandwichData.getString("description");
            String imageUrl = sandwichData.getString("image");
            String origin = sandwichData.optString("placeOfOrigin");

            /* Get the arrays alsoKnownAs and ingredients */
            JSONArray akasArray = nameObject.getJSONArray("alsoKnownAs");
            JSONArray ingredientsArray = sandwichData.getJSONArray("ingredients");

            akas = fillList(akasArray);
            ingredients = fillList(ingredientsArray);

            return new Sandwich(name, akas, origin, description, imageUrl, ingredients);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Makes a usable Java list from a JSONArray
     * @param array
     * @return list
     */
    private static List<String> fillList(JSONArray array) {
        List<String> someList = new ArrayList<>(0);
        int arrayLength = array.length();
        if(arrayLength != 0) {
            for (int i = 0; i < arrayLength; i++) {
                try {
                    someList.add(array.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return someList;
    }

}


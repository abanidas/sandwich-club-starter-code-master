package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        JSONObject jsonObject = null;
        Sandwich currentSandwich = new Sandwich();
        List<String> alsoKnownAsList = new ArrayList<>();
        List<String> ingredientList = new ArrayList<>();

        /*
        * Extracting details from JSON string and creating Sandwich object using those details
        */
        try {
            jsonObject = new JSONObject(json);
            JSONObject names = jsonObject.getJSONObject("name");
            currentSandwich.setMainName(names.getString("mainName"));
            JSONArray knownAsJArray = names.getJSONArray("alsoKnownAs");
            for (int i = 0; i < knownAsJArray.length(); i++){
                alsoKnownAsList.add(knownAsJArray.getString(i));
            }
            currentSandwich.setAlsoKnownAs(alsoKnownAsList);

            currentSandwich.setPlaceOfOrigin(jsonObject.getString("placeOfOrigin"));
            currentSandwich.setDescription(jsonObject.getString("description"));
            currentSandwich.setImage(jsonObject.getString("image"));

            JSONArray ingredientJArray = jsonObject.getJSONArray("ingredients");
            for (int i = 0; i < ingredientJArray.length(); i++){
                ingredientList.add(ingredientJArray.getString(i));
            }
            currentSandwich.setIngredients(ingredientList);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return currentSandwich;
    }
}

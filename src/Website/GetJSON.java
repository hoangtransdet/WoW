package Website; /**
 * This file contains the method for acquiring a JSON file regarding the auction data of World of Warcraft online
 * game produced by Blizzard, Inc.
 *
 * @author  Nathan, Hoang, Min
 * Date     20 Feb 2016
 */

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * GetJSON
 */
public class GetJSON {

    /**
     * The method for acquiring the data.
     */
    public static void getJSON() {

        // local variables
        URL webPage = null;
        String uriAddress = "https://us.api.battle.net/wow/auction/data/medivh?locale=en_US&apikey=kysd7pvzpy6dwsc62qntbdwx24h36gjd";
        String jsonFile = "";
        String fileDate = "";

        try {

            // assign the address to URL object
            webPage = new URL(uriAddress);

            try {

                // request the dump
                webPage.openConnection().connect();

                // process the URI object in acquiring the actual JSON file of auction house data
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(webPage.openStream(), "UTF-8"))) {

                    for (String line; (line = reader.readLine()) != null; ) {

                        try {

                            // create a JSON Object of URI
                            JSONObject address = new JSONObject(line);

                            // acquire the actual address
                            jsonFile = address.getJSONArray("files").getJSONObject(0).getString("url");
                            System.out.println("Acquiring the data from: " + jsonFile);

                            // acquire the date of the dump
                            fileDate = address.getJSONArray("files").getJSONObject(0).get("lastModified").toString();
                            System.out.println("   The date of the dump: " + fileDate);

                            // process the given address
                            ProcessJSON.processJSONFromURL(jsonFile);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

}


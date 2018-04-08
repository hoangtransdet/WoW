package Website; /**
 * This file contains the methods for processing JSON objects.
 *
 * @author  Nathan, Hoang, Min
 * Date     20 Feb 2016
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * JSONUtils
 */
public class JSONUtils {

    /**
     * The method for acquiring collection of a given JSON object
     * @param jsonObject
     * @return auctions
     */
    public static Collection<Auction> parseJSONFromURL(JSONObject jsonObject) {

        // local variables
        Collection<Auction> auctions = new ArrayList<Auction>();
        JSONObject tempJSONObject;
        ProcessJSON.processJSONFromFile();

        // current Timestamp
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new java.sql.Date(calendar.getTime().getTime());
        Timestamp currentTime = new Timestamp(currentDate.getTime());

        try {

            JSONArray jsonArray = (JSONArray) jsonObject.get("auctions");

            for (int i = 0; i < jsonArray.length(); i++) {

                try {

                    Auction auction = new Auction();
                    tempJSONObject = (JSONObject) jsonArray.get(i);

                    auction.setId();
                    auction.setActionNumber(tempJSONObject.getInt("auc"));
                    auction.setItem(tempJSONObject.getInt("item"));
                    auction.setOwner(tempJSONObject.getString("owner"));
                    auction.setOwnerRealm(tempJSONObject.getString("ownerRealm"));
                    auction.setBid(tempJSONObject.getInt("bid"));
                    auction.setBuyout(tempJSONObject.getInt("buyout"));
                    auction.setQuantity(tempJSONObject.getInt("quantity"));
                    auction.setTimeLeft(tempJSONObject.getString("timeLeft"));
                    auction.setDumpDate(new Date(currentTime.getTime()));
                    auction.setDumpTime(new Time(currentTime.getTime()));
                    String itemName = ProcessJSON.getItemName(auction.getItem());

                    // there is a new item in the auction dump but it doesn't exist in our item database
                    if(itemName != null) {
                        auction.setItemName(itemName);
                    }
                    else {
                        auction.setItemName("Please add me");
                    }

                    auctions.add(auction);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return auctions;
    }

    /**
     * The method for acquiring collection of item id and name from a given JSON object.
     *
     * @param jsonObject
     * @return
     */
    public static void parseJSONFromFile(JSONObject jsonObject) {

        // local variables
        JSONObject tempJSONObject;

        try {

            JSONArray jsonArray = (JSONArray) jsonObject.get("items");

            for (int i = 0; i < jsonArray.length(); i++) {

                try {

                    Item item = new Item();

                    tempJSONObject = (JSONObject) jsonArray.get(i);
                    item.setId(tempJSONObject.getInt("id"));
                    item.setName(tempJSONObject.getString("name"));

                    // the item is already in the item database
                    if(!ProcessJSON.isItemExist(item)) {
                        ProcessJSON.addItem(item);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method to create a JSON object which include the given array data.
     *
     * @param auctions
     * @return obj
     */
    public static JSONObject objectFromArray(List<Auction> auctions) {

        JSONObject obj = new JSONObject();
        JSONArray array  = new JSONArray();

        for(Auction a : auctions) {

            try {

                obj.put("ID", a.getId());
                obj.put("ACTION_NUMBER", a.getActionNumber());
                obj.put("ITEM", a.getItem());
                obj.put("ITEM_NAME", a.getItemName());
                obj.put("OWNER", a.getOwner());
                obj.put("OWNER_REALM", a.getOwnerRealm());
                obj.put("BID", a.getBid());
                obj.put("BUYOUT", a.getBuyout());
                obj.put("QUANTITY", a.getQuantity());
                obj.put("TIME_LEFT", a.getTimeLeft());
                obj.put("DUMP_DATE", a.getDumpDate());
                obj.put("DUMP_TIME", a.getDumpTime());
                obj.put("SOLD", a.isSold());
                array.put(obj);

                // reset obj
                obj = new JSONObject();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject json = new JSONObject();

        try {

            json.put("Session", array);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }

}

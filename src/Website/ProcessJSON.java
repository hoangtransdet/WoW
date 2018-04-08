package Website; /**
 * This file contains the method for processing the actual JSON object which contains the auction house data.
 *
 * @author  Nathan, Hoang, Min
 * Date     20 Feb 2016
 */

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;

/**
 * ProcessJSON
 */
public class ProcessJSON {

    private static Collection<Item> items = new ArrayList<Item>();

    /**
     * The method to get item's name by ID.
     *
     * @param item
     * @return i.getName() or null
     */
    public static String getItemName(int item) {
        for(Item i: items) {
            if(i.getId() == item) {
                return i.getName();
            }
        }
        return null;
    }

    /**
     * The method to check if the item already exist in the list.
     * @param item
     * @return
     */
    public static boolean isItemExist(Item item) {
        for(Item i: items) {
            if(i.getId() == item.getId()) {
                return true;
            }
        }
        return false;
    }

    public static void addItem(Item myItem) {
        items.add(myItem);
    }

    /**
     * The method for acquiring the JSON object of auction data.
     * @param url
     */
    public static void processJSONFromURL(String url) {

        // local variables
        String fileName = "MedivhAuction.json";

        System.out.println("             Processing: " + fileName);

        try {

            StringBuffer stringBuffer = new StringBuffer();
            URL oracle = new URL(url);
            String inputLine = "";
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));

            // process the file
            while ((inputLine = in.readLine()) != null) {
                stringBuffer.append(inputLine);
            }
            in.close();

            JSONObject jsonObject = new JSONObject(stringBuffer.toString());
            Collection<Auction> auctions = JSONUtils.parseJSONFromURL(jsonObject);
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            StatelessSession session = sessionFactory.openStatelessSession();
            Transaction tran = session.beginTransaction();

            for (Auction auction : auctions) {
                session.insert(auction);
            }

            tran.commit();
            session.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("   Processing completed: " + fileName);
    }

    /**
     * The method to create Item database.
     */
    public static void processJSONFromFile() {

        // local variables
        items = new ArrayList<Item>();
        String fileName = "WoWItems.json";

        System.out.println("             Processing: " + fileName);

        try {

            StringBuffer itemBuffer = new StringBuffer();
            String input = "";

            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));

            while ((input = in.readLine()) != null) {
                itemBuffer.append(input);
            }
            in.close();

            JSONObject itemsList = new JSONObject(itemBuffer.toString());
            JSONUtils.parseJSONFromFile(itemsList);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("   Processing completed: " + fileName);
    }

}

package Website;

import java.util.List;

/**
 * This file contains the method for testing the retrieval from the database.
 *
 * @author  Nathan, Hoang, Min
 * Date     20 Feb 2016
 */

public class TestDatabase {

    public static void main(String[] arg) {

        System.out.println("Search by Date");
        List<Auction> auctions = Website.HibernateUtils.retrieveByDate(null, null);
        auctions = Website.HibernateUtils.sortByItemNameFromTheList("Gold Bar", auctions);
        int counter = 0;
        for(Auction a: auctions) {
            String buffer = String.format("%d ItemID: %-12d\tItemName: %-50s\tQuantity: %-2d\tTime: %-15s\tDate: %s",
                    counter, a.getItem(), a.getItemName(), a.getQuantity(), a.getDumpTime(), a.getDumpDate());
            System.out.println(buffer);
            counter++;
        }

        System.out.println();
        auctions = Website.HibernateUtils.getTotal(auctions);
        counter = 0;
        for(Auction a: auctions) {
            String buffer = String.format("%d ItemID: %-12d\tItemName: %-50s\tQuantity: %-2d\tTime: %-15s\tDate: %s",
                    counter, a.getItem(), a.getItemName(), a.getQuantity(), a.getDumpTime(), a.getDumpDate());
            System.out.println(buffer);
            counter++;
        }


    }
}
//test
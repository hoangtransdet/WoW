package Website;
/**
 * This file contains the methods for processing JSON objects.
 *
 * @author  Nathan, Hoang, Min
 * Date     20 Feb 2016
 */

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * HibernateUtils
 */
public class HibernateUtils {

    /**
     * The method for retrieving the data with a given string.
     *
     * @param myItem
     * @return auctions
     */
    public static List<Auction> retrieveByName(String myItem) {

        List<Auction> auctions = new ArrayList<Auction>();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        StatelessSession session = sessionFactory.openStatelessSession();
        Transaction tran = session.beginTransaction();

        Criteria cr = session.createCriteria(Auction.class);
        /**
         * ANYWHERE Match the pattern anywhere in the string
         * START    Match the start of the string to the pattern
         * END      Match the end of the string to the pattern
         * EXACT    Match the entire string to the pattern
         */
        cr.add(Restrictions.like("itemName", myItem, MatchMode.ANYWHERE));
        auctions = cr.list();

        tran.commit();
        session.close();

        return auctions;
    }

    /**
     * The method for retrieving the item/items with a given bid.
     *
     * @param minBid
     * @parm maxBid
     * @return auctions
     */
    public static List<Auction> retrieveByBid(int minBid, int maxBid) {

        List<Auction> auctions = new ArrayList<Auction>();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        StatelessSession session = sessionFactory.openStatelessSession();
        Transaction tran = session.beginTransaction();

        Criteria cr = session.createCriteria(Auction.class);
        cr.add(Restrictions.between("bid", minBid, maxBid));

        auctions = cr.list();

        tran.commit();
        session.close();

        return auctions;
    }

    /**
     * The method for retrieving the item/items with a given bid.
     *
     * @parm start
     * @parm end
     * @return auctions
     */
    public static List<Auction> retrieveByDate(Date start, Date end) {

        List<Auction> auctions = new ArrayList<Auction>();
        Calendar calendar = Calendar.getInstance();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        StatelessSession session = sessionFactory.openStatelessSession();
        Transaction tran = session.beginTransaction();

        if(start == null) {
            // start date is set to 48 hours ago from the current time
            start = new java.sql.Date(calendar.getTime().getTime() - (28 * (1000 * 60 * 60 * 24)));
        }

        if(end == null) {
            end = new java.sql.Date(calendar.getTime().getTime());
        }

        Criteria cr = session.createCriteria(Auction.class);
        cr.add(Restrictions.between("date", start, end));
        auctions = cr.list();

        tran.commit();
        session.close();

        return auctions;
    }

    /**
     * The method for retrieving the item/items with a given buy out price.
     *
     * @param minBuyOut
     * @param maxBuyOut
     * @return auctions
     */
    public static List<Auction> retrieveByBuyOut(int minBuyOut, int maxBuyOut) {

        List<Auction> auctions = new ArrayList<Auction>();
        Calendar calendar = Calendar.getInstance();

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        StatelessSession session = sessionFactory.openStatelessSession();
        Transaction tran = session.beginTransaction();

        Criteria cr = session.createCriteria(Auction.class);
        cr.add(Restrictions.between("buyout", minBuyOut, maxBuyOut));

        auctions = cr.list();

        tran.commit();
        session.close();

        return auctions;
    }

    /**
     * The method for retrieving the item/items with a given buy out price.
     *
     * @param date
     * @return auctions
     */
    public static List<Auction> retrieveByBuyTime(Date date) {

        List<Auction> auctions = new ArrayList<Auction>();
        Calendar calendar = Calendar.getInstance();
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        StatelessSession session = sessionFactory.openStatelessSession();
        Transaction tran = session.beginTransaction();

        if(date == null) {
            date = new java.sql.Date(calendar.getTime().getTime());
        }

        Criteria cr = session.createCriteria(Auction.class);
        cr.add(Restrictions.eq("time", date.getTime()));

        auctions = cr.list();

        tran.commit();
        session.close();

        return auctions;
    }

    /**
     * The method to search the item of the auctions based on AuctionID.
     *
     * @param auction
     * @param id
     * @return
     */
    public static boolean auctionExistByAuctionID(List<Auction> auction, int id) {
        boolean exist = false;

        for(Auction a:auction) {
            if(a.getActionNumber() == id) {
                exist = true;
            }
        }
        return exist;
    }

    public static Auction getItemByDate(List<Auction> auction, java.sql.Date date) {

        int counter = 0;
        Auction item = new Auction();

        for(Auction a: auction) {

            if(a.getDumpDate().equals(date)) {

                counter = counter + a.getQuantity();
                item.setItemName(a.getItemName());
                item.setItem(a.getItem());
                item.setDumpDate(a.getDumpDate());
                item.setDumpTime(a.getDumpTime());
            }

        }

        item.setQuantity(counter);

        return item;
    }

    /**
     * The method for adding items with unique AuctionID to the bucket.
     *
     * @param auction
     * @return
     */
    public static List<Auction> sortItemsByAuctionID(List<Auction> auction) {
        List<Auction> bucket = new ArrayList<Auction>();

        for(Auction a:auction) {
            if(!auctionExistByAuctionID(bucket, a.getActionNumber())) {
                bucket.add(a);
            }
        }

        return bucket;
    }

    /**
     * The method for getting the result of an Item.
     * @param auction
     * @return
     */
    public static List<Auction> getTotal(List<Auction> auction) {

        List<Auction> bucket = new ArrayList<Auction>();

        java.sql.Date current = auction.get(auction.size() - 1).getDumpDate();

        for(int i = 7; i > 1; i--) {

            Auction temp = getItemByDate(auction, current);

            if(temp.getQuantity() > 0) {
                bucket.add(temp);
            }

            current = new java.sql.Date(current.getTime() - (1000 * 60 * 60 * 24));

        }

        return bucket;

    }

    // auctions.get(index) ; it will co

//    String itemName = request.getParameter("itemName");
//    Map<String, String> us = new LinkedHashMap<String, String>();
//    us.put("Mon Feb 22 2016 00:00:00 GMT-0600 (Central Standard Time)", "420");
//    us.put("Tue Feb 23 2016 00:00:00 GMT-0600 (Central Standard Time)", "1300");
//    us.put("Wen Feb 24 2016 00:00:00 GMT-0600 (Central Standard Time)", "400");
//    us.put("Thu Feb 25 2016 00:00:00 GMT-0600 (Central Standard Time)", "591");


    /**
     * The method for sorting from item array list.
     *
     * @param name
     * @param auction
     * @return
     */
    public static List<Auction> sortByItemNameFromTheList(String name, List<Auction> auction) {
        List<Auction> bucket = new ArrayList<Auction>();
        for(Auction a:auction) {
            if(a.getItemName().equals(name)) {
                bucket.add(a);
            }
        }
        return bucket;
    }

}

package Website;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Min on 4/16/2016.
 */
@WebServlet(name = "WoWItemServlet", urlPatterns = "/WoWItemServlet")
public class WoWItemServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String itemName = request.getParameter("itemName");
        Map<String, String> us = new LinkedHashMap<String, String>();

        int counter;
        System.out.println("Search by Date");
        List<Auction> auctions = Website.HibernateUtils.retrieveByName(itemName);
        // auctions = Website.HibernateUtils.sortByItemNameFromTheList(itemName, auctions);
        auctions = Website.HibernateUtils.getTotal(auctions);
        counter = 0;
        for(Auction a: auctions) {
            String buffer = String.format("%d ItemID: %-12d\tItemName: %-50s\tQuantity: %-2d\tTime: %-15s\tDate: %s",
                    counter, a.getItem(), a.getItemName(), a.getQuantity(), a.getDumpTime(), a.getDumpDate());
            System.out.println(buffer);
            counter++;
        }

        counter = 12;

        for(Auction a: auctions){
            us.put("Tue Apr "+counter+" 2016 00:00:00 GMT-0600 (Central Standard Time)", "" + a.getQuantity());

            counter++;
        }

//        us.put("Fri Feb 26 2016 00:00:00 GMT-0600 (Central Standard Time)", "1200");
//        us.put("Sat Feb 27 2016 00:00:00 GMT-0600 (Central Standard Time)", "130");
//        us.put("Sun Feb 28 2016 00:00:00 GMT-0600 (Central Standard Time)", "444");

        String json = null ;

        json=new Gson().toJson(us);


        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}

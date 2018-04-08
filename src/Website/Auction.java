package Website;

/**
 * Created by Nathan on 2/5/2016.
 */

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity(name = "Auction")
public class Auction {

    // the UID for wowdb
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID")
    private int id;

    @Column(name = "ACTION_NUMBER")
    private int actionNumber;

    @Column(name = "ITEM")
    private int item;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "OWNER_REALM")
    private String ownerRealm;

    @Column(name = "BID")
    private int bid;

    @Column(name = "BUYOUT")
    private int buyout;

    @Column(name = "QUANTITY")
    private int quantity;

    @Column(name = "TIME_LEFT")
    private String timeLeft;

    @Column(name = "DUMP_DATE")
    private Date date;

    @Column(name = "DUMP_TIME")
    private Time time;

    @Column(name = "SOLD")
    private boolean isSold;

    public int getId() {
        return id;
    }

    public void setId() {
    }

    public int getActionNumber() {
        return actionNumber;
    }

    public void setActionNumber(int actionNumber) {
        this.actionNumber = actionNumber;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerRealm() {
        return ownerRealm;
    }

    public void setOwnerRealm(String ownerRealm) {
        this.ownerRealm = ownerRealm;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getBuyout() {
        return buyout;
    }

    public void setBuyout(int buyout) {
        this.buyout = buyout;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Date getDumpDate() {
        return date;
    }

    public void setDumpDate(Date date) {
        this.date = date;
    }

    public Time getDumpTime() {
        return time;
    }

    public void setDumpTime(Time time) {
        this.time = time;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }
}

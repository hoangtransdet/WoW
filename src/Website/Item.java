package Website; /**
 * This file contains the class structure for Item's identification number and associated name.
 *
 * @author  Nathan, Hoang, Min
 * Date     20 Feb 2016
 */

import javax.persistence.*;

@Entity(name = "Items")
public class Item {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

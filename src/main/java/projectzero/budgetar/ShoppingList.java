package projectzero.budgetar;

import javax.xml.transform.sax.SAXSource;
import java.util.HashMap;

public class ShoppingList extends GetData {
    HashMap cogsList = cogs;
    HashMap nvList = nv;

    public ShoppingList() {
        //Object[] xList = new Object[];
        System.out.printf(String.valueOf(cogsList), String.valueOf(nvList));
    }
}

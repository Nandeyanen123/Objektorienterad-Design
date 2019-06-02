package se.kth.iv1201.retail.integration.matching;

import se.kth.iv1201.retail.integration.ItemDTO;
import se.kth.iv1201.retail.integration.ItemInventory;

public interface Matcher {

    ItemDTO match(int searched, ItemInventory available);
}

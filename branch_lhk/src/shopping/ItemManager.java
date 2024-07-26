package shopping;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

	private List<Item> itemList = new ArrayList<Item>();
	private int nextItemNumber = 1;
	
	public List<Item> getItemList() {
		return itemList;
	}
	
	public void addItem(Item item) {
		itemList.add(item);
		nextItemNumber++;
	}
	
	public void removeItem(Item item) {
		itemList.remove(item);
	}
	
	public Item getItemNumber(int itemNumber) {
		for(Item item : itemList) {
			if(item.getItemNumber() == itemNumber) {
				return item;
			}
		}
		return null;
	}
	
	public int getNextItemNumber() {
		return nextItemNumber;
	}
}

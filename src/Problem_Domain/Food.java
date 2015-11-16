package Problem_Domain;

import java.util.Calendar;

public class Food
{
	private String name;
	private int quantity;
	private int weight;
	private int calories;
	private boolean isFreezerItem;
	private Essential.Pair<Integer, Integer> location;
	private Calendar expirationDate;

	public Food
		(String newName, int newQuantity, int newWeight, int newCalories, boolean bFreezer, int row, int col, Calendar newExpirationDate)
	{
		name = newName;
		quantity = newQuantity;
		weight = newWeight;
		calories = newCalories;
		isFreezerItem = bFreezer;
		location = new Essential.Pair<Integer, Integer>(row, col);
		expirationDate = newExpirationDate;
	}
	
	public boolean isProhibited() { return RefrigeratorSystem.getProhibitedList().indexOf(name, 0) == -1; }
	public boolean isExpired() { return Calendar.getInstance().before(expirationDate); }
	public int getQuantity() { return quantity; }
	public int getWeight() { return weight; }
	public int getCalories() { return calories; }
	public boolean isFreezerItem() { return isFreezerItem; }
	public Essential.Pair<Integer, Integer> getLocation() { return location; }
	
	public WarningMessage createWarningMessage()
	{
		if(isProhibited()) return new WarningMessage("Prohibited item : " + name);
		if(isExpired())
		{
			StringBuffer buf = new StringBuffer();
			buf.append("Item expired : " + name + ", location : ");
			if(isFreezerItem) buf.append("freezer, ");
			else buf.append("cooler, ");
			buf.append("row " + location.first + ", column " + location.second);
			return new WarningMessage(buf.toString());
		}
		return null;
	}
}

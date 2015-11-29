package Problem_Domain;

import java.util.Calendar;

public class Food {
	private String name;
	private int quantity;
	private int weight;
	private int calories;
	private boolean freezeType;
	private String floor;
	private Calendar expirationDate;
	private Calendar insertedDate;

	/**
	 * @param newName
	 *            ���� �̸�
	 * @param newQuantity
	 *            ���� ����
	 * @param newWeight
	 *            ���� ����
	 * @param newCalories
	 *            ������ Į�θ�
	 * @param type
	 *            �õ����̸� true, ������̸� false
	 * @param location
	 *            ��ġ, ����� �� ��°ĭ
	 * @param newExpirationDate
	 *            �������
	 */
	public Food(String newName, int newQuantity, int newWeight,
			int newCalories, boolean type, String location,
			Calendar newExpirationDate) {
		name = newName;
		quantity = newQuantity;
		weight = newWeight;
		calories = newCalories;
		freezeType = type;
		floor = location;
		expirationDate = newExpirationDate;
		insertedDate = Calendar.getInstance();
	}

	public boolean isProhibited() {
		return RefrigeratorSystem.getFoodList().getProhibitedList()
				.indexOf(name) != -1;
	}

	public boolean isExpired() {
		return Calendar.getInstance().before(expirationDate);
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getWeight() {
		return weight;
	}

	public int getCalories() {
		return calories;
	}

	public boolean getFreezeType() {
		return freezeType;
	}

	public String getFloor() {
		return floor;
	}

	public Calendar getExpirationDate() {
		return expirationDate;
	}

	public Calendar getinsertedDate() {
		return insertedDate;
	}

	public void setQuantity(int newQuantity) {
		quantity = newQuantity;
	}

	public void setWeight(int newWeight) {
		weight = newWeight;
	}

	public void setCalories(int newCalories) {
		calories = newCalories;
	}

	public void setFreezeType(boolean newFreezeType) {
		freezeType = newFreezeType;
	}

	public void setFloor(String newFloor) {
		floor = newFloor;
	}

	public String toString() {
		return name + " : Quantity -> " + quantity + ", weight -> " + weight
				+ "g, calories -> " + calories + " each, Location"
				+ (freezeType ? "Freezer " : "Cooler") + ", floor " + floor
				+ "Inserted at " + insertedDate + ", Expiration Date -> "
				+ expirationDate;
	}
}

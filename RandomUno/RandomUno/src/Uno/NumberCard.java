package Uno;

public class NumberCard extends ColorCard {

	private int number;
	
	public NumberCard(String color, int number) {
		super(color);
		if(0<=number && number<10) {
			this.number = number;
		}
		
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public void viewCard() {
		System.out.println(this.color + this.number);
	}

}

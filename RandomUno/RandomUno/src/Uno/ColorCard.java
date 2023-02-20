package Uno;

public abstract class ColorCard {
	protected String color;
	
	public ColorCard(String color) {
		if(color.equals("Red") || color.equals("Yellow") || color.equals("Blue") || color.equals("Green")) {
			this.color = color;
		}
	
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public void viewCard() {
		System.out.println(this.color);
	}
}

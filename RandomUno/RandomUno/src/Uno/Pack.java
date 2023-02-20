package Uno;

import java.util.ArrayList;

public class Pack implements Bank {

	private ArrayList<ColorCard> packCards;

	public ArrayList<ColorCard> getPackCards() {
		return packCards;
	}

	public void setDeckCards(ArrayList<ColorCard> packCards) {
		this.packCards = packCards;
	}
	
	//constructor with input of packCards
	public Pack(ArrayList<ColorCard> packCards) {
		this.packCards = packCards;
	}
	//constructor with no input, to create a empty pack
	public Pack() {
		this.packCards = new ArrayList<ColorCard>();
	}
	
	@Override
	public void addCard(ColorCard z) {
		packCards.add(z);

	}

	@Override
	public void removeCard(int z) {
		packCards.remove(z);

	}
	
	public int positionLastPackCard() {
		return packCards.size()-1;
	}
	
	public ColorCard returnLastColorCard() {
		return packCards.get(positionLastPackCard());
	}
	
	public void viewVisual() {
		System.out.println("Card in the pack:");
		System.out.println("-------");
		
		System.out.println("|  " + returnLastColorCard().getColor().charAt(0)+ "  |");
		
		if(returnLastColorCard() instanceof BlockCard) {
			BlockCard a = (BlockCard) returnLastColorCard();
			if(a.getNumberBlock()==0) {
				System.out.println("|     |");
			}else {
				System.out.println("|  +  |");
			}
		}else {
			System.out.println("|     |");
		}
		
		if(returnLastColorCard() instanceof BlockCard ) {
			BlockCard a = (BlockCard) returnLastColorCard();
			if(a.getNumberBlock()==0) {
				System.out.println("|  X  |   ");
			}else {
				System.out.println("|  " + a.getNumberBlock() + "  |   ");
			}
		}else {
			NumberCard a = (NumberCard) returnLastColorCard();
			System.out.println("|  " + a.getNumber() + "  |   ");
		}
		
		System.out.println("-------");
		
		
	}
		
	

}

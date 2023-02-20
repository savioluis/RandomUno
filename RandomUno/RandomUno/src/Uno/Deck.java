package Uno;

import java.util.ArrayList;

public class Deck implements Bank {
	private ArrayList<ColorCard> deckCards;

	public ArrayList<ColorCard> getDeckCards() {
		return deckCards;
	}

	public void setDeckCards(ArrayList<ColorCard> deckCards) {
		this.deckCards = deckCards;
	}

	public Deck(ArrayList<ColorCard> deckCards) {
		super();
		this.deckCards = deckCards;
	}

	@Override
	public void addCard(ColorCard z) {
		deckCards.add(z);
	}

	@Override
	public void removeCard(int z) {
		deckCards.remove(z);
		
	}
	
	public void addArrayCard(ArrayList<ColorCard> a) {
		for(int i=0;i<a.size();i++) {
			this.addCard(a.get(i));
		}
	}
	
	public void addDeckinDeck(Deck deck1) {
		for(int i=0;i<deck1.getDeckCards().size();i++) {
			this.addCard(deck1.getDeckCards().get(i));
		}
	}
	
	@Override
	public void viewVisual() {
		/*System.out.println("-------");
		System.out.println("|     |");
		System.out.println("|  " + deckCards.size() + "  |");
		System.out.println("|     |");
		System.out.println("-------");
		*/
		System.out.println("Q.Cards in the deck: " + deckCards.size());
	}
	
	
	
	
}

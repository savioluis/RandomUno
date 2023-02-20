package Uno;

import java.util.ArrayList;

public class Player implements Bank {
	private String name;
	private ArrayList<ColorCard> playerCards;

	public ArrayList<ColorCard> getPlayerCards() {
		return playerCards;
	}

	public void setPlayerCards(ArrayList<ColorCard> playerCards) {
		this.playerCards = playerCards;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//constructor of player with hand
	public Player(ArrayList<ColorCard> playerCards) {
		super();
		this.playerCards = playerCards;
	}
	
	public Player(String name) {
		this.name=name;
		this.playerCards= new ArrayList<ColorCard>();
	}
	
	
	//constructor of player with name and hand 
	public Player(String name, ArrayList<ColorCard> playerCards) {
		super();
		this.name = name;
		this.playerCards = playerCards;
	}

	@Override
	public void addCard(ColorCard z) {
		playerCards.add(z);
		
	}

	@Override
	public void removeCard(int z) {
		playerCards.remove(z);
		
	}
	
	public void viewVisual() {
		System.out.println("|" +this.name + " have " + playerCards.size() + " card(s) in the hand|" );
	}
	
	
	
	public void viewVisualHand() {
		System.out.println(this.name +"'s" + " hand:");
		for(int i=0;playerCards.size()>i;i++) {
			System.out.print("-------   ");
		}
		System.out.print("\n");
		
		for(int i=0;playerCards.size()>i;i++) {
			System.out.print("|  " + playerCards.get(i).getColor().charAt(0) + "  |   ");
		}
		System.out.print("\n");
		
		for(int i=0;playerCards.size()>i;i++) {
			if(playerCards.get(i) instanceof BlockCard ) {
				BlockCard a = (BlockCard) playerCards.get(i);
				if(a.getNumberBlock()==0) {
					System.out.print("|     |   ");
				}else {
					System.out.print("|  +  |   ");
				}
			}else {
				System.out.print("|     |   ");
			}
		}
		System.out.print("\n");
		
		for(int i=0;playerCards.size()>i;i++) {
			if(playerCards.get(i) instanceof BlockCard ) {
				BlockCard a = (BlockCard) playerCards.get(i);
				if(a.getNumberBlock()==0) {
					System.out.print("|  X  |   ");
				}else {
					System.out.print("|  " + a.getNumberBlock() + "  |   ");
				}
			}else {
				NumberCard a = (NumberCard) playerCards.get(i);
				System.out.print("|  " + a.getNumber() + "  |   ");
			}
		}
		System.out.print("\n");
		
		for(int i=0;playerCards.size()>i;i++) {
			System.out.print("-------   ");
		}
		System.out.print("\n");
		
	}
	
	
}

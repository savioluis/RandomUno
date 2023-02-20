import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Uno.*;
import java.util.Random;
import java.util.Scanner;


public class Main {
	
	//return a new complete deck of UNO!
	public static Deck returnNewFullDeck() {
		ArrayList<ColorCard> arrayFullDeck = new ArrayList<ColorCard>();
		
		ArrayList<NumberCard> arrayGreenCard = new ArrayList<NumberCard>();
		ArrayList<NumberCard> arrayRedCard = new ArrayList<NumberCard>();
		ArrayList<NumberCard> arrayBlueCard = new ArrayList<NumberCard>();
		ArrayList<NumberCard> arrayYellowCard = new ArrayList<NumberCard>();
		ArrayList<BlockCard> arrayBlockCard = new ArrayList<BlockCard>();
		
		//loop create numbered and colored cards(0,1,2,3,4,5,6,7,8,9)
		for(int i=0;i<2;i++) {
			for(int j=0;j<10;j++) {
				arrayGreenCard.add(new NumberCard("Green",j));
				arrayRedCard.add(new NumberCard("Red",j));
				arrayBlueCard.add(new NumberCard("Blue",j));
				arrayYellowCard.add(new NumberCard("Yellow",j));
			}
		}
		//loop create each color of a block card
		for(int i=0;i<2;i++) {
			arrayBlockCard.add(new BlockCard("Green",0));
			arrayBlockCard.add(new BlockCard("Red",0));
			arrayBlockCard.add(new BlockCard("Blue",0));
			arrayBlockCard.add(new BlockCard("Yellow",0));
		}
		//loop create each color of a +2 card
		for(int i=0;i<2;i++) {
			arrayBlockCard.add(new BlockCard("Green",2));
			arrayBlockCard.add(new BlockCard("Red",2));
			arrayBlockCard.add(new BlockCard("Blue",2));
			arrayBlockCard.add(new BlockCard("Yellow",2));
		}
		//add all cards in a array of ColorCards
		for(int i=0;i<20;i++) {
			arrayFullDeck.add(arrayGreenCard.get(i));
			arrayFullDeck.add(arrayRedCard.get(i));
			arrayFullDeck.add(arrayBlueCard.get(i));
			arrayFullDeck.add(arrayYellowCard.get(i));
			if(i<16) {
				arrayFullDeck.add(arrayBlockCard.get(i));
			}
		}
		
		Deck fullDeck = new Deck(arrayFullDeck);
		return fullDeck;
	}
	
	
	//draw a card from the deck for a player
	public static void drawRandomCard(Deck d1,Pack pack,Player p1) {
		
		if(d1.getDeckCards().size()>0) {
			Random random1 = new Random();
			int randomPositionDeckCard = random1.nextInt(d1.getDeckCards().size());
		
			p1.addCard(d1.getDeckCards().get(randomPositionDeckCard));
			d1.removeCard(randomPositionDeckCard);
		}
		
	}
	
	public static void firstPackCard(Deck d1, Pack p1) {
		Random random2 = new Random();
		if(p1.getPackCards().size()==0 && d1.getDeckCards().size()>0){
			int randomPositionDeckCard = random2.nextInt(d1.getDeckCards().size());
			p1.addCard(d1.getDeckCards().get(randomPositionDeckCard));
			d1.removeCard(randomPositionDeckCard);
		}else {
			System.out.println("Erro in draw the first card of the pack");
		}
		
	}
	
	//play a card in the pack of cards
	public static void playCard(Pack pack, Player player, int positionHandCard) {
		
		if(player.getPlayerCards().size()>positionHandCard) {
			ColorCard playedCard = player.getPlayerCards().get(positionHandCard);
			ColorCard lastPackCard = pack.returnLastColorCard();
			
			
			//if the played card is the same color of the last color of the pack
			if(playedCard.getColor()== lastPackCard.getColor()) {
				pack.addCard(playedCard);
				player.removeCard(positionHandCard);
			}else {
				if(playedCard instanceof NumberCard && lastPackCard instanceof NumberCard) {
					NumberCard nc1 =(NumberCard) playedCard;
					NumberCard nc2 = (NumberCard) lastPackCard;
					
					if(nc1.getNumber()==nc2.getNumber()) {
						pack.addCard(playedCard);
						player.removeCard(positionHandCard);
					}

				}else if(playedCard instanceof BlockCard && lastPackCard instanceof BlockCard) {
					BlockCard bc1 =(BlockCard) playedCard;
					BlockCard bc2 = (BlockCard) lastPackCard;
					
					if(bc1.getNumberBlock()==bc2.getNumberBlock()) {
						pack.addCard(playedCard);
						player.removeCard(positionHandCard);
					}
				}
			}
		}else {
			System.out.println("ERRO: POSITION OUT OF RANGE");
		}
	}
	
	//return if is a player have a playable card in hand
	public static boolean havePlayableCardInHand(Pack pack, Player player) {
		//ColorCard lastPackCard = pack.returnLastColorCard();
		int initialPackSize = pack.getPackCards().size();
		boolean possibleToPlay= false;
			for(int i=0;player.getPlayerCards().size()>i && possibleToPlay==false ;i++) {
				playCard(pack,player,i);
				if(pack.getPackCards().size()>initialPackSize) {
					//moment when have the reverse action of playCard()
					player.getPlayerCards().add(i,pack.returnLastColorCard());
					pack.removeCard(pack.getPackCards().size()-1);
					possibleToPlay=true;
				}
				
			
			}
		return possibleToPlay;
	}
	
	
	
	//play the first card possible to play in the hand in the pack
	public static void playTheFirstPlayableCardInHand(Pack pack, Player player) {
		//always is necessary to verify if the function havePlayableCardInHand() is true
		int initialPackSize = pack.getPackCards().size();
		boolean possibleToPlay= false;
		
		for(int i=0;player.getPlayerCards().size()>i && possibleToPlay==false ;i++) {
			playCard(pack,player,i);
			if(pack.getPackCards().size()>initialPackSize) {
				possibleToPlay=true;
			}
		}
				
	}
	
	//function not used, fusion between havePlayableCardInHand() and playTheFirstPlayableCardInHand()
		public static boolean havePlayableCardInHandAndPlayCard(Pack pack, Player player) { //TESTED && POSSIBLE REMOVED?
			int initialPackSize = pack.getPackCards().size();
			boolean possibleToPlay= false;
				for(int i=0;player.getPlayerCards().size()>i && possibleToPlay==false ;i++) {
					playCard(pack,player,i);
					if(pack.getPackCards().size()>initialPackSize) {
						//dont have the moment when have the reverse action of playCard()
						possibleToPlay=true;
					}
					
				
				}
			return possibleToPlay;
		}
	
	//return a list of players based on a archive
	public static ArrayList<Player> returnCreatePlayers(String s1){
		File f1 = new File(s1);
		ArrayList<Player> Players = new ArrayList<Player>();
		
		try {
			Scanner scanner = new Scanner(f1);
			int cnt=0;
			while(scanner.hasNext() && cnt<=10) {
				String namePlayer = scanner.next();
				ArrayList<ColorCard> handPlayer = new ArrayList<ColorCard>();
				Player player1 = new Player(namePlayer,handPlayer);
				Players.add(player1);
				cnt++;
			}
		}catch(FileNotFoundException e) {
			System.out.println("ERRO DE LEITURA");
		}
		/*ArrayList<Player> Players = new ArrayList<Player>();

		String namePlayer = "G";
		ArrayList<ColorCard> handPlayer = new ArrayList<ColorCard>();
		Player player1 = new Player(namePlayer,handPlayer);
		Players.add(player1);

		String namePlayer2 = "M";
		ArrayList<ColorCard> handPlayer2 = new ArrayList<ColorCard>();
		Player player2 = new Player(namePlayer2,handPlayer2);
		Players.add(player2);

		String namePlayer3 = "S";
		ArrayList<ColorCard> handPlayer3 = new ArrayList<ColorCard>();
		Player player3 = new Player(namePlayer3,handPlayer3);
		Players.add(player3);*/

		
		return Players;
		
	}
	
	//deal the 7 necessary for the players and add the first card of the pack for the game begins
	public static void dealCards(Deck deck,Pack pack,ArrayList<Player> players ) {
		//to use this is necessary the full deck 
		if(pack.getPackCards().size()==0 && deck.getDeckCards().size()==returnNewFullDeck().getDeckCards().size()) {
			for(int i=0;i<players.size();i++) {
				for(int j=0;j<7;j++) {
					drawRandomCard(deck,pack,players.get(i));
				}
			}
			firstPackCard(deck,pack);
		}
	}
	//visualize the current state of the game
	public static void visualizeGame(Deck deck, Pack pack, ArrayList<Player> Players, int currentPlayer) {
		System.out.println("\n///////////////////////////////////////////////////////////////////////////");
		deck.viewVisual();
		for(int i=0;i<Players.size();i++) {
			Players.get(i).viewVisual();
		}
		System.out.println("\n");
		pack.viewVisual();
		System.out.println("\n");
		if(Players.size()>currentPlayer) {
			Players.get(currentPlayer).viewVisualHand();
		}
		
	}
	
	//inicialize a game
	public static void runAutomaticGame(Deck deck, Pack pack, ArrayList<Player> players) {
		dealCards(deck,pack,players);
		boolean havePlayersWith0cards = false;
		boolean blockedTheNextPlayer = false;
		boolean plus2CardForNextPlayer = false;
		
		for(int j=0;havePlayersWith0cards==false;j++) {//"infinity" loop, will end when any player have 0 cards
			
			for(int i=0;i<players.size();i++) {// round system
				
				Player currentPlayer = players.get(i);
				
				if(j==0 && i==0) {//if is the first round, visualize the initial scenario of the game
					System.out.println("THE GAME BEGINS");
					visualizeGame(deck,pack,players,i);
					System.out.println("\n\nREST OF THE GAME:\n\n");
				}
				
				
				if(blockedTheNextPlayer == true) { //block system
					i++;
					if(i>=players.size()) {//this if exists because when penultimate player block, the next player is the player in 0 position of the list
						i=0;
					}
					currentPlayer = players.get(i);
					blockedTheNextPlayer = false;
				}else if(plus2CardForNextPlayer==true) {
					drawRandomCard(deck, pack, currentPlayer);  
					drawRandomCard(deck, pack, currentPlayer);
					i++;
					if(i>=players.size()) {//same had the last if
						i=0;
					}
					currentPlayer = players.get(i);
					plus2CardForNextPlayer = false;
				}
				
				
				visualizeGame(deck,pack,players,i);
				
				if(havePlayableCardInHand(pack, currentPlayer)==true) {//the player plays a card
					playTheFirstPlayableCardInHand(pack, currentPlayer);
					
					System.out.println(currentPlayer.getName() + " plays a ");
					pack.returnLastColorCard().viewCard();
					
					
				}else {//dont have a playable card in hand
					for(;havePlayableCardInHand(pack, currentPlayer)==false;) {//"infinity" loop, this will end the player draws a playable card
						if(deck.getDeckCards().size()==0) {//if the deck empty in the moment of draw
							System.out.println("FILL DECK");
							deck.addDeckinDeck(returnNewFullDeck());
							
						}else {//the deck have cards
							drawRandomCard(deck,pack,currentPlayer);
							System.out.println(currentPlayer.getName()+ " draw a card");
						}
					}
					
					visualizeGame(deck,pack,players,i);
					
					playTheFirstPlayableCardInHand(pack, currentPlayer);
					
					System.out.println(currentPlayer.getName() + " plays a ");
					pack.returnLastColorCard().viewCard();
					
					
				}
				
				if(currentPlayer.getPlayerCards().size()==0) {//checks if the player, after playing a card, has no more cards in his hand
					System.out.println("\nGAME ENDED.\nCheck the winner in the txt archive or in Result.java ");
					try {
					String Winner = currentPlayer.getName();
					FileWriter fw = new FileWriter(new File("Winner.txt"));
					fw.write(Winner);
					fw.close();
					
					}catch(IOException Winner) {
						System.out.println("ERRO IN TRY/CATCH");
					}
					havePlayersWith0cards=true;
					break;
					
				}
					
				if(pack.returnLastColorCard() instanceof BlockCard) {//checks if the next player after the current player will be blocked
					System.out.println("NEXT PLAYER BLOCKED!");
					BlockCard lastBlockCardInPack = (BlockCard) pack.returnLastColorCard();
					if(lastBlockCardInPack.getNumberBlock()==0) {
						blockedTheNextPlayer = true;
					}else {
						plus2CardForNextPlayer=true;
					}
				}
			}
		}
	}

	
	
	public static void main(String[] args) {
		
		Deck d1 = returnNewFullDeck();
		Pack p1 = new Pack();
		
		runAutomaticGame(d1,p1,returnCreatePlayers("Players.txt"));
		
		
	}

}

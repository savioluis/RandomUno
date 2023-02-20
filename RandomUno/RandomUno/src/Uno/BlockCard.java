package Uno;

public class BlockCard extends ColorCard {

	private int numberBlock;
	
	public BlockCard(String color, int numberBlock) {
		super(color);
		if(numberBlock==0 || numberBlock==2) {
			this.numberBlock= numberBlock;
		}
		
	}

	public int getNumberBlock() {
		return numberBlock;
	}

	public void setNumberBlock(int numberBlock) {
		this.numberBlock = numberBlock;
	}
	@Override
	public void viewCard() {
		System.out.println(this.color + "Block" + this.numberBlock);
	}

}

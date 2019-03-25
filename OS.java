
public class OS {
	
	public static PageTable pageTable = new PageTable(256);
	public static Node hand;
	
	public static void resetR() {
		for(int i = 0; i < pageTable.getLength(); i++) {
			pageTable.getEntry(i).setReference(false);
		}
	}
	
	//when first page is initialized, hand will point to first page
	public static int Clock() {
		int frameNum = 0;
		while(pageTable.getEntry(hand.getCurrent()).isReference()) {
			pageTable.getEntry(hand.getCurrent()).setReference(false);
			hand = hand.getNext();
		}
		frameNum = hand.getFrame();
		for(int i = 0; i < CPU.tlb.gettlbEntries().length; i++) {
			if( Integer.parseInt(CPU.tlb.gettlbEntries()[i].getvPage(), 16) == hand.getCurrent()) {
				CPU.tlb.gettlbEntries()[i].setValid(false);
			}
		}
		pageTable.getEntry(hand.getCurrent()).setValid(false);
		return frameNum;
	}
	
}

public class OS
{
	public static PageTable pageTable = new PageTable(256);
	public static Node hand;
	public static PageTableEntry lastEntry;
	public static String lastAddress;
	public static void resetR()
	{
		for(int i = 0; i < pageTable.getPageTableEntries().length; i++)
		{
			if(pageTable.getEntry(i) != null)
			pageTable.getEntry(i).setReference(false);
		}
	}
	//when first page is initialized, hand will point to first page
	public static void newClock(TLBEntry entry)
	{
		//System.out.println("replacement: " + entry.getvPage());
        while(pageTable.getEntry(hand.getCurrent()).isReference())
        {
            pageTable.getEntry(hand.getCurrent()).setReference(false);
            hand = hand.getNext();
        }
        PageTableEntry pte = pageTable.getEntry(hand.getCurrent());
        lastEntry = pte;
        lastAddress = Integer.toHexString(hand.getCurrent());
        if(pte.isDirty())
        {
            //PageWriter.writeFile(Integer.toHexString(hand.getCurrent()), pte.getPageFrame());
            pageTable.getEntry(hand.getCurrent()).setDirty(false);
        }
        hand.setCurrent(Integer.parseInt(entry.getvPage(), 16));
    }
	public static void remainingWrite()
	{
		// String pageFileAddress, int pageFrameNumber
		for(int i = 0; i < CPU.tlb.gettlbEntries().length; i++)
		{
			TLBEntry curr = CPU.tlb.getEntry(i);
			PageWriter.writeFile(curr.getvPage(), curr.getPageFrame());
		}
	}
	public static void readFile(String address, int index)
	{
		PageReader.readPageFile(address, index);
	}
}
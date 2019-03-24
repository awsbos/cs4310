public class MMU
{
	// variable used for FIFO
	public static int tlbIndex = 0;
	public static void read(String address)
	{
		//int frameNumber = talk to OS and get FrameNumber from potentially evicted slot
		//CPU.tlb.setEntry(tlbIndex, new TLBEntry(address, frameNumber));
		CPU.tlb.setEntry(tlbIndex, new TLBEntry(address, tlbIndex));
		// also put entry in PAGE TABLE
		tlbIndex++;
		if(tlbIndex >= 16)
		{
			tlbIndex = 0;
		}
	}
	public static void write(String address, int value)
	{
		System.out.println(address + " : " + value);
		// set dirty to true
		TLBEntry newEntry = new TLBEntry(address, tlbIndex);
		newEntry.setDirty(true);
		CPU.tlb.setEntry(tlbIndex, newEntry);
		tlbIndex++;
		if(tlbIndex >= 16)
		{
			tlbIndex = 0;
		}
	}
}
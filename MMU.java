public class MMU
{
	// variable used for FIFO
	public static int tlbIndex = 0;
	public static boolean firstLoop = true;
	public static void read(String address)
	{
		//int frameNumber = talk to OS and get FrameNumber from potentially evicted slot
		//CPU.tlb.setEntry(tlbIndex, new TLBEntry(address, frameNumber));
		Main.fileOutputData.append(address + ",");
		Main.fileOutputData.append("0,");
		
		int existing = checkIfExistsInTLB(address);
		if(existing >= 0)
		{
			// here if address is also in TLB
			int frameNumber = CPU.tlb.getEntry(existing).getPageFrame();
			CPU.tlb.getEntry(existing).setReference(true);
			OS.pageTable.getEntry(Integer.parseInt(CPU.tlb.getEntry(existing).getvPage().substring(0, 2), 16)).setReference(true);
			int offset = Integer.parseInt(address.substring(2,4), 16);
			Main.fileOutputData.append(CPU.physicalMemory[frameNumber][offset] + ",");
			Main.fileOutputData.append("0, 0, 1,");
			Main.fileOutputData.append("N/A, N/A,");
		} else {
			// here if address is not in TLB
			// got to check for soft/hard miss
			int existsInPageTable = checkIfExistsInPageTable(address);
			if(existsInPageTable >= 0)
			{
				// soft miss
				// not in tlb, valid set to 1 in page table 
				PageTableEntry foundEntry = OS.pageTable.getEntry(existsInPageTable);
				TLBEntry newEntry = new TLBEntry(Integer.toHexString(existsInPageTable), foundEntry.getPageFrame());
				CPU.tlb.setEntry(tlbIndex, newEntry);
				int offset = Integer.parseInt(address.substring(2,4), 16);
				Main.fileOutputData.append(CPU.physicalMemory[tlbIndex][offset] + ",");
				Main.fileOutputData.append("1, 0, 0,");
				Main.fileOutputData.append("N/A, N/A,");
			} else {
				// hard miss
				TLBEntry tlbEntry = new TLBEntry(address, tlbIndex);
				CPU.tlb.setEntry(tlbIndex, tlbEntry);
				OS.pageTable.setEntry(Integer.parseInt(address.substring(0,2), 16), tlbIndex);
				
				// also put entry in PAGE TABLE
				int index = tlbIndex;
				boolean evicted = false;
				if(!firstLoop)
				{
					OS.newClock(tlbEntry);
					index = OS.lastEntry.getPageFrame();
					evicted = true;
				}
				OS.readFile(address, index);
				// make node for new page info
				if(firstLoop)
				{
					if(OS.hand == null)
					{
						Node node = new Node(null, null, Integer.parseInt(tlbEntry.getvPage(), 16), 0);
						node.setPrevious(node);
						node.setNext(node);
						OS.hand = node;
					} else {
						Node current = OS.hand;
						Node newNode = new Node(current.getPrevious(), current, Integer.parseInt(tlbEntry.getvPage(), 16), tlbIndex);
						current.getPrevious().setNext(newNode);
						current.setPrevious(newNode);
					}
				}
				int offset = Integer.parseInt(address.substring(2,4), 16);
				Main.fileOutputData.append(CPU.physicalMemory[tlbIndex][offset] + ",");
				Main.fileOutputData.append("0, 1, 0,");
				if(evicted)
				{
					int dirty = 0;
					if(OS.lastEntry.isDirty())
					{
						dirty = 1;
					}
					String appended = OS.lastAddress.toUpperCase() + "," + dirty;
					Main.fileOutputData.append(appended);
				} else {
					Main.fileOutputData.append("N/A, N/A,");
				}
			}
			tlbIndex++;
			if(tlbIndex >= 16)
			{
				tlbIndex = 0;
				firstLoop = false;
			}
		}
	}
	public static void write(String address, int value)
	{
		Main.fileOutputData.append(address + ",");
		Main.fileOutputData.append("1,");
		int existing = checkIfExistsInTLB(address);
		if(existing >= 0)
		{
			// hit
			int frameNumber = CPU.tlb.getEntry(existing).getPageFrame();
			CPU.tlb.getEntry(existing).setReference(true);
			CPU.tlb.getEntry(existing).setDirty(true);
			OS.pageTable.getEntry(Integer.parseInt(CPU.tlb.getEntry(existing).getvPage().substring(0, 2), 16)).setReference(true);
			OS.pageTable.getEntry(Integer.parseInt(CPU.tlb.getEntry(existing).getvPage().substring(0, 2), 16)).setDirty(true);
			int offset = Integer.parseInt(address.substring(2,4), 16);
			CPU.physicalMemory[frameNumber][offset] = value;
			PageWriter.writeFile(address.substring(0, 2), frameNumber);
			Main.fileOutputData.append(CPU.physicalMemory[frameNumber][offset] + ",");
			Main.fileOutputData.append("0, 0, 1,");
			Main.fileOutputData.append("N/A, N/A,");
		} else {
			int existsInPageTable = checkIfExistsInPageTable(address);
			if(existsInPageTable >= 0)
			{
				// soft miss
				// not in tlb, valid set to 1 in page table 
				PageTableEntry foundEntry = OS.pageTable.getEntry(existsInPageTable);
				foundEntry.setDirty(true);
				TLBEntry newEntry = new TLBEntry(Integer.toHexString(existsInPageTable), foundEntry.getPageFrame());
				newEntry.setDirty(true);
				CPU.tlb.setEntry(tlbIndex, newEntry);
				int offset = Integer.parseInt(address.substring(2,4), 16);
				CPU.physicalMemory[newEntry.getPageFrame()][offset] = value;
				PageWriter.writeFile(address.substring(0, 2), newEntry.getPageFrame());
				Main.fileOutputData.append(CPU.physicalMemory[newEntry.getPageFrame()][offset] + ",");
				Main.fileOutputData.append("1, 0, 0,");
				Main.fileOutputData.append("N/A, N/A,");
			} else {
				// hard miss
				TLBEntry newEntry = new TLBEntry(address, tlbIndex);
				newEntry.setDirty(true);
				CPU.tlb.setEntry(tlbIndex, newEntry);
				PageTableEntry pte = new PageTableEntry(tlbIndex);
				pte.setDirty(true);
				OS.pageTable.setEntry(Integer.parseInt(address.substring(0,2), 16), pte);			
				// also put entry in PAGE TABLE
				int index = tlbIndex;
				boolean evicted = false;
				if(!firstLoop)
				{
					OS.newClock(newEntry);
					index = OS.lastEntry.getPageFrame();
					evicted = true;
				}
				OS.readFile(address, index);
				// make node for new page info
				if(firstLoop)
				{
					if(OS.hand == null)
					{
						Node node = new Node(null, null, Integer.parseInt(newEntry.getvPage(), 16), 0);
						node.setPrevious(node);
						node.setNext(node);
						OS.hand = node;
					} else {
						Node current = OS.hand;
						Node newNode = new Node(current.getPrevious(), current, Integer.parseInt(newEntry.getvPage(), 16), tlbIndex);
						current.getPrevious().setNext(newNode);
						current.setPrevious(newNode);
					}
				}
				int offset = Integer.parseInt(address.substring(2,4), 16);
				CPU.physicalMemory[tlbIndex][offset] = value;
				PageWriter.writeFile(address.substring(0, 2), tlbIndex);
				Main.fileOutputData.append(CPU.physicalMemory[tlbIndex][offset] + ",");
				Main.fileOutputData.append("0, 1, 0,");
				if(evicted)
				{
					int dirty = 0;
					if(OS.lastEntry.isDirty())
					{
						dirty = 1;
					}
					String appended = OS.lastAddress.toUpperCase() + "," + dirty;
					Main.fileOutputData.append(appended);
				} else {
					Main.fileOutputData.append("N/A, N/A,");
				}
			}
			tlbIndex++;
			if(tlbIndex >= 16)
			{
				tlbIndex = 0;
			}
		}
	}
	public static int checkIfExistsInTLB(String address)
	{
		for(int i = 0; i < CPU.tlb.gettlbEntries().length; i++)
		{
			if(CPU.tlb.getEntry(i) != null)
			{
				if(address.substring(0,2).equalsIgnoreCase(CPU.tlb.getEntry(i).getvPage()))
				{
					return i;
				}
			}
		}
		return -1;
	}
	public static int checkIfExistsInPageTable(String address)
	{
		int realIndex = Integer.parseInt(address.substring(0, 2), 16);
		if(OS.pageTable.getPageTableEntries()[realIndex] != null && OS.pageTable.getPageTableEntries()[realIndex].isValid())
		{
			return realIndex;
		}
		return -1;
	}
	public static void resetR()
	{
		for(int i = 0; i < CPU.tlb.gettlbEntries().length; i++)
		{
			if(CPU.tlb.gettlbEntries()[i] != null)
				CPU.tlb.gettlbEntries()[i].setReference(false);
		}
	}
}
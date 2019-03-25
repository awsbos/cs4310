public class PageTable
{
	private PageTableEntry[] pageTableEntries;
	public PageTable(int length)
	{
		setPageTableEntries(new PageTableEntry[length]);
	}
	public PageTable()
	{
		setPageTableEntries(new PageTableEntry[256]);
	}
	// functionality
	public boolean checkEntry(int index)
	{
		return getPageTableEntries()[index].isValid();
	}
	public boolean checkIfEntryIsValid(int index)
	{
		return (getPageTableEntries()[index].isValid() && !getPageTableEntries()[index].isDirty());
	}
	public PageTableEntry getEntry(int index)
	{
		return getPageTableEntries()[index];
	}
	public void setEntry(int index, PageTableEntry input)
	{
		getPageTableEntries()[index] = input;
	}
	public void setEntry(int pageNumber, String frameNumberInHex)
	{
		getPageTableEntries()[pageNumber] = new PageTableEntry(frameNumberInHex);
	}
	public void setEntry(int pageNumber, int frameNumber)
	{
		getPageTableEntries()[pageNumber] = new PageTableEntry(frameNumber);
	}
	// default getters/setters + toString
	public PageTableEntry[] getPageTableEntries()
	{
		return pageTableEntries;
	}
	public void setPageTableEntries(PageTableEntry[] pageTableEntries)
	{
		this.pageTableEntries = pageTableEntries;
	}
	public String toString()
	{
		String returned = "";
		returned += "Page Table Contents: \n";
		 for(int x = 0; x < getPageTableEntries().length; x++)
		{
			returned += "(" + x + "): " + getPageTableEntries()[x] + "\n";
		}
		return returned;
	}
}
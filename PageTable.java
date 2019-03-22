public class PageTable
{
	private PageTableEntry[][] pageTableEntries;
	public PageTable(int rowLength, int columnLength)
	{
		setPageTableEntries(new PageTableEntry[rowLength][columnLength]);
	}
	public PageTable()
	{
		setPageTableEntries(new PageTableEntry[256][4096]);
	}
	// functionality
	public PageTableEntry getEntry(int row, int col)
	{
		return getPageTableEntries()[row][col];
	}
	public boolean checkEntry(int row, int col)
	{
		return getPageTableEntries()[row][col].isValid();
	}
	public boolean checkIfEntryIsValid(int row, int col)
	{
		return (getPageTableEntries()[row][col].isValid() && !getPageTableEntries()[row][col].isDirty());
	}
	// Needs to be intrepretted with austin
	
	
	
	
	/*    public void writeEntry(int pagenumber, String framenumber)
   {
       PT[pagenumber].writeEntry(framenumber);
   }
	 * 
	 * 
	 * 
	 * public void writeEntry(int pageNumber, String frameNumberInHex)
	{
		getPageTableEntries[][].writeEntry()
	}
	public void writeEntry(int pageNumber, String frameNumber)
	{
		
	}*/
	// default getters/setters + toString
	public PageTableEntry[][] getPageTableEntries()
	{
		return pageTableEntries;
	}
	public void setPageTableEntries(PageTableEntry[][] pageTableEntries)
	{
		this.pageTableEntries = pageTableEntries;
	}
	public String toString()
	{
		String returned = "";
		returned += "Page Table Contents: \n";
		// find a way to print this out correctly! Meaning the increments must be correct
		/* for(int x = 0; x < pageTableEntries.length; x++)
		{
			for(int y = 0; y < pageTableEntries[x].length; y++)
			{
				returned += "(" + x + ", " + y + "):" + getPageTableEntries()[x][y] + "\n";
			}
		}*/
		return returned;
	}
}
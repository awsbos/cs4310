public class TLB
{
	private TLBEntry[] tlbEntries;
	public TLB(int length)
	{
		settlbEntries(new TLBEntry[length]);
	}
	public TLB()
	{
		settlbEntries(new TLBEntry[16]);
	}
	public TLBEntry[] gettlbEntries()
	{
		return tlbEntries;
	}
	public void settlbEntries(TLBEntry[] tlbEntries)
	{
		this.tlbEntries = tlbEntries;
	}
	public String toString()
	{
		String returned = "";
		returned += "TLB Contents: \n";
		 for(int x = 0; x < gettlbEntries().length; x++)
		{
			returned += "(" + x + "):" + gettlbEntries()[x] + "\n";
		}
		return returned;
	}
}
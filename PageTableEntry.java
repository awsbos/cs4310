public class PageTableEntry extends VirtualEntry
{
	public PageTableEntry(boolean valid, boolean reference, boolean dirty, int pageFrameNumber)
	{
		super(valid, reference, dirty, pageFrameNumber);
	}
	public PageTableEntry(String hexFrameNumber)
	{
		super(Integer.parseInt(hexFrameNumber.subSequence(0, 2).toString(), 16));
	}
	public PageTableEntry(int intPageFrameNumber)
	{
		super(intPageFrameNumber);
	}
	public String toString()
	{
		return "Page Table Entry: (V: " + isValid() + ", R: " + isReference() + ", D: " + isDirty() + ", Page Frame: " + getPageFrame();
	}
}
public class PageTableEntry
{
	private boolean valid;
	private boolean reference;
	private boolean dirty;
	private int pageFrame;
	private PageTableEntry(boolean valid, boolean reference, boolean dirty, int pageFrameNumber)
	{
		setValid(valid);
		setReference(reference);
		setDirty(dirty);
		setPageFrame(pageFrameNumber);
	}
	// proper builder, that way PTE(B,B,B,I) stays private
	public static PageTableEntry writeEntry(String hexFrameNumber)
	{
		return (new PageTableEntry(true, true, false, Integer.parseInt(hexFrameNumber.subSequence(0, 2).toString(), 16)));
	}
	// proper alternate builder, that way PTE(B,B,B,I) stays private
	public static PageTableEntry writeEntry(int intPageFrameNumber)
	{
		return (new PageTableEntry(true, true, false, intPageFrameNumber));
	}
	// main used functions below this point
	public void resetAllBits()
	{
		setValid(true);
		setReference(true);
		setDirty(false);
	}
	// below this point is default getter/setters + toString
	public boolean isValid()
	{
		return valid;
	}
	public void setValid(boolean valid)
	{
		this.valid = valid;
	}
	public boolean isReference()
	{
		return reference;
	}
	public void setReference(boolean reference)
	{
		this.reference = reference;
	}
	public boolean isDirty()
	{
		return dirty;
	}
	public void setDirty(boolean dirty)
	{
		this.dirty = dirty;
	}
	public int getPageFrame()
	{
		if(isValid())
		{
			return -1;
		}
		return pageFrame;
	}
	public void setPageFrame(int pageFrame)
	{
		this.pageFrame = pageFrame;
	}
	public String toString()
	{
		return "Page Table Entry: (Valid: " + isValid() + ", Reference: " + isReference() + ", Dirty: " + isDirty() + ", Page Frame: " + getPageFrame();
	}
}

public abstract class VirtualEntry
{
	private boolean valid;
	private boolean reference;
	private boolean dirty;
	private int pageFrame;
	public VirtualEntry(boolean valid, boolean reference, boolean dirty, int pageFrameNumber)
	{
		setValid(valid);
		setReference(reference);
		setDirty(dirty);
		setPageFrame(pageFrameNumber);
	}
	public VirtualEntry(int pageFrameNumber)
	{
		setPageFrame(pageFrameNumber);
		resetAllBits();
	}
	// main used functions below this point
	public void resetAllBits()
	{
		setValid(true);
		setReference(true);
		setDirty(false);
	}
	// below this point is default getter/setters
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
		if(!isValid())
		{
			return -1;
		}
		return pageFrame;
	}
	public void setPageFrame(int pageFrame)
	{
		this.pageFrame = pageFrame;
	}
}
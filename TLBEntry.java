
public class TLBEntry extends VirtualEntry
{
	private String vPage;

	public TLBEntry(boolean valid, boolean reference, boolean dirty, int pageFrameNumber, String vPage)
	{
		super(valid, reference, dirty, pageFrameNumber);
		setvPage(vPage);
	}
	public TLBEntry(String vPage, int pageFrameNumber)
	{
		super(pageFrameNumber);
		setvPage(vPage);
	}
	public TLBEntry(String vPage, String hexFrameNumber)
	{
		super(Integer.parseInt(hexFrameNumber.subSequence(0, 2).toString(), 16));
		setvPage(vPage);
	}
	public String getvPage()
	{
		return vPage;
	}
	public void setvPage(String vPage)
	{
		this.vPage = vPage;
	}
	public String toString()
	{
		return "TLB Entry: [V-Page: " + getvPage() + ", (Valid: " + isValid() + ", Reference: " + isReference() + ", Dirty: " + isDirty() + "), Page Frame: " + getPageFrame() + "]";
	}
}
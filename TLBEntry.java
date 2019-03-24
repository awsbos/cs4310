
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
		setvPage(vPage.substring(0, 2));
	}
	public TLBEntry(String vPage, String hexFrameNumber)
	{
		super(Integer.parseInt(hexFrameNumber.subSequence(0, 2).toString(), 16));
		setvPage(vPage.substring(0, 2));
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
		return "TLB Entry: [V-Page: " + getvPage() + ", (V: " + isValid() + ", R: " + isReference() + ", D: " + isDirty() + "), Page Frame: " + getPageFrame() + "]";
	}
}
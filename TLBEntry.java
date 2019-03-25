
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
		if(vPage.length() > 1)
		{
			setvPage(vPage.substring(0, 2).toUpperCase());
		} else {
			setvPage(("0" + vPage).toUpperCase());
		}
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
	public static TLBEntry copy(TLBEntry in)
	{
		return new TLBEntry(in.isValid(), in.isReference(), in.isDirty(), in.getPageFrame(), in.getvPage());
	}
}
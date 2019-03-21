
public class TLBEntry {

	private String VPage;
	private int PageFrame;
	private boolean V;
	private boolean R;
	private boolean D;
	
	public TLBEntry(String page) {
		VPage = page;
		V = true;
		R = true;
		D = false;
	}
	
	public void reset() {
		setR(false);
	}

	public String getVPage() {
		return VPage;
	}

	public void setVPage(String vPage) {
		VPage = vPage;
	}

	public int getPageFrame() {
		return PageFrame;
	}

	public void setPageFrame(int pageFrame) {
		PageFrame = pageFrame;
	}

	public boolean isValid() {
		return V;
	}

	public void setV(boolean v) {
		V = v;
	}

	public boolean isR() {
		return R;
	}

	public void setR(boolean r) {
		R = r;
	}

	public boolean isDirty() {
		return D;
	}

	public void setD(boolean d) {
		D = d;
	}
	
}

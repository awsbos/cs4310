public class Node 
{
	private Node previous;
	private int current = 0;
	private Node next;
	public Node(Node previous, Node next, int current)
	{
		setPrevious(previous);
		setNext(next);
		setCurrent(current);
	}
	public Node evict()
	{
		if(previous != null)
		{
			getPrevious().setNext(getNext());
			getNext().setPrevious(getPrevious());
			return this;
		} else {
			getNext().setPrevious(null);
			return this;
		}
	}
	public Node getPrevious()
	{
		return previous;
	}
	public void setPrevious(Node previous)
	{
		this.previous = previous;
	}
	public int getCurrent()
	{
		return current;
	}
	public void setCurrent(int current)
	{
		this.current = current;
	}
	public Node getNext()
	{
		return next;
	}
	public void setNext(Node next)
	{
		this.next = next;
	}
}
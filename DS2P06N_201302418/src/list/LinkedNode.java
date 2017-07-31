package list;

public class LinkedNode<T> {

	private T _element;
	private LinkedNode<T> _next;
	//생성자
	public LinkedNode()
	{
		this.setElement(null);
		this.setNext(null);
	}
	public LinkedNode(T givenElement, LinkedNode<T> givenNext)
	{
		this.setElement(givenElement);
		this.setNext(givenNext);
	}
	//getter & setter
	public T element()
	{
		return this._element;
	}
	public void setElement(T newElement)
	{
		this._element = newElement;
	}
	//현재 Node의 다음 Node를 반환한다.
	public LinkedNode<T> next()
	{
		return this._next;
	}
	//다음Node를 설정한다.
	public void setNext(LinkedNode<T> newNext)
	{
		this._next = newNext;
	}
}

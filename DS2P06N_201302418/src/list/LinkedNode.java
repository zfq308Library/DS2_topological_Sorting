package list;

public class LinkedNode<T> {

	private T _element;
	private LinkedNode<T> _next;
	//������
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
	//���� Node�� ���� Node�� ��ȯ�Ѵ�.
	public LinkedNode<T> next()
	{
		return this._next;
	}
	//����Node�� �����Ѵ�.
	public void setNext(LinkedNode<T> newNext)
	{
		this._next = newNext;
	}
}

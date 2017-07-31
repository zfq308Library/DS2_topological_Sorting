package list;

public class LinkedStack<E> implements Stack<E> {
	//private ����
	private int _size;
	private LinkedNode<E> _top;
	//������
	public LinkedStack() {
		this.reset();
	}
	// Getter & Setter
	private LinkedNode<E> top(){
		return this._top;
	}
	private void setTop(LinkedNode<E> newTop){
		this._top = newTop;
	}
	//Interface�� Stack�Լ��� �����Ѵ�.
	public int size() {
		return this._size;
	}
	private void setSize(int newSize) {
		this._size = newSize;
	}
	//Stack�� �ʱ�ȭ�Ѵ�.
	@Override
	public void reset() {
		this.setSize(0);
		this.setTop(null);
	}
	//Stack�� ������� Ȯ���Ѵ�.
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	//Node�� �̷�����ֱ⿡ �׻� ���������ʴ´�.
	@Override
	public boolean isFull() {
		return false;
	}
	//Stack�� ���ο� ���Ҹ� �߰��Ѵ�.
	@Override
	public boolean push(E anElement) {
		LinkedNode<E> newTop = new LinkedNode<E>(anElement, this.top());
		this.setTop(newTop);
		this.setSize(this.size()+1);
		return true;
	}
	//Stack���� top���Ҹ� ������.
	@Override
	public E pop() {
		if (this.isEmpty()){
			return null;
		}
		else {
			E removedElement = this.top().element();
			this.setTop(this.top().next());
			this.setSize(this.size()-1);
			return removedElement;
		}
	}
	//Stack�� top���ִ� ���Ҹ� ��ȯ�Ѵ�.
	@Override
	public E peek() {
		if (this.isEmpty()){
			return null;
		}
		else {
			return this.top().element();
		}
	}
	public IteratorForLinkedStack iterator() 
	{
		return new IteratorForLinkedStack();
	}

	public class IteratorForLinkedStack implements Iterator<E> {                                        
		LinkedNode<E> _nextNode;
		
		private IteratorForLinkedStack()
		{
			this.setNextNode(LinkedStack.this.top());
		}
		
		private LinkedNode<E> nextNode()
		{
			return this._nextNode;
		}
		private void setNextNode(LinkedNode<E> newLinkedNode)
		{
			this._nextNode = newLinkedNode;
		}
		
		public boolean hasNext()
		{
			return this.nextNode() != null;
		}
		public E next() //
		{
			E nextElement = this.nextNode().element();
			this.setNextNode(this.nextNode().next());
			return nextElement;
		}
	}
}

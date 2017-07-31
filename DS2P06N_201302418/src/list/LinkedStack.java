package list;

public class LinkedStack<E> implements Stack<E> {
	//private 변수
	private int _size;
	private LinkedNode<E> _top;
	//생성자
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
	//Interface의 Stack함수를 구현한다.
	public int size() {
		return this._size;
	}
	private void setSize(int newSize) {
		this._size = newSize;
	}
	//Stack을 초기화한다.
	@Override
	public void reset() {
		this.setSize(0);
		this.setTop(null);
	}
	//Stack이 비었는지 확인한다.
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	//Node로 이루어져있기에 항상 가득차지않는다.
	@Override
	public boolean isFull() {
		return false;
	}
	//Stack에 새로운 원소를 추가한다.
	@Override
	public boolean push(E anElement) {
		LinkedNode<E> newTop = new LinkedNode<E>(anElement, this.top());
		this.setTop(newTop);
		this.setSize(this.size()+1);
		return true;
	}
	//Stack에서 top원소를 빼낸다.
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
	//Stack의 top에있는 원소를 반환한다.
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

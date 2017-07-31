package list;

public class LinkedList<T> implements List<T> {

	private LinkedNode<T> _head;
	private int 		  _size;
	
	//������ reset�� �̿��Ͽ� �ʱ�ȭ�Ѵ�.
	public LinkedList()
	{
		this.reset();
	}
	//LinkedList�� head�� ��ȯ�Ѵ�.
	private LinkedNode<T> head() 
	{
		return this._head;
	}
	//LinkedList�� head�� ���� �����Ѵ�.
	private void setHead(LinkedNode<T> newHead) 
	{
		this._head = newHead;
	}
	//LinkedList�� ũ�⸦ ��ȯ�Ѵ�.
	public int size() 
	{
		return this._size;
	}
	//LinkedList�� ũ�⸦ �����Ѵ�.
	private void setSize(int newSize) 
	{
		this._size = newSize;
	}
	//LinkedList�� ������� �˻��Ѵ�.
	public boolean isEmpty() 
	{
		return (this.size() == 0);
	}
	//LinkedList�� ����á���� �˻��Ѵ�. ������ Node�� �̷�����ֱ� ������ ���� �������� �ʴ´�.
	public boolean isFull() 
	{
		return false;
	}
	//LinkedList�� anElement������ Node�� �����Ͽ� �߰��Ѵ�.
	public boolean add (T anElement) 
	{
		return this.addToFirst(anElement);
	}
	//LinkedList�� head�� Node�� �����Ѵ�.
	public T removeAny() 
	{
		return this.removeFirst();
	}
	
	//List�� �ʱ�ȭ�Ѵ�.
	public void reset() {
		this.setSize(0);
		this.setHead(null);
	}
	//List�� ù���� Element�� ��ȯ�Ѵ�.
	public T firstElement() {
		if (this.isEmpty()){
			return null;
		}
		else {
			return this.head().element();
		}
	}
	//List�� ������ Element�� ��ȯ�Ѵ�.
	public T lastElement() {
		LinkedNode<T> current = this.head();
		if (current == null){
			return null;
		}
		else {
			while(current.next() != null){
				current = current.next();
			}
			return current.element();
		}
	}
	//List���� anIndex��°�� ��ġ�� Element�� ��ȯ�Ѵ�.
	public T elementAtIndex(int anIndex) {
		if (anIndex<0 || anIndex >= this.size()){
			return null;
		}
		else {
			LinkedNode<T> current = this.head();
			for(int i=0; i<anIndex; i++){
				current = current.next();
			}
			return current.element();
		}
			
	}
	//List�� anElement�� ���� Node�� ù��°�� �߰��Ѵ�.
	public boolean addToFirst(T anElement) {
		LinkedNode<T> newHeadNode = new LinkedNode<T>(anElement, this.head());
		this.setHead(newHeadNode);
		this.setSize(this.size()+1);
		return true;
	}
	//List�� anElement�� ���� Node�� �������� �߰��Ѵ�.
	//List�� ����ִٸ� ������=ó�� �̹Ƿ� ù��°�� �߰��Ѵ�.
	public boolean addToLast(T anElement) {
		if (this.isEmpty()){
			return this.addToFirst(anElement);
		}
		else {
			LinkedNode<T> current = this.head();
			while(current.next() !=null){
				current = current.next();
			}
			current.setNext(new LinkedNode<T>(anElement, null));
			this.setSize(this.size()+1);
			return true;
		}
	}
	//anIndex�� ��ġ�� anElement�� ���� ���ο� Node�� �߰��Ѵ�.
	//Index�� 0�̸� ù��°�� �߰��ϹǷ� First�� ȣ���Ѵ�.
	public boolean addAtIndex(T anElement, int anIndex) {
		if(anIndex < 0 || anIndex > this.size()){
			return false;
		}
		else if (anIndex == 0){
			return this.addToFirst(anElement);
		}
		else {
			LinkedNode<T> previous = this.head();
			LinkedNode<T> current = previous.next();
			for (int i=0; i<anIndex; i++){ //�߰��� ��ġ�� �˻�
				previous = current;
				current = current.next();
			}
			LinkedNode<T> newNode = new LinkedNode<T>(anElement, current);
			previous.setNext(newNode);
			this.setSize(this.size()+1);
			return true;
		}
		
	}
	//ù���� Node�� �����ϰ� �� Element�� ��ȯ�Ѵ�.
	public T removeFirst() {
		if(this.isEmpty()){
			return null;
		}
		else {
			T removedElement = this.head().element();
			this.setHead(this.head().next());
			this.setSize(this.size()-1);
			return removedElement;
		}
	}
	//������ Node�� �����ϰ� �� Element�� ��ȯ�Ѵ�.
	//Node�� �ϳ��ۿ����ٸ� head�� null�� �Ǹ� List�� ũ�Ⱑ 0�̵ȴ�.
	public T removeLast() {
		if(this.isEmpty()){
			return null;
		}
		else if (this.head().next() == null){
			T lastElement = this.head().element();
			this.setHead(null);
			this.setSize(0);
			return lastElement;
		}
		else {
			LinkedNode<T> previous = this.head();
			LinkedNode<T> current = previous.next();
			while (current.next() != null){
				previous = current;
				current = current.next();
			}
			T lastElement = current.element();
			previous.setNext(current.next());
			this.setSize(this.size()-1);
			return lastElement;
		}
	}
	//anIndex�� ��ġ�� �����ϴ� Node�� �����ϰ� �� Element�� ��ȯ�Ѵ�.
	//0�̶�� ù��° Node�� �����ϴ°��̹Ƿ� First�� ȣ���Ѵ�.
	public T removeAtIndex(int anIndex) {
		if (anIndex < 0 || anIndex >= this.size()){
			return null;
		}
		else if (anIndex == 0){
			return this.removeFirst();
		}
		else {
			LinkedNode<T> previous = this.head();
			LinkedNode<T> current = previous.next();
			for (int i=1; i<anIndex; i++){
				previous = current;
				current = current.next();
			}
			previous.setNext(current.next());
			this.setSize(this.size()-1);
			return current.element();
		}
	}
	
	//�ݺ��ڰ�ü�� �����Ѵ�.
		public IteratorForLinkedList iterator() 
		{
			return new IteratorForLinkedList();
		}

		public class IteratorForLinkedList implements Iterator<T> 
		{                                        
			LinkedNode<T> _nextNode;
			
			private IteratorForLinkedList()
			{
				this.setNextNode(LinkedList.this.head());
			}
			
			private LinkedNode<T> nextNode()
			{
				return this._nextNode;
			}
			private void setNextNode(LinkedNode<T> newLinkedNode)
			{
				this._nextNode = newLinkedNode;
			}
			
			public boolean hasNext()
			{
				return this.nextNode() != null;
			}
			public T next() //
			{
				T nextElement = this.nextNode().element();
				this.setNextNode(this.nextNode().next());
				return nextElement;
			}
		}
}

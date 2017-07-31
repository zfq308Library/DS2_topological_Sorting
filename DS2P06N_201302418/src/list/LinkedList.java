package list;

public class LinkedList<T> implements List<T> {

	private LinkedNode<T> _head;
	private int 		  _size;
	
	//생성자 reset을 이용하여 초기화한다.
	public LinkedList()
	{
		this.reset();
	}
	//LinkedList의 head를 반환한다.
	private LinkedNode<T> head() 
	{
		return this._head;
	}
	//LinkedList의 head를 새로 지정한다.
	private void setHead(LinkedNode<T> newHead) 
	{
		this._head = newHead;
	}
	//LinkedList의 크기를 반환한다.
	public int size() 
	{
		return this._size;
	}
	//LinkedList의 크기를 지정한다.
	private void setSize(int newSize) 
	{
		this._size = newSize;
	}
	//LinkedList가 비었는지 검사한다.
	public boolean isEmpty() 
	{
		return (this.size() == 0);
	}
	//LinkedList가 가득찼는지 검사한다. 하지만 Node로 이루어져있기 때문에 결코 가득차지 않는다.
	public boolean isFull() 
	{
		return false;
	}
	//LinkedList에 anElement를갖는 Node를 생성하여 추가한다.
	public boolean add (T anElement) 
	{
		return this.addToFirst(anElement);
	}
	//LinkedList의 head인 Node를 삭제한다.
	public T removeAny() 
	{
		return this.removeFirst();
	}
	
	//List를 초기화한다.
	public void reset() {
		this.setSize(0);
		this.setHead(null);
	}
	//List의 첫번쨰 Element를 반환한다.
	public T firstElement() {
		if (this.isEmpty()){
			return null;
		}
		else {
			return this.head().element();
		}
	}
	//List의 마지막 Element를 반환한다.
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
	//List에서 anIndex번째에 위치한 Element를 반환한다.
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
	//List에 anElement를 갖는 Node를 첫번째에 추가한다.
	public boolean addToFirst(T anElement) {
		LinkedNode<T> newHeadNode = new LinkedNode<T>(anElement, this.head());
		this.setHead(newHeadNode);
		this.setSize(this.size()+1);
		return true;
	}
	//List에 anElement를 갖는 Node를 마지막에 추가한다.
	//List가 비어있다면 마지막=처음 이므로 첫번째에 추가한다.
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
	//anIndex의 위치에 anElement를 갖는 새로운 Node를 추가한다.
	//Index가 0이면 첫번째에 추가하므로 First를 호출한다.
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
			for (int i=0; i<anIndex; i++){ //추가될 위치를 검색
				previous = current;
				current = current.next();
			}
			LinkedNode<T> newNode = new LinkedNode<T>(anElement, current);
			previous.setNext(newNode);
			this.setSize(this.size()+1);
			return true;
		}
		
	}
	//첫번쨰 Node를 삭제하고 그 Element를 반환한다.
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
	//마지막 Node를 삭제하고 그 Element를 반환한다.
	//Node가 하나밖에없다면 head가 null이 되며 List의 크기가 0이된다.
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
	//anIndex의 위치에 존재하는 Node를 삭제하고 그 Element를 반환한다.
	//0이라면 첫번째 Node를 삭제하는것이므로 First를 호출한다.
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
	
	//반복자객체를 생성한다.
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

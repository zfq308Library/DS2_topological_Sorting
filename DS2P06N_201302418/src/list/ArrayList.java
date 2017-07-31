package list;

public class ArrayList<T> implements List<T> {
	
	private static final int DEFAULT_CAPACITY = 10;
	//private 변수
	private int _capacity;
	private int _size;
	private T[] _elements;
	//생성자
	public ArrayList() {
		this(ArrayList.DEFAULT_CAPACITY);
	}
	@SuppressWarnings("unchecked")
	public ArrayList(int givenCapacity) {
		this.setCapacity(givenCapacity);
		this.setElement((T[])new Object[this.capacity()]);
		this.setSize(0);
	}
	
	//Getter & Setter
	private int capacity() {
		return this._capacity;
	}
	private void setCapacity(int newCapacity) {
		this._capacity = newCapacity;
	}
	//왜 public 이어야 하는거지 ?
	@Override
	public int size() {  
		return this._size;
	}
	private void setSize(int newSize){
		this._size = newSize;
	}
	private T[] elements() {
		return this._elements;
	}
	private void setElement(T[] newElements) {
		this._elements = newElements;
	}
	//List의 모든 값을 비운다(초기화한다)
	@Override
	public void reset() {
		for (int i=0; i<this.size(); i++){
			this.elements()[i] = null;
		}
		this.setSize(0);
	}
	//List가 비었는지 확인한다.
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	//List가 가득찼는지 확인한다.
	@Override
	public boolean isFull() {
		return this.size() == this.capacity();
	}
	//List의 첫번쨰 원소를 반환한다.
	@Override
	public T firstElement() {
		if (! this.isEmpty()){
			return this.elements()[0];
		}
		return null;
	}
	//List의 마지막 원소를 반환한다.
	@Override
	public T lastElement() {
		if (! this.isEmpty()){
			return this.elements()[this.size()-1];
		}
		return null;
	}
	//List의 anIndex번쨰의 원소를 반환한다.
	@Override
	public T elementAtIndex(int anIndex) {
		if (! this.isEmpty()){
			if (anIndex >=0 || anIndex < this.size()){
				return this.elements()[anIndex];
			}
		}
		return null;
	}
	//List에 원소를 추가한다. 기본적으로 마지막에 넣게된다(배열)
	@Override
	public boolean add(T anElement) {
		return this.addToLast(anElement);
	}
	//List의 첫번째위치에 원소를 추가한다.
	@Override
	public boolean addToFirst(T anElement) {
		return this.addAtIndex(anElement, 0);
	}
	//List의 마지막위치에 원소를 추가한다.
	@Override
	public boolean addToLast(T anElement) {
		return this.addAtIndex(anElement, this.size());
	}
	//해당위치에 원소를 추가한다. 넣을 위치부터 배열의 마지막까지의 원소를 한칸씩 뒤로 밀어 원하는위치에 추가한다.
	@Override
	public boolean addAtIndex(T anElement, int anIndex) {
		if (! this.isFull()){
			if (anIndex >= 0 && anIndex <= this.size()){
				for(int i= this.size(); i>anIndex; i--){
					this.elements()[i] = this.elements()[i-1];
				}
			this.elements()[anIndex] = anElement;
			this.setSize(this.size()+1);
			return true;
			}
		}
		return false;
	}
	//원소를 삭제하고 그 원소를 반환한다. 일반적으로 배열에서 마지막 원소를 삭제하므로 Last를 호출한다.
	@Override
	public T removeAny() {
		return this.removeLast();
	}
	//첫번쨰 원소를 삭제하고 그 원소를 반환한다.
	@Override
	public T removeFirst() {
		return this.removeAtIndex(0);
	}
	//마지막 원소를 삭제하고 그 원소를 반환한다.
	@Override
	public T removeLast() {
		return this.removeAtIndex(this.size()-1);
	}
	//해당 위치의 원소를 삭제하고 반환한다. 삭제하는 위치부터 마지막까지 한칸씩 앞으로 밀어 삭제한다.
	@Override
	public T removeAtIndex(int anIndex) {
		if (! this.isEmpty()){
			if (anIndex >= 0 && anIndex < this.size()){
				T removedElement = this.elements()[anIndex];
				for (int i=anIndex; i<this.size(); i++){
					this.elements()[i] = this.elements()[i+1];
				}
				this.setSize(this.size()-1);
				this.elements()[this.size()] = null;
				return removedElement;
			}
		}
		return null;
	}
	//반복자객체를 생성한다.
	public IteratorForArrayList iterator() {
		return new IteratorForArrayList();
	}
	//ArrayList를 위한 반복 클래스이다.
	public class IteratorForArrayList implements Iterator<T> {                                        
		private int _nextPosition;
		
		private int nextPosition(){
			return this._nextPosition;
		}
		private void setNextPosition(int newPosition){
			this._nextPosition = newPosition;
		}
		
		private IteratorForArrayList(){
			this.setNextPosition(0);
		}
		
		public boolean hasNext() {
			return this.nextPosition() < ArrayList.this.size();
		}
		public T next() {
			T nextElement = ArrayList.this.elements()[this.nextPosition()];
			this.setNextPosition(this.nextPosition()+1);
			return nextElement;
		}
	}
}

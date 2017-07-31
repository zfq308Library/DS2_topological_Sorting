package list;

public class ArrayList<T> implements List<T> {
	
	private static final int DEFAULT_CAPACITY = 10;
	//private ����
	private int _capacity;
	private int _size;
	private T[] _elements;
	//������
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
	//List�� ��� ���� ����(�ʱ�ȭ�Ѵ�)
	@Override
	public void reset() {
		for (int i=0; i<this.size(); i++){
			this.elements()[i] = null;
		}
		this.setSize(0);
	}
	//List�� ������� Ȯ���Ѵ�.
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	//List�� ����á���� Ȯ���Ѵ�.
	@Override
	public boolean isFull() {
		return this.size() == this.capacity();
	}
	//List�� ù���� ���Ҹ� ��ȯ�Ѵ�.
	@Override
	public T firstElement() {
		if (! this.isEmpty()){
			return this.elements()[0];
		}
		return null;
	}
	//List�� ������ ���Ҹ� ��ȯ�Ѵ�.
	@Override
	public T lastElement() {
		if (! this.isEmpty()){
			return this.elements()[this.size()-1];
		}
		return null;
	}
	//List�� anIndex������ ���Ҹ� ��ȯ�Ѵ�.
	@Override
	public T elementAtIndex(int anIndex) {
		if (! this.isEmpty()){
			if (anIndex >=0 || anIndex < this.size()){
				return this.elements()[anIndex];
			}
		}
		return null;
	}
	//List�� ���Ҹ� �߰��Ѵ�. �⺻������ �������� �ְԵȴ�(�迭)
	@Override
	public boolean add(T anElement) {
		return this.addToLast(anElement);
	}
	//List�� ù��°��ġ�� ���Ҹ� �߰��Ѵ�.
	@Override
	public boolean addToFirst(T anElement) {
		return this.addAtIndex(anElement, 0);
	}
	//List�� ��������ġ�� ���Ҹ� �߰��Ѵ�.
	@Override
	public boolean addToLast(T anElement) {
		return this.addAtIndex(anElement, this.size());
	}
	//�ش���ġ�� ���Ҹ� �߰��Ѵ�. ���� ��ġ���� �迭�� ������������ ���Ҹ� ��ĭ�� �ڷ� �о� ���ϴ���ġ�� �߰��Ѵ�.
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
	//���Ҹ� �����ϰ� �� ���Ҹ� ��ȯ�Ѵ�. �Ϲ������� �迭���� ������ ���Ҹ� �����ϹǷ� Last�� ȣ���Ѵ�.
	@Override
	public T removeAny() {
		return this.removeLast();
	}
	//ù���� ���Ҹ� �����ϰ� �� ���Ҹ� ��ȯ�Ѵ�.
	@Override
	public T removeFirst() {
		return this.removeAtIndex(0);
	}
	//������ ���Ҹ� �����ϰ� �� ���Ҹ� ��ȯ�Ѵ�.
	@Override
	public T removeLast() {
		return this.removeAtIndex(this.size()-1);
	}
	//�ش� ��ġ�� ���Ҹ� �����ϰ� ��ȯ�Ѵ�. �����ϴ� ��ġ���� ���������� ��ĭ�� ������ �о� �����Ѵ�.
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
	//�ݺ��ڰ�ü�� �����Ѵ�.
	public IteratorForArrayList iterator() {
		return new IteratorForArrayList();
	}
	//ArrayList�� ���� �ݺ� Ŭ�����̴�.
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

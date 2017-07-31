package list;

public interface Iterator<E> {
	public boolean hasNext();
	public E       next();
}
//T로 하던 E로하던 똑같음  E로 한 이유는 반복자가 확인하는것이 Element = Edge이기 때문에 구분하기위해서 한것.
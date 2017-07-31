package graph;

import list.Iterator;

//Graph의 기본 함수들을 interface로 만든것이다.
public interface Graph<E> {
	
	public int numberOfVertices();
	public int numberOfEdges();
	
	public boolean vertexDoesExist(int aVertex);
	public boolean edgeDoesExist(int aTailVertex, int aHeadVertex);
	public boolean edgeDoesExist(E anEdge);
	
	public boolean edgeIsValid(int aTailVertex, int aHeadVertex);
	public boolean edgeIsValid(E anEdge);
	
	public boolean addEdge(E anEdge);
	
	public E edge(int aTailVertex, int aHeadVertex);
	public Iterator<E> neighborIteratorOf(int aTailVertex);
}

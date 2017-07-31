package graph;

import list.LinkedList;
import list.Iterator;

public class DirectedAdjacencyListGraph<E extends Edge> extends AdjacencyGraph<E> {

	private LinkedList<E>[] _adjacency;
	//생성자 
	@SuppressWarnings("unchecked") //잘못된 변환일지도 모른다는 경고를 무시하기위해 사용하였다.
	public DirectedAdjacencyListGraph(int givenNumberOfVertices){
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setAdjacency(new LinkedList[givenNumberOfVertices]);
		for (int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++){
			this.adjacency()[tailVertex] = new LinkedList<E>();
		}
		this.setNumberOfEdges(0);
	}
    //getter & setter
	protected LinkedList<E>[] adjacency(){
		return this._adjacency;
	}
	protected void setAdjacency(LinkedList<E>[] newAdjacency){
		this._adjacency = newAdjacency;
	}
	//해당 vertex에 있는 List를 반환한다.
	protected LinkedList<E> neighborListOf(int aTailVertex){
		return this.adjacency()[aTailVertex];
	}
	//edge가 존재하면 exist값을 존재하지않으면 none값들 돌려주는 함수이다.
	protected int adjacencyOfEdge(int aTailVertex, int aHeadVertex){
		if(this.vertexDoesExist(aTailVertex) && this.vertexDoesExist(aHeadVertex)){
			Iterator<E> iterator = this.neighborIteratorOf(aTailVertex); //tailvertex의 이웃한 edge의 반복자를 얻음
			while(iterator.hasNext()){
				E neighborEdge = iterator.next();
				if (aHeadVertex == neighborEdge.headVertex()){
					return AdjacencyGraph.EDGE_EXIST;
				}
			}
		}
		return AdjacencyGraph.EDGE_NONE;
	}


	//해당 vertex를 갖는 edge가 있는지 확인한다.
	@Override
	public boolean edgeDoesExist(int aTailVertex, int aHeadVertex) {
		return (this.adjacencyOfEdge(aTailVertex, aHeadVertex) != AdjacencyGraph.EDGE_NONE);
	}

	@Override
	public boolean edgeDoesExist(E anEdge) {
		if (anEdge != null){
			return this.edgeDoesExist(anEdge.tailVertex(), anEdge.headVertex());
		}
		return false;
	}
	//List에 Edge를 추가한다.
	@Override
	public boolean addEdge(E anEdge) {
		if (this.edgeIsValid(anEdge) && (! this.edgeDoesExist(anEdge))){
			this.neighborListOf(anEdge.tailVertex()).add(anEdge);
			this.setNumberOfEdges(this.numberOfEdge()+1);
			return true;
		}
		return false;
	}
	//해당 tailVertex에 이웃한 edge들의 list의 반복자를 얻는다.
	@Override
	public Iterator<E> neighborIteratorOf(int aTailVertex) {
		if (this.vertexDoesExist(aTailVertex)){
			return (Iterator<E>) this.neighborListOf(aTailVertex).iterator();
		}
		return null;
	}
	//해당 vertex에 존재하는 edge를 반환한다.
	@Override
	public E edge(int aTailVertex, int aHeadVertex) {
		if (this.vertexDoesExist(aTailVertex)){
			Iterator<E> iterator = this.neighborIteratorOf(aTailVertex);
			while(iterator.hasNext()){
				E neighborEdge = iterator.next();
				if(aHeadVertex == neighborEdge.headVertex()){
					return neighborEdge;
				}
			}
		}
		return null;
	}
	//edge의 수를 반환한다.
	@Override
	public int numberOfEdges() {
		return this.numberOfEdge();
	}
}

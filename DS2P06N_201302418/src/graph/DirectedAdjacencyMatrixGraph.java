package graph;

import list.Iterator;

public class DirectedAdjacencyMatrixGraph<E extends Edge>
	extends AdjacencyGraph<E>
	implements Graph<E>
{
	private int[][] _adjacency;
	
	//생성자
	protected DirectedAdjacencyMatrixGraph() {
	}
	public DirectedAdjacencyMatrixGraph(int givenNumberOfVertices) {
		this.setNumberOfVertices(givenNumberOfVertices);
		this.setNumberOfEdges(0);
		this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
		for (int tailVertex = 0; tailVertex < this.numberOfVertices(); tailVertex++){
			for (int headVertex = 0; headVertex < this.numberOfVertices(); headVertex++){
				this.setAdjacencyOfEdgeAsNone(tailVertex, headVertex);
			}
		}
	}
	
	// Getters & Setters 
	protected int[][] adjacency() {
		return this._adjacency;
	}
	protected void setAdjacency(int[][] newAdjacency) {
		this._adjacency = newAdjacency;
	}
	protected int adjacencyOfEdge(int tailVertex, int headVertex){
		return this.adjacency()[tailVertex][headVertex];
	}
	protected void setAdjacencyOfEdgeAs(int tailVertex, int headVertex, int anAdjacecnyOfEdge) {
		this.adjacency()[tailVertex][headVertex] = anAdjacecnyOfEdge;
	}
	//해당 vertex를 갖는 Edge가 있다고 저장
	protected void setAdjacencyOfEdgeAsExist(int tailVertex, int headVertex){
		this.setAdjacencyOfEdgeAs(tailVertex, headVertex, AdjacencyGraph.EDGE_EXIST);
	}
	//해당 vertex를 갖는 Edge가 없다고 저장
	protected void setAdjacencyOfEdgeAsNone(int tailVertex, int headVertex){
		this.setAdjacencyOfEdgeAs(tailVertex, headVertex, AdjacencyGraph.EDGE_NONE);
	}
	//해당 Vertex에 Edge가 있는지 검사한다.
	protected boolean adjacencyOfEdgeDoesExist(int tailVertex, int headVertex) {
		return (this.adjacencyOfEdge(tailVertex, headVertex) !=
				AdjacencyGraph.EDGE_NONE);
	}
	//해당 vertex에 Edge가 있는지 검사한다.
	public boolean edgeDoesExist(int aTailVertex, int aHeadVertex) {
		if (this.edgeIsValid(aTailVertex, aHeadVertex)) {
			return (this.adjacencyOfEdgeDoesExist(aTailVertex, aHeadVertex));
		}	
		return false;
	}
	//해당 edge가 있는지 없는지 검사한다.
	public boolean edgeDoesExist(E anEdge) {
		if (anEdge != null){
			return this.edgeDoesExist(anEdge.tailVertex(), anEdge.headVertex());
		}
		return false;
	}
	//Matrix에 Edge를 추가한다.
	public boolean addEdge(E anEdge) {
		if (anEdge != null) {
			if (this.edgeIsValid(anEdge) && (! this.edgeDoesExist(anEdge))){
				int tailVertex = anEdge.tailVertex();
				int headVertex = anEdge.headVertex();
				this.setAdjacencyOfEdgeAsExist(tailVertex, headVertex);
				this.setNumberOfEdges(this.numberOfEdge()+1);
				return true;
			}
		}
		return false;
	}
	//해당 위치에 존재하는 Edge를 반환한다.
	@SuppressWarnings("unchecked")
	public E edge(int aTailVertex, int aHeadVertex) {
		if (this.edgeDoesExist(aTailVertex, aHeadVertex)){
			return (E) new Edge(aTailVertex, aHeadVertex);
		}
		return null;
	}
	//tailVertex의 반복자 객체를 반환한다.
	@Override
	public Iterator<E> neighborIteratorOf(int aTailVertex) {
		return new IteratorForNeighborsOf(aTailVertex);
	}
	//내부클래스로 Matrix의 반복자객체를 얻기 위한 클래스이다.
	public class IteratorForNeighborsOf implements Iterator<E> {
		private int _tailVertex;
		private int _nextHeadVertex;
		
		private IteratorForNeighborsOf(int givenTailVertex) {
			this.setTailVertex(givenTailVertex);
			this.setNextHeadVertex(0);
		}
		
		public int tailVertex() {
			return this._tailVertex;
		}
		public void setTailVertex(int newTailVertex) {
			this._tailVertex = newTailVertex;
		}
		
		private int nextHeadVertex() {
			return this._nextHeadVertex;
		}
		private void setNextHeadVertex(int newNextHeadVertex) {
			this._nextHeadVertex = newNextHeadVertex;
		}
		@Override
		public boolean hasNext() {
			while (this.nextHeadVertex() < DirectedAdjacencyMatrixGraph.this.numberOfVertices()){
				if (DirectedAdjacencyMatrixGraph.this.adjacencyOfEdgeDoesExist(this.tailVertex(), this.nextHeadVertex())){
					return true;
				}
				this.setNextHeadVertex(this.nextHeadVertex()+1);
			}
			return false;
		}
		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			E nextElement = (E) new Edge(this.tailVertex(), this.nextHeadVertex());
			this.setNextHeadVertex(this.nextHeadVertex()+1);
			return nextElement;
		}
	}
	//Edge의 수를 반환한다.
	@Override
	public int numberOfEdges() {
		return this.numberOfEdge();
	}
}

package graph;

import list.Iterator;

public class DirectedAdjacencyMatrixGraph<E extends Edge>
	extends AdjacencyGraph<E>
	implements Graph<E>
{
	private int[][] _adjacency;
	
	//������
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
	//�ش� vertex�� ���� Edge�� �ִٰ� ����
	protected void setAdjacencyOfEdgeAsExist(int tailVertex, int headVertex){
		this.setAdjacencyOfEdgeAs(tailVertex, headVertex, AdjacencyGraph.EDGE_EXIST);
	}
	//�ش� vertex�� ���� Edge�� ���ٰ� ����
	protected void setAdjacencyOfEdgeAsNone(int tailVertex, int headVertex){
		this.setAdjacencyOfEdgeAs(tailVertex, headVertex, AdjacencyGraph.EDGE_NONE);
	}
	//�ش� Vertex�� Edge�� �ִ��� �˻��Ѵ�.
	protected boolean adjacencyOfEdgeDoesExist(int tailVertex, int headVertex) {
		return (this.adjacencyOfEdge(tailVertex, headVertex) !=
				AdjacencyGraph.EDGE_NONE);
	}
	//�ش� vertex�� Edge�� �ִ��� �˻��Ѵ�.
	public boolean edgeDoesExist(int aTailVertex, int aHeadVertex) {
		if (this.edgeIsValid(aTailVertex, aHeadVertex)) {
			return (this.adjacencyOfEdgeDoesExist(aTailVertex, aHeadVertex));
		}	
		return false;
	}
	//�ش� edge�� �ִ��� ������ �˻��Ѵ�.
	public boolean edgeDoesExist(E anEdge) {
		if (anEdge != null){
			return this.edgeDoesExist(anEdge.tailVertex(), anEdge.headVertex());
		}
		return false;
	}
	//Matrix�� Edge�� �߰��Ѵ�.
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
	//�ش� ��ġ�� �����ϴ� Edge�� ��ȯ�Ѵ�.
	@SuppressWarnings("unchecked")
	public E edge(int aTailVertex, int aHeadVertex) {
		if (this.edgeDoesExist(aTailVertex, aHeadVertex)){
			return (E) new Edge(aTailVertex, aHeadVertex);
		}
		return null;
	}
	//tailVertex�� �ݺ��� ��ü�� ��ȯ�Ѵ�.
	@Override
	public Iterator<E> neighborIteratorOf(int aTailVertex) {
		return new IteratorForNeighborsOf(aTailVertex);
	}
	//����Ŭ������ Matrix�� �ݺ��ڰ�ü�� ��� ���� Ŭ�����̴�.
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
	//Edge�� ���� ��ȯ�Ѵ�.
	@Override
	public int numberOfEdges() {
		return this.numberOfEdge();
	}
}

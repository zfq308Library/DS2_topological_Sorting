package graph;

public abstract class AdjacencyGraph<E extends Edge> implements Graph<E> {

	protected static final int EDGE_EXIST = 1;
	protected static final int EDGE_NONE  = 0;
	
	private int _numberOfVertices;
	private int _numberOfEdges;
	//getter & setter
	public int numberOfVertices(){
		return this._numberOfVertices;
	}
	public int numberOfEdge(){
		return this._numberOfEdges;
	}
	
	protected void setNumberOfVertices(int newNumberOfVertices){
		this._numberOfVertices = newNumberOfVertices;
	}
	protected void setNumberOfEdges(int newNumberOfEdges){
		this._numberOfEdges = newNumberOfEdges;
	}
	//해당 vertex가 존재하는지 확인한다.
	public boolean vertexDoesExist(int aVertex){
		return ((aVertex >= 0) && (aVertex < this.numberOfVertices()));
	}
	//해당 edge가 유효한지 확인한다.
	public boolean edgeIsValid(int aTailVertex, int aHeadVertex){
		return (this.vertexDoesExist(aTailVertex)&&
				this.vertexDoesExist(aHeadVertex));
	}
	public boolean edgeIsValid(E anEdge){
		if(anEdge != null){
			return (this.vertexDoesExist(anEdge.tailVertex()) &&
					this.vertexDoesExist(anEdge.headVertex()));
		}
		return false;
	}
}

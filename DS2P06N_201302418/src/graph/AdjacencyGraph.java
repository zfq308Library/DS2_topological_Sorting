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
	//�ش� vertex�� �����ϴ��� Ȯ���Ѵ�.
	public boolean vertexDoesExist(int aVertex){
		return ((aVertex >= 0) && (aVertex < this.numberOfVertices()));
	}
	//�ش� edge�� ��ȿ���� Ȯ���Ѵ�.
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

package topologicalSort;

import app.AppView;
import graph.AdjacencyGraph;
import graph.Edge;
import list.ArrayList;
import list.Iterator;
import list.LinkedStack;
import list.List;
import list.Stack;

public class TopologicalSort<E extends Edge> {

	private static final boolean DEBUG_MODE = true;
	
	private static void showDebugMessage(String aMessage){
		if (DEBUG_MODE){
			AppView.outputDebugMessage(aMessage);
		}
	}
	// Private 변수 
	// Interface로 생성하여 실제객체를 생성할때는 배열이나 연결체인으로 만들 수 있다.
	private AdjacencyGraph<E> _graph;
	private int[]			  _predecessorCounts;
	private Stack<Integer>    _zeroCountVertices;
	private List<Integer>     _sortedList;
	//생성자
	public TopologicalSort() {
		this.setGraph(null);
		this.setPredecessorCounts(null);
		this.setZeroCountVertices(null);
		this.setSortedList(null);
	}
	//Getters & Setters
	private AdjacencyGraph<E> graph() {
		return this._graph;
	}
	private void setGraph(AdjacencyGraph<E> newGraph){
		this._graph = newGraph;
	}
	private int[] predecessorCounts(){
		return this._predecessorCounts;
	}
	private void setPredecessorCounts(int[] newPredecessorCount) {
		this._predecessorCounts = newPredecessorCount;
	}
	private Stack<Integer> zeroCountVertices(){
		return this._zeroCountVertices;
	}
	private void setZeroCountVertices(Stack<Integer> newZeroCountVertices){
		this._zeroCountVertices = newZeroCountVertices;
	}
	
	private List<Integer> sortedList() {
		return this._sortedList;
	}
	private void setSortedList(List<Integer> newSortedList){
		this._sortedList = newSortedList;
	}
	public List<Integer> topologicallySortedList() {
		return this.sortedList();
	}
	//바로 앞 선행자 개수를 가지는 배열을 초기화한 후 그래프를 가져와 개수를 세고 저장하여 이를 출력하여 보여준다.
	private void initPredecessorCounts() {
		this.setPredecessorCounts(new int[this.graph().numberOfVertices()]);
		for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++){
			this.predecessorCounts()[tailVertex ] = 0;
		}
		for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++){
			Iterator<E> iterator = this.graph().neighborIteratorOf(tailVertex);
			while(iterator.hasNext()){
				Edge edge = (Edge) iterator.next();
				this.predecessorCounts()[edge.headVertex()]++;
			}
		}
		TopologicalSort.showDebugMessage("\n[Debug] 각 vertex의 초기 선행자 수는 다음과 같습니다:\n-->");
		for (int vertex = 0; vertex < this.graph().numberOfVertices(); vertex++){
			TopologicalSort.showDebugMessage(" ["+vertex+"] "+this.predecessorCounts()[vertex]);
		}
		TopologicalSort.showDebugMessage("\n");
	}
	//그래프에서 선행자가 없는 vertex들을 Stack에 넣어 이를 출력해준다.
	private void initZeroCountVertices() {
		this.setZeroCountVertices(new LinkedStack<Integer>());
		TopologicalSort.showDebugMessage(
				"\n[Debug] 그래프에 선행자가 없는 vertex들은 다음과 같습니다:\n --> (");
		for (int vertex = 0; vertex < this.graph().numberOfVertices(); vertex++){
			if(this.predecessorCounts()[vertex] == 0){
				this.zeroCountVertices().push(vertex);
				TopologicalSort.showDebugMessage(vertex + " ");
			}
		}
		TopologicalSort.showDebugMessage(")\n");
	}
	//디버깅 목적의 출력을 위한 함수이다. 바로 앞 선행자가 없는 vertex를 모아놓은 스택을 출력한다.
	private void showZeroCountVertices() {
		TopologicalSort.showDebugMessage("--> 스택: <Top>");
		Iterator<Integer> iterator = this.zeroCountVertices().iterator();
		
		while(iterator.hasNext()){
			int vertex = (Integer) iterator.next();
			TopologicalSort.showDebugMessage(" " + vertex);
		}
		TopologicalSort.showDebugMessage(" <Bottom>\n");
	}
	//주어진 그래프에서 대해 위상정렬을 실행한다. 처음에 앞선행자의수가 0인 vertex를 스택에 넣은 후 하나씩 pop하면서
	//push 후 그래프에서 다시한번 앞 선행자의수가 0인것을 push을 을 반복하며 정렬을 한다.
	public boolean solve(AdjacencyGraph<E> aGraph) {
		this.setGraph(aGraph);
		this.initPredecessorCounts();
		this.initZeroCountVertices();
		this.setSortedList(new ArrayList<Integer>(this.graph().numberOfVertices()));
		
		TopologicalSort.showDebugMessage("\n[Debug] 스택에 pop/push 되는 과정은 다읍과 같습니다:\n");
		this.showZeroCountVertices();
		while(! this.zeroCountVertices().isEmpty()){
			int tailVertex = this.zeroCountVertices().pop();
			TopologicalSort.showDebugMessage("--> Popped = " + tailVertex + ": Pushed = ( ");
			this.sortedList().add(tailVertex);
			Iterator<E> iterator = this.graph().neighborIteratorOf(tailVertex);
			while (iterator.hasNext()){
				Edge edge = (Edge) iterator.next();
				--this.predecessorCounts()[edge.headVertex()];
				if(this.predecessorCounts()[edge.headVertex()] == 0){
					this.zeroCountVertices().push(edge.headVertex());
					TopologicalSort.showDebugMessage(edge.headVertex()+" ");
				}
			}
			TopologicalSort.showDebugMessage(")\n");
			this.showZeroCountVertices();
		}
		return (this.sortedList().size() == this.graph().numberOfVertices());
	}	
}

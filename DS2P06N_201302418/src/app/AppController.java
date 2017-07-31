package app;


import graph.AdjacencyGraph;
import graph.DirectedAdjacencyMatrixGraph;
import graph.Edge;
import list.Iterator;
import list.List;
import topologicalSort.TopologicalSort;

public class AppController {
	 //private 변수
	 private AdjacencyGraph<Edge>   _graph;
	 private TopologicalSort<Edge>  _topologicalSort;
	 
	 //setter & getter
	 private AdjacencyGraph<Edge> graph(){
		 return this._graph;
	 }
	 private void setGraph(AdjacencyGraph<Edge> newGraph){
		 this._graph = newGraph;
	 }
	 private TopologicalSort<Edge> topologicalSort() {
		 return this._topologicalSort;
	 }
	 private void setTopologicalSort(TopologicalSort<Edge> newTopologicalSort) {
		 this._topologicalSort = newTopologicalSort;
	 }

	 //생성자
	 public AppController(){
		 this.setGraph(null);
		 this.setTopologicalSort(new TopologicalSort<Edge>());
	 }
	 
	   //사용자로부터 vertex수와 edge수 cost를 입력받고 edge를 생성하여 반환한다.
     private Edge inputEdge(){ 
    	 do {
    		 AppView.outputLine("- 입력할 edge의 두 vertex를 차례로 입력해야 합니다.");
    		 int tailVertex = AppView.inputTailVertex();
    		 int headVertex = AppView.inputHeadVertex();
    		 if(this.graph().vertexDoesExist(tailVertex) && this.graph().vertexDoesExist(headVertex)){
    			 if(tailVertex == headVertex){
    				 AppView.outputLine("[오류] 두 vertex 번호가 동일합니다.");
    			 }
    			 else {
    				 return (new Edge(tailVertex, headVertex));
    			 }
    		 }
    		 else {
    			 if (! this.graph().vertexDoesExist(tailVertex)){
    				 AppView.outputLine("[오류] 존재하지 않는 tail vertex 입니다:" + tailVertex);
    			 }
    			 if (! this.graph().vertexDoesExist(headVertex)){
    				 AppView.outputLine("[오류] 존재하지 않는  head vertex 입니다:" + headVertex);
    			 }
    		 }		 
    	 } while(true);
     }
     //vertex의 수를 입력받으며 유효한 값인지 검사한다.
     private int inputNumberOfVertices(){ 
    	 int numberOfVertices = AppView.inputNumberOfVertices();
    	 while (numberOfVertices <=0){
    		 AppView.outputLine("[오류] vertex 수는 0보다 커야 합니다: "+numberOfVertices);
    		 numberOfVertices = AppView.inputNumberOfVertices();
    	 }
    	 return numberOfVertices;
     }
     //edge의 수를 입력받으며 유효한 값인지 검사한다.
     private int inputNumberOfEdges(){ 
    	 int numberOfEdges = AppView.inputNumberOfEdges();
    	 while (numberOfEdges < 0){
    		 AppView.outputLine("[오류] edge 수는 0보다 크거나 같아야 합니다: "+ numberOfEdges);
    		 numberOfEdges = AppView.inputNumberOfEdges();
    	 }
    	 return numberOfEdges;
     }

     
    //vertex와 edge의 수를 입력받은 후 그래프를 생성하고 edge를 주어진 수 만큼 입력한다.
	private void inputAndMakeGraph(){ 
    	 AppView.outputLine("> 입력할 그래프의 vertex 수와 edge 수를 먼저 입력해야 합니다: ");
    	 int numberOfVertices = this.inputNumberOfVertices();
    	 this.setGraph(new DirectedAdjacencyMatrixGraph<Edge>(numberOfVertices));
    	 
    	 int numberOfEdges = this.inputNumberOfEdges();
    	 AppView.outputLine("");
    	 AppView.outputLine("> 이제부터 edge를 주어진 수 만큼 입력합니다.");
    	 
    	 int edgeCount = 0;
    	 while(edgeCount<numberOfEdges){
    		 Edge edge = this.inputEdge();
    		 if(this.graph().edgeDoesExist(edge)){
    			 AppView.outputLine("[오류] 입력된 edge (" + edge.tailVertex()+", "+edge.headVertex()+
    					 ")는 그래프에 이미 존재합니다.");
    		 }
    		 else{
    			 edgeCount++;
    			 this.graph().addEdge(edge);
    			 AppView.outputLine("!새로운 edge (" +
    			     edge.tailVertex() + "," + edge.headVertex()+
    			     ") 가 그래프에 삽입되었습니다.");   			
    		 }
    	 }
     }
	
	 // 만들어진 그래프를 보여준다.
     private void showGraph(){ 
    	 AppView.outputLine("");
    	 AppView.outputLine("> 입력된 그래프는 다음과 같습니다: ");
    	 for (int tailVertex = 0; tailVertex < this.graph().numberOfVertices(); tailVertex++){
    		 AppView.output("["+tailVertex+"] ->");
    		 Iterator<Edge> neighborIterator =
    				 this.graph().neighborIteratorOf(tailVertex);
    		 while(neighborIterator.hasNext()){
    			 Edge nextEdge = neighborIterator.next();
    			 AppView.output(" "+nextEdge.headVertex());
    		 }
    		 AppView.outputLine("");
    	 }
     }

     private void showSortedResult() {
    	 AppView.outputLine("");
    	 AppView.outputLine("> 위상정렬의 결과는 다음과 같습니다: ");
    	 List<Integer> topologicallySortedList = this.topologicalSort().topologicallySortedList();
    	 Iterator<Integer> iterator = topologicallySortedList.iterator();
    	 while(iterator.hasNext()){
    		 int vertex = iterator.next();
    		 AppView.output("-> "+vertex+" ");
    	 }
    	 AppView.outputLine("");
     }
     
     //프로그램을 실행한다.
     public void run(){
    	 AppView.outputLine("<<< 위상정렬 프로그램을 시작합니다 >>>");
    	 this.inputAndMakeGraph();
    	 this.showGraph();
    	 if(this.topologicalSort().solve(this.graph()) ) {
    		 this.showSortedResult();
    	 }
    	 else {
    		 AppView.outputLine("");
    		 AppView.outputLine("[오류] 위상정렬을 성공적으로 마치지 못했습니다. 그래프에 사이클이 존재합니다.");
    	 }
    	 
    	 AppView.outputLine("");
    	 AppView.outputLine("<<< 위상정렬 프로그램을 종료합니다 >>>");

     }
}

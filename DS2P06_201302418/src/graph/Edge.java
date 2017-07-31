package graph;
public class Edge {
     private int _tailVertex;
     private int _headVertex;
     
     //생성자
     public Edge(){ 
    	 
     }
     public Edge(int givenTailVertex, int givenHeadVertex){
    	 this.setTailVertex(givenTailVertex);
    	 this.setHeadVertex(givenHeadVertex); // 내부에서도 이렇게 더욱 캡슐화하자
     }
     
     //tailvertex의 setter
     public void setTailVertex(int newTailVertex){ 
    	 this._tailVertex = newTailVertex;
     }
     //tailvertex의 getter
     public int tailVertex(){ 
    	 return this._tailVertex;
     }
     //headvertex의 setter
     public void setHeadVertex(int newHeadVertex){
    	 this._headVertex = newHeadVertex;
     }
     //headvertex의 getter
     public int headVertex(){ 
    	 return this._headVertex;
     }
}

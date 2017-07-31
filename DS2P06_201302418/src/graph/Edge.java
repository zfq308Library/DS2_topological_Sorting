package graph;
public class Edge {
     private int _tailVertex;
     private int _headVertex;
     
     //������
     public Edge(){ 
    	 
     }
     public Edge(int givenTailVertex, int givenHeadVertex){
    	 this.setTailVertex(givenTailVertex);
    	 this.setHeadVertex(givenHeadVertex); // ���ο����� �̷��� ���� ĸ��ȭ����
     }
     
     //tailvertex�� setter
     public void setTailVertex(int newTailVertex){ 
    	 this._tailVertex = newTailVertex;
     }
     //tailvertex�� getter
     public int tailVertex(){ 
    	 return this._tailVertex;
     }
     //headvertex�� setter
     public void setHeadVertex(int newHeadVertex){
    	 this._headVertex = newHeadVertex;
     }
     //headvertex�� getter
     public int headVertex(){ 
    	 return this._headVertex;
     }
}

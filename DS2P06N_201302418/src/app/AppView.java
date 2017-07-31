package app;
import java.util.Scanner;

public final class AppView {
     private static Scanner scanner = new Scanner(System.in);
     
     //������
     private AppView(){
    	 
     }
     
     //����Լ�
     public static void outputLine(String aString){
    	 System.out.println(aString);
     }
     
     public static void output(String aString){
    	 System.out.print(aString);
     }
     public static void outputDebugMessage(String aString){
    	 System.out.print(aString);
     }
     
     //����ڷκ��� vertex�� ���� �Է¹޴´�.
     public static int inputNumberOfVertices(){ 
    	 int numberOfVertices;
    	 String scannedToken;
    	 while(true){
    		 AppView.output("? Vertex ���� �Է��Ͻÿ� : ");
    		 scannedToken = AppView.scanner.next();
    		 try{ //�޾ƿ� ���ڿ��� Interger�� �̿��Ͽ� int������ �ٲپ��ش�. try���� �������߻��ϸ� ��, �޾ƿ¹��ڿ��� ���ڰ��ƴ϶�� int�������� ��������ʾ� ������ �߻��Ѵ�.
    			 numberOfVertices = Integer.parseInt(scannedToken);
    			 return numberOfVertices;
    		 }
    		 catch(NumberFormatException e){
    			 AppView.outputLine("(����) Vertex �� �Է¿� ������ �ֽ��ϴ�: "+scannedToken);
    		 }
    	 }
     }
    //����ڷκ��� edge�� ���� �Է¹޴´�.
     public static int inputNumberOfEdges(){   
    	 int numberOfEdges;
    	 String scannedToken;
    	 while(true){
    		 AppView.output("? edge ���� �Է��Ͻÿ�: ");
    		 scannedToken = AppView.scanner.next();
    		 try{ //�޾ƿ� ���ڿ��� Interger�� �̿��Ͽ� int������ �ٲپ��ش�. try���� �������߻��ϸ� ��, �޾ƿ¹��ڿ��� ���ڰ��ƴ϶�� int�������� ��������ʾ� ������ �߻��Ѵ�.
    			 numberOfEdges = Integer.parseInt(scannedToken);
    			 return numberOfEdges;
    		 }
    		 catch(NumberFormatException e){
    			 AppView.outputLine("(����) edge �� �Է¿� ������ �ֽ��ϴ�: "+scannedToken);
    		 }
    	 }
     }
     //����ڷκ��� cost�� �Է¹޴´�.
     public static int inputCost(){
    	 int cost;
    	 String scannedToken;
    	 while(true){
    		 AppView.output("? cost�� �Է��Ͻÿ� :");
    		 scannedToken = AppView.scanner.next();
    		 try { //�޾ƿ� ���ڿ��� Interger�� �̿��Ͽ� int������ �ٲپ��ش�. try���� �������߻��ϸ� ��, �޾ƿ¹��ڿ��� ���ڰ��ƴ϶�� int�������� ��������ʾ� ������ �߻��Ѵ�.
    			 cost = Integer.parseInt(scannedToken);
    			 return cost;
    		 }
    		 catch (NumberFormatException e){
    			 AppView.outputLine("[����] cost�Է¿� ������ �ֽ��ϴ�: "+scannedToken);
    		 }
    	 }
     }
     //����ڷκ��� tailvertex�� �Է¹޴´�.
     public static int inputTailVertex(){ 
    	 int tailVertex;
    	 String scannedToken;
    	 while(true){
    		 AppView.output("? tail vertex �� �Է��Ͻÿ�: ");
    		 scannedToken = AppView.scanner.next();
    		 try{ //�޾ƿ� ���ڿ��� Interger�� �̿��Ͽ� int������ �ٲپ��ش�. try���� �������߻��ϸ� ��, �޾ƿ¹��ڿ��� ���ڰ��ƴ϶�� int�������� ��������ʾ� ������ �߻��Ѵ�.
    		     tailVertex = Integer.parseInt(scannedToken);
    		     return tailVertex;
    		 }
    		 catch(NumberFormatException e){
    			 AppView.outputLine("(����) tail vertex �Է¿� ������ �ֽ��ϴ�: "+scannedToken);
    		 }
    	 }
     }
     //����ڷκ��� headvertex�� �Է¹޴´�.
     public static int inputHeadVertex(){ 
    	 int headVertex;
    	 String scannedToken;
    	 while(true){
    		 AppView.output("? head vertex �� �Է��Ͻÿ�: ");
    		 scannedToken = AppView.scanner.next();
    		 try{ //�޾ƿ� ���ڿ��� Interger�� �̿��Ͽ� int������ �ٲپ��ش�. try���� �������߻��ϸ� ��, �޾ƿ¹��ڿ��� ���ڰ��ƴ϶�� int�������� ��������ʾ� ������ �߻��Ѵ�.
    		     headVertex = Integer.parseInt(scannedToken);
    		     return headVertex;
    		 }
    		 catch(NumberFormatException e){
    			 AppView.outputLine("(����) head vertex �Է¿� ������ �ֽ��ϴ�: "+scannedToken);
    		 }
    	 }
     }
     //����ڷκ��� ������� �Է¹޴´�.
     public static int inputSourceVertex() {
    	 int sourceVertex;
    	 String scannedToken;
    	 while(true) {
    		 AppView.output("? ��� vertex�� �Է��Ͻÿ�: ");
    		 scannedToken = AppView.scanner.next();
    		 try{//�޾ƿ� ���ڿ��� Interger�� �̿��Ͽ� int������ �ٲپ��ش�. try���� �������߻��ϸ� ��, �޾ƿ¹��ڿ��� ���ڰ��ƴ϶�� int�������� ��������ʾ� ������ �߻��Ѵ�.
    			 sourceVertex = Integer.parseInt(scannedToken);
    			 return sourceVertex;
    		 }
    		 catch(NumberFormatException e){
    			 AppView.outputLine("[����] ��� vertex �Է¿� ������ �ֽ��ϴ�: "+scannedToken);
    		 }
    	 }
     }
}

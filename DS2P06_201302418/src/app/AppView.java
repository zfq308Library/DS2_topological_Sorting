package app;
import java.util.Scanner;

public final class AppView {
     private static Scanner scanner = new Scanner(System.in);
     
     //생성자
     private AppView(){
    	 
     }
     
     //출력함수
     public static void outputLine(String aString){
    	 System.out.println(aString);
     }
     
     public static void output(String aString){
    	 System.out.print(aString);
     }
     public static void outputDebugMessage(String aString){
    	 System.out.print(aString);
     }
     
     //사용자로부터 vertex의 수를 입력받는다.
     public static int inputNumberOfVertices(){ 
    	 int numberOfVertices;
    	 String scannedToken;
    	 while(true){
    		 AppView.output("? Vertex 수를 입력하시오 : ");
    		 scannedToken = AppView.scanner.next();
    		 try{ //받아온 문자열을 Interger를 이용하여 int형으로 바꾸어준다. try동안 오류가발생하면 즉, 받아온문자열이 숫자가아니라면 int형변수에 저장되지않아 오류가 발생한다.
    			 numberOfVertices = Integer.parseInt(scannedToken);
    			 return numberOfVertices;
    		 }
    		 catch(NumberFormatException e){
    			 AppView.outputLine("(오류) Vertex 수 입력에 오류가 있습니다: "+scannedToken);
    		 }
    	 }
     }
    //사용자로부터 edge의 수를 입력받는다.
     public static int inputNumberOfEdges(){   
    	 int numberOfEdges;
    	 String scannedToken;
    	 while(true){
    		 AppView.output("? edge 수를 입력하시오: ");
    		 scannedToken = AppView.scanner.next();
    		 try{ //받아온 문자열을 Interger를 이용하여 int형으로 바꾸어준다. try동안 오류가발생하면 즉, 받아온문자열이 숫자가아니라면 int형변수에 저장되지않아 오류가 발생한다.
    			 numberOfEdges = Integer.parseInt(scannedToken);
    			 return numberOfEdges;
    		 }
    		 catch(NumberFormatException e){
    			 AppView.outputLine("(오류) edge 수 입력에 오류가 있습니다: "+scannedToken);
    		 }
    	 }
     }

     //사용자로부터 tailvertex를 입력받는다.
     public static int inputTailVertex(){ 
    	 int tailVertex;
    	 String scannedToken;
    	 while(true){
    		 AppView.output("? tail vertex 를 입력하시오: ");
    		 scannedToken = AppView.scanner.next();
    		 try{ //받아온 문자열을 Interger를 이용하여 int형으로 바꾸어준다. try동안 오류가발생하면 즉, 받아온문자열이 숫자가아니라면 int형변수에 저장되지않아 오류가 발생한다.
    		     tailVertex = Integer.parseInt(scannedToken);
    		     return tailVertex;
    		 }
    		 catch(NumberFormatException e){
    			 AppView.outputLine("(오류) tail vertex 입력에 오류가 있습니다: "+scannedToken);
    		 }
    	 }
     }
     //사용자로부터 headvertex를 입력받는다.
     public static int inputHeadVertex(){ 
    	 int headVertex;
    	 String scannedToken;
    	 while(true){
    		 AppView.output("? head vertex 를 입력하시오: ");
    		 scannedToken = AppView.scanner.next();
    		 try{ //받아온 문자열을 Interger를 이용하여 int형으로 바꾸어준다. try동안 오류가발생하면 즉, 받아온문자열이 숫자가아니라면 int형변수에 저장되지않아 오류가 발생한다.
    		     headVertex = Integer.parseInt(scannedToken);
    		     return headVertex;
    		 }
    		 catch(NumberFormatException e){
    			 AppView.outputLine("(오류) head vertex 입력에 오류가 있습니다: "+scannedToken);
    		 }
    	 }
     }
}

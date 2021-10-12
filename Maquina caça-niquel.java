import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class No{

	No next;
	String data;
    
    public No(String data){
        this.data = data;
    }
    
    public No(String data, No next){
        this.data = data;
        this.next = next;
    }
  

}

class ListaCircular {
    protected No Strings;
    private int quantidade;
    
    public boolean isEmpty(){
        return quantidade==0;
    }
  
	public ListaCircular() {
        this.quantidade=0;
	}
  	
	public void inserir(String valor) {
        if(isEmpty()){
            Strings = new No(valor);
            Strings.next = Strings;
        }else{
            No temp = Strings;
            while(temp.next != Strings){
                temp = temp.next;
            }
            temp.next = new No(valor, Strings);
        }
        quantidade++;
	}
  
	
	
}

class Result {
  
  	static ListaCircular lc1 = new ListaCircular();
	static ListaCircular lc2 = new ListaCircular();
	static ListaCircular lc3 = new ListaCircular();
  
  	public static void preencherTambores(String[] tambor1,String[] tambor2,String[] tambor3){
		for(int i=0;i<tambor1.length;i++){
            lc1.inserir(tambor1[i]);
        }
        for(int j=0;j<tambor2.length;j++){
            lc2.inserir(tambor2[j]);
        }
        for(int k=0;k<tambor3.length;k++){
            lc3.inserir(tambor3[k]);
        }
	}

    public static String girarTambores(int nDesloc1, int nDesloc2, int nDesloc3) {
      String resultado = "";
        for(int i=0;i<nDesloc1;i++){
            lc1.Strings = lc1.Strings.next;
        }
        for(int j=0;j<nDesloc2;j++){
            lc2.Strings = lc2.Strings.next;
        }
        for(int k=0;k<nDesloc3;k++){
            lc3.Strings = lc3.Strings.next;
        }
        resultado= lc1.Strings.data + " " + lc2.Strings.data + " " + lc3.Strings.data;
      
      return resultado;

    }

}

public class Solution {
  	
  
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] tambor1 = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        String[] tambor2 = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        String[] tambor3 = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
    
        //Preenche os tambores do calça niquel
        Result.preencherTambores(tambor1,tambor2,tambor3);
      
      

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int numDesloc1 = Integer.parseInt(firstMultipleInput[0]);

        int numDesloc2 = Integer.parseInt(firstMultipleInput[1]);

        int numDesloc3 = Integer.parseInt(firstMultipleInput[2]);

        String resultado = Result.girarTambores(numDesloc1, numDesloc2, numDesloc3);
      
      	bufferedWriter.write(resultado);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

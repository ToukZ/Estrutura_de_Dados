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

class Node{
    protected Node next;
    
    protected int divisor;
    
    public Node(int divisor){
        this.divisor = divisor;
    }
}

class Fila{
    protected int quantidade;
    
    protected Node node;
    
    public boolean isEmpty(){
        return quantidade == 0;
    }
    
    public void enqueue(int divisor){
        if(isEmpty()){
            node = new Node(divisor);
        }else{
            Node temp = node;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = new Node(divisor);
        }
        quantidade++;
    }
    
    public int dequeue(){
        int r = node.divisor;
        node = node.next;
        return r;
    }
    public Fila(){
        this.quantidade = 0;
    }
}

class Result {
    protected static Fila fila = new Fila();
    
    public static int mdc(int number1, int number2) {
        for(int i=2;i<=number1 && i<=number2;i++){
            if(number1%i==0 && number2%i==0){
                fila.enqueue(i);
            }
        }
        if(fila.node == null){
            return 1;
        }else{
            while(fila.node.next!=null){
                fila.node = fila.node.next;
            }
            return fila.node.divisor;
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int num1 = Integer.parseInt(bufferedReader.readLine().trim());

        int num2 = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.mdc(num1, num2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
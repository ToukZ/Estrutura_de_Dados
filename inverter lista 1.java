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

class Queue {
    protected Queue next;
    
    protected String value;
    
    public Queue(String value){
        this.value = value;
    }
}

class Result {
    protected Queue fila;
    
    private int quantidade = 0;
    
    public Result(){
    }
    
    public boolean isEmpty(){
        return quantidade==0;
    }
    
    public void enqueue(String value){
        if(isEmpty()){
            fila = new Queue(value);
        }else{
            Queue temp = fila;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = new Queue(value);
        }
        quantidade++;
    }
    
    public String dequeue(){
        String r = "";
        r = fila.value;
        fila = fila.next;
        quantidade--;
        return r;
    }
    
    public Queue invertQueue(String input) {
        String[] inputs = input.split(" ",0);
        for(int k=0;k<inputs.length;k++){
            if(isEmpty()){
                enqueue(inputs[k]);
            }else{
                enqueue(inputs[k]);
                for(int i=1;i<quantidade;i++){
                    enqueue(dequeue());
                }
            }
        }
        return fila;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")))) {
                String input = bufferedReader.readLine();
                Result queue = new Result();
                queue.fila = queue.invertQueue(input.trim());
                String result = "";
                while(!queue.isEmpty())
                    result += queue.dequeue() + " ";
                bufferedWriter.write(result.trim());
                bufferedWriter.newLine();
            }
        }
    }
}
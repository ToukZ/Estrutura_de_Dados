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


class Fila<T>{
    private int size[];
    
    private int first;
    
    private int last;
    
    private int quantidade;
    
    public boolean isEmpty(){
        return quantidade==0;
    }
    
    public boolean isFull(){
        return quantidade==size.length;
    }
    
    public Fila(int size){
        this.size = new int[size];
        this.quantidade = 0;
        this.first = 0;
        this.last = 0;
    }
    
    public int remover(){
        int r = size[first];
        first = (first+1)%size.length;
        quantidade--;
        return r;
    }
    
    public boolean inserir(int e){
        if(!isFull()){ 
            if(isEmpty()){
                size[quantidade] = e;
            }else{
                last = (last+1)%size.length;
                size[last] = e;
            }
            quantidade++;
            return true;
        }else{
            return false;
        }
    }
    
    public int peek(){           
        return size[first];
    }
    
    public String imprimirFila(){
        String r = "";
        for(int i=0;i<quantidade;i++){
            r += size[(first+i)%size.length] + " ";
        }
        return r;
    }
    
}

class Result {

    public static void parseListElements(List<String> operationsList) {
            Fila queue = new Fila(4);
            
            
            for(Iterator<String> iter = operationsList.iterator();
                    iter.hasNext();) {
                
                String s = iter.next();
                
//                match int inside parenthesis
                Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(s);
                Integer elem = null;
                
                if(m.find()) {
                    elem = Integer.parseInt(m.group(1));
                }
                        
                String op = s.substring(0, 3);
                
                switch (op) {
                    case "add":
                        if(queue.inserir(elem))
                            System.out.println(queue.imprimirFila());
                        break;
                    
                    case "pee":
                        if(!queue.isEmpty()){
                            System.out.println(queue.peek());
                        }else{
                            System.out.println("null");
                        }
                        break;
                        
                    case "rem":
                        if(!queue.isEmpty()){
                            System.out.println(queue.remover());
                        }else{
                            System.out.println("null");
                        }
                        break;
                        
                    default:
                        break;
                }
            }

    }

}
public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<String> operationsList = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .collect(toList());

        Result.parseListElements(operationsList);

        bufferedReader.close();
    }
}
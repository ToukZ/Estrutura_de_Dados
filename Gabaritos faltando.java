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
class NoStack{
    protected NoStack next;
    private char value;
    
    public NoStack(char value){
        this.value = value;
    }
    
    public char getValue(){
        return value;
    }
}
class Stack{
    protected NoStack stack;
    private int quantidade;
    
    public Stack(){
        this.quantidade = 0;
    }
    
    public int size(){
        return quantidade;
    }
    
    public void push(char value){
        if(size()==0){
            stack = new NoStack(value);
        }else{
            NoStack temp = stack;
            stack = new NoStack(value);
            stack.next = temp;
        }
        quantidade++;
    }
    public char peek(){
        return stack.getValue();
    }
}
class Result {
    public static boolean isPalindrome(String phrase) {
        Boolean r = true;
        Stack pilha = new Stack();
        Stack pilhaReversa = new Stack();
        char[] values = phrase.toLowerCase().toCharArray();
        for(int i=0;i<values.length;i++){
            if(values[i]!=' '){
                pilha.push(values[i]);
            }
            if(values[values.length-i-1]!=' '){
                pilhaReversa.push(values[values.length-i-1]);
            }
        }
        if(pilha.size()!=pilhaReversa.size()){
            r = false;
        }else{
            while(pilha.stack!=null || pilhaReversa.stack!=null){
                if(pilha.peek()!=pilhaReversa.peek()){
                    r = false;
                    break;
                }
                pilha.stack = pilha.stack.next;
                pilhaReversa.stack = pilhaReversa.stack.next;
            }
        }
        return r;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String inputValue = bufferedReader.readLine();

        boolean result = Result.isPalindrome(inputValue);

        bufferedWriter.write(String.valueOf(result ? 1 : 0));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

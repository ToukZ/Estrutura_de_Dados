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
    private String value;
    
    public NoStack(String value){
        this.value = value;
    }
    
    public String getValue(){
        return value;
    }
}
class Stack {
    private int quantidade;
    private NoStack stack;
    
    public NoStack getStack(){
        return this.stack;
    }
    
    public int size(){
        return quantidade;
    }
    
	public void push(String inputValue){
        if(size()==0){
            stack = new NoStack(inputValue);
        }else{
            NoStack temp = new NoStack(inputValue);
            temp.next = stack;
            stack = temp;
        }
        quantidade++;
    }
	public String pop(){
        String r = stack.getValue();
        stack = stack.next;
        quantidade--;
        return r;
    }
	public String peek(){
        return stack.getValue();
    }    
	public String toString(){
        String r="";
        while(stack!=null){
            r += stack.getValue() + " ";
            stack = stack.next;
        }
        return r;
    }
    
    public Stack(){
        this.quantidade = 0;
    }
}

class Result {

    public static Stack createStack(String phrase) {
		Stack rStack = new Stack();
		String[] inputs = phrase.split(" ");
		for(int i=inputs.length-1; i>=0; i--) {
			rStack.push(inputs[i]);
		}
		return rStack;
    }

    public static void joinStacks(Stack stackA, Stack stackB) {
        NoStack temp = stackA.getStack();
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = stackB.getStack();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        Stack stackA = Result.createStack(bufferedReader.readLine());
        Stack stackB = Result.createStack(bufferedReader.readLine());
		
		Result.joinStacks(stackA,stackB);
      
        bufferedWriter.write(stackA.toString());
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

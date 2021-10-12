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

class NoPilha{
    protected NoPilha next;
    private char ch;
    
    public NoPilha(char ch){
        this.ch=ch;
    }
    
    public char getCh(){
        return this.ch;
    }
}

class Pilha{
    protected NoPilha pilha;
    protected int quantidade;
    protected int quantidadeParenteses;
    protected int quantidadeChaves;
    protected int quantidadeColchetes;
    
    public Pilha(){
        this.quantidade = 0;
        this.quantidadeParenteses = 0;
        this.quantidadeColchetes = 0;
        this.quantidadeChaves = 0;
    } 
    public int size(){
        return quantidade;
    }
    
    public Boolean push(char ch){
        Boolean r = true;
        if(size()==0){
            pilha = new NoPilha(ch);
        }else{
            NoPilha temp = pilha;
            pilha = new NoPilha(ch);
            pilha.next = temp;
        }
        switch(ch){
                case '(':
                    quantidadeParenteses++;
                    break;
                case ')':
                    quantidadeParenteses--;
                    if(quantidadeParenteses<0){
                        r = false;
                    }
                    break;
                case '[':
                    quantidadeColchetes++;
                    break;
                case ']':
                    quantidadeColchetes--;
                    if(quantidadeColchetes<0){
                        r = false;
                    }
                    break;
                case '{':
                    quantidadeChaves++;
                    break;
                case '}':
                    quantidadeChaves--;
                    if(quantidadeChaves<0){
                        r = false;
                    }
                    break;
                default:
                    break;
            }
        quantidade++;
        return r;
    }
    public char peek(){
        return pilha.getCh();
    }
}
class Result {
    static Pilha pilha = new Pilha();

    public static boolean contBrackets(String input) {
        Boolean r = true;
        char[] inputs = input.trim().toCharArray();
        for(int i=0; i<inputs.length;i++){
            if(!pilha.push(inputs[i])){
                r = false;
            }
        }
        if(pilha.quantidadeParenteses>0 || pilha.quantidadeColchetes>0 || pilha.quantidadeChaves>0){
            r = false;
        }
        return r;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String inputString = bufferedReader.readLine();

        boolean result = Result.contBrackets(inputString);

        bufferedWriter.write(String.valueOf(result ? 1 : 0));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

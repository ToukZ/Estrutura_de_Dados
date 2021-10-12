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
    private String frase;
    
    public NoPilha(String frase){
        this.frase=frase;
    }
    
    public String getFrase(){
        return this.frase;
    }
}

class Pilha{
    protected NoPilha pilha;
    private int quantidade;
    
    public int size(){
        return quantidade;
    }
    
    public void push(String frase){
        if(size()==0){
            pilha = new NoPilha(frase);
        }else{
            NoPilha temp = pilha;
            pilha = new NoPilha(frase);
            pilha.next = temp;
        }
        quantidade++;
    }
    public String peek(){
        return pilha.getFrase();
    }
    public String pop(){
        String r = pilha.getFrase();
        pilha = pilha.next;
        quantidade--;
        return r;
    }
}

public class Solution {    
    static Pilha pilha = new Pilha();
    static Boolean quemAchouQueTratarDasAspasEraUmaBoaIdeia = false;
    
    public static void inserir(String t){
        quemAchouQueTratarDasAspasEraUmaBoaIdeia = t.contains("\"");
        String r = t;
        if(pilha.size()!=0){
            if(quemAchouQueTratarDasAspasEraUmaBoaIdeia){
            r = "\"" + pilha.pilha.getFrase().replaceAll("\"","") + t.replaceFirst("\"","");
            }else{
                r = pilha.pilha.getFrase() + t;
            }
        }
        pilha.push(r);
    }
    
    public static void alteracoes(){
        NoPilha temp = pilha.pilha;
        System.out.print("Topo");
        while(temp!=null){
            System.out.print("->" + temp.getFrase());
            temp = temp.next;
        }
        System.out.println();
    }
    
    public static void desfazer(){
        String r = pilha.pop();
    }
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String a = bufferedReader.readLine();
        String b = bufferedReader.readLine();
        String c = bufferedReader.readLine();
    
        Solution.inserir(a);
        Solution.alteracoes();
        Solution.inserir(b);
        Solution.alteracoes();
        Solution.inserir(c);
        Solution.alteracoes();
        Solution.desfazer();
        Solution.alteracoes();
        bufferedReader.close();
    }
}
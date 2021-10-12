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
    protected int valor[];
    protected int quantidade;
    protected int max;
    
    public Node(){
        this.max = 10;
        int valor[] = new int[max];
        this.valor = valor;
        this.quantidade = 0;
    }
    
}

class Lista{
    private Node first = new Node();
    
    public boolean isEmpty(){
        return first.quantidade == 0;
    }
    public boolean isFull(){
        return first.quantidade == first.max-1;
    }
    public boolean consultar(int val){
        if(isEmpty()){
            return false;
        }else{
            boolean r = false;
            for(int i=0;i<first.quantidade;i++){
                if(val == first.valor[i]){
                    r = true;
                    break;
                }
            }
            return r;
        }
    }
    public void adicionar(int val){
        if(!consultar(val)){
            if(!isFull()){
                first.valor[first.quantidade++] = val;
            }else{
                first.max*=2;
                int temp[] = new int[first.max];
                for(int i=0;i<first.quantidade;i++){
                    temp[i] = first.valor[i];
                }
                first.valor = temp;
                first.valor[first.quantidade++] = val;
            }
        }
    }
    public String print(){
        String saida = "";
        for(int i=0;i<first.quantidade;i++){
            saida += first.valor[i] + " ";
        }
        return saida;
    }
}

class Result{

    public static String uniaoConjuntos(String conj1, String conj2) {
        Lista atual = new Lista();
        char[] cj1 = conj1.toCharArray();
        char[] cj2 = conj2.toCharArray();
        String colocarNaLista = "";
        
        for(int i=0;i<conj1.length();i++){
            if(cj1[i] != ' '){
                colocarNaLista += cj1[i];
                if(i == conj1.length()-1){
                    atual.adicionar(Integer.valueOf(colocarNaLista));
                }
            }else{
                atual.adicionar(Integer.valueOf(colocarNaLista));
                colocarNaLista = "";
                }
            }
        colocarNaLista = "";
        for(int i=0;i<conj2.length();i++){
            if(cj2[i] != ' '){
                colocarNaLista += cj2[i];
                if(i == conj2.length()-1){
                    atual.adicionar(Integer.valueOf(colocarNaLista));
                }
            }else{
                atual.adicionar(Integer.valueOf(colocarNaLista));
                colocarNaLista = "";
                }
            }
        return atual.print();
        }
    }


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String conjunto1 = bufferedReader.readLine();

        String conjunto2 = bufferedReader.readLine();

        String result = Result.uniaoConjuntos(conjunto1, conjunto2);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
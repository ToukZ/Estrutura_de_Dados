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
class Pilha{
    private int caixa;
    protected Pilha next;
    public Pilha(int caixa){
        this.caixa = caixa;
    }
    
    public int getCaixa(){
        return this.caixa;
    }
}
class Carro{
    private Pilha pilha;
    private int quantidade;
    private int capacity;
    public Carro(int capacity){
        this.quantidade = 0;
        this.capacity = capacity;
    }
    
    public int size(){
        return quantidade;
    } 
    public Boolean isEmpty(){
        return size()==0;
    }  
    public Boolean isFull(){
        return size()==capacity;
    }
    public void push(int box){
        if(isEmpty()){
            pilha = new Pilha(box);
        }else{
            Pilha temp = pilha;
            pilha = new Pilha(box);
            pilha.next = temp;
        }
        quantidade++;
    }
    public int pop(){
        int r = pilha.getCaixa();
        pilha = pilha.next;
        quantidade--;
        return r;
    }
}

class Result {
    private static Carro carro; // possuímos apenas um carro
    private static int boxes = 1; // º da proxima caixa a ser pegue
    public static String operation(int capacity, int numberOfBoxs) {
        //inicio de uma viagem
        if(numberOfBoxs<=0){
            //se nao houver mais caixas a se pegar, nao precisa ser feita a viagem.
            return "";
        }else{
            String temp = ""; // ordenação temporaria do armazém.
            carro = new Carro(capacity); // o carro possui a capacidade dada na questão.
            for(int i=0;i<numberOfBoxs&&i<capacity;i++){ // os trabalhadores empilham as caixas no carro.
                carro.push(boxes++);
            }
            while(!carro.isEmpty()){ // os trabalhadores chegam no armazém.
                temp = String.valueOf(carro.pop()) + "\n" + temp;// os trabalhadores retiram do carro e empilham no armazém.
            } 
            return operation(capacity,numberOfBoxs-capacity) + temp; // outra viagem é requisitada para continuar a ordenação no armazém.
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int capacity = Integer.parseInt(bufferedReader.readLine().trim());

        int numberOfBoxs = Integer.parseInt(bufferedReader.readLine().trim());

        String result = Result.operation(capacity, numberOfBoxs);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

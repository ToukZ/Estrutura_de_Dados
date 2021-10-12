import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

class NoLista{
    int valor;
    
    NoLista proximo;
    
    NoLista ultimo;
    
    NoLista(int valor){
        this.valor = valor;
    }
    
    NoLista(int valor, NoLista ultimo){
        this.valor = valor;
        this.ultimo = ultimo;
    }
}

class ListaDinamica{
    private NoLista valores;
    private int quantidade;
    
    public boolean isEmpty(){
        return quantidade==0;
    }
    boolean consultar(int valor, NoLista tempo){
        boolean r = false;
        NoLista temp = tempo;
        while(temp!=null){
            if(temp.valor == valor){
                r=true;
                break;
            }else{
                temp = temp.proximo;
            }
        }
        return r;
    }
    public void add(int valor){
        if(isEmpty()){
            valores = new NoLista(valor);
        }else{
            NoLista temp = valores;
            while(temp.proximo!=null){
                temp = temp.proximo;
            }
            temp.proximo = new NoLista(valor, temp);
        }
        quantidade++;
    }
    public void add(int valor, NoLista tempo){
            NoLista temp = tempo;
            while(temp.proximo!=null){
                temp = temp.proximo;
            }
            temp.proximo = new NoLista(valor, temp);
            quantidade++;
    }

    public void print(){
        NoLista temp = valores;
        while(temp.proximo!=null){
            temp = temp.proximo;
            if(temp.proximo == null){
                NoLista invertida = new NoLista(temp.valor);
                while(temp!=null){
                    if(!consultar(temp.valor,invertida)){
                        add(temp.valor,invertida);
                    }
                    temp = temp.ultimo;
                }
                while(invertida!=null){
                    System.out.print(invertida.valor + " ");
                    invertida = invertida.proximo;
                }
                break;
            }
        }
    }
    
    public ListaDinamica listaNova() {
        return this;
    }
}

public class Solution{
    public static void main(String[] args) throws IOException{
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(bufferedReader.readLine().trim());

        int b = Integer.parseInt(bufferedReader.readLine().trim());

        int c = Integer.parseInt(bufferedReader.readLine().trim());

        int d = Integer.parseInt(bufferedReader.readLine().trim());

        int e = Integer.parseInt(bufferedReader.readLine().trim());

        int f = Integer.parseInt(bufferedReader.readLine().trim());

        int g = Integer.parseInt(bufferedReader.readLine().trim());
        
        ListaDinamica lista = new ListaDinamica();
        //adicionar os valores na lista
        lista.add(a);
        lista.add(b);
        lista.add(c);
        lista.add(d);
        lista.add(e);
        lista.add(f);
        lista.add(g);
        
        //printar nova lista
        lista.listaNova().print();

        bufferedReader.close();
    }
}
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
class NoFila{
    protected NoFila next;
    protected int valor;
    
    public NoFila(int valor){
        this.valor = valor;
    }
}

class FilaDinamica{
    private NoFila fila, reverter;

    
    public void add(int valor){
        if(reverter == null){
            reverter = new NoFila(valor);
        }else{
            NoFila temp = reverter;
            while(temp.next!=null){
                temp = temp.next;
            }
            temp.next = new NoFila(valor);
        }
    }
    
    public void reverterFila(int n) {
        if(n > 0){
            NoFila temp = reverter;
            int valores[] = new int[n];
            for(int i=n-1;i>=0;i--){
                valores[i]=temp.valor;
                temp = temp.next;
            }
            fila = new NoFila(valores[0]);
            NoFila tempp = fila;
            for(int k=1;k<n;k++){
                tempp.next = new NoFila(valores[k]);
                tempp = tempp.next;
            }
            tempp.next = temp;
        }else{
            fila = reverter;
        }
    }
    public void print(){
        NoFila temp = fila;
        while(temp != null){
            System.out.print(temp.valor + " ");
            temp = temp.next;
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException{
        
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int a = Integer.parseInt(bufferedReader.readLine().trim());

        int b = Integer.parseInt(bufferedReader.readLine().trim());

        int c = Integer.parseInt(bufferedReader.readLine().trim());

        int d = Integer.parseInt(bufferedReader.readLine().trim());

        int e = Integer.parseInt(bufferedReader.readLine().trim());

        int f = Integer.parseInt(bufferedReader.readLine().trim());

        int g = Integer.parseInt(bufferedReader.readLine().trim());
        
        int h = Integer.parseInt(bufferedReader.readLine().trim());
        
        int i = Integer.parseInt(bufferedReader.readLine().trim());
        
        int j = Integer.parseInt(bufferedReader.readLine().trim());
        
        int l = Integer.parseInt(bufferedReader.readLine().trim());
        
        FilaDinamica fila = new FilaDinamica();
        fila.add(a);
        fila.add(b);
        fila.add(c);
        fila.add(d);
        fila.add(e);
        fila.add(f);
        fila.add(g);
        fila.add(h);
        fila.add(i);
        fila.add(j);
        
        fila.reverterFila(l);
        
        fila.print();

        bufferedReader.close();
    }
}
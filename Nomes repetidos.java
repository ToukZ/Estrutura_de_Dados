import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;

class ImplementedList {
    protected String[] nomes;
    private int quantidade;
    private int max;
    
    public ImplementedList(){
        this.max = 10;
        this.nomes = new String[this.max];
        this.quantidade = 0;
    }
    public int getQuantidade(){
        return this.quantidade;
    }
    public void setQuantidade(){
        this.quantidade++;
    }
    public int getMax(){
        return this.max;
    }
    public void setMax(){
        this.max++;
    }
}

class Result {
    static ImplementedList lista = new ImplementedList();
    
    public static boolean isEmpty(){
        return lista.getQuantidade() == 0;
    }
    
    public static boolean isFull(){
        return lista.getQuantidade() == lista.getMax()-1;
    }
    
    public static boolean consultar(String nom){
        if(isEmpty()){
            return false;
        }else{
            boolean r = true;
            String[] nomeInserido = nom.split(" ",0);
                for(int j=0;j<nomeInserido.length;j++){
                    if(!lista.nomes[lista.getQuantidade()-1].toLowerCase().contains(nomeInserido[j].toLowerCase())){
                        System.out.println(lista.nomes[lista.getQuantidade()-1] + "=" + nomeInserido[j]);
                        r = false;
                        break;
                    }
                }
            return r;
        }
    }
    
    public static void adicionar(String nom){
        if(!consultar(nom)){
            if(isFull()){
                lista.setMax();
                String temp[] = new String[lista.getMax()];
                for(int i=0;i<lista.getQuantidade();i++){
                    temp[i] = lista.nomes[i];
                }
                lista.nomes = temp;
            }
            lista.nomes[lista.getQuantidade()] = nom;
            lista.setQuantidade();
        }
    }

    public static int attendance(String value) {
        String[] destrincharNomes = value.split("\n",0);
        for(int i=0;i<destrincharNomes.length;i++){
            adicionar(destrincharNomes[i]);
        }
        return lista.getQuantidade();
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
            Scanner scanner = new Scanner(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String input = "";
        do {
            input += scanner.nextLine() + "\n";
        }while (scanner.hasNext());

        int result = Result.attendance(input.trim());
        System.out.println(result);
        bufferedWriter.write(String.format("%d",result));
        bufferedWriter.newLine();

        scanner.close();
        bufferedWriter.close();
    }
}
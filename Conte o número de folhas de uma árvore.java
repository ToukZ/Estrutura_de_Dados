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
    private String value;
    private Node left;
    private Node right;
    
    public Node(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
    public void setValue(String value){
        this.value = value;
    }
    public Node getLeft(){
        return this.left;
    }
    public Node getRight(){
        return this.right;
    }
    public void SetLeft(Node left){
        this.left = left;
    }
    public void SetRight(Node right){
        this.right = right;
    }
}
class ArvoreBi{
    private Node initialNode;
    private int quantidadeFolhas = 0;
    
    public void add(String elem) {
        if(initialNode==null){
            initialNode = new Node(elem);
            quantidadeFolhas++;
        }else{
            Node temp = initialNode;
            while(temp!=null){
                if(elem.compareToIgnoreCase(temp.getValue())>0){
                    if(temp.getRight()==null){
                        if(temp.getLeft()!=null){
                            quantidadeFolhas++;
                        }
                        temp.SetRight(new Node(elem));
                        break;
                    }else{
                        temp = temp.getRight();
                    }
                }else if(elem.compareToIgnoreCase(temp.getValue())<=0){
                    if(temp.getLeft()==null){
                        if(temp.getRight()!=null){
                            quantidadeFolhas++;
                        }
                        temp.SetLeft(new Node(elem));
                        break;
                    }else{
                        temp = temp.getLeft();
                    }
                }
            }
        }
    }
    
    public int quantasFolhas(){
        return this.quantidadeFolhas;
    }
}
class Result {

    public static int contaFolha(String a, ArvoreBi arvore) {
        String[] valuesToAdd = a.split(" ");
        for(String value : valuesToAdd){
            arvore.add(value);
        }
        return arvore.quantasFolhas();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));


        String a = bufferedReader.readLine();
        ArvoreBi b = new ArvoreBi();
        int result = Result.contaFolha(a,b);
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
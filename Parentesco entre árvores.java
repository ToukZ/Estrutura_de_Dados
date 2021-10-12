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

class No {
    private String info;
    private No left;
    private No right;
    
    public No(String info){
        this.info = info;
    }
    
    public String getInfo(){
        return info;
    }
    public No getLeft(){
        return this.left;
    }
    public No getRight(){
        return this.right;
    }
    public void setLeft(No left){
        this.left = left;
    }
    public void setRight(No right){
        this.right = right;
    }
}

class ArvoreBinaria {
    private No a;
    
    public void add(String e) {
        if(a ==null){
            a = new No(e);
        }else{
            No temp = a;
            while(temp!=null){
                if(e.compareTo(temp.getInfo())>0){
                    if(temp.getRight()==null){
                        temp.setRight(new No(e));
                        break;
                    }else{
                        temp = temp.getRight();
                    }
                }else if(e.compareTo(temp.getInfo())<0){
                    if(temp.getLeft()==null){
                        temp.setLeft(new No(e));
                        break;
                    }else{
                        temp = temp.getLeft();
                    }
                }else{
                    break;
                }
            }
        }
    }
    
    public int relacao(String nodeA, String nodeB){
        int contador = 0;
        No temp = a;
        while(temp!=null){
            if(nodeA.compareTo(temp.getInfo())>0){
                temp = temp.getRight();
            }else if(nodeA.compareTo(temp.getInfo())<0){
                temp = temp.getLeft();
            }else{
                break;
            }
        }
        if(temp !=null){
            while(temp!=null){
                if(nodeB.compareTo(temp.getInfo())>0){
                    temp = temp.getRight();
                    contador++;
                }else if(nodeB.compareTo(temp.getInfo())<0){
                    temp = temp.getLeft();
                    contador++;
                }else{
                    break;
                }
            }
            if(temp==null){
                contador = 0;
            }
        }
        return contador;
    }
}

class Result {

    public static String nodeGenealogy(String values, String nodeA, String nodeB) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        String[] value = values.trim().split(" ");
        for(int i=0; i<value.length;i++){
            arvore.add(value[i]);
        }
        String r = "";
        int relacao = (arvore.relacao(nodeA, nodeB)>arvore.relacao(nodeB, nodeA))?arvore.relacao(nodeA, nodeB):arvore.relacao(nodeB, nodeA);
        if(relacao>0){
            r = "Pai";
            for(int j=1;j<relacao;j++){
                r += " " + "do Pai";
            }
        }else{
            r = "nao ha parentesco";
        }
        return r;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String treeNodes = bufferedReader.readLine();

        String nodeA = bufferedReader.readLine();

        String nodeB = bufferedReader.readLine();

        String result = Result.nodeGenealogy(treeNodes, nodeA, nodeB);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

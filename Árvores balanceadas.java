import java.io.*;
import java.lang.Math.*;
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
    private int altura;
    
    public No(String info){
        this.info = info;
        this.altura = 0;
    }
    
    public String getInfo(){
        return this.info;
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
    public int getAltura(){
        return this.altura;
    }
    public void setAltura(int altura){
        this.altura = altura;
    }
}

class ArvoreBinaria {
    protected No a;
    
    public void add(String e) {
        if(a == null){
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
                }else if(e.compareTo(temp.getInfo())<=0){
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
    public void setAltura(No a){
        if(a.getRight()!=null && a.getLeft()!=null){
            setAltura(a.getRight());
            setAltura(a.getLeft());
            a.setAltura((a.getRight().getAltura()>a.getLeft().getAltura()?a.getRight().getAltura():a.getLeft().getAltura())+1);
        }else if(a.getRight()!=null){
            setAltura(a.getRight());
            a.setAltura(a.getRight().getAltura()+1);
        }else if(a.getLeft()!=null){
            setAltura(a.getLeft());
            a.setAltura(a.getLeft().getAltura()+1);
        }
    }
    public Boolean isBalanced(No a){
        Boolean r;
        if(a.getRight()==null && a.getLeft()!=null){
            if(a.getLeft().getAltura()>0){
                r = false;
            }else{
                r = true;
            }
        }else if(a.getRight()!=null && a.getLeft()==null){
            if(a.getRight().getAltura()>0){
                r = false;
            }else{
                r = true;
            }
        }else if(a.getRight()!=null && a.getLeft()!=null){
            if(Math.abs(a.getRight().getAltura()-a.getLeft().getAltura())>1){
                r = false;
            }else{
                r = true;
            }
        }else{
            r = true;
        }
        return r;
    }
}


class Result {
    private static ArvoreBinaria arv = new ArvoreBinaria();

    public static String NodeInformation(String values) {
        String r = "";
        String[] infos = values.trim().split(" ");
        for(int i=0;i<infos.length;i++){
            arv.add(infos[i]);
        }
        arv.setAltura(arv.a);
        if(arv.isBalanced(arv.a)){
            r = "E balanceada";
        }else{
            r = "Nao e balanceada";
        }
        return r;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String treeNodes = bufferedReader.readLine();

        String result = Result.NodeInformation(treeNodes);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

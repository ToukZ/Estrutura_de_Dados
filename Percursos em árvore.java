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

class BinaryNode {
    private int value;
    private BinaryNode left;
    private BinaryNode right;
    
    public BinaryNode(int value){
        this.value = value;
    }
    public int getValue(){
        return this.value;
    }
    public void setValue(int value){
        this.value = value;
    }
    public BinaryNode getLeft(){
        return this.left;
    }
    public BinaryNode getRight(){
        return this.right;
    }
    public void setLeft(BinaryNode left){
        this.left = left;
    }
    public void setRight(BinaryNode right){
        this.right = right;
    }
}

class BinaryTree {
    private BinaryNode initialNode;

    public void add(int elem) {
        if(initialNode==null){
            initialNode = new BinaryNode(elem);
        }else{
            BinaryNode temp = initialNode;
            while(temp!=null){
                if(elem>temp.getValue()){
                    if(temp.getRight()==null){
                        temp.setRight(new BinaryNode(elem));
                        break;
                    }else{
                        temp = temp.getRight();
                    }
                }else if(elem<temp.getValue()){
                    if(temp.getLeft()==null){
                        temp.setLeft(new BinaryNode(elem));
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
    
    private String posOrdem(BinaryNode a){
        String r = "";
        if(a.getLeft()!=null && a.getRight()==null){
            r += posOrdem(a.getLeft());
        }else if(a.getRight()!=null && a.getLeft()==null){
            r += posOrdem(a.getRight());   
        }else if(a.getRight()!=null && a.getLeft()!=null){
            r += posOrdem(a.getLeft()) + posOrdem(a.getRight());
        }
        return r + a.getValue() + " ";
    }
    private String preOrdem(BinaryNode a){
        String r = a.getValue() + " ";
        if(a.getLeft()!=null && a.getRight()==null){
            r += preOrdem(a.getLeft());
        }else if(a.getRight()!=null && a.getLeft()==null){
            r += preOrdem(a.getRight());   
        }else if(a.getRight()!=null && a.getLeft()!=null){
            r += preOrdem(a.getLeft()) + preOrdem(a.getRight());
        }
        return r;
    }
    private String emOrdem(BinaryNode a){
        String r = "";
        if(a.getLeft()!=null && a.getRight()==null){
            r += emOrdem(a.getLeft()) + " " + a.getValue();
        }else if(a.getRight()!=null && a.getLeft()==null){
            r += a.getValue() + " " + emOrdem(a.getRight());   
        }else if(a.getRight()!=null && a.getLeft()!=null){
            r += emOrdem(a.getLeft()) + " " + a.getValue() + " " + emOrdem(a.getRight());
        }else{
            r += a.getValue();
        }
        return r;
    }
    
    public void questao(){
        System.out.println("Em ordem:\n" + emOrdem(initialNode));
        System.out.println("Pre ordem:\n" + preOrdem(initialNode));
        System.out.println("Pos ordem:\n" + posOrdem(initialNode));
    }
    
}

public class Solution {
    
    public static void addArvore(String entrada, BinaryTree a) {
        String[] elementos = entrada.trim().split(",");
        for(int i=0;i<elementos.length;i++){
            a.add(Integer.valueOf(elementos[i]));
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String entrada = bufferedReader.readLine();
        BinaryTree a = new BinaryTree();
        addArvore(entrada,a);
        a.questao();

        bufferedReader.close();
    }
}
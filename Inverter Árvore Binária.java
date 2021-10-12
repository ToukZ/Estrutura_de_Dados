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

class ArvoreBinaria {
    protected No a;
    
    public void add(int e) {
        if(a ==null){
            a = new No(e);
        }else{
            No temp = a;
            while(temp!=null){
                if(e>temp.getInfo()){
                    if(temp.getRight()==null){
                        temp.setRight(new No(e));
                        break;
                    }else{
                        temp = temp.getRight();
                    }
                }else if(e<temp.getInfo()){
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
    
    public void inverterArvore(No a) {
        if(a.getLeft()!=null && a.getRight()==null){
            inverterArvore(a.getLeft());
            a.setRight(a.getLeft());
            a.setLeft(null);
        }else if(a.getRight()!=null && a.getLeft()==null){
            inverterArvore(a.getRight());
            a.setLeft(a.getRight());
            a.setRight(null);
        }else if(a.getRight()!=null && a.getLeft()!=null){
            inverterArvore(a.getRight());
            inverterArvore(a.getLeft());
            No temp = a.getRight();
            a.setRight(a.getLeft());
            a.setLeft(temp);
        }
    }
    
    public void percursoEmOrdem(No a) {
        if(a.getLeft()!=null && a.getRight()==null){
            percursoEmOrdem(a.getLeft());
            System.out.print(" " + a.getInfo());
        }else if(a.getRight()!=null && a.getLeft()==null){
            System.out.print(a.getInfo() + " ");
            percursoEmOrdem(a.getRight());
        }else if(a.getRight()!=null && a.getLeft()!=null){
            percursoEmOrdem(a.getLeft());
            System.out.print(" " + a.getInfo() + " ");
            percursoEmOrdem(a.getRight());
        }else{
            System.out.print(a.getInfo());
        }
        
    }
    
}

class No {
    private int info;
    private No left;
    private No right;
    
    public No(int info){
        this.info = info;
    }
    
    public int getInfo(){
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

public class Solution {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArvoreBinaria arvore = new ArvoreBinaria();
        String[] input = scan.nextLine().split(" ");
        for (int i = 0; i < input.length; i++)
            arvore.add(Integer.parseInt(input[i]));
        arvore.percursoEmOrdem(arvore.a);
        arvore.inverterArvore(arvore.a);
        System.out.println();
        arvore.percursoEmOrdem(arvore.a);
        if(scan != null)
            scan.close();
    }
}
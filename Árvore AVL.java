import java.io.*;
import java.math.*;
import java.lang.Math.*;
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
    private int value;
    private Node left;
    private Node right;
    private int altura;
    
    public Node(int value){
        this.value = value;
        this.altura = 0;
    }
    public int getValue(){
        return this.value;
    }
    public void setValue(int value){
        this.value = value;
    }
    public Node getLeft(){
        return this.left;
    }
    public Node getRight(){
        return this.right;
    }
    public void setLeft(Node left){
        this.left = left;
    }
    public void setRight(Node right){
        this.right = right;
    }
    public int getAltura(){
        return this.altura;
    }
    public void setAltura(int altura){
        this.altura = altura;
    }
}

class BinaryTree {
    private Node initialNode;

    public void add(int elem) {
        if(initialNode==null){
            initialNode = new Node(elem);
        }else{
            Node temp = initialNode;
            while(temp!=null){
                if(elem>temp.getValue()){
                    if(temp.getRight()==null){
                        temp.setRight(new Node(elem));
                        setAltura(initialNode);
                        if(checkBalance(initialNode)!=null){
                            fixTree();
                        }
                        break;
                    }else{
                        temp = temp.getRight();
                    }
                }else if(elem<temp.getValue()){
                    if(temp.getLeft()==null){
                        temp.setLeft(new Node(elem));
                        setAltura(initialNode);
                        if(checkBalance(initialNode)!=null){
                            fixTree();
                        }
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
    
    public void setAltura(Node a){
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
        //System.out.println("altura de: " + a.getValue() + " = " + a.getAltura());
    }
    
    public Node checkBalance(Node a){
        Node r = null;
        if(a.getRight()==null && a.getLeft()!=null){
            if(a.getLeft().getAltura()>0){
                if(checkBalance(a.getLeft())==null){
                    r = a;
                }else{
                    r = checkBalance(a.getLeft());
                }
            }
        }else if(a.getRight()!=null && a.getLeft()==null){
            if(a.getRight().getAltura()>0){
                if(checkBalance(a.getRight())==null){
                    r = a;
                }else{
                    r = checkBalance(a.getRight());
                }
            }
        }else if(a.getRight()!=null && a.getLeft()!=null){
            if(Math.abs(a.getRight().getAltura()-a.getLeft().getAltura())>1){
                if(checkBalance(a.getRight())!=null){
                    r = checkBalance(a.getRight());
                }else if(checkBalance(a.getLeft())!=null){
                    r = checkBalance(a.getLeft());
                }else{
                    r = a;
                }
            }else{
                if(checkBalance(a.getRight())!=null){
                    r = checkBalance(a.getRight());
                }else if(checkBalance(a.getLeft())!=null){
                    r = checkBalance(a.getLeft());
                }else{
                    r = null;
                }
            }
        }else{
            r = null;
        }
        return r;
    }
    public void fixTree(){
        Node a, b, c = checkBalance(initialNode);
        //System.out.println("estou consertando esse vagabundo:" + c.getValue());
        Node paiDoC = initialNode;
        while(paiDoC!=null){
            if(paiDoC.getValue()<c.getValue()){
                if(paiDoC.getRight() == c){
                    break;
                }else{
                    paiDoC = paiDoC.getRight();
                }
            }else if(paiDoC.getValue()>c.getValue()){
                if(paiDoC.getLeft() == c){
                    break;
                }else{
                    paiDoC = paiDoC.getLeft();
                }
            }else{
                paiDoC = null;
            }
        }
        Boolean paiDoRight = false;
        if(paiDoC!=null){
           //System.out.println("paidoC: " + paiDoC.getValue());
            if(paiDoC.getRight()==c){
                paiDoRight = true;
            }
            
        }
        if(c.getRight() == null){
            b = c.getLeft();
        }else if(c.getLeft() == null){
            b = c.getRight();
        }else{
            b = (c.getRight().getAltura()>c.getLeft().getAltura())?c.getRight():c.getLeft();
        }
        if(b.getRight() == null){
            a = b.getLeft();
        }else if(b.getLeft() == null){
            a = b.getRight();
        }else{
            a = (b.getRight().getAltura()>b.getLeft().getAltura())?b.getRight():b.getLeft();
        }
        Node t0, t1, t2, t3;
        if(b == c.getRight()){
            t0 = c.getLeft();
            if(a == b.getRight()){
                t1 = b.getLeft();
                t2 = a.getLeft();
                t3 = a.getRight();
            }else{
                t1 = a.getLeft();
                t2 = a.getRight();
                t3 = b.getRight();
            }
        }else{
            t3 = c.getRight();
            if(a == b.getRight()){
                t0 = b.getLeft();
                t1 = a.getLeft();
                t2 = a.getRight();
            }else{
                t0 = a.getLeft();
                t1 = a.getRight();
                t2 = b.getRight();
            }
        }
        if(c.getValue()>b.getValue() && a.getValue()>b.getValue()){
            Node temp = b;
            b = a;
            a = temp;
        }else if(c.getValue()<b.getValue() && a.getValue()<b.getValue()){
            Node temp1 = a, temp2 = b;
            a = c;
            b = temp1;
            c = temp2;
        }else if(c.getValue()<b.getValue() && a.getValue()>b.getValue()){
            Node temp = c;
            c = a;
            a = temp;
        }
        if(paiDoC==null){
            initialNode = new Node(b.getValue());
            initialNode.setLeft(new Node(a.getValue()));
            initialNode.getLeft().setLeft(t0);
            initialNode.getLeft().setRight(t1);
            initialNode.setRight(new Node(c.getValue()));
            initialNode.getRight().setLeft(t2);
            initialNode.getRight().setRight(t3);
        }else{
            if(paiDoRight){
                paiDoC.setRight(new Node(b.getValue()));
                paiDoC.getRight().setLeft(new Node(a.getValue()));
                paiDoC.getRight().getLeft().setLeft(t0);
                paiDoC.getRight().getLeft().setRight(t1);
                paiDoC.getRight().setRight(new Node(c.getValue()));
                paiDoC.getRight().getRight().setLeft(t2);
                paiDoC.getRight().getRight().setRight(t3);
            }else{
                paiDoC.setLeft(new Node(b.getValue()));
                paiDoC.getLeft().setLeft(new Node(a.getValue()));
                paiDoC.getLeft().getLeft().setLeft(t0);
                paiDoC.getLeft().getLeft().setRight(t1);
                paiDoC.getLeft().setRight(new Node(c.getValue()));
                paiDoC.getLeft().getRight().setLeft(t2);
                paiDoC.getLeft().getRight().setRight(t3);
            }
        }
    }
    
    private String treeSearch(Node actual) {
        if (actual == null) {
            return "(null)";
        }
        if(actual.getLeft() == null && actual.getRight() == null )
            return "" + actual.getValue();
        return String.format("l(%s) - %s - r(%s)", treeSearch(actual.getLeft()), actual.getValue(), treeSearch(actual.getRight()));
    }

    public String toString() {
        return treeSearch(initialNode);
    }
}
class Result {
    private static BinaryTree arvore = new BinaryTree();

    public static void addToTree(String values) {
        String[] infos = values.trim().split(" ");
        for(int i=0;i<infos.length;i++){
            arvore.add(Integer.valueOf(infos[i]));
        }
    }

    public static String printTree() {
        return arvore.toString();
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String addValues = bufferedReader.readLine();

        Result.addToTree(addValues);

        String result = Result.printTree();

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

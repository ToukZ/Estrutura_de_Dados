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
    private int deep;
    private boolean folha;
    
    public Node(String value){
        this.value = value;
        this.deep = 0;
        this.folha = true;
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
    public void setDeep(int deep){
        this.deep = deep;
    }
    public int getDeep(){
        return this.deep;
    }
    public void notFolha(){
        this.folha = false;
    }
    public boolean isFolha(){
        return this.folha;
    }
}

class BinaryTree {
    private Node initialNode;   

    public void add(String elem) {
        if(initialNode==null){
            initialNode = new Node(elem);
        }else{
            Node temp = initialNode;
            int deep = 1;
            while(temp!=null){
                if(elem.compareToIgnoreCase(temp.getValue())>0){
                    if(temp.getRight()==null){
                        temp.SetRight(new Node(elem));
                        temp.getRight().setDeep(deep);
                        temp.notFolha();
                        break;
                    }else{
                        temp = temp.getRight();
                        deep++;
                    }
                }else if(elem.compareToIgnoreCase(temp.getValue())<=0){
                    if(temp.getLeft()==null){
                        temp.SetLeft(new Node(elem));
                        temp.getLeft().setDeep(deep);
                        temp.notFolha();
                        break;
                    }else{
                        temp = temp.getLeft();
                        deep++;
                    }
                }
            }
        }
    }
    public Node find(String elem){
        Node temp = initialNode;
        while(temp!=null){
            if(elem.compareToIgnoreCase(temp.getValue())==0){
                break;
            }else if(elem.compareToIgnoreCase(temp.getValue())>0){
                temp = temp.getRight();
            }else if(elem.compareToIgnoreCase(temp.getValue())<0){
                temp = temp.getLeft();
            }
        }
        return temp;
    }
}
class Result {
    static BinaryTree arvore = new BinaryTree();
    
    public static String nodeInformation(String values, String nodeValue) {
        Node target;
        String deep, isFolha;
        String[] valuesToAdd = values.split(" ");
        for(String value : valuesToAdd){
            arvore.add(value);
        }
        target = arvore.find(nodeValue);
        if(target!=null){
            deep = String.valueOf(target.getDeep());
            if(target.isFolha()){
                isFolha = "1";
            }else{
                isFolha = "0";
            }
        }else{
            deep = "-1";
            isFolha = "-1";
        }
        return deep + " " + isFolha;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String treeNodes = bufferedReader.readLine();

        String searchNode = bufferedReader.readLine();

        String result = Result.nodeInformation(treeNodes, searchNode);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

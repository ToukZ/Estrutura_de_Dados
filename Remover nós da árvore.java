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

class BinaryTree {

    private Node initialNode;

    public void add(String elem) {
        if(initialNode==null){
            initialNode = new Node(elem);
        }else{
            Node temp = initialNode;
            while(temp!=null){
                if(elem.compareToIgnoreCase(temp.getValue())>0){
                    if(temp.getRight()==null){
                        temp.SetRight(new Node(elem));
                        break;
                    }else{
                        temp = temp.getRight();
                    }
                }else if(elem.compareToIgnoreCase(temp.getValue())<=0){
                    if(temp.getLeft()==null){
                        temp.SetLeft(new Node(elem));
                        break;
                    }else{
                        temp = temp.getLeft();
                    }
                }
            }
        }
    }
    public void rem(String elem){
        Node temp = initialNode;
        Node paiTemp = temp;
        while(temp!=null){
            if(elem.compareToIgnoreCase(temp.getValue())==0){
                break;
            }else if(elem.compareToIgnoreCase(temp.getValue())>0){
                paiTemp = temp;
                temp = temp.getRight();
            }else if(elem.compareToIgnoreCase(temp.getValue())<0){
                paiTemp = temp;
                temp = temp.getLeft();
            }
        }
        if(temp!=null){
            if(paiTemp==temp){
                if(temp.getLeft()==null && temp.getRight()!=null){
                    initialNode = temp.getRight();
                }else if(temp.getRight()==null && temp.getLeft()!=null){
                    initialNode = temp.getLeft();
                }else if(temp.getRight()==null && temp.getLeft()==null){
                    initialNode = null;
                }else{
                    Node aux = temp.getLeft();
                    Node paiAux = temp;
                    while(aux.getRight()!=null){
                        paiAux = aux;
                        aux = aux.getRight();
                    }
                    temp.setValue(aux.getValue());
                    if(paiAux!=temp){
                        paiAux.SetRight(aux.getLeft());
                    }else{
                        paiAux.SetLeft(aux.getLeft());
                    }
                }
            }else{
                if(temp.getLeft()==null && temp.getRight()!=null){
                    if(paiTemp.getRight()==temp){
                        paiTemp.SetRight(temp.getRight());
                    }else if(paiTemp.getLeft()==temp){
                        paiTemp.SetLeft(temp.getRight());
                    }
                }else if(temp.getRight()==null && temp.getLeft()!=null){
                    if(paiTemp.getRight()==temp){
                        paiTemp.SetRight(temp.getLeft());
                    }else if(paiTemp.getLeft()==temp){
                        paiTemp.SetLeft(temp.getLeft());
                    }
                }else if(temp.getRight()==null && temp.getLeft()==null){
                    if(paiTemp.getRight()==temp){
                        paiTemp.SetRight(null);
                    }else if(paiTemp.getLeft()==temp){
                        paiTemp.SetLeft(null);
                    }
                }else{
                    Node aux = temp.getLeft();
                    Node paiAux = temp;
                    while(aux.getRight()!=null){
                        paiAux = aux;
                        aux = aux.getRight();
                    }
                    temp.setValue(aux.getValue());
                    if(paiAux!=temp){
                        paiAux.SetRight(aux.getLeft());
                    }else{
                        paiAux.SetLeft(aux.getLeft());
                    }
                }
            }
        }
    }
    
    private String treeSearch(Node actual) {
        if (actual == null){
            return "(null)";
        }else{
            if(actual.getLeft() == null && actual.getRight() == null){
                return actual.getValue();
            }else{
                return String.format("l(%s) - %s - r(%s)", treeSearch(actual.getLeft()), actual.getValue(), treeSearch(actual.getRight()));
            }
        }
    }


    public String toString() {
        return treeSearch(initialNode);
    }
}

class Result {
    private final static BinaryTree tree = new BinaryTree();
    
    public static void createTree(String values){
        String[] valuesToAdd = values.split(" ");
        for(String value : valuesToAdd){
            tree.add(value);
        }
    }
    public static String removeNodes(String removeValues){
        String[] valuesToRem = removeValues.split(" ");
        for(String value : valuesToRem){
            tree.rem(value);
        }
        return tree.toString();
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String addValues = bufferedReader.readLine();

        String removeValues = bufferedReader.readLine();

        Result.createTree(addValues);

        String result = Result.removeNodes(removeValues);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

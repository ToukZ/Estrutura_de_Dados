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
class NoList<T>{
    public T object;
    public NoList<T> next;
    
    public NoList(T object){
        this.object = object;
    }
}

class ImplementedList<T> {
    public NoList<T> initial;
    public int quantidade = 0;
    
    public void add(T object){
        if(initial == null){
            initial = new NoList(object);
        }else{
            NoList<T> temp = initial;
            while(temp.next!=null){
                temp = temp.next;
            }
            temp.next = new NoList(object);
        }
        quantidade++;
    }
}

class Vertex {
    public int value;
    public Boolean visited;
    
    public Vertex(int value){
        this.value = value;
        visited = false;
    }
}

class Edges {
    public Vertex origem, destino;
    
    public Edges(Vertex origem, Vertex destino){
        this.origem = origem;
        this.destino = destino;
    }
}

class Graph {
    private ImplementedList<Vertex> vertices = new ImplementedList<>();
    private ImplementedList<Edges> edges = new ImplementedList<>();
    
    public Vertex findVertex(Integer exist){
        NoList<Vertex> temp = vertices.initial;
        while(temp!=null){
            if(temp.object.value == exist){
                break;
            }
            temp = temp.next;
        }
        if(temp!=null){
            if(temp.object==null){
                return null;
            }else{
                return temp.object;
            }
        }else{
            return null;
        }
    }

    public void addEdge(Integer src, Integer dst) {
        Vertex srcIn;
        Vertex dstOut;
        
        if(findVertex(src)!=null){
            srcIn = findVertex(src);
        }else{
            srcIn = new Vertex(src);
            vertices.add(srcIn);
        }
        if(findVertex(dst)!=null){
            dstOut = findVertex(dst);
        }else{
            dstOut = new Vertex(dst);
            vertices.add(dstOut);
        }    
        edges.add(new Edges(srcIn, dstOut));
    }       
    public String toString() {
        //podia nao precisar disso tudo, mas precisa ordenar. EU SEI QUE DA PRA FAZER MELHOR, mas eh foda neh, tava tudo bonitinho, passei a tarde toda, dai no final vc percebe que precisa ordenar, sad times
        String r = "";
        Vertex menor = vertices.initial.object;
        NoList<Vertex> temp = vertices.initial;
        for(int i=0;i<vertices.quantidade;i++){
            temp = vertices.initial;
            while(temp!=null){
                if(!temp.object.visited){
                    menor = temp.object;
                    break;
                }
                temp = temp.next;
            }
            temp = vertices.initial;
            while(temp!=null){
                if(!temp.object.visited && temp.object.value<menor.value){
                    menor = temp.object;
                }
                temp = temp.next;
            }
            menor.visited = true;
            r += String.format("(%d [%s])\n", menor.value, NodesPointingTo(menor));
        }     
        return r;
    }
    
    public String NodesPointingTo(Vertex vertex) {
        String r = "";
        NoList<Edges> temp = edges.initial;
        while(temp!=null){
            if(temp.object.origem == vertex){
                r += temp.object.destino.value + " ";
            }
            temp = temp.next;
        }
        return r.trim();
    }
}
public class Solution {
    public static void main(String args[]) throws Exception{
        try (Scanner scanner = new Scanner(new InputStreamReader(System.in))) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")))) {
                Graph graph = new Graph();
                while (scanner.hasNext()) {
                    String[] nodes = scanner.nextLine().split(" ");
                    graph.addEdge(Integer.parseInt(nodes[0]), Integer.parseInt(nodes[1]));
                }
                bufferedWriter.write(graph.toString());
                bufferedWriter.newLine();
            }
        }
    }
}
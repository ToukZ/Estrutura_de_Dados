import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
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
    
    public void transpose(){
        Vertex temp = origem;
        origem = destino;
        destino = temp;
    }
}

class Grafo {
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
	public void addEdge(Integer origem, Integer destino){
		Vertex srcIn;
        Vertex dstOut;
        
        if(findVertex(origem)!=null){
            srcIn = findVertex(origem);
        }else{
            srcIn = new Vertex(origem);
            vertices.add(srcIn);
        }
        if(findVertex(destino)!=null){
            dstOut = findVertex(destino);
        }else{
            dstOut = new Vertex(destino);
            vertices.add(dstOut);
        }    
        edges.add(new Edges(srcIn, dstOut));
    } 
	
	public void transporGrafo(){
        NoList<Edges> temp = edges.initial;
        while(temp!=null){
            temp.object.transpose();
            temp = temp.next;
        }
	}
	
	public void imprimirGrafo(){
        //Forgive me lord for i have sinned. Again.
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
            r += String.format("%d-->%s\n", menor.value, NodesPointingTo(menor));
        }     
        System.out.println(r);
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
	
	public static boolean isNumeric(String str) { 
  	  try {  
  	    Integer.parseInt(str);  
  	    return true;
  	  } catch(NumberFormatException e){  
  	    return false;  
  	  }  
  	}
	
	public static void Resultado(List<String> entrada){
		Grafo grafo = new Grafo();
		String[] entradaVertices = entrada.toArray(new String[0]);
		for (int i = 0; i < entradaVertices.length; i++) {
            String vertice = entradaVertices[i].replaceAll("(?:--.*)","");
            String[] arrayLigacoes = entradaVertices[i].replaceAll("^[0-9]-->", "").split(" ");
			for (int j = 0; j < arrayLigacoes.length; j++) {
				if(isNumeric(arrayLigacoes[j]) && isNumeric(vertice)){
					Integer verticeValue = Integer.parseInt(vertice);
					Integer ligacao= Integer.parseInt(arrayLigacoes[j]);
					grafo.addEdge(verticeValue, ligacao);
				}
			}
		}
        grafo.transporGrafo();
        grafo.imprimirGrafo();            
		
	}

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<String> entrada = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String entradaItem = bufferedReader.readLine();
            entrada.add(entradaItem);
        }
      	Grafo.Resultado(entrada);

        bufferedReader.close();
    }
}

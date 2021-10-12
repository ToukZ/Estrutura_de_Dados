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

class Result {

    private static int contador = -1;
    //Usado para contar as repetições da recursao/caractere atual da string
    public static int getContador(){
    //Usado para ler o valor atual do contador
        return contador;
    }
    public static void setContador(){
    //Usado para adicionar +1 ao valor do contador
        contador++;
    }
    
    public static String substituir(String s, char problema, char solucao) {
    if(s.length()<=0){ //impossivel de ocorrer, mas pode impedir crashs.
        return "error 404: string not found";
    }else{
        setContador(); //no primeiro caso, contador == 0
        if(getContador() == s.length()){ //caso estejamos no final da string, nao ha mais porque repetir a recursão
        return s;
        }else{ //caso nao, começemos a verificaçao de problemas
            if(s.charAt(getContador()) == problema){ //se o caracter atual da string for igual ao nosso caractere problema,
                if(getContador()==0){ // -> e este for o primeiro caractere:
                return substituir(solucao + s.substring(getContador()+1,s.length()), problema, solucao); 
                }else{ // -> caso nao seja o primeiro caractere:
                return substituir(s.substring(0,getContador()) + solucao + s.substring(getContador()+1,s.length()),     problema, solucao);
                }
            }else{ //caso não seja um problema, recursivamos até acharmos um/ou cheguemos ao final da string
            return substituir(s, problema, solucao);
            }
        }

    }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String entrada = bufferedReader.readLine();

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        char problema = firstMultipleInput[0].charAt(0);

        char solucao = firstMultipleInput[1].charAt(0);

        String result = Result.substituir(entrada, problema, solucao);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}


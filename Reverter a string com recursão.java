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
    private static int contador=0;
    
    public static void setContador(){
        contador++;
    }
    public static int getContador(){
        return contador;
    }

    public static String fun(String s) {
        setContador();
        if(s.length()<(getContador()*2)){
            return s;
        }else{
            char[] string = new char[s.length()];
            char suporte;
            s.getChars(0, s.length(), string, 0);
            suporte = string[s.length()-getContador()];
            string[s.length()-getContador()] = string[getContador()-1];
            string[getContador()-1] = suporte;
            return fun(String.valueOf(string));
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String entrada = bufferedReader.readLine();

        String result = Result.fun(entrada);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

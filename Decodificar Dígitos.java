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
    private static String resposta="";
    
    public static void setResposta(String resp){
        resposta=resp;
    }
    public static String getResposta(){
        return resposta;
    }

    public static String decodificarDigitos(String scrambledDigits) {
        
        if(scrambledDigits.contains("z")){
            setResposta(getResposta() + "0");
            scrambledDigits = scrambledDigits.replaceFirst("z","*");
            scrambledDigits = scrambledDigits.replaceFirst("e","*");
            scrambledDigits = scrambledDigits.replaceFirst("r","*");
            scrambledDigits = scrambledDigits.replaceFirst("o","*");
            decodificarDigitos(scrambledDigits);
        }else if(scrambledDigits.contains("m")){
            setResposta(getResposta() + "1");
            scrambledDigits = scrambledDigits.replaceFirst("u","*");
            scrambledDigits = scrambledDigits.replaceFirst("m","*");
            decodificarDigitos(scrambledDigits);
        }else if(scrambledDigits.contains("d")){
            setResposta(getResposta() + "2");
            scrambledDigits = scrambledDigits.replaceFirst("d","*");
            scrambledDigits = scrambledDigits.replaceFirst("o","*");
            scrambledDigits = scrambledDigits.replaceFirst("i","*");
            scrambledDigits = scrambledDigits.replaceFirst("s","*");
            decodificarDigitos(scrambledDigits);
        }else if(scrambledDigits.contains("q")){
            setResposta(getResposta() + "4");
            scrambledDigits = scrambledDigits.replaceFirst("q","*");
            scrambledDigits = scrambledDigits.replaceFirst("u","*");
            scrambledDigits = scrambledDigits.replaceFirst("a","*");
            scrambledDigits = scrambledDigits.replaceFirst("t","*");
            scrambledDigits = scrambledDigits.replaceFirst("r","*");
            scrambledDigits = scrambledDigits.replaceFirst("o","*");
            decodificarDigitos(scrambledDigits);
        }else if(scrambledDigits.contains("c")){
            setResposta(getResposta() + "5");
            scrambledDigits = scrambledDigits.replaceFirst("c","*");
            scrambledDigits = scrambledDigits.replaceFirst("i","*");
            scrambledDigits = scrambledDigits.replaceFirst("n","*");
            scrambledDigits = scrambledDigits.replaceFirst("c","*");
            scrambledDigits = scrambledDigits.replaceFirst("o","*");
            decodificarDigitos(scrambledDigits);
        }else if(scrambledDigits.contains("r")){
            setResposta(getResposta() + "3");
            scrambledDigits = scrambledDigits.replaceFirst("t","*");
            scrambledDigits = scrambledDigits.replaceFirst("r","*");
            scrambledDigits = scrambledDigits.replaceFirst("e","*");
            scrambledDigits = scrambledDigits.replaceFirst("s","*");
            decodificarDigitos(scrambledDigits);
        }else if(scrambledDigits.contains("v")){
            setResposta(getResposta() + "9");
            scrambledDigits = scrambledDigits.replaceFirst("n","*");
            scrambledDigits = scrambledDigits.replaceFirst("o","*");
            scrambledDigits = scrambledDigits.replaceFirst("v","*");
            scrambledDigits = scrambledDigits.replaceFirst("e","*");
            decodificarDigitos(scrambledDigits);
        }else if(scrambledDigits.contains("o")){
            setResposta(getResposta() + "8");
            scrambledDigits = scrambledDigits.replaceFirst("o","*");
            scrambledDigits = scrambledDigits.replaceFirst("i","*");
            scrambledDigits = scrambledDigits.replaceFirst("t","*");
            scrambledDigits = scrambledDigits.replaceFirst("o","*");
            decodificarDigitos(scrambledDigits);
        }else if(scrambledDigits.contains("i")){
            setResposta(getResposta() + "6");
            scrambledDigits = scrambledDigits.replaceFirst("s","*");
            scrambledDigits = scrambledDigits.replaceFirst("e","*");
            scrambledDigits = scrambledDigits.replaceFirst("i","*");
            scrambledDigits = scrambledDigits.replaceFirst("s","*");
            decodificarDigitos(scrambledDigits);
        }else if(scrambledDigits.contains("t")){
            setResposta(getResposta() + "7");
            scrambledDigits = scrambledDigits.replaceFirst("s","*");
            scrambledDigits = scrambledDigits.replaceFirst("e","*");
            scrambledDigits = scrambledDigits.replaceFirst("t","*");
            scrambledDigits = scrambledDigits.replaceFirst("e","*");
            decodificarDigitos(scrambledDigits);
        }else{
            char[] codigo = new char[getResposta().length()];
            getResposta().getChars(0, getResposta().length(), codigo, 0);   
            if(codigo[0] > codigo[1]){
                setResposta(Character.toString(codigo[1]) + Character.toString(codigo[0]));       
            }
        }       
        return getResposta();
        }
    }
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String scrambledDigits = bufferedReader.readLine();

        String digits = Result.decodificarDigitos(scrambledDigits);

        bufferedWriter.write(digits);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

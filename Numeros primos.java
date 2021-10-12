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
    private static int contador = 2; // contador de numeros, idealmente este irah de 2 ateh o numero escolhido
    
    public static void setContador(){ // avanca o contador
        contador++;
    }
    
    public static int getContador(){ // leh o contador
        return contador;
    }

    public static boolean isPrime(int number) {
        if(number<=3 && number>1){ // se o numero for 2 ou 3, este eh primo (condicao de parada)
            return true;
        }else if(number <=1){ // se o numero for menor ou igual a um o programa para
            return false;
        }else if(number>getContador() && isPrime(getContador())){
             // aqui eh onde a magica funciona. Se o numero for maior que o contador atual, e tambem o contador atual for um primo:
            if(number%getContador()==0){
            /* se o resto de divisao do numero atual pelo contador atual (-> caso este seja um primo) for igual a 0,
             * quer dizer que o contador atual divide o numero. Ou seja, o numero nao eh um primo.
             */
                return false;
            }else{
            /* caso o contador atual nao divida o numero, o resultado eh inconclusivo.
             * Ou seja, devemos continuar testando para todos os outros contadores atuais que forem primos se estes conseguem dividir o numero.
             */
                setContador();
                return isPrime(number);
            }
        }else{
            /* caso o numero ja seja igual ao contador atual, quer dizer que este ja foi verificado para todos os n numeros que vem antes desse. 
             * Ou seja, ele eh primo
            */ 
            return true;
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int inputNumber = Integer.parseInt(bufferedReader.readLine().trim());

        boolean result = Result.isPrime(inputNumber);

        bufferedWriter.write(String.valueOf(result ? 1 : 0));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

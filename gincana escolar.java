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
    protected static int quantidade = 0;
    protected static int max = 10;
    protected static String nomes[] = new String[max];
    
    public static boolean isEmpty(){
        return quantidade == 0;
    }
    public static boolean isFull(){
        return quantidade == max-1;
    }

    public static void addToOrderedList(String value) {
        if(isEmpty()){
            nomes[quantidade++] = value;
        }else{ 
            if(isFull()){
                max+=2;
                String temp[] = new String[max];
                for(int i=0;i<quantidade;i++){
                    temp[i] = nomes[i];
                }
                nomes = temp;
            }
            for(int i=0;i<quantidade;i++){
                if(nomes[i].compareToIgnoreCase(value)>0){
                    for(int j=quantidade;j>=i;j--){
                        nomes[j+1] = nomes[j];
                    }
                    nomes[i] = value;
                    quantidade++;
                    break;
                }else if(i+1==quantidade){
                    nomes[quantidade++] = value;
                    break;
                }
            }
        }
    }


    public static String orderedListToString() {
        String r = "";
        for(int i=0;i<quantidade;i++){
            r += nomes[i] + "\n";
        }
        return r;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int numberOfLists = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, numberOfLists).forEach(numberOfListsItr -> {
            try {
                String names = bufferedReader.readLine();

                Result.addToOrderedList(names);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        String rValue = Result.orderedListToString();

        bufferedWriter.write(rValue);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

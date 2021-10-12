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
    private static double currentDrawChance = 1;
    
    public static void setCurrentDrawChance(double drawChance){
        currentDrawChance = drawChance;
    }
    public static double getCurrentDrawChance(){
        return currentDrawChance;
    }

    public static double calculateProbability(int numberOfCardsInDeck, int numberOfCopies, int numberOfDraws) {
        if(numberOfCardsInDeck>100 || numberOfCopies>100 || numberOfDraws>100 || numberOfCopies<1 || numberOfCardsInDeck<1 || numberOfDraws<1){
            return 0;
        }
        setCurrentDrawChance(getCurrentDrawChance()*(1.000-(((double)numberOfCopies)/numberOfCardsInDeck)));
        if(numberOfDraws==1 || numberOfCardsInDeck<=numberOfCopies){    
                BigDecimal d = BigDecimal.valueOf(getCurrentDrawChance()*100).setScale(3, RoundingMode.HALF_UP);
                double aprox3Casas = d.doubleValue();
                return aprox3Casas;
        }else{
            return calculateProbability(numberOfCardsInDeck-1, numberOfCopies, numberOfDraws-1);
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int deck = Integer.parseInt(bufferedReader.readLine().trim());

        int copies = Integer.parseInt(bufferedReader.readLine().trim());

        int draws = Integer.parseInt(bufferedReader.readLine().trim());

        double probability = Result.calculateProbability(deck, copies, draws);

        bufferedWriter.write(String.valueOf(probability));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

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
class Encrypt{
    private int num;
    private char ch;
    
    public Encrypt(int num, char ch){
        this.num = num;
        this.ch = ch;
    }
    public int getNum(){
        return this.num;
    }
    public char getCh(){
        return this.ch;
    }
}
class NoLista{
    private Encrypt info;
    protected NoLista next;
    
    public NoLista(Encrypt info){
        this.info = info;
    }
    public Encrypt getInfo(){
        return this.info;
    }
}
class ListaDinamica{
    public NoLista initial;
    
    public void add(Encrypt info){
        if(initial==null){
            initial= new NoLista(info);
        }else{
            NoLista temp = initial;
            while(temp.next!=null){
                temp = temp.next;
            }
            temp.next = new NoLista(info);
        }
    }
    public Integer findEncrypted(char ch){
        NoLista temp = initial;
        Integer r = null;
        while(temp!=null){
            if(temp.getInfo().getCh()==ch){
                break;
            }else{
                temp = temp.next;
            }
        }
        if(temp!=null){
            r = temp.getInfo().getNum();
        }
        return r;
    }
}
class Hashtable{
    private ListaDinamica[] infos;
    
    public Hashtable(int i){
        this.infos = new ListaDinamica[i];
        for(int j=0;j<i;j++){
            infos[j] = new ListaDinamica();
        }
    }
    public ListaDinamica getInfos(int where){
        return infos[where];
    }
}

class Result {
    private static Hashtable hashtable = new Hashtable(7);
    //Hashtable com enderecamento Fechado.

    public static String encrypt(String dictionary, String phrase) {
        String[] values = dictionary.trim().toLowerCase().split(" ");
        for(int i=0;i<values.length;i++){
            char ch = values[i].charAt(0);
            int num = Integer.valueOf(values[i].substring(2));
            hashtable.getInfos(num%7).add(new Encrypt(num,ch));
        }
        String r = "";
        for(int j=0;j<phrase.length();j++){ 
            int n = 0;
            for(int k=0;k<7;k++){
                if(hashtable.getInfos(k).findEncrypted(phrase.toLowerCase().charAt(j))!=null){
                    n = hashtable.getInfos(k).findEncrypted(phrase.toLowerCase().charAt(j));
                    break;
                }
            }
            r += n + " ";
        }
        return r;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String dictionary = bufferedReader.readLine();

        String phrase = bufferedReader.readLine();

        String result = Result.encrypt(dictionary, phrase);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

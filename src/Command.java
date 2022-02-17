import java.io.*;
import java.util.*;

public class Command {
    public static ArrayList<String> partsList = new ArrayList<>();
    public static ArrayList<String> itemsList = new ArrayList<>();
    public static ArrayList<String> tokensList = new ArrayList<>();
    public static ArrayList<String> tasksList = new ArrayList<>();

    public static void commands() throws IOException {
        partsList = reader(Main.partsFile);
        itemsList = reader(Main.itemsFile);
        tokensList = reader(Main.tokensFile);
        tasksList = reader(Main.tasksFile);

        // parts.txt den çektiğin elemanlarla item classında obje oluştur ve hepsini bi LinkedHashMap e at.
        // her objeden bi tane stack oluştur ve obje ile bağla
        // items.txt den çektiklerini stack lara ekle
        // tasks tan çektiğin buy ve put ları oku.
        // put ile stack a push yap
        // buy ile stack tan pop yap. burada tokenleri de kullanmayı çöz

        Command.partCreater(partsList);
        Command.itemAdder(itemsList);

        // partCreater ile parts.txt deki Biscuits Chocolates Drinks Crisps bir Item olarak oluşturuldu ve yanlarına name ve boş bir yeni stack eklendi
        // itemAdder ile items.txt deki itemler gerekli objelerinin stack larının içine eklendi

        // token.txt de gezerek token class ında obje oluştur. name(String T1), type(Item Biscuits), value(int 2)

        tokenCreator(tokensList);

        // tasks.txt i oku. buy ve put methodlarını oluştur.
        tasksSeperator(tasksList);

        outputCreator();
    }

    public static ArrayList<String> reader(String file) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        String line;
        FileReader fReader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(fReader);
        while ((line = bReader.readLine()) != null) {
            list.add(line);
        }
        return list;
    }

    public static void partCreater(ArrayList<String> partsList){
        for(String str : partsList){
            Stack newStack = new Stack();
            Item newItem = new Item(str,newStack);
            Item.itemHash.put(str, newItem);
        }
    }

    public static void itemAdder(ArrayList<String> itemsList){
        for(String str : itemsList){
            String[] strList = str.split(" ");
            Item.itemHash.get(strList[1]).getStack().push(strList[0]);
        }
    }

    public static void tokenCreator(ArrayList<String> tokensList){
        Token[] tokenObjectArray = new Token[0];
        for (String str : tokensList) {
            String[] strList = str.split(" ");
            Token newToken = new Token(strList[0], Item.itemHash.get(strList[1]), Integer.parseInt(strList[2]));
            Token.tokenHash.put(strList[0], newToken);
            tokenObjectArray = Token.tokenHash.values().toArray(new Token[0]);
        }
        tokenSorter(tokenObjectArray);
        tokenQueueCreator(tokenObjectArray);
    }
    public static void tokenSorter(Token[] tokens){
        Arrays.sort(tokens, (token2, token1) -> token1.getValue().compareTo(token2.getValue()));
    }

    public static void tokenQueueCreator(Token[] tokens){
        // yeni queue oluşturuyorum tokens ten gelen tokenleri bu queue ye ekliyorum enqueue
        // yeni queue yi Token deki genel queue ye atıyorum
        // dequeue yapınca return olarak çıkan elemanı döndürüyor
        // ve queue deki listeden çıkan elemanı çıkarıyor

        Queue newQueue = new Queue();
        for (Token token : tokens){
            newQueue.enqueue(token.getName());
        }
        Token.tokenQueue = newQueue;
    }

    public static void tasksSeperator(ArrayList<String> tasksList){
        for (String str : tasksList){
            if (str.startsWith("BUY")){
                String[] strList = str.split("\t");
                for (int i = 1; i < strList.length; i++) {
                    String[] strList2 = strList[i].split(",");
                    // strList2[0] obje adı strList2[1] kaç tane alınacağı
                    itemBuyyer(strList2[0], Integer.parseInt(strList2[1]));
                }
            }
            if (str.startsWith("PUT")){
                String[] strList = str.split("\t");
                for (int i = 1; i < strList.length; i++) {
                    String[] strList2 = strList[i].split(",");
                    itemPutter(strList2);
                }
            }
        }
    }

    public static void itemPutter(String[] putList){
        String obje = putList[0];
        for (int i = 1; i < putList.length; i++) {
            Item.itemHash.get(putList[0]).getStack().push(putList[i]);
        }
    }

    public static void itemBuyyer(String name, int number){
        // Burada buy işlemi ile beraber tokenleri de kullan
        tokenUser(name,number);
        for (int i = 1; i < number+1; i++) {
            //System.out.println("i: " + i);
            Item.itemHash.get(name).getStack().pop();
        }
    }

    public static void tokenUser(String name, int number){
        String searchSonucu = Token.tokenQueue.search(name);
        if (Token.tokenHash.get(searchSonucu).getValue() > number){
            Token.tokenHash.get(searchSonucu).setValue(Token.tokenHash.get(searchSonucu).getValue() - number);
            Token.tokenQueue.search(searchSonucu,Token.tokenHash.get(searchSonucu).getValue());
        }
        else if(Token.tokenHash.get(searchSonucu).getValue() == number){
            Token.tokenHash.get(searchSonucu).setValue(0);
            Token.tokenHash.remove(searchSonucu);
        }
        else{
            number -= Token.tokenHash.get(searchSonucu).getValue();
            tokenUser(name,number);
        }
    }



    public static void outputCreator() throws IOException {
        File outputFile = new File(Main.outputFile);
        FileWriter fWriter = new FileWriter(outputFile,false);
        BufferedWriter bWriter = new BufferedWriter(fWriter);
        for(Item item : Item.itemHash.values()){
            Collections.reverse(item.getStack().stack);
            bWriter.write(item.getName() + ":" + "\n");
            if (item.getStack().stack.size() == 0){
                bWriter.write("\n");
            }
            for (String str : item.getStack().stack){
                bWriter.write(str + "\n");
            }
            bWriter.write("---------------" + "\n");

        }
        bWriter.write("Token Box:");
        for (int i = Token.tokenQueue.queue.size()-1; i > -1 ; i--) {
            bWriter.write("\n" + Token.tokenHash.get(Token.tokenQueue.queue.get(i)).getName() + " " + Token.tokenHash.get(Token.tokenQueue.queue.get(i)).getType().getName() + " " + Token.tokenHash.get(Token.tokenQueue.queue.get(i)).getValue());
        }
        bWriter.close();
    }
}
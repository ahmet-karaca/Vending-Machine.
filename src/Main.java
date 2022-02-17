import java.io.IOException;

public class Main {
    public static String partsFile;
    public static String itemsFile;
    public static String tokensFile;
    public static String tasksFile;
    public static String outputFile;

    public static void main(String[] args) throws IOException {
        partsFile = args[0];
        itemsFile = args[1];
        tokensFile = args[2];
        tasksFile = args[3];
        outputFile = args[4];

        Command.commands();
    }
}
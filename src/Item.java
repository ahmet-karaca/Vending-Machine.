import java.io.*;
import java.util.*;

public class Item {
    public static LinkedHashMap<String,Item> itemHash = new LinkedHashMap<>();
    private String name;
    private Stack stack;

    public Item(String name ,Stack stack){
        this.name = name;
        this.stack = stack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }
}
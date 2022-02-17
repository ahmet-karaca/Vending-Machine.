import java.io.*;
import java.util.*;

public class Token {
    public static LinkedHashMap<String,Token> tokenHash = new LinkedHashMap<>();
    public static Queue tokenQueue = new Queue();
    private String name;
    private Item type;
    private Integer value;

    public Token(String name,Item type,Integer value){
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item getType() {
        return type;
    }

    public void setType(Item type) {
        this.type = type;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

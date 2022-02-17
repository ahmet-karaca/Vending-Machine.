/*
public class Stack {
    int topOfStack;
    int capacity;
    int[] stack;
    Stack(int capacity){
        this.capacity = capacity;
        stack = new int[capacity];
        topOfStack = -1;
    }
    void push(int element){
        if (topOfStack == capacity-1){
            System.out.println("overflow");
        }
        else {
            topOfStack++;
            stack[topOfStack] = element;
            System.out.println("ekleniyor:" + element);
        }
    }
    void pop(){
        if (topOfStack < 0){
            System.out.println("underflow");
        }
        else{
            int element = stack[topOfStack];
            topOfStack--;
            System.out.println(element);
        }

    }
}
*/

import java.util.*;

public class Stack {
    int topOfStack;
    ArrayList<String> stack;

    Stack(){
        stack = new ArrayList<>();
        topOfStack = -1;
    }
    public void push(String element){
        topOfStack++;
        stack.add(element);
    }
    public void pop(){
        String element = stack.get(topOfStack);
        topOfStack--;
        stack.remove(element);
    }
}


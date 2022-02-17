/*public class Queue {
    int[] arr;
    int capacity, first, last,currentSize;

    Queue(int capacity){
        arr = new int[capacity];
        first = 0;
        last = -1;
        currentSize = 0;
    }

    boolean isEmpty(){
        return currentSize==0;
    }

    boolean isFull(){
        return currentSize==capacity;
    }

    void enqueue(int item){
        System.out.println("ekleniyor: " + item);
        last += 1;
        arr[last] = item;
        currentSize += 1;
        System.out.println(last + " " + item);
    }

    void dequeue(){
        System.out.println("çıkarılıyor: " + arr[first]);
        first += 1;
        currentSize -= 1;
    }
}*/

import java.util.*;

public class Queue {
    ArrayList<String> queue;


    Queue(){
        queue = new ArrayList<>();

    }

    public void enqueue(String element){
        queue.add(element);

    }

    public void enqueue(String element,int index){
        queue.add(index,element);

    }

    public void dequeue(String element){
        queue.remove(element);
    }

    public String search(String type){
        for (String str : queue) {
            if (Token.tokenHash.get(str).getType().getName().equals(type)) {
                dequeue(str);
                return str;
            }
        }
        return null;
    }

    public void search(String element,int value){
        for (int i = 0; i < queue.size(); i++) {
            if (Token.tokenHash.get(queue.get(0)).getValue() < value){
                enqueue(element,0);
                break;
            }
            if (i == (queue.size()-1)){
                enqueue(element);
                break;
            }
            if (Token.tokenHash.get(queue.get(i)).getValue() == value && Token.tokenHash.get(queue.get(i+1)).getValue() < value){
                enqueue(element,i+1);
                break;
            }
        }
    }
}
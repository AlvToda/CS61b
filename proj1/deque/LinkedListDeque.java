package deque;
import org.junit.Test;
import static org.junit.Assert.*;
public class LinkedListDeque<T> implements Deque<T>{
    public class IntNode {
        public IntNode prev;
        public T item;
        public IntNode next;
        private int size;
        public IntNode(IntNode p,T i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    public IntNode sentA;
    public int size;
    public LinkedListDeque(){
        size=0;
        sentA=new IntNode(null,null,null);
        sentA.prev=sentA;
        sentA.next=sentA;
    }
    public void addFirst(T front) {
        IntNode t=new IntNode(null,front,null);
        t.next=sentA.next;
        t.prev=sentA;
        sentA.next=t;
        sentA.prev=t;
        size+=1;
    }
    public void addLast(T middle) {
        IntNode t=new IntNode(null,middle,null);
        t.prev=sentA.prev;
        t.next=sentA;
        sentA.prev.next=t;
        sentA.prev=t;
        size+=1;
    }

    /*public boolean isEmpty(){
        return size==0;
    }*/

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode t=sentA.next;
        if(t==sentA){
            System.out.println("null");
        }
        while(t!=sentA){
            System.out.println(t.item);
            t=t.next;
        }
    }

    public T removeFirst() {
        if(size==0){
            return null;
        }
        else{
            T val=sentA.next.item;
            sentA.next.next.prev=sentA;
            sentA.next=sentA.next.next;
            size-=1;
            return val;
        }

    }

    public T removeLast() {
        if(size==0){
            return null;
        }
        else{
            T val=sentA.prev.item;
            sentA.prev=sentA.prev.prev;
            sentA.prev.next=sentA;
            size-=1;
            return val;
        }

    }

    public T get(int index){
        if(size==0&&index+1>size){
            return null;
        }
        IntNode t=sentA.next;
        int i=0;
        while(i<index){
            t=t.next;
            i++;
        }
        return t.item;
    }

    public T helper(IntNode t,int index){
        if(index==0){
            return t.item;
        }
        else {
            return helper(t.next,index-1);
        }

    }
    public T getRecursive(int index){
        if(size==0&&index+1>size){
            return null;
        }
        IntNode t=sentA.next;
        return helper(t,index);
    }
}
package deque;
import org.junit.Test;
import static org.junit.Assert.*;
public class ArrayDeque<T> implements Deque<T>{
    public T[] items;//the way of initialize is from 2.5
    public int size;
    public int nextFirst;
    public int nextLast;
    public int capacity;//different from 'size',no less than it
    public ArrayDeque(){
        size=0;
        capacity=8;
        items=(T[]) new Object[capacity];
        nextFirst=0;
        nextLast=1;
    }
    public void addFirst(T front) {
        //remember to resize
        if (size==capacity){
            resize(0);
        }
        //to do
        items[nextFirst]=front;
        if (nextFirst==0){
            nextFirst=capacity-1;
        }
        else{
            nextFirst--;
        }
        size+=1;
    }

    private void resize(int i) {
        if (i==0){
            T[] tmp=(T[]) new Object[capacity*2];
            int t=(nextFirst+1)%capacity;
            int k=0;
            /*while(t!=nextLast){
                tmp[k++]=items[t];
                t=(t+1)%capacity;
            }*/

            for (int j=0;j<size;j++){
                tmp[j]=items[(t+j)%capacity];
            }
            items=tmp;

            capacity*=2;
            nextLast=size;
            nextFirst=capacity-1;
        }
        if(i==1){
            T[] tmp=(T[]) new Object[capacity/2];
            int t=(nextFirst+1)%capacity;
            int k=0;
            /*while(t!=nextLast){
                tmp[k++]=items[t];
                t=(t+1)%capacity;
            }*/
            for (int j=0;j<size;j++){
                tmp[j]=items[(t+j)%capacity];
            }
            items=tmp;

            capacity/=2;
            nextFirst=capacity-1;
            nextLast=size;
        }
    }

    public void addLast(T middle) {
        if (size==capacity){
            resize(0);
        }
        items[nextLast]=middle;
        nextLast=(nextLast+1)%capacity;
        size+=1;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int tmp=(nextFirst+1)%capacity;
        /*while(tmp!=nextLast){
            System.out.println(items[tmp]);
            tmp=(tmp+1)%capacity;
        }*/
        for (int i=0;i<size;i++){
            System.out.println(items[(tmp+i)%capacity]);
        }
    }

    public T removeFirst() {
        if (size<=capacity/4){
            resize(1);
        }
        if(size==0){
            return null;
        }
        else {
            nextFirst=(nextFirst+1)%capacity;
            T t=items[nextFirst];
            items[nextFirst]=null;
            size-=1;
            return t;
        }
    }

    public T removeLast() {
        if (size<=capacity/4){
            resize(1);
        }
        if(size==0){
            return null;
        }
        else{
            nextLast=(nextLast-1+capacity)%capacity;
            T t=items[nextLast];
            items[nextLast]=null;
            size-=1;
            return t;
        }
    }

    public T get(int i) {
        if(i+1>size){
            return null;
        }
        else{
            return items[(nextFirst+1+i)%capacity];
        }
    }
}
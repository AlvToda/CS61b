package deque;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;
public class MaxArrayDeque<T> extends ArrayDeque<T>{
    //public ArrayDeque<T> deque;
    public Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c){
        //deque=new ArrayDeque<T>();
        comparator=c;
    }
    public T max(){
        //compare by default comparator
        if(isEmpty()){
            return null;
        }
        int Max_index=0;
        for(int i=0;i<size();i++){
            if(comparator.compare(get(i),get(Max_index))>0){
                 Max_index=i;
            }
        }
        return get(Max_index);
    }
    public T max(Comparator<T> c){
        if(isEmpty()){
            return null;
        }
        int Max_index=0;
        for(int i=0;i<size();i++){
            if(c.compare(get(i),get(Max_index))>0){
                Max_index=i;
            }
        }
        return get(Max_index);
    }


}
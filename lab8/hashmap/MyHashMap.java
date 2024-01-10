package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int TableSize=16;
    private double loadFactor=0.75;

    /*the number of key-value mappings in this map. */
    private int size=0;
    private HashSet<K> keySet = new HashSet<>();

    /** Constructors */
    public MyHashMap() {
       buckets= createTable(TableSize);

    }

    public MyHashMap(int initialSize) {
        TableSize=initialSize;
        buckets=createTable(initialSize);
    }

    /**
     * TODO: MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        TableSize=initialSize;
        loadFactor=maxLoad;
        buckets= createTable(initialSize);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key,value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
       buckets=createTable(TableSize);
       keySet=new HashSet<>();
       size=0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        //根据key获得index
        int index=Math.floorMod(key.hashCode(), TableSize);
        if(buckets[index]==null){
            return null;
        }else{
            for(Node n:buckets[index]){
                if(n.key.equals(key)){
                    return n.value;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        //根据key获得index
        int index=Math.floorMod(key.hashCode(), TableSize);
        //如果对应的bucket为空，则创建一个（类型可以是arraylist，也可以是set）
        if(buckets[index]==null){
            buckets[index]=createBucket();
            buckets[index].add(createNode(key,value));
            keySet.add(key);
            size+=1;
        }else {
            if (get(key) == null) {
               buckets[index].add(createNode(key,value));
                keySet.add(key);
               size+=1;
            }else{
                for (Node n:buckets[index]) {
                    if(key.equals(n.key)){
                        n.value=value;
                        //不涉及size的加减，因此直接退出就可以
                        return;
                    }
                }
            }
        }
        //TODO: 判断是否需要resize:
        // The load factor (# items / # buckets) should always be <= loadFactor
        if((double) size /TableSize>=loadFactor){
            Resize();
        }
    }
    private void Resize(){
        //创建TableSize为2倍的tmp，然后根据buckets里面的key将对应的节点放到对应的位置
        Collection<Node>[] tmp=createTable(TableSize*2);
        for(K key:this.keySet){
            int Newindex=Math.floorMod(key.hashCode(), TableSize*2);
            if(tmp[Newindex]==null){
                tmp[Newindex]=createBucket();
            }
            //这里的get需要用到bucket
            tmp[Newindex].add(createNode(key,get(key)));
        }
        buckets=tmp;
        TableSize*=2;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        int index=Math.floorMod(key.hashCode(), TableSize);
        V v;
        if((v=get(key))!=null){
            //删除
            for (Node n:buckets[index]) {
                if(n.key.equals(key)){
                    buckets[index].remove(n);
                    break;
                }
            }
            size-=1;
            keySet.remove(key);
        }
        return v;
    }

    @Override
    public V remove(K key, V value) {
        V v=get(key);
        if(value.equals(v)){
            int index=Math.floorMod(key.hashCode(), TableSize);
            //删除
            for (Node n:buckets[index]) {
                if(n.key.equals(key)){
                    buckets[index].remove(n);
                    break;
                }
            }
            size-=1;
            keySet.remove(key);
            return value;
        }
        return null;
    }
    @Override
    public Iterator<K> iterator() {
        return null;
    }

}

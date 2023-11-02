package bstmap;
import java.lang.Comparable;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{

    public static class BST<K extends Comparable<K>, V>{
        private K key;
        private V value;
        private BST<K,V> left;
        private BST<K,V> right;
        public BST(K key,V value,BST<K,V> left,BST<K,V> right){
             this.key=key;
             this.value=value;
             this.left=left;
             this.right=right;
        }
        public BST(K key,V value){
            this.key=key;
            this.value=value;
        }
        public BST findKey(BST T, K sk){
            if(T==null){
                return null;
            }else if(sk.equals(T.key)){
                return T;
            }else if(T.key.compareTo(sk)>0){
                return findKey(T.left,sk);
            }else{
                return findKey(T.right,sk);
            }
        }
        /*public BST findValue(BST T,V sv){
            if(T==null){
                return null;
            }else if(sv.equals(T.value)){
                return T;
            }else if(T.value.compareTo(sv)>0){
                return findValue(T.left,sv);
            }else{
                return findValue(T.right,sv);
            }
        }*/
        public BST insert(BST T, K ik,V iv) {
            if(T ==null){
                return new BST(ik,iv);
            }
            else if(T.key.compareTo(ik)>0){
                T.left=insert(T.left,ik,iv);
            }
            else if(T.key.compareTo(ik)<0){
                T.right=insert(T.right,ik,iv);
            }
            return T;
        }
        public BST delete(BST T,K dk){
            if(T==null){
                return null;
            }
            else if(T.key.compareTo(dk)>0){
                T.left=delete(T.left,dk);
            }
            else if(T.key.compareTo(dk)<0){
                T.right=delete(T.right,dk);
            }
            else{
                if(T.left==null&&T.right==null){
                    return null;
                }
                //two child
                else if(T.left!=null&&T.right!=null){
                    BST r=T.right;
                    while(r.left!=null){
                        r=r.left;
                    }

                    T.key=r.key;
                    T.value=r.value;
                    T.right=delete(T.right, (K) r.key);
                    return T;
                }
                else{
                    return (T.left==null)? T.right:T.left;
                }

            }
            return T;
        }


    }
    public BST root;
    private int size;
    public BSTMap(){
        root=null;
        size=0;
    }
    /** Removes all the mappings from this map. */
    public void clear(){
       root=null;
       size=0;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key){
        if(root==null){
            return false;
        }
        return root.findKey(root,key)!=null;
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key){
        if (root==null){
            return null;
        }
        if (root.findKey(root,key)==null){
            return null;
        }
        V v= (V) root.findKey(root,key).value;
        return v;
    }

    /* Returns the number of key-value mappings in this map. */
    public int size(){
        return size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value){
        size+=1;
        if (root==null){
            root=new BST(key,value);
            return;
        }
        root.insert(root,key,value);

    }

    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * TODO: If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. TODO: If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. TODO: If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
    public void printInOrder(){
        Helper_printInOrder(root);
    }
    public void Helper_printInOrder(BST T){
        if(T==null){
            return;
        }
        Helper_printInOrder(T.left);
        System.out.println("key: " + T.key + " value: " + T.value);
        Helper_printInOrder(T.right);
    }

}

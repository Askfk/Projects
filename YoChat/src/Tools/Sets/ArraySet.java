package Tools.Sets;

import java.util.*;

/**
 * Customized Set, can get elements by given index
 *
 * @author Yiming Li
 * @version 2020-03
 */
public class ArraySet<E>{

    private Set<E> set;

    public ArraySet(){
        set = new HashSet<E>();
    }

    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public boolean contains(E o) {
        return set.contains(o);
    }

    public Iterator iterator() {
        return set.iterator();
    }

    public Object[] toArray() {
        return set.toArray();
    }

    public boolean add(E o) {
        return set.add((E) o);
    }

    public boolean remove(E o) {
        return set.remove(o);
    }

    public boolean addAll(Collection c) {
        return set.addAll(c);
    }

    public void clear() {
        set.clear();
    }

    public boolean removeAll(Collection c) {
        return set.removeAll(c);
    }

    public boolean retainAll(Collection c) {
        return set.retainAll(c);
    }

    public boolean containsAll(Collection c) {
        return set.containsAll(c);
    }

    public E[] toArray(E[] a) {
        return set.toArray(a);
    }

    public E get(int index){
        if(index < set.size()){
            int i = 0;
            for(E o : set){
                if(i == index)
                    return o;
                i++;
            }
        }
        else
            throw new IndexOutOfBoundsException();
            return null;
    }
}

package practica5;

import practica4.EDDoubleLinkedList;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class EDLinkedHashSet<T> implements Set<T> {
    protected class Node {
        T data;
        Node next = null;
        Node prev = null;

        Node(T data) {
            this.data = data;
        }
    }

    static final private int DEFAULT_CAPACITY = 10;
    static final private int DEFAULT_THRESHOLD = 7;

    Node[] table;
    boolean[] used;
    int size = 0;
    int dirty = 0;
    int rehashThreshold;
    Node first = null;
    Node last = null;

    public EDLinkedHashSet() {
        table = new EDLinkedHashSet.Node[DEFAULT_CAPACITY];
        used = new boolean[DEFAULT_CAPACITY];
        rehashThreshold = DEFAULT_THRESHOLD;
    }

    public EDLinkedHashSet(Collection<T> col) {
        table = new EDLinkedHashSet.Node[DEFAULT_CAPACITY];
        used = new boolean[DEFAULT_CAPACITY];
        rehashThreshold = DEFAULT_THRESHOLD;

        addAll(col);
    }

    /**
     * Calcula un código de dispersión mayor que cero y ajustado al tamaño de <code>table</code>.
     * @param item  Un valor cualquiera, distinto de <code>null</code>.
     * @return Código de disersión ajustado al tamaño de la tabla.
     */
    int hash(T item) {
        return (item.hashCode() & Integer.MAX_VALUE) % table.length;
    }

    /**
     * Realiza la ampliación de las tabla y la redispersión de los elementos si se cumple la condición de que
     * <code>dirty > rehashThreshold</code>. La tabla  dobla su tamaño. El vector <code>used</code> y
     * <code>rehashThreshold</code> se modifican de forma análoga.
     */
    private void rehash() {
        // TODO EJERCICIO 2
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object item) {
        if (item == null)
            throw new NullPointerException();

        int code = hash((T) item);

        while(used[code] == true) {
            if (table[code]!=null && table[code].data.equals(item))
                return true;

            code = (code + 1) % table.length;
        }

        return false;
    }

    @Override
    public Iterator<T> iterator()  { throw new UnsupportedOperationException(); }


    @Override
    public Object[] toArray() {
        Object[] v = new Object[size];

        Node ref = first;
        int i = 0;
        while (ref != null) {
            v[i] = ref.data;
            ref = ref.next;
            i++;
        }

        return v;
    }

    @Override
    public <T1> T1[] toArray(T1[] a)  {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T item) {
        if (item == null)
            throw new NullPointerException();

        int code = hash(item);
        int  free = -1;

        while (used[code]) {
            if (table[code] == null && free == -1)
                free = code;

            if (table[code]!= null && table[code].data.equals(item))
                return false;

            code = (code + 1) % table.length;
        }

        if (free == -1) {
            used[code] = true;
            dirty++;
        } else
            code = free;


        Node n = new Node(item);
        table[code] = n;
        if (last == null)
            first = last = n;
        else {
            n.prev = last;
            last.next = n;
            last = n;
        }
        size++;
        rehash();

        return true;
    }

    @Override
    public boolean remove(Object item) {
        //Aplicar el HASH
        int index = hash((T) item);

        // Recorer la tabla
        while ( used[index]){

            // Comprobar si es
            if (table[index] != null && item.equals(table[index])){
                // Eliminar de table
                table[index].prev.next = table[index].next;
                table[index].next = table[index].prev;
                // Cambiar el size
                size--;
            }
            index = (index +1) % table.length;
        }

        return  false;
    }

    @Override
    public boolean containsAll(Collection<?> c) { throw new UnsupportedOperationException(); }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        int s = size();
        for(T item: c)
            add(item);

        return (s != size());
    }

    @Override
    public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }

    @Override
    public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }


    @Override
    public void clear() {
        size = dirty  = 0;
        first = last = null;

        for (int i=0; i < table.length; i++) {
            used[i] = false;
            table[i] = null;
        }
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Table: {");
        boolean coma = false;
        for (int i = 0; i < table.length; i++){
            if (table[i] != null) {
                if (coma)
                    sb.append(", ");
                sb.append(i + ": " + table[i].data);
                coma = true;
            }
        }

        sb.append("}\n");

        sb.append("Insertion order: {");
        Node ref = first;
        coma = false;
        while (ref != null) {
            if (coma)
                sb.append(", ");
            sb.append(ref.data);
            ref = ref.next;
            coma = true;
        }
        sb.append("}\n");
        sb.append("size: " + size);
        sb.append(", capacity: " + table.length);
        sb.append(", rehashThreshold: " + rehashThreshold);

        return sb.toString();
    }

}

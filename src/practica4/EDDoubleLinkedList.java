package practica4;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
* Implementación incompleta de una lista usando una cadena no circular de nodos 
* doblemente enlazados.
*/
public class EDDoubleLinkedList<T> implements List<T> {
    private class Node {
        private T data;
        private Node next;
        private Node prev;

        public Node(T data) { this.data = data;};
    }

    private Node first = null;
    private Node last = null;
    private int size = 0;

    public EDDoubleLinkedList(Collection<T> col) {
        for (T elem: col) {
            Node n = new Node(elem);
            if (first == null)
                first = last = n;
            else {
                n.prev = last;
                last.next = n;
                last = n;
            }
        }
        size = col.size();
    }

    
     /**
     * Invierte el orden de los elementos de la lista.
     */
    public void reverse() {
        // Comprovar que no este vacia y que size!=1
        if(size != 0 && size !=1){
            // Crear un VALOR de nodo auxiliar
            T valor_inicio;
            // Los nodos para hacer el intercambio
            Node actual, inicio;
            // Contador por donde empezar
            int empezar_cont = 0;
            // Contador donde terminar
            int finalizar_cont = size-1;

            // Dar tantas vuetas como nodas alla
            for (int i = 0; i < size/2; i++){

                actual = first;

                // Ir hasta la posicion de inicio
                if (empezar_cont != 0){ // Comprovar que no sea el 1º caso
                    for (int t =0; t < empezar_cont; empezar_cont++){
                        actual = actual.next;
                    }
                }
                empezar_cont++;

                //Guardar los datos para sustituir despues
                inicio = actual;
                valor_inicio = actual.data;

                // Llegar hasta el final (el que queremos sustituir)
                for (int a = 0; a < finalizar_cont; a++){
                    actual = actual.next;
                }
                // Camviar datos
                inicio.data = actual.data;
                actual.data = valor_inicio;
                finalizar_cont--;
            }

        }
    }

    /**
     *  Añade los elementos de la lista intercalándolo con la lista actual.
     */
    public void shuffle(List<T> lista) {
        //Comprovar que la lista que nos pasan no este vacia
        if (lista.size() != 0){

            // Crear datos para implementar los datos
            Node actual = this.first;
            T valor;

            // Comprovar que no este vacia y que size!=1
            if(size == 0){
                if (lista.size() > 1){
                    Node n = new Node(lista.get(0));
                    first = n;
                    Node actual2 = this.first;
                    size++;

                    // Añadir el resto de elementos
                    for (int i = 1; i < lista.size(); i++){
                        insertAfter(actual2, lista.get(i));
                        actual2 = actual2.next;
                    }
                }
                else {
                    insertAfter(actual, lista.get(0));
                }

            }
            // Tiene mas elemetos
            else{
                // Añadir el resto de elementos
                for (int i = 0; i < lista.size(); i++){

                    insertAfter(actual, lista.get(i));
                    actual = actual.next;

                    // Cuando se añde un elemento, y luego en this habian mas hay que avanzar el que he ingresado
                    if (actual.next != null){
                        actual = actual.next;
                    }

                }
            }

        }

    }


    private Node insertAfter(Node node, T value){
        Node n = new Node(value);

        // Insertar en una cadena vacia
        if (first == null || node == null){
            first = n;
        }
        // Insertar antes de un elemento dado
        else {
            // Dar valor a los campos next y prev de los campos que estamos creando
            n.next = node.next;
            n.prev = node;

            // Cambiar los enlaces anterior y posterior que queremos insertar
            node.next = n;
        }

        size++;

        return n;
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
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<T> iterator() { throw new UnsupportedOperationException(); }

    @Override
    public Object[] toArray() {
        Object[] v = new Object[size];

        Node n = first;
        int i = 0;
        while(n != null) {
            v[i] = n.data;
            n = n.next;
            i++;
        }

        return v;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        first = last = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(int index, T element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, T element) {
        insertAfter(first,element);
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("");

        if (isEmpty())
            sb.append("[]");
        else {
            sb.append("[");
            Node ref = first;
            while (ref != null) {
                sb.append(ref.data);
                ref = ref.next;
                if (ref == null)
                    sb.append("]");
                else
                    sb.append(", ");
            }
        }

        sb.append(": ");
        sb.append(size);

        return sb.toString();
    }
}

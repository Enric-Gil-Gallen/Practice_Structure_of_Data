//package pruebas;
//
//import org.w3c.dom.Node;
//
//public class Boletin3 {
//
//    // EJERCICIO 3.12 ---> Add Tabla de dispersión -- Encadenamiento
//
//    public class ChainedHashBag <T > implements Set <T > {
//        private class Node {
//            public T data;
//            public Node next = null;
//            public Node prev = null;
//
//            public Node(T item) {
//                data = item;
//            }
//        }
//
//        private Node[] table;
//        private int size;
//        private Node first = null;
//        private Node last = null;
//
//        private int hash(T item) {}
//
//        private void rehash() {}
//
//    // Ejercicio
//    public boolean add(T item){
//        // Comprobar de que no este vacio el item que nos pasan
//        if (item == null){
//            return  false ;
//        }
//        // Optener posocion SIEMPRE
//        int index = hash (item);
//
//        //Crear el nuevo nodo para operar
//        Node nuevo = new Node()(item);
//
//        // CASO 0 ----> La lista vacia
//        if (size == 0){
//            first = last = nuevo;
//            table[index ] =nuevo
//        }
//        // CAS0 1 --> La posición ya esta -- hay colisión
//        // Tenemos que ponerlo delante del que esta
//        else if{
//            Node aux = table[inex];
//            nuevo.next = aux;
//            nuevo.prev = aux.prev;
//
//            // Comprobar si es el primero
//            if (aux.prev != null){
//                aux.prev.next = nuevo;
//            }
//            else {
//                first = nuevo;
//            }
//
//            aux.prev = nuevo;
//            // Que la tabla apunte al nuevo elemeto
//            table[index] = nuevo;
//        }
//        // CASO 2 --> table(index) == null
//        else{
//            // Recorer la tabla hacia atras, buscando uno que sea distinto de null
//            // Hasta encontrar alguno que no sea null
//            // Puede que no lo alla
//            int i = index;
//            while (i > 0 && table[i]==null){
//                // Primer caso ,  No se encuentra no alla 0
//
//                if (table[i]==null) i--{ // No hay anterior
//                    nuevo,next = first;
//                    first.prev = nuevo;
//                    first = nuevo;
//                    table[index] = nuevo;
//                }
//                else{
//                    // Recorer la nueva lista
//                    Node = table[i];
//                    Node ant = null;
//                    while (aux!=null && hass(aux.data)==i){
//                        ant = aux;
//                        aux=aux.next;
//                    }
//                    nuevo.next = aux;
//                    nuevo.prev = ant;
//                    ant.next = nuevo;
//                    if (aux != null){
//                        aux.prev = nuevo;
//                    }
//                    else{
//                        last = nuevo;
//                    }
//                    table(index) = nuevo;
//                }
//            }
//        }
//        size++;
//        rehash();
//        return true;
//    }
//}
//}
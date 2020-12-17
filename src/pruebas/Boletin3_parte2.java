//package pruebas;
//
//import java.util.List;
//
//public class Boletin3_parte2 {
//
//    public class DoubleLinkedList <T > implements List<T > {
//        private class Node {
//            public T data ;
//            public Node next = null ;
//            public Node prev = null ;
//        }
//        private Node head = null ;
//        private Node tail = null ;
//        private int size = 0;
//        public void DoubleLinkedList () {}
//
//
//        //Empieza el ejecicio 3.13
//
//        public List <T> trim(int i){
//            List<T> res = new DoubleLinkedList();
//            // Caso 1
//            if (i <0){
//                return res;
//            }
//            // Caso 2
//            if (i*2 >= size){
//                // Apuntar a las mismas referencias
//                res.head = head;
//                res.tail = tail;
//                res.size = size;
//                // Ahora podemos poner la lista original limpia
//                head = tail = null;
//                size = 0;
//                return res;
//            }
//            // Caso 3
//            res.head = head;
//            res.tail = tail;
//
//            // Poner lo que les toca
//            for (j = 0; j < i; j++){
//                head = head.next;
//                tail = tail.prev;
//            }
//
//            // Desenganchar
//            head.prev.next = tail.next;
//            tail.next.prev = head.prev;
//
//            head.prev = null;
//            tail.next = null;
//
//            res.size = i*2;
//            size -= res.size();
//            return  res;
//        }
//    }
//
//
//
//}

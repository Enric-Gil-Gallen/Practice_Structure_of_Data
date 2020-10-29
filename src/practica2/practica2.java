package practica2;

import java.util.*;

public class practica2 {

    /** Comprueba si dos listas de String son equivalentes.
     *
     * Dos listas son equivalentes si contienen los mismos elementos y la misma cantidad de ellos.
     * @param l1    Primera lista
     * @param l2    Segunda lista
     * @return <code>true</code> si las listas son equivalentes. <code>false</code> en caso contrario.
     */
    static public boolean equivalentes(List<String> l1, List<String> l2) {
       if (l1.size() != l2.size()){
           return false;
       }else {
//           for (int i = 0; i < l1.size(); i++){
//               for (int t = 0; t < l2.size(); t++){
//                   if (l1.indexOf(l2.get(t)) == -1{
//                       return false;
//                   }
//               }
//           }

//         SI SE ENCUENTRA UN ELEMENTO QUE ESTA EN L1 EN L2
//         PASALO A L1 Y ELIMINALO DE L2
//         SI AL ACABAR EL TAMAÑO DE L1 == L1 + L2 (INICIAL) == TRUE != FALSE
           int total = l1.size() + l2.size();
           int num;
           for (int i = 0; i < l1.size(); i++){
               num = l2.indexOf(l1.get(i));
               if (num != -1){
                    l1.add(l2.get(num));
                    l2.remove(num);
               }
           }

           System.out.println(total);
           System.out.println(l1.size());
           if (total == l1.size()){
               return true;
           }

//           int total = l1.size() + l2.size();
//           int num=0;
//           for (int i = 0; i < l1.size(); i++){
//               if (l2.indexOf(l1.get(i)) != -1){
//                   num++;
//               }
//           }
//
//           System.out.println(total);
//           System.out.println(num);
//           if (total == num){
//               return true;
//           }

//         PROBAR USANDO EL METODO CONTAINS -- Por si no se puede de la forma anterior
       }
        return false;
    }

    /** Invierte el orden de los elmentos de una lista.
     *
     * @param iter Un iterador de la lista. Puede estar en cualqueir posición de la lista.
     */
    static public void invierte(ListIterator<String> iter) {
        //TODO completa la implmentación
    }


    /** Ordena los elementos de una lista de menor a mayor
     * @param l     La lista
     * @return      Una nueva lista con los mismo elementos, pero ordenados.
     */
    static public List<Integer> ordenar(List<Integer> l) {
        //TODO completa la implmentación
        return l;
    }

}

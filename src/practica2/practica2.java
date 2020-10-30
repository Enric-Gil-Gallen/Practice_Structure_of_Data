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
           List<String> copia = new LinkedList<>(l2);
           int num;
           for (int i = 0; i < l1.size(); i++){
               num = copia.indexOf(l1.get(i));
               if (num != -1){
                    copia.remove(num);
               }
           }

           if (0 == copia.size()){
               return true;
           }
       }
        return false;
    }

    /** Invierte el orden de los elmentos de una lista.
     *
     * @param iter Un iterador de la lista. Puede estar en cualqueir posición de la lista.
     */
    static public void invierte(ListIterator<String> iter) {
        if (iter.nextIndex() == -1){
            // Se crear una lista para almacenar los datos al reves
            LinkedList<String> list = new LinkedList<String>();

            // Dejamos al final el Iterador
            while (iter.hasNext()) {
                iter.next();
            }

            // Recorerla lista de forma inversa y almacenar los datos en la lista
            while(iter.hasPrevious()){
                list.add(iter.previous());
            }
            // Recorer la lista hacia delante y con el set modificar los datos
            while (iter.hasNext()){
                iter.next();
                iter.set(list.getFirst());
            }
        }

    }


    /** Ordena los elementos de una lista de menor a mayor
     * @param l     La lista
     * @return      Una nueva lista con los mismo elementos, pero ordenados.
     */
    static public List<Integer> ordenar(List<Integer> l) {

        // Creo una lista la cual sera la solución y el NUM_L que almacenara el Nº de la 1º lista
        List<Integer> sol = new LinkedList<Integer>();

        int num_L;

        // Recoro la lista Inicial
        for (int i = 0; i < l.size(); i++) {
            num_L = l.get(i);

            int j = 0;
            while (j < sol.size() && num_L > sol.get(j)) {
                j++;
            }
            sol.add(j, num_L);
        }

        return sol;
    }

}

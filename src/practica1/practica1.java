package practica1;

import java.util.*;

public class practica1 {
    /**
     *  Método que toma dos conjuntos de enteros y separa los elementos entre aquellos que sólo aparecen una vez
     *  y aquellos que aparecen repetidos. El método modifica los conjuntos que toma como parámetros.
     * @param unicos    A la entrada un conjunto de enteros. A la sálida los elementos que solo aparecen en uno de
     *                  los conjuntos.
     * @param repetidos A la entrada un conjunto de enteros. A la salida los elementos que aparecen en ambos conjuntos.
     */
    static public void separa(Set<String> unicos, Set<String> repetidos) {

        for (String repe: repetidos) {
            if(unicos.contains(repe)){
                unicos.remove(repe);
            }else {
                unicos.add(repe);
            }
        }

        for (String unic: unicos) {
            if (repetidos.contains(unic)){
                repetidos.remove(unic);
            }
        }

    }

    /**
     *  Toma un iterador a una colección de enteros positivos y devuelve como resultado un conjunto con aquellos elementos
     *  de la colección que no son múltiplos de algún otro de la colección. Los ceros son descartados
     * @param iter  Iterador a una colección de enteros
     * @return Conjunto de de enteros.
     */
    static public Set<Integer> filtra(Iterator<Integer> iter) {
        Set<Integer> sol = new HashSet<>();
        Set<Integer> sol2 = new HashSet<>();

//      RELLENO SOL CON TODOS LOS DATOS POSIBLES
        int num_actual;
        while (iter.hasNext()){
            num_actual = iter.next();
            boolean esta = true;
            if (num_actual == 1){ // EN ESTE CASO EL 1 EL DIVISOR  UNICO
                sol.clear();
                sol.add(1);
                return sol;
            }else if (num_actual != 0){ // NO SE PUEDE VIVIDIR POR 0 !!!! CUIDADO !!!
                for (Integer num : sol){
                    if (sol.contains(num_actual) || num_actual % num == 0){
                        esta = false;
                    }
                }
                if (esta){
                    sol.add(num_actual);
                    esta = true;
                }
            }
        }

//      COMPROVAR CADA ELEMENTO DE LA LISTA
//          USANDO OTRAVEZ EL ITERADOR
        int num_actual2;
        int num_sol = 0;
        boolean noDivisor;
        Iterator<Integer> iter2 = sol.iterator();

        while (iter2.hasNext()){
            num_actual2 = iter2.next();
            noDivisor = true;

            for (Integer num : sol){
                if (num != num_actual2){
                    if (num_actual2 %  num == 0){
                        noDivisor = false;
                        break;
                    }
                }
            }

            if (noDivisor){
                sol2.add(num_actual2);
            }
        }

        return sol2;
    }

    /**
     * Toma una colección de conjuntos de <i>String</i> y devuelve como resultado un conjunto con aquellos <i>String </i>
     * Que aparecen en al menos dos conjuntos de la colección.
     * @param col Coleccion de conjuntos de <i>String</i>
     * @return Conjunto de <i>String</i> repetidos.
     */
    static public Set<String> repetidos(Collection<Set<String>> col) {
//      CREAR LA SOLUCION
        Set<String> sol = new HashSet<>();
        ArrayList<String> lista = new ArrayList(col.size());

//      CASOS ESPECIALES
        if (col.isEmpty()){
            return sol;
        }

//      ITERADOS PARA PASAR LOS DATOS
        for (Set<String> col2: col) {

            Iterator<String> it = col2.iterator();

            // COJER TODA LA COLECCION Y METERLA EN UNA LISTA
            while (it.hasNext()){
                String cadena = it.next();

                String[] numeros = cadena.split(",");
                for (int i = 0; i < numeros.length; i++){
                    lista.add(numeros[i]);
                }
            }

        }

        for (int i = 0; i< lista.size(); i++)
        {
            for (int t = i+1; t < lista.size(); t++){
                if (lista.get(t).equals(lista.get(i))){
                    sol.add(lista.get(i));
                }
            }
        }
        return sol;
    }
}

package practica7;

import prac6.BinaryExpressionTree;

import java.io.IOException;
import java.util.*;

public class HuffmanTree {
    private static class  Frequency {
        public char c;
        public double f;

        public Frequency( char c, double freq) { this.c =  c; f = freq; }

        public String toString()
        {
            return "[" + c + ", " + f + "]";
        }

        public  boolean equals( Object o)  {
            if  (o == null)
                return false;

            if (o.getClass() != getClass())
                return false;

            Frequency f = (Frequency)o;

            System.out.println(c + " == " + f.c);
            return   c == f.c;
        }
    }

    EDBinaryNode<Frequency> root = null;

    //Builds a binaryTree for Huffman codification given the array of characters and its frequency
    public HuffmanTree(int[] freqs, char[] chars)
    {
        Comparator<EDBinaryNode<Frequency>> comparator = new NodeComparator();

        //build Huffman binary tree
        PriorityQueue<EDBinaryNode<Frequency>> queue = new PriorityQueue<>(256, comparator);

        for (int i=0; i<256; i++)
        {
            if (freqs[i] > 0) {
                Frequency d  = new Frequency(chars[i], freqs[i]);
                EDBinaryNode<Frequency> n = new EDBinaryNode<>(d);
                queue.add(n);
            }
        }

        while (queue.size() > 1)
        {
            EDBinaryNode<Frequency> n1 = queue.remove();
            EDBinaryNode<Frequency> n2 = queue.remove();

            Frequency d = new Frequency('\0',n1.data().f + n2.data().f);
            EDBinaryNode<Frequency> n3 = new EDBinaryNode<>(d, n1, n2);
            queue.add(n3);
        }

        root = queue.remove();
    }

    public static class NodeComparator implements Comparator<EDBinaryNode<Frequency>>
    {
        @Override
        public int compare(EDBinaryNode<Frequency> x, EDBinaryNode<Frequency> y)
        {
            // Assume both nodes can be null

            if (x == null && y == null)
            {
                return 0;
            }
            if (x.data().f < y.data().f)
            {
                return -1;
            }
            if (x.data().f > y.data().f)
            {
                return 1;
            }

            return 0;
        }
    }

    /** Construye un árbol de Huffman a partir de los carateres y sus codificaciones.
     * @param codes Map en el que por cada caracter se contiene la lista de 0s y 1s que lo codifica.
     */
    public HuffmanTree (Map<Character, List<Integer>> codes) {

       System.out.println(codes.keySet().toArray()[1]);
       System.out.println(codes.values());
       EDBinaryNode r;

       for (int i = 0; i <codes.size(); i++){

           // Cojo el valor y el camino a recorer
           Character letra = (Character) codes.keySet().toArray()[i];
           List<Integer> camino = codes.get(letra);

           r = root;

           // Ir creando todos los nodos
           // COMPROBAR SI YA EXISTEN
           for (int t = 0; t < camino.size(); t++){
               // Crear el nodo principal
               if (r == null){
//                   root = new EDBinaryNode<Character>();
               }

               // Colocar a la IZQ o DER y añadir donde toquen
               if (camino.get(t) == 0){

               }
               else{

               }
           }

           // Añadir valor al nodo final
       }
    }

    /** Dado un charácter c, devuelve la lista de 0s y 1s que lo codifica dentro del árbol. <cose>null</cose> en el
     *  caso de que el carácter no se encuentre en el árbol.
     * @param c El carácter
     * @return  Lista de 0s y 1s o <code>null</code>.
     */
    public List<Integer> findCode(char c) {
        // No sabemos donde esta
        // Si el parametro no esta
        // Los elementos estan en el nodo hoja

        // El arbol esta vacio
        if (root == null){
            return null;
        }

        List<Integer> sol = new LinkedList<Integer>();
        // Metodo privado que realiza la operacion
        return codificar(root,c, sol);
    }

    private List<Integer> codificar (EDBinaryNode<Frequency> r, char c, List<Integer> sol){

        // Estoy en nodo hoja
        if (r.isLeaf()){
            // Comprobar si es el la letra que buscamos
            if (r.data().c == c){
                return sol;
            }

            return null;

        }
        // No es nodo hoja -- El resultado anterior es != null
        else {
            // Compruebo si tiene Izquierda y hajo la llamada recursiva
            if (r.hasLeft()){

                if (codificar(r.left(), c, sol) != null){
                    sol.add(0, 0);
                    return sol;
                }
            }

            // Compruebo si tiene Derecha y hajo la llamada recursiva
            if (r.hasRight()){

                if (codificar(r.right(), c, sol) != null){
                    sol.add(0, 1);
                    return sol;
                }
            }

            return null;

        }

    }

    /** Dada un String compuesto  por 0s y 1s que representan la codificacion de uno o mas carácteres. Decodifica y
     * añade a los carateres a una lista.
     * @param str   0s y 1s
     * @param l     Lista conteniendo los caracteres decodificados.
     */
    public void decode(String str, List<Character> l)
    {

        EDBinaryNode<Frequency> r = root;


        for (int i = 0; i < str.length(); i++){

             // Compruebo a que lado he de ir
            if (str.charAt(i) == '0'){
                // Valor a comprobar
                r = r.left();

                // Es HOJA
                if (r.isLeaf()){
                    l.add(r.data().c);
                    r = root;
                }

            }
            else{
                // Valor a comprobar
                r = r.right();
                // Es HOJA
                if (r.isLeaf()){
                    l.add(r.data().c);
                    r = root;
                }

            }

        }

    }

    public static HuffmanTree createFromFile(String filename) throws IOException {
        Frequencies table = new Frequencies();
        HuffmanTree tree = null;

        table.loadFile(filename);
        tree = new HuffmanTree(table.frequenciesTable(),table.charsTable());

        return tree;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

       HuffmanTree tree = (HuffmanTree) obj;

       if (root == tree.root)
            return true;

       return root.equals(tree.root);
    }
}

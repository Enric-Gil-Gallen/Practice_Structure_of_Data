package prac6;

import java.util.*;

public class BinaryExpressionTree {
	
	//private prac6.BinaryTree<Character> exp = null;

	//cronstructor de árbol de expresiones a partir de una expresión notación potfijas completamente parentizada
			
	public static EDBinaryNode<Character> buildBinaryExpressionTree(String chain) {

		EDBinaryNode<Character> tree = null;
		if (chain == null || chain.length()==0)
			return tree;
		
		Stack<EDBinaryNode<Character>> pila = new Stack<EDBinaryNode<Character>>();
		boolean error = false;

		int i=0;

		while (i < chain.length() && !error) {
			Character c = chain.charAt(i);
			if (Character.isDigit(c)) {
				EDBinaryNode<Character> single = new EDBinaryNode<Character>(c);
				pila.push(single);
			}
			else if (isValidOperator(c)) {
				EDBinaryNode<Character> rightS=null, leftS=null;
				if (!pila.empty()) {
					rightS = pila.peek();
					pila.pop();
				}
				else error = true;  //wrong input chain
				if (!error) {
					if (!pila.empty()) {
						leftS = pila.peek();
						pila.pop();
					}
					else error = true; //wrong input chain
				}
				if (!error) {
					EDBinaryNode<Character> b = new EDBinaryNode<Character>(c,leftS,rightS);
					pila.push(b);
				}
			}
			else if (c!=' ') error = true; //wrong input chain, operator
			i++;  //next character in chain
		}
		if (!error) {
			if (!pila.empty()) {
				tree = pila.peek();
				pila.pop();
			}
			else error = true;; //wrong input chain
		}
		if (!pila.empty()) error=true; //stack must be empty at the end

		if (error ) return null; 
		else return tree;
	}

    static String toPostfix (String chain) {
        Stack<Character> pila = new Stack<Character>();
        String out = "";
        boolean error = false;
        if (chain != null) {
            int i = 0;

            while (i < chain.length() && !error) {
                Character c = chain.charAt(i);
                if (Character.isDigit(c)) out += c;
                else if (isValidOperator(c)) pila.push(c);
                else if (c == '(') pila.push(c);
                else if (c == ')') {
                    if (pila.isEmpty()) error = true;
                    boolean salir = false;
                    while (!pila.isEmpty() && !salir) {
                        char e = pila.pop();
                        if (e != '(') out += e;
                        else salir = true;
                    }
                    if (!salir) error = true;
                } else error = true;
                i++;
            }
        }
        if (error) {
            System.out.println("expressión de entrada incorrecta");
            out = null;
        }
        return out;
    }


    public static EDBinaryNode<Character> buildFromList(List<Character> l) {
		return buildFromList(l, 0);
	}

	private static EDBinaryNode<Character> buildFromList(List<Character> l, int current) {
		if (current >= l.size())
			return null;

		EDBinaryNode<Character> n = new EDBinaryNode<Character>(l.get(current));
		n.setLeft(buildFromList(l, current * 2 + 1));
		n.setRight(buildFromList(l, current * 2 + 2));

		return n;
	}



	/**
	 * Determines wether the tree is extended. That is that each node has either two or none siblings.
	 * @return <code>true</code> if extended.
	 */
	public static boolean isExtended(EDBinaryNode<Character> tree) {
		// Comprobar que el arbol este vacio
		if (tree == null){
			return true;
		}

		// Hoja
		if (tree.isLeaf()){
			return true;
		}
		// Tiene mas nodos
		else {
			// No tiene ninguno de los nodos
			if (!tree.hasLeft() || !tree.hasRight()){
				return false;
			}
			else {
				if (!isExtended(tree.right()) || !isExtended(tree.left())) {
					return false;
				} else {
					return true;
				}
			}
		}

	}


    /** Detemrines whether, on a expression tree, the digits are on the leaves and valid operators on the internal
     * nodes.
     * @param tree
     * @return <code>true</code> if digits are only on the leaves.
     */
	public static boolean digistsOnLeaves(EDBinaryNode<Character> tree) {
		// Comprobar que el arbol este vacio
		if (tree == null){
			return true;
		}

		// Solo tenta en nodo cabezera
		if (tree.isLeaf()){
			// El valor es correcto
			if (tree.data() >= '0' && tree.data() <= '9'){  // CUIDADO CON LAS PUTAS COMILLAS
				return true;
			}
			else {
				return false;
			}
		}
		// Comprobar las operaciones
		else {
			// Tiene 2 hijos
			if (tree.hasLeft() && tree.hasRight()){
				if (isValidOperator(tree.data())){
					if (digistsOnLeaves(tree.right()) && digistsOnLeaves(tree.left())) {
						return true;
					}
				}
				return false;
			}

		}
		return true;
	}

	private static boolean isValidOperator(Character c) {
		if (c=='+' || c=='-' || c=='*' || c=='/' || c=='^')
			return true;
		else return false;
	}
			

    /** Computes the result of executing the operation on a evaluation tree.
     * @param tree
     * @return The result of the evaluation.
     */
	public static float evaluate(EDBinaryNode<Character> tree) {
		// Comprobar que el arbol este vacio
		if (tree == null){
			return -1;
		}

		float result = 0;
		Character c = '+';

		// Hoja
		if (tree.isLeaf()){
			result = (operation(c, tree.data() - '0', result));
		}
		// Cabecera
		else {
			result = operation(tree.data(), evaluate(tree.left()), evaluate(tree.right())) ;
		}

		return result;
	}	

	private static float operation(Character c, float n1, float n2){
		switch (c){
			case '+':
				return  n1 + n2;
			case '-':
				return  n1 - n2;
			case '*':
				return  n1 * n2;
			case '/':
				return  n1 / n2;
			case '^':
				return (float) Math.pow(n1, n2);
		}
		return -1;
	}
    /** Returns a list with the result of crossing the nodes of a expression tree in inOrder. The list will be
     * completely parenthesised.
     * @param tree
     * @return The list
     */
	public static List<Character> asListInorder(EDBinaryNode<Character> tree) {
		// Comprobar que el arbol este vacio
		if (tree == null){
			return null;
		}
		// Inicio
		List<Character> resul = new LinkedList<>();

		// Hoja
		if (tree.isLeaf()){
			resul.add(tree.data());
		}
		// Cabecera
		else {
			if (tree.left().isLeaf()){

				resul.add('(');
				resul.add(tree.left().data());
				resul.add(tree.data());
				resul.add(tree.right().data());
				resul.add(')');
			}
			else {
				// Me falta tener claro los padres para colocar las uniones
				// tree.parent().data()

				resul.add('(');
				resul.addAll(asListInorder(tree.left()));

				// Importante -- Revisar
				if (tree.parent() != null){
					resul.add(tree.right().parent().data());
				}
				resul.addAll(asListInorder(tree.right()));
				resul.add(')');

				// Importante -- Revisar
				if (tree.parent() != null){
					resul.add(tree.parent().data());
				}

			}

		}

		//Final
		return resul;
	}

	// methods needed for toString
	private static StringBuilder repeated(char c, int times) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < times; i++)
			sb.append(c);

		return sb;
	}

	private static int depth(EDBinaryNode<Character> node) {
		if (node == null)
			return 0;

		int ld = depth(node.left()) + 1;
		int rd = depth(node.right()) + 1;

		return ld > rd ? ld : rd;
	}

	private static int createSpaces(EDBinaryNode<Character> tree, List<String> offset, List<String> separator) {
		int depth = depth(tree);

		offset.clear();
		separator.clear();

		if (depth < 1)
			return 0;

		int pad = 1;
		for (int i = 0; i < depth; i++) {
			offset.add(0, repeated(' ', pad -1 ).toString());
			pad = 2*pad+1;
			separator.add(0, repeated(' ', pad).toString());
		}

		return depth;
	}

	public static String toString(EDBinaryNode<Character> tree) {
		StringBuilder resultado = new StringBuilder();
		Queue<EDBinaryNode<Character>> q = new LinkedList<>();
		List<String> margen = new LinkedList<>();
		List<String> separacion = new LinkedList<>();
		int altura =  createSpaces(tree, margen, separacion);

		if (altura == 0) {
			resultado.append("------------\n");
			resultado.append(" Empty tree\n");
			resultado.append("------------\n");
			return resultado.toString();
		}

		StringBuilder barra = repeated('-',   ((1 << (altura-1)) * 4) - 3);

		int nactual = 0;
		int cuenta = 1;
		resultado.append(barra).append('\n').append(margen.get(nactual));
		q.add(tree);

		while (nactual < altura) {
			EDBinaryNode<Character> n = q.remove();
			String dato = " ";
			if (n != null)
				dato = n.data().toString();

			resultado.append(dato);
			cuenta--;
			if (cuenta > 0)
				resultado.append(separacion.get(nactual));
			else {
				resultado.append('\n');
				nactual++;
				cuenta = 1 << nactual;

				if (nactual < altura)
					resultado.append(margen.get(nactual));
				else
					resultado.append(barra).append('\n');
			}

			if (nactual < altura ) {
				if (n == null) {
					q.add(null);
					q.add(null);
				} else {
					q.add(n.left());
					q.add(n.right());
				}
			}
		}
		return resultado.toString();
	}
}

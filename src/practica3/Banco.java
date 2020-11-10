package practica3;

import java.util.*;

public class Banco {
    public HashMap<String, Integer> cuentas = new HashMap<>();                // Mapa con las cuentas y su saldos
    public HashMap<String, List<Transferencia>> desglose = new HashMap<>();  // Mapa con las trasnferencias por cada cuenta

    public Banco(List<String> codigos, List<Integer> saldos) throws IllegalArgumentException{

        //Comprobaciones previas
        if (codigos == null || saldos == null || codigos.size() != saldos.size()){
            throw new IllegalArgumentException("Los datos introducidos no son correctos");
        }

        String clave_Actual = "";
        Integer saldo_Actual = 0;

        // Recorer las 2 Listas
        for (int i = 0; i < codigos.size(); i++) {

            // Almacenar los valor actuales
            clave_Actual = codigos.get(i);
            saldo_Actual = saldos.get(i);

            //Comprobaciones previas

            if (clave_Actual == null || saldo_Actual==null){
                throw new IllegalArgumentException("Los datos introducidos no son correctos");
            }
            // Comprobar si exite ya, la clave que nos pasan
            if (cuentas.containsKey(clave_Actual)){
                cuentas.put(clave_Actual, cuentas.get(clave_Actual) + saldo_Actual);
            }
            else { // No exite la clave
                cuentas.put(clave_Actual, saldo_Actual);
            }
        }
    }

    /**
     * Realiza una una trasnferencia entres dos cuentas modificando su saldo. Guarda la transferencia en un histórico.
     *
     *
     * @param tr La transferencia. Los códigos de cuenta deben existir y ser distintos de <i>null</i>. La cuenta origen debe
     *           tener saldo positivo suficiente para realizar la transferencia.
     * @return <i>True</i> si la transferencia fue posible. ç
     * @throws <i>IllegalArgumentExcpetion</i> si alguna de las cuentas no existe, o el código es <i>null</i>.
     */

    public boolean asiento(Transferencia tr) {
        // La cuenta origen no existe
        if (!cuentas.containsKey(tr.origen) || !cuentas.containsKey(tr.destino) || tr.origen == null || tr.destino == null){
            throw new IllegalArgumentException("Alguna de las cuentas no existe");
        }
        else {

            // Comprobar ¿Ha que cuenta hay que transferirle el dinero ?
            if (tr.cantidad > 0){
                // Comprobar que se pueda efectuar la operación
                if (cuentas.get(tr.origen) < tr.cantidad){
                    return false;
                }

                // Traspasar el dinero
                cuentas.put(tr.origen, cuentas.get(tr.origen) - tr.cantidad);
                cuentas.put(tr.destino, cuentas.get(tr.destino) + tr.cantidad);

            }
            else{
                // Comprobar que se pueda efectuar la operación
                if (cuentas.get(tr.destino) < tr.cantidad*-1){
                    return false;
                }

                // Traspasar el dinero
                cuentas.put(tr.destino, cuentas.get(tr.destino) - tr.cantidad*-1);
                cuentas.put(tr.origen, cuentas.get(tr.origen) + tr.cantidad*-1);

            }

            // GURDAR TRANSFERENCIA ----- ORIGEN

            //Existe en DESGLOSE
            if (desglose.containsKey(tr.origen)) {
                desglose.get(tr.origen).add(tr);
            }
            // No Existe en DESGLOSE
            else {
                List<Transferencia> lista = new ArrayList<>();
                lista.add(tr);
                desglose.put(tr.origen, lista);
            }

            // GURDAR TRANSFERENCIA ----- DESTINO

            //Existe en DESGLOSE
            if (desglose.containsKey(tr.destino)) {
                desglose.get(tr.destino).add(tr);
            }
            // No Existe en DESGLOSE
            else {
                List<Transferencia> lista = new ArrayList<>();
                lista.add(tr);
                desglose.put(tr.destino, lista);
            }


            return true;
        }
    }

    /**
     * Devuelve el saldo de una cuenta
     * @param codigo Código de la cuenta. Debe ser distinto <i>null</i> y existir.
     * @return El saldo de la cuenta.
     * @throws <i>IllegalArgumentException</i> si el código de cuenta no es válido.
     */
    public int consulta(String codigo) {
        if (cuentas.containsKey(codigo) == false){
            throw new IllegalArgumentException("No existe la cuenta");
        }

        return cuentas.get(codigo);
    }

    /**
     *  Devuelve el histórico de transferencias entre dos cuentas.
     * @param primera Código válido de cuenta
     * @param segunda Código válido de cuenta
     * @return Lista de transferencias. El código <i> primera</i> siempre aparecerá como cuenta de origen de cada
     *          transferencia. En caso de el código de cuenta sea el mismo la lista estará vacía.
     * @throws <i>IllegalArgumentExcpetion</i> si alguno de los códigos de cuenta no son válidos.
     */
    public List<Transferencia> historico(String primera, String segunda) {
        // EXCEPCIONES
        if (!desglose.containsKey(primera) || !desglose.containsKey(segunda)){
            throw new IllegalArgumentException("Alguna de las cuentas no existe");
        }

        // Crear el resultado
        List<Transferencia> sol = new ArrayList<>();

        // Las cuentas son las mismas
        if (primera.equals(segunda)) {
            return sol;
        }

        // Recorer Transferencias
        List<Transferencia> list_origen = desglose.get(primera);
        List<Transferencia> list_destino = desglose.get(segunda);

        // Recoro todas las transferencias de PRIMERA
        for (int i = 0; i < list_origen.size(); i ++){

            // Compruebo el origen y el destino
            if (list_origen.get(i).origen.equals(primera) && list_origen.get(i).destino.equals(segunda)) {
                System.out.println("----------------------------");

                // Añado si coinciden origen y destino
                sol.add(list_origen.get(i));
            }
        }

        // Recoro todas las transferencias de SEGUNDA
        for (int i = 0; i < list_destino.size(); i ++){

            // Compruebo el origen y el destino
            if (list_destino.get(i).origen.equals(segunda) && list_destino.get(i).destino.equals(primera)) {
                System.out.println("----------------------------");
                // Añado si coinciden origen y destino
                list_destino.get(i).invertir();
                sol.add(list_destino.get(i));
            }
        }

        return sol;
    }

    public String toString() {
        StringBuilder bf = new StringBuilder("Banco - cuentas {\n") ;

        for (String cod: cuentas.keySet())
            bf.append("  " + cod + ": " +cuentas.get(cod)+ "\n");


        bf.append("} - size:" + cuentas.size());

        return bf.toString();
    }

    public String toStringDesglose() {
        StringBuilder bf = new StringBuilder("Banco - desglose {\n");

        for (String cod: desglose.keySet())
            bf.append("  " + cod + ": " + desglose.get(cod));


        bf.append("}");
        return bf.toString();
    }

}


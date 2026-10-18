/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jenifer Maribel Alamea Folres
 */
public class Recibo {

    private final String nombre;
    private final String ultimosCuatro;
    private final Retiro retiro;
    private final double saldoFinal;

    public Recibo(Cuenta cuenta, Retiro retiro) {
        this.nombre = cuenta.obtenerNombreT();
        this.ultimosCuatro = cuenta.obtenerCuatroDigitos();
        this.retiro = retiro;
        this.saldoFinal = cuenta.obtenerSaldo();
    }

    @Override
    public String toString() {
        return "========== BANCO AZUL ==========\n"
                + "         RECIBO DE RETIRO\n"
                + "================================\n"
                + "Titular: " + nombre + "\n"
                + "Cuenta: ****" + ultimosCuatro + "\n"
                + "Monto retirado: " + retiro.getMonto() + " MXN\n"
                + "Comisión: " + retiro.getComision() + " MXN\n"
                + "Saldo final: " + saldoFinal + " MXN\n"
                + "================================\n"
                + "Gracias por tu preferencia";
    }

    private String dinero(double valor) {
        return String.format(java.util.Locale.US, "$%,.2f MXN", valor);
    }
    
}

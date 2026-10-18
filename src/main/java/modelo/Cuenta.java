/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Jenifer Maribel Alamea Flores
 */
public class Cuenta {

    private final String nombreTitular;
    private final String numCuenta;
    private double saldo;

    public Cuenta(String nombreTitular, String numCuenta, double saldo) {

        if (nombreTitular == null || nombreTitular.isBlank()) {
            throw new IllegalArgumentException(
                "El nombre es obligatorio."
            );
        }

        if (numCuenta == null || numCuenta.length() < 4) {
            throw new IllegalArgumentException(
                "La cuenta debe tener al menos cuatro dígitos."
            );
        }

        if (saldo < 0) {
            throw new IllegalArgumentException(
                "El saldo inicial no puede ser negativo."
            );
        }

        this.nombreTitular = nombreTitular;
        this.numCuenta = numCuenta;
        this.saldo = saldo;
    }

    public String obtenerNombreT() {
        return nombreTitular;
    }

    public String obtenerNumC() {
        return numCuenta;
    }

    public String obtenerCuatroDigitos() {
        return numCuenta.substring(numCuenta.length() - 4);
    }

    public double obtenerSaldo() {
        return saldo;
    }

    public boolean puedeRetirar(double monto, double comision) {
        return monto > 0
                && comision >= 0
                && saldo >= monto + comision;
    }

    public void descontar(double monto, double comision) {
    if (monto <= 0 || comision < 0) {
        throw new IllegalArgumentException(
                "El monto o la comisión no son válidos."
        );
    }

    if (saldo < monto + comision) {
        throw new IllegalArgumentException(
                "No hay saldo suficiente para realizar el retiro."
        );
    }

    saldo -= monto + comision;
}
}
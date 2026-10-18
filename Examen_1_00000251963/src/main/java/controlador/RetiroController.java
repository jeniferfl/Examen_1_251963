/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import modelo.Cuenta;
import modelo.Recibo;
import modelo.Retiro;

import vistas.PRetiro;
import vistas.PConfirmarRetiro;
import vistas.PComprobante;

/**
 *
 * @author Jenifer Maribel Alamea 
 */
public class RetiroController {

    private final Cuenta cuenta;

    private final PRetiro vistaRetiro;
    private final PConfirmarRetiro vistaConfirmacion;
    private final PComprobante vistaComprobante;

    private Retiro retiroActual;
    private Recibo reciboActual;

    public RetiroController(Cuenta cuenta) {
        this.cuenta = cuenta;

        vistaRetiro = new PRetiro();
        vistaConfirmacion = new PConfirmarRetiro();
        vistaComprobante = new PComprobante();

        vistaRetiro.setController(this);
        vistaConfirmacion.setController(this);
        vistaComprobante.setController(this);
    }

    public void iniciar() {
        vistaRetiro.mostrarNombre(cuenta.obtenerNombreT());
        vistaRetiro.mostrarSaldo(cuenta.obtenerSaldo());
        vistaRetiro.actualizarResumen(200);

        vistaRetiro.setVisible(true);
    }

    public void montoSeleccionado(double monto) {
        vistaRetiro.actualizarResumen(monto);
    }

    public void confirmarRetiro(double monto) {
        try {
            Retiro retiro = new Retiro(monto);

            if (!cuenta.puedeRetirar(
                    retiro.getMonto(),
                    retiro.getComision())) {

                JOptionPane.showMessageDialog(
                        vistaRetiro,
                        "No hay saldo suficiente para cubrir "
                        + "el retiro y la comisión.",
                        "Saldo insuficiente",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            retiroActual = retiro;

            vistaConfirmacion.actualizarDatos(
                    cuenta.obtenerNombreT(),
                    cuenta.obtenerCuatroDigitos(),
                    cuenta.obtenerSaldo(),
                    retiroActual.getMonto(),
                    retiroActual.getComision()
            );

            vistaRetiro.setVisible(false);
            vistaConfirmacion.setVisible(true);

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    vistaRetiro,
                    ex.getMessage(),
                    "Retiro no válido",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    public void regresarAlRetiro() {
        vistaConfirmacion.setVisible(false);

        vistaRetiro.mostrarNombre(cuenta.obtenerNombreT());
        vistaRetiro.mostrarSaldo(cuenta.obtenerSaldo());

        vistaRetiro.setVisible(true);
    }

    public void procesarRetiro() {
        if (retiroActual == null) {
            return;
        }

        vistaConfirmacion.setVisible(false);

        Timer timer = new Timer(1800, e -> {
            ((Timer) e.getSource()).stop();
            completarRetiro();
        });

        timer.setRepeats(false);
        timer.start();
    }

    private void completarRetiro() {
        try {
            // Se vuelve a validar antes de descontar.
            if (!cuenta.puedeRetirar(
                    retiroActual.getMonto(),
                    retiroActual.getComision())) {


                JOptionPane.showMessageDialog(
                        vistaRetiro,
                        "El saldo ya no es suficiente para realizar "
                        + "esta operación.",
                        "Operación cancelada",
                        JOptionPane.WARNING_MESSAGE
                );

                vistaRetiro.mostrarSaldo(cuenta.obtenerSaldo());
                vistaRetiro.setVisible(true);
                return;
            }

            // El dinero se descuenta una sola vez.
            cuenta.descontar(
                    retiroActual.getMonto(),
                    retiroActual.getComision()
            );

            reciboActual = new Recibo(cuenta, retiroActual);

            vistaComprobante.actualizarDatos(
                    cuenta.obtenerNombreT(),
                    cuenta.obtenerCuatroDigitos(),
                    retiroActual.getMonto(),
                    retiroActual.getComision(),
                    retiroActual.getFechaHora()
            );

            vistaComprobante.setVisible(true);

        } catch (IllegalArgumentException ex) {

            JOptionPane.showMessageDialog(
                    vistaRetiro,
                    ex.getMessage(),
                    "No se pudo completar el retiro",
                    JOptionPane.ERROR_MESSAGE
            );

            vistaRetiro.mostrarSaldo(cuenta.obtenerSaldo());
            vistaRetiro.setVisible(true);
        }
    }

    public void finalizarOperacion() {
        retiroActual = null;

        vistaComprobante.setVisible(false);

        vistaRetiro.mostrarNombre(cuenta.obtenerNombreT());
        vistaRetiro.mostrarSaldo(cuenta.obtenerSaldo());
        vistaRetiro.actualizarResumen(200);

        vistaRetiro.setVisible(true);
    }

    public void mostrarRecibo() {
        if (reciboActual != null) {
            JOptionPane.showMessageDialog(
                    vistaComprobante,
                    reciboActual.toString(),
                    "Recibo de Banco Azul",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private String dinero(double valor) {
        return String.format(
                java.util.Locale.US,
                "$%,.2f MXN",
                valor
        );
    }
}
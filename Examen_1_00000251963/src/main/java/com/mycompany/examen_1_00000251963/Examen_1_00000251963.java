/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.examen_1_00000251963;

import modelo.Cuenta;
import vistas.PRetiro;
import controlador.RetiroController;
import javax.swing.SwingUtilities;

/**
 *
 * @author Jenifer Maribel Alamea Flores
 */
public class Examen_1_00000251963 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Cuenta cuenta = new Cuenta(
                "Jenifer Flores",
                "1234567890123456",
                5000.00
            );

            PRetiro vista = new PRetiro();

            RetiroController controller = new RetiroController(cuenta);
            controller.iniciar();

        });
    }
}

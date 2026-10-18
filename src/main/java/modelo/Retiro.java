/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Jenifer Maribel Alamea Flores
 */
public class Retiro {
     public static final double COMISION_FIJA = 25.00;
    private final double monto;
    private final double comision;
    private final LocalDateTime fechaHora;

    public Retiro(double monto) {
        if (monto <= 0 || monto % 200 != 0) {
            throw new IllegalArgumentException("El monto debe ser positivo y múltiplo de $200.00.");
        }
        this.monto = monto;
        this.comision = COMISION_FIJA;
        this.fechaHora = LocalDateTime.now();
    }

    public double getMonto() { return monto; }
    public double getComision() { return comision; }
    public double getTotalDescontado() { return monto + comision; }
    public String getFechaHora() {
        return fechaHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
}

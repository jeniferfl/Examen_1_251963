/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

import controlador.RetiroController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Retiro;

public class PRetiro extends JFrame {

    private RetiroController controller;

    private final JLabel nombreLabel = new JLabel("-");
    private final JLabel saldoLabel = new JLabel("$0.00 MXN");
    private final JLabel montoLabel = new JLabel("$200.00 MXN");
    private final JLabel comisionLabel = new JLabel("$25.00 MXN");
    private final JLabel finalLabel = new JLabel("$0.00 MXN");

    private final JTextArea reciboArea = new JTextArea(12, 28);
    private final List<JButton> botonesMonto = new ArrayList<>();

    private final JButton otroMontoBtn;
    private JButton botonSeleccionado;

    private double montoActual = 200.00;
    private double saldoActual = 0.00;

    private final Color AZUL_OSCURO = new Color(0, 48, 87);
    private final Color AZUL_LOGO = new Color(0, 71, 133);
    private final Color AZUL_CLARO = new Color(232, 242, 255);
    private final Color GRIS_CLARO = new Color(248, 250, 252);
    private final Color GRIS_BORDE = new Color(230, 230, 230);

    public PRetiro() {
        super("Banco Azul - Retirar Efectivo");

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // PANEL SUPERIOR
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(GRIS_BORDE, 1),
                new EmptyBorder(15, 20, 15, 20)
        ));

        JPanel logoPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 0)
        );
        logoPanel.setBackground(Color.WHITE);

        JLabel logoIcon = new JLabel("B", SwingConstants.CENTER);
        logoIcon.setOpaque(true);
        logoIcon.setBackground(AZUL_LOGO);
        logoIcon.setForeground(Color.WHITE);
        logoIcon.setFont(new Font("Arial", Font.BOLD, 18));
        logoIcon.setPreferredSize(new Dimension(35, 35));

        JLabel bankName = new JLabel("BANCO AZUL");
        bankName.setFont(new Font("Arial", Font.BOLD, 16));
        bankName.setForeground(AZUL_OSCURO);

        logoPanel.add(logoIcon);
        logoPanel.add(bankName);

        JPanel stepPanel = new JPanel(new GridLayout(2, 1));
        stepPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(
                "Retirar Efectivo", SwingConstants.RIGHT
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(AZUL_OSCURO);

        JLabel stepLabel = new JLabel(
                "PASO 1 DE 3", SwingConstants.RIGHT
        );
        stepLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        stepLabel.setForeground(Color.GRAY);

        stepPanel.add(titleLabel);
        stepPanel.add(stepLabel);

        headerPanel.add(logoPanel, BorderLayout.WEST);
        headerPanel.add(stepPanel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);

        // PANEL CENTRAL
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(
                new BoxLayout(centerPanel, BoxLayout.Y_AXIS)
        );
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(
                new EmptyBorder(20, 35, 15, 35)
        );

        JLabel instruccion1 = new JLabel(
                "Seleccione el monto a retirar"
        );
        instruccion1.setFont(new Font("Arial", Font.BOLD, 22));
        instruccion1.setForeground(AZUL_OSCURO);
        instruccion1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instruccion2 = new JLabel(
                "Elija una cantidad en pesos mexicanos"
        );
        instruccion2.setFont(new Font("Arial", Font.PLAIN, 14));
        instruccion2.setForeground(Color.GRAY);
        instruccion2.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(instruccion1);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        centerPanel.add(instruccion2);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        // DATOS DEL CLIENTE Y SALDO
        JPanel datosCliente = new JPanel(
                new GridLayout(1, 2, 10, 0)
        );
        datosCliente.setBackground(GRIS_CLARO);
        datosCliente.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(GRIS_BORDE, 1, true),
                new EmptyBorder(8, 12, 8, 12)
        ));
        datosCliente.setMaximumSize(new Dimension(700, 42));

        JPanel clientePanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 5, 0)
        );
        clientePanel.setBackground(GRIS_CLARO);

        JLabel clienteTexto = new JLabel("Cliente:");
        clienteTexto.setFont(new Font("Arial", Font.PLAIN, 13));

        nombreLabel.setFont(new Font("Arial", Font.BOLD, 13));
        nombreLabel.setForeground(AZUL_OSCURO);

        clientePanel.add(clienteTexto);
        clientePanel.add(nombreLabel);

        JPanel saldoPanel = new JPanel(
                new FlowLayout(FlowLayout.RIGHT, 5, 0)
        );
        saldoPanel.setBackground(GRIS_CLARO);

        JLabel saldoTexto = new JLabel("Saldo actual:");
        saldoTexto.setFont(new Font("Arial", Font.PLAIN, 13));

        saldoLabel.setFont(new Font("Arial", Font.BOLD, 13));
        saldoLabel.setForeground(AZUL_OSCURO);

        saldoPanel.add(saldoTexto);
        saldoPanel.add(saldoLabel);

        datosCliente.add(clientePanel);
        datosCliente.add(saldoPanel);

        centerPanel.add(datosCliente);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        // BOTONES DE MONTOS
        JPanel buttonsGrid = new JPanel(
                new GridLayout(2, 4, 12, 12)
        );
        buttonsGrid.setBackground(Color.WHITE);
        buttonsGrid.setMaximumSize(new Dimension(700, 115));
        buttonsGrid.setPreferredSize(new Dimension(700, 115));

        int[] montos = {
            200, 400, 600, 800,
            1000, 1200, 1400, 1600
        };

        for (int monto : montos) {
            JButton boton = crearBotonMonto(
                    "$" + String.format("%,d", monto),
                    true
            );

            boton.addActionListener(e ->
                    seleccionarMonto(monto, boton)
            );

            botonesMonto.add(boton);
            buttonsGrid.add(boton);

            if (monto == 200) {
                botonSeleccionado = boton;
            }
        }

        centerPanel.add(buttonsGrid);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // OTRO MONTO
        otroMontoBtn = crearBotonMonto("Otro monto", false);
        otroMontoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        otroMontoBtn.setMaximumSize(new Dimension(250, 40));
        otroMontoBtn.setPreferredSize(new Dimension(250, 40));

        otroMontoBtn.addActionListener(e -> solicitarOtroMonto());

        botonesMonto.add(otroMontoBtn);
        centerPanel.add(otroMontoBtn);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 12)));

        // RESUMEN
        JPanel resumenPanel = new JPanel(
                new GridLayout(1, 3, 10, 0)
        );
        resumenPanel.setBackground(Color.WHITE);
        resumenPanel.setMaximumSize(new Dimension(700, 58));

        resumenPanel.add(
                crearDato("Monto seleccionado", montoLabel)
        );
        resumenPanel.add(
                crearDato("Comisión fija", comisionLabel)
        );
        resumenPanel.add(
                crearDato("Saldo después del retiro", finalLabel)
        );

        centerPanel.add(resumenPanel);
        add(centerPanel, BorderLayout.CENTER);

        actualizarEstiloBotones();
        actualizarResumen(montoActual);

        // PANEL INFERIOR
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(GRIS_CLARO);
        footerPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(GRIS_BORDE, 1),
                new EmptyBorder(12, 25, 12, 25)
        ));

        JLabel securityLabel = new JLabel(
                "Operación segura",
                UIManager.getIcon("OptionPane.informationIcon"),
                JLabel.LEFT
        );
        securityLabel.setForeground(new Color(60, 100, 120));
        securityLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton continuarBtn = new JButton("Continuar");
        continuarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        continuarBtn.setForeground(Color.WHITE);
        continuarBtn.setBackground(AZUL_LOGO);
        continuarBtn.setOpaque(true);
        continuarBtn.setContentAreaFilled(true);
        continuarBtn.setBorderPainted(false);
        continuarBtn.setFocusPainted(false);
        continuarBtn.setPreferredSize(new Dimension(200, 42));

        continuarBtn.addActionListener(e -> {
            if (controller != null) {
                controller.confirmarRetiro(montoActual);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se ha conectado el controlador del retiro.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        });

        footerPanel.add(securityLabel, BorderLayout.WEST);
        footerPanel.add(continuarBtn, BorderLayout.EAST);

        add(footerPanel, BorderLayout.SOUTH);
    }

    // CREAR BOTONES
    private JButton crearBotonMonto(
            String texto, boolean textoGrande) {

        JButton boton = new JButton(texto);

        boton.setFont(new Font(
                "Arial", Font.BOLD, textoGrande ? 18 : 14
        ));
        boton.setForeground(AZUL_OSCURO);
        boton.setBackground(Color.WHITE);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setFocusPainted(false);

        boton.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(180, 200, 220), 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (boton != botonSeleccionado) {
                    boton.setBackground(new Color(240, 248, 255));
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (boton != botonSeleccionado) {
                    boton.setBackground(Color.WHITE);
                }
            }
        });

        return boton;
    }

    // SELECCIONAR MONTO
    private void seleccionarMonto(double monto, JButton boton) {
        montoActual = monto;
        botonSeleccionado = boton;

        actualizarEstiloBotones();
        actualizarResumen(monto);

        if (controller != null) {
            controller.montoSeleccionado(monto);
        }
    }

    // ACTUALIZAR ESTILO DE BOTONES
    private void actualizarEstiloBotones() {
        for (JButton boton : botonesMonto) {
            boolean seleccionado = boton == botonSeleccionado;

            if (seleccionado) {
                boton.setBackground(AZUL_CLARO);
                boton.setForeground(AZUL_LOGO);
                boton.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(AZUL_LOGO, 2, true),
                        new EmptyBorder(7, 9, 7, 9)
                ));
            } else {
                boton.setBackground(Color.WHITE);
                boton.setForeground(AZUL_OSCURO);
                boton.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(180, 200, 220), 1, true
                        ),
                        new EmptyBorder(8, 10, 8, 10)
                ));
            }
        }
    }

    // SOLICITAR OTRO MONTO
    private void solicitarOtroMonto() {
        String entrada = JOptionPane.showInputDialog(
                this,
                "Escribe el monto que deseas retirar.\n"
                + "Debe ser múltiplo de $200, entre $200 y $5,000.",
                "Otro monto",
                JOptionPane.QUESTION_MESSAGE
        );

        if (entrada == null || entrada.trim().isEmpty()) {
            return;
        }

        try {
            String valor = entrada.trim()
                    .replace("$", "")
                    .replace(",", "")
                    .replace(" ", "");

            int nuevoMonto = Integer.parseInt(valor);

            if (nuevoMonto < 200
                    || nuevoMonto > 5000
                    || nuevoMonto % 200 != 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "El monto debe estar entre $200 y $5,000 "
                        + "y ser múltiplo de $200.",
                        "Monto no válido",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            seleccionarMonto(nuevoMonto, otroMontoBtn);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Escribe una cantidad numérica válida.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // CREAR TARJETAS DEL RESUMEN
    private JPanel crearDato(String titulo, JLabel valor) {
        JPanel panel = new JPanel(new GridLayout(2, 1, 0, 3));
        panel.setBackground(GRIS_CLARO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(GRIS_BORDE, 1, true),
                new EmptyBorder(5, 8, 5, 8)
        ));

        JLabel tituloLabel = new JLabel(titulo);
        tituloLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        tituloLabel.setForeground(Color.GRAY);

        valor.setFont(new Font("Arial", Font.BOLD, 13));
        valor.setForeground(AZUL_OSCURO);

        panel.add(tituloLabel);
        panel.add(valor);

        return panel;
    }

    // CONECTAR CONTROLADOR
    public void setController(RetiroController controller) {
        this.controller = controller;
    }

    // ACTUALIZAR NOMBRE
    public void mostrarNombre(String nombre) {
        nombreLabel.setText(nombre);
    }

    // ACTUALIZAR SALDO REAL
    public void mostrarSaldo(double saldo) {
        saldoActual = saldo;
        saldoLabel.setText(dinero(saldoActual));
        actualizarResumen(montoActual);
    }

    // MOSTRAR RECIBO
    public void mostrarRecibo(String texto) {
        reciboArea.setEditable(false);
        reciboArea.setFont(
                new Font(Font.MONOSPACED, Font.PLAIN, 12)
        );
        reciboArea.setText(texto);
        reciboArea.setCaretPosition(0);

        JScrollPane scroll = new JScrollPane(reciboArea);
        scroll.setPreferredSize(new Dimension(420, 260));

        JOptionPane.showMessageDialog(
                this,
                scroll,
                "Recibo de retiro",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ACTUALIZAR RESUMEN CON EL SALDO NUMÉRICO
    public void actualizarResumen(double monto) {
        montoActual = monto;

        double comision = Retiro.COMISION_FIJA;
        double saldoFinal = saldoActual - monto - comision;

        montoLabel.setText(dinero(monto));
        comisionLabel.setText(dinero(comision));

        if (saldoFinal < 0) {
            finalLabel.setText("Saldo insuficiente");
            finalLabel.setForeground(Color.RED);
        } else {
            finalLabel.setText(dinero(saldoFinal));
            finalLabel.setForeground(AZUL_OSCURO);
        }
    }

    // FORMATO DE DINERO
    private String dinero(double valor) {
        return String.format(
                java.util.Locale.US,
                "$%,.2f MXN",
                valor
        );
    }
}
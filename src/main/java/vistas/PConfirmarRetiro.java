/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

/**
 *
 * @author Jenifer Maribel Alamea Flores
 *
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class PConfirmarRetiro extends JFrame {

    // Paleta de colores
    private final Color DARK_BLUE = new Color(0, 48, 87);
    private final Color BRAND_BLUE = new Color(0, 84, 153);
    private final Color LIGHT_GRAY_TEXT = new Color(120, 130, 140);
    private final Color LIGHT_BLUE_BG = new Color(244, 249, 253);
    
    private controlador.RetiroController controller;

    private JLabel lblCantidad;
    private JLabel lblNombre;
    private JLabel lblCuenta;

    private JLabel lblSaldoActual;
    private JLabel lblMontoRetirar;
    private JLabel lblComision;
    private JLabel lblTotalValue;

    public PConfirmarRetiro() {
        setTitle("Banco Azul - Confirmar Retiro");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // --- 1. Panel Superior (Header) ---
        add(createHeader(), BorderLayout.NORTH);

        // --- 2. Panel Central (Contenido Principal) ---
        add(createMainContent(), BorderLayout.CENTER);

        // --- 3. Panel Inferior (Footer) ---
        add(createFooter(), BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1, true),
                new EmptyBorder(15, 20, 15, 20)
        ));

        // Logo y Nombre
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        logoPanel.setBackground(Color.WHITE);
        
        JLabel logoIcon = new JLabel("B", SwingConstants.CENTER);
        logoIcon.setOpaque(true);
        logoIcon.setBackground(BRAND_BLUE);
        logoIcon.setForeground(Color.WHITE);
        logoIcon.setFont(new Font("Arial", Font.BOLD, 18));
        logoIcon.setPreferredSize(new Dimension(35, 35));
        
        JLabel bankName = new JLabel("BANCO AZUL");
        bankName.setFont(new Font("Arial", Font.BOLD, 16));
        bankName.setForeground(DARK_BLUE);
        
        logoPanel.add(logoIcon);
        logoPanel.add(bankName);

        // Título del paso
        JPanel stepPanel = new JPanel(new GridLayout(2, 1));
        stepPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("Confirmar Retiro", SwingConstants.RIGHT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(DARK_BLUE);
        
        JLabel stepLabel = new JLabel("PASO 2 DE 3", SwingConstants.RIGHT);
        stepLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        stepLabel.setForeground(Color.GRAY);
        
        stepPanel.add(titleLabel);
        stepPanel.add(stepLabel);

        headerPanel.add(logoPanel, BorderLayout.WEST);
        headerPanel.add(stepPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createMainContent() {
        JPanel mainPanel = new JPanel(new BorderLayout(0, 20));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Títulos
        JPanel titlesPanel = new JPanel();
        titlesPanel.setLayout(new BoxLayout(titlesPanel, BoxLayout.Y_AXIS));
        titlesPanel.setBackground(Color.WHITE);
        
        JLabel mainTitle = new JLabel("Confirme su operación");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 22));
        mainTitle.setForeground(DARK_BLUE);
        
        JLabel subTitle = new JLabel("Verifique que la información sea correcta");
        subTitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subTitle.setForeground(LIGHT_GRAY_TEXT);
        
        titlesPanel.add(mainTitle);
        titlesPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        titlesPanel.add(subTitle);

        mainPanel.add(titlesPanel, BorderLayout.NORTH);

        // Contenedor de las dos tarjetas
        JPanel cardsPanel = new JPanel(new GridLayout(1, 2, 25, 0));
        cardsPanel.setBackground(Color.WHITE);

        cardsPanel.add(createLeftCard());
        cardsPanel.add(createRightCard());

        mainPanel.add(cardsPanel, BorderLayout.CENTER);

        return mainPanel;
    }

    
    private JPanel createLeftCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(LIGHT_BLUE_BG);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(0, 102, 204), 2, true),
                new EmptyBorder(60, 20, 60, 20)
        ));

        JLabel iconLabel = new JLabel("[ O ]", SwingConstants.CENTER);
        iconLabel.setFont(new Font("Arial", Font.BOLD, 24));
        iconLabel.setForeground(BRAND_BLUE);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblMontoTitulo = new JLabel("MONTO SELECCIONADO");
        lblMontoTitulo.setFont(new Font("Arial", Font.BOLD, 12));
        lblMontoTitulo.setForeground(BRAND_BLUE);
        lblMontoTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Usar la variable de la clase, no declarar otra JLabel.
        lblCantidad = new JLabel("$200.00");
        lblCantidad.setFont(new Font("Arial", Font.BOLD, 36));
        lblCantidad.setForeground(DARK_BLUE);
        lblCantidad.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalGlue());
        card.add(iconLabel);
        card.add(Box.createRigidArea(new Dimension(0, 20)));
        card.add(lblMontoTitulo);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(lblCantidad);
        card.add(Box.createVerticalGlue());

        return card;
    }


    
        private JPanel createRightCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1, true),
                new EmptyBorder(25, 25, 25, 25)
        ));

        JLabel lblInfo = new JLabel("Información del cliente");
        lblInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblInfo.setForeground(LIGHT_GRAY_TEXT);

        lblNombre = new JLabel("Cliente");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 18));
        lblNombre.setForeground(DARK_BLUE);

        lblCuenta = new JLabel("Cuenta");
        lblCuenta.setFont(new Font("Arial", Font.PLAIN, 13));
        lblCuenta.setForeground(LIGHT_GRAY_TEXT);

        card.add(lblInfo);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(lblNombre);
        card.add(Box.createRigidArea(new Dimension(0, 5)));
        card.add(lblCuenta);
        card.add(Box.createRigidArea(new Dimension(0, 15)));

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(230, 230, 230));
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        card.add(separator);
        card.add(Box.createRigidArea(new Dimension(0, 20)));

        card.add(crearFilaImporte("Saldo actual", 0));
        card.add(Box.createRigidArea(new Dimension(0, 15)));

        card.add(crearFilaImporte("Monto a retirar", 1));
        card.add(Box.createRigidArea(new Dimension(0, 15)));

        card.add(crearFilaImporte("Comisión", 2));
        card.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.setBackground(DARK_BLUE);
        totalPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
        totalPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel lblTotalText = new JLabel("Total a descontar");
        lblTotalText.setForeground(Color.WHITE);
        lblTotalText.setFont(new Font("Arial", Font.BOLD, 14));

        lblTotalValue = new JLabel("$215.00 MXN");
        lblTotalValue.setForeground(Color.WHITE);
        lblTotalValue.setFont(new Font("Arial", Font.BOLD, 22));

        totalPanel.add(lblTotalText, BorderLayout.WEST);
        totalPanel.add(lblTotalValue, BorderLayout.EAST);
        card.add(totalPanel);

        return card;
    }

    private JPanel crearFilaImporte(String texto, int tipo) {
        JPanel fila = new JPanel(new BorderLayout());
        fila.setBackground(Color.WHITE);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 25));

        JLabel titulo = new JLabel(texto);
        titulo.setFont(new Font("Arial", Font.PLAIN, 14));
        titulo.setForeground(LIGHT_GRAY_TEXT);

        JLabel valor = new JLabel("$0.00 MXN");
        valor.setFont(new Font("Arial", Font.BOLD, 14));
        valor.setForeground(DARK_BLUE);

        switch (tipo) {
            case 0:
                lblSaldoActual = valor;
                break;
            case 1:
                lblMontoRetirar = valor;
                break;
            case 2:
                lblComision = valor;
                break;
            default:
                throw new IllegalArgumentException("Tipo de importe inválido");
        }

        fila.add(titulo, BorderLayout.WEST);
        fila.add(valor, BorderLayout.EAST);

        return fila;
    }

    private JPanel createFooter() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(250, 250, 250)); // Gris extra claro
        footerPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1, true),
                new EmptyBorder(15, 30, 15, 30)
        ));

        // Botón Regresar
        JButton btnBack = new JButton("Regresar");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setForeground(DARK_BLUE);
        btnBack.setBackground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setPreferredSize(new Dimension(150, 45));
        btnBack.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));

        // Botón Retirar Efectivo
        JButton btnRetirar = new JButton("Retirar Efectivo");
        btnRetirar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRetirar.setForeground(Color.WHITE);
        btnRetirar.setBackground(BRAND_BLUE);
        btnRetirar.setOpaque(true);
        btnRetirar.setBorderPainted(false);
        btnRetirar.setFocusPainted(false);
        btnRetirar.setPreferredSize(new Dimension(200, 45));
        
        btnBack.addActionListener(e -> {
            if (controller != null) {
                controller.regresarAlRetiro();
            }
        });
        
        btnRetirar.addActionListener(e -> {
            if (controller != null) {
                controller.procesarRetiro();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "No se ha conectado el controlador.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        footerPanel.add(btnBack, BorderLayout.WEST);
        footerPanel.add(btnRetirar, BorderLayout.EAST);

        return footerPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PConfirmarRetiro().setVisible(true);
        });
    }
    public void setController(controlador.RetiroController controller) {
        this.controller = controller;
    }

    public void actualizarDatos(
            String nombre,
            String ultimosCuatro,
            double saldo,
            double monto,
            double comision) {

        lblNombre.setText(nombre);
        lblCuenta.setText("Cuenta •••• " + ultimosCuatro);
        lblCantidad.setText(dinero(monto));
        lblSaldoActual.setText(dinero(saldo));
        lblMontoRetirar.setText(dinero(monto));
        lblComision.setText(dinero(comision));
        lblTotalValue.setText(dinero(monto + comision));
    }

    private String dinero(double valor) {
        return String.format(
                java.util.Locale.US,
                "$%,.2f MXN",
                valor
        );
    }
}

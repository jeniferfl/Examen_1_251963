/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vistas;

/**
 *
 * @author Jenifer Maribel Alamea Flores
 */
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import controlador.RetiroController;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import vistas.PComprobante.SuccessIconPanel;

public class PComprobante extends JFrame {

    // Paleta de colores
    private final Color DARK_BLUE = new Color(0, 48, 87);
    private final Color BRAND_BLUE = new Color(0, 84, 153);
    private final Color LIGHT_GRAY_TEXT = new Color(140, 150, 160);
    private final Color SUCCESS_GREEN = new Color(0, 168, 107);
    private final Color SUCCESS_BG = new Color(210, 245, 230);
    private final Color CARD_BORDER = new Color(230, 230, 230);

    // Controlador
    private RetiroController controller;

    // Etiquetas que se actualizan con los datos del retiro
    private JLabel lblCuenta;
    private JLabel lblNombre;
    private JLabel lblMonto;
    private JLabel lblComision;
    private JLabel lblFecha;
    private JLabel lblHora;

    public PComprobante() {
        setTitle("Banco Azul - Comprobante");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        add(createHeader(), BorderLayout.NORTH);
        add(createMainContent(), BorderLayout.CENTER);
        add(createFooter(), BorderLayout.SOUTH);
    }

    private JPanel createHeader() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(CARD_BORDER, 1, true),
                new EmptyBorder(15, 20, 15, 20)
        ));

        JPanel logoPanel = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 10, 0)
        );
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

        JPanel stepPanel = new JPanel(new GridLayout(2, 1));
        stepPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel(
                "Comprobante", SwingConstants.RIGHT
        );
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(DARK_BLUE);

        JLabel stepLabel = new JLabel(
                "PASO 3 DE 3", SwingConstants.RIGHT
        );
        stepLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        stepLabel.setForeground(Color.GRAY);

        stepPanel.add(titleLabel);
        stepPanel.add(stepLabel);

        headerPanel.add(logoPanel, BorderLayout.WEST);
        headerPanel.add(stepPanel, BorderLayout.EAST);

        return headerPanel;
    }

    private JPanel createMainContent() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(
                new BoxLayout(mainPanel, BoxLayout.Y_AXIS)
        );
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(
                new EmptyBorder(30, 40, 30, 40)
        );

        // Encabezado de operación exitosa
        JPanel successHeaderPanel = new JPanel();
        successHeaderPanel.setLayout(
                new BoxLayout(successHeaderPanel, BoxLayout.X_AXIS)
        );
        successHeaderPanel.setBackground(Color.WHITE);
        successHeaderPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        successHeaderPanel.add(new SuccessIconPanel());
        successHeaderPanel.add(
                Box.createRigidArea(new Dimension(20, 0))
        );

        JPanel titlesPanel = new JPanel();
        titlesPanel.setLayout(
                new BoxLayout(titlesPanel, BoxLayout.Y_AXIS)
        );
        titlesPanel.setBackground(Color.WHITE);

        JLabel mainTitle = new JLabel("Retiro Exitoso");
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setForeground(DARK_BLUE);

        JLabel subTitle = new JLabel(
                "Retire su efectivo y conserve su comprobante"
        );
        subTitle.setFont(new Font("Arial", Font.PLAIN, 14));
        subTitle.setForeground(LIGHT_GRAY_TEXT);

        titlesPanel.add(mainTitle);
        titlesPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        titlesPanel.add(subTitle);

        successHeaderPanel.add(titlesPanel);

        mainPanel.add(successHeaderPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Tarjeta del comprobante
        JPanel receiptCard = new JPanel();
        receiptCard.setLayout(
                new BoxLayout(receiptCard, BoxLayout.Y_AXIS)
        );
        receiptCard.setBackground(Color.WHITE);
        receiptCard.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(CARD_BORDER, 1, true),
                new EmptyBorder(25, 30, 25, 30)
        ));
        receiptCard.setMaximumSize(new Dimension(650, 280));
        receiptCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Folio y estado
        JPanel cardHeader = new JPanel(new BorderLayout());
        cardHeader.setBackground(Color.WHITE);
        cardHeader.setMaximumSize(new Dimension(
                Integer.MAX_VALUE, 50
        ));

        JPanel folioPanel = new JPanel();
        folioPanel.setLayout(
                new BoxLayout(folioPanel, BoxLayout.Y_AXIS)
        );
        folioPanel.setBackground(Color.WHITE);

        JLabel lblRecibo = new JLabel("RECIBO DIGITAL");
        lblRecibo.setFont(new Font("Arial", Font.BOLD, 12));
        lblRecibo.setForeground(BRAND_BLUE);

        JLabel lblFolio = new JLabel(
                "Folio: " + generarFolio()
        );
        lblFolio.setFont(new Font("Arial", Font.PLAIN, 12));
        lblFolio.setForeground(Color.GRAY);

        folioPanel.add(lblRecibo);
        folioPanel.add(lblFolio);

        JLabel statusBadge = new JLabel(
                "Completado", SwingConstants.CENTER
        );
        statusBadge.setOpaque(true);
        statusBadge.setBackground(SUCCESS_BG);
        statusBadge.setForeground(SUCCESS_GREEN);
        statusBadge.setFont(new Font("Arial", Font.BOLD, 12));
        statusBadge.setBorder(
                new EmptyBorder(8, 15, 8, 15)
        );

        cardHeader.add(folioPanel, BorderLayout.WEST);
        cardHeader.add(statusBadge, BorderLayout.EAST);

        receiptCard.add(cardHeader);
        receiptCard.add(Box.createRigidArea(
                new Dimension(0, 15)
        ));

        JSeparator separator = new JSeparator();
        separator.setForeground(CARD_BORDER);
        separator.setMaximumSize(
                new Dimension(Integer.MAX_VALUE, 1)
        );

        receiptCard.add(separator);
        receiptCard.add(Box.createRigidArea(
                new Dimension(0, 20)
        ));

        // Datos reales del retiro
        JPanel gridData = new JPanel(
                new GridLayout(3, 2, 20, 15)
        );
        gridData.setBackground(Color.WHITE);

        lblCuenta = crearValor();
        lblNombre = crearValor();
        lblMonto = crearValor();
        lblComision = crearValor();
        lblFecha = crearValor();
        lblHora = crearValor();

        gridData.add(createDataPair("Cuenta", lblCuenta));
        gridData.add(createDataPair("Cliente", lblNombre));
        gridData.add(createDataPair(
                "Cantidad retirada", lblMonto
        ));
        gridData.add(createDataPair("Comisión", lblComision));
        gridData.add(createDataPair("Fecha", lblFecha));
        gridData.add(createDataPair("Hora", lblHora));

        receiptCard.add(gridData);
        mainPanel.add(receiptCard);

        return mainPanel;
    }

    private JLabel crearValor() {
        JLabel label = new JLabel("-");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(DARK_BLUE);
        return label;
    }

    private JPanel createDataPair(
            String titulo, JLabel valor
    ) {
        JPanel panel = new JPanel();
        panel.setLayout(
                new BoxLayout(panel, BoxLayout.Y_AXIS)
        );
        panel.setBackground(Color.WHITE);

        JLabel lblTop = new JLabel(titulo);
        lblTop.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTop.setForeground(LIGHT_GRAY_TEXT);

        panel.add(lblTop);
        panel.add(Box.createRigidArea(
                new Dimension(0, 5)
        ));
        panel.add(valor);

        return panel;
    }

    private JPanel createFooter() {
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(250, 250, 250));
        footerPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(CARD_BORDER, 1, true),
                new EmptyBorder(15, 30, 15, 30)
        ));

        JPanel leftActions = new JPanel(
                new FlowLayout(FlowLayout.LEFT, 15, 0)
        );
        leftActions.setBackground(new Color(250, 250, 250));

        JButton btnImprimir = createSecondaryButton(
                "Imprimir Recibo"
        );
        JButton btnCorreo = createSecondaryButton(
                "Enviar por Correo"
        );

        btnImprimir.addActionListener(e -> imprimirRecibo());

        btnCorreo.addActionListener(e -> {
            JOptionPane.showMessageDialog(
                    this,
                    "La función de envío por correo "
                    + "aún no está configurada.",
                    "Enviar comprobante",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        leftActions.add(btnImprimir);
        leftActions.add(btnCorreo);

        JButton btnFinalizar = new JButton("Finalizar");
        btnFinalizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnFinalizar.setForeground(Color.WHITE);
        btnFinalizar.setBackground(BRAND_BLUE);
        btnFinalizar.setOpaque(true);
        btnFinalizar.setBorderPainted(false);
        btnFinalizar.setFocusPainted(false);
        btnFinalizar.setPreferredSize(
                new Dimension(180, 45)
        );

        btnFinalizar.addActionListener(e -> {
            setVisible(false);

            if (controller != null) {
                controller.finalizarOperacion();
            } else {
                dispose();
            }
        });

        footerPanel.add(leftActions, BorderLayout.WEST);
        footerPanel.add(btnFinalizar, BorderLayout.EAST);

        return footerPanel;
    }

    private JButton createSecondaryButton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setForeground(DARK_BLUE);
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setPreferredSize(new Dimension(180, 45));
        btn.setBorder(new LineBorder(
                new Color(200, 210, 220), 1, true
        ));
        return btn;
    }

    // Conectar con RetiroController
    public void setController(RetiroController controller) {
        this.controller = controller;
    }

    // Actualizar los datos del comprobante
    public void actualizarDatos(
            String nombre,
            String ultimosCuatro,
            double monto,
            double comision,
            String fechaHora) {

        lblNombre.setText(nombre);
        lblCuenta.setText("•••• " + ultimosCuatro);
        lblMonto.setText(dinero(monto));
        lblComision.setText(dinero(comision));

        // Separar fecha y hora cuando vienen juntas.
        if (fechaHora != null && !fechaHora.trim().isEmpty()) {
            String fechaNormalizada = fechaHora.trim()
                    .replace('T', ' ');

            int separador = fechaNormalizada.lastIndexOf(' ');

            if (separador > 0
                    && separador < fechaNormalizada.length() - 1) {
                lblFecha.setText(
                        fechaNormalizada.substring(0, separador)
                );
                lblHora.setText(
                        fechaNormalizada.substring(separador + 1)
                );
            } else {
                lblFecha.setText(fechaNormalizada);
                lblHora.setText("-");
            }
        } else {
            lblFecha.setText("-");
            lblHora.setText("-");
        }
    }

    private String dinero(double valor) {
        return String.format(
                java.util.Locale.US,
                "$%,.2f MXN",
                valor
        );
    }

    private String generarFolio() {
        return "AZ-" + System.currentTimeMillis();
    }

     // Imprimir la ventana del comprobante
    private void imprimirRecibo() {
        PrinterJob trabajo = PrinterJob.getPrinterJob();
        trabajo.setJobName("Comprobante - Banco Azul");

        trabajo.setPrintable((grafico, pagina, indicePagina) -> {
            if (indicePagina > 0) {
                return java.awt.print.Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2 = (Graphics2D) grafico.create();

            try {
                g2.translate(
                        pagina.getImageableX(),
                        pagina.getImageableY()
                );

                double escalaX =
                        pagina.getImageableWidth() / getWidth();
                double escalaY =
                        pagina.getImageableHeight() / getHeight();
                double escala = Math.min(escalaX, escalaY);

                g2.scale(escala, escala);
                printAll(g2);

            } finally {
                g2.dispose();
            }

            return java.awt.print.Printable.PAGE_EXISTS;
        });

        if (trabajo.printDialog()) {
            try {
                trabajo.print();

                JOptionPane.showMessageDialog(
                        this,
                        "Comprobante enviado a impresión.",
                        "Imprimir recibo",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo imprimir el comprobante:\n"
                        + ex.getMessage(),
                        "Error de impresión",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // Icono gráfico de operación exitosa
    class SuccessIconPanel extends JPanel {

        public SuccessIconPanel() {
            setPreferredSize(new Dimension(80, 80));
            setMinimumSize(new Dimension(80, 80));
            setMaximumSize(new Dimension(80, 80));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g.create();

            try {
                g2d.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                int width = getWidth();
                int height = getHeight();

                g2d.setColor(new Color(230, 250, 240));
                g2d.fillOval(0, 0, width, height);

                int padding = 12;
                g2d.setColor(SUCCESS_BG);
                g2d.fillOval(
                        padding,
                        padding,
                        width - 2 * padding,
                        height - 2 * padding
                );

                g2d.setColor(SUCCESS_GREEN);
                g2d.setStroke(new BasicStroke(
                        4,
                        BasicStroke.CAP_ROUND,
                        BasicStroke.JOIN_ROUND
                ));

                int cx = width / 2;
                int cy = height / 2;

                g2d.drawLine(cx - 10, cy, cx - 2, cy + 8);
                g2d.drawLine(cx - 2, cy + 8, cx + 12, cy - 6);

            } finally {
                g2d.dispose();
            }
        }
    }
}
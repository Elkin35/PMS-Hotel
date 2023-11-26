package presentacion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class PHuespedes extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private TableRowSorter<TableModel> sorter;

    public PHuespedes(List<List<Object>> datos) {

        modelo = new DefaultTableModel();

        modelo.addColumn("ID reserva");
        modelo.addColumn("Nombre");
        modelo.addColumn("Documento");
        modelo.addColumn("Correo");
        modelo.addColumn("Celular");
        modelo.addColumn("Fecha");

        for (List<Object> fila : datos) {
            modelo.addRow(fila.toArray());
        }

        tabla = new JTable(modelo);

        sorter = new TableRowSorter<>(tabla.getModel());
        tabla.setRowSorter(sorter);

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        tabla.setBackground(new Color(144, 198, 244));
        tabla.setForeground(new Color(0, 51, 102));
        tabla.setGridColor(new Color(51, 153, 204));
        tabla.setSelectionBackground(new Color(0, 51, 102));
        tabla.setSelectionForeground(Color.WHITE);
        tabla.setFont(new Font("Verdana", Font.PLAIN, 12));
        tabla.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 14));

        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBackground(new Color(51, 153, 204));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(scrollPane, BorderLayout.CENTER);

        JPanel busquedaPanel = new JPanel(new BorderLayout());
        JTextField idReservaTextField = new JTextField(10);
        JButton buscarButton = new JButton("Buscar");
  
        buscarButton.setFont(new Font("Verdana", Font.BOLD, 15));
        busquedaPanel.add(idReservaTextField, BorderLayout.WEST);
        busquedaPanel.add(buscarButton, BorderLayout.CENTER);
        add(busquedaPanel, BorderLayout.NORTH);

        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String idReserva = idReservaTextField.getText();
                if (idReserva.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    try {
                        sorter.setRowFilter(RowFilter.regexFilter("^" + idReserva + "$", 0));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        

        setTitle("Huespedes actuales");
        setSize(1200, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(new Color(70, 130, 180));
       

    }


}
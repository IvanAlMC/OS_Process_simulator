package views;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public interface Constants {
    Font TahomaPlain14 = new Font("Tahoma", Font.PLAIN, 14);
    Font tahoma12 = new Font("Tahoma", Font.BOLD, 12);
    Font tahoma18 = new Font("Tahoma", Font.BOLD, 18);
    DefaultTableModel defaultTableModel = new DefaultTableModel(
            new Object[][]{
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null}
            },
            new String[]{
                    "Title 1", "Title 2", "Title 3", "Title 4"
            }
    );
    DefaultTableModel dataModelListProcessList = new DefaultTableModel(
            new Object[][]{

            },
            new String[]{
                    "N° Proceso", "Nombre", "Tiempo total", "Quantum", "Tiempo faltante", "Estado","I/O Espera", "Tiempo Espera"
            }
    );
    Color colorProcessActual = new Color(0, 51, 255);
    DefaultTableModel tableProcessTerminates = new DefaultTableModel(
            new Object[][]{

            },
            new String[]{
                    "N° Proceso", "Nombre", "Tiempo total", "Quantum", "Tiempo Final"
            }
    );
    KeyAdapter validateOnlyNumber = new KeyAdapter() {
        public void keyTyped(KeyEvent e) {
            char character = e.getKeyChar();
            if (((character < '0') || (character > '9')) && (character != '\b'))
                e.consume();
        }
    };

    Color colorListProcessList = new Color(78, 250, 129);
    Color foregroundTableProcessList = new Color(0, 32, 134);
}

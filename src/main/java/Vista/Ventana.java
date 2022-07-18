package Vista;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class Ventana extends JFrame {

    private Panel panel;

    public Ventana() {
        init1();
    }

    private void init1() {
        setSize(1000, 1000);
        setPreferredSize(this.getSize());
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        JMenuItem itemLeer = new JMenuItem("Leer archivo");
        JMenuItem itemGuardar = new JMenuItem("Guardar archivo");

        itemLeer.addActionListener(e -> {

            FileNameExtensionFilter xlsx = new FileNameExtensionFilter("Archivos excel(2007+)", "xlsx");

            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("."));
            fc.setAcceptAllFileFilterUsed(false);
            fc.addChoosableFileFilter(xlsx);

            int respuesta = fc.showOpenDialog(null);

            if (respuesta == JFileChooser.APPROVE_OPTION) {
                String path = fc.getSelectedFile().getPath();
                panel.leerArchivo(path);
            }
        });

        itemGuardar.addActionListener(e -> {
            FileNameExtensionFilter xlsx = new FileNameExtensionFilter("Archivos excel(2007+)", "xlsx");

            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new File("."));
            fc.setAcceptAllFileFilterUsed(false);
            fc.addChoosableFileFilter(xlsx);

            int respuesta = fc.showSaveDialog(null);

            if (respuesta == JFileChooser.APPROVE_OPTION) {
                String path = fc.getSelectedFile().getPath();
                path += ".xlsx";
                panel.guardarArchivo(path);
            }
        });

        panel = new Panel(this.getSize());

        menu.add(itemGuardar);
        menu.add(itemLeer);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
        this.add(panel);
        this.pack();
    }

}

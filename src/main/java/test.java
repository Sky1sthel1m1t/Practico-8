import Modelo.Lista;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

public class test {
    public static void main(String[] args) {
        try {
            InputStream excel = new FileInputStream("Libro1.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(excel);

            XSSFSheet hoja = wb.getSheetAt(0);
            XSSFRow fila;
            XSSFCell celda;

            Iterator<Row> filas = hoja.rowIterator();

            while (filas.hasNext()) {
                fila = (XSSFRow) filas.next();
                Iterator<Cell> celdas = fila.cellIterator();
                String[] datos = new String[6];
                int numColumna = 0;

                while (celdas.hasNext()) {
                    celda = (XSSFCell) celdas.next();
                    String aux = "";

                    if (celda.getCellType() == CellType.STRING) {
                        aux = celda.getStringCellValue();
                    } else if (celda.getCellType() == CellType.NUMERIC) {
                        DataFormatter dataFormatter = new DataFormatter();
                        aux = dataFormatter.formatCellValue(celda);
                    }

                    System.out.print(aux + " ");

                    datos[numColumna] = aux;
                    numColumna++;
                }

                System.out.println(numColumna);

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Ha ocurrido un error en la lectura del archivo");
            e.printStackTrace();
        }
    }
}

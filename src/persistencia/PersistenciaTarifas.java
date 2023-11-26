package persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class PersistenciaTarifas {
    // Almacenamiento principal de inventarios

    public HashMap<LocalDate, Double> estandar = new HashMap<LocalDate, Double>();
    public HashMap<LocalDate, Double> suite = new HashMap<LocalDate, Double>();
    public HashMap<LocalDate, Double> suiteDoble = new HashMap<LocalDate, Double>();

    // Methods

    public void leer(String archivo) throws FileNotFoundException {

        File persistentFile = new File(archivo);

        Scanner Reader = new Scanner(persistentFile);

        while (Reader.hasNextLine()) {
            String line = Reader.nextLine();
            String[] pv = line.split(";");
            String tipo = pv[0];
            LocalDate dia = LocalDate.parse(pv[1], DateTimeFormatter.ofPattern("d-M-yyyy"));
            double valor = Double.valueOf(pv[2]);

            if (tipo.equals("Estandar")) {

                if (estandar.containsKey(dia)) {
                    if (estandar.get(dia) > valor) {
                        estandar.put(dia, valor);
                    }
                } else {
                    estandar.put(dia, valor);
                }
            }

            else if (tipo.equals("Suite")) {

                if (suite.containsKey(dia)) {
                    if (suite.get(dia) > valor) {
                        suite.put(dia, valor);
                    }
                } else {
                    suite.put(dia, valor);
                }
            } else if (tipo.equals("Suite doble")) {

                if (suiteDoble.containsKey(dia)) {
                    if (suiteDoble.get(dia) > valor) {
                        suiteDoble.put(dia, valor);
                    }
                } else {
                    suiteDoble.put(dia, valor);
                }
            }
        }
        ;

        Reader.close();
    }

    public void escribir(File archivo) throws IOException {

        archivo.createNewFile();
        FileWriter writer = new FileWriter(archivo, false);

        String text = "";

        for (LocalDate fecha : estandar.keySet()) {
            text += "Estandar;";
            text += fecha.format(DateTimeFormatter.ofPattern("d-M-yyyy"));
            text += ";";
            text += estandar.get(fecha).toString();
            text += "\n";
        }
        for (LocalDate fecha : suite.keySet()) {
            text += "Suite;";
            text += fecha.format(DateTimeFormatter.ofPattern("d-M-yyyy"));
            text += ";";
            text += suite.get(fecha).toString();
            text += "\n";
        }
        for (LocalDate fecha : suiteDoble.keySet()) {
            text += "Suite doble;";
            text += fecha.format(DateTimeFormatter.ofPattern("d-M-yyyy"));
            text += ";";
            text += suiteDoble.get(fecha).toString();
            text += "\n";
        }

        writer.write(text);
        writer.close();
    }

}

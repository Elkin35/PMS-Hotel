package persistencia;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.*;

import presentacion.App;

public class PersistenciaUsuario {

    public void leerUsuarios(HashMap<String, ArrayList<String>> usuarios, HashMap<String, String> contrasenas, ArrayList<String> idEmpleados) {
        try {
            BufferedReader brUs = new BufferedReader(new FileReader(App.path + "/entrega 3/Data/usuarios.txt"));
            String linea;
            while ((linea = brUs.readLine()) != null) {
                String datos[] = linea.split(";");
                String tipo = datos[0];
                String nombre = datos[1];
                String contrasena = datos[2];

                if(!tipo.equals("administrador")){

                    String idEmpleado = datos[3];
                    if(idEmpleados.contains(idEmpleado)){
                    } else {
                        idEmpleados.add(idEmpleado);
                    }
                }
                

                if (usuarios.containsKey(tipo)) {
                    usuarios.get(tipo).add(nombre);
                } else {
                    ArrayList<String> lista = new ArrayList<>();
                    lista.add(nombre);
                    usuarios.put(tipo, lista);
                }
                contrasenas.put(nombre, contrasena);

                
            }
            brUs.close();
        } catch (Exception e) {
            System.out.println("Error al leer los usuarios");
        }
    }

    public void escribirUsuario(String tipo, String nombre, String contrasena, HashMap<String, ArrayList<String>> usuarios, HashMap<String, String> contrasenas,
    String idEmpleado, ArrayList<String> idEmpleados) {
        try {
            FileWriter fw = new FileWriter(App.path + "/entrega 3/Data/usuarios.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);

            if(!tipo.equals("administrador")){
                if(idEmpleados.contains(idEmpleado) && !tipo.equals("cliente")){
                } else {
                    bw.write(tipo + ";" + nombre + ";" + contrasena + ";" + idEmpleado);
                    bw.newLine();
                    idEmpleados.add(idEmpleado);
                }
            } else {
            bw.write(tipo + ";" + nombre + ";" + contrasena);
            bw.newLine();
            }

            bw.close();
            fw.close();

            ArrayList<String> lista = new ArrayList<>();
            lista.add(nombre);
            usuarios.put(tipo, lista);
            contrasenas.put(nombre, contrasena);
            

        } catch (Exception e) {
            System.out.println("Error al crear el usuario");
        }

    }

    
}

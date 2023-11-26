package logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Set;
import java.io.*;
import logica.Habitacion;
import logica.PasarelaGenerica;
import persistencia.PersistenciaTarifas;
import persistencia.PersistenciaUsuario;
import presentacion.App;
import persistencia.PersistenciaInventario;
import persistencia.PersistenciaMenuComedor;
import persistencia.PersistenciaMenuHabitacion;
import persistencia.PersistenciaFactura;
import persistencia.PersistenciaGrupo;
import persistencia.PersistenciaHabitacionReservada;
import persistencia.PersistenciaReserva;
import persistencia.PersistenciaServicio;

public class PMS {

    //----------------------------------------------------------------------------------------------------

    private static PersistenciaUsuario archivoUsuarios = new PersistenciaUsuario();
    private static HashMap<String, ArrayList<String>> mapaUsuarios = new HashMap<>(); // <Tipo de usuario, nombre deusuario>
    private static HashMap<String, String> contrasenas = new HashMap<>(); // <nombre de usuario, contraseña>

    private static PersistenciaMenuHabitacion archivoMenuHabitacion = new PersistenciaMenuHabitacion();
    public static HashMap<String, Plato> mapaPlatosMenuHabitacion = new HashMap<>();
    public static HashMap<String, Bebida> mapaBebidasMenuHabitacion = new HashMap<>();

    private static PersistenciaMenuComedor archivoMenuComedor = new PersistenciaMenuComedor();
    public static HashMap<String, Plato> mapaPlatosMenuComedor = new HashMap<>();
    public static HashMap<String, Bebida> mapaBebidasMenuComedor = new HashMap<>();

    private static PersistenciaServicio archivoServicios = new PersistenciaServicio();
    private static HashMap<String, Float> mapaServiciosPersist = new HashMap<>(); // <nombre, precio> -> solo guarda nombre y precio para ser modificados por el admin

    private static HashMap<String, ArrayList<Servicio>> mapaServicios = new HashMap<>(); // <idReserva, <Servicio>>
    public static HashMap<String, ArrayList<Servicio>> getMapaServicios() {
        return mapaServicios;
    }
    
    private static ArrayList<String> pasarelasDePagoStr = new ArrayList<>();
    private static ArrayList<PasarelaGenerica> pasarelasDePagoObj = new ArrayList<>();

    //----------------------------------------------------------------------------------------------------

    private static PersistenciaFactura archivoFacturas = new PersistenciaFactura();

    private static HashMap<String, ArrayList<Servicio>> mapaEmpleadosServicios = new HashMap<>(); // <idEmpleado, <Servicio>>
    private static ArrayList<String> listaEmpleados = new ArrayList<>(); // <String idEmpleado>

    private static ArrayList<Servicio> serviciosNoPagos = new ArrayList<>();
    private static ArrayList<Servicio> serviciosPagos = new ArrayList<>();

    private static PersistenciaReserva archivoReservas;
    private static PersistenciaGrupo archivoGrupos;
    private static PersistenciaHabitacionReservada archivoHabitacionesReservadas;
    private Recepcionista recepcionista;
    private static HashMap<String, Reserva> reservas;
    private static HashMap<String, Grupo> huespedes;
    public static Inventario inventario;
    public static Inventario getInventario() {
        return inventario;
    }

    private static HashMap<String, ArrayList<LocalDate>> ReservaHabitaciones;

    public static HashMap<String, ArrayList<LocalDate>> getReservaHabitaciones() {
        return ReservaHabitaciones;
    }

    public Administrador admin;
    public PersistenciaInventario pInventario;
    public PersistenciaTarifas pTarifas;
    ArrayList<Habitacion> estandar;
    ArrayList<Habitacion> suite;
    ArrayList<Habitacion> suiteDoble;
    Ventas ventas;

    // CAROL----------------------------------------------------------------------------------------

    public PMS() {
    	App.path = System.getProperty("user.dir");
        this.reservas = new HashMap<>();
        this.huespedes = new HashMap<>();
        this.recepcionista = new Recepcionista();
        this.ReservaHabitaciones = new HashMap<>();
        this.admin = new Administrador();
        this.pInventario = new PersistenciaInventario();
        this.pTarifas = new PersistenciaTarifas();
        this.inventario = new Inventario();
        this.estandar = this.inventario.estandar;
        this.suite = this.inventario.suite;
        this.suiteDoble = this.inventario.suiteDoble;
        
        try {
			this.ventas = new Ventas(App.path + "/entrega 3/Data/pVentas.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    public HashMap<String, Reserva> reservas() {
        return this.reservas;
    }

    public List<List<Object>> consultarReservas() {
        List<List<Object>> x = recepcionista.consultarReservas();
        return x;
    }





    public String crearRecerva(LocalDateTime fechaInicio, LocalDateTime fechaFin, float tarifaTotal, String idReserva,
            Huesped infoCliente, LocalDateTime fechareserva, String habitacion, String nombre, Long documento,
            String correo, Long celular, int numAcompanantes, ArrayList<String> tipoHabitacion) {
        
                LocalDate fecha1 = LocalDate.parse(fechaInicio.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fecha2 = LocalDate.parse(fechaFin.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                float tarifasTotalC = this.getTarifas(fecha1, fecha2, tipoHabitacion);
        
                Reserva reserva = recepcionista.crearRecerva(fechaInicio, fechaFin, tarifasTotalC, idReserva, infoCliente,
                fechareserva, habitacion, nombre, documento, correo, celular, numAcompanantes, tipoHabitacion);

        guardarReseva(reserva);
        guardar();

        return reserva.getidReserva();
    }


    

    public void registro(String idReserva) {

        Grupo grupo = new Grupo(buscarReservas(idReserva));
        guardarGrupo(idReserva, grupo);

    }

    public void acompanantes(String idReserva, String nombre, Long documento, String correo, Long celular,
            LocalDate fechaDeNacimiento) {
    
        Grupo grupo = getGrupo(idReserva);
        recepcionista.registro(nombre, documento, correo, celular, fechaDeNacimiento, grupo);
    }

    public List<List<Object>> consultarHuespedes() {
        List<List<Object>> x = recepcionista.consultarHuespedes();
        return x;
    }

    public void guardar() {
        this.archivoReservas = new PersistenciaReserva();
        this.archivoReservas.escribir("reservass.txt", reservas);
    }
    
    public void cargar() {
        this.archivoReservas = new PersistenciaReserva();
        this.reservas = this.archivoReservas.leer("reservass.txt");
    }

    public void guardarRegistro() {
        this.archivoGrupos = new PersistenciaGrupo();
        this.archivoGrupos.escribir("Registro.txt", huespedes);
    }

    public static Acompanate x(String nombre, Long documento, String correo, Long celular, LocalDate fechaDeNacimiento){
        Acompanate y = new Acompanate(nombre, documento, correo, celular, fechaDeNacimiento);
        return y;
    }

    public void cargarRegistro() {
        this.archivoGrupos = new PersistenciaGrupo();
        this.huespedes = this.archivoGrupos.leer("Registro.txt", reservas);
        HashMap<String, Grupo> x = this.huespedes;
        //System.out.println("vb");
    }
    public ArrayList<String> consultarReserva(String reserva) {
        return recepcionista.consultarReserva(reserva);
    }
    public void guardarHabitaciones() {
        PMS.archivoHabitacionesReservadas = new PersistenciaHabitacionReservada();
        this.archivoHabitacionesReservadas.escribir("OcupacionDeHabitaciones.txt", ReservaHabitaciones);
    }

    public void cargarHabitaciones() {
        PMS.archivoHabitacionesReservadas = new PersistenciaHabitacionReservada();
        ReservaHabitaciones = this.archivoHabitacionesReservadas.leer("OcupacionDeHabitaciones.txt");
    }

    public void guardarFacturas() {
        PMS.archivoFacturas = new PersistenciaFactura();
        this.archivoFacturas.escribir("Facturas.txt", mapaServicios);
    }

    public void cargarFacturas() {
        PMS.archivoFacturas = new PersistenciaFactura();
        mapaServicios = PMS.archivoFacturas.leer("Facturas.txt");
    }
    public void guardarReseva(Reserva actual) {
        reservas.put(actual.getidReserva(), actual);

    }

    public static HashMap<String, Reserva> getReservas() {
        return reservas;
    }

    public Reserva buscarReservas(String idReserva) {
        return reservas.get(idReserva);
    }

    public static PersistenciaReserva getArchivoReservas() {
        return archivoReservas;
    }

    public String cancelar(String idreserva) throws FileNotFoundException {
        String x="";
        Reserva reserva = this.reservas().get(idreserva);
        if (reserva != null) {
            x="si_id";
            ArrayList<LocalDate> fechas = reserva.ListaDias();

            for (LocalDate fecha : fechas) {
                for (Habitacion habitacion : reserva.listaHabitaciones())

                    this.getReservaHabitaciones().get(habitacion.getIdentificador() + "-" + habitacion.getUbicacion())
                            .remove(fecha);

            }
            guardarHabitaciones();
            cargarHabitaciones();

            if (!recepcionista.cancelar(idreserva)){
                x="hora";
            }
      
        } else {
            x="no_id";
        }
        return x;
    }

    public void eliminarHuespedes(String idreserva) throws FileNotFoundException {
        recepcionista.eliminarHuespedes(idreserva);
    }

    public void guardarGrupo(String idReserva, Grupo grupo) {
        huespedes.put(idReserva, grupo);
        guardarRegistro();
    }

    public static PersistenciaGrupo getArchivoGrupos() {
        return archivoGrupos;
    }

    public static HashMap<String, Grupo> getHuespedes() {
        return huespedes;
    }

    public Grupo getGrupo(String idReserva) {
        return huespedes.get(idReserva);
    }

	public void realizarPago(String idReserva, String namePasarela, String nameTitular, String numTarjeta, 
    String caducidad, String codSeguridad) throws Exception{
            
            this.cargar();
            this.cargarRegistro();
            
            HashMap<String, Reserva> reserva = getReservas();
            
            try {
                float tarifa = reserva.get(idReserva).getTarifaTotal();
                PasarelaGenerica x = new PasarelaGenerica(codSeguridad);
                x.realizarPago(idReserva, namePasarela, nameTitular, numTarjeta,  Float.toString(tarifa),  "19%", Double.toString((tarifa)*0.10), caducidad, codSeguridad);
           
            } catch (NullPointerException e) {
                throw new Exception("Error al obtener la tarifa total: la reserva no existe");
            }
            

            }
    public float consultarPago (String idReserva) throws Exception{
        this.cargar();

        HashMap<String, Reserva> reserva = getReservas();
        try {
        float tarifa = reserva.get(idReserva).getTarifaTotal();
        return(tarifa);
    } catch (NullPointerException e) {
        throw new Exception("Error al obtener la tarifa total: la reserva no existe");
    }
        
    }

    public ArrayList<String> disponibilidadHabitaciones(String tipo, int capacidadAdultos, int CapacidadNinos,
            boolean balcon,
            boolean vista, boolean cocina, LocalDateTime fechaInico, LocalDateTime fechaFinal) {

        cargarHabitaciones();
        ArrayList<String> codigos = new ArrayList<>();

        LocalDate fecha1 = LocalDate.parse(fechaInico.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fecha2 = LocalDate.parse(fechaFinal.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        ArrayList<LocalDate> listaFechas = new ArrayList<>();
        for (LocalDate fecha = fecha1; !fecha.isAfter(fecha2); fecha = fecha.plusDays(1)) {
            listaFechas.add(fecha);
        }

    
        ArrayList<Habitacion> disponibilidad = inventario.getInventario().get(tipo);
        System.out.println("\n");
        System.out.printf("%-20s%-20s%-20s%-15s%-20s\n",
                "Identificador", "Ubicacion", "Balcon", "Vista", "Cocina integrada");

        Boolean bool = true;
        int x = 0;
        for (Habitacion habitacion : disponibilidad) {
            if (habitacion.perfecta(capacidadAdultos, CapacidadNinos, balcon, vista, cocina) == true) {
                ArrayList<LocalDate> ocupado = ReservaHabitaciones
                        .get(habitacion.getIdentificador() + "-" + habitacion.getUbicacion());
                if (ocupado != null) {
                    for (LocalDate fecha : ocupado) {
                        if (listaFechas.contains(fecha)) {
                            bool = false;
                        }
                    }
                }

                if (bool == true) {
                    System.out.printf("%-20s%-20s%-20s%-15s%-20s\n",
                            habitacion.getIdentificador(), habitacion.getUbicacion(), habitacion.haveBalcon(),
                            habitacion.haveVista(), habitacion.haveCocinaIntegrada());
                    codigos.add(habitacion.getIdentificador() + "-" + habitacion.getUbicacion());
                    x += 1;
                }
            }
        }
        if (x == 0) {
            System.out.println("\nNo hay habitaciones que cumplan con los requisitos\n");
        }
        return codigos;

    }

    public void reservar(String habitacionesApartadas, LocalDateTime fechaInico, LocalDateTime fechaFinal) {

        recepcionista.reservar(habitacionesApartadas, fechaInico, fechaFinal);

        guardarHabitaciones();

    }

    public void liberar(String habitacionesApartadas) {
        recepcionista.liberar(habitacionesApartadas);

    }

    // CAMILO----------------------------------------------------------------------------------------*

    public float getTarifas(LocalDate inicio, LocalDate fin, ArrayList<String> habitaciones) {
        // Retorna la tarifa total calculada de un arreglo de habitaciones entre ciertas
        // fechas
        double total = 0;

        for (String habitacion : habitaciones) {
            if (habitacion.equals("Estandar")) {

                for (LocalDate fecha : this.pTarifas.estandar.keySet()) {
                    if (fecha.compareTo(inicio) >= 0 && fecha.compareTo(fin) <= 0) {
                        total += this.pTarifas.estandar.get(fecha);
                    }
                }
            }

            else if (habitacion.equals("Suite")) {

                for (LocalDate fecha : this.pTarifas.suite.keySet()) {
                    if (fecha.compareTo(inicio) >= 0 && fecha.compareTo(fin) <= 0) {
                        total += this.pTarifas.suite.get(fecha);
                    }
                }
            }

            else if (habitacion.equals("Suite doble")) {

                for (LocalDate fecha : this.pTarifas.suiteDoble.keySet()) {
                    if (fecha.compareTo(inicio) >= 0 && fecha.compareTo(fin) <= 0) {
                        total += this.pTarifas.suiteDoble.get(fecha);
                    }
                }
            }

        }
        return (float) total;

    }

    public ArrayList<String> consultar(LocalDate fechainicio, LocalDate fechafinal) {
        cargarHabitaciones();
        HashMap<String, ArrayList<LocalDate>> habitaciones_ocupadas = PMS.getReservaHabitaciones();
        ArrayList<String> habitaciones_libres = new ArrayList<>();
        
        
        for (String habitacion : habitaciones_ocupadas.keySet()) {
            ArrayList<LocalDate> fechas_ocupadas = habitaciones_ocupadas.get(habitacion);
            boolean habitacionLibre = true;
    
            for (LocalDate fecha : fechas_ocupadas) {
                if (fecha.isAfter(fechainicio.minusDays(1)) && fecha.isBefore(fechafinal.plusDays(1))) {
                    habitacionLibre = false;
                    break;
                }
            }
    
            if (habitacionLibre) {
                habitaciones_libres.add(habitacion);
            }
        }
    
        return habitaciones_libres;
    }

    public ArrayList<String> ConsultarIdentificador(LocalDate inicial, LocalDate fechaFinal, String identificador) {
        ArrayList<String> infoHabitacion = new ArrayList<>();
        String tipo = "";

        HashMap<String, ArrayList<Habitacion>> inventariototal = Inventario.getInventario();
        for (ArrayList<Habitacion> listaHabitaciones : inventariototal.values()) {
            for (Habitacion habitacion : listaHabitaciones) {
                if ((habitacion.getIdentificador()+"-"+habitacion.getUbicacion()).equals(identificador)) {
                    infoHabitacion.add("Identificador    :"+String.valueOf(habitacion.getIdentificador()));
                    infoHabitacion.add("Ubicacion        :"+habitacion.getUbicacion());
                    infoHabitacion.add("Capacidad        :"+String.valueOf(habitacion.getCapacidad()));
                    infoHabitacion.add("Capacidad niños  :"+String.valueOf(habitacion.getCapacidadNinos()));
                    infoHabitacion.add("Capacidad adultos:"+String.valueOf(habitacion.getCapacidadAdultos()));
                    infoHabitacion.add("Balcon           :"+String.valueOf(habitacion.haveBalcon()));
                    infoHabitacion.add("Vista            :"+String.valueOf(habitacion.haveVista()));
                    infoHabitacion.add("Cocina           :"+String.valueOf(habitacion.haveCocinaIntegrada()));
                    infoHabitacion.add("Balcon           :"+String.valueOf(habitacion.isBalcon()));
                    infoHabitacion.add("Cafetera         :"+String.valueOf(habitacion.isCafetera()));
                    infoHabitacion.add("Aire Acondicionado:"+String.valueOf(habitacion.isAireAc()));
                    infoHabitacion.add("Televisión        :"+String.valueOf(habitacion.isTV()));
                    infoHabitacion.add("Desayuno incluido :"+String.valueOf(habitacion.isDesayuno()));
                    infoHabitacion.add("Plancha           :"+String.valueOf(habitacion.isPlancha()));
                    infoHabitacion.add("Secador           :"+String.valueOf(habitacion.isSecador()));
                    infoHabitacion.add("USB A             :"+String.valueOf(habitacion.isUsb_a()));
                    infoHabitacion.add("USB B             :"+String.valueOf(habitacion.isUsb_c()));
                    infoHabitacion.add("Tamaño            :"+String.valueOf(habitacion.getTamanio()));
                    infoHabitacion.add("Hipoalergénico    :"+String.valueOf(habitacion.isHipoalergenico()));
                    infoHabitacion.add("Tipo             :"+habitacion.getTipo());
                    ArrayList<String> inf = new ArrayList<>();
                    inf.add(habitacion.getTipo());
                    float tarifa = this.getTarifas(inicial, fechaFinal, inf);
                    infoHabitacion.add("Tarifa             :"+tarifa);
                }
            }
        }
    
        return infoHabitacion;
    }


    public ArrayList<LocalDate> verificarTarifaPorTipoyAnio(int anio, String tipo) {
        // retirna la lista de las habitaciones sin tarifa en un año dado
        ArrayList<LocalDate> sinTarifa = new ArrayList<LocalDate>();
        LocalDate control = LocalDate.of(anio, 1, 1);

        if (tipo.equals("Estandar"))
            while (control.getYear() == anio) {
                if (!pTarifas.estandar.containsKey(control)) {
                    sinTarifa.add(control);
                }
                control = control.plusDays(1);
            }
        else if (tipo.equals("Suite"))
            while (control.getYear() == anio) {
                if (!pTarifas.suite.containsKey(control)) {
                    sinTarifa.add(control);
                }
                control = control.plusDays(1);
            }
        else if (tipo.equals("Suite doble"))
            while (control.getYear() == anio) {
                if (!pTarifas.suiteDoble.containsKey(control)) {
                    sinTarifa.add(control);
                }
                control = control.plusDays(1);
            }
        return sinTarifa;
    }

    public boolean verificarTarifaPorAnio(int anio) {
        // retorna falso si algún día del año no tiene tarifa
        LocalDate control = LocalDate.of(anio, 1, 1);

        while (control.getYear() == anio) {
            if (!pTarifas.estandar.containsKey(control)
                    || !pTarifas.suite.containsKey(control)
                    || !pTarifas.suiteDoble.containsKey(control)) {
                return false;
            }
            control = control.plusDays(1);
        }
        return true;
    }

    public void cargarDatos(String archivo, String archivo2) throws FileNotFoundException {
        pTarifas.leer(App.path + "/entrega 3/Data/" + archivo);
        pInventario.leer(App.path + "/entrega 3/Data/" + archivo2, this.inventario);
    }

    public void actualizarTarifas(Tarifa tarifa, LocalDate inicio, LocalDate fin, float valor) throws IOException {
        admin.actualizarTarifas(tarifa, inicio, fin, valor, pTarifas);
        this.pTarifas.escribir(new File(App.path + "/entrega 3/Data/pTar.txt"));
    }

    public boolean crearTarifa(LocalDate fechaInicio, LocalDate fechaFin, ArrayList<LocalDate> diasSemana,
            float costo, String tipo) throws IOException {
        boolean res = admin.crearTarifa(fechaInicio, fechaFin, diasSemana, costo, tipo, pTarifas);
        return res;
    }

    public boolean crearHabitacion(int identificador, String ubicacion, int capacidad, int tamanio, int voltaje, boolean balcon, boolean vista,
			boolean cocinaIntegrada, boolean calefaccion, boolean aireAc, boolean TV, boolean cafetera, boolean hipoalergenico, boolean plancha,
			boolean secador, boolean usb_a, boolean usb_c, boolean desayuno, String tipo, boolean nino, int capacidadNinos,
			Inventario inventario) throws IOException {
        boolean res = admin.crearHabitacion(identificador, ubicacion, capacidad, tamanio, voltaje, balcon, vista,
				cocinaIntegrada, calefaccion, aireAc, TV, cafetera, hipoalergenico, plancha,
				secador, usb_a, usb_c, desayuno, tipo, true, nino, capacidadNinos, inventario);
        this.pInventario.escribir(new File(App.path + "/entrega 3/Data/pInv.txt"), this.inventario);
        return res;
    }

    // ELKIN----------------------------------------------------------------------------------------


        // Funciones para los empleados y usuarios

    public void cargarUsuarios() {
        archivoUsuarios = new PersistenciaUsuario();
        archivoUsuarios.leerUsuarios(mapaUsuarios, contrasenas, listaEmpleados);
    }

    public void guardarUsuario(String tipoUsuario, String nombreUsuario, String contrasena, String idEmpleado) {
        archivoUsuarios = new PersistenciaUsuario();
        archivoUsuarios.escribirUsuario(tipoUsuario, nombreUsuario, contrasena, mapaUsuarios, contrasenas, idEmpleado,
                listaEmpleados);
    }

    public boolean existeEmpleado(String idEmpleado) {
        return listaEmpleados.contains(idEmpleado);
    }

    public HashMap<String, String> obtenerContrasenas() {
        return contrasenas;
    }

    public HashMap<String, ArrayList<String>> obtenerUsuarios() {
        return mapaUsuarios;
    }

    public void actualizarMapaServicios(String idReserva, Servicio servicio) {

        if (mapaServicios.containsKey(idReserva)) {
            (mapaServicios.get(idReserva)).add(servicio);
        } else {
            ArrayList<Servicio> servicios = new ArrayList<>();
            servicios.add(servicio);
            mapaServicios.put(idReserva, servicios);
        }
    }

    public void actualizarMapaEmpleadosServicios(String idEmpleado, Servicio servicio) {
        if (mapaEmpleadosServicios.containsKey(idEmpleado)) {
            (mapaEmpleadosServicios.get(idEmpleado)).add(servicio);
        } else {
            ArrayList<Servicio> servicios = new ArrayList<>();
            servicios.add(servicio);
            mapaEmpleadosServicios.put(idEmpleado, servicios);
        }
    }

    public void agregarServicioFactura(String idReserva, int saldoAPagar, String nombreServicio, String idEmpleado, boolean pagar) {
        this.cargarFacturas();
        Mesero mesero = new Mesero(null, null);
        GuiaTuristico guia = new GuiaTuristico(null, null);
        EncargadoDeSpa encargadoSpa = new EncargadoDeSpa(null, null);
 
        if (nombreServicio.equals("tour")) {
            guia.agregarServicioFactura(idReserva, saldoAPagar, nombreServicio, idEmpleado, pagar);
        } else if (nombreServicio.equals("spa")) {
            encargadoSpa.agregarServicioFactura(idReserva, saldoAPagar, nombreServicio, idEmpleado, pagar);
        } else if (nombreServicio.equals("restaurante")) {
            mesero.agregarServicioFactura(idReserva, saldoAPagar, nombreServicio, idEmpleado, pagar);
        }
        this.guardarFacturas();

    }
    public String factura(String idResrva){
        cargarFacturas();
        cargarRegistro();
        cargarUsuarios();
        return recepcionista.consultarFactura(idResrva);
    }
    public void registrarPago(int dinero, Servicio servicio, Empleado empleado) {
        empleado.registrarPago(dinero, servicio);
    }

    public void registrarPagoRestaurante(int dinero, Servicio servicio, Mesero mesero) throws Exception {
        mesero.registrarPago(dinero, servicio);
    }
    public void registrarPagoSpa(int dinero, Servicio servicio, EncargadoDeSpa encargadoSpa) {
        encargadoSpa.registrarPago(dinero, servicio);
    }
    
    //nuevo
    
    public void guardarVentas() throws Exception {
    	ventas.guardar(App.path + "/entrega 3/Data/pVentas.txt");
    }
    
    public void registrarPagoTour(int dinero, Servicio servicio, GuiaTuristico guia) {
        guia.registrarPago(dinero, servicio);
    }
    
    public HashMap<String, Float> getServiciosPrecios() { //El mapa se llama mapaServiciosPersist
    	return mapaServiciosPersist;
    }
    
    public void cargarServiciosPrecios() {
    	archivoServicios.leerServicios(mapaServiciosPersist);
    }
    
    public void addServiciosNoPagos(Servicio servicio) { // Los servicios no pagos son los que se pagan al final (tour y spa)
    	serviciosNoPagos.add(servicio);
    }

    public void addServiciosPagos(Servicio servicio) { // Los servicios pagos son los que se pagan al instante (restaurante)
    	serviciosPagos.add(servicio);
    }
    

    // funciones para cargar / agregar / eliminar / actualizar productos de los
    // menus

    public void cargarMenus() {
        archivoMenuHabitacion = new PersistenciaMenuHabitacion();
        archivoMenuHabitacion.leerPlatos(mapaPlatosMenuHabitacion);
        archivoMenuHabitacion.leerBebidas(mapaBebidasMenuHabitacion);

        archivoMenuComedor = new PersistenciaMenuComedor();
        archivoMenuComedor.leerPlatos(mapaPlatosMenuComedor);
        archivoMenuComedor.leerBebidas(mapaBebidasMenuComedor);

    }

        public HashMap<String, Plato> getPlatosMenuHabitacion(){
            return mapaPlatosMenuHabitacion;
        }

        public HashMap<String, Bebida> getBebidasMenuHabitacion(){
            return mapaBebidasMenuHabitacion;
        }

        public HashMap<String, Plato> getPlatosMenuComedor(){
            return mapaPlatosMenuComedor;
        }

        public HashMap<String, Bebida> getBebidasMenuComedor(){
            return mapaBebidasMenuComedor;
        }

    public void actualizarMenus(String nombre, float precio, LocalTime fechaInicio, LocalTime fechaFin, int f,
            boolean esPlato, boolean esHabitacion) {
        /*
         * Esta funcion sirve para actualizar 1 producto de alguno de los menus (comedor
         * o habitacion)
         */
        archivoMenuHabitacion = new PersistenciaMenuHabitacion();
        archivoMenuComedor = new PersistenciaMenuComedor();

        if (esHabitacion) {
            if (esPlato) {
                archivoMenuHabitacion.eliminarPlato(nombre, mapaPlatosMenuHabitacion);
                archivoMenuHabitacion.escribirPlato(nombre, precio, fechaInicio, fechaFin, f,
                        mapaPlatosMenuHabitacion);
            } else {
                archivoMenuHabitacion.eliminarBebida(nombre, mapaBebidasMenuHabitacion);
                archivoMenuHabitacion.escribirBebida(nombre, precio, fechaInicio, fechaFin, f,
                        mapaBebidasMenuHabitacion);
            }
        } else {
            if (esPlato) {
                archivoMenuComedor.eliminarPlato(nombre, mapaPlatosMenuComedor);
                archivoMenuComedor.escribirPlato(nombre, precio, fechaInicio, fechaFin, f,
                        mapaPlatosMenuComedor);
            } else {
                archivoMenuComedor.eliminarBebida(nombre, mapaBebidasMenuComedor);
                archivoMenuComedor.escribirBebida(nombre, precio, fechaInicio, fechaFin, f,
                        mapaBebidasMenuComedor);
            }
        }
    }

    public void escribirProducto(String nombre, float precio, LocalTime fechaInicio, LocalTime fechaFin, int impuesto,
            boolean esPlato, boolean esHabitacion) {
        /*
         * Esta funcion sirve para actualizar 1 producto de alguno de los menus (comedor
         * o habitacion)
         */
        archivoMenuHabitacion = new PersistenciaMenuHabitacion();
        archivoMenuComedor = new PersistenciaMenuComedor();

        if (esHabitacion) {
            if (esPlato) {
                archivoMenuHabitacion.escribirPlato(nombre, precio, fechaInicio, fechaFin, impuesto,
                        mapaPlatosMenuHabitacion);
            } else {
                archivoMenuHabitacion.escribirBebida(nombre, precio, fechaInicio, fechaFin, impuesto,
                        mapaBebidasMenuHabitacion);
            }
        } else {
            if (esPlato) {
                archivoMenuComedor.escribirPlato(nombre, precio, fechaInicio, fechaFin, impuesto,
                        mapaPlatosMenuComedor);
            } else {
                archivoMenuComedor.escribirBebida(nombre, precio, fechaInicio, fechaFin, impuesto,
                        mapaBebidasMenuComedor);
            }
        }
    }

    public void eliminarProducto(String nombre, boolean esPlato, boolean esHabitacion) {
        /*
         * Esta funcion sirve para actualizar 1 producto de alguno de los menus (comedor
         * o habitacion)
         */
        archivoMenuHabitacion = new PersistenciaMenuHabitacion();
        archivoMenuComedor = new PersistenciaMenuComedor();

        if (esHabitacion) {
            if (esPlato) {
                archivoMenuHabitacion.eliminarPlato(nombre, mapaPlatosMenuHabitacion);
            } else {
                archivoMenuHabitacion.eliminarBebida(nombre, mapaBebidasMenuHabitacion);
            }
        } else {
            if (esPlato) {
                archivoMenuComedor.eliminarPlato(nombre, mapaPlatosMenuComedor);
            } else {
                archivoMenuComedor.eliminarBebida(nombre, mapaBebidasMenuComedor);
            }
        }
    }

    // Funciones para agregar y eliminar servicios

    public void cargarServicios() {
        archivoServicios = new PersistenciaServicio();
        archivoServicios.leerServicios(mapaServiciosPersist);
    }

    public void actualizarservicio(String nombre, float precio) {
        archivoServicios = new PersistenciaServicio();
        cargarServicios();
        archivoServicios.actualizarServicios(nombre, precio, mapaServiciosPersist);
    }

    // ======================= OCUPACIÓN ==============================
    
    public Object[] formatOcupacion() {
    	
    	/* 
    	 * En posición 0 retorna el total de reservas, 
    	 * En posición 1 retorna el mapa con el formato para la ocupación
    	 * */
    	
    	cargarHabitaciones();
    	
    	// Construir el nuevo mapa <Año, <Mes, cantidad> >
    	HashMap<Integer, HashMap<Integer, Integer>> ocupacion = new HashMap <Integer, HashMap<Integer, Integer>>();
    	int totalReservas = 0;
    	
    	// Ir clasificando las habitaciones del mapa Reserva Habitaciones
    	
    	for (ArrayList<LocalDate> listaFechas : ReservaHabitaciones.values()) {
    		for (LocalDate fecha : listaFechas) {
    			int anio = fecha.getYear();
    			int mes = fecha.getMonthValue();
    			int cantidad;
    			totalReservas += 1;
    			
    			HashMap<Integer, Integer> mapAnio;
    			
    			if (!ocupacion.containsKey(anio)) {
    				ocupacion.put(anio, new HashMap<Integer, Integer>());			
    			}
    			
    			mapAnio = ocupacion.get(anio);
    			
    			if (!mapAnio.containsKey(mes)) {
    				cantidad = 0;
    			}
    			else {
    				cantidad = mapAnio.get(mes);
    			}
    			
    			cantidad += 1;
    			mapAnio.put(mes, cantidad);
    		}
    	}
    	
    	Object[] retorno = {totalReservas, ocupacion};
    	
    	return retorno;
    }
    
    
    // -----------PASARELAS DE PAGO------------
    
    public void cargarPasarelas() throws IOException {

            BufferedReader brUs = new BufferedReader(new FileReader(App.path + "/entrega 3/Data/PasarelasNombres.txt"));
            String linea;
            while ((linea = brUs.readLine()) != null) {
                pasarelasDePagoStr.add(linea);
            }
            brUs.close();
        
    	
    	crearPasarelas();
    }
    
    public void crearPasarelas() {
    	
    	for(String namePasarela: pasarelasDePagoStr) {
    		PasarelaGenerica pasarela = new PasarelaGenerica(namePasarela);
    		pasarelasDePagoObj.add(pasarela);
    	}
    	
    }
    
    public ArrayList<String> getpasarelasDePagoStr() {
    	return pasarelasDePagoStr;
    }
    
    public ArrayList<PasarelaGenerica> getpasarelasDePagoObj() {
    	return pasarelasDePagoObj;
    }

    public HashMap<String, Integer> graficaServicios() {
        cargarFacturas();
        HashMap<String, ArrayList<Servicio>> mapaservicio = getMapaServicios();

        HashMap<String, Integer> frecuenciaServicios = new HashMap<>();

       for (ArrayList<Servicio> servicios : mapaservicio.values()) {
            for (Servicio servicio : servicios) {
                String nombreServicio = servicio.getNombreServicio();
                if (frecuenciaServicios.containsKey(nombreServicio)) {
                    int frecuencia = frecuenciaServicios.get(nombreServicio);
                    frecuenciaServicios.put(nombreServicio, frecuencia + 1);
                } else {
                     frecuenciaServicios.put(nombreServicio, 1);
                }
            }
        }
        return frecuenciaServicios;

    }

    public HashMap<String, Double> graficaRelacion() {
        cargar();
        cargarFacturas();
        HashMap<String, Reserva> reservas = this.reservas();
        HashMap<String, ArrayList<Servicio>> mapaservicio = getMapaServicios();
    
        HashMap<String, Double> nuevoHashMap = new HashMap<>();
    
        for (String reservaId : reservas.keySet()) {
            if (mapaservicio.containsKey(reservaId)) {
                Reserva reserva = reservas.get(reservaId);
                double tarifaTotal = reserva.getTarifaTotal();
    
                ArrayList<Servicio> servicios = mapaservicio.get(reservaId);
                double tarifaRestaurante = 0;
    
                for (Servicio servicio : servicios) {
                    if (servicio.getNombreServicio().equals("restaurante")) {
                        tarifaRestaurante += servicio.gettarifaServicio();
                    }
                }
    
                String habitacion = reserva.getHabitacion();
                String[] letras = habitacion.split(", ");
    
                for (String letra : letras) {
                    String keyRestaurante = letra + "_restaurante";
                    String keyTarifa = letra + "_tarifa";
    
                    // Verificar si la llave ya estaba en el HashMap
                    if (nuevoHashMap.containsKey(keyRestaurante)) {
                        double valorRestauranteAnterior = nuevoHashMap.get(keyRestaurante);
                        tarifaRestaurante += valorRestauranteAnterior;
                    }
                    if (nuevoHashMap.containsKey(keyTarifa)) {
                        double valorTarifaAnterior = nuevoHashMap.get(keyTarifa);
                        tarifaTotal += valorTarifaAnterior;
                    }
    
                    nuevoHashMap.put(keyRestaurante, tarifaRestaurante);
                    nuevoHashMap.put(keyTarifa, tarifaTotal);
                }
            }
        }
        return nuevoHashMap;
    }
    
    
}

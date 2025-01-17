package Paquete;

import Paquete.Clases.Ejemplar;
import Paquete.Clases.Prestamo;
import Paquete.Clases.Usuario;

import java.time.LocalDate;
import java.util.List;

public class Validaciones {
    //QUE LA FECHA DE DEVOLUCIÓN DE UN PRESTAMO SEA SIEMPRE 15 DÍAS DESPUES
    public static LocalDate calcularFechaDevolucion(LocalDate fechaInicio){
        return fechaInicio.plusDays(15);
    }

    //Que el estado penalizado de un usuario pase a estar activo con una fecha de 15 días de penalización desde
    //que devuelve el libro y no pueda hacer más préstamos hasta esa fecha
    public static void calcularFechaPenalizacion(Usuario usuario, List<Prestamo> listaPrestamos){
        int diasPenalizacion = 0;
        for (Prestamo prestamo : listaPrestamos) {
            //si la fecha de devolución es distinta de nula o se pasa de los 15 días es que tiene 15 días de penalización por cada vez que ocurra
            if(prestamo.getFechaDevolucion()!=null&& prestamo.getFechaDevolucion().isAfter(prestamo.getFechaInicio().plusDays(15))){
                diasPenalizacion+=15;
            }
        }
        //Si tiene algún día de penalización....
        if(diasPenalizacion>0){
            //...Se le ponen los días de penalización extra
            usuario.setPenalizacionHasta(LocalDate.now().plusDays(diasPenalizacion));
        }
    }

    //Saber si el usuario está penalizado
    public static boolean isPenalizado(Usuario usuario){
        //Si la penalización NO es null o la fecha de penalizadoHasta es menos de los 15 días de penalización...
        //...entonces isPenalizado es true
        return usuario.getPenalizacionHasta()!=null && LocalDate.now().isBefore(usuario.getPenalizacionHasta());
    }

    //Si está penalizado no puede recibir más préstamos
    public static boolean puedeMásPrestamos (Usuario usuario){
        if (isPenalizado(usuario)){//Si el usuario está con penalizado a true
            System.out.println("El usuario no puede recibir más préstamos hasta "+ usuario.getPenalizacionHasta());
            return false;
        }
        return true;
    }

    //Validación del ISBN13
    public static boolean ISBN13Valido(String ISBN){
        //Que la longitud sea 13 y que solo sean números
        final String ISBNPatron ="^\\d{13}$";
        if (!ISBN.matches(ISBNPatron)){
            return false;
        }
        //Validar el dígito de control
        int suma =0;
        //Recorrer los primeros 12 números
        for (int i = 0; i < 12; i++){
            //transformar el string a dígito por cada posición
            int digito = Character.getNumericValue(ISBN.charAt(i));
            if (i%2==0){
                //los dígitos de las posiciones impares se suman
                suma += digito;
            }else{
                //Los dígitos de las posiciones pares se multiplican por 3
                suma += digito*3;
            }
        }
        //Obtener el último dígito que es el de control
        int digitoControl= Character.getNumericValue(ISBN.charAt(12));
        //Calcular el dígito de control
        int caluclarDigitoControl = (10-(suma % 10))%10;

        //Comparar el dígito de control calculado con el puesto
        return caluclarDigitoControl == digitoControl;
    }
    //Validación del DNI
    public static boolean validarDNI(String dni) {
        String regex = "\\d{8}[A-Za-z]";

        if (dni == null || !dni.matches(regex)) {
            return false;
        }

        String numeros = dni.substring(0, 8);
        char letra = dni.charAt(8);

        char letraCalculada = calcularLetraDNI(numeros);

        return letra == letraCalculada;
    }

    private static char calcularLetraDNI(String numeros) {
       int numero = Integer.parseInt(numeros);

       int resto = numero % 23;

       char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

       return letras[resto];
    }

    //Contar stock disponible contando estado disponible
    public static int calcularStockDisponible(List<Ejemplar> listaEjemplares){
        int contadorDisponibles=0;
        //Recorres la lista de ejemplares
        for (Ejemplar ejemplar: listaEjemplares){
            //Si el estado es igual al estado enum disponible...
            if(ejemplar.getEstado()==Ejemplar.EstadoEjemplar.Disponible){
                //...Aumenta el contador
                contadorDisponibles++;
            }
        }
        return contadorDisponibles;
    }

    //Validar que un usuario no puede tener más de 3 préstamos activos
    public static boolean limiteTresPrestamos(int usuarioId, List<Prestamo> listaPrestamosUsuario) {
        int prestamosUsuario=0;
        boolean usuarioExiste=false;
        for (Prestamo prestamo: listaPrestamosUsuario){
            if(prestamo.getUsuario().getId()==usuarioId){
                usuarioExiste=true;
                boolean prestamoEnProceso = prestamo.getFechaDevolucion()==null || prestamo.getFechaDevolucion().isBefore(prestamo.getFechaInicio().plusDays(15));
                boolean noPenalizado = !isPenalizado(prestamo.getUsuario());
                if(prestamoEnProceso && noPenalizado){
                    prestamosUsuario++;
                }
            }
        }
        if (usuarioExiste == false) {
            System.out.println("El usuario con id "+usuarioId+" no existe");
            return false;
        }
            if (prestamosUsuario>=3){
                System.out.println("El usuario con id "+usuarioId+" ya tiene 3 préstamos activos");
                return false;
            }
        return true;
    }
}

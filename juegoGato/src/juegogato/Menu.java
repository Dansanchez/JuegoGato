/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegogato;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Daniel Sanchez
 */
public class Menu {

    private TableroGato _TableroGato;
    private BufferedReader _Lectura;
    private int _TurnoJugador;

    /**
     * Constructor
     */
    public  Menu()
    {
        System.out.println(MENSAJE_BIENVENIDA);
        System.out.println(MENSAJE_MARCA_JUGADOR_UNO);
        System.out.println(MENSAJE_MARCA_JUGADOR_DOS);
        System.out.println(MENSAJE_FILAS_COLUMNAS);
        _TableroGato = new TableroGato();
        _TableroGato.inicializarMatrizTablero();
        _Lectura = new BufferedReader(new InputStreamReader(System.in));
        _TurnoJugador = 1;
    }

    /**
     * Metodo que se encarga de manejar el menu para el juego de gato
     * @throws IOException
     */
    public void pintarMenu() throws IOException
    {
        int fila,columna,resultadoJugada;
        String caracterLeidoFila = "",caracterLeidoColumna = "";
        
        while(true)
        {
            System.out.println("\nTurno Jugador: "+_TurnoJugador+"\n");
            _TableroGato.visualizarTablero();
            System.out.println("\nElija la casilla que desea marcar");
            System.out.print("Fila: ");
            caracterLeidoFila = leerDatosTeclado();
            System.out.print("Columna: ");
            caracterLeidoColumna = leerDatosTeclado();

            if(esNumero(caracterLeidoFila)    == true &&
               esNumero(caracterLeidoColumna) == true)
            {
                fila = Integer.parseInt(caracterLeidoFila);
                columna = Integer.parseInt(caracterLeidoColumna);
                resultadoJugada = _TableroGato.realizarJugada(fila, columna, _TurnoJugador);
                crearAdvertencia(resultadoJugada);

                if(_TableroGato.validarJugadaGanadora() == true)
                {
                    System.out.println("-------------------\n");
                    System.out.println("Victoria del jugador: " + _TurnoJugador + "\n");
                    _TableroGato.visualizarTablero();
                    System.out.println("\n"+MENSAJE_FIN_JUEGO);
                    break;
                }

                if(_TableroGato.validarTableroLleno()== true)
                {
                    System.out.println("-------------------");
                    _TableroGato.visualizarTablero();
                    System.out.println("No hay ganador"+"\n"+MENSAJE_FIN_JUEGO+"\n" );
                    break;
                }

                System.out.println("-------------------");

                if(_TurnoJugador == TURNO_JUGADOR_1 && resultadoJugada == JUGADA_VALIDA)
                    _TurnoJugador++;
                else if(_TurnoJugador == TURNO_JUGADOR_2 && resultadoJugada == JUGADA_VALIDA)
                    _TurnoJugador--;
                else
                    System.out.println(MENSAJE_INTENTE_DE_NUEVO);
            }
            else
                System.out.println(MENSAJE_DATOS_SON_LETRAS +"\n"+MENSAJE_INTENTE_DE_NUEVO+"\n" );
        }
    }

    
    /**
     * Metodo que se encarga de la lectura de datos del teclado
     * @return retorna el comando leido desde el teclado
     * @throws IOException
     */
    public String leerDatosTeclado() throws IOException
    {
        String caracterLeido="";
        caracterLeido = _Lectura.readLine();

        return caracterLeido;
    }


    /**
     * Metodo encargado de decifrar si un string contiene numeros o letras
     * @param pCadena cadena de caracteres que es leida del teclado
     * @return retorna true si la cadena solo contiene numeros
     *         retorna false en caso contrario
     */
    public boolean esNumero(String pCadena)
    {
        try
        {
            Integer.parseInt(pCadena);
            return true;
        }
        catch(NumberFormatException e)
        {
            return false;
        }
    }

    
    /**
     * Metodo que se encarga de mostrar advertencias al usuario en caso de
     * haber realizado una jugada de manera correcta o indica si hubo algun eror
     * @param pResultadoRealizarJugada es una bandera que indica si la jugada
     *                                 efectuada fue valida o incorrecta
     */
    public void crearAdvertencia(int pResultadoRealizarJugada)
    {
        if(pResultadoRealizarJugada == 0)
           System.out.println(MENSAJE_JUGADA_VALIDA);
        else if(pResultadoRealizarJugada == 1)
           System.out.println(MENSAJE_VALORES_MATRIZ_FUERA_RANGO);
        else if(pResultadoRealizarJugada == 2)
            System.out.println(MENSAJE_CASILLA_OCUPADA);
    }

    
    /**
     * Constantes
     */
    private String MENSAJE_JUGADA_VALIDA = "<< Jugada realizada con exito >>";
    private String MENSAJE_VALORES_MATRIZ_FUERA_RANGO = "<< Valores ingresados fuera de rango >>";
    private String MENSAJE_CASILLA_OCUPADA = "<< Casilla ocupada >>";
    private String MENSAJE_DATOS_SON_LETRAS = "<< Los datos ingresados son letras >>";
    private String MENSAJE_INTENTE_DE_NUEVO = "<< Intentelo de nuevo >>";
    private String MENSAJE_FIN_JUEGO = "**** FIN DEL JUEGO ****";
    private String MENSAJE_BIENVENIDA = "=== Bienvenido al Juego de Gato ===";
    private String MENSAJE_MARCA_JUGADOR_UNO = "Marca del jugador 1 : X" ;
    private String MENSAJE_MARCA_JUGADOR_DOS = "Marca del jugador 2 : O" ;
    private String MENSAJE_FILAS_COLUMNAS = "Tanto filas como columnas pueden ser numeros entre 0 y 2";
    private int TURNO_JUGADOR_1 = 1;
    private int TURNO_JUGADOR_2 = 2;
    private int JUGADA_VALIDA = 0;


    /**
     * Main
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        Menu menu= new Menu();
        menu.pintarMenu();
    }

}

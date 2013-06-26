/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package juegogato;

/**
 * @author Daniel Sanchez
 */
public class TableroGato
{
    private String _MatrizTablero[][];

    /**
     * Constructor
     */
    public TableroGato()
    {
        _MatrizTablero = new String [TAMANO_TABLERO][TAMANO_TABLERO];
    }

    
    /**
     *Metodo que se encarga de inicializar el tablero
     * con cada casilla vacia
     */
    public void inicializarMatrizTablero()
    {
        for(int contadorFilas = 0; contadorFilas < TAMANO_TABLERO  ;contadorFilas++)
        {
            for(int contadorColumnas = 0; contadorColumnas < TAMANO_TABLERO ;contadorColumnas++)
            {
                if (contadorFilas < TAMANO_TABLERO -1)
                {
                    _MatrizTablero[contadorFilas][contadorColumnas]= PISO_TABLERO_VACIO_UNO;
                }
                else
                {
                    _MatrizTablero[contadorFilas][contadorColumnas]= PISO_TABLERO_VACIO_DOS;
                }

            }
        }
    }

    
    /**
     * Metodo que se encarga de pintar el tablero
     * en la consola
     */
    public void visualizarTablero()
    {
        for(int contadorFilas = 0; contadorFilas < TAMANO_TABLERO  ;contadorFilas++)
        {
            for(int contadorColumnas = 0; contadorColumnas < TAMANO_TABLERO ;contadorColumnas++)
            {
                if (contadorFilas < TAMANO_TABLERO -1)
                {
                    System.out.print(_MatrizTablero[contadorFilas][contadorColumnas]);
                }
                else
                {
                    System.out.print(_MatrizTablero[contadorFilas][contadorColumnas]);
                }
                if(contadorColumnas < TAMANO_TABLERO -1)
                {
                    System.out.print(PARED_TABLERO);
                }
                
            }

            System.out.println();
        }
    }


    /**
     * Metodo encargado de agregar una jugada al tablero
     * @param pFila  fila en la cual el jugador quiere colocar su marca
     * @param pColumna  columna en la cual el jugador quiere colocar su marca
     * @param pTurnoJugador  indica si el turno es del jugador 1 o 2
     * @return retorna 0 si la jugada es valida,
     *                 1 si hay error en el rango de la matriz,
     *                 2 si la casilla para realizar la jugada esta ocupada
     */
    public int realizarJugada(int pFila, int pColumna, int pTurnoJugador)
    {
        int jugadaValida = 0;
        int valoresMatrizFueraRango = 1;
        int casillaOcupada = 2;
        int retorno = jugadaValida;

        if(pFila    >= 0 && pFila    < TAMANO_TABLERO &&
           pColumna >= 0 && pColumna < TAMANO_TABLERO &&
           pTurnoJugador == 1)
        {
            if(_MatrizTablero [pFila][pColumna].equals(PISO_TABLERO_VACIO_UNO) ||
               _MatrizTablero [pFila][pColumna].equals(PISO_TABLERO_VACIO_DOS))
            {
                _MatrizTablero[pFila][pColumna] = MARCA_JUGADOR_UNO;
            }
            else
                retorno = casillaOcupada;
        }
        else
        if(pFila    >= 0 && pFila    < TAMANO_TABLERO &&
           pColumna >= 0 && pColumna < TAMANO_TABLERO &&
           pTurnoJugador == 2)
        {
            if(_MatrizTablero [pFila][pColumna].equals(PISO_TABLERO_VACIO_UNO) ||
               _MatrizTablero [pFila][pColumna].equals(PISO_TABLERO_VACIO_DOS))
            {
                _MatrizTablero[pFila][pColumna] = MARCA_JUGADOR_DOS;
            }
            else
                retorno = casillaOcupada;
        }
        else
            retorno = valoresMatrizFueraRango;
        
        return retorno;
    }


    /**
     * Metodo encargado de validar si hay un ganador en el juego de gato
     * @return retorna true si hay 3 marcas seguidas del mismo tipo
     *         en el tablero, lo que inica que hay un ganador, y
     *         retorna false en caso contrario
     */
    public boolean validarJugadaGanadora()
    {
        boolean banderaVictoria = false;

        if(validarJugadaGanadoraFilas()             == true ||
           validarJugadaGanadoraColumnas()          == true ||
           validarJugadaGanadoraDiagonalPrincipal() == true ||
           validarJugadaGanadoraDiagonalInversa()   == true)
        {
            banderaVictoria = true;
        }

        return banderaVictoria;
    }


    /**
     * Metodo que valida si hay 3 marcas consecutivas del mismo tipo
     * en alguna de las filas del tablero (jugada ganadora)
     * @return retorna true en caso de que hayan 3 marcas consecutivas
     *         retorna false en caso contrario
     */
    public boolean validarJugadaGanadoraFilas()
    {
        boolean banderaVictoria = false;
        int contadorMarcasFilaJugadorUno = 0;
        int contadorMarcasFilaJugadorDos = 0;

        for(int contadorFilas = 0; contadorFilas < TAMANO_TABLERO  ;contadorFilas++)
        {
            for(int contadorColumnas = 0; contadorColumnas < TAMANO_TABLERO ;contadorColumnas++)
            {
                if(_MatrizTablero[contadorFilas][contadorColumnas].equals(MARCA_JUGADOR_UNO))
                {
                    contadorMarcasFilaJugadorUno++;
                    if(contadorMarcasFilaJugadorUno == TAMANO_TABLERO)
                    {
                        banderaVictoria = true;
                        break;
                    }
                }
                if(_MatrizTablero[contadorFilas][contadorColumnas].equals(MARCA_JUGADOR_DOS))
                {
                    contadorMarcasFilaJugadorDos++;
                    if(contadorMarcasFilaJugadorDos == TAMANO_TABLERO)
                    {
                        banderaVictoria = true;
                        break;
                    }
                }

            }
            contadorMarcasFilaJugadorUno = 0;
            contadorMarcasFilaJugadorDos = 0;
        }
        
        return banderaVictoria;
    }


    /**
     * Metodo que valida si hay 3 marcas consecutivas del mismo tipo
     * en alguna de las columnas del tablero (jugada ganadora)
     * @return retorna true en caso de que hayan 3 marcas consecutivas
     *         retorna false en caso contrario
     */
    public boolean validarJugadaGanadoraColumnas()
    {
        boolean banderaVictoria = false;
        int contadorMarcasColumnaJugadorUno = 0;
        int contadorMarcasColumnaJugadorDos = 0;

        for(int contadorColumnas = 0; contadorColumnas < TAMANO_TABLERO  ;contadorColumnas++)
        {
            for(int contadorFilas = 0; contadorFilas < TAMANO_TABLERO ;contadorFilas++)
            {
                if(_MatrizTablero[contadorFilas][contadorColumnas].equals(MARCA_JUGADOR_UNO))
                {
                    contadorMarcasColumnaJugadorUno++;
                    if(contadorMarcasColumnaJugadorUno == TAMANO_TABLERO)
                    {
                        banderaVictoria = true;
                        break;
                    }
                }
                if(_MatrizTablero[contadorFilas][contadorColumnas].equals(MARCA_JUGADOR_DOS))
                {
                    contadorMarcasColumnaJugadorDos++;
                    if(contadorMarcasColumnaJugadorDos == TAMANO_TABLERO)
                    {
                        banderaVictoria = true;
                        break;
                    }
                }

            }

            contadorMarcasColumnaJugadorUno = 0;
            contadorMarcasColumnaJugadorDos = 0;
        }
        
        return banderaVictoria;
    }


    /**
     * Metodo que valida si hay 3 marcas consecutivas del mismo tipo
     * en la diagonal principal (izquierda a derecha) del tablero(jugada ganadora)
     * @return retorna true en caso de que hayan 3 marcas consecutivas
     *         retorna false en caso contrario
     */
    public boolean validarJugadaGanadoraDiagonalPrincipal()
    {
        boolean banderaVictoria = false;
        int contadorMarcasDiagonalJugadorUno = 0;
        int contadorMarcasDiagonalJugadorDos = 0;
        int contadorFilas = 0,contadorColumnas = 0;

        while(contadorFilas < TAMANO_TABLERO)
        {
            if(_MatrizTablero[contadorFilas][contadorColumnas].equals(MARCA_JUGADOR_UNO))
            {
                contadorMarcasDiagonalJugadorUno++;
                if(contadorMarcasDiagonalJugadorUno == TAMANO_TABLERO)
                {
                    banderaVictoria = true;
                    break;
                }
            }
            if(_MatrizTablero[contadorFilas][contadorColumnas].equals(MARCA_JUGADOR_DOS))
            {
                contadorMarcasDiagonalJugadorDos++;
                if(contadorMarcasDiagonalJugadorDos == TAMANO_TABLERO)
                {
                    banderaVictoria = true;
                    break;
                }
            }

            contadorFilas++;
            contadorColumnas++;
        }
   
        return banderaVictoria;
    }


    /**
     * Metodo que valida si hay 3 marcas consecutivas del mismo tipo
     * en la diagonal inversa (derecha a izquierda) del tablero(jugada ganadora)
     * @return retorna true en caso de que hayan 3 marcas consecutivas
     *         retorna false en caso contrario
     */
    public boolean validarJugadaGanadoraDiagonalInversa()
    {
        boolean banderaVictoria = false;
        int contadorMarcasDiagonalJugadorUno = 0;
        int contadorMarcasDiagonalJugadorDos = 0;
        int contadorFilas = 0,contadorColumnas = 2;

        while(contadorFilas < TAMANO_TABLERO)
        {
            if(_MatrizTablero[contadorFilas][contadorColumnas].equals(MARCA_JUGADOR_UNO))
            {
                contadorMarcasDiagonalJugadorUno++;
                if(contadorMarcasDiagonalJugadorUno == TAMANO_TABLERO)
                {
                    banderaVictoria = true;
                    break;
                }
            }
            if(_MatrizTablero[contadorFilas][contadorColumnas].equals(MARCA_JUGADOR_DOS))
            {
                contadorMarcasDiagonalJugadorDos++;
                if(contadorMarcasDiagonalJugadorDos == TAMANO_TABLERO)
                {
                    banderaVictoria = true;
                    break;
                }
            }

            contadorFilas++;
            contadorColumnas--;
        }

        return banderaVictoria;

    }


    /**
     * Metodo que verifica si el tablero de juego se encuentra lleno, es decir
     * no cuenta con mas espacios libres
     * @return retorna true si el tablero esta lleno
     *         retorna false en caso contrario
     */
    public boolean validarTableroLleno()
    {
        boolean tableroLleno = true;

        for(int contadorFilas = 0; contadorFilas < TAMANO_TABLERO  ;contadorFilas++)
        {
            for(int contadorColumnas = 0; contadorColumnas < TAMANO_TABLERO ;contadorColumnas++)
            {
                if (_MatrizTablero[contadorFilas][contadorColumnas].equals(PISO_TABLERO_VACIO_UNO) ||
                    _MatrizTablero[contadorFilas][contadorColumnas].equals(PISO_TABLERO_VACIO_DOS)   )
                {
                    tableroLleno = false;
                }

            }
        }

        return tableroLleno;
    }

    /**
     * Constantes
     */
    private int TAMANO_TABLERO = 3;
    private String MARCA_JUGADOR_UNO = "_X_";
    private String MARCA_JUGADOR_DOS = "_O_";
    private String PISO_TABLERO_VACIO_UNO = "___";
    private String PISO_TABLERO_VACIO_DOS = "   ";
    private String PARED_TABLERO = "|";

}

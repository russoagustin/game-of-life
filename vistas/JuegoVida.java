package vistas;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.awt.Color;

public class JuegoVida extends JPanel implements MouseListener {
    // VARIABLES DEL PANEL
    private static final int CELL_SIZE = 21;
    private static final int WIDTH = 900;
    private static final int HEIGHT = 900;
    private static final int ROW = WIDTH / CELL_SIZE;
    private static final int COLUMNS = HEIGHT / CELL_SIZE;
    // VARIABLES DE TABLERO
    private static final Color[] colores = { Color.CYAN, Color.WHITE, Color.MAGENTA, Color.green };
    private static int colorIndex = 0;
    private static Color colorActual = colores[0];

    // VARIABLES DE VISTA
    private static int[] gridOptions = { 1, 2, 3, 4 };
    private static int gridIndex = 0;
    private static int actualGrid = 1;
    private static boolean pausa = false;
    private static int[][] tablero = new int[ROW][COLUMNS];

    public JuegoVida() {
        this.setSize(WIDTH, HEIGHT);
        // this.setBackground(new Color(56,216,252));
        this.setBackground(Color.BLACK);
        this.setLocation(-4500, -4500);
        this.addMouseListener(this);

    }

    public void pausa() {
        pausa = !pausa;
    }

    public boolean getPausa() {
        return pausa;
    }

    // LLENA LA MATRIZ CON 1 Y 0 ALEATORIAMENTE
    public void tableroAleatorio() {
        Random aleatorio = new Random();
        for (int e = 0; e < 3*ROW; e++) {
            for (int i = 2*ROW/CELL_SIZE; i <= aleatorio.nextInt(ROW); i++) {
                for (int j = 2*ROW/CELL_SIZE; j <= aleatorio.nextInt(COLUMNS); j++) {
                    tablero[i][j] = aleatorio.nextInt(2);
                }
            }
        }
        
    }

    // ACTUALIZA EL TABLERO AL SIGUIENTE ESTADO
    public void actualizarTablero() {
        if (pausa) {
            int[][] nuevoTablero = new int[ROW][COLUMNS];
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    if (contarVecinos(i, j) == 3) {
                        nuevoTablero[i][j] = 1;
                    } else if (tablero[i][j] == 1 && (contarVecinos(i, j) > 3 || contarVecinos(i, j) <= 1)) {
                        nuevoTablero[i][j] = 0;
                    } else if (tablero[i][j] == 1 && contarVecinos(i, j) == 2) {
                        nuevoTablero[i][j] = 1;
                    }
                }
            }
            tablero = nuevoTablero;
        }

    }

    //CUENTA LA CANTIDAD DE VECINOS VIVOS DE UNA DETERMINADA CELDA
    //EN UN TABLERO "TOROIDAL" (EXTREMOS CONTIGUOS)
    private int contarVecinos(int a, int b) {
        int suma = 0;
        suma = tablero[(a + 1) % ROW][(b + 1) % COLUMNS] +
                tablero[(a) % ROW][(b + 1) % COLUMNS] +
                tablero[(a + 1) % ROW][(b) % COLUMNS] +
                tablero[(ROW + a - 1) % ROW][(COLUMNS + b - 1) % COLUMNS] +
                tablero[(ROW + a - 1) % ROW][(b + 1) % COLUMNS] +
                tablero[(a + 1) % ROW][(COLUMNS + b - 1) % COLUMNS] +
                tablero[(ROW + a) % ROW][(COLUMNS + b - 1) % COLUMNS] +
                tablero[(ROW + a - 1) % ROW][(COLUMNS + b) % COLUMNS];

        // System.out.println(suma);
        return suma;
    }
    /* 
    // CUENTA LA CANTIDAD DE VECINOS VIVOS DE UNA DETERMINADA CELDA
    private static int contarVecinosVivos(int a, int b) {
        int suma = 0;
        for (int i = a - 2; i <= a + 1; i++) {
            for (int j = b - 2; j <= b + 1; j++) {
                // suma=suma+tablero[(i+1)%ROW][(j+1)%COLUMNS];
                if (i >= 0 && j >= 0 && i < ROW && j < COLUMNS) {
                    // suma = suma + tablero[i][j];
                    // suma=suma+tablero[(i+1)%ROW][(j+1)%COLUMNS];
                }
            }
        }
        if (tablero[a][b] == 1) {
            return suma - 1;
        } else {
            return suma;
        }
    }*/

    public void limpiarTablero() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = 0;
            }
        }
    }

    public void cambiarColor() {
        colorActual = colores[(colorIndex + 1) % colores.length];
        colorIndex = colorIndex + 1;
    }

    public void cambiarGrid() {
        actualGrid = gridOptions[(gridIndex + 1) % gridOptions.length];
        gridIndex = gridIndex + 1;
    }

    // DIBUJA LOS CUADROS EN EL PANEL
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(Color.darkGray);
        // pintarTablero();
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (tablero[i][j] == 0) {
                    g.setColor(Color.darkGray);
                    // g.fillRect(i*CELL_SIZE, j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    if (actualGrid == 1 || actualGrid == 2) {
                        g.drawRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    }

                } else {
                    g.setColor(colorActual);
                    g.fillRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    g.setColor(Color.darkGray);
                    if (actualGrid == 1 || actualGrid == 3) {
                        g.drawRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    }
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (tablero[e.getX() / CELL_SIZE][e.getY() / CELL_SIZE] == 0) {
            tablero[e.getX() / CELL_SIZE][e.getY() / CELL_SIZE] = 1;
        } else {
            tablero[e.getX() / CELL_SIZE][e.getY() / CELL_SIZE] = 0;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}

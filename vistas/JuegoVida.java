package vistas;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.awt.Color;

public class JuegoVida extends JPanel implements MouseListener {
    // VARIABLES DEL PANEL
    private static final  int CELL_SIZE = 21;
    private static final int WIDTH = 900;
    private static final  int HEIGHT = 900;
    private static final int ROWS = WIDTH / CELL_SIZE;
    private static final int COLUMNS = HEIGHT / CELL_SIZE;
    // VARIABLES DE TABLERO
    private static final Color[] colorV = {Color.WHITE, Color.CYAN, Color.MAGENTA, Color.green };
    private  int colorIndex = 0;
    private  Color colorActual = colorV[0];

    // VARIABLES DE VISTA
    private static final int[] gridOptions = { 1, 2, 3, 4 };
    private  int gridIndex = 0;
    private  int actualGrid = 1;
    private  boolean pausa = false;
    private  int[][] board = new int[ROWS][COLUMNS];
   

    public JuegoVida() {
        this.setSize(WIDTH, HEIGHT);
        this.setBackground(Color.BLACK);
        this.addMouseListener(this);
    }

    public void pause() {
        pausa = !pausa;
    }

    // LLENA LA MATRIZ CON 1 Y 0 ALEATORIAMENTE
    public void randomBoard() {
        this.clearBoard();
        Random random = new Random();
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if(random.nextInt(7)==1)
                board[i][j] = 1;
            }
        }   
    }

    // ACTUALIZA EL TABLERO AL SIGUIENTE ESTADO
    public void refreshBoard() {
        if (pausa) {
            int[][] nuevoTablero = new int[ROWS][COLUMNS];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board.length; j++) {
                    if (neighborsCount(i, j) == 3) {
                        nuevoTablero[i][j] = 1;
                    } else if (board[i][j] == 1 && (neighborsCount(i, j) > 3 || neighborsCount(i, j) <= 1)) {
                        nuevoTablero[i][j] = 0;
                    } else if (board[i][j] == 1 && neighborsCount(i, j) == 2) {
                        nuevoTablero[i][j] = 1;
                    }
                }
            }
            board = nuevoTablero;
        }

    }

    //CUENTA LA CANTIDAD DE VECINOS VIVOS DE UNA DETERMINADA CELDA
    //EN UN TABLERO CUYOS EXTREMOS SON CONTIGUOS
    private int neighborsCount(int a, int b) {
        int total;
        total = board[(a + 1) % ROWS][(b + 1) % COLUMNS] +
                board[(a) % ROWS][(b + 1) % COLUMNS] +
                board[(a + 1) % ROWS][(b) % COLUMNS] +
                board[(ROWS + a - 1) % ROWS][(COLUMNS + b - 1) % COLUMNS] +
                board[(ROWS + a - 1) % ROWS][(b + 1) % COLUMNS] +
                board[(a + 1) % ROWS][(COLUMNS + b - 1) % COLUMNS] +
                board[(ROWS + a) % ROWS][(COLUMNS + b - 1) % COLUMNS] +
                board[(ROWS + a - 1) % ROWS][(COLUMNS + b) % COLUMNS];

        return total;
    }

    public void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }
    }

    //CAMBIA EL COLOR DE LAS CELDAS CON UN ARREGLO CIRCULAR
    public void switchColor() {
        colorActual = colorV[(colorIndex + 1) % colorV.length];
        colorIndex = colorIndex + 1;
    }

    //CAMBIA SI SE VE EL GRID O LOS BORDES DE LAS CELDAS CON UN ARREGLO CIRCULAR
    public void switchGrid() {
        actualGrid = gridOptions[(gridIndex + 1) % gridOptions.length];
        gridIndex = gridIndex + 1;
    }

    // DIBUJA LOS CUADROS EN EL PANEL
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (board[i][j] == 0) {
                    g.setColor(Color.darkGray);
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
        if (board[e.getX() / CELL_SIZE][e.getY() / CELL_SIZE] == 0) {
            board[e.getX() / CELL_SIZE][e.getY() / CELL_SIZE] = 1;
        } else {
            board[e.getX() / CELL_SIZE][e.getY() / CELL_SIZE] = 0;
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

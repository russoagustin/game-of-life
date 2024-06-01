package vistas;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.awt.Color;

public class JuegoVida extends JPanel implements MouseListener {
    // VARIABLES DEL PANEL
    private static  int CELL_SIZE = 21;
    private static final int WIDTH = 900;
    private static final int HEIGHT = 900;
    private static final int ROWS = WIDTH / CELL_SIZE;
    private static final int COLUMNS = HEIGHT / CELL_SIZE;
    // VARIABLES DE TABLERO
    private static final Color[] colorV = {Color.WHITE, Color.CYAN, Color.MAGENTA, Color.green };
    private static int colorIndex = 0;
    private static Color colorActual = colorV[0];

    // VARIABLES DE VISTA
    private static final int[] gridOptions = { 1, 2, 3, 4 };
    private static int gridIndex = 0;
    private static int actualGrid = 1;
    private static boolean pausa = false;
    private static int[][] board = new int[ROWS][COLUMNS];
    public static int z=CELL_SIZE;

    public JuegoVida() {
       // this.setSize(WIDTH, HEIGHT);
        //this.setBounds(-WIDTH/2,-HEIGHT/2, WIDTH, HEIGHT);
        this.setBounds(0,0, WIDTH, HEIGHT);
        // this.setBackground(new Color(56,216,252));
        this.setBackground(Color.BLACK);
       // this.setLocation(-4500, -4500);
        this.addMouseListener(this);

    }

    public void pause() {
        pausa = !pausa;
    }

    public void zoomIn(){
        this.setBounds(-WIDTH/2-CELL_SIZE/4,-HEIGHT/2-CELL_SIZE/4, WIDTH, HEIGHT);
        CELL_SIZE=CELL_SIZE+CELL_SIZE/4;
    }

    public void zoomOut(){
        this.setBounds((-WIDTH/2)+(z/WIDTH*4),(-HEIGHT/2)+(z/WIDTH*4), WIDTH, HEIGHT);
        CELL_SIZE=CELL_SIZE-1;
        z=z+10;
        System.out.println(ROWS);
    }

    // LLENA LA MATRIZ CON 1 Y 0 ALEATORIAMENTE
    public void randomBoard() {
        Random random = new Random();
        for (int e = 0; e < 3* ROWS; e++) {
            for (int i = 2* ROWS /CELL_SIZE; i <= random.nextInt(ROWS); i++) {
                for (int j = 2* ROWS /CELL_SIZE; j <= random.nextInt(COLUMNS); j++) {
                    board[i][j] = random.nextInt(2);
                }
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
    //EN UN TABLERO "TOROIDAL" (EXTREMOS CONTIGUOS)
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

        // System.out.println(total);
        return total;
    }

    public void clearBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = 0;
            }
        }
    }

    public void switchColor() {
        colorActual = colorV[(colorIndex + 1) % colorV.length];
        colorIndex = colorIndex + 1;
    }

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
                    //g.fillArc(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE,0,360);
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

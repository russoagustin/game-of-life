package vistas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame implements KeyListener{
    private static JuegoVida game = new JuegoVida();

    public VentanaPrincipal(int WIDTH, int HEIGHT){
        this.setTitle("Juego de la vida");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(true);
        this.setLocation(540, 100);
        this.setVisible(true);
        this.add(game);
        this.addKeyListener(this);
       
        while (true) {
            game.refreshBoard();
            try {
                // Pausa de 3 segundos
                Thread.sleep(100); // 3000 milisegundos = 3 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            game.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case ' ' :
                game.pause();
                break;
            case 'l':
                game.clearBoard();
                break;
            case 'a':
                game.randomBoard();
                break;
            case 'c':
                game.switchColor();
                break;
            case 'g':
                game.switchGrid();
                break;
            case 'z':
                game.zoomIn();
                break;
            case'<':
                game.zoomOut();
                break;
            default: //game.pause();
                break;
        }                
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //game.pause();
        System.out.println("presionado");
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // game.pause();
        System.out.println("presionado");
        
    }



}
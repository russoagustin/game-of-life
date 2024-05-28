package vistas;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame implements KeyListener{
    private static JuegoVida juego = new JuegoVida();

    public VentanaPrincipal(int WIDTH, int HEIGHT){
        this.setTitle("Juego de la vida");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(true);
        this.setLocation(540, 100);
        this.setVisible(true);
        this.add(juego);
        this.addKeyListener(this);
       
        while (true) {
            juego.actualizarTablero();
            try {
                // Pausa de 3 segundos
                Thread.sleep(100); // 3000 milisegundos = 3 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            juego.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case ' ' :
                juego.pausa();
                break;
            case 'l':
                juego.limpiarTablero();
                break;
            case 'a':
                juego.tableroAleatorio();
                break;
            case 'c':
                juego.cambiarColor();
                break;
            case 'g':
                juego.cambiarGrid();
                break;
            case 'z':
                juego.zoomIn();
                break;
            case'<':
                juego.zoomOut();
                break;
            default: juego.pausa();
                break;
        }                
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //juego.pausa();
        System.out.println("presionado");
       
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // juego.pausa();
        System.out.println("presionado");
        
    }



}
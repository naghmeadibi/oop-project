import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Atmospher-PC on 24/06/2021.
 */
public class MyGraphics extends JFrame implements MouseListener {
    JFrame frame;
    ImageIcon backGround;
    JLabel main;
    JPanel mainPanel;
    JLabel[][] key;
    Manager manager = new Manager();
    ImageIcon grass;

    
    
    public void run() {
        manager.setLogger();

        frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        grass = new ImageIcon("grass.png");





        mainPanel = new JPanel();
        mainPanel.setBackground(Color.red);
        mainPanel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mainPanel.setLayout(new BorderLayout());




        backGround = new ImageIcon("newBack.jpg");




        printKey();




        main = new JLabel();
        main.setIcon(backGround);
        main.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mainPanel.add(main);




        frame.setLayout(null);
        frame.add(mainPanel);
        frame.setVisible(true);
    }


    public void printKey() {
        key = new JLabel[6][6];
        for (int i = 0; i < 6 ; i++) {
            for (int j = 0; j < 6; j++) {
                key[i][j] = new JLabel();
                key[i][j].setBounds(i*100+350,j*90+140,100,90);
                mainPanel.add(key[i][j]);
                key[i][j].setOpaque(false);
                key[i][j].addMouseListener(this);


            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < 6 ; i++) {
            for (int j = 0; j < 6 ; j++) {
                if (e.getSource() == key[i][j]) {
                    int coordinate = 10 * (i+1) + j +1;
                    key[i][j].setIcon(grass);
                    key[i][j].setOpaque(false);
                    manager.plant(coordinate);
                    break;
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

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

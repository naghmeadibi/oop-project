import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Atmospher-PC on 24/06/2021.
 */
public class Graphics extends JFrame implements ActionListener {
    JFrame frame;
    ImageIcon backGround;
    JLabel main;
    JPanel mainPanel;
    JButton[][] key;
    Manager manager = new Manager();
    ImageIcon grass;
    
    
    public void run() {
        manager.setLogger();

        frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        ImageIcon image = new ImageIcon("logo.jpg");
        frame.setIconImage(image.getImage());
        grass = new ImageIcon("grass.png");



        mainPanel = new JPanel();
        mainPanel.setBackground(Color.red);
        mainPanel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mainPanel.setLayout(new BorderLayout());




        backGround = new ImageIcon("back.jpg");




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
        key = new JButton[6][6];
        for (int i = 0; i < 6 ; i++) {
            for (int j = 0; j < 6; j++) {
                key[i][j] = new JButton();
                key[i][j].setBounds(i*100+350,j*90+140,100,90);
                mainPanel.add(key[i][j]);
                key[i][j].setOpaque(false);
                key[i][j].setContentAreaFilled(false);
                key[i][j].setBorderPainted(false);
                key[i][j].addActionListener(this);


            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 6 ; i++) {
            for (int j = 0; j < 6 ; j++) {
                if (e.getSource() == key[i][j]) {
                    int coordinate = 10 * (i+1) + j +1;
                    key[i][j].setIcon(grass);
                    key[i][j].setOpaque(true);
                    key[i][j].setContentAreaFilled(true);
                    //key[i][j].setBorderPainted(true);
                    manager.plant(coordinate);
                    break;
                }
            }
        }
    }
}

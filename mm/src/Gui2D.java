import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Gui2D {
    public static void main(String[] args) {
        Gui2 gui = new Gui2();
        gui.go();
    }

    static class Gui2 extends JFrame {
        JFrame frame = new JFrame();
        MyDrawPanel drawpanel = new MyDrawPanel();


        public void go() {
            drawpanel.manager.setLogger();
            drawpanel.manager.menu(true);
            drawpanel.manager.readingLevels();
            drawpanel.manager.setCounter(0);
            drawpanel.manager.setBack();
            drawpanel.manager.coinSet();



            frame.getContentPane().add(drawpanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1370, 850);
            frame.setVisible(true);

        }

    }

    static class MyDrawPanel extends JComponent implements MouseListener {
        Manager manager = new Manager();


        public void paintComponent(Graphics g) {


            manager.check();


            Graphics2D g2D = (Graphics2D) g;
            g2D.drawImage(new ImageIcon("newBack.jpg").getImage(), 0, 0, null);
            g2D.drawImage(new ImageIcon("buyHen.jpg").getImage(),0,0,80,80,null);



            this.addMouseListener(this);
            for (int i = 0; i < manager.grasses.size(); i++) {
                int x = manager.grasses.get(i).coordinate % 10;
                int y = manager.grasses.get(i).coordinate / 10;
                g2D.drawImage(new ImageIcon("grass.png").getImage(), 250 + 100 * x, 50 + 90 * y, 100, 90, null);
            }

            for (int i = 0; i < manager.farmanimals.farmanimalss.size(); i++) {
                int x = manager.farmanimals.farmanimalss.get(i).coordinate % 10;
                int y = manager.farmanimals.farmanimalss.get(i).coordinate / 10;
                if (manager.farmanimals.farmanimalss.get(i) instanceof Hen)
                    g2D.drawImage(new ImageIcon("duck.png").getImage(), 250 + 100 * x, 50 + 90 * y, 90, 70, null);

            }

            for (int i = 0; i < manager.products.allUnPickedupedProducts.size() ; i++) {
                int x = manager.products.allUnPickedupedProducts.get(i).coordinate % 10;
                int y = manager.products.allUnPickedupedProducts.get(i).coordinate / 10;
              if (manager.products.allUnPickedupedProducts.get(i) instanceof Egg) {
                  g2D.drawImage(new ImageIcon("egg.png").getImage(), 290 + 100 * x, 80 + 90 * y, 20, 30, null);
              }
            }


            for (int i = 0; i < manager.wildAnimals.livingWildAnimals.size() ; i++) {
                int x =  manager.wildAnimals.livingWildAnimals.get(i).getCoordinate() % 10;
                int y =  manager.wildAnimals.livingWildAnimals.get(i).getCoordinate() / 10;
                if (manager.wildAnimals.livingWildAnimals.get(i) instanceof Lion) {
                    g2D.drawImage(new ImageIcon("lion.png").getImage(), 250 + 100 * x, 50 + 90 * y, 150, 120, null);
                }
            }

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if ((e.getX() >= 350 + i * 100 && e.getX() <= 450 + i * 100 && e.getY() >= 140 + j * 90 && e.getY() <= 230 + j * 90)) {

                        int tr = manager.pickUp(j * 10 + 10 + i + 1);

                        if (tr == 0) {
                            int tr2 = manager.cage(j * 10 + 10 + i + 1);
                            if (tr2 == 0) {
                                if (!manager.checkWell()) {
                                    manager.plant(j * 10 + 10 + i + 1);
                                } else {
                                    JOptionPane.showMessageDialog(null, "Well is empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }

                        this.removeMouseListener(this);
                        this.invalidate();
                        this.validate();
                        this.repaint();

                        return;
                    }
                }
            }
            if(e.getX() >= 0 && e.getX() <= 80 && e.getY() >= 0 && e.getY() <= 80) {
                manager.addHen();
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            }

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

    }

}

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
            g2D.drawImage(new ImageIcon("buyHen.jpg").getImage(), 0, 0, 60, 60, null);
            g2D.drawImage(new ImageIcon("buyTurkey.jpg").getImage(), 0, 60, 60, 60, null);
            g2D.drawImage(new ImageIcon("buyBuffalo.jpg").getImage(), 0, 120, 60, 60, null);
            g2D.drawImage(new ImageIcon("well.png").getImage(),600,30,100,150,null);
            g2D.drawImage(new ImageIcon("mill.png").getImage(),0,180,60,60,null);


            this.addMouseListener(this);


            printGrass(g2D);
            printLivingWildAnimals(g2D);
            printFarmAnimals(g2D);
            printUnpickedProduct(g2D);
            printCagedWildAnimals(g2D);
            printFactory(g2D);

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
            if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 0 && e.getY() <= 60) {
                manager.addHen();
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            }
            else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 60 && e.getY() <= 120) {
                manager.addTurkey();
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            }
            else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 120 && e.getY() <= 180) {
                manager.addBuffalo();
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            }else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 180 && e.getY() <= 240) {
                manager.processBuild("mill");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 200 && e.getX() <= 380 && e.getY() >= 60 && e.getY() <= 240) {
                manager.workFactory("mill");
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

        public void printUnpickedProduct(Graphics2D g2D) {
            for (int i = 0; i < manager.products.allUnPickedupedProducts.size() ; i++) {
                int x = manager.products.allUnPickedupedProducts.get(i).coordinate % 10;
                int y = manager.products.allUnPickedupedProducts.get(i).coordinate / 10;
                if (manager.products.allUnPickedupedProducts.get(i) instanceof Egg) {
                    g2D.drawImage(new ImageIcon("egg.png").getImage(), 290 + 100 * x, 80 + 90 * y, 30, 40, null);
                } else if (manager.products.allUnPickedupedProducts.get(i) instanceof Milk) {
                    g2D.drawImage(new ImageIcon("milk.png").getImage(), 290 + 100 * x, 80 + 90 * y, 50, 50, null);
                } else if (manager.products.allUnPickedupedProducts.get(i) instanceof Feather) {
                    g2D.drawImage(new ImageIcon("feather.png").getImage(), 275 + 100 * x, 65 + 90 * y, 70, 70, null);
                } else if (manager.products.allUnPickedupedProducts.get(i) instanceof Powder) {
                    g2D.drawImage(new ImageIcon("powder.png").getImage(), 270 + 100 * x, 60 + 90 * y, 80, 80, null);
                } else if (manager.products.allUnPickedupedProducts.get(i) instanceof PackagedMilk) {
                    g2D.drawImage(new ImageIcon("packagedMilk.png").getImage(), 290 + 100 * x, 80 + 90 * y, 40, 50, null);
                } else if (manager.products.allUnPickedupedProducts.get(i) instanceof Cloth) {
                    g2D.drawImage(new ImageIcon("thread.png").getImage(), 260 + 100 * x, 60 + 90 * y, 60, 60, null);
                } else if (manager.products.allUnPickedupedProducts.get(i) instanceof Bread) {
                    g2D.drawImage(new ImageIcon("bread.png").getImage(), 270 + 100 * x, 70 + 90 * y, 60, 50, null);
                } else if (manager.products.allUnPickedupedProducts.get(i) instanceof IceCream) {
                    g2D.drawImage(new ImageIcon("iceCream.png").getImage(), 265 + 100 * x, 60 + 90 * y, 60, 60, null);
                } else if (manager.products.allUnPickedupedProducts.get(i) instanceof Shirt) {
                    g2D.drawImage(new ImageIcon("shirt.png").getImage(), 265 + 100 * x, 65 + 90 * y, 70, 70, null);
                }
            }
        }

        public void printFarmAnimals(Graphics2D g2D) {

            for (int i = 0; i < manager.farmanimals.farmanimalss.size(); i++) {
                int x = manager.farmanimals.farmanimalss.get(i).coordinate % 10;
                int y = manager.farmanimals.farmanimalss.get(i).coordinate / 10;
                if (manager.farmanimals.farmanimalss.get(i) instanceof Hen)
                    g2D.drawImage(new ImageIcon("duck.png").getImage(), 250 + 100 * x, 50 + 90 * y, 90, 70, null);
                if (manager.farmanimals.farmanimalss.get(i) instanceof Turkey)
                    g2D.drawImage(new ImageIcon("turkey.png").getImage(), 250 + 100 * x, 50 + 90 * y, 90, 70, null);
                if (manager.farmanimals.farmanimalss.get(i) instanceof Buffalo)
                    g2D.drawImage(new ImageIcon("buffalo.png").getImage(), 250 + 100 * x, 50 + 90 * y, 90, 70, null);

            }
        }

        public void printLivingWildAnimals(Graphics2D g2D) {
            for (int i = 0; i < manager.wildAnimals.livingWildAnimals.size(); i++) {
                int x = manager.wildAnimals.livingWildAnimals.get(i).getCoordinate() % 10;
                int y = manager.wildAnimals.livingWildAnimals.get(i).getCoordinate() / 10;
                if (manager.wildAnimals.livingWildAnimals.get(i) instanceof Lion) {
                    g2D.drawImage(new ImageIcon("lion.png").getImage(), 250 + 100 * x, 50 + 90 * y, 100, 90, null);
                    if (manager.wildAnimals.livingWildAnimals.get(i).life <= 2)
                        g2D.drawImage(new ImageIcon("verticalStick.png").getImage(), 260 + 100 * x, 50 + 90 * y, 20, 90, null);
                    if (manager.wildAnimals.livingWildAnimals.get(i).life <= 1)
                        g2D.drawImage(new ImageIcon("verticalStick.png").getImage(), 300 + 100 * x, 50 + 90 * y, 20, 90, null);
                }
                if (manager.wildAnimals.livingWildAnimals.get(i) instanceof Bear) {
                    g2D.drawImage(new ImageIcon("bear.png").getImage(), 250 + 100 * x, 50 + 90 * y, 100, 90, null);
                    if (manager.wildAnimals.livingWildAnimals.get(i).life <= 3)
                        g2D.drawImage(new ImageIcon("verticalStick.png").getImage(), 260 + 100 * x, 50 + 90 * y, 20, 90, null);
                    if (manager.wildAnimals.livingWildAnimals.get(i).life <= 2)
                        g2D.drawImage(new ImageIcon("verticalStick.png").getImage(), 300 + 100 * x, 50 + 90 * y, 20, 90, null);
                    if (manager.wildAnimals.livingWildAnimals.get(i).life <= 1)
                        g2D.drawImage(new ImageIcon("horizentalStick.png").getImage(), 250 + 100 * x, 70 + 90 * y, 100, 50, null);
                }
                if (manager.wildAnimals.livingWildAnimals.get(i) instanceof Tiger) {
                    g2D.drawImage(new ImageIcon("tiger.png").getImage(), 250 + 100 * x, 50 + 90 * y, 100, 90, null);
                    if (manager.wildAnimals.livingWildAnimals.get(i).life <= 3)
                        g2D.drawImage(new ImageIcon("verticalStick.png").getImage(), 260 + 100 * x, 50 + 90 * y, 20, 90, null);
                    if (manager.wildAnimals.livingWildAnimals.get(i).life <= 2)
                        g2D.drawImage(new ImageIcon("verticalStick.png").getImage(), 300 + 100 * x, 50 + 90 * y, 20, 90, null);
                    if (manager.wildAnimals.livingWildAnimals.get(i).life <= 1)
                        g2D.drawImage(new ImageIcon("horizentalStick.png").getImage(), 250 + 100 * x, 70 + 90 * y, 100, 50, null);
                }
            }
        }

        public void printCagedWildAnimals(Graphics2D g2D){
            for (int i = 0; i < manager.wildAnimals.cagedWildAnimals.size(); i++) {
                int x = manager.wildAnimals.cagedWildAnimals.get(i).getCoordinate() % 10;
                int y = manager.wildAnimals.cagedWildAnimals.get(i).getCoordinate() / 10;
                if (manager.wildAnimals.cagedWildAnimals.get(i) instanceof Lion) {
                    g2D.drawImage(new ImageIcon("sadLion.png").getImage(), 250 + 100 * x, 50 + 90 * y, 100, 90, null);
                    g2D.drawImage(new ImageIcon("cage.png").getImage(), 250 + 100 * x, 50 + 90 * y, 100, 90, null);
                }
                if (manager.wildAnimals.cagedWildAnimals.get(i) instanceof Bear) {
                    g2D.drawImage(new ImageIcon("sadBear.png").getImage(), 250 + 100 * x, 50 + 90 * y, 100, 90, null);
                    g2D.drawImage(new ImageIcon("cage.png").getImage(), 250 + 100 * x, 50 + 90 * y, 100, 90, null);
                }
                if (manager.wildAnimals.cagedWildAnimals.get(i) instanceof Tiger) {
                    g2D.drawImage(new ImageIcon("sadTiger.png").getImage(), 250 + 100 * x, 50 + 90 * y, 100, 90, null);
                    g2D.drawImage(new ImageIcon("cage.png").getImage(), 250 + 100 * x, 50 + 90 * y, 100, 90, null);
                }
            }
        }

        public void printFactory(Graphics2D g2D){
            for (int i = 0; i < manager.factory.factories.size() ; i++) {
                if (manager.factory.factories.get(i) instanceof Mill){
                    g2D.drawImage(new ImageIcon("mill.png").getImage(),200,60,180,180,null);
                }
            }

        }

        public void printGrass(Graphics2D g2D) {
            for (int i = 0; i < manager.grasses.size(); i++) {
                int x = manager.grasses.get(i).coordinate % 10;
                int y = manager.grasses.get(i).coordinate / 10;
                g2D.drawImage(new ImageIcon("grass.png").getImage(), 250 + 100 * x, 50 + 90 * y, 100, 90, null);
            }
        }

    }

}

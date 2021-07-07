import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.text.AttributedString;
import java.util.Arrays;
import java.util.Random;

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
            g2D.drawImage(new ImageIcon("well.png").getImage(), 600, 30, 100, 150, null);
            g2D.drawImage(new ImageIcon("mill.png").getImage(), 0, 180, 60, 60, null);
            g2D.drawImage(new ImageIcon("wareHouse.png").getImage(), 1090, 570, 210, 200, null);
            g2D.drawImage(new ImageIcon("truck.png").getImage(), 60, 610, 210, 180, null);
            g2D.drawImage(new ImageIcon("go.png").getImage(), 0, 670, 60, 60, null);
            g2D.drawImage(new ImageIcon("coin.png").getImage(),1300,20,50,50,null);
            if (manager.orders.contains("truckGo"))
                g2D.drawImage(new ImageIcon("truckIsMoving.png").getImage(), 135, 670, 60, 60, null);
            this.addMouseListener(this);


            printGrass(g2D);
            printLivingWildAnimals(g2D);
            printFarmAnimals(g2D);
            printUnpickedProduct(g2D);
            printCagedWildAnimals(g2D);
            printFactory(g2D);
            printPickUp(g2D);
            printOnTruck(g2D);
            Turn(manager.timeCounter,g2D);

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
            } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 60 && e.getY() <= 120) {
                manager.addTurkey();
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 120 && e.getY() <= 180) {
                manager.addBuffalo();
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 180 && e.getY() <= 240) {
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
            } else if (e.getX() >= 60 && e.getX() <= 270 && e.getY() >= 610 && e.getY() <= 790) {
                if (!manager.orders.contains("truckGo"))
                    truckClicked();
                return;
            } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 670 && e.getY() <= 730) {
                processTruckGo();
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
            for (int i = 0; i < manager.products.allUnPickedupedProducts.size(); i++) {
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

        public void printCagedWildAnimals(Graphics2D g2D) {
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

        public void printFactory(Graphics2D g2D) {
            for (int i = 0; i < manager.factory.factories.size(); i++) {
                if (manager.factory.factories.get(i) instanceof Mill) {
                    g2D.drawImage(new ImageIcon("mill.png").getImage(), 200, 60, 180, 180, null);
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

        public void printPickUp(Graphics2D g2D) {
            int cnt = 0, cntt = 0;
            for (int i = 0; i < manager.wildAnimals.storedWildAnimals.size(); i++) {
                for (int j = 0; j < 15; j++) {
                    g2D.drawImage(new ImageIcon(manager.wildAnimals.storedWildAnimals.get(i).sadImage).getImage(), (j / 5) * 25 + 1125 + i * 75, (j % 5) * 20 + 650, 20, 25, null);
                }
                cnt++;
            }
            for (int j = 0; j < manager.store.stuff.size(); j++) {
                for (int k = 0; k < manager.store.stuff.get(j).capacity; k++) {
                    g2D.drawImage(new ImageIcon(manager.store.stuff.get(j).imageAddress).getImage(), 1125 + cnt * 75 + (cntt / 5) * 25, (cntt % 5) * 20 + 650, 20, 25, null);
                }
                cntt += manager.store.stuff.get(j).capacity;
            }
        }

        public void truckClicked() {
            Random random = new Random();
            JFrame jFrame = new JFrame("truck load");
            jFrame.setLayout(null);
            jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            jFrame.setSize(1000, 800);
            jFrame.getContentPane().setBackground(new Color(144, 198, 149));
            jFrame.setVisible(true);
            JPanel panel = new JPanel();
            panel.setBackground(Color.WHITE);
            panel.setBounds(550, 50, 400, 700);
            jFrame.add(panel);
            JPanel panel1 = new JPanel();
            panel1.setBounds(5, 50, 400, 700);
            panel1.setBackground(new Color(144, 198, 149));
            jFrame.add(panel1);
            JButton ok = new JButton("OK");
            ok.setBounds(470, 730, 60, 30);
            ok.addActionListener(e -> {
                jFrame.dispose();
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
            });
            jFrame.add(ok);
            printTruckLoad(jFrame, random, panel, panel1);
            printTruckUnload(jFrame, random, panel, panel1);

        }

        public void printTruckLoad(JFrame jFrame, Random random, JPanel panel, JPanel panel1) {
            int size = manager.store.stuff.size() + manager.wildAnimals.storedWildAnimals.size() + manager.farmanimals.farmanimalss.size();
            JButton[] labels = new JButton[size];
            for (int i = 0; i < size; i++) {
                if (i < manager.store.stuff.size()) {
                    ImageIcon imageIcon = new ImageIcon(manager.store.stuff.get(i).imageAddress);
                    Image image = imageIcon.getImage();
                    Image newIm = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newIm);
                    labels[i] = new JButton(imageIcon);
                    labels[i].setBounds((i % 5) * 60 + 20, (i / 5) * 60 + 20, 50, 50);
                    labels[i].setBackground(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
                    int index = i;
                    labels[i].addActionListener(e -> {
                        if (manager.truckLoad(manager.store.stuff.get(index).name)) {
                            panel1.removeAll();
                            printTruckLoad(jFrame, random, panel, panel1);
                        }
                        panel.removeAll();
                        printTruckUnload(jFrame, random, panel, panel1);
                        panel.repaint();
                        jFrame.invalidate();
                        jFrame.validate();
                        jFrame.repaint();
                    });
                    panel1.add(labels[i]);
                } else if (i - manager.store.stuff.size() < manager.wildAnimals.storedWildAnimals.size()) {
                    ImageIcon imageIcon = new ImageIcon(manager.wildAnimals.storedWildAnimals.get(i - manager.store.stuff.size()).sadImage);
                    Image image = imageIcon.getImage();
                    Image newIm = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newIm);
                    labels[i] = new JButton(imageIcon);
                    labels[i].setBounds((i % 5) * 60 + 20, (i / 5) * 60 + 20, 50, 50);
                    labels[i].setBackground(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
                    int index1 = i - manager.store.stuff.size();
                    int in = i;
                    labels[i].addActionListener(e -> {
                        if (manager.truckLoad(manager.wildAnimals.storedWildAnimals.get(index1).name)) {
                            panel1.removeAll();
                            printTruckLoad(jFrame, random, panel, panel1);
                        }
                        panel.removeAll();
                        printTruckUnload(jFrame, random, panel, panel1);
                        panel.repaint();
                        jFrame.invalidate();
                        jFrame.validate();
                        jFrame.repaint();
                    });
                    panel1.add(labels[i]);
                } else if (i - (manager.store.stuff.size() + manager.wildAnimals.storedWildAnimals.size()) < manager.farmanimals.farmanimalss.size()) {
                    ImageIcon imageIcon = new ImageIcon(manager.farmanimals.farmanimalss.get(i - (manager.store.stuff.size() + manager.wildAnimals.storedWildAnimals.size())).image);
                    Image image = imageIcon.getImage();
                    Image newIm = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newIm);
                    labels[i] = new JButton(imageIcon);
                    labels[i].setBounds((i % 5) * 60 + 20, (i / 5) * 60 + 20, 50, 50);
                    labels[i].setBackground(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
                    int index2 = i - (manager.store.stuff.size() + manager.wildAnimals.storedWildAnimals.size());
                    int labInd = i;
                    labels[i].addActionListener(e -> {
                        if (manager.truckLoad(manager.farmanimals.farmanimalss.get(index2).name)) {
                            panel1.removeAll();
                            printTruckLoad(jFrame, random, panel, panel1);
                        }
                        panel.removeAll();
                        printTruckUnload(jFrame, random, panel, panel1);
                        panel.repaint();
                        jFrame.invalidate();
                        jFrame.validate();
                        jFrame.repaint();
                    });
                    panel1.add(labels[i]);
                }
            }
        }

        public void printTruckUnload(JFrame jFrame, Random random, JPanel panel, JPanel panel1) {
            int size1 = manager.truck.farmAnimalsToSell.size() + manager.truck.wildAnimalsToSell.size() + manager.truck.stuffToSell.size();
            JButton[] buttons = new JButton[size1];
            for (int i = 0; i < size1; i++) {
                if (i < manager.truck.farmAnimalsToSell.size()) {
                    ImageIcon imageIcon = new ImageIcon(manager.truck.farmAnimalsToSell.get(i).image);
                    Image image = imageIcon.getImage();
                    Image newIm = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newIm);
                    buttons[i] = new JButton(imageIcon);
                    buttons[i].setBounds((i % 5) * 60 + 20, (i / 5) * 60 + 20, 50, 50);
                    buttons[i].setBackground(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
                    int index = i;
                    buttons[i].addActionListener(e -> {
                        if (manager.truckUnload(manager.truck.farmAnimalsToSell.get(index).name)) {
                            panel.removeAll();
                            printTruckUnload(jFrame, random, panel, panel1);
                        }
                        panel1.removeAll();
                        printTruckLoad(jFrame, random, panel, panel1);
                        panel.repaint();
                        jFrame.invalidate();
                        jFrame.validate();
                        jFrame.repaint();
                    });
                    panel.add(buttons[i]);
                } else if (i - manager.truck.farmAnimalsToSell.size() < manager.truck.wildAnimalsToSell.size()) {
                    ImageIcon imageIcon = new ImageIcon(manager.truck.wildAnimalsToSell.get(i - manager.truck.farmAnimalsToSell.size()).image);
                    Image image = imageIcon.getImage();
                    Image newIm = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newIm);
                    buttons[i] = new JButton(imageIcon);
                    buttons[i].setBounds((i % 5) * 60 + 20, (i / 5) * 60 + 20, 50, 50);
                    buttons[i].setBackground(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
                    int index1 = i - manager.truck.farmAnimalsToSell.size();
                    buttons[i].addActionListener(e -> {
                        if (manager.truckUnload(manager.truck.wildAnimalsToSell.get(index1).name)) {
                            panel.removeAll();
                            printTruckUnload(jFrame, random, panel, panel1);
                        }
                        panel1.removeAll();
                        printTruckLoad(jFrame, random, panel, panel1);
                        panel.repaint();
                        jFrame.invalidate();
                        jFrame.validate();
                        jFrame.repaint();
                    });
                    panel.add(buttons[i]);
                } else if (i - (manager.truck.farmAnimalsToSell.size() + manager.truck.wildAnimalsToSell.size()) < manager.truck.stuffToSell.size()) {
                    ImageIcon imageIcon = new ImageIcon(manager.truck.stuffToSell.get(i - (manager.truck.farmAnimalsToSell.size() + manager.truck.wildAnimalsToSell.size())).imageAddress);
                    Image image = imageIcon.getImage();
                    Image newIm = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    imageIcon = new ImageIcon(newIm);
                    buttons[i] = new JButton(imageIcon);
                    buttons[i].setBounds((i % 5) * 60 + 20, (i / 5) * 60 + 20, 50, 50);
                    buttons[i].setBackground(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
                    int index2 = i - (manager.truck.farmAnimalsToSell.size() + manager.truck.wildAnimalsToSell.size());
                    buttons[i].addActionListener(e -> {
                        if (manager.truckUnload(manager.truck.stuffToSell.get(index2).name)) {
                            panel.removeAll();
                            printTruckUnload(jFrame, random, panel, panel1);
                        }
                        panel1.removeAll();
                        printTruckLoad(jFrame, random, panel, panel1);
                        panel.repaint();
                        jFrame.invalidate();
                        jFrame.validate();
                        jFrame.repaint();
                    });
                    panel.add(buttons[i]);
                }
            }
        }

        public void printOnTruck(Graphics2D g2D) {
            int cnt = 0, cntt = 0;
            for (int i = 0; i < manager.truck.wildAnimalsToSell.size(); i++) {
                for (int j = 0; j < 15; j++) {
                    g2D.drawImage(new ImageIcon(manager.truck.wildAnimalsToSell.get(i).sadImage).getImage(), 135 + (j % 5) * 25, 620 + (j / 5) * 25, 20, 20, null);
                }
            }
            for (int i = 0; i < manager.truck.farmAnimalsToSell.size(); i++) {
                g2D.drawImage(new ImageIcon(manager.truck.farmAnimalsToSell.get(i).image).getImage(), 135 + (i % 5) * 25, 620 + (i / 5) * 25, 20, 20, null);
                cnt++;
            }
            for (int i = 0; i < manager.truck.stuffToSell.size(); i++) {
                for (int j = 0; j < manager.truck.stuffToSell.get(i).capacity; j++) {
                    int x = 135 + ((j + cnt + cntt) % 5) * 25;
                    int y = 620 + ((j + cnt + cntt) / 5) * 25;
                    g2D.drawImage(new ImageIcon(manager.truck.stuffToSell.get(i).imageAddress).getImage(), x, y, 20, 20, null);
                }
                cntt += manager.truck.stuffToSell.get(i).capacity;
            }
        }

        public void processTruckGo() {
            if (!manager.orders.contains("truckGo")) {
                manager.checkTruckGo();
                manager.orders.add("truckGo");
                manager.orders.add("11");
            } else if (manager.orders.contains("truckGo")) {
                JOptionPane.showMessageDialog(null, "truck is moving", "ERROR", JOptionPane.ERROR_MESSAGE);
                manager.logger.warning("Truck is moving");
            }
        }

        public void Turn(int timeCounter, Graphics2D g2D) {
            Font font = new Font("Courier New",Font.BOLD,18);
            String time = timeCounter + " units of time passed :)";
            AttributedString as = new AttributedString(time);
            as.addAttribute(TextAttribute.FONT,font);
            as.addAttribute(TextAttribute.FOREGROUND,Color.PINK);
            g2D.drawString(as.getIterator(), 1000, 20);
            Font fontCoin = new Font("Courier New",Font.BOLD,15);
            String coin = manager.coin+"$";
            AttributedString as1 = new AttributedString(coin);
            as1.addAttribute(TextAttribute.FONT,fontCoin);
            as1.addAttribute(TextAttribute.FOREGROUND,new Color(0x800919));
            g2D.drawString(as1.getIterator(), 1300, 48);




            System.out.println("Remaining tasks : ");
            manager.printTask();
        }

    }

}

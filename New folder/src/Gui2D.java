import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.text.AttributedString;
import java.util.Arrays;
import java.util.Random;
import javax.swing.*;
import javax.sound.sampled.*;

public class Gui2D {
    public static void main(String[] args) {
        Manager manager = new Manager();
        Menu menu = new Menu(manager);
        manager.setMusic();
        menu.menu();
    }

    static class Gui2 extends JFrame {
        JFrame frame = new JFrame();
        MyDrawPanel drawpanel;
        MainThread mainThread;
        TurnThread turnThread;

        public Gui2(MyDrawPanel drawpanel) {
            this.drawpanel = drawpanel;
        }


        public void go(Manager manager) {
            manager.playMusic(manager.clip);
            mainThread = new MainThread(manager, drawpanel);
            turnThread = new TurnThread(manager, drawpanel);


            turnThread.start();
            mainThread.start();

        }

        public void goOn(Manager manager) {
            drawpanel.manager.readingLevels();
            drawpanel.manager.setCounter(0);
            drawpanel.manager.setBack();
            drawpanel.manager.coinSet();


            frame.getContentPane().add(drawpanel);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setSize(1370, 850);
            frame.setVisible(true);
            drawpanel.setjFrame(frame);

        }

    }

    static class MyDrawPanel extends JComponent implements MouseListener {
        Manager manager;
        JFrame jFrame1;
        boolean clickPose = false;
        Graphics2D g2D;
        boolean exit = false;
        static File file = new File("error.wav");
        static Clip clip = null;
        int[][] iconSize = new int[11][2];



        public void setjFrame(JFrame jFrame) {
            this.jFrame1 = jFrame;
        }

        public MyDrawPanel(Manager manager) {
            this.manager = manager;
            for (int i = 0; i < 11; i++) {
                Arrays.fill(this.iconSize[i],60);
            }
        }

        public void paintComponent(Graphics g) {
            g2D = (Graphics2D) g;
            if (!clickPose) {
                if (!checkFinishLevel()) {


                    printIcons(g2D);

                    if (this.getMouseListeners().length == 0)
                        this.addMouseListener(this);


                    printGrass(g2D);
                    printLivingWildAnimals(g2D);
                    printFarmAnimals(g2D);
                    printUnpickedProduct(g2D);
                    printCagedWildAnimals(g2D);
                    printFactory(g2D);
                    printWorkingFactory(g2D);
                    printPickUp(g2D);
                    printOnTruck(g2D);

                    Turn(manager.timeCounter, g2D);
                } else {

                    printFinishLevel(g2D);
                    this.removeMouseListener(this);
                    this.addMouseListener(this);
                }
            } else {
                pose(g2D);
            }

        }

        @Override
        public void mouseClicked(MouseEvent e) {
           if (!checkFinishLevel()) {
            if (!clickPose) {
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 6; j++) {
                        if ((e.getX() >= 350 + i * 100 && e.getX() <= 450 + i * 100 && e.getY() >= 140 + j * 90 && e.getY() <= 230 + j * 90)) {

                            int tr = manager.pickUp(j * 10 + 10 + i + 1);

                            if (tr == 0) {
                                int tr2 = manager.cage(j * 10 + 10 + i + 1);
                                manager.unCage();
                            /*    if (manager.wildAnimals.livingWildAnimals.get(0).getLife() == 1) {
                                      manager.unCage();
                                }*/
                                if (tr2 == 0) {
                                    if (!manager.checkWell()) {
                                        manager.plant(j * 10 + 10 + i + 1);
                                    } else {
                                        playErrorMusic();
                                     //   JOptionPane.showMessageDialog(null, "Well is empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }

                           // this.removeMouseListener(this);
                            this.invalidate();
                            this.validate();
                            this.repaint();

                            return;
                        }
                    }
                }
                if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 0 && e.getY() <= 60) {
                    manager.addHen();
                   // this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();

                } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 60 && e.getY() <= 120) {
                    manager.addTurkey();
                   // this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();

                } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 120 && e.getY() <= 180) {
                    manager.addBuffalo();
                   // this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 190 && e.getX() <= 220 && e.getY() >= 210 && e.getY() <= 240) {
                    manager.processUpgrade("mill");
                   // this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 1130 && e.getX() <= 1160 && e.getY() >= 220 && e.getY() <= 250) {
                    manager.processUpgrade("bakery");
                  //  this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 170 && e.getX() <= 200 && e.getY() >= 360 && e.getY() <= 390) {
                    manager.processUpgrade("milkPackaging");
                   // this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 1150 && e.getX() <= 1180 && e.getY() >= 380 && e.getY() <= 410) {
                    manager.processUpgrade("iceCreamShop");
                 //   this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 160 && e.getX() <= 190 && e.getY() >= 590 && e.getY() <= 620) {
                    manager.processUpgrade("sewing");
                    this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 1150 && e.getX() <= 1180 && e.getY() >= 580 && e.getY() <= 610) {
                    manager.processUpgrade("weaving");
                    this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 180 && e.getY() <= 240) {
                    manager.processBuild("mill");
                 //   this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 200 && e.getX() <= 380 && e.getY() >= 60 && e.getY() <= 240) {
                    manager.workFactory("mill");
                 //   this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 180 && e.getY() <= 240) {
                    manager.processBuild("mill");
               //     this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 200 && e.getX() <= 380 && e.getY() >= 60 && e.getY() <= 240) {
                    manager.workFactory("mill");
                  //  this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 240 && e.getY() <= 300) {
                    manager.processBuild("bakery");
                  //  this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 900 && e.getX() <= 1080 && e.getY() >= 80 && e.getY() <= 260) {
                    manager.workFactory("bakery");
                  //  this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 300 && e.getY() <= 360) {
                    manager.processBuild("milkPackaging");
                 //   this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 180 && e.getX() <= 400 && e.getY() >= 250 && e.getY() <= 450) {
                    manager.workFactory("milkPackaging");
                    this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 360 && e.getY() <= 420) {
                    manager.processBuild("iceCreamShop");
                 //   this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 970 && e.getX() <= 1150 && e.getY() >= 261 && e.getY() <= 430) {
                    manager.workFactory("iceCreamShop");
                //    this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 420 && e.getY() <= 480) {
                    manager.processBuild("sewing");
                //    this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 180 && e.getX() <= 360 && e.getY() >= 451 && e.getY() <= 630) {
                    manager.workFactory("sewing");
               //     this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 480 && e.getY() <= 540) {
                    manager.processBuild("weaving");
                //    this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 970 && e.getX() <= 1150 && e.getY() >= 450 && e.getY() <= 630) {
                    manager.workFactory("weaving");
               //     this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 520 && e.getX() <= 560 && e.getY() >= 90 && e.getY() <= 130) {
                    manager.processBuild("makeHen");
               //     this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 500 && e.getX() <= 680 && e.getY() >= 20 && e.getY() <= 180) {
                    manager.workFactory("makeHen");
              //      this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 750 && e.getX() <= 850 && e.getY() >= 30 && e.getY() <= 180) {
                    manager.processWell();
                   // this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 60 && e.getX() <= 270 && e.getY() >= 610 && e.getY() <= 790) {
                    if (!manager.orders.contains("truckGo")) {
                        this.removeMouseListener(this);
                        truckClicked();
                    }
                    return;
                } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 670 && e.getY() <= 730) {
                    processTruckGo();
                 //   this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 1290 && e.getX() <= 1360 && e.getY() >= 450 && e.getY() <= 520) {
                    processTime();
                  //  this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 1290 && e.getX() <= 1360 && e.getY() >= 550 && e.getY() <= 620) {
                    clickPose = true;
                    this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 540 && e.getY() <= 600) {
                    manager.addDog();
                  //  this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 600 && e.getY() <= 660) {
                    manager.addCat();
                 //   this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                }
            } else {
                if (e.getX() >= 400 && e.getX() <= 900 && e.getY() >= 300 && e.getY() <= 400) {

                    exit = false;
                    Manager.logger.info("game continued");
                    clickPose = false;
                    this.removeMouseListener(this);
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    return;
                } else if (e.getX() >= 400 && e.getX() <= 900 && e.getY() >= 500 && e.getY() <= 600) {
                    this.removeMouseListener(this);
                    end();
                    return;
                }
            } } else {
               if (e.getX() >= 70 && e.getX() <= 190 && e.getY() >= 20 && e.getY() <= 120) {
                   end();
               }
           }


        }

        @Override
        public void mouseEntered(MouseEvent e) {



        }

        @Override
        public void mouseExited(MouseEvent e) {


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

            for (int i = 0; i < manager.dogs.size(); i++) {
                int x = manager.dogs.get(i).coordinate % 10;
                int y = manager.dogs.get(i).coordinate / 10;
                g2D.drawImage(new ImageIcon("dog.png").getImage(), 250 + 100 * x, 50 + 90 * y, 90, 70, null);
            }

            for (int i = 0; i < manager.cats.size(); i++) {
                int x = manager.cats.get(i).coordinate % 10;
                int y = manager.cats.get(i).coordinate / 10;
                g2D.drawImage(new ImageIcon("cat.png").getImage(), 250 + 100 * x, 50 + 90 * y, 90, 70, null);
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
                    cntt++;
                }

            }
        }

        public void truckClicked() {
            this.removeMouseListener(this);
            exit = true;
            Manager.logger.info("game posed");
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

                exit = false;
                Manager.logger.info("game continued");
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
                    //labels[i].setBounds((i % 5) * 60 + 20, (i / 5) * 60 + 20, 50, 50);
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
                    //labels[i].setBounds((i % 5) * 60 + 20, (i / 5) * 60 + 20, 50, 50);
                    labels[i].setBackground(new Color(random.nextFloat(), random.nextFloat(), random.nextFloat()));
                    int index1 = i - manager.store.stuff.size();
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
                    //labels[i].setBounds((i % 5) * 60 + 20, (i / 5) * 60 + 20, 50, 50);
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
                   // buttons[i].setBounds((i % 5) * 60 + 20, (i / 5) * 60 + 20, 50, 50);
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
                    //buttons[i].setBounds((i % 5) * 60 + 20, (i / 5) * 60 + 20, 50, 50);
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
                    //buttons[i].setBounds((i % 5) * 60 + 20, (i / 5) * 60 + 20, 50, 50);
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

            JLabel price = new JLabel("price = " + manager.truck.getPrice());
            price.setFont(new Font("Courier", Font.ITALIC, 18));
            price.setForeground(Color.magenta);
            panel.add(price);

        }

        public void printOnTruck(Graphics2D g2D) {
            int cnt = 0, cntt = 0;
            for (int i = 0; i < manager.truck.wildAnimalsToSell.size(); i++) {
                for (int j = 0; j < 15; j++) {
                    g2D.drawImage(new ImageIcon(manager.truck.wildAnimalsToSell.get(i).sadImage).getImage(), 135 + (j % 5) * 25, 620 + (j / 5) * 25, 20, 20, null);
                }
            }
            for (int i = 0; i < manager.truck.farmAnimalsToSell.size(); i++) {
                g2D.drawImage(new ImageIcon(manager.truck.farmAnimalsToSell.get(i).image).getImage(), 135 + (i % 5) * 25, 670 - (i / 5) * 25, 20, 20, null);
                cnt++;
            }
            for (int i = 0; i < manager.truck.stuffToSell.size(); i++) {
                for (int j = 0; j < manager.truck.stuffToSell.get(i).capacity; j++) {
                    int x = 135 + ((j + cnt + cntt) % 5) * 25;
                    int y = 670 - ((j + cnt + cntt) / 5) * 25;
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

        public void printIcons(Graphics2D g2D) {
            g2D.drawImage(new ImageIcon("newBack.jpg").getImage(), 0, 0, null);
            g2D.drawImage(new ImageIcon("buyHen.jpg").getImage(), 0, 0, iconSize[0][0], iconSize[0][1], null);
            g2D.drawImage(new ImageIcon("buyTurkey.jpg").getImage(), 0, 60, iconSize[1][0], iconSize[1][1], null);
            g2D.drawImage(new ImageIcon("buyBuffalo.jpg").getImage(), 0, 120, iconSize[2][0], iconSize[2][1], null);
            if (manager.orders.contains("well")){
                g2D.drawImage(new ImageIcon("wellAtWork.png").getImage(), 750, 30, 100, 150, null);
            } else {
                g2D.drawImage(new ImageIcon("well.png").getImage(), 750, 30, 100, 150, null);
            }
            if (manager.checkWell()) {
                g2D.drawImage(new ImageIcon("bucketEmpty.png").getImage() , 850 , 90,40,80,null);
            } else {
                g2D.drawImage(new ImageIcon("bucketFull.png").getImage() , 850 , 90,40,80,null);
            }

            g2D.drawImage(new ImageIcon("makeHen.png").getImage(), 520, 90, 40, 40, null);
            g2D.drawImage(new ImageIcon("mill.png").getImage(), 0, 180, iconSize[3][0], iconSize[3][1], null);
            g2D.drawImage(new ImageIcon("bakery.png").getImage(), 0, 240, iconSize[4][0], iconSize[4][1], null);
            g2D.drawImage(new ImageIcon("packagingMilk.png").getImage(), 0, 300, iconSize[5][0], iconSize[5][1], null);
            g2D.drawImage(new ImageIcon("iceCreamShop.png").getImage(), 0, 360, iconSize[6][0], iconSize[6][1], null);
            g2D.drawImage(new ImageIcon("sewing.png").getImage(), 0, 420, iconSize[7][0], iconSize[7][1], null);
            g2D.drawImage(new ImageIcon("weaving.png").getImage(), 0, 480, iconSize[8][0], iconSize[8][1], null);
            g2D.drawImage(new ImageIcon("dogIcon.png").getImage(), 0, 540, iconSize[9][0], iconSize[9][1], null);
            g2D.drawImage(new ImageIcon("catIcon.png").getImage(), 0, 600, iconSize[10][0], iconSize[10][1], null);
            g2D.drawImage(new ImageIcon("wareHouse.png").getImage(), 1090, 570, 210, 200, null);
            g2D.drawImage(new ImageIcon("truck.png").getImage(), 60, 610, 210, 180, null);
            g2D.drawImage(new ImageIcon("go.png").getImage(), 0, 670, 60, 60, null);
            g2D.drawImage(new ImageIcon("coin.png").getImage(), 1300, 20, 50, 50, null);
            g2D.drawImage(new ImageIcon("win-back.png").getImage(), 1150, 15, 155, 250, null);
            g2D.drawImage(new ImageIcon("turn.png").getImage(), 1290, 450, 70, 70, null);
            g2D.drawImage(new ImageIcon("menu.png").getImage(), 1290, 550, 70, 70, null);


            if (manager.orders.contains("truckGo"))
                g2D.drawImage(new ImageIcon("truckIsMoving.png").getImage(), 135, 670, 60, 60, null);

        }

        public void Turn(int timeCounter, Graphics2D g2D) {
            Font font = new Font("Courier New", Font.BOLD, 18);
            String time = timeCounter + " units of time passed :)";
            AttributedString as = new AttributedString(time);
            as.addAttribute(TextAttribute.FONT, font);
            as.addAttribute(TextAttribute.FOREGROUND, Color.PINK);
            g2D.drawString(as.getIterator(), 1000, 20);
            Font fontCoin = new Font("Courier New", Font.BOLD, 15);
            String coin = manager.coin + "$";
            AttributedString as1 = new AttributedString(coin);
            as1.addAttribute(TextAttribute.FONT, fontCoin);
            as1.addAttribute(TextAttribute.FOREGROUND, new Color(0x800919));
            g2D.drawString(as1.getIterator(), 1300, 48);


            int l = 0;
            manager.printTask();
            Font font1 = new Font("Courier New", Font.BOLD, 14);
            for (int i = 0; i < manager.tasks.length; i++) {
                if (manager.tasks[i] != null) {
                    String time1 = manager.tasks[i];
                    AttributedString as2 = new AttributedString(time1);
                    as2.addAttribute(TextAttribute.FONT, font1);
                    as2.addAttribute(TextAttribute.FOREGROUND, Color.BLACK);
                    g2D.drawString(as2.getIterator(), 1175, 75 + l);
                    l += 20;
                }
            }
        }

        public void printWorkingFactory(Graphics2D g2D) {
            for (int i = 0; i < manager.orders.size(); i += 2) {
                if (manager.orders.get(i).equals("addPowder")) {
                    g2D.drawImage(new ImageIcon("factoryWorking.png").getImage(), 280, 45, 40, 40, null);
                } else if (manager.orders.get(i).equals("addCloth")) {
                    if (manager.factory.factories.get(foundIndexOfFactory("sewing")).getLevel() == 1) {
                        g2D.drawImage(new ImageIcon("factoryWorking.png").getImage(), 295, 460, 40, 40, null);
                    } else {
                        g2D.drawImage(new ImageIcon("factoryWorking.png").getImage(), 250, 440, 40, 40, null);
                    }
                } else if (manager.orders.get(i).equals("addPackagedMilk")) {
                    if (manager.factory.factories.get(foundIndexOfFactory("milkPackaging")).getLevel() == 1) {
                        g2D.drawImage(new ImageIcon("factoryWorking.png").getImage(), 245, 270, 40, 40, null);
                    } else {
                        g2D.drawImage(new ImageIcon("factoryWorking.png").getImage(), 170, 310, 40, 40, null);
                    }
                } else if (manager.orders.get(i).equals("addBread")) {
                    if (manager.factory.factories.get(foundIndexOfFactory("bakery")).getLevel() == 1) {
                        g2D.drawImage(new ImageIcon("factoryWorking.png").getImage(), 960, 80, 40, 40, null);
                    } else {
                        g2D.drawImage(new ImageIcon("factoryWorking.png").getImage(), 1120, 160, 40, 40, null);
                    }
                } else if (manager.orders.get(i).equals("addShirt")) {
                    if (manager.factory.factories.get(foundIndexOfFactory("weaving")).getLevel() == 1) {
                        g2D.drawImage(new ImageIcon("factoryWorking.png").getImage(), 1050, 420, 40, 40, null);
                    } else {
                        g2D.drawImage(new ImageIcon("factoryWorking.png").getImage(), 1130, 530, 40, 40, null);
                    }
                } else if (manager.orders.get(i).equals("addIceCream")) {
                    if (manager.factory.factories.get(foundIndexOfFactory("iceCreamShop")).getLevel() == 1) {
                        g2D.drawImage(new ImageIcon("factoryWorking.png").getImage(), 1130, 320, 40, 40, null);
                    } else {
                        g2D.drawImage(new ImageIcon("factoryWorking.png").getImage(), 1140, 350, 40, 40, null);
                    }
                } else if (manager.orders.get(i).equals("makeHen")) {
                    g2D.drawImage(new ImageIcon("factoryWorking.png").getImage(), 500, 45, 40, 40, null);
                }
            }
        }

        public int foundIndexOfFactory(String name) {
            int i;
            for (i = 0; i < manager.factory.factories.size(); i++) {
                if (manager.factory.factories.get(i).getName().equalsIgnoreCase(name)) {
                    return i;
                }
            }
            return -1;
        }

        public void printFactory(Graphics2D g2D) {
            for (int i = 0; i < manager.factory.factories.size(); i++) {
                if (manager.factory.factories.get(i) instanceof Mill) {
                    if (manager.factory.factories.get(i).getLevel() == 1) {
                        g2D.drawImage(new ImageIcon("mill.png").getImage(), 200, 60, 180, 180, null);
                        g2D.drawImage(new ImageIcon("upgrade.png").getImage(), 190, 210, 30, 30, null);
                    } else if (manager.factory.factories.get(i).getLevel() == 2) {
                        g2D.drawImage(new ImageIcon("millLevel2.png").getImage(), 180, 70, 180, 180, null);
                    }
                } else if (manager.factory.factories.get(i) instanceof Bakery) {
                    if (manager.factory.factories.get(i).getLevel() == 1) {
                        g2D.drawImage(new ImageIcon("bakery.png").getImage(), 950, 80, 180, 180, null);
                        g2D.drawImage(new ImageIcon("upgrade.png").getImage(), 1130, 220, 30, 30, null);
                    } else if (manager.factory.factories.get(i).getLevel() == 2) {
                        g2D.drawImage(new ImageIcon("bakeryLevel2.png").getImage(), 950, 100, 180, 180, null);
                    }
                } else if (manager.factory.factories.get(i) instanceof MilkPackaging) {
                    if (manager.factory.factories.get(i).getLevel() == 1) {
                        g2D.drawImage(new ImageIcon("packagingMilk.png").getImage(), 180, 250, 220, 220, null);
                        g2D.drawImage(new ImageIcon("upgrade.png").getImage(), 170, 360, 30, 30, null);
                    } else if (manager.factory.factories.get(i).getLevel() == 2) {
                        g2D.drawImage(new ImageIcon("packagingMilkLevel2.png").getImage(), 180, 250, 180, 180, null);
                    }
                } else if (manager.factory.factories.get(i) instanceof IcecreamShop) {
                    if (manager.factory.factories.get(i).getLevel() == 1) {
                        g2D.drawImage(new ImageIcon("iceCreamShop.png").getImage(), 970, 250, 180, 180, null);
                        g2D.drawImage(new ImageIcon("upgrade.png").getImage(), 1150, 380, 30, 30, null);
                    } else if (manager.factory.factories.get(i).getLevel() == 2) {
                        g2D.drawImage(new ImageIcon("iceCreamShopLevel2.png").getImage(), 970, 250, 180, 180, null);
                    }
                } else if (manager.factory.factories.get(i) instanceof Sewing) {
                    if (manager.factory.factories.get(i).getLevel() == 1) {
                        g2D.drawImage(new ImageIcon("sewing.png").getImage(), 180, 450, 180, 180, null);
                        g2D.drawImage(new ImageIcon("upgrade.png").getImage(), 160, 590, 30, 30, null);
                    } else if (manager.factory.factories.get(i).getLevel() == 2) {
                        g2D.drawImage(new ImageIcon("sewingLevel2.png").getImage(), 180, 450, 180, 180, null);
                    }
                } else if (manager.factory.factories.get(i) instanceof Weaving) {
                    if (manager.factory.factories.get(i).getLevel() == 1) {
                        g2D.drawImage(new ImageIcon("weaving.png").getImage(), 970, 450, 180, 180, null);
                        g2D.drawImage(new ImageIcon("upgrade.png").getImage(), 1150, 580, 30, 30, null);
                    } else if (manager.factory.factories.get(i).getLevel() == 2) {
                        g2D.drawImage(new ImageIcon("weavingLevel2.png").getImage(), 970, 450, 200, 200, null);
                    }
                } else if (manager.factory.factories.get(i) instanceof MakeHen) {
                    g2D.drawImage(new ImageIcon("makeHen.png").getImage(), 500, 20, 180, 180, null);
                }
            }

        }

        public boolean checkFinishLevel() {
            return manager.checkTasks();
        }

        public void printFinishLevel(Graphics2D g2D) {
            exit = true;
            Manager.logger.info("level finished");
            manager.checkFinishLevel();
            String st = manager.moneySet(manager.timeCounter);
            //this.removeMouseListener(this);
            g2D.clearRect(0, 0, 1370, 850);
            g2D.drawImage(new ImageIcon("newBack.jpg").getImage(), 0, 0, null);
            g2D.drawImage(new ImageIcon("ok.png").getImage(), 70, 20, 120, 100, null);
            g2D.drawImage(new ImageIcon("sheet.png").getImage(), 385, 25, 600, 700, null);
            if (st.equalsIgnoreCase("golden")) {
                g2D.drawImage(new ImageIcon("golden.png").getImage(), 485, 175, 400, 400, null);
            } else if (st.equalsIgnoreCase("silver")) {
                g2D.drawImage(new ImageIcon("silver.png").getImage(), 485, 175, 400, 400, null);
            } else if (st.equalsIgnoreCase("bronze")) {
                g2D.drawImage(new ImageIcon("bronze.png").getImage(), 485, 175, 400, 400, null);
            }
            String massage = manager.users.get(manager.indexOfUser).name + "! " + "now you have " + manager.users.get(manager.indexOfUser).money + " $";
            AttributedString as1 = new AttributedString(massage);
            as1.addAttribute(TextAttribute.FONT, new Font("Courier New", Font.BOLD, 18));
            as1.addAttribute(TextAttribute.FOREGROUND, new Color(20, 58, 10));
            g2D.drawString(as1.getIterator(), 500, 560);


        }

        public void end() {
            exit = true;
            Manager.logger.info("game ended");
            jFrame1.invalidate();
            jFrame1.dispose();
            manager.timeCounter = 0;
            manager.orders.clear();
            manager.levels.clear();
            manager.readingLevels();

            JFrame frame;
            frame = new JFrame("menu");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setSize(900, 600);
            frame.setVisible(true);
            frame.setLayout(null);
            frame.setContentPane(new MenuSocond(manager, frame));
        }

        public void processTime() {
            manager.check();
            this.removeMouseListener(this);
            this.invalidate();
            this.validate();
            this.repaint();
        }

        public void pose(Graphics2D g2D) {
            exit = true;
            Manager.logger.info("Game Stopped");
            g2D.drawImage(new ImageIcon("newBack.jpg").getImage(), 0, 0, null);
            g2D.drawImage(new ImageIcon("darkGlass.png").getImage(), 0, 0, 1370, 900, null);
            g2D.drawImage(new ImageIcon("Wooden-Blackboard.png").getImage(), 0, 100, 1330, 730, null);
            g2D.drawImage(new ImageIcon("continue.png").getImage(), 400, 300, 500, 100, null);
            g2D.drawImage(new ImageIcon("newExit.png").getImage(), 400, 500, 500, 100, null);
            this.addMouseListener(this);
        }

        static {
            AudioInputStream audioStream = null;
            try {
                audioStream = AudioSystem.getAudioInputStream(file);
            } catch (UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                clip = AudioSystem.getClip();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
            try {
                clip.open(audioStream);
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void playErrorMusic() {
            clip.setMicrosecondPosition(1000);
            clip.start();
        }


    }

    static class Menu extends JFrame implements ActionListener {

        JFrame jFrame;
        JTextField textField;
        JPasswordField passwordField;
        JButton login;
        JButton signUp;
        JButton exit;
        Manager manager ;
        JLabel userNameField;
        JLabel passField;
        JLabel passArrow;
        JLabel password;
        JLabel usernameArrow;
        JLabel userName;


        public Menu(Manager manager) {
            this.manager = manager;
        }

        public void menu() {



            jFrame = new JFrame("menu");
            jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            jFrame.setSize(900, 700);
            ImageIcon arrow = new ImageIcon("arrow.png");
            ImageIcon userPassField = new ImageIcon("passField.png");





            JLabel label5 = new JLabel(new ImageIcon("rain.gif"));
            label5.setBounds(0,0,900,600);
            jFrame.add(label5);
            label5.setVisible(false);
            JLabel label6 = new JLabel(new ImageIcon("snow.gif"));
            label6.setBounds(0,0,900,600);
            jFrame.add(label6);
            label6.setVisible(false);

            JLabel label7 = new JLabel(new ImageIcon("sun.gif"));

            label7.setBounds(-200,-200,450,450);
            jFrame.add(label7);
            label7.setVisible(false);



           JButton mood = new JButton("mood");
           mood.setOpaque(false);
           mood.setContentAreaFilled(false);
           mood.setBorderPainted(false);
           mood.addActionListener(e -> {
               Random random = new Random();
               int whichMood = random.nextInt(4);
               if (whichMood == 0) {
                   label7.setVisible(false);
                   label6.setVisible(false);
                   label5.setVisible(false);
               } else if (whichMood == 1) {
                   label7.setVisible(true);
                   label6.setVisible(false);
                   label5.setVisible(false);
               } else if (whichMood == 2) {
                   label7.setVisible(false);
                   label6.setVisible(true);
                   label5.setVisible(false);
               } else if (whichMood == 3) {
                   label7.setVisible(false);
                   label6.setVisible(false);
                   label5.setVisible(true);
               }
           });
           mood.setBounds(10,600,150,50);
           mood.setFont(new Font("",Font.BOLD,20));
           mood.setForeground(new Color(90,90,10));
           jFrame.add(mood);







            JLabel label = new JLabel();
            label.setBounds(0, 0, 1370, 700);


            ImageIcon back = new ImageIcon("levelSelect.jpeg");
            label.setIcon(back);

            usernameArrow = new JLabel();
            usernameArrow.setBounds(10, 240, 300, 300);
            usernameArrow.setIcon(arrow);


            userName = new JLabel("USERNAME");
            userName.setBounds(50, 288, 200, 100);
            userName.setFont(new Font("Comic Sans", Font.PLAIN, 15));
            userName.setForeground(new Color(186, 101, 86, 255));


            userNameField = new JLabel();
            userNameField.setIcon(userPassField);
            userNameField.setBounds(260, 200, 200, 300);


            textField = new JTextField();
            textField.setFont(new Font("Consolas", Font.PLAIN, 18));
            textField.setBackground(new Color(201, 96, 27));
            textField.setBounds(280, 320, 150, 30);
            textField.setForeground(Color.WHITE);
            jFrame.add(textField);


            passArrow = new JLabel();
            passArrow.setBounds(160, 400, 300, 300);
            passArrow.setIcon(arrow);


            password = new JLabel("PASSWORD");
            password.setBounds(196, 448, 200, 100);
            password.setFont(new Font("Comic Sans", Font.PLAIN, 15));
            password.setForeground(new Color(186, 101, 86, 255));


            passField = new JLabel();
            passField.setIcon(userPassField);
            passField.setBounds(430, 370, 200, 300);


            passwordField = new JPasswordField();
            passwordField.setFont(new Font("Consolas", Font.PLAIN, 18));
            passwordField.setBackground(new Color(201, 96, 27));
            passwordField.setBounds(450, 490, 150, 30);
            passwordField.setForeground(Color.WHITE);
            jFrame.add(passwordField);


            login = new JButton("Login");
            login.addActionListener(this);
            login.setFocusable(false);
            login.setBounds(695, 20, 90, 30);
            login.setFont(new Font("Comic Sans", Font.BOLD, 13));
            login.setBackground(Color.CYAN);


            signUp = new JButton("SignUP");
            signUp.addActionListener(this);
            signUp.setFocusable(false);
            signUp.setBounds(800, 20, 90, 30);
            signUp.setFont(new Font("Comic Sans", Font.BOLD, 13));
            signUp.setBackground(Color.CYAN);


            exit = new JButton("EXIT");
            exit.addActionListener(this);
            exit.setFocusable(false);
            exit.setBounds(800, 620, 90, 30);
            exit.setFont(new Font("Comic Sans", Font.BOLD, 13));
            exit.setBackground(Color.red);


            jFrame.add(exit);
            jFrame.add(login);
            jFrame.add(signUp);
            jFrame.add(passField);
            jFrame.add(password);
            jFrame.add(passArrow);
            jFrame.add(userNameField);
            jFrame.add(userName);
            jFrame.add(usernameArrow);
            jFrame.add(label);


            jFrame.setVisible(true);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == exit) {
                if (!manager.users.isEmpty())
                    manager.writeGsonUsers();
                    Manager.writeLogger();
                System.exit(0);
            } else if (e.getSource() == login) {
                manager.username = textField.getText();
                manager.password = passwordField.getText();
                manager.choice = "1";
                int index = manager.menu(true);
                printLogMassage(index);
                if (index == 1) {
                    jFrame.dispose();
                }
            } else if (e.getSource() == signUp) {
                manager.username = textField.getText();
                manager.password = passwordField.getText();
                manager.choice = "2";
                int index = manager.menu(true);
                printLogMassage(index);
                if (index == 2) {
                    jFrame.dispose();
                    manager.writeGsonUsers();
                }
            }
        }


        public void printLogMassage(int i) {
            if ((i == -1) || (i == 3)) {
                JOptionPane.showMessageDialog(null, "INVALID USERNAME!", "MASSAGE", JOptionPane.WARNING_MESSAGE);
            } else if (i == 0) {
                JOptionPane.showMessageDialog(null, "WRONG PASSWORD!!", "MASSAGE", JOptionPane.WARNING_MESSAGE);
            } else if (i == 1) {
                JOptionPane.showMessageDialog(null, "LOGIN SUCCESSFULLY!", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);
                JFrame frame;
                frame = new JFrame("menu");
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.setSize(900, 600);
                frame.setVisible(true);
                frame.setLayout(null);
                frame.setContentPane(new MenuSocond(manager, frame));
            } else if (i == 2) {
                JOptionPane.showMessageDialog(null, "SIGN UP SUCCESSFULLY!", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);
                manager.addUserToSql(manager.username, manager.password);

                JFrame frame;
                frame = new JFrame("menu");
                frame.setSize(900, 600);
                frame.setVisible(true);
                frame.setLayout(null);
                frame.setContentPane(new MenuSocond(manager, frame));
            }
        }


    }

}

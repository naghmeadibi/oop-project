import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.AttributedString;
import java.util.Arrays;
import java.util.Random;

import javax.imageio.ImageIO;
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
            menu(true);


        }

        public void menu(boolean c) {
            JFrame myJFrame = new JFrame();

            String password;
            String username;
            drawpanel.manager.readUser();
            boolean mainWhile = true;
            while (mainWhile && c) {
                System.out.println("Enter number of your choice : ");
                System.out.println("1) login");
                System.out.println("2) sign up");
                System.out.println("3) EXIT");
                String choice = drawpanel.manager.scanner.nextLine();
                switch (choice) {
                    case "1":
                        System.out.println("enter your username :");
                        username = drawpanel.manager.scanner.nextLine();
                        if (drawpanel.manager.checkUsername(username) == -1) {
                            System.out.println("Invalid Username!!");
                            drawpanel.manager.logger.warning("Invalid Username");
                        } else {
                            while (true) {
                                System.out.println("enter your password : ");
                                password = drawpanel.manager.scanner.nextLine();
                                if (drawpanel.manager.users.get(drawpanel.manager.checkUsername(username)).getPassword().equalsIgnoreCase(password)) {
                                    mainWhile = false;
                                    System.out.println("done!");
                                    drawpanel.manager.logger.info("logged in successfully");
                                    drawpanel.manager.indexOfUser = drawpanel.manager.checkUsername(username);
                                    break;
                                } else {
                                    drawpanel.manager.logger.warning("wrong password");
                                    System.out.println("Wrong Password, try again!!");
                                }
                            }
                        }
                        break;
                    case "2":
                        while (true) {
                            System.out.println("enter your username :");
                            username = drawpanel.manager.scanner.nextLine();
                            if (drawpanel.manager.checkUsername(username) == -1) {
                                System.out.println("enter your password : ");
                                password = drawpanel.manager.scanner.nextLine();
                                mainWhile = false;
                                drawpanel.manager.indexOfUser = drawpanel.manager.numOfUsers;
                                drawpanel.manager.numOfUsers++;
                                drawpanel.manager.setNumOfUsers(drawpanel.manager.numOfUsers);
                                drawpanel.manager.users.add(new User(username, password, 1, 0));
                                drawpanel.manager.logger.info("signed up successfully");
                                break;
                            } else {
                                drawpanel.manager.logger.warning("invalid username");
                                System.out.println("your username is invalid choose another ");
                            }
                        }
                        break;
                    case "3":
                        drawpanel.manager.writeGsonUsers();
                        drawpanel.manager.logger.info("exit");
                        System.exit(0);
                }
            }
            while (true) {
                System.out.println("what shall we do next?");
                System.out.println("1) start [level] " + " 2) log out" + "  3) settings " + " 4) EXIT");
                String choice = drawpanel.manager.scanner.nextLine();
                String[] strings = choice.split("\\s+");
                if (strings[0].equalsIgnoreCase("1")) {
                /*selectedLevel = Integer.parseInt(strings[1]);
                if (selectedLevel > users.get(getIndexOfUser()).getLevel()) {
                    logger.warning("wrong selected level");
                    System.out.println("You cant play this level");
                } else {
                    System.out.println("Go On");
                    logger.info("Start Game");
                    return;
                }*/
                    graphicChoseLevel(myJFrame);
                    return;
                } else if (strings[0].equalsIgnoreCase("2")) {
                    c = true;
                    drawpanel.manager.logger.info("log out");
                    menu(c);
                    return;
                } else if (strings[0].equalsIgnoreCase("3")) {
                    grphicBuyWorkShop(myJFrame);
                    drawpanel.manager.logger.info("enter setting");
                    System.out.println("you wanna buy?");
                    System.out.println("1) mill 2) weaving 3) milkPackaging 4) bakery 5) sewing 6) iceCreamShop ");
                    String workShop = drawpanel.manager.scanner.nextLine();
                    if (workShop.equals("1")) {
                        if (!drawpanel.manager.users.get(drawpanel.manager.indexOfUser).workShops.get("mill")) {
                            if (drawpanel.manager.users.get(drawpanel.manager.indexOfUser).getMoney() >= 150) {
                                drawpanel.manager.users.get(drawpanel.manager.indexOfUser).workShops.replace("mill", true);
                                drawpanel.manager.users.get(drawpanel.manager.indexOfUser).setMoney(drawpanel.manager.users.get(drawpanel.manager.indexOfUser).getMoney() - 150);
                                drawpanel.manager.logger.info("Mill was bought");
                            } else {
                                System.out.println("you don't have enough money");
                                drawpanel.manager.logger.warning("There is not enough money");
                            }
                        } else {
                            System.out.println("mill is already taken");
                            drawpanel.manager.logger.warning("mill is already taken");
                        }
                    }
                    if (workShop.equals("2")) {
                        if (!drawpanel.manager.users.get(drawpanel.manager.indexOfUser).workShops.get("clothWeaving")) {
                            if (drawpanel.manager.users.get(drawpanel.manager.indexOfUser).getMoney() >= 250) {
                                drawpanel.manager.users.get(drawpanel.manager.indexOfUser).workShops.replace("clothWeaving", true);
                                drawpanel.manager.users.get(drawpanel.manager.indexOfUser).setMoney(drawpanel.manager.users.get(drawpanel.manager.indexOfUser).getMoney() - 250);
                                drawpanel.manager.logger.info("ClothWeaving was bought");
                            } else {
                                System.out.println("you don't have enough money");
                                drawpanel.manager.logger.warning("There is not enough money");
                            }
                        } else {
                            System.out.println("clothWeaving is already taken");
                            drawpanel.manager.logger.warning("clothWeaving is already taken");
                        }
                    }
                    if (workShop.equals("3")) {
                        if (!drawpanel.manager.users.get(drawpanel.manager.indexOfUser).workShops.get("milkPackaging")) {
                            if (drawpanel.manager.users.get(drawpanel.manager.indexOfUser).getMoney() >= 400) {
                                drawpanel.manager.users.get(drawpanel.manager.indexOfUser).workShops.replace("milkPackaging", true);
                                drawpanel.manager.users.get(drawpanel.manager.indexOfUser).setMoney(drawpanel.manager.users.get(drawpanel.manager.indexOfUser).getMoney() - 400);
                                drawpanel.manager.logger.info("MilkPackaging was bought");
                            } else {
                                System.out.println("you don't have enough money");
                                drawpanel.manager.logger.warning("There is not enough money");
                            }
                        } else {
                            System.out.println("milkPackaging is already taken");
                            drawpanel.manager.logger.warning("milkPackaging is already taken");
                        }
                    }
                    if (workShop.equals("4")) {
                        if (!drawpanel.manager.users.get(drawpanel.manager.indexOfUser).workShops.get("bakery")) {
                            if (drawpanel.manager.users.get(drawpanel.manager.indexOfUser).getMoney() >= 250) {
                                drawpanel.manager.users.get(drawpanel.manager.indexOfUser).workShops.replace("bakery", true);
                                drawpanel.manager.users.get(drawpanel.manager.indexOfUser).setMoney(drawpanel.manager.users.get(drawpanel.manager.indexOfUser).getMoney() - 250);
                                drawpanel.manager.logger.info("Bakery was bought");
                            } else {
                                System.out.println("you don't have enough money");
                                drawpanel.manager.logger.warning("There is not enough money");
                            }
                        } else {
                            System.out.println("bakery is already taken");
                            drawpanel.manager.logger.warning("bakery is already taken");
                        }
                    }
                    if (workShop.equals("5")) {
                        if (!drawpanel.manager.users.get(drawpanel.manager.indexOfUser).workShops.get("Sewing")) {
                            if (drawpanel.manager.users.get(drawpanel.manager.indexOfUser).getMoney() >= 400) {
                                drawpanel.manager.users.get(drawpanel.manager.indexOfUser).workShops.replace("Sewing", true);
                                drawpanel.manager.users.get(drawpanel.manager.indexOfUser).setMoney(drawpanel.manager.users.get(drawpanel.manager.indexOfUser).getMoney() - 400);
                                drawpanel.manager.logger.info("Sewing was bought");
                            } else {
                                System.out.println("you don't have enough money");
                                drawpanel.manager.logger.warning("There is not enough money");
                            }
                        } else {
                            System.out.println("sewing is already taken");
                            drawpanel.manager.logger.warning("sewing is already taken");
                        }
                    }
                    if (workShop.equals("6")) {
                        if (!drawpanel.manager.users.get(drawpanel.manager.indexOfUser).workShops.get("iceCreamShop")) {
                            if (drawpanel.manager.users.get(drawpanel.manager.indexOfUser).getMoney() >= 550) {
                                drawpanel.manager.users.get(drawpanel.manager.indexOfUser).workShops.replace("iceCreamShop", true);
                                drawpanel.manager.users.get(drawpanel.manager.indexOfUser).setMoney(drawpanel.manager.users.get(drawpanel.manager.indexOfUser).getMoney() - 550);
                                drawpanel.manager.logger.info("IceCreamShop was bought");
                            } else {
                                System.out.println("you don't have enough money");
                                drawpanel.manager.logger.warning("There is not enough money");
                            }
                        } else {
                            System.out.println("iceCreamShop is already taken");
                            drawpanel.manager.logger.warning("iceCreamShop is already taken");
                        }
                    }
                    drawpanel.manager.writeGsonUsers();
                } else if (choice.equals("4")) {
                    drawpanel.manager.writeGsonUsers();
                    drawpanel.manager.logger.info("exit");
                    System.exit(0);
                }

            }
        }

        public void printOnButton(JButton button, String address) {
            ImageIcon imageIcon = new ImageIcon(address);
            Image image = imageIcon.getImage();
            Image newIm = image.getScaledInstance(35, 35, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(newIm);
            button.setIcon(imageIcon);
        }

        public void graphicChoseLevel(JFrame myJFrame) {
            try {
                BufferedImage myImage = ImageIO.read(new File("menuBack.jpeg"));
                myJFrame.setTitle("choose level");
                myJFrame.setContentPane(new ImagePanel(myImage));
                myJFrame.setSize(900, 600);
                myJFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                myJFrame.setVisible(true);
                Image image = new ImageIcon("back.png").getImage().getScaledInstance(80, 50, Image.SCALE_SMOOTH);
                myJFrame.setLayout(null);
                ImageIcon icon = new ImageIcon(image);
                JButton back = new JButton(icon);
                back.setBounds(0, 0, 80, 50);
                back.addActionListener(e -> {
                    myJFrame.removeAll();
                    menu(false);
                });
                back.setOpaque(false);
                back.setContentAreaFilled(false);
                back.setBorderPainted(false);
                myJFrame.add(back);

                JButton[] buttons = new JButton[10];


                for (int i = 0; i < 10; i++) {
                    buttons[i] = new JButton();
                    buttons[i].setOpaque(false);
                    buttons[i].setContentAreaFilled(false);
                    buttons[i].setBorderPainted(false);
                    if (drawpanel.manager.users.get(drawpanel.manager.indexOfUser).level >= i + 1) {
                        printOnButton(buttons[i], "openLock.png");
                        int f = i;
                        buttons[i].addActionListener(e -> {
                            myJFrame.dispose();
                            drawpanel.manager.selectedLevel = f + 1;
                            drawpanel.manager.logger.info("Start Game");
                            start();
                            return;
                        });
                    } else {
                        printOnButton(buttons[i], "closeLock.png");
                        buttons[i].addActionListener(e -> {
                            JOptionPane.showMessageDialog(null, "this level is locked", "ERROR", JOptionPane.ERROR_MESSAGE);
                            drawpanel.manager.logger.warning("wrong selected level");
                        });
                    }
                    myJFrame.add(buttons[i]);

                }
                buttons[0].setBounds(320, 500, 40, 40);
                buttons[1].setBounds(260, 480, 40, 40);
                buttons[2].setBounds(200, 460, 40, 40);
                buttons[3].setBounds(127, 420, 40, 40);
                buttons[4].setBounds(190, 380, 40, 40);
                buttons[5].setBounds(240, 350, 40, 40);
                buttons[6].setBounds(300, 320, 40, 40);
                buttons[7].setBounds(350, 290, 40, 40);
                buttons[8].setBounds(280, 270, 40, 40);
                buttons[9].setBounds(235, 260, 40, 40);


            } catch (IOException e) {
                System.out.println("Error");
            }


        }

        public void start() {
            drawpanel.manager.readingLevels();
            drawpanel.manager.setCounter(0);
            drawpanel.manager.setBack();
            drawpanel.manager.coinSet();


            frame.getContentPane().add(drawpanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1370, 850);
            frame.setVisible(true);
        }

        public void grphicBuyWorkShop(JFrame myJFrame) {
            try {
                myJFrame.invalidate();
                myJFrame.validate();
                BufferedImage myImage = ImageIO.read(new File("backBuyWorkShop.jpg"));
                myJFrame.setContentPane(new ImagePanel(myImage));
                myJFrame.repaint();
            } catch (IOException e) {
            }
        }

    }

    static class MyDrawPanel extends JComponent implements MouseListener {
        Manager manager = new Manager();

        public void paintComponent(Graphics g) {


            manager.check();

            Graphics2D g2D = (Graphics2D) g;


            printIcons(g2D);


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
            } else if (e.getX() >= 190 && e.getX() <= 220 && e.getY() >= 210 && e.getY() <= 240) {
                manager.processUpgrade("mill");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 1130 && e.getX() <= 1160 && e.getY() >= 220 && e.getY() <= 250) {
                manager.processUpgrade("bakery");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 170 && e.getX() <= 200 && e.getY() >= 360 && e.getY() <= 390) {
                manager.processUpgrade("milkPackaging");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 1150 && e.getX() <= 1180 && e.getY() >= 380 && e.getY() <= 410) {
                manager.processUpgrade("iceCreamShop");
                this.removeMouseListener(this);
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
            } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 240 && e.getY() <= 300) {
                manager.processBuild("bakery");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 900 && e.getX() <= 1080 && e.getY() >= 80 && e.getY() <= 260) {
                manager.workFactory("bakery");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 300 && e.getY() <= 360) {
                manager.processBuild("milkPackaging");
                this.removeMouseListener(this);
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
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 970 && e.getX() <= 1150 && e.getY() >= 261 && e.getY() <= 430) {
                manager.workFactory("iceCreamShop");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 420 && e.getY() <= 480) {
                manager.processBuild("sewing");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 180 && e.getX() <= 360 && e.getY() >= 451 && e.getY() <= 630) {
                manager.workFactory("sewing");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 0 && e.getX() <= 60 && e.getY() >= 480 && e.getY() <= 540) {
                manager.processBuild("weaving");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 970 && e.getX() <= 1150 && e.getY() >= 450 && e.getY() <= 630) {
                manager.workFactory("weaving");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
                return;
            } else if (e.getX() >= 600 && e.getX() <= 700 && e.getY() >= 30 && e.getY() <= 180) {
                manager.processWell();
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


            System.out.println("Remaining tasks : ");
            manager.printTask();
        }

        public void printIcons(Graphics2D g2D) {
            g2D.drawImage(new ImageIcon("newBack.jpg").getImage(), 0, 0, null);
            g2D.drawImage(new ImageIcon("buyHen.jpg").getImage(), 0, 0, 60, 60, null);
            g2D.drawImage(new ImageIcon("buyTurkey.jpg").getImage(), 0, 60, 60, 60, null);
            g2D.drawImage(new ImageIcon("buyBuffalo.jpg").getImage(), 0, 120, 60, 60, null);
            g2D.drawImage(new ImageIcon("well.png").getImage(), 600, 30, 100, 150, null);
            g2D.drawImage(new ImageIcon("mill.png").getImage(), 0, 180, 60, 60, null);
            g2D.drawImage(new ImageIcon("bakery.png").getImage(), 0, 240, 60, 60, null);
            g2D.drawImage(new ImageIcon("packagingMilk.png").getImage(), 0, 300, 60, 60, null);
            g2D.drawImage(new ImageIcon("iceCreamShop.png").getImage(), 0, 360, 60, 60, null);
            g2D.drawImage(new ImageIcon("sewing.png").getImage(), 0, 420, 60, 60, null);
            g2D.drawImage(new ImageIcon("weaving.png").getImage(), 0, 480, 60, 60, null);
            g2D.drawImage(new ImageIcon("wareHouse.png").getImage(), 1090, 570, 210, 200, null);
            g2D.drawImage(new ImageIcon("truck.png").getImage(), 60, 610, 210, 180, null);
            g2D.drawImage(new ImageIcon("go.png").getImage(), 0, 670, 60, 60, null);
            g2D.drawImage(new ImageIcon("coin.png").getImage(), 1300, 20, 50, 50, null);
            if (manager.orders.contains("truckGo"))
                g2D.drawImage(new ImageIcon("truckIsMoving.png").getImage(), 135, 670, 60, 60, null);

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
                        g2D.drawImage(new ImageIcon("factoryWorking.png").getImage(), 1130, 150, 40, 40, null);
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
                }
            }
        }

        public int foundIndexOfFactory(String name) {
            int i = 0;
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
                }
            }

        }


    }

}

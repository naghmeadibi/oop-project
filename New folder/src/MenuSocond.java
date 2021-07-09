import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class MenuSocond extends JComponent implements MouseListener {
    Manager manager;
    JFrame frame;
    int witchPage = 1;


    public MenuSocond(Manager manager, JFrame frame) {
        this.manager = manager;
        this.frame = frame;

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        if (witchPage == 2)
            chooseLevel(graphics2D);
        if (witchPage == 1)
            next(graphics2D);
        if (witchPage == 3)
            setting(graphics2D);
        this.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (witchPage == 2) {
            if (e.getX() >= 320 && e.getX() <= 360 && e.getY() >= 500 && e.getY() <= 540) {
                if (checkPossible(1)) {
                    frame.dispose();
                    Gui2D.Gui2 gui = new Gui2D.Gui2(new Gui2D.MyDrawPanel(manager));
                    manager.selectedLevel = 1;
                    manager.logger.info("Start level 1");
                    gui.go(manager);
                } else {
                    manager.logger.warning("Level 1 is locked!!");
                    JOptionPane.showMessageDialog(null, "this level is locked", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getX() >= 260 && e.getX() <= 300 && e.getY() >= 480 && e.getY() <= 520) {
                if (checkPossible(2)) {
                    frame.dispose();
                    Gui2D.Gui2 gui = new Gui2D.Gui2(new Gui2D.MyDrawPanel(manager));
                    manager.selectedLevel = 2;
                    manager.logger.info("Start level 2");
                    gui.go(manager);
                } else {
                    manager.logger.warning("Level 2 is locked!!");
                    JOptionPane.showMessageDialog(null, "this level is locked", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getX() >= 200 && e.getX() <= 240 && e.getY() >= 460 && e.getY() <= 500) {
                if (checkPossible(3)) {
                    frame.dispose();
                    Gui2D.Gui2 gui = new Gui2D.Gui2(new Gui2D.MyDrawPanel(manager));
                    manager.selectedLevel = 3;
                    manager.logger.info("Start level 3");
                    gui.go(manager);
                } else {
                    manager.logger.warning("Level 3 is locked!!");
                    JOptionPane.showMessageDialog(null, "this level is locked", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getX() >= 127 && e.getX() <= 167 && e.getY() >= 420 && e.getY() <= 460) {
                if (checkPossible(4)) {
                    frame.dispose();
                    Gui2D.Gui2 gui = new Gui2D.Gui2(new Gui2D.MyDrawPanel(manager));
                    manager.selectedLevel = 4;
                    manager.logger.info("Start level 4");
                    gui.go(manager);
                } else {
                    manager.logger.warning("Level 4 is locked!!");
                    JOptionPane.showMessageDialog(null, "this level is locked", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getX() >= 190 && e.getX() <= 230 && e.getY() >= 380 && e.getY() <= 420) {
                if (checkPossible(5)) {
                    frame.dispose();
                    Gui2D.Gui2 gui = new Gui2D.Gui2(new Gui2D.MyDrawPanel(manager));
                    manager.selectedLevel = 5;
                    manager.logger.info("Start level 5");
                    gui.go(manager);
                } else {
                    manager.logger.warning("Level 5 is locked!!");
                    JOptionPane.showMessageDialog(null, "this level is locked", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getX() >= 240 && e.getX() <= 280 && e.getY() >= 350 && e.getY() <= 390) {
                if (checkPossible(6)) {
                    frame.dispose();
                    Gui2D.Gui2 gui = new Gui2D.Gui2(new Gui2D.MyDrawPanel(manager));
                    manager.selectedLevel = 6;
                    manager.logger.info("Start level 6");
                    gui.go(manager);
                } else {
                    manager.logger.warning("Level 6 is locked!!");
                    JOptionPane.showMessageDialog(null, "this level is locked", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getX() >= 300 && e.getX() <= 340 && e.getY() >= 320 && e.getY() <= 360) {
                if (checkPossible(7)) {
                    frame.dispose();
                    Gui2D.Gui2 gui = new Gui2D.Gui2(new Gui2D.MyDrawPanel(manager));
                    manager.selectedLevel = 7;
                    manager.logger.info("Start level 7");
                    gui.go(manager);
                } else {
                    manager.logger.warning("Level 7 is locked!!");
                    JOptionPane.showMessageDialog(null, "this level is locked", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getX() >= 350 && e.getX() <= 390 && e.getY() >= 290 && e.getY() <= 310) {
                if (checkPossible(8)) {
                    frame.dispose();
                    Gui2D.Gui2 gui = new Gui2D.Gui2(new Gui2D.MyDrawPanel(manager));
                    manager.selectedLevel = 8;
                    manager.logger.info("Start level 8");
                    gui.go(manager);
                } else {
                    manager.logger.warning("Level 8 is locked!!");
                    JOptionPane.showMessageDialog(null, "this level is locked", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getX() >= 280 && e.getX() <= 320 && e.getY() >= 290 && e.getY() <= 330) {
                if (checkPossible(9)) {
                    frame.dispose();
                    Gui2D.Gui2 gui = new Gui2D.Gui2(new Gui2D.MyDrawPanel(manager));
                    manager.selectedLevel = 9;
                    manager.logger.info("Start level 9");
                    gui.go(manager);
                } else {
                    manager.logger.warning("Level 9 is locked!!");
                    JOptionPane.showMessageDialog(null, "this level is locked", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getX() >= 235 && e.getX() <= 275 && e.getY() >= 260 && e.getY() <= 300) {
                if (checkPossible(10)) {
                    frame.dispose();
                    Gui2D.Gui2 gui = new Gui2D.Gui2(new Gui2D.MyDrawPanel(manager));
                    manager.selectedLevel = 10;
                    manager.logger.info("Start level 10");
                    gui.go(manager);
                } else {
                    manager.logger.warning("Level 10 is locked!!");
                    JOptionPane.showMessageDialog(null, "this level is locked", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getX() >= 0 && e.getX() <= 80 && e.getY() >= 0 && e.getY() <= 40) {
                witchPage = 1;
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
            }
        } else if (witchPage == 1) {
            if (e.getX() >= 200 && e.getX() <= 700 && e.getY() >= 100 && e.getY() <= 200) {
                witchPage = 2;
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
            } else if (e.getX() >= 200 && e.getX() <= 700 && e.getY() >= 250 && e.getY() <= 350) {
                witchPage = 3;
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
            } else if (e.getX() >= 200 && e.getX() <= 700 && e.getY() >= 400 && e.getY() <= 500) {
                manager.logger.info("Logged Out");
                Gui2D.Menu menu = new Gui2D.Menu(manager);
                menu.menu();
                frame.dispose();
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
            } else if (e.getX() >= 0 && e.getX() <= 80 && e.getY() >= 0 && e.getY() <= 50) {
                this.removeMouseListener(this);
                manager.writeGsonUsers();
                manager.logger.info("Exit");
                System.exit(0);
            }
        } else if (witchPage == 3) {
            if (e.getX() >= 50 && e.getX() <= 250 && e.getY() >= 50 && e.getY() <= 250) {
                buyWorkShop("4");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
            } else if (e.getX() >= 250 && e.getX() <= 450 && e.getY() >= 50 && e.getY() <= 250) {
                buyWorkShop("1");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
            } else if (e.getX() >= 450 && e.getX() <= 650 && e.getY() >= 50 && e.getY() <= 250) {
                buyWorkShop("3");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
            } else if (e.getX() >= 50 && e.getX() <= 250 && e.getY() >= 250 && e.getY() <= 450) {
                buyWorkShop("5");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
            } else if (e.getX() >= 250 && e.getX() <= 450 && e.getY() >= 250 && e.getY() <= 450) {
                buyWorkShop("2");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
            } else if (e.getX() >= 450 && e.getX() <= 650 && e.getY() >= 250 && e.getY() <= 450) {
                buyWorkShop("6");
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
            } else if (e.getX() >= 0 && e.getX() <= 80 && e.getY() >= 510 && e.getY() <= 550) {
                witchPage = 1;
                this.removeMouseListener(this);
                this.invalidate();
                this.validate();
                this.repaint();
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

    public Image printLevels(int level) {
        ImageIcon lock = new ImageIcon("closeLock.png");
        ImageIcon open = new ImageIcon("openLock.png");
        if (manager.users.get(manager.indexOfUser).level >= level)
            return open.getImage();
        else
            return lock.getImage();
    }

    public boolean checkPossible(int level) {
        if (manager.users.get(manager.indexOfUser).level >= level)
            return true;
        else
            return false;
    }

    public void chooseLevel(Graphics2D graphics2D) {
        graphics2D.drawImage(new ImageIcon("levelSelect.jpeg").getImage(), 0, 0, 900, 600, this);
        graphics2D.drawImage(printLevels(1), 320, 500, 40, 40, null);
        graphics2D.drawImage(printLevels(2), 260, 480, 40, 40, null);
        graphics2D.drawImage(printLevels(3), 200, 460, 40, 40, null);
        graphics2D.drawImage(printLevels(4), 127, 420, 40, 40, null);
        graphics2D.drawImage(printLevels(5), 190, 380, 40, 40, null);
        graphics2D.drawImage(printLevels(6), 240, 350, 40, 40, null);
        graphics2D.drawImage(printLevels(7), 300, 320, 40, 40, null);
        graphics2D.drawImage(printLevels(8), 350, 290, 40, 40, null);
        graphics2D.drawImage(printLevels(9), 280, 270, 40, 40, null);
        graphics2D.drawImage(printLevels(10), 235, 260, 40, 40, null);
        graphics2D.drawImage(new ImageIcon("back.png").getImage(), 0, 0, 80, 40, null);
    }

    public void next(Graphics2D graphics2D) {
        graphics2D.drawImage(new ImageIcon("backgroundNext.png").getImage(), 0, 0, 900, 600, null);
        graphics2D.drawImage(new ImageIcon("board.png").getImage(), 200, 80, 500, 520, null);
        Font font = new Font("Courier New", Font.BOLD, 35);
        graphics2D.setFont(font);
        graphics2D.drawString("Levels", 395, 160);
        graphics2D.drawString("Setting", 395, 310);
        graphics2D.drawString("LogOut", 395, 460);
        graphics2D.drawImage(new ImageIcon("exit.png").getImage(), 0, 0, 80, 50, null);
    }

    public void setting(Graphics2D graphics2D) {
        graphics2D.drawImage(new ImageIcon("backBuyWorkShop.jpg").getImage(), 0, 0, 900, 600, null);
        graphics2D.drawImage(new ImageIcon("bakery.png").getImage(), 50, 50, 200, 200, null);
        graphics2D.drawImage(new ImageIcon("mill.png").getImage(), 250, 50, 200, 200, null);
        graphics2D.drawImage(new ImageIcon("packagingMilk.png").getImage(), 450, 50, 200, 200, null);
        graphics2D.drawImage(new ImageIcon("sewing.png").getImage(), 50, 250, 200, 200, null);
        graphics2D.drawImage(new ImageIcon("weaving.png").getImage(), 250, 250, 200, 200, null);
        graphics2D.drawImage(new ImageIcon("iceCreamShop.png").getImage(), 450, 250, 200, 200, null);
        graphics2D.drawImage(new ImageIcon("back.png").getImage(), 0, 510, 80, 40, null);
        String money = "MONEY : " + manager.users.get(manager.indexOfUser).getMoney() + " $";
        AttributedString as1 = new AttributedString(money);
        as1.addAttribute(TextAttribute.FONT, new Font("Courier New",Font.BOLD,18));
        as1.addAttribute(TextAttribute.FOREGROUND, new Color(0x800919));
        graphics2D.drawString(as1.getIterator(), 750, 20);
    }

    public void buyWorkShop(String workShop) {
        manager.logger.info("enter setting");

        if (workShop.equals("1")) {
            if (!manager.users.get(manager.indexOfUser).workShops.get("mill")) {
                if (manager.users.get(manager.indexOfUser).getMoney() >= 150) {
                    manager.users.get(manager.indexOfUser).workShops.replace("mill", true);
                    manager.users.get(manager.indexOfUser).setMoney(manager.users.get(manager.indexOfUser).getMoney() - 150);
                    manager.logger.info("Mill was bought");
                    JOptionPane.showMessageDialog(null, "Mill was bought", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    manager.logger.warning("There is not enough money");
                    JOptionPane.showMessageDialog(null, "There is not enough money", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                manager.logger.warning("mill is already taken");
                JOptionPane.showMessageDialog(null, "mill is already taken", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        if (workShop.equals("2")) {
            if (!manager.users.get(manager.indexOfUser).workShops.get("clothWeaving")) {
                if (manager.users.get(manager.indexOfUser).getMoney() >= 250) {
                    manager.users.get(manager.indexOfUser).workShops.replace("clothWeaving", true);
                    manager.users.get(manager.indexOfUser).setMoney(manager.users.get(manager.indexOfUser).getMoney() - 250);
                    manager.logger.info("ClothWeaving was bought");
                    JOptionPane.showMessageDialog(null, "ClothWeaving was bought", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    manager.logger.warning("There is not enough money");
                    JOptionPane.showMessageDialog(null, "There is not enough money", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                manager.logger.warning("clothWeaving is already taken");
                JOptionPane.showMessageDialog(null, "clothWeaving is already taken", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        if (workShop.equals("3")) {
            if (!manager.users.get(manager.indexOfUser).workShops.get("milkPackaging")) {
                if (manager.users.get(manager.indexOfUser).getMoney() >= 400) {
                    manager.users.get(manager.indexOfUser).workShops.replace("milkPackaging", true);
                    manager.users.get(manager.indexOfUser).setMoney(manager.users.get(manager.indexOfUser).getMoney() - 400);
                    manager.logger.info("MilkPackaging was bought");
                    JOptionPane.showMessageDialog(null, "MilkPackaging was bought", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    manager.logger.warning("There is not enough money");
                    JOptionPane.showMessageDialog(null, "There is not enough money", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                manager.logger.warning("milkPackaging is already taken");
                JOptionPane.showMessageDialog(null, "milkPackaging is already taken", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        if (workShop.equals("4")) {
            if (!manager.users.get(manager.indexOfUser).workShops.get("bakery")) {
                if (manager.users.get(manager.indexOfUser).getMoney() >= 250) {
                    manager.users.get(manager.indexOfUser).workShops.replace("bakery", true);
                    manager.users.get(manager.indexOfUser).setMoney(manager.users.get(manager.indexOfUser).getMoney() - 250);
                    manager.logger.info("Bakery was bought");
                    JOptionPane.showMessageDialog(null, "Bakery was bought", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    manager.logger.warning("There is not enough money");
                    JOptionPane.showMessageDialog(null, "There is not enough money", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                manager.logger.warning("bakery is already taken");
                JOptionPane.showMessageDialog(null, "bakery is already taken", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        if (workShop.equals("5")) {
            if (!manager.users.get(manager.indexOfUser).workShops.get("Sewing")) {
                if (manager.users.get(manager.indexOfUser).getMoney() >= 400) {
                    manager.users.get(manager.indexOfUser).workShops.replace("Sewing", true);
                    manager.users.get(manager.indexOfUser).setMoney(manager.users.get(manager.indexOfUser).getMoney() - 400);
                    manager.logger.info("Sewing was bought");
                    JOptionPane.showMessageDialog(null, "Sewing was bought", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    manager.logger.warning("There is not enough money");
                    JOptionPane.showMessageDialog(null, "There is not enough money", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                manager.logger.warning("sewing is already taken");
                JOptionPane.showMessageDialog(null, "sewing is already taken", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        if (workShop.equals("6")) {
            if (!manager.users.get(manager.indexOfUser).workShops.get("iceCreamShop")) {
                if (manager.users.get(manager.indexOfUser).getMoney() >= 550) {
                    manager.users.get(manager.indexOfUser).workShops.replace("iceCreamShop", true);
                    manager.users.get(manager.indexOfUser).setMoney(manager.users.get(manager.indexOfUser).getMoney() - 550);
                    manager.logger.info("IceCreamShop was bought");
                    JOptionPane.showMessageDialog(null, "IceCreamShop was bought", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    manager.logger.warning("There is not enough money");
                    JOptionPane.showMessageDialog(null, "There is not enough money", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

                }
            } else {
                manager.logger.warning("iceCreamShop is already taken");
                JOptionPane.showMessageDialog(null, "iceCreamShop is already taken", "MASSAGE", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        manager.writeGsonUsers();
    }
}

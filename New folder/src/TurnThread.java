
public class TurnThread extends Thread {
    Manager manager;
    Gui2D.MyDrawPanel drawPanel;

    public TurnThread(Manager manager, Gui2D.MyDrawPanel drawPanel) {

        super("TurnThread");
        this.manager = manager;
        this.drawPanel = drawPanel;

    }

    public void run() {
        drawPanel.manager.readingLevels();
        int i  = 0;

        while (true) {
            if (i == 9) {
                drawPanel.manager.playMusic(manager.clip);
                i = 0;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (!drawPanel.exit) {
                    drawPanel.processTime();
                }
                i ++;
            } catch (Exception e) {
                System.out.println("something occurred");
            }
        }
    }
}


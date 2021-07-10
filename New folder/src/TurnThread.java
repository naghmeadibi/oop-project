/**
 * Created by Atmospher-PC on 02/07/2021.
 */
public class TurnThread extends Thread {
    Manager manager;
    Gui2D.MyDrawPanel drawPanel;

    public TurnThread(Manager manager , Gui2D.MyDrawPanel drawPanel) {

        super("TurnThread");
        this.manager = manager;
        this.drawPanel = drawPanel;

    }
    public void run() {
        while (true) {
           // manager.check();

            drawPanel.processTime();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    }


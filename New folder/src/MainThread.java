/**
 * Created by Atmospher-PC on 02/07/2021.
 */
public class MainThread extends Thread {
    Manager manager;
    Gui2D.MyDrawPanel drawPanel;
    public MainThread(Manager manager, Gui2D.MyDrawPanel drawPanel) {
        super("MainThread");
        this.manager = manager;
        this.drawPanel = drawPanel;
    }
    public void run() {

        Gui2D.Gui2 gui = new Gui2D.Gui2(drawPanel);
        gui.goOn(manager);
    }
}

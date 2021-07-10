/**
 * Created by Atmospher-PC on 02/07/2021.
 */
public class MainThread extends Thread {
    Manager manager;
    public MainThread(Manager manager) {
        super("MainThread");
        this.manager = manager;
    }
    public void run() {
        Gui2D.MyDrawPanel draw =(new Gui2D.MyDrawPanel(manager));
        draw.w = 2;
        Gui2D.Gui2 gui = new Gui2D.Gui2(draw);
        gui.goOn(manager);
    }
}

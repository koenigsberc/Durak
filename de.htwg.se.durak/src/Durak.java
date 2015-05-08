import de.htwg.controller.DurakController;
import de.htwg.view.tui.TUI;
import de.htwg.view.gui.DurakFrame;

/**
 * Created by jawaigel on 16.04.2015.
 */
public class Durak {

    public static void main(String args[]){
        DurakController controller = new DurakController();

        new DurakFrame(controller);
        TUI tui = new TUI(controller);
        tui.printTUI();

        while(!tui.iterate());
    }
}

//TODO: Tests, Sonar, Jenkins, GUI,

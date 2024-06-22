package runner;

import views.GuiManager;

import java.awt.*;

public class Runner {

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> new GuiManager());
    }


}

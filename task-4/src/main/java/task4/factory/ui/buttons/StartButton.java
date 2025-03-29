package task4.factory.ui.buttons;

import javax.swing.*;
import java.awt.event.ActionListener;

public class StartButton extends JButton {
    public StartButton(ActionListener actionListener) {
        super("Start");
        this.setBorderPainted(true);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.addActionListener(actionListener);
    }
}

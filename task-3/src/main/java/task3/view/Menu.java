package task3.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.LinkedList;

public class Menu extends Scene implements ActionListener {

    protected final MenuListener menuListener;
    private final LinkedList<JButton> buttonsPool;

    protected final int buttonHeight = 150;
    protected final int buttonWidth = 300;
    protected final int xOffset = 60;
    protected final int yOffset = 100;
    protected final int rangeBetweenButtons = 25;
    protected int buttonsNum = 0;

    protected final JLabel background;

    protected JButton addButton(String path) {
        buttonsNum++;
        URL iconURL = getClass().getResource(path);
        Icon texture = null;
        if (iconURL != null) {
            Image image = new ImageIcon(iconURL).getImage().getScaledInstance(buttonWidth, buttonHeight, Image.SCALE_DEFAULT);
            texture = new ImageIcon(image);
        }
        JButton button = new JButton(texture);
        button.setBounds(xOffset,yOffset + (buttonHeight + rangeBetweenButtons) * buttonsNum, buttonWidth, buttonHeight);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addActionListener(this);
        buttonsPool.add(button);
        return button;
    }

    public Menu(Dimension screenSize, MenuListener menuListener) {
        super(screenSize);
        buttonsPool = new LinkedList<>();

        URL iconURL = getClass().getResource("/menuBackground.png");
        ImageIcon backGroundImage = null;
        if (iconURL != null) {
            Image image = new ImageIcon(iconURL).getImage().getScaledInstance(screenSize.width, screenSize.height, Image.SCALE_DEFAULT);
            backGroundImage = new ImageIcon(image);
        }

        this.setLayout(null);
        this.setOpaque(true);
        this.menuListener = menuListener;

        background = new JLabel(backGroundImage);
        background.setBounds(0,0, screenSize.width, screenSize.height);
        this.add(background);
    }

    protected void setZOrder() {
        this.setComponentZOrder(background, this.getComponentCount() - 1);
        int index = 0;
        for (JButton button : buttonsPool) {
            this.setComponentZOrder(button, index);
            index++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

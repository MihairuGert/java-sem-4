package task4.factory.ui.slider;

import task4.factory.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class FactorySlider {
    private JLabel inStock;
    private JLabel created;

    public FactorySlider(GridBagConstraints gbc, MainWindow mainWindow) {
        gbc.gridy = mainWindow.getCurGridy();
        mainWindow.setCurGridy(gbc.gridy + 1);
        JPanel textFieldsPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        inStock = new JLabel("In stock: ");
        textFieldsPanel.add(inStock);
        created = new JLabel("Created: ");
        textFieldsPanel.add(created);
        mainWindow.add(textFieldsPanel, gbc);

        gbc.gridy = mainWindow.getCurGridy();
        mainWindow.setCurGridy(gbc.gridy + 1);
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        mainWindow.add(slider, gbc);
    }
}

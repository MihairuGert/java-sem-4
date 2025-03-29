package task4.factory.ui.slider;

import task4.factory.ui.MainWindow;

import javax.swing.*;
import java.awt.*;

public class FactorySlider extends JSlider {
    private JLabel inStock;
    private JLabel created;

    public FactorySlider(GridBagConstraints gbc, MainWindow mainWindow, String name) {
        super(JSlider.HORIZONTAL, 0, 5000, 2500);
        gbc.gridy = mainWindow.getCurGridy();
        mainWindow.setCurGridy(gbc.gridy + 1);
        JPanel textFieldsPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        textFieldsPanel.add(new JLabel(name + " speed (ms)"));
        if (!name.equals("Dealers")) {
            inStock = new JLabel("In stock: ");
            textFieldsPanel.add(inStock);
            created = new JLabel("Created: ");
            textFieldsPanel.add(created);
        }
        mainWindow.add(textFieldsPanel, gbc);

        gbc.gridy = mainWindow.getCurGridy();
        mainWindow.setCurGridy(gbc.gridy + 1);
        setMajorTickSpacing(1000);
        setMinorTickSpacing(500);
        setPaintTicks(true);
        setPaintLabels(true);
        mainWindow.add(this, gbc);
    }

    public void setCreated(long num) {
        created.setText("Created: " + num);
    }

    public void setInStock(int num) {
        inStock.setText("In stock: " + num);
    }
}

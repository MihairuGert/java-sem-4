package task4.factory.ui;

import task4.factory.FactoryStat;
import task4.factory.ui.slider.FactorySlider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.URL;
import java.util.Objects;

public class MainWindow extends JFrame implements ActionListener {
    private final MainWindowListener mainWindowListener;

    private int curGridy = 1;

    private final FactorySlider engineSlider;
    private final FactorySlider bodySlider;
    private final FactorySlider accessorySlider;

    private final JLabel carsInStock;
    private final JLabel carsCreated;
    private final JLabel carsInQueue;

    private JPanel carNum;

    private Timer timer;

    public MainWindow(MainWindowListener mainWindowListener) {
        this.mainWindowListener = mainWindowListener;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle("Factory");

        URL iconURL = getClass().getResource("/icon.jpg");
        if (iconURL == null) {
            throw new RuntimeException("Cannot find icon.jpg");
        }
        ImageIcon icon = new ImageIcon(iconURL);
        setIconImage(icon.getImage());
        setBounds(500,250, 900, 550);

        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/morkovka.jpg")));
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        });

        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;

        bodySlider = new FactorySlider(gbc, this, "Bodies sup");
        bodySlider.addChangeListener(e -> {
            int value = bodySlider.getValue();
            mainWindowListener.setBodySupplySpeed(value);
        });

        engineSlider = new FactorySlider(gbc, this, "Engine sup");
        engineSlider.addChangeListener(e -> {
            int value = engineSlider.getValue();
            mainWindowListener.setEngineSupplySpeed(value);
        });

        accessorySlider = new FactorySlider(gbc, this, "Accessory sup");
        accessorySlider.addChangeListener(e -> {
            int value = accessorySlider.getValue();
            mainWindowListener.setAccessorySupplySpeed(value);
        });

        FactorySlider dealersSlider = new FactorySlider(gbc, this, "Dealers");
        dealersSlider.addChangeListener(e -> {
            int value = accessorySlider.getValue();
            mainWindowListener.setDealerSpeed(value);
        });

        gbc.gridy = 1;
        gbc.gridx = 2;
        carNum = new JPanel(new GridLayout(1, 5, 5, 10));

        carsInStock = new JLabel("Cars in stock: ");
        carNum.add(carsInStock);
        carsCreated = new JLabel("Cars created: ");
        carNum.add(carsCreated);
        carsInQueue = new JLabel("Cars in queue: ");
        carNum.add(carsInQueue);

        add(carNum, gbc);

        gbc.gridx = 0;

        timer = new Timer(10, this);
        timer.start();

        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                mainWindowListener.mainWindowExit();
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            FactoryStat factoryStat = mainWindowListener.getStat();

            bodySlider.setCreated(factoryStat.bodiesCreated());
            bodySlider.setInStock(factoryStat.bodiesInStock());

            engineSlider.setCreated(factoryStat.enginesCreated());
            engineSlider.setInStock(factoryStat.enginesInStock());

            accessorySlider.setCreated(factoryStat.accessoriesCreated());
            accessorySlider.setInStock(factoryStat.accessoriesInStock());

            carsCreated.setText("Cars created: " + factoryStat.carsCreated());
            carsInQueue.setText("Cars in queue: " + factoryStat.carsInQueue());
            carsInStock.setText("Cars in stock: " + factoryStat.carsInStock());
    }

    public int getCurGridy() {
        return curGridy;
    }

    public void setCurGridy(int curGridy) {
        this.curGridy = curGridy;
    }
}

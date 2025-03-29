package task4.factory.ui;

import task4.factory.FactoryStat;
import task4.factory.ui.buttons.StartButton;
import task4.factory.ui.slider.FactorySlider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Objects;

public class MainWindow extends JFrame implements ActionListener {

    private StartButton startButton;
    private boolean isWorking = false;

    private final MainWindowListener mainWindowListener;

    private int curGridy = 1;

    private FactorySlider engineSlider;
    private FactorySlider bodySlider;
    private FactorySlider accessorySlider;
    private FactorySlider dealersSlider;

    private JLabel carsInStock;
    private JLabel carsCreated;
    private JLabel carsInQueue;

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
        setBounds(500,250, 800, 450);

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
        startButton = new StartButton(this);
        startButton.setOpaque(true);
        add(startButton, gbc);

        bodySlider = new FactorySlider(gbc, this);
        engineSlider = new FactorySlider(gbc, this);
        accessorySlider = new FactorySlider(gbc, this);
        dealersSlider = new FactorySlider(gbc, this);

        gbc.gridy = 0;
        gbc.gridx = 2;
        carNum = new JPanel(new GridLayout(1, 5, 5, 10));

        carsInStock = new JLabel("Cars in stock: ");
        carNum.add(carsInStock);
        carsCreated = new JLabel("Cars created: ");
        carNum.add(carsCreated);
        carsInQueue = new JLabel("Cars in queue: ");
        carNum.add(carsInQueue);

        carNum.add(new JLabel());
        carNum.add(new JLabel());
        add(carNum, gbc);

        gbc.gridx = 0;

        timer = new Timer(100, this);
        timer.start();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            isWorking = !isWorking;
            startButton.setText(isWorking ? "Stop" : "Start");
        } else if (e.getSource() == timer) {
            FactoryStat factoryStat = mainWindowListener.getStat();

            bodySlider.setCreated(factoryStat.bodiesCreated());
            bodySlider.setInStock(factoryStat.bodiesInStock());

            engineSlider.setCreated(factoryStat.enginesCreated());
            engineSlider.setInStock(factoryStat.enginesInStock());

            accessorySlider.setCreated(factoryStat.accessoriesCreated());
            accessorySlider.setInStock(factoryStat.accessoriesInStock());

            carsCreated.setText("Cars created: " + factoryStat.carsCreated());
            carsInQueue.setText("Cars in queue: " + factoryStat.carsInQueue());
            carsInStock.setText("Cars in stock: " + factoryStat.carsCreated());
        }
    }

    public int getCurGridy() {
        return curGridy;
    }

    public void setCurGridy(int curGridy) {
        this.curGridy = curGridy;
    }
}

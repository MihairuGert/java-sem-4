package task4.factory.ui;

import task4.factory.FactoryStat;

public interface MainWindowListener {
    void start();
    FactoryStat getStat();

    void setDealerSpeed(int speed);
    void setBodySupplySpeed(int speed);
    void setEngineSupplySpeed(int speed);
    void setAccessorySupplySpeed(int speed);

    void mainWindowExit();
}

package task3.sound;

import java.net.URL;

public class ShootSound extends Sound{
    public ShootSound() {
        super();
        super.setSoundUrl(ShootSound.class.getResource("/shoot1.wav"));
    }
}

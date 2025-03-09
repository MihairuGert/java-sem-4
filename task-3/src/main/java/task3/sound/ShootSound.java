package task3.sound;

public class ShootSound extends Sound{
    public ShootSound() {
        super();
        super.setSoundUrl(ShootSound.class.getResource("/shoot1.wav"));
    }
}

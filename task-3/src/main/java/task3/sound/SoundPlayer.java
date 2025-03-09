package task3.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class SoundPlayer {
    public static void playShoot() {
        try {
            URL shootURL = SoundPlayer.class.getResource("/shoot1.wav");
            if (shootURL == null) {
                return;
            }

            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(shootURL));
            clip.start();
        } catch (Exception e) {
            System.out.println("Cannot find the file.");
        }
    }
}

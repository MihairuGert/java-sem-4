package task3.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class SoundPlayer {
    public static void playShoot() {
        try {
            Clip clip = AudioSystem.getClip();
            URL shootURL = SoundPlayer.class.getResource("/shoot1.wav");
            clip.open(AudioSystem.getAudioInputStream(new File(shootURL.getPath())));
            clip.start();
        } catch (Exception e) {
            System.out.println("Cannot find the file.");
        }
    }
}

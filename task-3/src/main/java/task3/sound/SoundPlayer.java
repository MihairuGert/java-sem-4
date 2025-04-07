package task3.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class SoundPlayer {
    public void playSound(URL soundURL) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundURL));
            clip.start();
        } catch (Exception e) {
            System.out.println("Cannot find the file.");
        }
    }
}

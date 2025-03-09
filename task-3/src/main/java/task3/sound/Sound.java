package task3.sound;

import java.net.URL;

public class Sound {
    private SoundPlayer soundPlayer;
    private URL soundUrl = null;
    public Sound() {
        soundPlayer = new SoundPlayer();
    }

    public void playSound() {
        if (soundUrl == null) {
            return;
        }
        soundPlayer.playSound(soundUrl);
    }

    public void setSoundUrl(URL soundUrl) {
        this.soundUrl = soundUrl;
    }
}

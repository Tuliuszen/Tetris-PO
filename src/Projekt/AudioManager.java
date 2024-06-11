package Projekt;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class AudioManager {

    private String trackPath = "src/Projekt/music.wav"; // Ensure this path is correct and file exists
    private Clip track;

    public AudioManager() { // Correct constructor name
        try {
            File audioFile = new File(trackPath);

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

            AudioFormat format = audioInputStream.getFormat();

            DataLine.Info info = new DataLine.Info(Clip.class, format);

            track = (Clip) AudioSystem.getLine(info);

            track.open(audioInputStream);
        } catch (LineUnavailableException e) {
            throw new RuntimeException("Line unavailable: " + e.getMessage(), e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException("Unsupported audio file: " + e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException("I/O error: " + e.getMessage(), e);
        }
    }

    public void playBgMusic() {
        if (track != null) {
            track.start();
            Main.bMusic = true;
        } else {
            System.out.println("Track is not initialized.");
        }
    }

    public void stopBgMusic() {
        if (track != null) {
            track.stop();
            Main.bMusic = false;
            track.setFramePosition(0); // Rewind the track to the beginning
        } else {
            System.out.println("Track is not initialized.");
        }
    }

    public static void main(String[] args) {
        // Test AudioManager
        AudioManager audioManager = new AudioManager();
        audioManager.playBgMusic();

        // Add a sleep to allow the music to play for a bit
        try {
            Thread.sleep(5000); // Play for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        audioManager.stopBgMusic();
    }
}

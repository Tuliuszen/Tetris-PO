package Projekt;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class AudioManager {

    //private String trackPath = "src/Projekt/music.mp3";
    //private String trackPath = "Projekt/music.mp3";
    //private String trackPath = "C:\\Users\\Maciek\\IdeaProjects\\Tetris\\out\\production\\Tetris\\Projekt\\music.mp3";

    private String trackPath = "music.wav";

    //String trackPath = "src/Projekt/music.mp3";
    private Clip track;

    public void AudioPlayer()
    {
        try {
            track = AudioSystem.getClip();
            track.open(AudioSystem.getAudioInputStream(new File(trackPath)));
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void playBgMusic()
    {
        track.start();
    }

    public void stopBgMusic()
    {
        track.stop();
    }


    public static void finder() {
        // Specify the project folder path
        String projectFolderPath = "C:\\Users\\Maciek\\IdeaProjects\\Tetris";

        // Call the method to find and print MP3 files
        findMP3Files(projectFolderPath);
    }

    public static void findMP3Files(String folderPath) {
        File folder = new File(folderPath);

        // Check if the path is a directory
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        // Recursive call for subdirectories
                        findMP3Files(file.getAbsolutePath());
                    } else {
                        // Check if the file is an MP3 file
                        if (file.getName().toLowerCase().endsWith(".mp3")) {
                            System.out.println("MP3 File: " + file.getName());
                            System.out.println("Path: " + file.getAbsolutePath());
                        }
                    }
                }
            }
        }
    }

}

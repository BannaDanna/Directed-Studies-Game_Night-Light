package handler.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.InputStream;

public class SoundManager {

    public static void playSound(String filePath)
    {
        InputStream sound;
        try
        {
            File soundPath = new File(filePath);
            if(soundPath.exists())
            {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(soundPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();


            } else {
                System.out.println("No sound found");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}

package handler.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.*;
import java.io.File;
import java.io.InputStream;

public class SoundManager {
    FloatControl fc;
    //-80.0f to 6.0f
    float BGMusicVolume = -30.0f;
    public void playSound(String filePath, float volume)
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
                fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                fc.setValue(volume);
            } else {
                System.out.println("No sound found");
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void playBGMusic(String filePath)
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
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                fc.setValue(BGMusicVolume);
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

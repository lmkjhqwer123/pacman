
import java.net.URL;
import javax.sound.sampled.*;


/* kiểm soát hiệu ứng âm thanh*/
public class GameSounds{
    
    Clip nomNom;
    Clip newGame;
    Clip death;

    boolean stopped;
       

// điều khiển âm thanh trong game
    public GameSounds(){
        stopped=true; 
        URL url;
        AudioInputStream audioIn;
        
        try{
            //âm thanh ăn
            url = this.getClass().getClassLoader().getResource("sounds/nomnom.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            nomNom = AudioSystem.getClip();
            nomNom.open(audioIn);
            
            // âm thanh bắt đầu game     
            url = this.getClass().getClassLoader().getResource("sounds/newGame.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            newGame = AudioSystem.getClip();
            newGame.open(audioIn);
            
            // âm thanh die       
            url = this.getClass().getClassLoader().getResource("sounds/death.wav");
            audioIn = AudioSystem.getAudioInputStream(url);
            death = AudioSystem.getClip();
            death.open(audioIn);

        }catch(Exception e){}
    }
    
    //âm thanh ăn
    public void nomNom(){
        /* If it's already playing, don't start it playing again!*/
        if (!stopped)
          return;

        stopped=false;
        nomNom.stop();
        nomNom.setFramePosition(0);
        nomNom.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // dừng âm thanh ăn 
    public void nomNomStop(){
        stopped=true;
        nomNom.stop();
        nomNom.setFramePosition(0);
    }
    
 //âm thanh bắt đầu game
    public void newGame(){
        newGame.stop();
        newGame.setFramePosition(0);
        newGame.start();
    }
    
    //âm thanh die
    public void death(){
        death.stop();
        death.setFramePosition(0);
        death.start();
    }
}

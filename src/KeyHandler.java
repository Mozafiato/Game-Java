import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyHandler implements KeyListener {
    public boolean upPress, downPress, rightPress, leftPress;
 
    @Override
    public void keyTyped (KeyEvent e){
    }

    @Override
    public void keyPressed (KeyEvent e){ // cuma buat gerakin player, jd klo tombol dipencet jadi true
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            upPress = true;
        }
        if (code == KeyEvent.VK_A){
            leftPress = true;
        }
        if (code == KeyEvent.VK_S){
            downPress = true;
        }
        if (code == KeyEvent.VK_D){
            rightPress = true;
        }
    }

    @Override
    public void keyReleased (KeyEvent e){ // cuma buat gerakin player, jd klo tombol gk dipencet jadi false
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            upPress = false;
        }
        if (code == KeyEvent.VK_A){
            leftPress = false;
        }
        if (code == KeyEvent.VK_S){
            downPress = false;
        }
        if (code == KeyEvent.VK_D){
            rightPress = false;
        }
    }
}

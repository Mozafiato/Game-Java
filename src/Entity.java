import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    gamePanel gp;
    public int x, y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea  = new Rectangle (0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public Entity (gamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int screenX = x - gp.player.x + gp.player.screenX;
        int screenY = y - gp.player.y + gp.player.screenY;

        if(x + gp.tileSize > gp.player.x - gp.player.screenX &&
        x - gp.tileSize < gp.player.x + gp.player.screenX &&
        y + gp.tileSize > gp.player.y - gp.player.screenY &&
        y - gp.tileSize < gp.player.y + gp.player.screenY) {

            switch(direction){
                case "down":
                if(spriteNum ==1){
                    image = left1;
                }
                if(spriteNum ==2){
                    image = right1;
                }
                    break;
            }    
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
}


import java.awt.Graphics2D;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Player extends Entity{
    gamePanel gp;
    KeyHandler keyH;
   

    public Player(gamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){ //import image movement player saja
        try {
            left1 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_4.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_5.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_0.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_1.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_6.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_7.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyH.upPress == true){
            direction = "up";
            y -= speed;
        }
        else if (keyH.leftPress == true){
            direction = "down";
            x -= speed;
        }
        else if (keyH.downPress == true){
            direction = "left";
            y += speed;
        }
        else if (keyH.rightPress == true){
            direction = "right";
            x += speed;
        }
        spriteCounter++;
        if(spriteCounter > 12){ // untuk gerakan nya berganti ganti
            if(spriteNum==1){
                spriteNum=2;
            }
            else if (spriteNum == 2){
                spriteNum=1;
            }
            spriteCounter=0;
        }
    }
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        switch(direction){
            case "up":
            if(spriteNum ==1){
                image = up1;
            }
            if(spriteNum ==2){
                image = up2;
            }
                break;
            case "down":
            if(spriteNum ==1){
                image = down1;
            }
            if(spriteNum ==2){
                image = down2;
            }
                break;
            case "left":
            if(spriteNum ==1){
                image = left1;
            }
            if(spriteNum ==2){
                image = left2;
            }
                break;
            case "right":
            if(spriteNum ==1){
                image = right1;
            }
            if(spriteNum ==2){
                image = right2;
            }
                break;
        }    
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        
    }
}

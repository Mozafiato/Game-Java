import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;


public class Player extends Entity{
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
   

    public Player(gamePanel gp, KeyHandler keyH){

        super(gp);

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(); // bisa di isi x,y, widht. height
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 18;
        solidArea.height = 18;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 350;
        y = 300;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){ //import image movement player saja
        try {
            left1 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_0.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_4.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_5.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_6.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_7.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyH.upPress == true || keyH.downPress == true || keyH.leftPress == true || keyH.rightPress == true){

        if (keyH.upPress == true){
            direction = "up";
        }
        else if (keyH.leftPress == true){
            direction = "left";
        }
        else if (keyH.downPress == true){
            direction = "down";
        }
        else if (keyH.rightPress == true){
            direction = "right";
        }

        //check tile collision
        collisionOn = false;
        gp.Checker.checkTile(this);
        // if collision is false player can move

        if (collisionOn == false){
            switch (direction) {
                
                case "up": y -= speed; break;

                case "down": y += speed; break;

                case "left": x -= speed; break;
                
                case "right": x += speed; break;
            }
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

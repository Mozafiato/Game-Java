import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class Player extends Entity {
    private int attack;
    private String name;
    private int health;
    private gamePanel gp;
    private KeyHandler keyH;
    private int defense;
    private BufferedImage enemyImage;

    public Player(int health, String name, gamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        this.name = name;
        this.attack = 10;
        this.health = health;
        solidArea = new Rectangle(); // bisa di isi x,y, widht. height
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 18;
        solidArea.height = 18;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 350;
        y = 300;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() { //import image movement player saja
        try {
            // Load player images
            // Adjust the paths as needed
            left1 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_0.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_1.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_2.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_3.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_4.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_5.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_6.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Character/sprite_7.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Implement the attack method to handle player's attack
    public void attack(Character opponent) {
        int damage = Math.max(0, this.attack - opponent.defense);
        opponent.takeDamage(damage); // Assuming the opponent has a method to take damage
        System.out.println(this.name + " attacks " + opponent.getName() + " for " + damage + " damage.");
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (!isAlive()) {
            System.out.println("Player is defeated!"); // Or any other appropriate action
        }
    }


    // Getters and setters for health and name
    public int getHealth() {
        return health;
    }

    public void attack(Enemy enemy) {
        int damage = this.attack; // Adjust this based on your damage calculation logic
        enemy.takeDamage(damage);
    }

    public void defend() {
        // Increase defense temporarily when defending
        // For example, increase defense by 50% for 3 turns
        int defenseIncrease = (int) (this.defense * 0.5); // Increase defense by 50%
        int duration = 3; // Number of turns the defense increase lasts

        // Apply defense increase
        this.defense += defenseIncrease;

        // Schedule to revert the defense increase after a certain duration
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Revert the defense increase after the duration
                Player.this.defense -= defenseIncrease;
            }
        }, duration * 1000); // Convert duration to milliseconds (assuming each turn takes 1 second)
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Rectangle getBounds() {
        int spriteWidth = enemyImage.getWidth();
        int spriteHeight = enemyImage.getHeight();

        return new Rectangle(x, y, spriteWidth, spriteHeight); // Assuming x, y, playerWidth, and playerHeight are your player's coordinates and dimensions
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return health > 0;
    }

    // Update player's position and animation
    public void update() {
        if (keyH.upPress || keyH.downPress || keyH.leftPress || keyH.rightPress) {
            if (keyH.upPress) {
                direction = "up";
            } else if (keyH.leftPress) {
                direction = "left";
            } else if (keyH.downPress) {
                direction = "down";
            } else if (keyH.rightPress) {
                direction = "right";
            }

            // Check tile collision
            collisionOn = false;
            gp.Checker.checkTile(this);
            // If collision is false, player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;
                    case "right":
                        x += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) { // untuk gerakan nya berganti ganti
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    // Draw the player on the screen
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}

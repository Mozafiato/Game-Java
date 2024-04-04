
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;



public class TileManager {
    
    gamePanel gp;
    public Tile[] tile;
    public int mapTile[][];

    public TileManager(gamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTile = new int[gp.maxScreenCol][gp.maxScreenRow];//buat ngestore number dari file txt
        getTileImage();
        loadMap("map1.txt");
    }

    public void getTileImage() {

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/floor.png"));   

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Enemy/enemy_9.png"));
            tile[2].collision = true;


        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){ // scan line by line dari map abis tu ditaro var mapTile
        try {
            InputStream input = getClass().getResourceAsStream(filePath);
            BufferedReader buffer = new BufferedReader(new InputStreamReader(input));

            int col =0;
            int row= 0;

            while (col < gp.maxScreenCol && row < gp.maxScreenRow){
                String line = buffer.readLine(); //read line dari map1
                while (col < gp.maxScreenCol){
                    String numbers[] = line.split(" "); //ngambil nomor 1 per 1 dari txt abis tu ditaro di array
                    int num = Integer.parseInt(numbers[col]);
                    mapTile[col][row] = num; // naro num ke variabel mapTile
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
        }
        catch(Exception e){

        }
    }
    public void draw(Graphics2D g2) { //ngegambar tiles

       int col=0;
       int row=0;
       int x=0;
       int y=0;

       while(col < gp.maxScreenCol && row <gp.maxScreenRow){ //jadi dia draw tiles dari kiri kekanan, kalo dah mentok dia bakal turun, dst
            int tileNum = mapTile[col][row];
            g2.drawImage(tile[tileNum].image, x,y, gp.tileSize, gp.tileSize,null);
            col++;
            x += gp.tileSize;

            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y+= gp.tileSize;
            }
       }
    }
}
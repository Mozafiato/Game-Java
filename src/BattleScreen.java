
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;

// import javax.imageio.ImageIO;

// import java.awt.Graphics2D;



// public class BattleScreen {
    
//     gamePanel gp;
//     public Tile[] tile;
//     public int mapTile[][];

//     public BattleScreen(gamePanel gp) {
//         this.gp = gp;
//         tile = new Tile[10];
//         mapTile = new int[gp.maxScreenCol][gp.maxScreenRow];//buat ngestore number dari file txt
//         loadMap("map2.txt");
//     }

//     public void getEnemyImage() {

//         try {
//             tile[2] = new Tile();
//             tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Enemy/enemy_9.png"));
//             tile[2].collision= true;
            

//         }catch (IOException e) {
//             e.printStackTrace();
//         }
//     }

//     public void loadMap(String filePath){ // scan line by line dari map abis tu ditaro var mapTile
//         try {
//             InputStream input = getClass().getResourceAsStream(filePath);
//             BufferedReader buffer = new BufferedReader(new InputStreamReader(input));

//             int col =0;
//             int row= 0;

//             while (col < gp.maxScreenCol && row < gp.maxScreenRow){
//                 String line = buffer.readLine(); //read line dari map1
//                 while (col < gp.maxScreenCol){
//                     String numbers[] = line.split(" "); //ngambil nomor 1 per 1 dari txt abis tu ditaro di array
//                     int num = Integer.parseInt(numbers[col]);
//                     mapTile[col][row] = num; // naro num ke variabel mapTile
//                     col++;
//                 }
//                 if(col == gp.maxScreenCol){
//                     col = 0;
//                     row++;
//                 }
//             }
//         }
//         catch(Exception e){

//         }
//     }
//     public void draw(Graphics2D g2) { //ngegambar tiles

//        int col=0;
//        int row=0;
//        int x=0;
//        int y=0;

//        while(col < gp.maxScreenCol && row <gp.maxScreenRow){ //jadi dia draw tiles dari kiri kekanan, kalo dah mentok dia bakal turun, dst
//             int tileNum = mapTile[col][row];
//             g2.drawImage(tile[tileNum].image, x,y, gp.tileSize, gp.tileSize,null);
//             col++;
//             x += gp.tileSize;

//             if(col == gp.maxScreenCol){
//                 col = 0;
//                 x = 0;
//                 row++;
//                 y+= gp.tileSize;
//             }
//        }
//     }
// }
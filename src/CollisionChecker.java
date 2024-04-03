public class CollisionChecker {
    gamePanel gp;
    public CollisionChecker (gamePanel gp){
        this.gp = gp;
    }
    public void checkTile (Entity entity){
        int entityLeft = entity.x + entity.solidArea.x; //this all coordinates player yg kita buat baru
        int entityRight = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTop = entity.y + entity.solidArea.y;
        int entityBottom = entity.y + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeft/gp.tileSize;
        int entityRightCol = entityRight/gp.tileSize; // buat dapet col row nya
        int entityTopRow = entityTop/gp.tileSize;
        int entityBottomRow = entityBottom/gp.tileSize;

        int tileNum1, tileNum2; // untuk mengecek 2 tiles for each direction jadi misal naik dia bakal cek shoulder kanan kiri ada yang nyentuh collison gk

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTop - entity.speed)/gp.tileSize; //prediksi gerakan player mau kemana
                tileNum1 = gp.tileM.mapTile[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTile[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottom + entity.speed)/gp.tileSize; //prediksi gerakan player mau kemana
                tileNum1 = gp.tileM.mapTile[entityLeftCol][entityBottomRow]; // kotak yang kita bikin ini tu pinggir kiri bwh sama kanan bwh
                tileNum2 = gp.tileM.mapTile[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeft - entity.speed)/gp.tileSize; //prediksi gerakan player mau kemana
                tileNum1 = gp.tileM.mapTile[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTile[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRight + entity.speed)/gp.tileSize; //prediksi gerakan player mau kemana
                tileNum1 = gp.tileM.mapTile[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTile[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisionOn = true;
                }
                break;
        }
        
    }
} 
    


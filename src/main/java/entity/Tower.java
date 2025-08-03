package entity;

import load.*;
import util.CopyUtil;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 魔塔实体类
 */
public final class Tower implements Cloneable, Serializable {
    private Player player;
    // 1帧的贴图
    private HashMap<String, Floor> floorMap;
    private HashMap<String, Wall> wallMap;
    private HashMap<String, Stair> stairMap;
    private HashMap<String, Item> itemMap;
    // 2帧以上的贴图
    private HashMap<String, Door> doorMap;
    private HashMap<String, Monster> monsterMap;
    private HashMap<String, Shop> shopMap;
    private HashMap<String, NPC> npcMap;

    private static final int FLOOR_NUM = 7;
    private Image[] floorImage = new Image[FLOOR_NUM];
    private static final int WALL_NUM = 10;
    private Image[] wallImage = new Image[WALL_NUM];

    // 需要保存的东西
    public boolean hasWeapon;
    public boolean canUseFloorTransfer;
    public boolean canUseMonsterManual;
    public String specialGameMapNo;
    public int floor;

    private List<GameMap> gameMapList;
    private HashMap<String, GameMap> specialMap;
    // 是否挑战额外楼层，即是否挑战血影
    public static boolean specialFloor = false;

    public Tower clone() throws CloneNotSupportedException {
        Tower cloneTower = (Tower) super.clone();
        cloneTower.setPlayer(this.player.clone());
        try {
            cloneTower.gameMapList = CopyUtil.deepCopyList(this.gameMapList);
            cloneTower.specialMap = CopyUtil.deepCopy(this.specialMap);
            cloneTower.doorMap = CopyUtil.deepCopy(this.doorMap);
            cloneTower.monsterMap = CopyUtil.deepCopy(this.monsterMap);
            cloneTower.shopMap = CopyUtil.deepCopy(this.shopMap);
            cloneTower.npcMap = CopyUtil.deepCopy(this.npcMap);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cloneTower;
    }

    public Tower() {
        player = new Player();
        loadIcon();
        // 1帧实体初始化
        wallMap = new LoadWall().initWall();
        itemMap = new LoadItem().initItem();
        stairMap = new LoadStair().initStair();
        // 2帧以上实体初始化
        floorMap = new LoadFloor().initFloor();
        doorMap = new LoadDoor().initDoor();
        monsterMap = new LoadMonster().initMonster();
        npcMap = new LoadNPC().initNPC();
        shopMap = new LoadShop().initShop();
        // 地图初始化
        gameMapList = new LoadMap().initMap();
        specialMap = new LoadSpecialMap().initSpecialMap();
    }

    private void loadIcon() {
        ImageIcon icon;
        for (int i = 1; i <= FLOOR_NUM; i++) {
            icon = new ImageIcon(getClass().getResource("/image/wall/floor" + String.format("%0" + 2 + "d", i) + "_1.png"));
            // 地板
            floorImage[i - 1] = icon.getImage();
        }
        for (int i = 1; i <= WALL_NUM; i++) {
            icon = new ImageIcon(getClass().getResource("/image/wall/wall" + String.format("%0" + 2 + "d", i) + "_1.png"));
            // 墙体
            wallImage[i - 1] = icon.getImage();
        }
        // 玩家图标
        ImageIcon[][] playerIcon = new ImageIcon[4][4];
        for (int i = 0; i < 4; i++) {
            // 帧数从1-4
            for (int j = 1; j <= 4; j++) {
                playerIcon[i][j - 1] = new ImageIcon(getClass().getResource("/image/player/player03_" + i + "_" + j + ".png"));
            }
        }
        player.setPlayerIcon(playerIcon);
    }

    public Image[] getFloorImage() {
        return floorImage;
    }

    public Image[] getWallImage() {
        return wallImage;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Map<String, Monster> getMonsterMap() {
        return monsterMap;
    }

    public Map<String, Floor> getFloorMap() {
        return floorMap;
    }

    public Map<String, Wall> getWallMap() {
        return wallMap;
    }

    public Map<String, Door> getDoorMap() {
        return doorMap;
    }

    public Map<String, Stair> getStairMap() {
        return stairMap;
    }

    public Map<String, Item> getItemMap() {
        return itemMap;
    }

    public Map<String, NPC> getNpcMap() {
        return npcMap;
    }

    public Map<String, Shop> getShopMap() {
        return shopMap;
    }

    public List<GameMap> getGameMapList() {
        return gameMapList;
    }

    public Map<String, GameMap> getSpecialMap() {
        return specialMap;
    }
}

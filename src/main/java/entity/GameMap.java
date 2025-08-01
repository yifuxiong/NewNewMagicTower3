package entity;

import java.io.Serializable;

/**
 * 游戏地图类
 */
public final class GameMap implements Cloneable, Serializable {
    /**
     * 楼层
     */
    public String floor;

    /**
     * 玩家上到这层，人物出现的位置，X为宽，Y为高
     */
    public byte upPositionX;
    public byte upPositionY;

    /**
     * 玩家下到这层，任务出现的位置
     */
    public byte downPositionX;
    public byte downPositionY;

    /**
     * 上图层：玩家player，怪物monster，NPC，商店shop
     */
    public String[][] layer1;

    /**
     * 中图层：道具item
     */
    public String[][] layer2;

    /**
     * 下图层：门door，墙wall，楼梯stair
     */
    public String[][] layer3;

    public GameMap(int floor, String[][] layer1, String[][] layer2, String[][] layer3) {
        this.floor = String.valueOf(floor);
        this.layer1 = layer1;
        this.layer2 = layer2;
        this.layer3 = layer3;
    }

    public GameMap(String floor, String[][] layer1, String[][] layer2, String[][] layer3) {
        this.floor = floor;
        this.layer1 = layer1;
        this.layer2 = layer2;
        this.layer3 = layer3;
    }
}

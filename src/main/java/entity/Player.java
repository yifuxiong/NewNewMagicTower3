package entity;

import lombok.Data;
import util.CopyUtil;

import javax.swing.ImageIcon;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 玩家类
 */
@Data
public final class Player implements Cloneable {
    // 基础属性
    public String name;
    public int hp;
    public int attack;
    public int defense;
    // 新增暴击
    public int critical;
    // 新增敏捷
    public int agility;
    // 新增闪避
    public int dodgo;
    // 新增攻击次数
    public int attackTimes;
    // 新增特殊能力：反伤、吸血
    public int ability;
    public int exp;
    public int money;
    public int level;
    // 钥匙
    public int yKey;
    public int bKey;
    public int rKey;
    // 上楼器属性
    public int maxFloor;
    public int minFloor;
    // 当前位置
    public byte x;
    public byte y;
    // 上一步位置
    public byte lastX;
    public byte lastY;
    // 游戏结算信息
    public int killNum;
    public int killBossNum;     // 击杀boss总数
    public int killBoss1Num;    // 击杀boss1（21层吸血鬼）数量，最高为1
    public int killBoss2Num;    // 击杀boss2（血影）数量，最高为9
    public int killBoss3Num;    // 击杀boss3（魔龙）数量，最高为9
    public int stepNum;
    public long startPlayTime;
    private Integer playerScore;
    // 持有特殊道具信息
    public HashMap<String, Integer> inventory;

    /**
     * 不需要父类的icon，[方向][帧数]
     */
    ImageIcon[][] playerIcon;

    public Player() {
        this.name = "勇士";
        this.hp = 2000;
        this.attack = 10;
        this.defense = 10;
        this.exp = 0;
        this.money = 0;
        this.level = 1;
        this.yKey = 0;
        this.bKey = 0;
        this.rKey = 0;
        this.maxFloor = 0;
        this.minFloor = 0;
        this.x = 0;
        this.y = 0;
        this.lastX = 0;
        this.lastY = 0;
        this.killNum = 0;
        this.killBossNum = 0;
        this.killBoss1Num = 0;
        this.killBoss2Num = 0;
        this.killBoss3Num = 0;
        this.stepNum = 0;
        this.inventory = new HashMap<>();
        this.playerIcon = new ImageIcon[4][4];
        //this.startPlayTime = UploadScore.getNetworkTime();
    }

    public ImageIcon[][] getPlayerIcon() {
        return playerIcon;
    }

    public void setPlayerIcon(ImageIcon[][] playerIcon) {
        this.playerIcon = playerIcon;
    }

    public Player clone() throws CloneNotSupportedException {
        Player clonePlayer = (Player) super.clone();
        try {
            clonePlayer.setInventory(CopyUtil.deepCopy(this.inventory));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clonePlayer;
    }

    public void calculateScore() {
        this.killBossNum = this.killBoss1Num + this.killBoss2Num + this.killBoss3Num;
        double score = hp * 0.01 + attack * 1.8 + defense * 1.8 + exp / 3 + money * 0.3 + yKey * 3 + bKey * 15 + rKey * 30;
        score = score * (1 + 0.1 * this.killBoss1Num + 0.2 * this.killBoss2Num + 0.5 * this.killBoss3Num);
        this.playerScore = (int) score;
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + name + '\'' + ", hp=" + hp + ", attack=" + attack + ", defense=" + defense + ", exp=" + exp + ", money=" + money + ", level=" + level + ", yKey=" + yKey + ", bKey=" + bKey + ", rKey=" + rKey + ", maxFloor=" + maxFloor + ", minFloor=" + minFloor + ", x=" + x + ", y=" + y + ", inventory=" + inventory + ", playerIcon=" + Arrays.toString(playerIcon) + '}';
    }
}

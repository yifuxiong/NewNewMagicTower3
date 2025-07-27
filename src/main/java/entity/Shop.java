package entity;

import javax.swing.ImageIcon;
import java.io.Serializable;

/**
 * 商店类
 */
public final class Shop extends Entity implements Cloneable, Serializable {
    // 交换什么的商店：金币类，经验类，售卖钥匙，回收钥匙
    public String need;
    // 价格
    public int price;
    // 数量
    public int useNum;
    // 对话内容
    public String dialogue;
    //
    public boolean canMeet;
    // 售卖商品
    public Sell sell;

    // 1帧的商店：贪婪之神
    public Shop(String id, String name, String need, int price, boolean canMeet, ImageIcon imageIcon1) {
        this.id = id;
        this.name = name;
        this.need = need;
        this.price = price;
        this.canMeet = canMeet;
        this.icon = new ImageIcon[1];
        this.icon[0] = imageIcon1;
    }

    // 2帧的商店：老者，奸商，小偷
    public Shop(String id, String name, String need, int price, boolean canMeet, String dialogue, Sell sell, ImageIcon imageIcon1, ImageIcon imageIcon2) {
        this.id = id;
        this.name = name;
        this.need = need;
        this.price = price;
        this.canMeet = canMeet;
        this.dialogue = dialogue;
        this.sell = sell;
        this.icon = new ImageIcon[2];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
    }
}

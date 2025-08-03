package load;

import entity.Item;

import javax.swing.ImageIcon;
import java.util.HashMap;

/**
 * 道具加载类
 */
public final class LoadItem {
    public HashMap<String, Item> initItem() {
        HashMap<String, Item> itemMap = new HashMap<>(64);

        Item item = new Item("item01_1", "黄钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_2", "蓝钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_3", "红钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_4", "绿钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_5", "万用钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_5.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_6", "万用钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_6.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_7", "万用钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_7.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_8", "万用钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_8.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_9", "生锈的钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_9.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item01_10", "神秘钥匙", null,
                new ImageIcon(getClass().getResource("/image/item/item01_10.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item02_1", "红宝石", null,
                new ImageIcon(getClass().getResource("/image/item/item02_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item02_2", "蓝宝石", null,
                new ImageIcon(getClass().getResource("/image/item/item02_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item02_3", "绿宝石", null,
                new ImageIcon(getClass().getResource("/image/item/item02_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item02_4", "黄宝石", null,
                new ImageIcon(getClass().getResource("/image/item/item02_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item03_1", "小体力药水", null,
                new ImageIcon(getClass().getResource("/image/item/item03_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item03_2", "大体力药水", null,
                new ImageIcon(getClass().getResource("/image/item/item03_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item03_3", "经验药水", null,
                new ImageIcon(getClass().getResource("/image/item/item03_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item03_4", "力量药水", null,
                new ImageIcon(getClass().getResource("/image/item/item03_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item04_1", "铁剑", null,
                new ImageIcon(getClass().getResource("/image/item/item04_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item04_2", "银剑", null,
                new ImageIcon(getClass().getResource("/image/item/item04_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item04_3", "骑士剑", null,
                new ImageIcon(getClass().getResource("/image/item/item04_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item04_4", "圣剑", null,
                new ImageIcon(getClass().getResource("/image/item/item04_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item04_5", "神圣剑", null,
                new ImageIcon(getClass().getResource("/image/item/item04_5.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item05_1", "铁盾", null,
                new ImageIcon(getClass().getResource("/image/item/item05_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item05_2", "银盾", null,
                new ImageIcon(getClass().getResource("/image/item/item05_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item05_3", "骑士盾", null,
                new ImageIcon(getClass().getResource("/image/item/item05_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item05_4", "圣盾", null,
                new ImageIcon(getClass().getResource("/image/item/item05_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item05_5", "神圣盾", null,
                new ImageIcon(getClass().getResource("/image/item/item05_5.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item06_1", "解毒药水", null,
                new ImageIcon(getClass().getResource("/image/item/item06_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item06_2", "解衰药水", null,
                new ImageIcon(getClass().getResource("/image/item/item06_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item06_3", "圣水", "将自身的生命值增加一倍",
                new ImageIcon(getClass().getResource("/image/item/item06_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item06_4", "山泉", null,
                new ImageIcon(getClass().getResource("/image/item/item06_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item06_5", "玉琼", null,
                new ImageIcon(getClass().getResource("/image/item/item06_5.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item06_6", "甘露", null,
                new ImageIcon(getClass().getResource("/image/item/item06_6.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item07_1", "贤者之证", null,
                new ImageIcon(getClass().getResource("/image/item/item07_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item07_2", "霸者之证", null,
                new ImageIcon(getClass().getResource("/image/item/item07_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item07_3", "勇者之证", null,
                new ImageIcon(getClass().getResource("/image/item/item07_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item07_4", "红色法杖", null,
                new ImageIcon(getClass().getResource("/image/item/item07_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item07_5", "黄金法杖", null,
                new ImageIcon(getClass().getResource("/image/item/item07_5.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item07_6", "蓝色法杖", null,
                new ImageIcon(getClass().getResource("/image/item/item07_6.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item07_7", "紫色法杖", null,
                new ImageIcon(getClass().getResource("/image/item/item07_7.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item08_1", "小飞羽", null,
                new ImageIcon(getClass().getResource("/image/item/item08_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item08_2", "红色飞羽", null,
                new ImageIcon(getClass().getResource("/image/item/item08_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item08_3", "金色飞羽", null,
                new ImageIcon(getClass().getResource("/image/item/item08_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_1", "幸运金币", null,
                new ImageIcon(getClass().getResource("/image/item/item09_1.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_2", "银币", null,
                new ImageIcon(getClass().getResource("/image/item/item09_2.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_3", "防御药剂", null,
                new ImageIcon(getClass().getResource("/image/item/item09_3.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_4", "风之罗盘", "按\bF\b键在已经走过的楼层间跳跃",
                new ImageIcon(getClass().getResource("/image/item/item09_4.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_5", "十字架", "把他交给仙子，可以提升自身的能力",
                new ImageIcon(getClass().getResource("/image/item/item09_5.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_6", "怪物手册", "按\bD\b键查看怪物的基本信息",
                new ImageIcon(getClass().getResource("/image/item/item09_6.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_7", "破墙镐", null,
                new ImageIcon(getClass().getResource("/image/item/item09_7.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_8", "星光神锒", "把它交给小偷，用来打通18层的楼梯",
                new ImageIcon(getClass().getResource("/image/item/item09_8.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_9", "雪花", "可以将熔岩变为普通地面",
                new ImageIcon(getClass().getResource("/image/item/item09_9.png")));
        itemMap.put(item.getId(), item);

        item = new Item("item09_10", "屠龙匕首", "和魔龙作战时造成双倍伤害",
                new ImageIcon(getClass().getResource("/image/item/item09_10.png")));
        itemMap.put(item.getId(), item);

        return itemMap;
    }
}

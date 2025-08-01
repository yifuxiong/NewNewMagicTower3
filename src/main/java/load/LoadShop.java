package load;

import entity.Sell;
import entity.Shop;

import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 商店加载类
 */
public final class LoadShop {
    public HashMap<String, Shop> initShop() {
        HashMap<String, Shop> shopMap = new HashMap<>(16);

        /************************************************** 贪婪之神 初级 **************************************************/

        // 贪婪之神左肩膀
        Shop shop = new Shop("shop01_1", "贪婪之神", "money", 25, false,
                new ImageIcon(getClass().getResource("/image/shop/shop01_1_1.png")));
        shopMap.put(shop.getId(), shop);

        // 贪婪之神右肩膀
        shop = new Shop("shop01_3", "贪婪之神", "money", 25, false,
                new ImageIcon(getClass().getResource("/image/shop/shop01_3_1.png")));
        shopMap.put(shop.getId(), shop);

        List<String> name = new ArrayList<>(4);
        name.add("增加800点生命值");
        name.add("增加4点攻击");
        name.add("增加4点防御");
        name.add("离开商店");

        List<String> attribute = new ArrayList<>(3);
        attribute.add("hp");
        attribute.add("attack");
        attribute.add("defense");

        List<Short> val = new ArrayList<>(3);
        val.add(new Short((short) 800));
        val.add(new Short((short) 4));
        val.add(new Short((short) 4));

        Sell sell = new Sell(name, attribute, val);

        // 贪婪之神中间部位
        shop = new Shop("shop01_2", "贪婪之神", "money", 25, true, "\b人类啊,如果你愿意给我%%个金币的话,我可以提升你的战斗力!", sell,
                new ImageIcon(getClass().getResource("/image/shop/shop01_2_1.png")),
                new ImageIcon(getClass().getResource("/image/shop/shop01_2_2.png")));
        shopMap.put(shop.getId(), shop);

        /************************************************** 贪婪之神 高级 **************************************************/

        shop = new Shop("shop02_1", "贪欲之神", "money", 100, false,
                new ImageIcon(getClass().getResource("/image/shop/shop02_1_1.png")));
        shopMap.put(shop.getId(), shop);

        shop = new Shop("shop02_3", "贪欲之神", "money", 100, false,
                new ImageIcon(getClass().getResource("/image/shop/shop02_3_1.png")));
        shopMap.put(shop.getId(), shop);

        name = new ArrayList<>(4);
        name.add("增加4000点生命值");
        name.add("增加20点攻击");
        name.add("增加20点防御");
        name.add("离开商店");

        attribute = new ArrayList<>(3);
        attribute.add("hp");
        attribute.add("attack");
        attribute.add("defense");

        val = new ArrayList<>(3);
        val.add(new Short((short) 4000));
        val.add(new Short((short) 20));
        val.add(new Short((short) 20));

        sell = new Sell(name, attribute, val);

        shop = new Shop("shop02_2", "贪欲之神", "money", 100, true, "\b人类啊,如果你愿意给我%%个金币的话,我可以提升你的战斗力!", sell,
                new ImageIcon(getClass().getResource("/image/shop/shop02_2_1.png")),
                new ImageIcon(getClass().getResource("/image/shop/shop02_2_2.png")));
        shopMap.put(shop.getId(), shop);

        /************************************************** 经验老人 初级 **************************************************/

        name = new ArrayList<>(4);
        name.add("等级提升1级(需要100点)");
        name.add("增加5点攻击(需要30点)");
        name.add("增加5点防御(需要30点)");
        name.add("离开商店");

        attribute = new ArrayList<>(3);
        attribute.add("lv");
        attribute.add("attack");
        attribute.add("defense");

        val = new ArrayList<>(3);
        val.add(new Short((short) 1));
        val.add(new Short((short) 5));
        val.add(new Short((short) 5));

        List<Integer> priceList = new ArrayList<>(3);
        priceList.add(100);
        priceList.add(30);
        priceList.add(30);

        sell = new Sell(name, attribute, val, priceList);

        shop = new Shop("shop03_1", "神秘老人", "exp", 0, true, "\b勇士,只要你有足够的经验,我就可以让你变得更强大!", sell,
                new ImageIcon(getClass().getResource("/image/shop/shop03_1.png")),
                new ImageIcon(getClass().getResource("/image/shop/shop03_2.png")));
        shopMap.put(shop.getId(), shop);

        /************************************************** 经验老人 高级 **************************************************/

        name = new ArrayList<>(4);
        name.add("等级提升3级(需要270点)");
        name.add("增加17点攻击(需要95点)");
        name.add("增加17点防御(需要95点)");
        name.add("离开商店");

        attribute = new ArrayList<>(3);
        attribute.add("lv");
        attribute.add("attack");
        attribute.add("defense");

        val = new ArrayList<>(3);
        val.add(new Short((short) 3));
        val.add(new Short((short) 17));
        val.add(new Short((short) 17));

        priceList = new ArrayList<>(3);
        priceList.add(270);
        priceList.add(95);
        priceList.add(95);

        sell = new Sell(name, attribute, val, priceList);

        shop = new Shop("shop06_1", "神秘老人", "exp", 0, true, "\b勇士,只要你有足够的经验,我就可以让你变得更强大!", sell,
                new ImageIcon(getClass().getResource("/image/shop/shop03_1.png")),
                new ImageIcon(getClass().getResource("/image/shop/shop03_2.png")));
        shopMap.put(shop.getId(), shop);

        /************************************************** 钥匙出售商人 **************************************************/

        name = new ArrayList<>(4);
        name.add("购买1把黄钥匙($10)");
        name.add("购买1把蓝钥匙($50)");
        name.add("购买1把红钥匙($100)");
        name.add("离开商店");

        attribute = new ArrayList<>(3);
        attribute.add("yKey");
        attribute.add("bKey");
        attribute.add("rKey");

        val = new ArrayList<>(3);
        val.add(new Short((short) 1));
        val.add(new Short((short) 1));
        val.add(new Short((short) 1));

        priceList = new ArrayList<>(3);
        priceList.add(10);
        priceList.add(50);
        priceList.add(100);

        sell = new Sell(name, attribute, val, priceList);

        shop = new Shop("shop04_1", "钥匙商人", "money", 0, true, "\b相信你一定有特殊需要,只要你有金币,我就可以帮你!", sell,
                new ImageIcon(getClass().getResource("/image/shop/shop04_1.png")),
                new ImageIcon(getClass().getResource("/image/shop/shop04_2.png")));
        shopMap.put(shop.getId(), shop);

        /************************************************** 钥匙回收商人 **************************************************/

        name = new ArrayList<>(4);
        name.add("卖出1把黄钥匙($7)");
        name.add("卖出1把蓝钥匙($35)");
        name.add("卖出1把红钥匙($70)");
        name.add("离开商店");

        attribute = new ArrayList<>(3);
        attribute.add("yKey");
        attribute.add("bKey");
        attribute.add("rKey");

        val = new ArrayList<>(3);
        val.add(new Short((short) 1));
        val.add(new Short((short) 1));
        val.add(new Short((short) 1));

        priceList = new ArrayList<>(3);
        priceList.add(7);
        priceList.add(35);
        priceList.add(70);

        sell = new Sell(name, attribute, val, priceList);

        shop = new Shop("shop05_1", "钥匙商人", "item", 0, true, "\b嘿,欢迎你的到来,如果你手里缺少金币,我可以帮你!", sell,
                new ImageIcon(getClass().getResource("/image/shop/shop04_1.png")),
                new ImageIcon(getClass().getResource("/image/shop/shop04_2.png")));
        shopMap.put(shop.getId(), shop);

        /************************************************** 小偷杰克 **************************************************/

        return shopMap;
    }
}

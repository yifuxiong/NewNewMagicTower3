package pane;

import entity.Shop;
import entity.Tower;
import main.TowerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import static main.TowerPanel.CS;

/**
 * 商店绘制类
 */
public class ShopPane extends JPanel {
    public static JLayeredPane shopPane = new JLayeredPane();
    private static JPanel showPanel;
    private static JTextArea shopContent;
    private static JLabel selectLabel;
    static byte nowSelected = 0;

    // 背景贴图
    private static ImageIcon background;

    // 对话框size
    private static final int PANEL_SIZE_I = 7;
    private static final int PANEL_SIZE_J = 8;
    // 排布
    private static final int WIDTH = 80;
    private static final int HEIGHT = 25;
    private static final int MARGIN = 30;
    private static final int LINE_BOUND = 2;
    // 选择箭头大小
    private static final int SELECT_LABEL_SIZE = 30;
    // 字体设置
    private static final String FONT_FAMILY = "宋体";
    private static final int FONT_SIZE = 20;
    private static final int SMALL_SIZE = 16;

    public ShopPane() {
        background = new ImageIcon(ShopPane.class.getResource("/image/wall/floor01_1.png"));
        background.setImage(background.getImage().getScaledInstance(CS, CS, Image.SCALE_DEFAULT));
        // 商店主对界面
        shopPane.setBounds(CS * 8 - LINE_BOUND, CS * 2 - LINE_BOUND, CS * PANEL_SIZE_I + LINE_BOUND * 2, CS * PANEL_SIZE_J + LINE_BOUND * 2);
        shopPane.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 204), LINE_BOUND));
        shopPane.setVisible(false);
    }

    public static void showShop(Tower tower, String shopId) {
        TowerPanel.CAN_MOVE = false;
        shopPane.removeAll();
        Shop shop = tower.getShopMap().get(shopId);
        init(shop);
        // 必须将监听器设置给shopContent，否则可能出现无法响应Key的情况
        shopContent.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {
            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyPressed(KeyEvent arg0) {
                Shop shop = tower.getShopMap().get(shopId);
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        TowerPanel.input.clear();
                        if (nowSelected <= 0) {
                            break;
                        }
                        TowerPanel.musicPlayer.shopSelect();
                        nowSelected--;
                        selectLabel.setBounds(MARGIN, MARGIN * 6 + SELECT_LABEL_SIZE * nowSelected, SELECT_LABEL_SIZE, SELECT_LABEL_SIZE);
                        break;
                    case KeyEvent.VK_DOWN:
                        TowerPanel.input.clear();
                        if (nowSelected >= 3) {
                            break;
                        }
                        TowerPanel.musicPlayer.shopSelect();
                        nowSelected++;
                        selectLabel.setBounds(MARGIN, MARGIN * 6 + SELECT_LABEL_SIZE * nowSelected, SELECT_LABEL_SIZE, SELECT_LABEL_SIZE);
                        break;
                    case KeyEvent.VK_ENTER:
                        if (shop.sell.name.get(nowSelected).contains("离开")) {
                            shopPane.removeKeyListener(this);
                            shopPane.setVisible(false);
                            TowerPanel.musicPlayer.upAndDown();
                            TowerPanel.CAN_MOVE = true;
                            TowerPanel.input.clear();
                            nowSelected = 0;
                            break;
                        }
                        short price;
                        if (shop.price != 0) {
                            price = (short) shop.price;
                        } else {
                            price = Short.valueOf(shop.sell.price.get(nowSelected).toString());
                        }
                        if (shop.need.equals("money")) {
                            buyItemByMoney(tower, shop, price);
                        } else if (shop.need.equals("exp")) {
                            buyItemByExp(tower, shop, price);
                        } else if (shop.need.equals("item")) {
                            sellItem(tower, shop);
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        shopPane.removeKeyListener(this);
                        shopPane.setVisible(false);
                        TowerPanel.musicPlayer.upAndDown();
                        TowerPanel.CAN_MOVE = true;
                        TowerPanel.input.clear();
                        nowSelected = 0;
                        break;
                }
            }
        });
        shopPane.add(showPanel);
        shopPane.setVisible(true);
        shopPane.repaint();
        shopContent.requestFocus();
    }

    private static void init(Shop shop) {
        showPanel = new ShopPane();
        showPanel.setLayout(null);
        showPanel.setBounds(LINE_BOUND, LINE_BOUND, CS * PANEL_SIZE_I, CS * PANEL_SIZE_J);

        ImageIcon photo = new ImageIcon(shop.getIcon()[0].getImage());
        photo.setImage(shop.getIcon()[0].getImage().getScaledInstance(CS, CS, Image.SCALE_DEFAULT));
        JLabel shopImg = new JLabel();
        shopImg.setBounds(MARGIN, MARGIN, TowerPanel.CS, TowerPanel.CS);
        shopImg.setBorder(BorderFactory.createLineBorder(new Color(0, 155, 207), LINE_BOUND));
        shopImg.setIcon(photo);

        JLabel name = new JLabel();
        name.setText(shop.getName());
        name.setBounds(MARGIN * 4, MARGIN, WIDTH * 2, HEIGHT);
        name.setFont(new Font(FONT_FAMILY, Font.BOLD, SMALL_SIZE));
        name.setForeground(Color.white);

        shopContent = new JTextArea();
        shopContent.setText(shop.dialogue.replaceFirst("%%", String.valueOf(shop.price)));
        shopContent.setBounds(MARGIN * 2, MARGIN * 5 / 2, WIDTH * 5 / 2, HEIGHT * 3);
        // 自动换行
        shopContent.setLineWrap(true);
        shopContent.setEditable(false);
        // 禁用功能不透明
        shopContent.setOpaque(false);
        // 设置背景完全透明
        shopContent.setBackground(new Color(0, 0, 0, 0));
        shopContent.setFont(new Font(FONT_FAMILY, Font.BOLD, SMALL_SIZE));
        shopContent.setForeground(Color.white);

        // 选择箭头
        selectLabel = new JLabel();
        selectLabel.setIcon(new ImageIcon(ShopPane.class.getResource("/image/icon/selected.png")));
        // 初始位置和大小
        selectLabel.setBounds(MARGIN, MARGIN * 6, SELECT_LABEL_SIZE, SELECT_LABEL_SIZE);
        selectLabel.setForeground(Color.white);

        showPanel.add(shopImg);
        showPanel.add(name);
        showPanel.add(shopContent);
        showPanel.add(selectLabel);

        // 选项
        for (int i = 0; i < shop.sell.name.size(); i++) {
            JLabel label = new JLabel(shop.sell.name.get(i), JLabel.CENTER);
            label.setBounds(MARGIN, MARGIN * 6 + SELECT_LABEL_SIZE * i, WIDTH * 3, SELECT_LABEL_SIZE);
            label.setForeground(Color.white);
            label.setFont(new Font(FONT_FAMILY, Font.BOLD, SMALL_SIZE));
            showPanel.add(label);
        }
    }

    // 金币商店
    private static boolean buyItemByMoney(Tower tower, Shop shop, int price) {
        if (tower.getPlayer().money >= price) {
            TowerPanel.musicPlayer.shopBuySuc();
            tower.getPlayer().money -= price;
            shop.useNum++;
            shopContent.setText(shop.dialogue.replaceFirst("%%", String.valueOf(shop.price)));
            java.util.List<String> attributeList = shop.sell.attribute;
            java.util.List<Short> valList = shop.sell.val;
            if (attributeList.get(nowSelected).contains("hp")) {
                tower.getPlayer().hp += valList.get(nowSelected);
            } else if (attributeList.get(nowSelected).contains("attack")) {
                tower.getPlayer().attack += valList.get(nowSelected);
            } else if (attributeList.get(nowSelected).contains("defense")) {
                tower.getPlayer().defense += valList.get(nowSelected);
            } else if (attributeList.get(nowSelected).contains("yKey")) {
                tower.getPlayer().yKey += valList.get(nowSelected);
            } else if (attributeList.get(nowSelected).contains("bKey")) {
                tower.getPlayer().bKey += valList.get(nowSelected);
            } else if (attributeList.get(nowSelected).contains("rKey")) {
                tower.getPlayer().rKey += valList.get(nowSelected);
            }
            return true;
        } else {
            TowerPanel.musicPlayer.shopBuyFail();
            return false;
        }
    }

    // 经验商店
    private static boolean buyItemByExp(Tower tower, Shop shop, int price) {
        if (tower.getPlayer().exp >= price) {
            TowerPanel.musicPlayer.shopExpBuySuc();
            tower.getPlayer().exp -= price;
            shop.useNum++;
            shopContent.setText(shop.dialogue.replaceFirst("%%", String.valueOf(shop.price)));
            java.util.List<String> attributeList = shop.sell.attribute;
            java.util.List<Short> valList = shop.sell.val;
            if (attributeList.get(nowSelected).contains("lv")) {
                int var = valList.get(nowSelected);
                tower.getPlayer().level += var;
                tower.getPlayer().hp += 1000 * var;
                tower.getPlayer().attack += 7 * var;
                tower.getPlayer().defense += 7 * var;
            } else if (attributeList.get(nowSelected).contains("attack")) {
                tower.getPlayer().attack += valList.get(nowSelected);
            } else if (attributeList.get(nowSelected).contains("defense")) {
                tower.getPlayer().defense += valList.get(nowSelected);
            }
            return true;
        } else {
            TowerPanel.musicPlayer.shopBuyFail();
            return false;
        }
    }

    // 出售钥匙商店
    private static boolean sellItem(Tower tower, Shop shop) {
        boolean sell = false;
        List<String> attributeList = shop.sell.attribute;
        if (attributeList.get(nowSelected).contains("yKey")) {
            if (tower.getPlayer().yKey >= shop.sell.val.get(nowSelected)) {
                tower.getPlayer().yKey -= shop.sell.val.get(nowSelected);
                tower.getPlayer().money += shop.sell.price.get(nowSelected);
                sell = true;
            }
        } else if (attributeList.get(nowSelected).contains("bKey")) {
            if (tower.getPlayer().bKey >= shop.sell.val.get(nowSelected)) {
                tower.getPlayer().bKey -= shop.sell.val.get(nowSelected);
                tower.getPlayer().money += shop.sell.price.get(nowSelected);
                sell = true;
            }
        } else if (attributeList.get(nowSelected).contains("rKey")) {
            if (tower.getPlayer().rKey >= shop.sell.val.get(nowSelected)) {
                tower.getPlayer().rKey -= shop.sell.val.get(nowSelected);
                tower.getPlayer().money += shop.sell.price.get(nowSelected);
                sell = true;
            }
        }
        if (sell) {
            shop.useNum++;
            TowerPanel.musicPlayer.shopBuySuc();
            return true;
        } else {
            TowerPanel.musicPlayer.shopBuyFail();
            return false;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 构造背景
        for (int i = 0; i < PANEL_SIZE_I; i++) {
            for (int j = 0; j < PANEL_SIZE_J; j++) {
                g.drawImage(background.getImage(), i * CS, j * CS, CS, CS, this);
            }
        }
        // 绘制一条蓝色线条
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(0, 155, 207));
        g2d.setStroke(new BasicStroke(LINE_BOUND));
        g2d.drawLine(0, CS * 4, CS * 8, CS * 4);
    }
}

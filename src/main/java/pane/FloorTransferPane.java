package pane;

import entity.Tower;
import main.TowerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.TowerPanel.CS;

/**
 * 楼层传送绘制类
 */
public final class FloorTransferPane {
    public static JLayeredPane floorTransferPane = new JLayeredPane();
    // 楼层数字
    private static JLabel floorNoLabel;
    // 下楼键
    private static JLabel downPicLabel;
    // 上楼键
    private static JLabel upPicLabel;
    private static JPanel showPanel;

    // 背景图片
    private static JLabel backgroundLabel;

    static {
        // 怪物展示时的背景图片
        ImageIcon bgIcon = new ImageIcon(MonsterManualPane.class.getResource("/image/icon/background_s_grey.png"));
        backgroundLabel = new JLabel(bgIcon);
        backgroundLabel.setBounds(0, 0, CS * 11, CS * 11);
        floorTransferPane.setBounds(TowerPanel.CS * 6, TowerPanel.CS, TowerPanel.CS * 11, TowerPanel.CS * 11);
        floorTransferPane.setBackground(Color.black);
    }

    public static void showFloorTransfer(Tower tower) {
        if ((TowerPanel.nowSelectFloor = TowerPanel.floor) < 0) {
            TowerPanel.nowSelectFloor = 0;
        }
        TowerPanel.CAN_MOVE = false;
        floorTransferPane.removeAll();
        init(tower);
        floorTransferPane.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {
            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyPressed(KeyEvent arg0) {
                boolean closeFlag = false;
                boolean changeFlag = false;
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                    case KeyEvent.VK_F:
                        closeFlag = true;
                        break;
                    case KeyEvent.VK_UP:
                        if (TowerPanel.nowSelectFloor + 1 > tower.getPlayer().maxFloor) {
                            break;
                        }
                        TowerPanel.musicPlayer.floorTransferSelect();
                        TowerPanel.nowSelectFloor++;
                        changeFlag = true;
                        break;
                    case KeyEvent.VK_DOWN:
                        if (TowerPanel.nowSelectFloor - 1 < tower.getPlayer().minFloor) {
                            break;
                        }
                        TowerPanel.musicPlayer.floorTransferSelect();
                        TowerPanel.nowSelectFloor--;
                        changeFlag = true;
                        break;
                    case KeyEvent.VK_ENTER:
                        closeFlag = true;
                        TowerPanel.musicPlayer.upAndDown();
                        if (TowerPanel.floor < TowerPanel.nowSelectFloor) {
                            tower.getPlayer().x = tower.getGameMapList().get(TowerPanel.nowSelectFloor).upPositionX;
                            tower.getPlayer().y = tower.getGameMapList().get(TowerPanel.nowSelectFloor).upPositionY;
                            // 飞行至新楼层之后上一步的位置重置（防止在楼梯口不停切换）
                            tower.getPlayer().lastX = tower.getPlayer().x;
                            tower.getPlayer().lastY = tower.getPlayer().y;
                            TowerPanel.DIRECTION = TowerPanel.DIRECTION_DOWN;
                            TowerPanel.musicPlayer.playBackgroundMusic(TowerPanel.nowSelectFloor);
                            TowerPanel.nowMonsterManual = 0;
                        } else if (TowerPanel.floor > TowerPanel.nowSelectFloor) {
                            tower.getPlayer().x = tower.getGameMapList().get(TowerPanel.nowSelectFloor).downPositionX;
                            tower.getPlayer().y = tower.getGameMapList().get(TowerPanel.nowSelectFloor).downPositionY;
                            // 飞行至新楼层之后上一步的位置重置（防止在楼梯口不停切换）
                            tower.getPlayer().lastX = tower.getPlayer().x;
                            tower.getPlayer().lastY = tower.getPlayer().y;
                            TowerPanel.DIRECTION = TowerPanel.DIRECTION_DOWN;
                            TowerPanel.musicPlayer.playBackgroundMusic(TowerPanel.nowSelectFloor);
                            TowerPanel.nowMonsterManual = 0;
                        }
                        TowerPanel.floor = TowerPanel.nowSelectFloor;
                        TowerPanel.updateFloorNum();
                        break;
                    default:
                        return;
                }
                if (closeFlag) {
                    floorTransferPane.removeKeyListener(this);
                    floorTransferPane.setVisible(false);
                    TowerPanel.CAN_MOVE = true;
                    TowerPanel.input.clear();
                }
                if (changeFlag) {
                    update(tower);
                }
            }
        });
        floorTransferPane.add(showPanel);
        floorTransferPane.setVisible(true);
        floorTransferPane.requestFocus();
        floorTransferPane.repaint();
    }

    private static final int WIDTH = 100;
    private static final int HEIGHT = 80;
    private static final int LINE_BOUND = 5;
    private static final String FONT_FAMILY = "微软雅黑";
    private static final int FONT_SIZE = 52;
    private static final int SMALL_SIZE = 20;

    private static void init(Tower tower) {
        showPanel = new JPanel(null);
        showPanel.setSize(TowerPanel.CS * 11, TowerPanel.CS * 11);
        showPanel.setBackground(Color.black);

        JLabel mainLabel = new JLabel();
        mainLabel.setBounds(CS * 3 / 2 + 5, CS * 9 / 2 + 5, WIDTH * 3 + 10, HEIGHT + 10);
        mainLabel.setForeground(Color.white);
        mainLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 204), LINE_BOUND));

        floorNoLabel = new JLabel(String.valueOf(TowerPanel.nowSelectFloor), JLabel.CENTER);
        floorNoLabel.setBounds(CS * 5 / 2, LINE_BOUND, WIDTH, HEIGHT);
        floorNoLabel.setForeground(Color.white);
        floorNoLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

        JLabel floorLabel = new JLabel("F", JLabel.CENTER);
        floorLabel.setBounds(CS * 9 / 2, LINE_BOUND, WIDTH, HEIGHT);
        floorLabel.setForeground(Color.white);
        floorLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

        upPicLabel = new JLabel();
        if (TowerPanel.nowSelectFloor + 1 <= tower.getPlayer().maxFloor) {
            upPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/up_1.png")));
        } else {
            upPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/up_2.png")));
        }
        upPicLabel.setBounds(CS * 5, CS * 3 + 10, TowerPanel.CS, TowerPanel.CS);
        upPicLabel.setForeground(Color.white);

        downPicLabel = new JLabel();
        if (TowerPanel.nowSelectFloor - 1 >= tower.getPlayer().minFloor) {
            downPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/down_1.png")));
        } else {
            downPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/down_2.png")));
        }
        downPicLabel.setBounds(CS * 5, CS * 7 + 10, TowerPanel.CS, TowerPanel.CS);
        downPicLabel.setForeground(Color.white);

        JLabel enterLabel = new JLabel("-Enter-", JLabel.CENTER);
        enterLabel.setBounds(CS * 7, CS * 7 + 10, WIDTH, HEIGHT / 2);
        enterLabel.setForeground(Color.white);
        enterLabel.setFont(new Font(FONT_FAMILY, Font.PLAIN, SMALL_SIZE));

        JLabel quitLabel = new JLabel("-Quit(F)-", JLabel.CENTER);
        quitLabel.setBounds(CS * 17 / 2, CS * 10, WIDTH, HEIGHT / 2);
        quitLabel.setForeground(Color.white);
        quitLabel.setFont(new Font(FONT_FAMILY, Font.PLAIN, SMALL_SIZE));

        mainLabel.add(floorNoLabel);
        mainLabel.add(floorLabel);

        showPanel.add(mainLabel);
        showPanel.add(upPicLabel);
        showPanel.add(downPicLabel);
        showPanel.add(enterLabel);
        showPanel.add(quitLabel);
        showPanel.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        showPanel.repaint();
    }

    private static void update(Tower tower) {
        floorNoLabel.setText(String.valueOf(TowerPanel.nowSelectFloor));
        if (TowerPanel.nowSelectFloor - 1 >= tower.getPlayer().minFloor) {
            downPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/down_1.png")));
        } else {
            downPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/down_2.png")));
        }
        if (TowerPanel.nowSelectFloor + 1 <= tower.getPlayer().maxFloor) {
            upPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/up_1.png")));
        } else {
            upPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/up_2.png")));
        }
    }
}

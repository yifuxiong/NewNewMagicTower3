package pane;

import entity.Item;
import main.TowerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.TowerPanel.CS;

/**
 * 特殊物品提示绘制类
 */
public final class SpecialItemPane extends JPanel{
    public static JLayeredPane specialItemPane = new JLayeredPane();
    private static JPanel showPanel;

    // 背景贴图
    private static ImageIcon background;

    // 对话框size
    private static final int PANEL_SIZE_I = 13;
    private static final int PANEL_SIZE_J = 1;
    // 排布
    private static final int WIDTH = 100;
    private static final int HEIGHT = 25;
    private static final int BIAS = 20;
    private static final int LINE_BOUND = 3;
    // 字体设置
    private static final String FONT_FAMILY = "宋体";
    private static final int FONT_SIZE = 18;

    public SpecialItemPane(){
        background = new ImageIcon(ShopPane.class.getResource("/image/wall/floor01_1.png"));
        background.setImage(background.getImage().getScaledInstance(CS, CS, Image.SCALE_DEFAULT));
        // 提示框主界面
        specialItemPane.setBounds(CS * 4 + BIAS - LINE_BOUND, CS * 6 - LINE_BOUND, CS * PANEL_SIZE_I + LINE_BOUND * 2, CS * PANEL_SIZE_J + LINE_BOUND * 2);
        specialItemPane.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 204), LINE_BOUND));
        specialItemPane.setVisible(false);
    }

    public static void showSpecialItem(Item item) {
        TowerPanel.CAN_MOVE = false;
        specialItemPane.removeAll();

        showPanel = new SpecialItemPane();
        showPanel.setLayout(null);
        showPanel.setBounds(LINE_BOUND, LINE_BOUND, CS * PANEL_SIZE_I, CS * PANEL_SIZE_J);

        JLabel tip = new JLabel("取得", JLabel.CENTER);
        tip.setBounds(0, 10, WIDTH / 2, HEIGHT);
        tip.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));
        tip.setForeground(Color.white);

        JLabel name = new JLabel(item.getName(), JLabel.CENTER);
        name.setBounds(WIDTH / 2, 10, WIDTH, HEIGHT);
        name.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));
        name.setForeground(Color.white);

        JTextArea content = new JTextArea();
        content.setText(item.msg);
        // 自动换行
        content.setLineWrap(true);
        content.setEditable(false);
        // 禁用功能不透明
        content.setOpaque(false);
        // 设置背景完全透明
        content.setBackground(new Color(0, 0, 0, 0));
        content.setBounds(WIDTH * 3 / 2, 10, WIDTH * 3, HEIGHT);
        content.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));
        content.setForeground(Color.WHITE);

        JLabel enterLabel = new JLabel("-Enter-", JLabel.CENTER);
        enterLabel.setBounds(CS * 11, 10, WIDTH, HEIGHT);
        enterLabel.setFont(new Font("微软雅黑", Font.BOLD, FONT_SIZE));
        enterLabel.setForeground(Color.white);

        showPanel.add(tip);
        showPanel.add(name);
        showPanel.add(content);
        showPanel.add(enterLabel);
        content.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {
            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyPressed(KeyEvent arg0) {
                boolean close = false;
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                    case KeyEvent.VK_ENTER:
                    case KeyEvent.VK_ESCAPE:
                        close = true;
                        break;
                }
                if (close) {
                    specialItemPane.removeKeyListener(this);
                    specialItemPane.setVisible(false);
                    TowerPanel.musicPlayer.close();
                    TowerPanel.input.clear();
                    TowerPanel.CAN_MOVE = true;
                }
            }
        });
        specialItemPane.add(showPanel);
        specialItemPane.setVisible(true);
        specialItemPane.repaint();
        content.requestFocus();
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
    }
}

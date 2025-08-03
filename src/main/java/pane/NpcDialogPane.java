package pane;

import entity.Dialogue;
import entity.NPC;
import entity.Tower;
import main.TowerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static main.TowerPanel.CS;

/**
 * NPC对话框绘制类
 */
public class NpcDialogPane extends JPanel {
    public static JLayeredPane npcDialogPane = new JLayeredPane();
    private static JPanel showPanel;
    private static JLabel name;
    private static JTextArea content;
    private static JLabel picture;

    // 排布和字体设置
    private static final int WIDTH = 80;
    private static final int HEIGHT = 25;
    private static final int MARGIN = 15;
    private static final int LINE_BOUND = 3;
    private static final String FONT_FAMILY = "宋体";
    private static final int FONT_SIZE = 16;
    private static final int SMALL_SIZE = 14;

    // NPC头像和背景框
    private static ImageIcon background;
    private static JLabel backgroundLabel;

    // 秘籍彩蛋
    private static String secretScript = "";
    private static byte nowDialog = 0;
    private static int dialogNum = 0;

    public NpcDialogPane() {
        background = new ImageIcon(NpcDialogPane.class.getResource("/image/wall/floor01_1.png"));
        background.setImage(background.getImage().getScaledInstance(CS, CS, Image.SCALE_DEFAULT));
        backgroundLabel = new JLabel();
        // backgroundLabel.setIcon(background);
        backgroundLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 155, 207), LINE_BOUND));
        // 主对话框设置
        npcDialogPane.setBounds(CS * 15 / 2 - LINE_BOUND, CS * 11 / 2 - LINE_BOUND, CS * 8 + LINE_BOUND * 2, CS * 4 + LINE_BOUND * 2);
        npcDialogPane.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 204), LINE_BOUND));
        npcDialogPane.setVisible(false);
    }

    public static void showNpcDialog(Tower tower, String npcId, Byte x, Byte y) {
        TowerPanel.CAN_MOVE = false;
        NPC npc;
        try {
            npc = tower.getNpcMap().get(npcId);
        } catch (Exception e) {
            System.err.println("layer1 (x=" + y + ",y=" + x + ") npcId(" + npcId + ") 不存在!");
            TowerPanel.CAN_MOVE = true;
            return;
        }
        npc.script_start(tower);
        //重新获取一边,以防npc改变而这里没变
        npc = tower.getNpcMap().get(npcId);
        if (!npc.canMeet) {
            TowerPanel.CAN_MOVE = true;
            return;
        }
        npcDialogPane.removeAll();
        init(npc, tower.getPlayer().getPlayerIcon()[1][0].getImage());
        NPC finalNpc = npc;
        // 必须将监听器设置给content，否则可能出现无法响应Key的情况
        content.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {
            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyPressed(KeyEvent arg0) {
                boolean close = false;
                boolean next = false;
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                    case KeyEvent.VK_ENTER:
                        if (dialogNum <= ++nowDialog) {
                            close = true;
                        } else {
                            next = true;
                        }
                        TowerPanel.musicPlayer.dialogueSpace();
                        break;
                }
                if (finalNpc.getName().equals("奇怪的人")) {
                    switch (arg0.getKeyCode()) {
                        case KeyEvent.VK_UP:
                            secretScript += "8";
                            break;
                        case KeyEvent.VK_DOWN:
                            secretScript += "2";
                            break;
                        case KeyEvent.VK_LEFT:
                            secretScript += "4";
                            break;
                        case KeyEvent.VK_RIGHT:
                            secretScript += "6";
                            break;
                        case KeyEvent.VK_A:
                            secretScript += "A";
                            break;
                        case KeyEvent.VK_B:
                            secretScript += "B";
                            break;
                    }
                    if (secretScript.equals("88224646BABA")) {
                        close = true;
                        tower.getGameMapList().get(0).layer3[10][5] = "stair03_1";
                        tower.getGameMapList().get(1).layer1[9][6] = "";
                    }
                }
                if (next) {
                    updateDialog(finalNpc, tower.getPlayer().getPlayerIcon()[1][0].getImage());
                } else if (close) {
                    secretScript = "";
                    nowDialog = 0;
                    dialogNum = 0;
                    npcDialogPane.removeKeyListener(this);
                    npcDialogPane.setVisible(false);
                    TowerPanel.CAN_MOVE = true;
                    TowerPanel.input.clear();
                    if (finalNpc.canRemove && !(x == null || y == null)) {
                        if (TowerPanel.isNormalFloor()) {
                            tower.getGameMapList().get(TowerPanel.floor).layer1[y][x] = "";
                        } else {
                            tower.getSpecialMap().get(TowerPanel.specialGameMapNo).layer1[y][x] = "";
                        }
                    }
                    finalNpc.script_end(tower);
                    if (npcId.equals("npc06_2_3") && !tower.specialFloor) {
                        //TODO towerPanel.over();    结局1
                        TowerPanel.end = 1;
                        TowerPanel.RUNNING = false;
                    } else if (npcId.equals("npc07_1_2")) {
                        //TODO towerPanel.over();    结局2
                        TowerPanel.end = 2;
                        TowerPanel.RUNNING = false;
                    } else if (npcId.equals("npc07_2_2")) {
                        //TODO towerPanel.over();    结局3
                        TowerPanel.RUNNING = false;
                        TowerPanel.end = 3;
                    }
                }
            }
        });
        npcDialogPane.add(showPanel);
        npcDialogPane.setVisible(true);
        npcDialogPane.repaint();
        content.requestFocus();
    }

    private static void init(NPC npc, Image playerImg) {
        dialogNum = npc.dialogues.size();

        showPanel = new NpcDialogPane();
        showPanel.setLayout(null);
        showPanel.setBounds(LINE_BOUND, LINE_BOUND, CS * 8, CS * 4);

        name = new JLabel();
        name.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));
        name.setForeground(Color.white);

        // 对话框内容
        content = new JTextArea();
        content.setBounds(CS * 2, CS + 10, WIDTH * 5 / 2, HEIGHT * 3 + 10);
        // 自动换行
        content.setLineWrap(true);
        content.setEditable(false);
        // 禁用功能不透明
        content.setOpaque(false);
        // 设置背景完全透明
        content.setBackground(new Color(0, 0, 0, 0));
        content.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));
        content.setForeground(Color.white);

        JLabel enterLabel = new JLabel("-Enter-");
        enterLabel.setBounds(CS * 6 + 10, CS * 3 + 10, WIDTH, HEIGHT);
        enterLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, SMALL_SIZE));
        enterLabel.setForeground(Color.white);

        picture = new JLabel();
        updateDialog(npc, playerImg);
        showPanel.add(name);
        showPanel.add(content);
        showPanel.add(enterLabel);
        showPanel.add(picture);
        showPanel.add(backgroundLabel);
    }

    private static void updateDialog(NPC npc, Image playerImg) {
        Dialogue dialogue = npc.dialogues.get(nowDialog);
        content.setText(dialogue.text);
        ImageIcon photo;
        if (dialogue.name.contains("player")) {
            photo = new ImageIcon(playerImg);
            photo.setImage(playerImg.getScaledInstance(CS, CS, Image.SCALE_DEFAULT));
            // 这里可以调整头像和背景的位置
            picture.setBounds(MARGIN, MARGIN, CS, CS);
            backgroundLabel.setBounds(MARGIN - LINE_BOUND, MARGIN - LINE_BOUND, CS + LINE_BOUND * 2, CS + LINE_BOUND * 2);
            name.setText("勇士");
            name.setBounds(CS * 4, MARGIN, WIDTH, HEIGHT);
            // System.out.println("勇士:\n" + dialogue.text);
        } else {
            photo = new ImageIcon(npc.getIcon()[0].getImage());
            photo.setImage(npc.getIcon()[0].getImage().getScaledInstance(CS, CS, Image.SCALE_DEFAULT));
            // 这里可以调整头像和背景的位置
            picture.setBounds(MARGIN, MARGIN, CS, CS);
            backgroundLabel.setBounds(MARGIN - LINE_BOUND, MARGIN - LINE_BOUND, CS + LINE_BOUND * 2, CS + LINE_BOUND * 2);
            name.setText(npc.getName());
            name.setBounds(CS * 4, MARGIN, WIDTH, HEIGHT);
            // System.out.println(npc.getName() + ":\n" + dialogue.text);
        }
        picture.setIcon(photo);
    }

    // 描绘窗体，此处在默认JPanel基础上构建底层地图
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 构造背景
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                g.drawImage(background.getImage(), i * CS, j * CS, CS, CS, this);
            }
        }
    }
}

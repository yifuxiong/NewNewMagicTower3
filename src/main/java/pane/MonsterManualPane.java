package pane;

import entity.Monster;
import entity.Tower;
import main.TowerPanel;
import util.FightCalc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static main.TowerPanel.CS;

/**
 * 怪物手册绘制类
 */
public final class MonsterManualPane extends JPanel {
    // 界面
    public static JLayeredPane monsterManualPane = new JLayeredPane();
    // 怪物展示页面
    private static JPanel showPanel;
    // 背景图片
    private static JLabel bgLabel;

    // 每页显示怪物数量
    private static final int Mon3tNumPerPage = 3;

    // 设置
    private static final int FONT_SIZE = 16;
    private static final String FONT_FAMILY = "微软雅黑";
    private static final int WIDTH = 80;
    private static final int HEIGHT = 20;
    private static final int LINE_THICKNESS = 2;

    static {
        // 怪物展示时的背景图片
        ImageIcon bgIcon = new ImageIcon(MonsterManualPane.class.getResource("/image/icon/background_s_grey.png"));
        bgLabel = new JLabel(bgIcon);
        bgLabel.setBounds(0, 0, CS * 11, CS * 11);
        monsterManualPane.setBounds(CS * 6, CS, CS * 11, CS * 11);
        monsterManualPane.setBackground(Color.white);
    }

    public static void showMonsterManual(Tower tower) {
        List<FightCalc> fightCalcList = calculate(tower);
        if (fightCalcList.size() == 0) {
            return;
        }
        TowerPanel.CAN_MOVE = false;
        monsterManualPane.removeAll();
        showPanel = new JPanel(null);
        showPanel.setSize(CS * 11, CS * 11);
        update(fightCalcList, tower.getFloorImage()[0]);
        monsterManualPane.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent arg0) {
            }

            public void keyReleased(KeyEvent arg0) {
            }

            public void keyPressed(KeyEvent arg0) {
                boolean closeFlag = false;
                boolean changeFlag = false;
                switch (arg0.getKeyCode()) {
                    case KeyEvent.VK_D:
                    case KeyEvent.VK_SPACE:
                    case KeyEvent.VK_ENTER:
                    case KeyEvent.VK_ESCAPE:
                        closeFlag = true;
                        break;
                    case KeyEvent.VK_LEFT:
                        if (TowerPanel.nowMonsterManual > 0) {
                            TowerPanel.musicPlayer.mon3tManualSelect();
                            TowerPanel.nowMonsterManual--;
                            changeFlag = true;
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (TowerPanel.nowMonsterManual < (fightCalcList.size() + Mon3tNumPerPage - 1) / Mon3tNumPerPage - 1) {
                            TowerPanel.musicPlayer.mon3tManualSelect();
                            TowerPanel.nowMonsterManual++;
                            changeFlag = true;
                        }
                        break;
                    default:
                        return;
                }
                if (closeFlag) {
                    monsterManualPane.removeKeyListener(this);
                    monsterManualPane.setVisible(false);
                    TowerPanel.CAN_MOVE = true;
                    TowerPanel.input.clear();
                }
                if (changeFlag) {
                    update(fightCalcList, tower.getFloorImage()[0]);
                }
            }
        });
        monsterManualPane.add(showPanel);
        monsterManualPane.setVisible(true);
        monsterManualPane.requestFocus();
        monsterManualPane.repaint();
    }

    private static void update(List<FightCalc> fightCalcList, Image floorImage) {
        showPanel.removeAll();

        for (int i = TowerPanel.nowMonsterManual * Mon3tNumPerPage, length = fightCalcList.size();
             i < length && i < (TowerPanel.nowMonsterManual + 1) * Mon3tNumPerPage; i++) {
            Monster monster = fightCalcList.get(i).getMonster();

            /************************************************** 手册主页面 **************************************************/

            JLabel mainLabel = new JLabel();
            mainLabel.setBounds(10, 10 + (CS * 3) * (i % Mon3tNumPerPage), CS * 10, CS * 3);
            mainLabel.setForeground(Color.white);
            mainLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            // 怪物名称
            JLabel nameLabel = new JLabel("名称：", JLabel.LEFT);
            nameLabel.setBounds(20, 10, WIDTH - 20, HEIGHT);
            nameLabel.setForeground(Color.white);
            nameLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            JLabel nameValLabel = new JLabel(monster.getName(), JLabel.LEFT);
            nameValLabel.setBounds(20 + (WIDTH - 20), 10, 20 + WIDTH, HEIGHT);
            nameValLabel.setForeground(Color.white);
            nameValLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            // 特殊能力
            JLabel abilityLabel = new JLabel("特殊：", JLabel.LEFT);
            abilityLabel.setBounds(180, 10, WIDTH - 20, HEIGHT);
            abilityLabel.setForeground(Color.white);
            abilityLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            JLabel abilityValLabel = new JLabel("无", JLabel.LEFT);
            abilityValLabel.setBounds(180 + (WIDTH - 20), 10, 20 + WIDTH, HEIGHT);
            abilityValLabel.setForeground(Color.white);
            abilityValLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            // 怪物头像
            ImageIcon mon3tFigure = new ImageIcon();
            mon3tFigure.setImage(monster.getIcon()[0].getImage().getScaledInstance(CS, CS, Image.SCALE_DEFAULT));
            JLabel picLabel = new JLabel();
            picLabel.setBounds(20 + LINE_THICKNESS, 30 + LINE_THICKNESS, CS, CS);
            picLabel.setIcon(mon3tFigure);
            // 头像后面的背景图片
            ImageIcon background = new ImageIcon(floorImage);
            background.setImage(background.getImage().getScaledInstance(CS, CS, Image.SCALE_DEFAULT));
            JLabel backgroundLabel = new JLabel();
            backgroundLabel.setIcon(background);
            backgroundLabel.setBounds(20, 30, CS + LINE_THICKNESS * 2, CS + LINE_THICKNESS * 2);
            backgroundLabel.setBorder(BorderFactory.createLineBorder(new Color(0, 155, 207), LINE_THICKNESS));

            // 怪物体力
            JLabel hpLabel = new JLabel("体力：", JLabel.LEFT);
            hpLabel.setBounds(20 + WIDTH, 30, WIDTH, HEIGHT);
            hpLabel.setForeground(Color.white);
            hpLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            JLabel hpValLabel = new JLabel(String.valueOf(monster.getHp()), JLabel.LEFT);
            hpValLabel.setBounds(20 + WIDTH, 50, WIDTH, HEIGHT);
            hpValLabel.setForeground(new Color(109, 232, 5));
            hpValLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            // 怪物攻击力
            JLabel attackLabel = new JLabel("攻击力：", JLabel.LEFT);
            attackLabel.setBounds(20 + WIDTH * 2, 30, WIDTH, HEIGHT);
            attackLabel.setForeground(Color.white);
            attackLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            JLabel attackValLabel = new JLabel(String.valueOf(monster.getAttack()), JLabel.LEFT);
            attackValLabel.setBounds(20 + WIDTH * 2, 50, WIDTH, HEIGHT);
            attackValLabel.setForeground(new Color(123, 60, 118));
            attackValLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            // 怪物防御力
            JLabel defenseLabel = new JLabel("防御力：", JLabel.LEFT);
            defenseLabel.setBounds(20 + WIDTH * 3, 30, WIDTH, HEIGHT);
            defenseLabel.setForeground(Color.white);
            defenseLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            JLabel defenseValLabel = new JLabel(String.valueOf(monster.getDefense()), JLabel.LEFT);
            defenseValLabel.setBounds(20 + WIDTH * 3, 50, WIDTH, HEIGHT);
            defenseValLabel.setForeground(new Color(231, 164, 123));
            defenseValLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            // 怪物敏捷
            JLabel agilityLabel = new JLabel("敏捷：", JLabel.LEFT);
            agilityLabel.setBounds(20 + WIDTH * 4, 30, WIDTH, HEIGHT);
            agilityLabel.setForeground(Color.white);
            agilityLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            JLabel agilityValLabel = new JLabel(String.valueOf(1), JLabel.LEFT);
            agilityValLabel.setBounds(20 + WIDTH * 4, 50, WIDTH, HEIGHT);
            agilityValLabel.setForeground(new Color(37, 136, 40));
            agilityValLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            // 攻击次数
            JLabel attackTimesLabel = new JLabel("攻击次数：", JLabel.LEFT);
            attackTimesLabel.setBounds(20 + WIDTH, 70, WIDTH, HEIGHT);
            attackTimesLabel.setForeground(new Color(14, 146, 192));
            attackTimesLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            JLabel attackTimesValLabel = new JLabel(String.valueOf(1), JLabel.LEFT);
            attackTimesValLabel.setBounds(20 + WIDTH, 90, WIDTH, HEIGHT);
            attackTimesValLabel.setForeground(Color.white);
            attackTimesValLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            // 造成损失
            JLabel damageLabel = new JLabel("估计损失：", JLabel.LEFT);
            damageLabel.setBounds(20 + WIDTH * 2, 70, WIDTH, HEIGHT);
            damageLabel.setForeground(Color.white);
            damageLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            JLabel damageValLabel = new JLabel("", JLabel.LEFT);
            if (!fightCalcList.get(i).canAttack) {
                damageValLabel.setText("???");
            } else {
                if (fightCalcList.get(i).canWin) {
                    damageValLabel.setText(String.valueOf(fightCalcList.get(i).mDamageTotal));
                } else {
                    damageValLabel.setText("DIE");
                }
            }
            damageValLabel.setBounds(20 + WIDTH * 2, 90, WIDTH, HEIGHT);
            damageValLabel.setForeground(new Color(244, 7, 27));
            damageValLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            // 怪物经验值
            JLabel expLabel = new JLabel("Exp：", JLabel.LEFT);
            expLabel.setBounds(20 + WIDTH * 3, 70, WIDTH, HEIGHT);
            expLabel.setForeground(Color.white);
            expLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            JLabel expValLabel = new JLabel(String.valueOf(monster.getExp()), JLabel.LEFT);
            expValLabel.setBounds(20 + WIDTH * 3, 90, WIDTH, HEIGHT);
            expValLabel.setForeground(new Color(139, 238, 233));
            expValLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            // 怪物金币数
            JLabel goldLabel = new JLabel("Gold：", JLabel.LEFT);
            goldLabel.setBounds(20 + WIDTH * 4, 70, WIDTH, HEIGHT);
            goldLabel.setForeground(Color.white);
            goldLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            JLabel goldValLabel = new JLabel(String.valueOf(monster.getMoney()), JLabel.LEFT);
            goldValLabel.setBounds(20 + WIDTH * 4, 90, WIDTH, HEIGHT);
            goldValLabel.setForeground(new Color(249, 251, 69));
            goldValLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE));

            /************************************************** 翻页按钮 **************************************************/

            JLabel leftPicLabel = new JLabel();
            if (TowerPanel.nowMonsterManual == 0) {
                leftPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/left_2.png")));
            } else {
                leftPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/left_1.png")));
            }
            leftPicLabel.setBounds(CS * 8 + 20, HEIGHT * 17 + 10, CS, CS * 3 / 2);
            leftPicLabel.setForeground(Color.WHITE);

            JLabel rightPicLabel = new JLabel();
            if (TowerPanel.nowMonsterManual == (length + Mon3tNumPerPage - 1) / Mon3tNumPerPage - 1) {
                rightPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/right_2.png")));
            } else {
                rightPicLabel.setIcon(new ImageIcon(FloorTransferPane.class.getResource("/image/icon/right_1.png")));
            }
            rightPicLabel.setBounds(CS * 9 + 20, HEIGHT * 17 + 10, CS, CS * 3 / 2);
            rightPicLabel.setForeground(Color.WHITE);

            JLabel enterLabel = new JLabel("-Enter-", JLabel.CENTER);
            enterLabel.setBounds(CS * 8 + 20, HEIGHT * 19, WIDTH, CS * 3 / 2);
            enterLabel.setForeground(Color.GRAY);
            enterLabel.setFont(new Font(FONT_FAMILY, Font.PLAIN, 20));

            /************************************************** 添加到画板 **************************************************/

            mainLabel.add(nameLabel);
            mainLabel.add(nameValLabel);
            mainLabel.add(abilityLabel);
            mainLabel.add(abilityValLabel);
            mainLabel.add(picLabel);
            mainLabel.add(backgroundLabel);
            mainLabel.add(hpLabel);
            mainLabel.add(hpValLabel);
            mainLabel.add(attackLabel);
            mainLabel.add(attackValLabel);
            mainLabel.add(defenseLabel);
            mainLabel.add(defenseValLabel);
            mainLabel.add(agilityLabel);
            mainLabel.add(agilityValLabel);
            mainLabel.add(attackTimesLabel);
            mainLabel.add(attackTimesValLabel);
            mainLabel.add(damageLabel);
            mainLabel.add(damageValLabel);
            mainLabel.add(expLabel);
            mainLabel.add(expValLabel);
            mainLabel.add(goldLabel);
            mainLabel.add(goldValLabel);
            showPanel.add(mainLabel);
            showPanel.add(leftPicLabel);
            showPanel.add(rightPicLabel);
            showPanel.add(enterLabel);
        }
        showPanel.add(bgLabel, JLayeredPane.DEFAULT_LAYER);
        showPanel.repaint();
    }

    private static List<FightCalc> calculate(Tower tower) {
        //System.out.println("开始计算");
        String[][] monsterLayer;
        if (TowerPanel.isNormalFloor()) {
            monsterLayer = tower.getGameMapList().get(TowerPanel.floor).layer1;
        } else {
            monsterLayer = tower.getSpecialMap().get(TowerPanel.specialGameMapNo).layer1;
        }
        Set<String> monsterIdSet = new HashSet<>();
        //y
        for (int i = 0; i < monsterLayer.length; i++) {
            //x
            for (int j = 0; j < monsterLayer[i].length; j++) {
                if (monsterLayer[j][i] != null && monsterLayer[j][i].contains("monster")) {
                    monsterIdSet.add(monsterLayer[j][i]);
                }
            }
        }
        List<FightCalc> fightCalcList = new ArrayList<>();
        List<FightCalc> dieAttackList = new ArrayList<>();
        //血影和魔龙只需计算一次
        boolean monster11 = false, monster12 = false;
        for (String monsterId : monsterIdSet) {
            Monster monster = tower.getMonsterMap().get(monsterId);
            if (monster.getId().contains("monster11")) {
                if (monster11) {
                    continue;
                }
                monster = tower.getMonsterMap().get("monster11_8");
                monster11 = true;
            } else if (monster.getId().contains("monster12")) {
                if (monster12) {
                    continue;
                }
                monster = tower.getMonsterMap().get("monster12_8");
                monster12 = true;
            }
            FightCalc fightCalc = new FightCalc(tower.getPlayer(), monster);
            int no = 0;
            if (fightCalc.canWin) {
                for (int j = 0; j < fightCalcList.size(); j++) {
                    if (fightCalcList.get(j).mDamageTotal >= fightCalc.mDamageTotal) {
                        no = j;
                        break;
                    }
                    if (j == fightCalcList.size() - 1) {
                        no = fightCalcList.size();
                        break;
                    }
                }
                fightCalcList.add(no, fightCalc);
            } else {
                for (int j = 0; j < dieAttackList.size(); j++) {
                    if (dieAttackList.get(j).getMonster().getAttack() >= fightCalc.getMonster().getAttack()) {
                        no = j;
                        break;
                    }
                    if (j == dieAttackList.size() - 1) {
                        no = dieAttackList.size();
                        break;
                    }
                }
                dieAttackList.add(no, fightCalc);
            }
        }
        fightCalcList.addAll(dieAttackList);
        return fightCalcList;
    }
}

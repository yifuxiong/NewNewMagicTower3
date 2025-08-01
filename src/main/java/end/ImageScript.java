package end;

import main.TowerPanel;
import util.ImageUtil;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;

/**
 * 游戏结束，左侧滑动图片
 */
public class ImageScript {
    /**
     * 游戏结束字幕左侧图片显示脚本，需要在线程中调用
     *
     * @param towerPanel 游戏窗体
     */
    public static void imageScript(TowerPanel towerPanel) {
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ImageUtil imageUtil = new ImageUtil();
        JLabel label = new JLabel();
        label.setForeground(Color.white);
        label.setBounds(10, 140, 226, 162);
        label.setVisible(true);
        towerPanel.add(label);
        for (int i = 1; i <= 9; i++) {
            for (int j = 0; j <= 80; j++) {
                label.setIcon(new ImageIcon(imageUtil.changeAlpha("/image/gameImage/image" + i + ".png", j)));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j <= 100; j++) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int j = 0; j <= 80; j++) {
                label.setIcon(new ImageIcon(imageUtil.changeAlpha("/image/gameImage/image" + i + ".png", 80 - j)));
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

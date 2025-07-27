package entity;

import javax.swing.ImageIcon;

/**
 * 地板类
 */
public class Floor extends Entity {
    // 1帧的地板
    public Floor(String id, ImageIcon imageIcon) {
        this.id = id;
        this.icon = new ImageIcon[1];
        this.icon[0] = imageIcon;
    }

    // 2帧的地板
    public Floor(String id, ImageIcon imageIcon1, ImageIcon imageIcon2) {
        this.id = id;
        this.icon = new ImageIcon[2];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
    }

    // 4帧的地板：火地板
    public Floor(String id, ImageIcon imageIcon1, ImageIcon imageIcon2, ImageIcon imageIcon3, ImageIcon imageIcon4) {
        this.id = id;
        this.icon = new ImageIcon[4];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
        this.icon[2] = imageIcon3;
        this.icon[3] = imageIcon4;
    }
}

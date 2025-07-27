package entity;

import javax.swing.ImageIcon;

/**
 * 墙体类
 */
public final class Wall extends Entity {
    // 1帧的墙体
    public Wall(String id, ImageIcon imageIcon) {
        this.id = id;
        this.icon = new ImageIcon[1];
        this.icon[0] = imageIcon;
    }

    // 2帧的墙体：熔岩，星空
    public Wall(String id, ImageIcon imageIcon1, ImageIcon imageIcon2) {
        this.id = id;
        this.icon = new ImageIcon[2];
        this.icon[0] = imageIcon1;
        this.icon[1] = imageIcon2;
    }
}

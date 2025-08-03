package load;

import entity.Wall;

import javax.swing.ImageIcon;
import java.util.HashMap;

/**
 * 墙体加载类
 */
public final class LoadWall {
    public HashMap<String, Wall> initWall() {
        HashMap<String, Wall> wallMap = new HashMap<>(8);

        Wall wall = new Wall("wall01",
                new ImageIcon(getClass().getResource("/image/wall/wall03_1.png")));
        wallMap.put(wall.getId(), wall);

        wall = new Wall("wall02",
                new ImageIcon(getClass().getResource("/image/wall/wall02_1.png")));
        wallMap.put(wall.getId(), wall);

        wall = new Wall("wall03",
                new ImageIcon(getClass().getResource("/image/wall/wall01_1.png")));
        wallMap.put(wall.getId(), wall);

        wall = new Wall("wall04",
                new ImageIcon(getClass().getResource("/image/wall/wall04_1.png")));
        wallMap.put(wall.getId(), wall);

        // 星空
        wall = new Wall("wall05",
                new ImageIcon(getClass().getResource("/image/wall/wall09_1.png")),
                new ImageIcon(getClass().getResource("/image/wall/wall09_2.png")));
        wallMap.put(wall.getId(), wall);

        // 熔岩
        wall = new Wall("wall06",
                new ImageIcon(getClass().getResource("/image/wall/wall07_1.png")),
                new ImageIcon(getClass().getResource("/image/wall/wall07_2.png")));
        wallMap.put(wall.getId(), wall);

        // 水流
        wall = new Wall("wall07",
                new ImageIcon(getClass().getResource("/image/wall/wall08_1.png")),
                new ImageIcon(getClass().getResource("/image/wall/wall08_2.png")));
        wallMap.put(wall.getId(), wall);

        // 黄金
        wall = new Wall("wall08",
                new ImageIcon(getClass().getResource("/image/wall/wall06_1.png")),
                new ImageIcon(getClass().getResource("/image/wall/wall06_2.png")));
        wallMap.put(wall.getId(), wall);

        // 白银
        wall = new Wall("wall09",
                new ImageIcon(getClass().getResource("/image/wall/wall05_1.png")),
                new ImageIcon(getClass().getResource("/image/wall/wall05_2.png")));
        wallMap.put(wall.getId(), wall);

        // 星空浅色
        wall = new Wall("wall10",
                new ImageIcon(getClass().getResource("/image/wall/wall10_1.png")),
                new ImageIcon(getClass().getResource("/image/wall/wall10_2.png")));
        wallMap.put(wall.getId(), wall);

        return wallMap;
    }
}

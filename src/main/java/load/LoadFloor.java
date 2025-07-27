package load;

import entity.Floor;

import javax.swing.ImageIcon;
import java.util.HashMap;

/**
 * 地板加载类
 */
public final class LoadFloor {
    public HashMap<String, Floor> initFloor() {
        HashMap<String, Floor> floorMap = new HashMap<>(6);

        Floor floor = new Floor("floor01",
                new ImageIcon(getClass().getResource("/image/wall/floor01_1.png")));
        floorMap.put(floor.getId(), floor);

        floor = new Floor("floor02",
                new ImageIcon(getClass().getResource("/image/wall/floor02_1.png")));
        floorMap.put(floor.getId(), floor);

        floor = new Floor("floor03",
                new ImageIcon(getClass().getResource("/image/wall/floor03_1.png")));
        floorMap.put(floor.getId(), floor);

        floor = new Floor("floor04",
                new ImageIcon(getClass().getResource("/image/wall/floor04_1.png")));
        floorMap.put(floor.getId(), floor);

        floor = new Floor("floor05",
                new ImageIcon(getClass().getResource("/image/wall/floor05_1.png")));
        floorMap.put(floor.getId(), floor);

        floor = new Floor("floor06",
                new ImageIcon(getClass().getResource("/image/wall/floor06_1.png")));
        floorMap.put(floor.getId(), floor);

        // 火地板
        floor = new Floor("floor07",
                new ImageIcon(getClass().getResource("/image/wall/floor07_1.png")),
                new ImageIcon(getClass().getResource("/image/wall/floor07_2.png")),
                new ImageIcon(getClass().getResource("/image/wall/floor07_1.png")),
                new ImageIcon(getClass().getResource("/image/wall/floor07_2.png")));
        floorMap.put(floor.getId(), floor);

        return floorMap;
    }
}

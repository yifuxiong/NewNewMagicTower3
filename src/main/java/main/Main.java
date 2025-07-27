package main;

import entity.Tower;

/**
 * 游戏主函数
 */
public class Main {
    public static void main(String[] args) {
        TowerPanel game = new TowerPanel(new Tower());
        game.start();
    }
}

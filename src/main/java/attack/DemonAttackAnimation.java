package attack;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DemonAttackAnimation extends JPanel implements ActionListener {
    // 定时器控制动画帧
    private Timer timer;
    // 特效帧图片数组
    private BufferedImage[] frames;
    // 当前帧索引
    private int currentFrame = 0;
    // 特效中心坐标
    private int x, y;
    // 攻击状态
    private boolean isAttacking = false;

    public DemonAttackAnimation() {
        // 加载特效图片
        try{
            ClassLoader loader = this.getClass().getClassLoader();
            String path = loader.getResource("image").getPath();
            // 假设有6帧动画
            frames = new BufferedImage[6];
            for(int i = 0; i < frames.length; i++){
                frames[i] = ImageIO.read(new File(path + "/attack/magic01_" + (i + 1) + ".png"));
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        // 初始化定时器
        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(isAttacking){
            currentFrame++;
            if (currentFrame >= frames.length) {
                // 循环播放
                currentFrame = 0;
                // 或者停止动画
                // timer.stop();
            }
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (isAttacking && currentFrame < frames.length) {
            // 绘制当前帧（居中显示）
            BufferedImage frame = frames[currentFrame];
            int drawX = x - frame.getWidth() / 2;
            int drawY = y - frame.getHeight() / 2;
            g.drawImage(frame, drawX, drawY, this);
        }
    }

    // 触发攻击动画（外部调用）
    public void startAttack(int centerX, int centerY) {
        this.x = centerX;
        this.y = centerY;
        this.isAttacking = true;
        currentFrame = 0;
        repaint();
    }

    // 停止动画
    public void stopAttack(){
        this.isAttacking = false;
        currentFrame = 0;
        repaint();
    }

    // 设置窗口大小
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(800, 600);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("魔王格勒第攻击特效");
        DemonAttackAnimation animation = new DemonAttackAnimation();
        frame.add(animation);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // 模拟攻击触发（实际应由游戏逻辑控制）
        animation.startAttack(400, 300);
    }
}

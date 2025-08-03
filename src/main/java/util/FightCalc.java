package util;

import entity.Monster;
import entity.Player;

/**
 * 战斗结果计算器
 */
public final class FightCalc {
    private Monster monster;
    public int mDamageTotal;
    // 可以攻击怪物
    public boolean canAttack;
    // 可以战胜怪物
    public boolean canWin;

    /**
     * 玩家伤害临界值
     */
    private short playerCriticalVal = 20;

    public FightCalc(Player player, Monster monster) {
        this.monster = monster;
        if (!monster.hostile) {
            return;
        }
        int mHP = monster.getHp();
        int pHP = player.hp;
        int mDamage;
        // 魔法师系的怪物攻击玩家为真实伤害
        if (monster.getId().contains("monster04") && (monster.getId().equals("monster04_4") || monster.getId().equals("monster04_5") || monster.getId().equals("monster04_12"))) {
            mDamage = monster.getAttack();
        } else {
            mDamage = monster.getAttack() - player.defense;
        }
        int pDamage = player.attack - monster.getDefense();
        // -20为怪物伤害临界值
        if (mDamage <= 0 && mDamage > -playerCriticalVal) {
            mDamage = 1;
        } else if (mDamage <= -playerCriticalVal) {
            mDamage = 0;
        }
        // 判断是否可以攻击
        if (pDamage <= -playerCriticalVal) {
            canAttack = false;
            pDamage = 0;
        } else if (pDamage <= 0 && pDamage > -playerCriticalVal) {
            canAttack = true;
            pDamage = 1;
        } else{
            canAttack = true;
        }
        // System.out.println("怪物攻击一次的伤害:" + mDamage);
        // System.out.println("玩家攻击一次的伤害:" + pDamage);
        if (pDamage <= 0) {
            return;
        }
        /**
         * 战斗结果计算 默认玩家先攻
         */
        short pAttackNo = 0;
        short attackNo = 0;
        int pDamageTotal = 0;
        int mDamageTotal = 0;
        while (mHP > 0 && pHP > 0) {
            if (attackNo % 2 == 0) {
                pAttackNo++;
                mHP -= pDamage;
                pDamageTotal += pDamage;
            } else {
                pHP -= mDamage;
                mDamageTotal += mDamage;
            }
            attackNo++;
        }
        // System.out.println("玩家攻击次数:" + pAttackNo);
        // System.out.println("怪物攻击次数:" + (attackNo - pAttackNo));
        if (monster.getId().equals("monster04_13") && (mDamageTotal == 0 || mDamage == 1)) {
            this.mDamageTotal = (int) Math.round(player.hp / 3.0);
        } else if (monster.getId().equals("monster10_1") && (mDamageTotal == 0 || mDamage == 1)) {
            this.mDamageTotal = (int) Math.round(player.hp / 4.0);
        } else {
            this.mDamageTotal = mDamageTotal;
        }
        if (mDamageTotal < player.hp) {
            canWin = true;
        }
    }

    public Monster getMonster() {
        return monster;
    }
}

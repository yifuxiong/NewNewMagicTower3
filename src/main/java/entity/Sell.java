package entity;

import java.io.Serializable;
import java.util.List;

/**
 * 商店的商品类
 */
public final class Sell implements Cloneable, Serializable {
    // 商品名称
    public List<String> name;
    // 商品属性
    public List<String> attribute;
    // 加多少属性值
    public List<Short> val;
    // 需要的价格
    public List<Integer> price;

    public Sell(List<String> name, List<String> attribute, List<Short> val) {
        this.name = name;
        this.attribute = attribute;
        this.val = val;
    }

    public Sell(List<String> name, List<String> attribute, List<Short> val, List<Integer> price) {
        this.name = name;
        this.attribute = attribute;
        this.val = val;
        this.price = price;
    }
}

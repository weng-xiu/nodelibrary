package com.bjpowernode.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * public interface Serializable类通过实现 java.io.Serializable 接口以启用其序列化功能。
     * 未实现此接口的类将无法使其任何状态序列化或反序列化。可序列化类的所有子类型本身都是可序列化的。序列化接口没有方法或字段，
     * 仅用于标识可序列化的语义。
     * 要允许不可序列化类的子类型序列化，可以假定该子类型负责保存和恢复超类型的公用 (public)、受保护的 (protected)
     * 和（如果可访问）包 (package) 字段的状态。仅在子类型扩展的类有一个可访问的无参数构造方法来初始化该类的状态时，
     * 才可以假定子类型有此职责。如果不是这种情况，则声明一个类为可序列化类是错误的。该错误将在运行时检测到。
     * 在反序列化过程中，将使用该类的公用或受保护的无参数构造方法初始化不可序列化类的字段。可序列化的子类必须能够访问无参数构造方法。
     * 可序列化子类的字段将从该流中恢复。
     * 当遍历一个图形时，可能会遇到不支持 Serializable 接口的对象。在此情况下，将抛出 NotSerializableException，并将标识不可序列化对象的类。
     * 在序列化和反序列化过程中需要特殊处理的类必须使用下列准确签名来实现特殊方法：
     */

    //序列化运行时与每个可序列化的类关联一个版本号，称为serialVersionUID，
    // 在反序列化期间使用该版本号来验证序列化对象的发送者和接收者是否已加载了该对象的与序列化兼容的类。
    //如果接收者为对象加载的类serialVersionUID与相应的发送者的类不同，则反序列化将导致 InvalidClassException。
    // 可序列化的类可以serialVersionUID通过声明一个serialVersionUID必须为 static (静态) ，final和type的字段来显式声明其自身long：

    private int id;

    public User(int id, String name, String status, BigDecimal money) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    private String name;

    //状态
    private String status;

    //余额
    private BigDecimal money;

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", money=" + money +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(name, user.name) &&
                Objects.equals(status, user.status) &&
                Objects.equals(money, user.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, money);
    }

    public User() {
    }


}

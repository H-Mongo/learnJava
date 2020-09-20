package com.mongo.designmodel;

/**
 * 访问者模式
 *
 * @author H-Mongo
 * @create 2020/9/20 12:33
 */
public class VisitorModel {
    public static void main(String[] args) {
        Car car = new Car();
        Visitor visitor = new PrintVisitor();
        car.accept(visitor);
    }

}

/**
 * <p>描述中所提到访问者接口，重载的visit方法，用来对对象中不同的元素作出不同的反应</p>
 * @author H-Mongo
 */
interface Visitor {
    void visit(Wheel wheel);
    void visit(Engine engine);
    void visit(Body body);
    void visit(Car car);
}

/**
 * <p>汽车部件轮胎，拥有一个 accept方法，用来接收访问者的访问</p>
 * @author H-Mongo
 */
class Wheel {
    private String name;
    Wheel(String name) {
        this.name = name;
    }
    String getName() {
        return this.name;
    }
    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

/**
 * <p>汽车部件发动机，拥有一个 accept方法，用来接收访问者的访问</p>
 * @author H-Mongo
 */
class Engine {
    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

/**
 * <p>汽车车身，拥有一个 accept方法，用来接收访问者的访问</p>
 * @author H-Mongo
 */
class Body {
    void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

/**
 * <p>汽车，拥有一个 accept方法，用来接收访问者的访问（对它的元素逐一访问）</p>
 * @author H-Mongo
 */
class Car {
    private Engine  engine = new Engine();
    private Body    body   = new Body();
    private Wheel[] wheels
            = { new Wheel("front left"), new Wheel("front right"),
            new Wheel("back left") , new Wheel("back right")  };
    void accept(Visitor visitor) {
        visitor.visit(this);
        engine.accept(visitor);
        body.accept(visitor);
        for (int i = 0; i < wheels.length; ++ i)
            wheels[i].accept(visitor);
    }
}

/**
 * <p>实现了Visitor接口的访问者实现类</p>
 * @author H-Mongo
 */
class PrintVisitor implements Visitor {
    public void visit(Wheel wheel) {
        System.out.println("Visiting " + wheel.getName()
                + " wheel");
    }
    public void visit(Engine engine) {
        System.out.println("Visiting engine");
    }
    public void visit(Body body) {
        System.out.println("Visiting body");
    }
    public void visit(Car car) {
        System.out.println("Visiting car");
    }
}

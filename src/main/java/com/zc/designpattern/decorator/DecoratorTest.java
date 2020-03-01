package com.zc.designpattern.decorator;

/**
 * @author chock
 * @since 2020/3/1
 */
public class DecoratorTest {
    public static void main(String[] args) {
//        Component component = new ConcreateComponent();
//        Component component = new ConcreateDecorator1(new ConcreateComponent());
        Component component = new ConcreateDecorator2(new ConcreateDecorator1(new ConcreateComponent()));
        component.operation();
    }
}

interface Component {
    void operation();
}

class ConcreateComponent implements Component {

    @Override
    public void operation() {
        System.out.println("take photo");
    }
}

abstract class Decorator implements Component {
    Component component;

    public Decorator(Component component) {
        this.component = component;
    }
}

class ConcreateDecorator1 extends Decorator {

    public ConcreateDecorator1(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        component.operation();
        System.out.println("more beauty");
    }
}

class ConcreateDecorator2 extends Decorator {

    public ConcreateDecorator2(Component component) {
        super(component);
    }

    @Override
    public void operation() {
        component.operation();
        System.out.println("more clear");
    }
}
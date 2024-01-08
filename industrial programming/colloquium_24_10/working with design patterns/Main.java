package org.example;
interface factory_of_toys {
    piggy create_piggy();
    duck create_duck();
}
class plastic_toys implements factory_of_toys {
    @Override
    public piggy create_piggy() {
        return new plastic_piggy();
    }

    @Override
    public duck create_duck() {
        return new plastic_duck();
    }
}
class plush_toys implements factory_of_toys {
    @Override
    public piggy create_piggy() {
        return new plush_piggy();
    }

    @Override
    public duck create_duck() {
        return new plush_duck();
    }
}
interface piggy {
    Object print();
}
class plastic_piggy implements piggy {
    @Override
    public Object print() {
        System.out.println("свинка из пластика");
        return null;
    }
}

class plush_piggy implements piggy {
    @Override
    public Object print() {
        System.out.println("плюшевая свинка");
        return null;
    }
}

interface duck {
    Object print();
}
class plastic_duck implements duck {
    @Override
    public Object print() {
        System.out.println("уточка из пластика");
        return null;
    }
}
class plush_duck implements duck {
    @Override
    public Object print() {
        System.out.println("плюшевая уточка");
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        factory_of_toys plastic_toy = new plastic_toys();

        piggy plastic_piggy = plastic_toy.create_piggy();
        duck plastic_duck = plastic_toy.create_duck();

        plastic_piggy.print();
        plastic_duck.print();

        factory_of_toys plush_toy = new plush_toys();

        piggy plush_piggy = plush_toy.create_piggy();
        duck plush_duck = plush_toy.create_duck();

        plush_piggy.print();
        plush_duck.print();
    }
}
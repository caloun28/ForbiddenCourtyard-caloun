package entity;

import entity.items.Item;

import java.util.ArrayList;

public class Npc {
    private TypeOfNpc typeOfNpc;
    private String name;
    private int health;
    private int damage;
    private boolean alive;
    private ArrayList<Item> items;

    public Npc(TypeOfNpc typeOfNpc, String name, int health, int damage, boolean alive) {
        this.typeOfNpc = typeOfNpc;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.alive = alive;
        this.items = new ArrayList<>();
    }

    public TypeOfNpc getTypeOfNpc() {
        return typeOfNpc;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public boolean isAlive() {
        return alive;
    }

    public void addItem(Item item) {
        items.add(item);
    }

}

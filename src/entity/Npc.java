package entity;

import entity.items.Item;
import player.Player;

import java.util.ArrayList;
import java.util.Random;

public class Npc {
    private TypeOfNpc typeOfNpc;
    private String name;
    private int health;
    private int damage;
    private boolean alive;
    private ArrayList<Item> items;
    private Random rand = new Random();

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

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void setHealth(int health) {
        if(health > 0) {
            this.health = health;
        }else{
            this.alive = false;
        }

    }

    public void attack(Player player) {
        int realDamage = damage+ rand.nextInt(7);
        player.setHealth(player.getHealth() - realDamage);
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return name + " s " + health + "HP " + " a ma " + items;
    }
}

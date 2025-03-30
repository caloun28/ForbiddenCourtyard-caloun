package entity;

import entity.items.Item;
import player.Player;
import world.Location;

import java.util.ArrayList;
import java.util.Random;

/**
 * Class for an npc.
 */
public class Npc {
    private TypeOfNpc typeOfNpc;
    private String name;
    private int health;
    private int damage;
    private boolean alive;
    private ArrayList<Item> items;
    private Random rand = new Random();
    private Location currentLocation;

    public Npc(TypeOfNpc typeOfNpc, String name, int health, int damage, boolean alive) {
        this.typeOfNpc = typeOfNpc;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.alive = alive;
        this.currentLocation = null;
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

    /**
     * It sets the lives not to be less than 0 and if they are it sets the npc dead.
     * @param health
     */
    public void setHealth(int health) {
        if(health > 0) {
            this.health = health;
        }else{
            this.alive = false;
        }

    }

    /**
     * It counts single attack with random value and damage that npc has.
     * @param player Param 'player' is a instant of a player.
     */
    public void attack(Player player) {
        int realDamage = damage+ rand.nextInt(7);
        player.setHealth(player.getHealth() - realDamage);
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public String stats(){
        return health + "HP, " + damage + " utok";
    }

    @Override
    public String toString() {
        return name + " s " + health + "HP " + " a ma " + items;
    }
}

package player;

import entity.Npc;
import entity.items.Axe;
import entity.items.Bottle;
import entity.items.Medkit;
import world.Location;

import java.util.Random;

public class Player {
    private int health;
    private int damage;
    private boolean alive;
    private Location currentLocation;
    private Inventory inventory;
    private Random rand = new Random();
    private int maxHealth;

    public Player(int health, int damage, boolean alive) {
        this.health = health;
        this.damage = damage;
        this.alive = alive;
        this.currentLocation = null;
        this.inventory = new Inventory();
        this.maxHealth = health;
    }


    public int getDamage() {
        return damage;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void setHealth(int health) {
        if(health > 0) {
            this.health = health;
        }else{
            this.alive = false;
        }
    }

    public void healToMax() {
        this.health = maxHealth;
    }

    public Inventory getInventory() {
        return inventory;
    }


    public void moveTo(Location newLocation) {
        this.currentLocation = newLocation;
    }

    public void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }


    public void increaseHealth(Medkit medkit) {
        if (!medkit.isUsed()) {
            this.health += medkit.getPlusHealth();

            if (this.health > maxHealth) {
                this.maxHealth = this.health;
            }

            medkit.setUsed(true);
        }
    }

    public void increaseDamage(Axe axe){
        this.damage += axe.getPlusDamage();
    }

    public void attack(Npc npc) {
        int realDamage = damage+ rand.nextInt(7);
        npc.setHealth(npc.getHealth() - realDamage);
    }

    public void increaseEndurance(Npc npc, Bottle bottle) {
        if(!bottle.isUsed()) {
            npc.setDamage(npc.getDamage() / bottle.getQuality());
        }
    }

    @Override
    public String toString() {
        return "Mas " + getHealth() + "HP, tvoje utoky davaji za " + getDamage() + " a v inventari mas " + getInventory();
    }
}

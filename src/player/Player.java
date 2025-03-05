package player;

import entity.Npc;
import entity.items.Axe;
import entity.items.Bottle;
import entity.items.Medkit;

public class Player {
    private String name;
    private int health;
    private int damage;
    private boolean alive;

    public Player(String name, int health, int damage, boolean alive) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.alive = alive;
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

    public void setHealth(int health) {
        this.health = health;
    }

    public void increaseHealth(Medkit medkit) {
        if(!medkit.isUsed()) {
            this.health += medkit.getPlusHealth();
        }
        medkit.setUsed(true);
    }

    public void increaseDamage(Axe axe){
        this.damage += axe.getPlusDamage();
    }

    public void attack(Npc npc) {
        npc.setHealth(npc.getHealth() - damage);
    }

    public void increaseEndurance(Npc npc, Bottle bottle) {
        if(!bottle.isUsed()) {
            npc.setDamage(npc.getDamage() / bottle.getQuality());
        }
    }
}

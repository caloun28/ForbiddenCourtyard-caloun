package player;

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
}

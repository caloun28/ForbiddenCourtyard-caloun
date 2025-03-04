package entity;

public class Npc {
    private TypeOfNpc typeOfNpc;
    private String name;
    private int health;
    private int damage;
    private boolean alive;

    public Npc(TypeOfNpc typeOfNpc, String name, int health, int damage, boolean alive) {
        this.typeOfNpc = typeOfNpc;
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.alive = alive;
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
}

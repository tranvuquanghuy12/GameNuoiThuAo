package com.example.myapplication2;

public class Monster {
    public int id;
    public String name;
    public int imageResId;
    public int hp;
    public int attack;
    public int defense;
    public int mana;

    public Monster(int id, String name, int imageResId, int hp, int mana, int attack, int defense) {
        this.id = id;
        this.name = name;
        this.imageResId = imageResId;
        this.hp = hp;
        this.mana = mana;
        this.attack = attack;
        this.defense = defense;
    }
}

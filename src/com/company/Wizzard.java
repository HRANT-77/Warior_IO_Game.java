package com.company;

import java.io.File;

public class Wizzard {
    private String name;
    private double life;
    private double armor;
    private double force;

    public Wizzard() {
    }

    public Wizzard(String name, double life, double armor, double force) {
        this.name=name;
        this.life = life;
        this.armor = armor;
        this.force = force;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLife() {
        return life;
    }

    public void setLife(double life) {
        this.life = life;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public double getForce() {
        return force;
    }

    public void setForce(double force) {
        this.force = force;
    }

    public void beat(Archer archer){
        System.out.println(this.name+" beat "+archer.getName()+" with "+this.force+" force");
        System.out.println();
        System.out.println(archer.getName()+"s life is "+(archer.getLife()-((this.force)*(100- archer.getArmor())/100)));
        System.out.println(this.name+"s life is "+this.getLife());
        System.out.println();

    }
}

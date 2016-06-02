/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author g14925mm
 */
public class Monster implements Serializable {
    private String name;
    private int atk;
    private int hp; 
    
    public Monster(){ }
    public Monster(String name,int atk,int hp) {
        this.name = name;
        this.atk = atk;
        this.hp = hp;
    }
    public String getName() { return name; }
    public int getAtk() { return atk; }
    public int getHp() { return hp; }
}

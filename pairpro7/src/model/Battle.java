/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author g14925mm
 */
public class Battle {
    private String name;
    private String text;

    public Battle() { }
    public Battle(String name,String text) {
        this.name = name;
        this.text = text;
    }
    public String getName() { return name; }
    public String getText() { return text; }
}


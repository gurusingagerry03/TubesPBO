/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Actor implements Entity{
    private int actorId;
    private String name;
    private String birthDate;
    private String gender;

    // Constructor
    public Actor(int actorId, String name, String birthDate, String gender) {
        this.actorId = actorId;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    @Override
    public int getId() {
        return actorId;
    }

    @Override
    public void setId(int id) {
        this.actorId = id;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}

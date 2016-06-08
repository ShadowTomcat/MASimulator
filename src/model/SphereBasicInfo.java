/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Shadow
 */
public class SphereBasicInfo {

    private String id;
    private String name;
    private Integer classIndex;
    private String description;

    public SphereBasicInfo(String id, String name, Integer classIndex, String description) {
        this.id = id;
        this.name = name;
        this.classIndex = classIndex;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(Integer classIndex) {
        this.classIndex = classIndex;
    }

    @Override
    public String toString() {
        return name + " | " + description;
    }

}

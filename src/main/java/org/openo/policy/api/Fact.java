package org.openo.policy.api;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by 10184056 on 2016/8/20.
 */
public class Fact {
    private String name;
    private String gender;
    private float height;
    private int age;

    public Fact() {
    }

    public Fact(String name, String gender, float height, int age) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.age = age;
    }
    @JsonProperty
    public String getName() {
        return name;
    }
    @JsonProperty
    public String getGender() {
        return gender;
    }
    @JsonProperty
    public float getHeight() {
        return height;
    }
    @JsonProperty
    public int getAge() {
        return age;
    }
}

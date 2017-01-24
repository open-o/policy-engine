/**
 * Copyright 2017 ZTE Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openo.policy.api;

import com.fasterxml.jackson.annotation.JsonProperty;

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

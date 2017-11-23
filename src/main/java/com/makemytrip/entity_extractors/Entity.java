package com.makemytrip.entity_extractors;

/**
 * Entity POJO
 */
public class Entity {

    private String type;
    private Object value;

    public Entity(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public Entity setType(String type) {
        this.type = type;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public Entity setValue(Object value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "value=" + value +
                ", type='" + type + '\'' +
                '}';
    }
}

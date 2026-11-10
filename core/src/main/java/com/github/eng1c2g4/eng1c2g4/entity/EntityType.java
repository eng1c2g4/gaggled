package com.github.eng1c2g4.eng1c2g4.entity;

public enum EntityType {

    GOOSE(32, 32),
    ITEM(16, 16);

    private final int width;
    private final int height;

    EntityType(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
    
    public float getDistanceToCenter() {
        return (float) Math.sqrt(Math.pow(this.width, 2) * Math.pow(this.height, 2));
    }
}

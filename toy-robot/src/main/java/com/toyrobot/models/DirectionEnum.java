package com.toyrobot.models;

public enum DirectionEnum {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    private final int value;

    DirectionEnum(int value) {
        this.value = value;
    }

}

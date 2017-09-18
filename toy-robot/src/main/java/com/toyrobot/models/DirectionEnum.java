package com.toyrobot.models;

import java.util.HashMap;
import java.util.Map;

public enum DirectionEnum {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);

    private final int value;

    DirectionEnum(int value) {
        this.value = value;
    }

    private static final Map<Integer, DirectionEnum> intToTypeMap = new HashMap<>();
    static {
        for (DirectionEnum type : DirectionEnum.values()) {
            intToTypeMap.put(type.value, type);
        }
    }

    public static DirectionEnum fromInt(int i) {
        DirectionEnum type = intToTypeMap.get(Integer.valueOf(i));
        return type;
    }

}

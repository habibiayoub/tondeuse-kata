package com.kata.tondeuse.enums;

public enum DirectionEnum {

    N, E, S, W;

    public DirectionEnum turnRight () {
        switch (this) {
            case N -> {
                return E;
            }
            case E -> {
                return S;
            }
            case S -> {
                return W;
            }
            case W -> {
                return N;
            }
        }
        return null;
    }

    public DirectionEnum turnLeft () {
        switch (this) {
            case N -> {
                return W;
            }
            case E -> {
                return N;
            }
            case S -> {
                return E;
            }
            case W -> {
                return S;
            }
        }
        return null;
    }

}

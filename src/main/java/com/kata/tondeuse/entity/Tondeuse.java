package com.kata.tondeuse.entity;

import com.kata.tondeuse.dto.Coordinate;
import com.kata.tondeuse.enums.DirectionEnum;

public class Tondeuse {

    private int x;
    private int y;
    private DirectionEnum direction;

    public Tondeuse() {
    }

    public Tondeuse(int x, int y, DirectionEnum direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void move (Coordinate maxCoodinate, char action) {

        switch (action) {
            case 'D' -> this.direction = this.direction.turnRight();
            case 'G' -> this.direction = this.direction.turnLeft();
            case 'A' -> {
                switch (this.direction) {
                    case N -> {
                        if (maxCoodinate.getY() >= this.y + 1)
                            this.y++;
                    }
                    case E -> {
                        if (maxCoodinate.getX() >= this.x + 1)
                            this.x++;
                    }
                    case S -> {
                        if (this.y - 1 >= 0)
                            this.y--;
                    }
                    case W -> {
                        if (this.x - 1 >= 0)
                            this.x--;
                    }
                }
            }
            default -> System.out.println("Invalid action: " + action);
        }
    }

    public String getDirectionAsChar() {
        return direction.name();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Tondeuse{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }
}

package com.steve.orion.collision;

public class AABB {
    public float x, y;       // 中心位置
    public float halfWidth;  // 半宽
    public float halfHeight; // 半高

    public AABB(float x, float y, float halfWidth, float halfHeight) {
        this.x = x;
        this.y = y;
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
    }

    public boolean intersects(AABB other) {
        return Math.abs(this.x - other.x) <= (this.halfWidth + other.halfWidth) &&
                Math.abs(this.y - other.y) <= (this.halfHeight + other.halfHeight);
    }

    public void setCenter(float x, float y) {
        this.x = x;
        this.y = y;
    }
}

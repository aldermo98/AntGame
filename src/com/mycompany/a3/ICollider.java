package com.mycompany.a3;

import com.mycompany.a3.GameObjects.GameObject;

public interface ICollider {
	public boolean collidesWith(GameObject otherObject);
	public void handleCollision(GameObject otherObject);
}

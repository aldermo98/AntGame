package com.mycompany.a3;

import com.mycompany.a3.GameObjects.GameObject;

public interface ICollection {
	public void add(GameObject obj);
	public IIterator getIterator();
}

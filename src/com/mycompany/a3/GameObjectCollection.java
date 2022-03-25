package com.mycompany.a3;

import java.util.ArrayList;

import com.mycompany.a3.GameObjects.GameObject;

public class GameObjectCollection implements ICollection{
	private ArrayList<GameObject> collection;
	
	public GameObjectCollection(){
		collection = new ArrayList<>();
	}
	
	public void add(GameObject obj) {
		collection.add(obj);
	}
	
	public IIterator getIterator() {
		return new GameObjectIterator();  
	}
	
	private class GameObjectIterator implements IIterator{
		private int currElementIndex;
		
		public GameObjectIterator() {
			currElementIndex = -1;
		}
		public boolean hasNext() {
			if (collection.size () <= 0)  
				return false;
			if (currElementIndex == collection.size()-1)
				return false;
			return true;
		}
		public GameObject getNext () {
			currElementIndex++;
			return(collection.get(currElementIndex));
		}
	}
}

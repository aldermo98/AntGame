package com.mycompany.a3;

import java.util.Observable;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.geom.Dimension;
import com.mycompany.a3.GameObjects.Ant;
import com.mycompany.a3.GameObjects.Flag;
import com.mycompany.a3.GameObjects.FoodStation;
import com.mycompany.a3.GameObjects.GameObject;
import com.mycompany.a3.GameObjects.Movable;
import com.mycompany.a3.GameObjects.Spider;

public class GameWorld extends Observable{
	private GameObjectCollection worldObjects;
	private int width;
	private int height;
	private int lives = 3;
	private int time = 0;	//count up from 0. store time it took to reach last flag
	private boolean sound = false;
	private Sound flagReached, foodStationReached, spiderCollision;
	private BGSound bgSound;

	public void init(){ 
		//code here to create the       
		//initial   game   objects/setup 
		worldObjects = new GameObjectCollection();		//command design pattern
		
		//Game Settings/////////////////////////////////////////////////////////////
		Point initLocation = new Point(100,100);
		int antSize = 100;
		int antColor = ColorUtil.rgb(255, 0, 0);
		
		int flagSize = 100;
		int flagColor = ColorUtil.rgb(0, 0, 255);
		
		int spiderColor = ColorUtil.rgb(0, 0, 0);
		
		int foodStationColor = ColorUtil.rgb(0, 255, 0);
		/////////////////////////////////////////////////////////////////////////////
		
		//create and initialize all game objects and aggregate them into a collection
		//Ant ant = Ant.initAnt(antSize, antColor, initLocation);	//create singleton instance of Ant
		//ant.reInitialize();
		worldObjects.add(createAnt(antSize, antColor, initLocation));
		worldObjects.add(createFlag(flagSize,flagColor,initLocation));
		worldObjects.add(createFlag(flagSize,flagColor,null));		//third argument accepts Point object or null for default(rndom Point)
		worldObjects.add(createFlag(flagSize,flagColor,null));
		worldObjects.add(createFlag(flagSize,flagColor,null));
		worldObjects.add(createSpider(spiderColor));
		worldObjects.add(createSpider(spiderColor));
		worldObjects.add(createFoodStation(foodStationColor));
		worldObjects.add(createFoodStation(foodStationColor));
		
		setChanged();
		notifyObservers(Ant.getAnt());
	}

	//helper function to maintain cleaner looking code in object instantiations above
	public Ant createAnt(int antSize, int antColor, Point location) {
		return Ant.initAnt(antSize, antColor, location);
	}
	public Flag createFlag(int flagSize, int flagColor, Point location) {
		return (location == null) ? new Flag(flagSize, flagColor, new Point(randomNum(0, width), randomNum(0, height))) 
			    : new Flag(flagSize, flagColor, location);	
	}
	public Spider createSpider(int spiderColor) {
		return new Spider( randomNum(50, 100),
					   	   spiderColor, 
					   	   new Point(randomNum(0, width),randomNum(0, height)), 
					   	   randomNum(10, 15), 
					   	   randomNum(0, 359) );
	}
	public FoodStation createFoodStation(int foodStationColor) {
		return new FoodStation( randomNum(100, 150), foodStationColor, new Point(randomNum(0, width), randomNum(0, height)) );
	}
	
	public int randomNum(int min, int max) {
		return min + new Random().nextInt(max-min+1);		//return random number between min-max inclusively
	}
	
	public void createSounds() {
		flagReached = new Sound("cheer.wav");
		foodStationReached = new Sound("eat.wav");
		spiderCollision = new Sound("homer_scream.wav");
		bgSound = new BGSound("bgSound.wav");
	}
	
	public void playSound(Sound sfx) {
		if(sound)
			sfx.play();	
	}
	
	/** determine if player won, lost, or lost a life and game should restart */
	public void outcome(boolean reachedHighestFlag) {
		Flag.setHighestFlag(0);		//restart static flag count
		init();
		if(reachedHighestFlag == true) {
			System.out.println("Game over, you win! Total time: " + time);
			System.out.println("Starting new game...");
			lives = 3;
			time = 0;
		}else {
			if(lives > 1) {
				lives--;
				System.out.println("Restarting...lives: " + lives);
			}else {
				lives = 3;
				System.out.println("Game  over,  you  failed!");
				System.out.println("Starting over...lives: " + lives);
				time = 0;
			}
		}
		setChanged();
		notifyObservers(Ant.getAnt());
	}
	
	// additional methods here to    
	// manipulate world objects and    
	// related game state data 
	
	/** increase ant speed. acceleration is limited by food level, health level, and max speed */
	public void accelerate() {
		Ant ant = Ant.getAnt();
		
		int speedIncrement = 3;
		if(ant.getFoodLevel() == 0) {
			System.out.println("Food Level 0. too hungry to move...");
			outcome(false);
		}if(ant.getHealthLevel() == 0) {
			System.out.println("Health Level 0. can no longer move...");
			outcome(false);
		}else if(ant.getSpeed() + speedIncrement >= ant.getMaxSpeed()) {
			System.out.println("You are at max speed...speed: " + ant.getMaxSpeed());
			ant.setSpeed((int)ant.getMaxSpeed());
		}else { 
			ant.setSpeed(ant.getSpeed() + speedIncrement);
			System.out.println("accelerating...speed: " + ant.getSpeed());
		}
		
		setChanged();
		this.notifyObservers(Ant.getAnt());
	}
	
	/** decrease ant speed. cannot brake once at speed of 0  */
	public void brake() {
		Ant ant = Ant.getAnt();
		
		int speedDecrement = 3;
		if(ant.getSpeed() - speedDecrement <= 0) {
			System.out.println("You are stopped...speed: 0");
			ant.setSpeed(0);
		}else {
			ant.setSpeed(ant.getSpeed() - speedDecrement);
			System.out.println("braking...speed: " + ant.getSpeed());
		}	
		
		setChanged();
		this.notifyObservers(Ant.getAnt());
	}
	
	/** turn the ant to the left */
	public void headLeft() {
		Ant ant = Ant.getAnt();
		
		ant.changeHeading(ant.getHeading() - 5);
		System.out.println("turning left...heading: " + ant.getHeading());
		
		setChanged();
		this.notifyObservers(Ant.getAnt());
	}
	
	/** turn the ant to the right */
	public void headRight() {
		Ant ant = Ant.getAnt();

		ant.changeHeading(ant.getHeading() + 5);
		System.out.println("turning right...heading: " + ant.getHeading());
		
		setChanged();
		this.notifyObservers(Ant.getAnt());
	}
	
	/** new flag reached. update values */
	public void updateLastFlagReached(int flag) {
		Ant ant = Ant.getAnt();
		
		if(ant.getLastFlagReached()+1 == flag) {
			ant.setLastFlagReached(flag);
			System.out.println("Flag #" + flag + " has been reached...");
			playSound(flagReached);
			if(ant.getLastFlagReached() == Flag.getHighestFlag())
				outcome(true);
		}
		
		setChanged();
		this.notifyObservers(Ant.getAnt());
	}
	
	/** if ant reaches food station: increase food level, zero that station's capacity, fade the station's color, create a new food station */
	public void foodStationReached(FoodStation fs) {
		Ant ant = Ant.getAnt();
		
		ant.setFoodLevel(ant.getFoodLevel() + fs.getCapacity());
		fs.setCapacity(0);
		fs.setColor(fs.getColor()+65537*150);
		worldObjects.add(new FoodStation( randomNum(10, 50), ColorUtil.rgb(0, 255, 0), new Point(randomNum(0, width), randomNum(0, height)) ));
		System.out.println("Reached food station...food level: " + ant.getFoodLevel());
					
		playSound(foodStationReached);
		
		setChanged();
		this.notifyObservers(Ant.getAnt());
	}
	
	/** collided ith spider. decrease health, fade ant color, decrease ant speed*/
	public void spiderAttacked() {
		Ant ant = Ant.getAnt();
		
		ant.setHealthLevel(ant.getHealthLevel()-1);
		ant.setColor(ant.getColor()+257*20);
		ant.setMaxSpeed((int)( ((double)ant.getHealthLevel()/10) * ant.getMaxSpeed() ));
		if(ant.getMaxSpeed() < ant.getSpeed()) 
			ant.setSpeed(ant.getMaxSpeed());
		else
			ant.setSpeed(ant.getSpeed()-1);
		if(ant.getHealthLevel() <= 0) {
			ant.setHealthLevel(0);
			System.out.println("Health level 0. can no longer move...");
			outcome(false);
		}else {
			System.out.println("You ran into a spider...health level: " + ant.getHealthLevel());
		}
		
		playSound(spiderCollision);
				
		setChanged();
		this.notifyObservers(Ant.getAnt());
	}
	
	/** increment timer, update spider headings, update movable object locations, decrease food level according to foodConsumptionRate */
	public void tick(int timeMillis) {
		time++;
		
		//spiders update their heading
		Spider s = null;
		IIterator iter = worldObjects.getIterator();
		while(iter.hasNext()) {  
			GameObject obj = iter.getNext();
			if(obj instanceof Spider) {
				s = (Spider)obj;
				int rand = randomNum(0,1);
				if(rand == 0)
					s.setHeading( s.getHeading() + randomNum(10, 50) );
				else
					s.setHeading( s.getHeading() - randomNum(10, 50) );
			}
		}
		
		//all movable objects update location according to current heading and speed
		iter = worldObjects.getIterator();
		while(iter.hasNext()) {  
			GameObject obj = iter.getNext();
			if(obj instanceof Movable) {
				Dimension dCmpSize = new Dimension(width,height);
				((Movable) obj).move(dCmpSize, timeMillis);
			}
		}
		
		//all objects must detect for collision
		iter = worldObjects.getIterator();
		while(iter.hasNext()) {
			GameObject obj2 = iter.getNext();
			if(obj2!=Ant.getAnt()){
				if(Ant.getAnt().collidesWith(obj2)) {
					if(obj2 instanceof Flag) {
						updateLastFlagReached(((Flag) obj2).getSequenceNumber());
					}else if(obj2 instanceof FoodStation) {
						if(((FoodStation) obj2).getCapacity()!=0)
							foodStationReached((FoodStation)obj2);
					}else if(obj2 instanceof Spider) {
						Ant.getAnt().handleCollision(obj2);
						spiderAttacked();
					}
				}
			}
		}
		
		//ant food level decreased according to foodComsumptionRate
		Ant ant = Ant.getAnt();
		ant.setFoodLevel(ant.getFoodLevel() - ant.getFoodConsumptionRate());
		if(ant.getFoodLevel() <= 0) {
			System.out.println("Food level 0. to hungry to move...");
			outcome(false);
		}else {
			System.out.println("clock is ticking...time: " + time + " food level: " + ant.getFoodLevel() + " *object locations have been updated");
		}
		
		setChanged();
		this.notifyObservers(Ant.getAnt());
	}
	
	/**output lines of text on console according to game/ant state*/
	public void displayState() {
		Ant ant = Ant.getAnt();

		System.out.println( "Lives        = " + lives +
							"\nTime         = " + time + 
							"\nHighest Flag = " + ant.getLastFlagReached() + 
							"\nFood Level   = " + ant.getFoodLevel() + 
							"\nHealth Level = " + ant.getHealthLevel() +
							"\nSound        = " + sound);
	}
	
	/** display all objects and their attribute values */
	public void displayMap() {
		System.out.println("displaying object values...");
		IIterator elements = worldObjects.getIterator();
		while(elements.hasNext()) {  
			System.out.println(elements.getNext().toString() );
		}
	}
	
	/** enter exit mode. user can only enter y or n */
	public void exit() {
		System.out.println("Exit game?");
	}
	
	/** exit game */
	public void yes() {
		System.out.println("Exiting game...");
		System.exit(0);
	}
	
	/**continue game*/
	public void no() {
		System.out.println("game continued...");
	}
	
	public GameObjectCollection getWorldObjects() { return worldObjects; }
	public int getLives() { return lives; }
	public int getTime() { return time; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public boolean isSound() { return sound; }
	public void setSound(boolean sound) {
		this.sound = sound;
		setChanged();
		notifyObservers();
	}
	public BGSound getBgSound() { return bgSound; }
	public void setSize(int x, int y) { 
		width = x;
		height = y;
	}
	
}

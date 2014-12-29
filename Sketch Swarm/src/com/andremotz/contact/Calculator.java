package com.andremotz.contact;

import java.util.Iterator;
import java.util.Map;

import processing.core.*;

/*
 * This class handles all 'higher' mathematical
 * stuff that's necessary.
 */
public class Calculator extends PApplet {

	Calculator() {
		
	}

	/*
	 * Return any random position
	 */
	public int getRandomTargetPosition(int max) {
		int randomPosition = 0;

		randomPosition = Math.round(random(max));

		return randomPosition;
	};
	
	/*
	 * Thanks Processing for making this a one-liner...
	 */
	public float getDistanceOfVectors(Vector a, Vector b) {
		float distance = dist(a.getX(), a.getY(), b.getX(), b.getY());
		return distance;
	}

	/*
	 * Check out for the nearest distance to another particle
	 * for the given id and return that value, so that the particle
	 * can decide if it shall look for a new target.
	 */
	public float getNearestDistanceForCurrentParticle(int id) {
		// any high value to count down
		float nearestDistanceForCurrentParticle = 1000f;
		
		Vector currentParticlePosition = Memory.mapParticlePositions.get(id);
		
		Iterator i = Memory.mapParticlePositions.entrySet().iterator();  // Get an iterator
		while (i.hasNext ()) {
			Map.Entry particleToCheck = (Map.Entry)i.next();
			Integer particleToCheckId = (Integer) particleToCheck.getKey();
			Vector particleToCheckPosition = (Vector) particleToCheck.getValue();
			
			if (particleToCheckId != id) {
				float currentDistanceToOtherPosition = getDistanceOfVectors(currentParticlePosition, particleToCheckPosition);
				if (currentDistanceToOtherPosition < nearestDistanceForCurrentParticle) {
					nearestDistanceForCurrentParticle = currentDistanceToOtherPosition;
				}
			}
			
		}
		return nearestDistanceForCurrentParticle;
	}
	
}

package com.andremotz.contact;

import java.util.ArrayList;
import java.util.HashMap;

import processing.core.*;

public class SketchSwarm extends PApplet {

	/*
	 * Values for my MacBook-Air screen.
	 * 
	 */
	public final int WIDTH = 1440;
	public final int HEIGHT = 900;
	public final int AMOUNT = 200;
	public final float DISTANCETOKEEP = 10f;

	Calculator calculator;
	ArrayList<Particle> particles;
	private boolean mouseIsDragged;

	public static void main(String[] args) {
		PApplet.main(new String[] { "--present",
		"com.andremotz.contact.SketchSwarm" });	
	}

	/*
	 * Initialize everything
	 * 
	 */
	public void setup() {
		calculator = new Calculator();
		particles = new ArrayList<Particle>();


		Memory.mapParticlePositions = new HashMap<Integer, Vector>();
		for (int i = 0; i < AMOUNT; i++) {
			Particle newParticle = new Particle(i, WIDTH, HEIGHT,
					DISTANCETOKEEP);
			particles.add(newParticle);
			Memory.mapParticlePositions
					.put(i, newParticle.getCurrentPosition());
		}

		size(WIDTH, HEIGHT);
		smooth();
		noStroke();
		mouseIsDragged = false;
	}

	public void draw() {
		background(50);

		for (Particle currentParticle : particles) {
			currentParticle.calculate();

			/*
			 * Should the individual particle head for a new target?
			 * 
			 * Not so nice hack: Decision should be done within
			 * Particle-class...
			 */
			if (mouseIsDragged) {
				Vector targetPosition = new Vector();
				targetPosition.setX(mouseX);
				targetPosition.setY(mouseY);
				currentParticle.setTargetPosition(targetPosition);
			} else {
				currentParticle.checkToCalculateNewTarget();
			}
			displayParticle(currentParticle);
		}
	}

	public void mouseDragged() {
		mouseIsDragged = true;
	}
	
	public void mouseReleased() {
		mouseIsDragged = false;
	}

	private void displayParticle(Particle currentParticle) {

		float currentX = currentParticle.getCurrentX();
		float currentY = currentParticle.getCurrentY();
		float targetX = currentParticle.getTargetX();
		float targetY = currentParticle.getTargetY();

		// Path
		if (mouseIsDragged) {
			stroke(60);
			line(currentX, currentY, targetX, targetY);
		}
		
		// Particle
		noStroke();
		fill(200);
		ellipse(currentX, currentY, 24, 24);
	}

}

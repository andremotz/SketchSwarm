package com.andremotz.contact;

/*
 * A particle should be able to move on it's own and
 * decide whether it should head for a new target or not
 */
public class Particle {

	private Calculator calculator;
	private int width;
	private int height;

	private Vector currentPosition;
	private Vector targetPosition;
	private float distanceToKeep;
	private int id;

	public void calculate() {

		float currentX = currentPosition.getX();
		float currentY = currentPosition.getY();
		float targetX = targetPosition.getX();
		float targetY = targetPosition.getY();

		// calc distance
		float currentDistanceX = targetX - currentX;
		float currentDistanceY = targetY - currentY;

		// move
		float stepByX = currentDistanceX / 100;
		float stepByY = currentDistanceY / 100;

		currentPosition.setX(currentX + stepByX);
		currentPosition.setY(currentY + stepByY);

		Memory.mapParticlePositions.put(id, currentPosition);

	}

	public void checkToCalculateNewTarget() {
		float currentDistanceToTarget = calculator.getDistanceOfVectors(
				currentPosition, targetPosition);

		float nearestDistanceToAnyOtherParticle = calculator
				.getNearestDistanceForCurrentParticle(id);

		if ((currentDistanceToTarget < distanceToKeep)
				|| (nearestDistanceToAnyOtherParticle < distanceToKeep)) {
			targetPosition.setX(calculator.getRandomTargetPosition(width));
			targetPosition.setY(calculator.getRandomTargetPosition(height));

		}

	}

	Particle(int id, int WIDTH, int HEIGHT, float DISTANCETOKEEP) {
		this.id = id;
		width = WIDTH;
		height = HEIGHT;
		distanceToKeep = DISTANCETOKEEP;
		calculator = new Calculator();

		currentPosition = new Vector();
		// currentPosition.setX(calculator.getRandomTargetPosition(width));
		// currentPosition.setY(calculator.getRandomTargetPosition(height));
		currentPosition.setX(WIDTH / 2);
		currentPosition.setY(HEIGHT / 2);

		targetPosition = new Vector();
		targetPosition.setX(calculator.getRandomTargetPosition(width));
		targetPosition.setY(calculator.getRandomTargetPosition(height));
	}

	/**
	 * @return the currentX
	 */
	public float getCurrentX() {
		return currentPosition.getX();
	}

	/**
	 * @return the currentY
	 */
	public float getCurrentY() {
		return currentPosition.getY();
	}

	/**
	 * @return the targetX
	 */
	public float getTargetX() {
		return targetPosition.getX();
	}

	/**
	 * @return the targetY
	 */
	public float getTargetY() {
		return targetPosition.getY();
	}

	/**
	 * @return the currentPosition
	 */
	public Vector getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * @param currentPosition
	 *            the currentPosition to set
	 */
	public void setCurrentPosition(Vector currentPosition) {
		this.currentPosition = currentPosition;
	}

	/**
	 * @return the targetPosition
	 */
	public Vector getTargetPosition() {
		return targetPosition;
	}

	/**
	 * @param targetPosition the targetPosition to set
	 */
	public void setTargetPosition(Vector targetPosition) {
		this.targetPosition = targetPosition;
	}

}

public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;
	//first constructor
	public Planet(double xP, double yP, double xV, 
		double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	//second constructor
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	//calculate the distance between 2 planets
	public double calcDistance(Planet p) {
		return Math.sqrt(((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + 
			(this.yyPos - p.yyPos) * (this.yyPos - p.yyPos)));
	}
	//calculate the force exerted on this planet by the given planet
	public double calcForceExertedBy(Planet p){
		double radius = this.calcDistance(p);
		return G * this.mass * p.mass / (radius * radius);
	}
	//calculate the force exerted on the x and y direction
	public double calcForceExertedByX(Planet p) {
		return (calcForceExertedBy(p) * (p.xxPos - this.xxPos)) / calcDistance(p);
	}
	public double calcForceExertedByY(Planet p) {
		return (calcForceExertedBy(p) * (p.yyPos - this.yyPos)) / calcDistance(p);
	}
	//calculate the net force exerted on the x and y direction
	public double calcNetForceExertedByX(Planet[] allPlanets){
		double sumNetForceX = 0.0;
		for(int i = 0; i < allPlanets.length; i++){
			if(this.equals(allPlanets[i])){
				continue;
			}
			sumNetForceX = sumNetForceX + calcForceExertedByX(allPlanets[i]);
		}
		return sumNetForceX;
	} 
	public double calcNetForceExertedByY(Planet[] allPlanets){
		double sumNetForceY = 0.0;
		for(int i = 0; i < allPlanets.length; i++){
			if(this.equals(allPlanets[i]))
				continue;
			sumNetForceY = sumNetForceY + calcForceExertedByY(allPlanets[i]);
		}
		return sumNetForceY;
	} 
	//according force and tim, adjusting velocity and postion
	public void update(double time, double xxForce, double yyForce){
		double xxAcc = xxForce / this.mass;
		double yyAcc = yyForce / this.mass;
		double newXXVel = this.xxVel + time * xxAcc;
		double newYYVel = this.yyVel + time * yyAcc;
		this.xxVel = newXXVel;
		this.yyVel = newYYVel;
		this.xxPos = this.xxPos + time * newXXVel;
		this.yyPos = this.yyPos + time * newYYVel;
	}
	//drawing one planet
	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}

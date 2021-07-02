public class NBody{
//return the radius of the universe
	public static double readRadius(String filename) {
		In in = new In(filename);
		int numberOfPlanets = in.readInt();
		return in.readDouble();
	}
//return the array including all planets
	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int numberOfPlanets = in.readInt();
		Planet[] allPlanets = new Planet[numberOfPlanets];
		double radius = in.readDouble();
		//in this situation, only known the condition of ending, should use !while! loop
		for(int i = 0; i < numberOfPlanets; i++){
				double xxPos = in.readDouble();
				double yyPos = in.readDouble();
				double xxVel = in.readDouble();
				double yyVel = in.readDouble();
				double mass = in.readDouble();
				String imgFileName = in.readString();
				allPlanets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return allPlanets;
	}
//main method of NBody class
	public static void main(String[] args){
	//collecting all needed input
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] allPlanets = readPlanets(filename);
		int numberOfPlanets = allPlanets.length;
	//enables double buffering
		StdDraw.enableDoubleBuffering();
	//drawing the background
		//StdDraw.setScale(-2 * radius, 2 *radius);
		//StdDraw.picture(0, 0, "images/starfield.jpg", radius * 2, radius * 2);
	//drawing all planets
		//for(Planet p : allPlanets) p.draw();
	//calculate xNetForce and yNetForce, and store them into arrays
		double time = 0;
		double [] xForces = new double[numberOfPlanets];
		double [] yForces = new double[numberOfPlanets];
		while(time < T){
			for(int i = 0; i < numberOfPlanets; i++){
				xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
			} 
	//update each planet’s position, velocity, and acceleration
		for(int i = 0; i < numberOfPlanets; i++) allPlanets[i].update(dt, xForces[i], yForces[i]);
	//drawing the background
		StdDraw.setScale(-2 * radius, 2 *radius);
		StdDraw.picture(0, 0, "images/starfield.jpg", radius * 2, radius * 2);
		//drawing all planets
		for(Planet p : allPlanets) p.draw();
		StdDraw.show();
		StdDraw.pause(10);
		time += dt;
		}
		
		StdOut.printf("%d\n", allPlanets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < allPlanets.length; i++) {
    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                  allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
		}
	}

}
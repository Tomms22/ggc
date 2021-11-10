package ggc.core;

// The 3 ranks of Partner, with their lowerBound of classification
enum Status {
	NORMAL(0), SELECTION(2000), ELITE(25000);
	private int _points;
	
	Status(int points){
		_points = points;
	}
	
	// For Serialization purposes
	public String toString(){
		return "" + _points;
	}
}


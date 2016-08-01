package society;

public class Coordinate {
	
	private int myX;
	private int myY;

	public Coordinate(int x, int y) {
		setMyX(x);
		setMyY(y);
	}
    
    public Coordinate(Coordinate coordinate) {
        this.myX = coordinate.getMyX();
        this.myY = coordinate.getMyY();
    }

	public int getMyY() {
		return myY;
	}

	public void setMyY(int myY) {
		this.myY = myY;
	}

	public int getMyX() {
		return myX;
	}

	public void setMyX(int myX) {
		this.myX = myX;
	}
    
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate coordinate = (Coordinate) obj;
            return (coordinate.getMyX() == this.getMyX() && coordinate.getMyY() == this.getMyY());
        } else {
            return false;
        }
    }
    
    public int hashCode(){
        int hashcode = 0;
        hashcode = 11*myX + myY^3 + myX + 3*myY;
        return hashcode;
    }

}

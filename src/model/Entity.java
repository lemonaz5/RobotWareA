/**
 * 2110215 Prog Meth Project : Robot Warea
 * @author Wannita Takerngsaksiri (5830478421) , Techin Supapol (5831022021)
 * @version 14 Dec 2016
 */

package model;

public abstract class Entity implements IRenderable {
	
	protected int x,y,z;
	
	public Entity(int x,int y){
		this.x=x;
		this.y=y;
		z=1;
	}
	
	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return z;
	}
	
	public int getX() {
		return x;
	}
	
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void addEntity(IRenderable entity) {
		RenderableHolder.getInstance().add(entity);
	}
	
	public boolean isSamePosition(Entity other){
		if (this.getX()==other.getX() && this.getY()==other.getY()) {
			return true;
		}
		else 
			return false;
	}
}

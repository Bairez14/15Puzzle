import java.util.Arrays;

/*
 * @author Mark Hallenbeck
 * Copyright© 2014, Mark Hallenbeck, All Rights Reservered.
 */

public class Puzzle {

	private int[] key;					
	private String key2;				
	private Puzzle parent;
	private int depth;
	private int hValue;
	
	public Puzzle(int[] puzzle){
		key = puzzle;
		key2 = Arrays.toString(key);
		parent = null;
	}
	
	public int[] getKey(){
		return key;
	}
	
	public String getKey2(){
		return key2;
	}
	
	public Puzzle getParent(){
		return parent;
	}
	
	public void setParent(Puzzle from){
		parent = from;
	}

	public int getDepth(){
		return depth;
	}
	
	public void setDepth(int x){
		depth=x;
	}
	public void set_hValue(int x){
		hValue = x;
	}
	
	public int get_hValue(){
		return hValue;
	}
}

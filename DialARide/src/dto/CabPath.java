package dto;
public class CabPath {
	private int time;
	private int loc;
	private int capacityUsed;
	private int reqNo;
	private boolean startLoc;
	
	public int getCapacityUsed() {
		return capacityUsed;
	}
	public void setCapacityUsed(int capacityUsed) {
		this.capacityUsed = capacityUsed;
	}
	private CabPath next;
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getLoc() {
		return loc;
	}
	public void setLoc(int loc) {
		this.loc = loc;
	}
	public CabPath getNext() {
		return next;
	}
	public void setNext(CabPath next) {
		this.next = next;
	}
	public int getReqNo() {
		return reqNo;
	}
	public void setReqNo(int reqNo) {
		this.reqNo = reqNo;
	}
	public boolean isStartLoc() {
		return startLoc;
	}
	public void setStartLoc(boolean startLoc) {
		this.startLoc = startLoc;
	}
}

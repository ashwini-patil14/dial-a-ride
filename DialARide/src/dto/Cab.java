package dto;
public class Cab 
{
private RequestGroup rg[];
private int cabNo;
private CabPath head; //head pointing to start of cabPath location
private int totRG;
private int totalRevenue;
private int finalTime;
	public Cab(int n)
	{
		finalTime=0;
		totRG=0;
		setTotalRevenue(0);
		rg=new RequestGroup[n];
		for(int i=0;i<n;i++)
		{
			rg[i]=new RequestGroup();
		}
	}

	//Print final output
	public void printCab()
	{
		System.out.println("CabNo="+cabNo);
		System.out.println("Revenue="+totalRevenue);
		CabPath temp=head;
		String path="";
		String time="";
		String capUsed="";
		String req="";
		while(temp!=null)
		{
			path+=temp.getLoc()+",";
			time+=temp.getTime()+",";
			capUsed+=temp.getCapacityUsed()+",";
			req+=temp.getReqNo()+",";
			temp=temp.getNext();
		}

		System.out.println("Path: "+path);
		System.out.println("ReqNo "+req);
		System.out.println("Time: "+time);
		System.out.println("Capacity "+capUsed);
		System.out.println();
	}
	
	public int getTotRG() {
		return totRG;
	}
	
	public void setTotRG(int totRG) {
		this.totRG= totRG;
	}
	
	public RequestGroup[] getRg() {
		return rg;
	}
	
	public void addRg(RequestGroup rg) {
		this.rg[totRG] = rg;
	}
	
	public CabPath getHead() {
		return head;
	}
	
	public void setHead(CabPath head) {
		this.head = head;
	}
	public int getCabNo() {
		return cabNo;
	}
	
	
	public void setCabNo(int cabNo) {
		this.cabNo = cabNo;
	}

	public int getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(int totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	
	public int getFinalTime() {
		return finalTime;
	}
	
	public void setFinalTime(int finalTime) {
		this.finalTime = finalTime;
	}

}

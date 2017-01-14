package dto;
import biz.AllPairShortestPath;
import io.OutputInFile;

public class RequestGroup {
private int largestStartTime;
private int smallestEndTime;
private int count;
private int startLoc;
private boolean visited;
private int revenue;
private Request requestOrder[];
private int pathTraversed[];
private boolean serviced[];
OutputInFile of=new OutputInFile();
	public int[] getPathTraversed() {
	return pathTraversed;
}
public int getTotalTimeTakenByRG(AllPairShortestPath ob)
{
	int total=0;
	for(int i=1;i<=count;i++)
		total+=ob.calcTime(pathTraversed[i-1],pathTraversed[i]);
	return total;
}
public void setPathTraversed(int[] pathTraversed) {
	this.pathTraversed = pathTraversed;
}
	public RequestGroup() {
		requestOrder=new Request[5];
		serviced=new boolean[6];

		pathTraversed=new int[6];
		for(int i=0;i<5;i++)
			requestOrder[i]=new Request();
	}
	public void setServiced(int i)
	{
		serviced[i]=true;
	}
	public boolean getServiced(int i)
	{
		return serviced[i];
	}
	public int getLastLoc()
	{
		return pathTraversed[count];
	}
	public int getStartLoc() {
		return startLoc;
	}
	public void setStartLoc(int startLoc) {
		this.startLoc = startLoc;
	}
	public int getSmallestEndTime() {
		return smallestEndTime;
	}
	public void setSmallestEndTime(int smallestEndTime) {
		this.smallestEndTime = smallestEndTime;
	}
	public int getLargestStartTime() {
		return largestStartTime;
	}
	public void setLargestStartTime(int largestStartTime) {
		this.largestStartTime = largestStartTime;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public boolean isVisited() {
		return visited;
	}
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public int getRevenue() {
		return revenue;
	}
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
	public Request[] getRequestOrder() {
		return requestOrder;
	}
	public void setRequestOrder(Request[] pathOrder) {
		this.requestOrder = pathOrder;
	}
	public void print_RG(int index)
	{
		of.outputFile("Start Loc "+startLoc, "ReqGroup");
		of.outputFile("Largest start time="+largestStartTime, "ReqGroup");
		String tmp="Request: ";
		for(int i=0;i<count;i++)
			tmp+=requestOrder[i].getReqNo()+",";
			of.outputFile(tmp, "ReqGroup");
			tmp="";
			tmp="Path: ";
		for(int i=0;i<=count;i++)
			tmp+=pathTraversed[i]+",";
			of.outputFile(tmp, "ReqGroup");
		of.outputFile("", "ReqGroup");
	}

}

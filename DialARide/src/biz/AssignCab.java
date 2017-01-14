package biz;
import dto.Cab;
import dto.CabPath;
import dto.RequestGroup;
import io.OutputInFile;

public class AssignCab {
	CabPath cp[];
	Cab cab[];
	int noOfReqG;
	int totalCabs;
	RequestGroup rg[];
	int skipCount;
	AllPairShortestPath ob;
	public AssignCab(int startLoc[],int n, RequestGroup rg[], AllPairShortestPath ob)
	{
		this.ob=ob;
		skipCount=0;
		this.rg=rg;
		noOfReqG=n;
		totalCabs=startLoc.length;
		cp=new CabPath[startLoc.length];
		cab=new Cab[startLoc.length];
		for(int i=0;i<startLoc.length;i++)
		{
			cab[i]=new Cab(noOfReqG);
			cab[i].setCabNo(i+1);
			cp[i]=new CabPath();
			cp[i].setLoc(startLoc[i]);
			cp[i].setTime(0);
			cp[i].setNext(null);
			cp[i].setCapacityUsed(0);
			cab[i].setHead(cp[i]);
		}
	}
	public void assignCab(int capacity) throws Exception
	{
		int revenue=0;
		for(int i=0;i<noOfReqG;i++)
		{
//			if(i%2==0)
			{
			
			if(!rg[i].isVisited())
			findAvailableClosestCab(rg[i], capacity);	
/*			System.out.println("Cabs till now");
			for(int j=0;j<cab.length;j++)
				cab[j].printCab();*/

			}
		}
/*			for(int i=0;i<noOfReqG;i++)
		{
			if(i%2!=0 )
			{
				findAvailableClosestCab(rg[i], capacity);	
				System.out.println("Cabs till now");
				for(int j=0;j<cab.length;j++)
					cab[j].printCab();
			}
		}
*/
		for(int i=0;i<cab.length;i++)
			cab[i].printCab();
		for(int i=0;i<cab.length;i++)
			revenue+=cab[i].getTotalRevenue();
		System.out.println("Total revenue generated "+revenue+" request skipped "+(skipCount));
		
	}
	public void findAvailableClosestCab(RequestGroup rg, int capacity) throws Exception
	{
		int closestCab=-1,i=0;
		int minTime=1000;
		CabPath prev=null;
		OutputInFile of=new OutputInFile();
		
		for(i=0;i<totalCabs;i++) //check for the closest cab at that time
		{
				CabPath temp=cab[i].getHead().getNext();
				CabPath temp1=cab[i].getHead();

				//temp1 gives the request after which present request might be added
				while((temp!=null && (temp.getTime()<rg.getLargestStartTime()) ||(temp!=null && temp.getTime()<rg.getSmallestEndTime())) /*&& !temp.isStartLoc()*/) 
				{
					temp1=temp;
					temp=temp.getNext();

				}
				//Checking if adding this request would increase time above 1440
/*				if(cab[i].getFinalTime()+ob.calcTime(temp1.getLoc(), rg.getStartLoc())>1440)
				continue;*/

				//Check if the request can be added to the present cab, time wise and capacity wise
				if(temp==null)
				{
					if((temp1.getTime()+ob.calcTime(temp1.getLoc(), rg.getStartLoc())<=rg.getLargestStartTime()
							|| temp1.getTime()+ob.calcTime(temp1.getLoc(), rg.getStartLoc())<=rg.getSmallestEndTime()) 
							&& rg.getCount()+temp1.getCapacityUsed()<=capacity)
					{
						//Check if this is the closest cab for the present request
						if(ob.calcTime(temp1.getLoc(), rg.getStartLoc())<minTime)
						{
						minTime=ob.calcTime(temp1.getLoc(), rg.getStartLoc());
						closestCab=i;
						prev=temp1;
						}
					}
				}
				else
				//if the cab is to be inserted between temp and temp1
				{
					//check for capacity for temp as well as temp1
					if(rg.getCount()+temp1.getCapacityUsed()<=capacity && rg.getCount()+temp.getCapacityUsed()<=capacity )
					{
						//Check if requestGroup's largest start time or minimum end time can be accomodated between temp1 and temp
						if((temp1.getTime()+ob.calcTime(temp1.getLoc(), rg.getStartLoc())<=rg.getLargestStartTime() /*&& rg.getLargestStartTime()+ob.calcTime(rg.getStartLoc(), temp.getLoc())<=temp.getTime()*/)
								||(temp1.getTime()+ob.calcTime(temp1.getLoc(), rg.getStartLoc())<=rg.getSmallestEndTime() /*&& rg.getSmallestEndTime()+ob.calcTime(rg.getStartLoc(), temp.getLoc())<=temp.getTime())*/))
						{
							//Check if this is the closestCab
							if(ob.calcTime(temp1.getLoc(), rg.getStartLoc())<minTime)
							{
							minTime=ob.calcTime(temp1.getLoc(), rg.getStartLoc());
							closestCab=i;
							prev=temp1;
							}
						}
					}					
				}	
			}
		
		if(prev!=null || closestCab!=-1) //Closest Cab found for the request
		{
			addRequestInCab(closestCab, prev, rg);
		}
		else //Request cannot be accommodated in the existing cabs at given time
		{	skipCount+=rg.getCount();
			of.outputFile("Skipped req grp"+rg.getLargestStartTime()+" loc "+rg.getStartLoc(),"Skip");
		}

	}
	void addRequestInCab(int closestCab, CabPath prev, RequestGroup rg)
	{
		int i;
		//Create a newNode for the pick up of the RequestGroup
		CabPath newNode=new CabPath();
		newNode.setCapacityUsed(rg.getCount()+prev.getCapacityUsed());
		newNode.setLoc(rg.getStartLoc());
		
		if(prev.getTime()+ob.calcTime(prev.getLoc(), rg.getStartLoc())<=rg.getLargestStartTime())
		newNode.setTime(rg.getLargestStartTime());
		else
		{
		newNode.setTime(rg.getSmallestEndTime());	
		}
		newNode.setNext(null);
		newNode.setStartLoc(true);
		rg.setServiced(0);
		
		//Next of prev is null ->only drops remaining are of the present requestGroup, 
		//so add them on the path after newNode
		if(prev.getNext()==null)
		{
			CabPath temp=newNode;
			for(i=1;i<=rg.getCount();i++)
			{
			CabPath temp1=new CabPath();
			int path[]=ob.getCompletePath(rg.getPathTraversed()[i-1], rg.getPathTraversed()[i]);

			//Direct path between path[i-1] and path[i]
				if(path.length==2)
				{
						temp1.setLoc(rg.getPathTraversed()[i]);
						temp1.setCapacityUsed(temp.getCapacityUsed()-1); //path[i] is a drop location	
						temp1.setTime(temp.getTime()+ob.calcTime(temp.getLoc(),temp1.getLoc() ));
						temp1.setReqNo(rg.getRequestOrder()[i-1].getReqNo());
						temp1.setNext(null);
						temp.setNext(temp1);
						temp=temp.getNext();
						rg.setServiced(i);
				}
				else //Path from i-1 to i via some other locations, then add them too
				{
					for(int j=1;j<path.length;j++)
					{
						temp1=new CabPath();
						temp1.setLoc(path[j]);
						if(rg.getPathTraversed()[i]==path[j]) //if j is the destination
						{
						temp1.setReqNo(rg.getRequestOrder()[i-1].getReqNo());
						temp1.setCapacityUsed(temp.getCapacityUsed()-1);
						}
						else
						temp1.setCapacityUsed(temp.getCapacityUsed());
						temp1.setTime(temp.getTime()+ob.calcTime(temp.getLoc(),path[j] ));
						temp1.setNext(null);
						temp.setNext(temp1);
						temp=temp.getNext();
					}
					rg.setServiced(i); //i request of request group is serviced
				}
			}
			//temp is the final node
			cab[closestCab].setFinalTime(temp.getTime());
		}
		else
		//Adjust capacity and time for nodes after prev and add the drops of present request at the end 
		{
			newNode.setNext(prev.getNext()); //newNode's next is set to next of its prev
			CabPath temp2=newNode;
			CabPath temp1=newNode.getNext();
			int cnt=0;
			while(temp1!=null)
			{
				temp1.setCapacityUsed(temp1.getCapacityUsed()+rg.getCount()-cnt);
				for(int j=1;j<=rg.getCount();j++)
				{
					//check if any destinations of present request group are already present after prev
					if(temp1.getLoc()==rg.getPathTraversed()[j] && !rg.getServiced(j))
					{
						rg.setServiced(j); //request j in request group is serviced
						cnt++;
						CabPath temp3=new CabPath();
						temp3.setLoc(rg.getPathTraversed()[j]);
						temp3.setCapacityUsed(temp1.getCapacityUsed()-1);
						temp3.setTime(temp1.getTime());
						temp3.setReqNo(rg.getRequestOrder()[j-1].getReqNo());
						temp3.setNext(temp1.getNext());
						temp1.setNext(temp3);
						temp1=temp3;
					}
				}
				temp2=temp1;
				temp1=temp1.getNext();
			}
			CabPath temp=temp2;
			int prevLoc=temp2.getLoc(); //add the drops that are not already there in nodes after prev
			for(i=1;i<=rg.getCount();i++)
				{
				if(!rg.getServiced(i))
				{
					int path[]=ob.getCompletePath(prevLoc, rg.getPathTraversed()[i]);
					temp1=new CabPath();
					if(path.length==2) //If no nodes on the way between prevLoc and i Loc
					{
							temp1.setLoc(rg.getPathTraversed()[i]);
							temp1.setCapacityUsed(temp.getCapacityUsed()-1);
							temp1.setTime(temp.getTime()+ob.calcTime(prevLoc,temp1.getLoc() ));
							temp1.setReqNo(rg.getRequestOrder()[i-1].getReqNo());
							temp1.setNext(null);
							temp.setNext(temp1);
							temp=temp.getNext();
					}
					else
					{
						for(int j=1;j<path.length;j++)
						{
							temp1=new CabPath();
							temp1.setLoc(path[j]);
							if(rg.getPathTraversed()[i]==path[j])
							{
							temp1.setReqNo(rg.getRequestOrder()[i-1].getReqNo());
							temp1.setCapacityUsed(temp.getCapacityUsed()-1);
							}
							else
							temp1.setCapacityUsed(temp.getCapacityUsed());
							temp1.setTime(temp.getTime()+ob.calcTime(temp.getLoc(),path[j] ));
							temp1.setNext(null);
							temp.setNext(temp1);
							temp=temp.getNext();
						}
					}
					rg.setServiced(i);
					prevLoc=rg.getPathTraversed()[i];
				}
			}
				cab[closestCab].setFinalTime(temp.getTime());
		}
		
		//Add all the nodes on the way from prev till newNode to the Cab's path
		if(ob.getCompletePath(prev.getLoc(),newNode.getLoc()).length!=2)
		{
			CabPath temp4=prev;
			CabPath temp1;
			int path[]=ob.getCompletePath(prev.getLoc(),newNode.getLoc());
			for(int j=1;j<path.length-1;j++)
			{
				temp1=new CabPath();
				temp1.setLoc(path[j]);
				temp1.setCapacityUsed(temp4.getCapacityUsed());
				temp1.setTime(temp4.getTime()+ob.calcTime(temp4.getLoc(),path[j] ));
				temp1.setNext(null);
				temp4.setNext(temp1);
				temp4=temp4.getNext();
			}
			temp4.setNext(newNode);
		}
		else //if no nodes between prev and newNode
			prev.setNext(newNode);
		
			rg.setVisited(true);
			cab[closestCab].setTotRG(cab[closestCab].getTotRG()+1);
			cab[closestCab].addRg(rg);
			cab[closestCab].setTotalRevenue(cab[closestCab].getTotalRevenue()+rg.getRevenue());
		}
		
}

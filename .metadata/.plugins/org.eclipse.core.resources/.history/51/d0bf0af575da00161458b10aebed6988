public class Sort {

	int sortedRequestIndex[];
	
	public int[] getSortedRequestIndex() {
		return sortedRequestIndex;
	}
	//Sort arr[] according to field index order[0], followed by order[1] and so on
	public int[][] insertionSort2D(int arr[][],int order[])
	{
		//System.out.println("Sorting requests");
		int temp[];
		int temp1;
		sortedRequestIndex=new int[arr.length];
//		System.out.println("Sorting reuqests");
		for(int i=0;i<arr.length;i++)
			sortedRequestIndex[i]=i;	
		for(int i=0;i<arr.length-1;i++)
		{
			for(int j=i+1;j<arr.length;j++)
			{
				if(arr[i][order[0]]>arr[j][order[0]])
				{
					temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
					temp1=sortedRequestIndex[i];
					sortedRequestIndex[i]=sortedRequestIndex[j];
					sortedRequestIndex[j]=temp1;
				}
				else if(arr[i][order[0]]==arr[j][order[0]] && arr[i][order[1]]>arr[j][order[1]])
				{
					temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
					temp1=sortedRequestIndex[i];
					sortedRequestIndex[i]=sortedRequestIndex[j];
					sortedRequestIndex[j]=temp1;
				}
				else if(arr[i][order[0]]==arr[j][order[0]] && arr[i][order[1]]==arr[j][order[1]] && arr[i][order[2]]>arr[j][order[2]])
				{
					temp=arr[i];
					arr[i]=arr[j];
					arr[j]=temp;
					temp1=sortedRequestIndex[i];
					sortedRequestIndex[i]=sortedRequestIndex[j];
					sortedRequestIndex[j]=temp1;
				}
				
			}
		}
		return arr;
	}
	public RequestGroup[] sortRequestGroup(RequestGroup rg[], int noOfRG)
	{
		RequestGroup temp;
		for(int i=0;i<rg.length-1;i++)
		{
			for(int j=i+1;j<noOfRG;j++)
			{
				if(rg[i].getSmallestEndTime()>rg[j].getSmallestEndTime())
				{
					temp=rg[i];
					rg[i]=rg[j];
					rg[j]=temp;
				}				

				else if(rg[i].getLargestStartTime()>rg[j].getLargestStartTime())
				{
					temp=rg[i];
					rg[i]=rg[j];
					rg[j]=temp;
				}
				
			}
	}
		return rg;
	}
	
}


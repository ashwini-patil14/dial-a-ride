package main;
import biz.AllPairShortestPath;
import biz.AssignCab;
import biz.ProcessRequest;
import io.ReadInput;

public class DialARide {

	public static void main(String[] args) throws Exception{
		//Read Input
		ReadInput ri=new ReadInput();
		ri.readInput(args[0]);
/*		OutputInFile of=new OutputInFile();
		System.out.println("Distance matrix ");
		ri.displayMatrix(ri.getDistMatrix(),ri.getNoOfLocations(),ri.getNoOfLocations());
		of.outputFile("Request matrix","origReq");
		ri.displayMatrix(ri.getRequestMatrix(), ri.getNoOfRequests(), 4,"origReq");
		of.outputFile("Start Locations","origReq");
		for(int i=0;i<ri.getNoOfVehicles();i++)
			of.outputFile(ri.getStartLocations()[i]+" ","origReq");
*/
		//Find All Pair ShortestPath and PathFrom matrices 
		AllPairShortestPath ob=new AllPairShortestPath(ri.getNoOfLocations());
		ob.setDistMatrix(ri.getDistMatrix());
		ob.findShortestPath();
/*		ob.getCompletePath(5, 1);
		ob.getCompletePath(1, 5);
		ob.getCompletePath(2, 2);
		ob.getCompletePath(2, 4);
*/
		//Form RequestGroups of compatible requests which can be processed together
		ProcessRequest pr=new ProcessRequest();
		int n=pr.processReq(ri.getRequestMatrix(), ob,ri.getCapacity());
		
		//Assign cabs to the request groups
		AssignCab assign_cab=new AssignCab(ri.getStartLocations(), n, pr.getRG(),ob);
		assign_cab.assignCab(ri.getCapacity());
	}

}

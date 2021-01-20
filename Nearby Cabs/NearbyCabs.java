import java.io.*;
import java.util.*;
import java.text.*;
public class NearbyCabs {
	final static double RADIUS_EARTH=6357.00;

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		String line;
		String[] details;
		double d;
		int flag=2;
        FileReader fr=new FileReader("C:\\Users\\TCS\\1941012598\\End-Term Project\\src\\Cab Driver.txt");
        DecimalFormat df = new DecimalFormat("#0.000");   //format the calculated distance
        FileWriter fw = new FileWriter("Available Cabs.txt"); 
        BufferedReader in = new BufferedReader(fr);
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter your Latitude : ");
        double lat = sc.nextDouble();
        System.out.print("Enter your Longitude : ");
        double lon = sc.nextDouble();
        System.out.print("\n\n");
	fw.write("Nearby availabe Cabs :-\n\n");
        while((line=in.readLine()) != null) {
        	details=line.split(" ");
        	d=distance(toRadians(lat),toRadians(lon),toRadians(Double.parseDouble(details[1])),toRadians(Double.parseDouble(details[2])));
        	if(d <= 10.00) {
			flag=0;
			if(d < 1){
			  d=d*1000.0;
			   flag=1;
			  }
        		fw.write("Name : "+details[3]+"\nUser ID : "+details[0]+"\n");
			if(flag == 1)
			   fw.write("At distance : "+df.format(d)+" M \n\n");
			else 
			   fw.write("At distance : "+df.format(d)+" KM \n\n");
        	}
        	
        	
        }
	if(flag == 2)
	fw.write("Sorry! No Nearby Cabs Available .");
        fw.close();
        
        
	}
	static double toRadians(double value) {
		//This Method Converts Degree into radian
		return value*(Math.PI/180.0);
	}

	//This method calculates the distance between the two points
	static double distance(double lat1 ,double lon1, double lat2, double lon2) {
		
		double alpha=Math.acos(Math.sin(lon1)*Math.sin(lon2) + Math.cos(lon1)*Math.cos(lon2*Math.cos(lat1-lat2)));
		return alpha*RADIUS_EARTH;

	}
}

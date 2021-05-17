package projet1;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class revision {

	public static void main(String[] args) throws IOException {
		
		
		 FileInputStream fis = new FileInputStream("src/projet1/pass.txt");
		 int n = 5;
		 byte [] b2 = {15,16,17,18,19};
		 int x ;
			 while((x=fis.read(b2,2,3)) != -1) {
				 System.out.println(x);
				 x=fis.read(b2,2,3);
				 System.out.println(b2[4]);
				 System.out.println("sdfghjk");
				
			 for(int i=0; i<n; i++) {
			 System.out.print(b2[i]+" "); 
			 System.out.println();
	}
			 
			 
			
			  }
			  fis.close();
		// TODO Auto-generated method stub

	}

}

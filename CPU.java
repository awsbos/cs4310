import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CPU
{
	public static int[][] physicalMemory;
	public static TLB tlb;
	public static PageTable pageTable;
	
	
	public static void initiate(String instructionFile)
	{
		// [pf#][page of addressable data]
		physicalMemory = new int[16][256];
		tlb = new TLB(16);
		pageTable = new PageTable(256);
		
		
		File file = new File("test_files", instructionFile + ".txt"); 
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader(file));
			String st; 
			while((st = br.readLine()) != null) 
			{
				int job = Integer.parseInt(st);
				if(job == 0)
				{
					// read job
					MMU.read();
				} else {
					// write job
					MMU.write();
				}
			}
			/*while((st = br.readLine()) != null) 
			{
				System.out.println(st);
				//contents[loc] = st;
				loc++;
			}*/
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the file!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read in the file at " + file.toString());
			e.printStackTrace();
		}
	}
}
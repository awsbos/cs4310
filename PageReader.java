import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PageReader
{
	// feed in the name of a page_file
	// example 00.pg is actually "00"
	public static void readPageFile(String address, int index)
	{
		address = address.substring(0, 2);
		File file = new File("page_files_copy", address + ".pg"); 
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader(file));
			String st; 
			int loc = 0;
			while((st = br.readLine()) != null) 
			{
				int currentInt = Integer.parseInt(st);
				CPU.physicalMemory[index][loc] = currentInt;
				loc++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the file!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read in the file at " + file.toString());
			e.printStackTrace();
		}
		//printOut(CPU.physicalMemory[frameNumber]);
	}
	public static void printOut(int[] input)
	{
		for(int i = 0; i < input.length; i++)
		{
			System.out.println(input[i]);
		}
	}
}
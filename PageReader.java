import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PageReader
{
	// feed in the name of a page_file
	// example 00.pg is actually "00"
	public static void readPageFile(String address) throws IOException
	{
		String[] contents = new String[1024];
		File file = new File("page_files", address + ".pg"); 
		BufferedReader br;
		try
		{
			br = new BufferedReader(new FileReader(file));
			String st; 
			int loc = 0;
			while((st = br.readLine()) != null) 
			{
				contents[loc] = st;
				loc++;
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the file!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read in the file at " + file.toString());
			e.printStackTrace();
		}
		//printOut(contents);
	}
	public static void printOut(String[] input)
	{
		for(int i = 0; i < input.length; i++)
		{
			System.out.println(input[i]);
		}
	}
}
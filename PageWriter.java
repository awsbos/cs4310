import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PageWriter
{
	public static void writeFile(String pageFileAddress, int pageFrameNumber)
	{
		try {
			File file = new File("page_files_copy", pageFileAddress + ".pg"); 
			PrintWriter writer = new PrintWriter(file);
			for(int i = 0; i < CPU.physicalMemory[pageFrameNumber].length; i++)
			{
				writer.write(CPU.physicalMemory[pageFrameNumber][i] + "\n");
			}
			writer.close();
			//System.out.println("Page file: " + pageFileAddress + ".pg" + " has been created!");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
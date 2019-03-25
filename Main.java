import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.*;

public class Main
{
	public static StringBuilder fileOutputData = null;
	private static String currentTestFileName = "";
	public static void main(String[] args)
	{
		copyPageFiles();
		if(args.length == 0)
		{
			System.out.println("Please give the program an argument which is a text file name.  Example: testdata1.txt");
		} else {
			fileOutputData = new StringBuilder();
			currentTestFileName = args[0];
			// csv headers
			fileOutputData.append("Address,");
			fileOutputData.append("r/w,");
			fileOutputData.append("value,");
			fileOutputData.append("soft,");
			fileOutputData.append("hard,");
			fileOutputData.append("hit,");
			fileOutputData.append("evicted_pg#,");
			fileOutputData.append("dirty_evicted_page,");
			fileOutputData.append('\n');
			// time to run program
			CPU.initiate(args[0]);
			OS.remainingWrite();
			// finally writes data out to .csv file
			writeFile(fileOutputData);
			clearAllResources();
		}
	}
	public static void clearAllResources()
	{
		CPU.physicalMemory = null;
		CPU.tlb = null;
		OS.hand = null;
		OS.lastAddress = null;
		OS.lastEntry = null;
		OS.pageTable = new PageTable(256);
		MMU.firstLoop = true;
		MMU.tlbIndex = 0;
	}
	public static void writeFile(StringBuilder sb)
	{
		try {
			PrintWriter writer = new PrintWriter(new File(currentTestFileName + ".csv"));
			writer.write(sb.toString());
			writer.close();
			System.out.println("Output file: " + currentTestFileName + ".csv" + " has been created!");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void copyPageFiles()
	{
		File master = new File("page_files");
		if(!master.exists())
		{
			System.out.println("page_files directory does not exist!");
			System.exit(0);
		}
		File copy = new File("page_files_copy");
		copy.mkdir();
		for(int i = 0; i < master.listFiles().length; i++)
		{
			String nameOfFile = master.listFiles()[i].getName();
			try {
				Files.copy(master.listFiles()[i].toPath(), new File(copy.getName() + "\\" + nameOfFile).toPath(), REPLACE_EXISTING);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Main
{
	private static StringBuilder fileOutputData = null;
	private static String currentTestFileName = "";
	public static void main(String[] args)
	{
		if(args.length == 0)
		{
			System.out.println("Please give the program arguments which are text file names.  Example: testdata1.txt");
		} else {
			for(int i = 0; i < args.length; i++)
			{
				fileOutputData = new StringBuilder();
				currentTestFileName = args[i];
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
				CPU.initiate(args[i]);
				// finally writes data out to .csv file
				writeFile(fileOutputData);
			}
		}
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
}
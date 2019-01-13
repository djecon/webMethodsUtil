import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

public class ConvertStatsLogs
{
	public static void main(String args[])
	{
		if (args == null) {
			System.exit(0);
		} else if (args.length == 0) {
			System.out.println("-i  input file name - required");
			System.out.println("-o  output file name - optional, if not provided input file name will be suffixed with .out");
			System.exit(0);
		}
		if (!args[0].equals("-i")) {
			System.out.println("-i  input file name - required");
			System.out.println("-o  output file name - optional, if not provided input file name will be suffixed with .out");
			System.exit(0);
		}
		int lineCount = 0;
		int skippedCount = 0;
		int writeCount = 0;
		String userDir = System.getProperty("user.dir");
		String sep = System.getProperty("file.separator");
		String inputFileName = userDir + sep +  args[1]  ;
		String outputFileName;
		String columnHeader = "Date,Time,Zone,JVMTotalMem,JVMFreeMem,CurrentThreads,MaxThreads,SSNs,SSNx,SSNAvg,REQs,REQx,REQAvg,StartReq,EndReq\r\n";

		if (args.length == 2)  {
			String [] inTemp = args[1].split("\\.");
			outputFileName = userDir + sep + inTemp[0] + ".out";
			System.out.println("Output Filename defaulted to " + outputFileName);
		}
		else {
			outputFileName = userDir + sep + args[3];
		}
		File f = new File(inputFileName);
		if (!f.exists()) {
			System.out.println("Warning - Input file not found");
			System.exit(9);
		}
		Path p = Paths.get(outputFileName);

		try (OutputStream out = new BufferedOutputStream (new FileOutputStream(outputFileName))) {
			System.out.println("Converting");
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(inputFileName));
				String line;
				while ((line = br.readLine()) != null) {
					lineCount += 1;
					String[] attributes = line.split(" ");

					if (line.length() == 0 || attributes[3].contains("---(")) {
						skippedCount += 1;
						continue;
					}
					if (writeCount == 0) {
						byte b[]=columnHeader.getBytes(); 
						out.write(b);
					}
					String tempLine = attributes[0] + "," 
							+ attributes[1] + "," 
							+ attributes[2] + "," 
							+ Long.parseLong(attributes[3], 16) + ","
							+ Long.parseLong(attributes[4], 16) + ","
							+ Long.parseLong(attributes[5], 16) + ","
							+ Long.parseLong(attributes[6], 16) + ","
							+ Long.parseLong(attributes[7], 16) + ","
							+ Long.parseLong(attributes[8], 16) + ","
							+ Long.parseLong(attributes[9], 16) + ","
							+ Long.parseLong(attributes[10], 16) + ","
							+ Long.parseLong(attributes[11], 16) + ","
							+ Long.parseLong(attributes[12], 16) + ","
							+ Long.parseLong(attributes[13], 16) + ","
							+ Long.parseLong(attributes[14], 16) +
							"\r\n"
							;
					byte b[]=tempLine.getBytes(); 
					out.write(b);
					writeCount += 1;
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (br != null) {
						br.close();		
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}  
		} catch (IOException x) {
			System.err.println(x);
		}
		System.out.println("Input File Line Count: " + lineCount);
		System.out.println("Lines ignored: " + skippedCount);
		System.out.println("Lines Written: " + writeCount);
	}
}
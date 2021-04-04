package in.amazon.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class FileHelper
{
	String resultFilePath = ".\\AmazonTestData\\result.txt";
	
	public void writeToAFile(String content)
	{
		BufferedWriter bw = null;
		try 
		{
			bw = new BufferedWriter(new FileWriter(resultFilePath, true));
			bw.write(content);
			bw.newLine();
			bw.flush();
		} 
		catch (IOException ioException) 
		{
			ioException.printStackTrace();
		} 
		finally 
		{                      
			if (bw != null) 
				try 
				{
					bw.close();
				} 
				catch (IOException ioException2) 
				{
					ioException2.printStackTrace();
				}
		} 
	}
	
	public String getFileContents(File file) throws IOException
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), Charset.defaultCharset()));

			StringBuffer sb = new StringBuffer();
			try
			{
				String currentLine;
				while ((currentLine = reader.readLine()) != null)
				{
					sb.append(currentLine);
					sb.append(" | ");
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					reader.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			return sb.toString();
	}
	
	public void deletContentInFile(String content)
	{
		try 
		{
			File resultFile = new File(resultFilePath);
			FileWriter fileWriter = new FileWriter(resultFile.getAbsoluteFile());
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);
			bufferedWriter.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e);
		}
	}
}

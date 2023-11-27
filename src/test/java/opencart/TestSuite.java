package opencart;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestSuite {

	public static void browserSetup() throws IOException, InterruptedException {

		/*
		 * TestListenerAdapter tla = new TestListenerAdapter(); TestNG testng = new
		 * TestNG(); List<String> suites = Lists.newArrayList(); String currentDirectory
		 * = System.getProperty("user.dir");
		 * suites.add(currentDirectory+"testng.xml");// path to xml..
		 * testng.setTestSuites(suites); testng.run();
		 */
		String dirPath = "./src/test/resources/";
		File directory = new File(dirPath);
		int folderCount = 0;
		// Check if the provided path is a directory
		if (directory.isDirectory()) {
			File[] files = directory.listFiles();
			
			// Count files and directories
			for (File file : files) {

				if (file.isDirectory()) {
					folderCount++;
				}
			}
		}
		if(folderCount == 0)
		{	
			
			List< String> browsers = new ArrayList<>();
			
			browsers.add("https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/119.0.6045.105/linux64/chrome-linux64.zip");
			browsers.add( "https://edgedl.me.gvt1.com/edgedl/chrome/chrome-for-testing/119.0.6045.105/win64/chrome-win64.zip");
			
			for(String fileURL: browsers)
			{
			
			 
			String saveDir = "./src/test/resources/";
			String fileName = "";
			
			 try {
		            URL url = new URL(fileURL);
		            Path directoryPath = Paths.get(saveDir);

		            // Create the directory if it doesn't exist
		            if (!Files.exists(directoryPath)) {
		                Files.createDirectories(directoryPath);
		            }

		            // Get the file name from the URL
		            fileName = fileURL.substring(fileURL.lastIndexOf('/') + 1);

		            // Open a connection to the URL
		            InputStream inputStream = url.openStream();
		            FileOutputStream outputStream = new FileOutputStream(saveDir + fileName);

		            // Read data from the URL and write it into the file
		            byte[] buffer = new byte[1024];
		            int bytesRead;
		            while ((bytesRead = inputStream.read(buffer)) != -1) {
		                outputStream.write(buffer, 0, bytesRead);
		            }

		            // Close streams
		            outputStream.close();
		            inputStream.close();

		            System.out.println("File downloaded to: " + saveDir + fileName);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
			 
			 //extracting from the zip file
			 UnzipInSameFolder.unzip(saveDir+fileName);
			 
			 //deleting the zip files after extraction
			 File fileToDelete = new File(saveDir+fileName);

		        if (fileToDelete.exists()) {
		            if (fileToDelete.delete()) {
		                System.out.println("ZIP File deleted successfully.");
		            } else {
		                System.out.println("Failed to delete the file.");
		            }
		        } else {
		            System.out.println("ZIP File does not exist at the specified path.");
		        }
			}
			
		}
		
	}

	/*
	 * @Test public void executeTestSuite() { TestNG testng = new TestNG();
	 * List<Class<?>> classes = new ArrayList<>();
	 * 
	 * // Add test classes to the suite classes.add(Shopping.class);
	 * 
	 * // ... add more test classes if needed
	 * 
	 * testng.setTestClasses(classes.toArray(new Class[0])); testng.run(); }
	 */

}

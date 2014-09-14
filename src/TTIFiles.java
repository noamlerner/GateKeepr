import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;


public class TTIFiles {
	static String filePath="";
	private static String getPath(){
		if(filePath.equals("")){
			try {
				File prop = new File("C:/tti/idscan/prop.tti");
				byte[] bytes = Files.readAllBytes(prop.toPath());
				String contents = new String(bytes);
				int begIndex = contents.indexOf("LogPath|")+"LogPath|".length();
				int endIndex = contents.indexOf("MaxIdimgSize|");
				filePath = contents.substring(begIndex,endIndex).trim();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return filePath;
	}

	private static ArrayList<Scannable> getScannablesFromTemp(){
		ArrayList<Scannable> scans = new ArrayList<Scannable>();
		File directory = new File(getPath()).getAbsoluteFile();

		File[] files = directory.listFiles();
		for (int i = 0; i < files.length; i++)
		{
			String filePath = files[i].getAbsolutePath();
			if (filePath.substring(filePath.length() - 18).equals("_scannedXMLlog.tti"))
			{
				Scannable s = new Scannable(files[i]);
				scans.add(s);
				archiveFile(files[i]);

			}
		}
		return scans;
	}
	public static Scannable getScannable(){
		File directory = new File(getPath()).getAbsoluteFile();
		File[] files = directory.listFiles();
		Scannable scannable= null;
		for (int i = 0; i < files.length; i++)
		{
			String filePath = files[i].getAbsolutePath();
			if (filePath.substring(filePath.length() - 18).equals("_scannedXMLlog.tti"))
			{
				scannable = new Scannable(files[i]);
				archiveFile(files[i]);
			}
		}
		return scannable;
	}

	private static void archiveFile(File file){
		File archived = new File(getPath()+ "/archived");
		if(!archived.exists()){
			archived.mkdir();
		}
		Calendar cal = Calendar.getInstance(); 
		String date = String.valueOf(cal.getTimeInMillis());
		String newPath = getPath()+"archived/"+date+".tti";
		file.renameTo(new File(newPath));
	}
}

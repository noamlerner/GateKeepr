

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.image.Image;

public class Scannable
implements Comparable<Scannable>
{

	static String[] BEG = {"<FirstName>","<LastName>","<Address>","<City>",
		"<State>","<Zip>","<Country>","<IDtype>","<Id>","<PassportNumber>",
		"<DOBISO>","<_IssueDate>","<ExpireISO>","<Sex>"};

	static String [] END = {"</FirstName>","</LastName>","</Address>","</City>",
		"</State>","</Zip>","</Country>","</IDtype>","</Id>","</PassportNumber>",
		"</DOBISO>","</_IssueDate>","</ExpireISO>","</FirstName>","</Sex>"};
	static String BEG_IMG_PATH = "<ImageName>";
	static String END_IMG_PATH = "</ImageName>";
	
	static int INFO_SIZE = 18;
	
	static int FIRST_NAME = 0;
	static int LAST_NAME = 1;
	static int ADDRESS = 2;
	static int CITY = 3;
	static int STATE = 4;
	static int ZIP = 5;
	static int COUNTRY = 6;
	static int ID_TYPE = 7;
	static int DL_NUM =8;
	static int PP_NUM =9;
	static int DATE_OF_BIRTH = 10;
	static int ISSUE_DATE = 11;
	static int EXPIRE_DATE = 12;
	static int SEX = 13;
	static int CHECK_IN_DATE = 14;
	static int ALL_CHECK_IN_DATES = 15;
	
	
	static int BLACKLIST = 16;
	static int BLACKLIST_REASON = 17;
	//when adding something make sure to update getInfo() in controller or all hell breaks loose
	
	static int SORT_BY = 0;
	static int SORT_BY_NAME = 0;
	static int SORT_BY_BLACK_LIST =1;
	static int SORT_BY_CHECK_IN_DATE = 2;

	String [] info = new String[INFO_SIZE];
	String imgPath;
	String fullName;


	public Scannable() {}

	public boolean blackListed(){
		if(info[BLACKLIST].equalsIgnoreCase("N"))return false;
		else return true;

	}
	
	public Scannable(File file)
	{
		try
		{	
			byte[] bytes = Files.readAllBytes(file.toPath());
			String contents = new String(bytes);

			for(int i =0; i <BEG.length;i++){
				int begIndex = contents.indexOf(BEG[i]);
				int endIndex = contents.indexOf(END[i]);
				try{
					info[i] = contents.substring(begIndex + BEG[i].length(), endIndex);
				}
				catch(Exception e){
					info[i] = "";
				}

			}
			int begImg = contents.indexOf(BEG_IMG_PATH);
			int endImg = contents.indexOf(END_IMG_PATH);
			imgPath = (file.getParent() + "\\" + contents.substring(begImg + BEG_IMG_PATH.length(), endImg));
			fullName = info[FIRST_NAME] + " " + info[LAST_NAME];
			info[BLACKLIST] = "N";
			info[BLACKLIST_REASON] = "";
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy - kk:mm:ss");
			info[CHECK_IN_DATE] = sdf.format(new Date(file.lastModified()));
			info[ALL_CHECK_IN_DATES] = info[CHECK_IN_DATE];
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public Scannable(String [] infoParam, String imgPathParam){
		info = infoParam;
		imgPath = imgPathParam;
		fullName = info[FIRST_NAME] + " " + info[LAST_NAME];
	}



	public int compareTo(Scannable s)
	{	if(SORT_BY == SORT_BY_BLACK_LIST)
			return s.sortBy().compareTo(sortBy());
		return sortBy().compareTo(s.sortBy());
	}

	public String[] getInfo(){
		return info;
	}

	public String sortBy(){
		if(SORT_BY == SORT_BY_NAME)
			return fullName;
		else if(SORT_BY == SORT_BY_BLACK_LIST)
			return  info[BLACKLIST]+ " - " + fullName;
		else if(SORT_BY == SORT_BY_CHECK_IN_DATE)
			return info[CHECK_IN_DATE];
		else return fullName;
	}
	public String toString(){
		return fullName;
	}
	static public void setSort(int sort){
		SORT_BY = sort;
	}
	public void setInfo(String [] infoParam){
		info = infoParam;
	}
	public Image getImage()
	{
		try
		{
			return new Image("File:" + imgPath);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public Image getFace()
	{
		try
		{
			return new Image("File:" + imgPath.substring(0,imgPath.length()-4)+"_face.jpg");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
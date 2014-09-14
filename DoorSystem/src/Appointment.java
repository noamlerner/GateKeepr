import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;

import org.json.*;

public class Appointment {
	private boolean appointmentExists = false;
	public static int YEAR = 0;
	public static int MONTH = 1;
	public static int DAY = 2;
	public static int HOUR = 3;
	public static int MIN = 4;
	public static int SEC = 5;
	private String buildingID = "";
	private String tenantID = "";
	private int[] arrivalTime = new int[6];
	private int[] endArrivalTime = new int[6];
	private int duration;
	private String id = "";
	long arrivalMS;
	long endArrivalMS;

	public Appointment(JSONObject appointmentJson) {
		try {
			buildingID = appointmentJson.getString("building_id");
			String date = appointmentJson.getString("arrival_time");
			arrivalTime[YEAR] = Integer.valueOf(date.substring(0, 4))
					.intValue();
			arrivalTime[MONTH] = Integer.valueOf(date.substring(5, 7))
					.intValue() - 1;
			arrivalTime[DAY] = Integer.valueOf(date.substring(8, 10))
					.intValue();
			arrivalTime[HOUR] = Integer.valueOf(date.substring(11, 13))
					.intValue();
			arrivalTime[MIN] = Integer.valueOf(date.substring(14, 16))
					.intValue();
			arrivalTime[SEC] = Integer.valueOf(date.substring(17, 19))
					.intValue();
			for (int i = 0; i < arrivalTime.length; i++) {
				System.out.print(arrivalTime[i] + ",");
			}
			System.out.println("");
			date = appointmentJson.getString("end_arrival_time");
			endArrivalTime[YEAR] = Integer.valueOf(date.substring(0, 4))
					.intValue();
			endArrivalTime[MONTH] = Integer.valueOf(date.substring(5, 7))
					.intValue() - 1;
			endArrivalTime[DAY] = Integer.valueOf(date.substring(8, 10))
					.intValue();
			endArrivalTime[HOUR] = Integer.valueOf(date.substring(11, 13))
					.intValue();
			endArrivalTime[MIN] = Integer.valueOf(date.substring(14, 16))
					.intValue();
			endArrivalTime[SEC] = Integer.valueOf(date.substring(17, 19))
					.intValue();
			duration = Integer.valueOf(appointmentJson.getString("duration"))
					.intValue();
			appointmentExists = true;
			tenantID = appointmentJson.getString("tenant_id");

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(arrivalTime[YEAR], arrivalTime[MONTH],
					arrivalTime[DAY], arrivalTime[HOUR], arrivalTime[MIN],
					arrivalTime[SEC]);
			arrivalMS = calendar.getTimeInMillis();
			calendar.clear();
			calendar.set(endArrivalTime[YEAR], endArrivalTime[MONTH],
					endArrivalTime[DAY], endArrivalTime[HOUR],
					endArrivalTime[MIN], endArrivalTime[SEC]);
			endArrivalMS = calendar.getTimeInMillis();
			id = appointmentJson.getString("id");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isValid() {
		if (appointmentExists && buildingID.equals(Global.BUILDING_ID)) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
			long now = calendar.getTimeInMillis();
			System.out.println(now);
			System.out.println(arrivalMS);
			System.out.println(endArrivalMS);
			System.out.println("--------------------------");
			if (arrivalMS < now && endArrivalMS > now) {
				if (buildingID.equals(Global.BUILDING_ID)) {
					return true;
				}
			}

		}
		return false;
	}

	public String getTenantID() {
		return tenantID;
	}

	public String getID() {
		return id;
	}
}

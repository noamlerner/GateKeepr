import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.*;

public class DBHandler {

	public static String getAddress() {
		String address = "";
		try {
			URL url = new URL(
					"http://7369bd19.ngrok.com/doorkeepr/rest/getAddress.php?building_id="
							+ Global.BUILDING_ID);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
			address = br.readLine();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}

	public static String[][] getTenants() {
		String[][] tenants = new String[0][0];
		try {
			String jsonString = "";
			URL url = new URL(
					"http://7369bd19.ngrok.com/doorkeepr/rest/getNames.php?building_id="
							+ Global.BUILDING_ID);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String temp;
			while (null != (temp = br.readLine())) {
				jsonString += temp;
			}

			JSONArray jsonArray = new JSONArray(jsonString);
			tenants = new String[jsonArray.length()][2];
			for (int i = 0; i < tenants.length; i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				tenants[i][0] = jsonObject.getString("full_name");
				tenants[i][1] = jsonObject.getString("name");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tenants;
	}

	public static Appointment[] checkAppointment(String firstName,
			String lastName) {
		ArrayList<Appointment> appointments = new ArrayList<Appointment>();
		try {
			String jsonString = "";
			URL url = new URL(
					"http://7369bd19.ngrok.com/doorkeepr/rest/checkAppointment.php?firstName="
							+ firstName + "&lastName=" + lastName);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
			String temp;
			while (null != (temp = br.readLine())) {
				jsonString += temp;
			}
			JSONArray jsonArray = new JSONArray(jsonString);
			Appointment app;
			for (int i = 0; i < jsonArray.length(); i++) {
				app = new Appointment((JSONObject) jsonArray.get(i));
				if (app.isValid()) {
					appointments.add(app);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		Appointment[] appts = new Appointment[appointments.size()];
		for (int i = 0; i < appts.length; i++) {
			appts[i] = appointments.get(i);
		}
		return appts;
	}

	public static void deleteAppointment(String appointmentID) {
		try {
			URL url = new URL(
					"http://7369bd19.ngrok.com/doorkeepr/rest/cancelAppointment.php?id="
							+ appointmentID);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void changeStatus(String appointmentID) {
		try {
			URL url = new URL(
					"http://7369bd19.ngrok.com/doorkeepr/rest/changeApptStatus.php?id="
							+ appointmentID);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));

		} catch (Exception e) {
		}

	}

	public static void requestAppointment(String tenantID, String phoneNum,
			String visitorFirstName, String visitorLastName) {
		try {
			URL url = new URL(
					"http://7369bd19.ngrok.com/doorkeepr/rest/createRequest.php?phone="
							+ phoneNum + "&firstName=" + visitorFirstName
							+ "&lastName=" + visitorLastName + "&tenant_id="
							+ tenantID);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));

		} catch (Exception e) {
		}
	}
}

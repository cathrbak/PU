package tdt4140.gr1835.app.ui;

import  javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tdt4140.gr1835.app.core.Nurse;


public class RestApiClient {

	public static void main(String[] args) {
		
		
		Client client = ClientBuilder.newClient();
		
		WebTarget baseTarget =client.target("Her skal base URLen inn, feks. localhost +++");
		WebTarget nurseTarget = baseTarget.path("nurses");
		WebTarget singleNurseTarget = nurseTarget.path("nurseID");
		Nurse nurse =client
				.target("http:localhost:8080/nurses/{username/id}") // usikker på dette....
				.request(MediaType.APPLICATION_JSON)
				.get(Nurse.class);
		//Nurse nurse = response.readEntity(Nurse.class); // her pakker vi ut nurse
		System.out.println(nurse);
		
		

	}

}

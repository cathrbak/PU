package tdt4140.gr1835.app.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.ws.rs.core.MediaType;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

public class RestApiClient {
	
	private HttpClient client;
	
	private static final String baseURL="http://localhost:8080/webapi/";
	
	public RestApiClient() {
		this.client= HttpClients.createDefault();
	}
	
	public String doGet(String URL) throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet(baseURL+URL);
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		StringBuilder line = new StringBuilder();
		while (rd.ready()) {
			line.append(rd.readLine());
		}
		return line.toString();
	}
	
	//Returnerer statuskode
	public int doPost(String URL, String inputString) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(baseURL+URL);
		StringEntity input = new StringEntity(inputString);
		input.setContentType(MediaType.APPLICATION_JSON);
		post.setEntity(input);
		HttpResponse response = client.execute(post);
		return response.getStatusLine().getStatusCode();
	}
	
	//Returnerer statuskode
	public int doPut(String URL,String inputString) throws ClientProtocolException, IOException {
		HttpPut put = new HttpPut(baseURL+URL);
		StringEntity input = new StringEntity(inputString);
		input.setContentType(MediaType.APPLICATION_JSON);
		put.setEntity(input);
		System.out.println(put);
		HttpResponse response = client.execute(put);
		System.out.println(response.getStatusLine());
		return response.getStatusLine().getStatusCode();
	}
	
	public int doDelete(String URL) throws ClientProtocolException, IOException{
		HttpDelete delete= new HttpDelete(baseURL+URL);
		HttpResponse response = client.execute(delete);
		return response.getStatusLine().getStatusCode();
	}
	
	
	public static void main(String[] args) {
		RestApiClient client= new RestApiClient();
		try {
			System.out.println(client.doDelete("nurses/123"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

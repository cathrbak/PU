package tdt4140.gr1835.app.webclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

public class HTTPSimpleClient {
	
	private HttpClient client;
	
	private static final String baseURL="http://localhost:8080/webapi/";
	
	public HTTPSimpleClient() {
		this.client= HttpClients.createDefault();
	}
	
	public String doGet(String URL) throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet(baseURL+URL);
		//request.addHeader("Content-Type", "charset=UTF-8");
		HttpResponse response = client.execute(request);
		//hvis ikke responsen går gjennom (200 OK) returner vi null
		if(response.getStatusLine().getStatusCode()!=200) {
			return null;
		}
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
		StringBuilder line = new StringBuilder();
		while (rd.ready()) {
			line.append(rd.readLine());
		}
		return line.toString();
	}
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HTTPSimpleClient client=new HTTPSimpleClient();
		client.doGet("nurses/testsoster");
	}
	
	//Returnerer statuskode
	public int doPost(String URL, String inputString) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(baseURL+URL);
		//post.addHeader("Content-Type", "charset=UTF-8");
		StringEntity input = new StringEntity(inputString);
		input.setContentType("application/json");
		post.setEntity(input);
		HttpResponse response = client.execute(post);
		return response.getStatusLine().getStatusCode();
	}
	
	//Returnerer statuskode
	public int doPut(String URL,String inputString) throws ClientProtocolException, IOException {
		HttpPut put = new HttpPut(baseURL+URL);
		//put.addHeader("Content-Type", "charset=UTF-8");
		StringEntity input = new StringEntity(inputString);
		input.setContentType("application/json");
		put.setEntity(input);
		System.out.println(put);
		HttpResponse response = client.execute(put);
		System.out.println(response.getStatusLine());
		return response.getStatusLine().getStatusCode();
	}
	
	public int doDelete(String URL) throws ClientProtocolException, IOException{
		HttpDelete delete= new HttpDelete(baseURL+URL);
		delete.addHeader("Content-Type", "charset=UTF-8");
		HttpResponse response = client.execute(delete);
		return response.getStatusLine().getStatusCode();
	}
}

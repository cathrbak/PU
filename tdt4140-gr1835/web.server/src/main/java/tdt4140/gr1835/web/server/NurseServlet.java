package tdt4140.gr1835.web.server;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import io.jenetics.jpx.Person;
import tdt4140.gr1835.app.core.User;
//import tdt4140.gr1835.app.core.GeoLocationsPersistence;
//import tdt4140.gr1835.app.json.GeoLocationsJsonPersistence;

public class NurseServlet extends HttpServlet {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		super.init();
		
	}
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.getWriter().println("Hello friend!");
    		}
    
    
    }


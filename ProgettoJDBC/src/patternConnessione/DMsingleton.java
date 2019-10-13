package patternConnessione;


import java.sql.*;

public final class DMsingleton {
	
	  
	    private static Connection con=null;
	    
	    public static  Connection getConnection()
	    {
	        
	        String dbName = "ProgettoBasi17/18";
	        String userName = "postgres"; 
	        String password = "DanieleG1994";
	        // get db, user, pass from settings file
	        
	        return getConnection(dbName, userName, password);
	    }

	    private static Connection getConnection(String db_name,String user_name,String password)
	    {
	        try
	        {
	            Class.forName("org.postgresql.Driver");
	            con=DriverManager.getConnection("jdbc:postgresql:"+db_name+"?user="+user_name+"&password="+password);
	            
		        
	        }
	        catch(Exception e)
	        {
	            e.printStackTrace();
	        }
	       
	        return con;
	    }
}



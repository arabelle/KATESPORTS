// We need to import the java.sql package to use JDBC
import java.sql.*;
// for reading from the command line
import java.io.*;

// for the login window
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;


public class Queries {
	
	// command line reader 
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private Connection con;

	 /*CREATE GLOBAL TEMPORARY TABLE AvgTeamScores
	    ON COMMIT PRESERVE ROWS 
	    AS Select team, avg(score) as avg_score from ((Select home_team as team, home_score as score from MatchSummary) UNION ALL (select away_team as team, away_score as score from MatchSummary)) GROUP BY team
	 //get average score per team
	 Select team from AvgTeamScores ats where ats.avg_score >= ALL (select avg_score from AvgTeamScores)
	 //get team with biggest average */
    
    public ResultSet nestedQueryBiggest() {
    	ResultSet rs = null;
    	String nestedQueryString = "WITH AvgTeamScores AS (Select team, avg(score) as avg_score from ((Select home_team as team, " +
    							   "home_score as score from MatchSummary) UNION ALL (select away_team as team, " +
    							   "away_score as score from MatchSummary)) GROUP BY team) " + "Select team from " +
    							   "AvgTeamScores ats where ats.avg_score >= ALL (select avg_score from " + 
    							   "AvgTeamScores)";
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(nestedQueryString);
            ResultSetMetaData rsmd = rs.getMetaData();
            printResults(rsmd, rs);
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
        }
    	return rs;
    }

    
    public ResultSet nestedQuerySmallest() {
    	ResultSet rs = null;
    	String nestedQueryString = "WITH AvgTeamScores AS (Select team, avg(score) as avg_score from ((Select home_team as team, " +
    							   "home_score as score from MatchSummary) UNION ALL (select away_team as team, " +
    							   "away_score as score from MatchSummary)) GROUP BY team) ";
    	
    	String query = "Select team from AvgTeamScores ats where ats.avg_score <= ALL (select avg_score from AvgTeamScores)";
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(nestedQueryString + query);
            ResultSetMetaData rsmd = rs.getMetaData();
            printResults(rsmd, rs);
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
        }
    	return rs;
    }
    
    /*
     * display information about branches
     */ 
    public ResultSet selectPlayer(String[] params, String name ) {
		String     iid;
		String     itype;
		String     iname;
		Statement  stmt;
		ResultSet  rs = null;
		String queryString;
		   
		try {
		  queryString = "SELECT ";
		  if(params.length >= 1) {
			  queryString += params[0];
			  for(int i = 1; i < params.length; i++) {
				  queryString = queryString + ", " + params[i];
			  }
		  }
		  else {
			  return null;
		  }
		  queryString = queryString + " FROM Player WHERE name = '" + name + "';";
		  stmt = con.createStatement();
	
		  rs = stmt.executeQuery(queryString);
	
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();
		  
		  printResults(rsmd, rs);
	 
		  // close the statement; 
		  // the ResultSet will also be closed
		  //stmt.close();
		}
		catch (SQLException ex)	{
		    System.out.println("Message: " + ex.getMessage());
		}
		return rs;
    }
    
    
    /*Select * from Team cwt, Player p where cwt.team_name = p.team_name AND cwt.team_name = ‘’;
    (user will specify team name and which attributes to select)*/
    //this gets the given team’s roster
    public ResultSet joinQuery(String[] params, String teamname) {
    	String     iid;
		String     itype;
		String     iname;
		Statement  stmt;
		ResultSet  rs = null;
		String queryString;
		   
		try {
		  queryString = "SELECT ";
		  if(params.length >= 1) {
			  queryString += params[0];
			  for(int i = 1; i < params.length; i++) {
				  queryString = queryString + ", " + params[i];
			  }
		  }
		  else {
			  return null;
		  }
		  queryString = queryString + " FROM Team cwt, Player p WHERE cwt.team_name = p.team_name AND cwt.team_name = '" + teamname + "';";
		  stmt = con.createStatement();
	
		  rs = stmt.executeQuery(queryString);
	
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();
		  
		  printResults(rsmd, rs);
	 
		  // close the statement; 
		  // the ResultSet will also be closed
		  //stmt.close();
		}
		catch (SQLException ex)	{
		    System.out.println("Message: " + ex.getMessage());
		}
		return rs;
    }
    
    public ResultSet aggregationQuery(String aggType, String aggFeature, String[] params, String team) {
    	//Select count(*) from MatchSummary m WHERE m.winner = ‘’;
    	//this gets the number of wins for given team
    	String             tname;
		PreparedStatement  ps;
		String queryString;
		ResultSet rs = null;
		
		queryString = "SELECT " + aggType + "(" + aggFeature + ") ";
		
		if(params.length >= 1) {
		  for(int i = 0; i < params.length; i++) {
			  queryString = queryString + ", " + params[i];
		  }
		}
		
		queryString = queryString + " FROM MatchSummary m WHERE m.winner = '" + team + "';";
		
		try {
		  Statement stmt = con.createStatement();
		  rs = stmt.executeQuery(queryString);
			
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();
		  
		  printResults(rsmd, rs);
	
		}
		catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
		}	
		return rs;
    }
    
    private void printResults(ResultSetMetaData rsmd, ResultSet result) throws SQLException{
    	while(result.next()) {
  	      // for display purposes get everything from Oracle 
  	      // as a string
    	int numCols = rsmd.getColumnCount();
  	      // simplified output formatting; truncation may occur
  		  for (int i = 0; i < numCols; i++) {
  			  if (rsmd.getColumnType(i+1) == Types.INTEGER) {
  				  int col = result.getInt(rsmd.getColumnName(i+1));
  			      System.out.printf("%-10.10d", col);
  			  }
  			  else if (rsmd.getColumnType(i+1) == Types.VARCHAR) {
  				  String col = result.getString(rsmd.getColumnName(i+1));
  			      System.out.printf("%-15.15s", col);
  			  }
  			  else if (rsmd.getColumnType(i+1) == Types.FLOAT) {
  				  float col = result.getFloat(rsmd.getColumnName(i+1));
  			      System.out.printf("%-15.15f", col);
  			  }
  			  else if (rsmd.getColumnType(i+1) == Types.CHAR) {
  				  String col = result.getString(rsmd.getColumnName(i+1));
  			      System.out.printf("%-15.15s", col);
  			  }
  			  else if (rsmd.getColumnType(i+1) == Types.NUMERIC) {
				  int col = result.getInt(rsmd.getColumnName(i+1));
			      System.out.printf("%-15.15s", col);
			  }
  			  else {
  				  System.out.println("WHAT");
  			  }
  		  }
  	      
  	      System.out.print("\n");

  	  }
    }
    
    
    // Select name from Referee r WHERE NOT EXISTS (Select * from Team t WHERE NOT EXISTS (Select * from MatchInfo m WHERE m.ref_id = r.ref_id AND (t.team_name = m.home_team or t.team_name = m.away_team))
    //this returns the names of referees who have ref’d every team
    //should be Harry Jordan
    public ResultSet divisionQuery() {
    	ResultSet rs = null;
        String divisionQueryString = "SELECT name FROM Referee r WHERE NOT EXISTS ( SELECT * from Team t WHERE NOT EXISTS (Select * from MatchInfo m WHERE m.ref_id = r.ref_id AND (t.team_name = m.home_team OR t.team_name = m.away_team)))";
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(divisionQueryString);
            ResultSetMetaData rsmd = rs.getMetaData();
            printResults(rsmd, rs);

        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
        }
        return rs;
    }
    
    
    //Delete from Players where name like ‘Emilio%’; //no rentals to delete
    //Delete from Players where name like ‘Bob%’; //many rentals get deleted
    // user specifies the name
    public void deleteQuery(String nameregex) {
    	PreparedStatement  ps;
    	try
    	{
    	  ps = con.prepareStatement("DELETE FROM Players WHERE name LIKE ?");
    	
    	  ps.setString(1, nameregex);

    	  int rowCount = ps.executeUpdate();

    	  if (rowCount == 0)
    	  {
    	      System.out.println("\nDeletion failed!");
    	  }

    	  con.commit();

    	  ps.close();
    	}
    	catch (SQLException ex)
    	{
    	    System.out.println("Message: " + ex.getMessage());

                try 
    	    {
    		con.rollback();	
    	    }
    	    catch (SQLException ex2)
    	    {
    		System.out.println("Message: " + ex2.getMessage());
    		System.exit(-1);
    	    }
    	}
    }
    
    /*UPDATE MatchInfo SET end_time = ‘01-12-2017 16:30’ WHERE match_id = 2 //this is fine
      UPDATE MatchInfo SET end_time = ‘01-12-2017 14:30’ WHERE match_id = 2 //this fails the check
      UPDATE MatchInfo SET end_time = ‘01-11-2017 16:30’ WHERE match_id = 2 //this fails the check
      //user specifies the end time and match_id
      Record for reference:
      (2, ‘Hot Cheetos’, ‘Non-Losers’, NULL, NULL, to_timestamp(‘01-12-2017 15:30’,’MM-DD-YYYY HH24:MI’), NULL, 5) */
    public void updateQuery(String endtime, int matchid) {
		PreparedStatement  ps;
		  
		try {
		  ps = con.prepareStatement("UPDATE MatchInfo SET end_time = ? WHERE match_id = ?");
		
		  ps.setString(1, endtime);
		  ps.setInt(2, matchid);
	
		  int rowCount = ps.executeUpdate();
		  if (rowCount == 0) {
		      System.out.println("\nMatchID" + matchid + " does not exist!");
		  }
	
		  con.commit();
	
		  ps.close();
		}
		catch (SQLException ex) {
		    System.out.println("Message: " + ex.getMessage());
		    
		    try {
		    	con.rollback();	
		    }
		    catch (SQLException ex2) {
				System.out.println("Message: " + ex2.getMessage());
				System.exit(-1);
		    }
		}	
    }

	
}
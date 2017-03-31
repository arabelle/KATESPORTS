// We need to import the java.sql package to use JDBC
import java.sql.*;
// for reading from the command line
import java.io.*;

// for the login window

import java.util.*;


public class Queries {

	// command line reader
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

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
    							   "away_score as score from MatchSummary)) GROUP BY team) ";
    	String query = "Select team from AvgTeamScores ats where ats.avg_score >= ALL (select avg_score from AvgTeamScores)";
        try {
        	Connection con = UI.getCon();
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(nestedQueryString + query);
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
        	Connection con = UI.getCon();
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
		  queryString = queryString + " FROM Player WHERE name like '" + name + "'";

		  Connection con = UI.getCon();
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
		  queryString = queryString + " FROM Team cwt, Player p WHERE cwt.team_name = p.team_name AND cwt.team_name = '" + teamname + "'";

		  Connection con = UI.getCon();

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

    public ResultSet aggregationQueryAvgMaxMin(String aggtype, String team) {
    	//this gets avg/max/min score of given team over all games
		String queryString;
		ResultSet rs = null;

		queryString = "SELECT "+aggtype+"(score) FROM (select m.home_score as score " +
				"from MatchSummary m where m.home_team ='" + team + "' UNION ALL select " +
				"m2.away_score as score from MatchSummary m2 where m2.away_team = '" + team + "') score";

		try {

		  Connection con = UI.getCon();

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
    	System.out.println("Referee who refd all teams: ");
        String divisionQueryString = "SELECT name FROM Referee r WHERE NOT EXISTS ( SELECT * from Team t WHERE NOT EXISTS (Select * from MatchInfo m WHERE m.ref_id = r.ref_id AND (t.team_name = m.home_team OR t.team_name = m.away_team)))";
        //String divisionQueryString = "Select * from mtea";
    	try {
        	Connection con = UI.getCon();
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
    	Connection con = UI.getCon();
    	try
    	{
    	  ps = con.prepareStatement("DELETE FROM Player WHERE name LIKE ?");

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
		Connection con = UI.getCon();
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

	public ResultSet bonusQuery(String team, String year) {
		//create table to store results (month & that month's win percentage)
		String createResultTable = "CREATE GLOBAL TEMPORARY TABLE WinsPerMonth (month char(10), win_percentage NUMBER(5, 2)) ON COMMIT PRESERVE ROWS";

		try {
			Connection con = UI.getCon();
			Statement stmt = con.createStatement();
			stmt.executeQuery(createResultTable);
		} catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
		}

		//get win percentage per month for given team and year
		for (int i = 1; i <= 12; i++) {
			String month = null;

			switch (i) {
				case 1:
					month = "January";
					break;
				case 2:
					month = "February";
					break;
				case 3:
					month = "March";
					break;
				case 4:
					month = "April";
					break;
				case 5:
					month = "May";
					break;
				case 6:
					month = "June";
					break;
				case 7:
					month = "July";
					break;
				case 8:
					month = "August";
					break;
				case 9:
					month = "September";
					break;
				case 10:
					month = "October";
					break;
				case 11:
					month = "November";
					break;
				case 12:
					month = "December";
					break;
				default:
					month = "Error";
					break;
			}

			String month_as_num = (i < 10 ? "0" : "") + i;
			String next_month_as_num = (i==12 ? "01" : (((i+1) < 10 ? "0" : "") + (i+1)));

			String queryString = "INSERT INTO WinsPerMonth(month, win_percentage) Select '" + month + "', " +
					"((select count(*) from MatchInfo mi, MatchSummary ms where mi.home_team = ms.home_team and " +
					"mi.away_team = ms.away_team and mi.home_score = ms.home_score and " +
					"mi.away_score = ms.away_score and (ms.home_team = '" + team + "' or ms.away_team = '" + team + "') and " +
					"winner = '" + team + "' and end_time BETWEEN DATE '" + year + "-"+ month_as_num + "-01' AND " +
					"DATE '" + year + "-"+ next_month_as_num + "-01')*100 " +
					"/ " +
					"(select count(*) from MatchInfo mi, MatchSummary ms where mi.home_team = ms.home_team and " +
					"mi.away_team = ms.away_team and mi.home_score = ms.home_score and mi.away_score = ms.away_score and " +
					"(ms.home_team = '" + team + "' or ms.away_team = '" + team + "') and end_time BETWEEN " +
					"DATE '" + year + "-"+ month_as_num + "-01' AND DATE '" + year + "-"+ next_month_as_num + "-01')) " +
					"as win_percentage FROM DUAL";
			/*
			INSERT INTO WinsPerMonth(month, win_percentage) Select 'February', ((select count(*) from MatchInfo mi,
			MatchSummary ms where mi.home_team = ms.home_team and mi.away_team = ms.away_team and
			mi.home_score = ms.home_score and mi.away_score = ms.away_score and (ms.home_team = 'AndroidT' or
			ms.away_team = 'AndroidT') and winner = 'AndroidT' and end_time BETWEEN DATE '2017-02-01' AND
			DATE '2017-03-01')*100 / (select count(*) from MatchInfo mi, MatchSummary ms where
			mi.home_team = ms.home_team and mi.away_team = ms.away_team and mi.home_score = ms.home_score and
			mi.away_score = ms.away_score and (ms.home_team = 'AndroidT' or ms.away_team = 'AndroidT') and
			end_time BETWEEN DATE '2017-02-01' AND DATE '2017-03-01')) as win_percentage FROM DUAL;
			 */

			try {
				Connection con = UI.getCon();
				Statement stmt = con.createStatement();

				stmt.executeUpdate(queryString);

			} catch (SQLException ex) {
				System.out.println("Message: " + ex.getMessage());
			}
		}

		ResultSet rs = null;
		String getResultTable = "Select * from WinsPerMonth";

		try {
			Connection con = UI.getCon();
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(getResultTable);
			ResultSetMetaData rsmd = rs.getMetaData();
			printResults(rsmd, rs);
		} catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
		}

		return rs;
	}


	public void bonusQuery2(String team) {
		ResultSet rs = null;
		String queryString = "\n" +
				"Select ms.winner, to_char(mi.end_time, 'MM-DD-YYYY') \"DATE\" from MatchInfo mi, " +
				"MatchSummary ms where mi.home_team = ms.home_team and mi.away_team = ms.away_team and " +
				"mi.home_score = ms.home_score and mi.away_score = ms.away_score and (ms.home_team = '" + team + "' or " +
				"ms.away_team = '" + team + "') ORDER BY to_char(mi.end_time, 'MM-DD-YYYY HH24:MM')";

		/*
		Select ms.winner, to_char(mi.end_time, 'MM-DD-YYYY') "DATE" from MatchInfo mi, MatchSummary ms where
		mi.home_team = ms.home_team and mi.away_team = ms.away_team and mi.home_score = ms.home_score and
		mi.away_score = ms.away_score and (ms.home_team = 'Grannies' or ms.away_team = 'Grannies')
		ORDER BY to_char(mi.end_time, 'MM-DD-YYYY HH24:MM')
		 */
		try {
			Connection con = UI.getCon();
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(queryString);
			ResultSetMetaData rsmd = rs.getMetaData();

			Tuple tuple = new Tuple();
			List<Tuple> tuples = new ArrayList<>();

			while (rs.next()) {
				if (rs.getString(1).equals(team)) {
					if (tuple.startDate == null) {
						tuple.startDate = rs.getString(2);
					}
					tuple.winCount++;
				} else {
					if (tuple.startDate != null && tuple.endDate == null) {
						tuple.endDate = rs.getString(2);
						tuples.add(tuple);
						tuple = new Tuple();
					}
				}
			}

			int maxWins = 0;
			Tuple streak = null;
			for (Tuple t: tuples) {
				if (t.winCount > maxWins) {
					maxWins = t.winCount;
					streak = t;
				}
			}

			//TODO: display this prettier to user
			System.out.println("Longest win streak: " + streak.winCount);
			System.out.println("Streak start date: " + streak.startDate);
			System.out.println("Streak end date: " + streak.endDate);


		} catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
		}
	}



}

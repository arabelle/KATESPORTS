
// We need to import the java.sql package to use JDBC
import java.sql.*;

// for reading from the command line
import java.io.*;

// for the login window
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/*
 * This class implements a graphical login window and a simple text
 * interface for interacting with the branch table 
 */ 
public class ConnectDB implements ActionListener
{
    // command line reader 
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private static Connection con;

    // user is allowed 3 login attempts
    private int loginAttempts = 0;

    // components of the login window
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JFrame mainFrame;
    
    // usertypes:
    // referee = 0
    // coach = 1
    // player = 2
    private int USERTYPE = 2;
    
    private int REFEREE = 0;
    private int COACH = 1;
    private int PLAYER = 2;


    /*
     * constructs login window and loads JDBC driver
     */ 
    public ConnectDB() {
      mainFrame = new JFrame("User Login");

      JLabel usernameLabel = new JLabel("Enter username: ");
      JLabel passwordLabel = new JLabel("Enter password: ");

      usernameField = new JTextField(10);
      passwordField = new JPasswordField(10);
      passwordField.setEchoChar('*');

      JButton loginButton = new JButton("Log In");

      JPanel contentPane = new JPanel();
      mainFrame.setContentPane(contentPane);


      // layout components using the GridBag layout manager

      GridBagLayout gb = new GridBagLayout();
      GridBagConstraints c = new GridBagConstraints();

      contentPane.setLayout(gb);
      contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

      // place the username label 
      c.gridwidth = GridBagConstraints.RELATIVE;
      c.insets = new Insets(10, 10, 5, 0);
      gb.setConstraints(usernameLabel, c);
      contentPane.add(usernameLabel);

      // place the text field for the username 
      c.gridwidth = GridBagConstraints.REMAINDER;
      c.insets = new Insets(10, 0, 5, 10);
      gb.setConstraints(usernameField, c);
      contentPane.add(usernameField);

      // place password label
      c.gridwidth = GridBagConstraints.RELATIVE;
      c.insets = new Insets(0, 10, 10, 0);
      gb.setConstraints(passwordLabel, c);
      contentPane.add(passwordLabel);

      // place the password field 
      c.gridwidth = GridBagConstraints.REMAINDER;
      c.insets = new Insets(0, 0, 10, 10);
      gb.setConstraints(passwordField, c);
      contentPane.add(passwordField);

      // place the login button
      c.gridwidth = GridBagConstraints.REMAINDER;
      c.insets = new Insets(5, 10, 10, 10);
      c.anchor = GridBagConstraints.CENTER;
      gb.setConstraints(loginButton, c);
      contentPane.add(loginButton);

      // register password field and OK button with action event handler
      passwordField.addActionListener(this);
      loginButton.addActionListener(this);

      // anonymous inner class for closing the window
      mainFrame.addWindowListener(new WindowAdapter() {
    	  public void windowClosing(WindowEvent e) { 
    		  System.exit(0); 
    	  }
      });

      // size the window to obtain a best fit for the components
      mainFrame.pack();

      // center the frame
      Dimension d = mainFrame.getToolkit().getScreenSize();
      Rectangle r = mainFrame.getBounds();
      mainFrame.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

      // make the window visible
      mainFrame.setVisible(true);

      // place the cursor in the text field for the username
      usernameField.requestFocus();

      try {
		// Load the Oracle JDBC driver
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
      }
      catch (SQLException ex) {
		System.out.println("Message: " + ex.getMessage());
		System.exit(-1);
      }
    }

    public static Connection getCon(){
    	return con;
    }
    
    
    /*
     * connects to Oracle database named ug using user supplied username and password
     */ 
    private boolean connect(String username, String password) {
      String connectURL = "jdbc:oracle:thin:@localhost.ugrad.cs.ubc.ca:1522:ug"; 

      try {
		con = DriverManager.getConnection(connectURL,username,password);
	
		System.out.println("\nConnected to Oracle!");
		return true;
      }
      catch (SQLException ex) {
		System.out.println("Message: " + ex.getMessage());
		return false;
	  }
    }


    /*
     * event handler for login window
     */ 
    public void actionPerformed(ActionEvent e) {
		if ( connect(usernameField.getText(), String.valueOf(passwordField.getPassword()))) {
		  // if the username and password are valid, 
		  // remove the login window and display a text menu 
		  mainFrame.dispose();
		  showMenu();     
		}
		else {
		  loginAttempts++;
		  
		  if (loginAttempts >= 3) {
		      mainFrame.dispose();
		      System.exit(-1);
		  }
		  else {
		      // clear the password
			  passwordField.setText("");
		  }
		}                    
    }


    /*
     * displays simple text interface
     */ 
    private void showMenu() {
		int choice;
		boolean quit;
	
		quit = false;
		
		Queries q = new Queries();
		
		try {
		    // disable auto commit mode
		    con.setAutoCommit(false);
		    
		    /*System.out.println("\n\nPlease select the user type: ");
		    System.out.println("1. REFEREE");
		    System.out.println("2. COACH");
		    System.out.print("3. PLAYAA\n>>  ");
		    
		    choice = Integer.parseInt(in.readLine());
		    
		    System.out.println(" ");
		    
		    switch(choice) {
		    	case 1:  USERTYPE = 0; break;
		    	case 2:  USERTYPE = 1; break;
		    	case 3:  USERTYPE = 2; break;
		    }
		    
		    System.out.println("Changed user type to " + USERTYPE);*/
		    
		    if (USERTYPE == REFEREE) {
		    	while (!quit) {
					System.out.print("\n\nPlease choose one of the following: \n");
					System.out.print("1.  Insert Equipment\n");
					System.out.print("2.  Delete Equipment\n");
					System.out.print("3.  Update Equipment\n");
					System.out.print("4.  Show Equipment\n");
					System.out.print("5.  Selection Query\n");
					System.out.print("6.  Quit\n>> ");
			
					choice = Integer.parseInt(in.readLine());
					
					System.out.println(" ");
			
					switch(choice) {
					   case 1:  insertEquipment(); break;
					   case 2:  deleteEquipment(); break;
					   case 3:  updateNameEquipment(); break;
					   case 4:  showEquipment(); break;
					   case 5:  selectionQuery(); break;
					   case 6:  quit = true;
					}
			    }
		    }
		    else if (USERTYPE == COACH) {
		    	while (!quit) {
					System.out.print("\n\nPlease choose one of the following: \n");
					System.out.print("1.  Insert Equipment\n");
					System.out.print("2.  Delete Equipment\n");
					System.out.print("3.  Update Equipment\n");
					System.out.print("4.  Show Equipment\n");
					System.out.print("5.  Selection Query\n");
					System.out.print("6.  Quit\n>> ");
			
					choice = Integer.parseInt(in.readLine());
					
					System.out.println(" ");
			
					switch(choice) {
					   case 1:  insertEquipment(); break;
					   case 2:  deleteEquipment(); break;
					   case 3:  updateNameEquipment(); break;
					   case 4:  showEquipment(); break;
					   case 5:  selectionQuery(); break;
					   case 6:  quit = true;
					}
			    }
		    }
		    else if (USERTYPE == PLAYER) {
		    	while (!quit) {
					System.out.print("\n\nPlease choose one of the following: \n");
					System.out.print("1.  Nested Query Biggest\n");
					System.out.print("2.  Nested Query Smallest\n");
					System.out.print("3.  Bonus Query\n");
					System.out.print("4.  Update Query\n");
					System.out.print("5.  Agg. Query Max Min\n");
					System.out.print("6.  Division Query\n");
					System.out.print("7.  Bonus Query 2 Grannies\n");
					System.out.print("8.  Bonus Query 2 AndroidT\n");
					System.out.print("9.  Bonus Query 2 Hot Cheetos\n");
					System.out.print("10. Bonus Query 2 Ball Is Life\n");
					System.out.print("12. Bonus Query 2 Non-Losers\n");
					System.out.print("14.  Quit\n>> ");
			
					choice = Integer.parseInt(in.readLine());
					
					System.out.println(" ");
			
					switch(choice) {
					   case 1:  q.nestedQueryBiggest(); break;
					   case 2:  q.nestedQuerySmallest(); break;
					   case 3:  q.bonusQuery("AndroidT", "2017"); break;
					   case 4:  q.updateQuery("01-12-2017 16:31", 2); break;
					   case 5:  q.aggregationQueryAvgMaxMin("count", "Grannies"); break;
					   case 6:  q.divisionQuery(); break;
					   case 7:  q.bonusQuery2("Grannies"); break;
					   case 8:  q.bonusQuery2("AndroidT"); break;
					   case 9:  q.bonusQuery2("Hot Cheetos"); break;
					   case 10:  q.bonusQuery2("Ball Is Life"); break;
					   case 12:  q.bonusQuery2("Non-Losers"); break;
					   case 14:  quit = true;
					}
			    }
		    }
		    else {
		    	System.out.println("Usertype does not exist.\n");
		    }
	
		    con.close();
	            in.close();
		    System.out.println("\nGood Bye!\n\n");
		    System.exit(0);
		}
		catch (IOException e) {
		    System.out.println("IOException!");
	
		    try {
				con.close();
				System.exit(-1);
		    }
		    catch (SQLException ex) {
		    	System.out.println("Message: " + ex.getMessage());
		    }
		}
		catch (SQLException ex) {
		    System.out.println("Message: " + ex.getMessage());
		}
    }


    /*CREATE GLOBAL TEMPORARY TABLE AvgTeamScores
    ON COMMIT PRESERVE ROWS 
    AS Select team, avg(score) as avg_score from ((Select home_team as team, home_score as score from MatchSummary) UNION ALL (select away_team as team, away_score as score from MatchSummary)) GROUP BY team
 //get average score per team
 Select team from AvgTeamScores ats where ats.avg_score >= ALL (select avg_score from AvgTeamScores)
 //get team with biggest average
 Select team from AvgTeamScores ats where ats.avg_score <= ALL (select avg_score from AvgTeamScores)
 //get team with smallest average*/
    //private void 
    private void getAvgTeamScores() {
    	String nestedQueryString = "WITH AvgTeamScores AS (Select team, avg(score) as avg_score from ((Select home_team as team, " +
    							   "home_score as score from MatchSummary) UNION ALL (select away_team as team, " +
    							   "away_score as score from MatchSummary)) GROUP BY team) " + "Select team from " +
    							   "AvgTeamScores ats where ats.avg_score >= ALL (select avg_score from " + 
    							   "AvgTeamScores)";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(nestedQueryString);
            ResultSetMetaData rsmd = rs.getMetaData();
            printResults(rsmd, rs);
            
            
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
        }
    	return;
    }

    
    /*
     * inserts an equipment
     */ 
    private void insertEquipment()
    {
	int                iid;
	String             iname;
	String             itype;
	PreparedStatement  ps;
	  
	try
	{
	  ps = con.prepareStatement("INSERT INTO equipment VALUES (?,?,?)");
	
	  System.out.println("\nEquipment ID: ");
	  iid = Integer.parseInt(in.readLine());
	  ps.setInt(1, iid);

	  System.out.println("\nEquipment Type: ");
	  itype = in.readLine();
	  ps.setString(2, itype);
	  
	  System.out.println("\nEquipment Name: ");
	  iname = in.readLine();
	  ps.setString(3, iname);

	  ps.executeUpdate();

	  // commit work 
	  con.commit();

	  ps.close();
	}
	catch (IOException e)
	{
	    System.out.println("IOException!");
	}
	catch (SQLException ex)
	{
	    System.out.println("Message: " + ex.getMessage());
	    try 
	    {
		// undo the insert
		con.rollback();	
	    }
	    catch (SQLException ex2)
	    {
		System.out.println("Message: " + ex2.getMessage());
		System.exit(-1);
	    }
	}
    }


    /*
     * deletes an equipment
     */ 
    private void deleteEquipment()
    {
	int                iid;
	PreparedStatement  ps;
	  
	try
	{
	  ps = con.prepareStatement("DELETE FROM equipment WHERE item_id = ?");
	
	  System.out.print("\nEquipment ID: ");
	  iid = Integer.parseInt(in.readLine());
	  ps.setInt(1, iid);

	  int rowCount = ps.executeUpdate();

	  if (rowCount == 0)
	  {
	      System.out.println("\nEquipment " + iid + " does not exist!");
	  }

	  con.commit();

	  ps.close();
	}
	catch (IOException e)
	{
	    System.out.println("IOException!");
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
    

    /*
     * updates the name of an equipment
     */ 
    private void updateNameEquipment()
    {
		int                iid;
		String             iname;
		PreparedStatement  ps;
		  
		try {
		  ps = con.prepareStatement("UPDATE equipment SET item_name = ? WHERE item_id = ?");
		
		  System.out.print("\nEquipment ID: ");
		  iid = Integer.parseInt(in.readLine());
		  ps.setInt(2, iid);
	
		  System.out.print("\nEquipment Name: ");
		  iname = in.readLine();
		  ps.setString(1, iname);
	
		  int rowCount = ps.executeUpdate();
		  if (rowCount == 0) {
		      System.out.println("\nEquipment" + iid + " does not exist!");
		  }
	
		  con.commit();
	
		  ps.close();
		}
		catch (IOException e) {
		    System.out.println("IOException!");
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

    
    /*
     * display information about branches
     */ 
    private void showEquipment() {
		String     iid;
		String     itype;
		String     iname;
		Statement  stmt;
		ResultSet  rs;
		   
		try {
		  stmt = con.createStatement();
	
		  rs = stmt.executeQuery("SELECT * FROM matchinfo");
	
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();
	
		  System.out.println(" ");
	
		  printResults(rsmd, rs);
	 
		  // close the statement; 
		  // the ResultSet will also be closed
		  stmt.close();
		}
		catch (SQLException ex)	{
		    System.out.println("Message: " + ex.getMessage());
		}	
    }
    
    private void showWinners() {
    	//Select count(*) from MatchSummary m WHERE m.winner = ‘’;
    	//this gets the number of wins for given team
    	String             tname;
		PreparedStatement  ps;
		  
		try {
		  ps = con.prepareStatement("SELECT count(*) from MatchSummary m WHERE m.winner = ?");
	
		  System.out.print("\nTeam Name: ");
		  tname = in.readLine();
		  ps.setString(1, tname);
	
		  int rowCount = ps.executeUpdate();
		  if (rowCount == 0) {
		      System.out.println("\nTeam name" + tname + " does not exist!");
		  }
	
		  con.commit();
	
		  ps.close();
		}
		catch (IOException e) {
		    System.out.println("IOException!");
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
    
    private void joinQuery() {
        String joinQueryString = "SELECT p.name FROM Player p, Team t WHERE p.team_name = t.team_name AND t.team_name LIKE '%Grannies%'";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(joinQueryString);
            ResultSetMetaData rsmd = rs.getMetaData();
            printResults(rsmd, rs);
            
            
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
        }
        
    }
    
    private void divisionQuery() {
        String divisionQueryString = "SELECT name FROM Referee r WHERE NOT EXISTS ( SELECT * from Team t WHERE NOT EXISTS (Select * from MatchInfo m WHERE m.ref_id = r.ref_id AND (t.team_name = m.home_team OR t.team_name = m.away_team)))";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(divisionQueryString);
            ResultSetMetaData rsmd = rs.getMetaData();
            printResults(rsmd, rs);

        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
        }
        
    }
    
    private void aggregationQuery() {
        String aggregationQueryString = "SELECT max(matches_lost) FROM Player";
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(aggregationQueryString);
            ResultSetMetaData rsmd = rs.getMetaData();
            printResults(rsmd, rs);

        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
        }
        
    }
    
    private void selectionQuery() {
    String select_field;
    String database;
    String filter;
    String where_field;
    PreparedStatement ps;
    try{
        StringBuilder query = new StringBuilder("SELECT ");
        StringBuilder select = new StringBuilder();
        System.out.print("\nNumber of fields in SELECT: ");
        int num_select_fields = Integer.parseInt(in.readLine());
        for (int i=0; i < num_select_fields; i++) {
            System.out.print("\nSELECT: ");
            select_field = in.readLine();
            select.append(select_field);
            if (i < num_select_fields - 1) select.append(", ");
        }
        query.append(select);
        query.append(" ");
        
        System.out.print("\nFROM: ");
        database = in.readLine();
        query.append("FROM ");
        query.append(database);
        query.append(" ");
        
        // TODO: finish getting the arbitrary WHERE clause.
        StringBuilder where_clause = new StringBuilder();
        where_clause.append("WHERE ");
        System.out.print("\nNOTE: If comparing strings (i.e. using LIKE), please surround in single quotes");
        System.out.print("\nNumber of conditions in WHERE: ");
        int num_where_conditions = Integer.parseInt(in.readLine());
        for (int i=0; i < num_where_conditions; i++) {
            System.out.print("\nWHERE: ");
            where_field = in.readLine();
            where_clause.append(where_field);
            
            System.out.print("\n\nPlease choose one of the following: \n");
            System.out.print("1. EQUALS\n");
            System.out.print("2. GREATER THAN\n");
            System.out.print("3. GREATER THAN OR EQUALS\n");
            System.out.print("4. LESSER THAN\n");
            System.out.print("5. LESSER THAN OR EQUALS\n");
            System.out.print("6. NOT EQUALS\n");
            System.out.print("7. LIKE\n");

            int choice = Integer.parseInt(in.readLine());
            String comparator = "";

            switch(choice)
            {
               case 1:  
                   comparator = " = ";
                   break;
               case 2:  
                   comparator = " > ";
                   break;
               case 3:  
                   comparator = " >= ";
                   break;
               case 4:  
                   comparator = " < ";
                   break;
               case 5:  
                   comparator = " <= ";
                   break;
               case 6:  
                   comparator = " != ";
                   break;
               case 7:  
                   comparator = " LIKE ";
                   break;
            }
            if (comparator == "") {
                throw new IOException("unsupported comparator");
            }
            where_clause.append(comparator);
            
            System.out.print("\nValue: ");
            where_clause.append(in.readLine());
            
            // ask whether we want an AND or OR comparator
            if (i < num_where_conditions - 1) {
                System.out.print("\nChoose AND (1) or OR(2); ");
                int operator = Integer.parseInt(in.readLine());
                if (operator == 1) {
                    where_clause.append(" AND ");
                } else if (operator == 2) {
                    where_clause.append(" OR ");
                } else {
                    throw new IOException("unsupported operator");
                }
            }
            where_clause.append(" ");
        }

        query.append(where_clause);
        
        Statement stmt = con.createStatement();
        
        System.out.println("THIS IS THE QUERY: " + query.toString());
		ResultSet result = stmt.executeQuery(query.toString());
        
        // get info on ResultSet
		ResultSetMetaData rsmd = result.getMetaData();
	
	  // get number of columns
	  int numCols = rsmd.getColumnCount();

	  System.out.println(" ");
	  
	  // display column names;
	  for (int i = 0; i < numCols; i++) {
	      // get column name and print it

	      System.out.printf("%-15s", rsmd.getColumnName(i+1));    
	  }

	  System.out.println(" ");

	  printResults(rsmd, result);
 
	  // close the statement; 
	  // the ResultSet will also be closed
	  stmt.close();
        
        
    } catch (SQLException ex)
    {
        System.out.println("Message: " + ex.getMessage());
    } catch (IOException ex) {
        System.out.println("IO Exception!: " + ex.getMessage());
    }
    }
 
    public static void main(String args[])
    {
      ConnectDB condb = new ConnectDB();
    }
}

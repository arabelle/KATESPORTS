
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;



public class UI extends javax.swing.JFrame {

    /**
     * Creates new form UI
     */
	
	// command line reader
	private static Connection con;

	javax.swing.table.DefaultTableModel PlayerTableModel;
	Queries queries;
	
	
    public UI(String username, String password) {
    	
    	
    	queries = new Queries();
    	try {
			// Load the Oracle JDBC driver
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		} catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
			System.exit(-1);
		}
        initComponents();
        PlayerTableModel = new DefaultTableModel();
        PlayerTable.setModel(PlayerTableModel);
        
        
        String connectURL = "jdbc:oracle:thin:@localhost.ugrad.cs.ubc.ca:1522:ug";

		
		jComboBox1.removeAllItems();
		jComboBox1.addItem("Avg");
		jComboBox1.addItem("Min");
		jComboBox1.addItem("Max");
        TeamDropdown2.removeAllItems();
        TeamDropdown.removeAllItems();
		jComboBox2.removeAllItems();
		
		try {
			con = DriverManager.getConnection(connectURL, username, password);
			System.out.println("\nConnected to Oracle!");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT team_name FROM Team");
			while (rs.next()) {
			  TeamDropdown2.addItem(rs.getString(1));
			  TeamDropdown.addItem(rs.getString(1));
			  jComboBox2.addItem(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
		}
		String queryString = "Select * from MatchInfo";
		try {
			con = DriverManager.getConnection(connectURL, username, password);

			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(queryString);
			RefereeTable.setModel(buildTable(rs));
	    	RefereeTable.validate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		
    }
    
    public static Connection getCon() {
    	return con;
    }
    // Genrated by the netbeans GUI editor                      
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        MainPane = new javax.swing.JTabbedPane();
        PlayerPane = new javax.swing.JPanel();
        PlayerInfoPane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PlayerName = new javax.swing.JTextField();
        ViewPlayerInfo = new javax.swing.JButton();
        WeightBox1 = new javax.swing.JCheckBox();
        WonBox1 = new javax.swing.JCheckBox();
        LostBox1 = new javax.swing.JCheckBox();
        IDBox1 = new javax.swing.JCheckBox();
        NameBox1 = new javax.swing.JCheckBox();
        PhoneNumberBox1 = new javax.swing.JCheckBox();
        AgeBox1 = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        LostBox2 = new javax.swing.JCheckBox();
        PlayerTablePane = new javax.swing.JScrollPane();
        PlayerTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSpinner1 = new javax.swing.JSpinner();
        jButton2 = new javax.swing.JButton();
        RefereePane = new javax.swing.JPanel();
        RefSidePane = new javax.swing.JPanel();
        RefereeInfoPane = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        ViewReferee = new javax.swing.JButton();
        RefereeActionsPane = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        GameID = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        UpdateGameTime = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jSpinner4 = new javax.swing.JSpinner();
        jSpinner5 = new javax.swing.JSpinner();
        jSpinner6 = new javax.swing.JSpinner();
        jSpinner7 = new javax.swing.JSpinner();
        RefereeTablePane = new javax.swing.JScrollPane();
        RefereeTable = new javax.swing.JTable();
        CoachPane = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        CoachInfoPane = new javax.swing.JPanel();
        RosterButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        TeamDropdown = new javax.swing.JComboBox<>();
        IDBox = new javax.swing.JCheckBox();
        NameBox = new javax.swing.JCheckBox();
        PhoneNumberBox = new javax.swing.JCheckBox();
        AgeBox = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        WeightBox = new javax.swing.JCheckBox();
        WonBox = new javax.swing.JCheckBox();
        LostBox = new javax.swing.JCheckBox();
        CoachActionPane = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        RemovePlayerButton = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        PlayerToBeRemoved = new javax.swing.JTextField();
        CoachTablePane = new javax.swing.JScrollPane();
        CoachTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        TeamInfoPane = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TeamDropdown2 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        GetGoalsAverage = new javax.swing.JButton();
        ViewWinCount = new javax.swing.JButton();
        GetGoalsAverage1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PlayerInfoPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Player Information"));

        jLabel2.setText("Player Name:");

        ViewPlayerInfo.setText("View Player Info");
        ViewPlayerInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPlayerInfoActionPerformed(evt);
            }
        });

        WeightBox1.setText("Weight");

        WonBox1.setText("Matches Won");

        LostBox1.setText("Matches Lost");

        IDBox1.setText("ID");

        NameBox1.setText("Name");
        
        PhoneNumberBox1.setText("Phone Number");

        AgeBox1.setText("Age");

        jLabel11.setText("Select Attributes to display");

        LostBox2.setText("Team");

        javax.swing.GroupLayout PlayerInfoPaneLayout = new javax.swing.GroupLayout(PlayerInfoPane);
        PlayerInfoPane.setLayout(PlayerInfoPaneLayout);
        PlayerInfoPaneLayout.setHorizontalGroup(
            PlayerInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayerInfoPaneLayout.createSequentialGroup()
                .addGroup(PlayerInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PlayerInfoPaneLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(PlayerInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LostBox1)
                            .addComponent(WonBox1)
                            .addComponent(WeightBox1)
                            .addComponent(AgeBox1)
                            .addComponent(PhoneNumberBox1)
                            .addComponent(NameBox1)
                            .addComponent(IDBox1)
                            .addComponent(LostBox2)))
                    .addGroup(PlayerInfoPaneLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PlayerInfoPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PlayerInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PlayerInfoPaneLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PlayerInfoPaneLayout.createSequentialGroup()
                        .addComponent(PlayerName)
                        .addContainerGap())
                    .addComponent(ViewPlayerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        PlayerInfoPaneLayout.setVerticalGroup(
            PlayerInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PlayerInfoPaneLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(IDBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NameBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PhoneNumberBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AgeBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WeightBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WonBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LostBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LostBox2)
                .addGap(10, 10, 10)
                .addComponent(ViewPlayerInfo)
                .addContainerGap())
        );

        PlayerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        PlayerTablePane.setViewportView(PlayerTable);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Team Stats"));

        jLabel16.setText("Choose Team");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setText("Choose Year");

        jButton1.setText("View % wins/Month");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("View Largest Win Streak");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSpinner1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jButton2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(3, 3, 3)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PlayerPaneLayout = new javax.swing.GroupLayout(PlayerPane);
        PlayerPane.setLayout(PlayerPaneLayout);
        PlayerPaneLayout.setHorizontalGroup(
            PlayerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PlayerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PlayerInfoPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addComponent(PlayerTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 847, Short.MAX_VALUE)
                .addContainerGap())
        );
        PlayerPaneLayout.setVerticalGroup(
            PlayerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PlayerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PlayerPaneLayout.createSequentialGroup()
                        .addComponent(PlayerInfoPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(PlayerTablePane))
                .addContainerGap())
        );

        MainPane.addTab("Player", PlayerPane);

        RefereeInfoPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Referee Information"));

        jLabel3.setText("Referees who have overseen");

        jLabel4.setText("every team");

        ViewReferee.setText("View");
        ViewReferee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewRefereeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RefereeInfoPaneLayout = new javax.swing.GroupLayout(RefereeInfoPane);
        RefereeInfoPane.setLayout(RefereeInfoPaneLayout);
        RefereeInfoPaneLayout.setHorizontalGroup(
            RefereeInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RefereeInfoPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RefereeInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ViewReferee, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addContainerGap())
        );
        RefereeInfoPaneLayout.setVerticalGroup(
            RefereeInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RefereeInfoPaneLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addGap(1, 1, 1)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(ViewReferee)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        RefereeActionsPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Referee Actions"));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel5.setText("Change Game End Time");

        GameID.setBorder(null);

        jLabel6.setText("New ending Date and Time:");

        UpdateGameTime.setText("Update");
        UpdateGameTime.setBorder(null);
        UpdateGameTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateGameTimeActionPerformed(evt);
            }
        });

        jLabel7.setText("Game ID");

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(1, 1, 12, 1));

        jLabel18.setText("Month");

        jLabel19.setText("Day");

        jLabel20.setText("Year");

        jLabel21.setText("Hour (24)");

        jLabel22.setText("Minute");

        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));

        jSpinner5.setModel(new javax.swing.SpinnerNumberModel(2000, 2000, 2017, 1));

        jSpinner6.setModel(new javax.swing.SpinnerNumberModel(0, 0, 23, 1));

        jSpinner7.setModel(new javax.swing.SpinnerNumberModel(0, 0, 60, 1));

        javax.swing.GroupLayout RefereeActionsPaneLayout = new javax.swing.GroupLayout(RefereeActionsPane);
        RefereeActionsPane.setLayout(RefereeActionsPaneLayout);
        RefereeActionsPaneLayout.setHorizontalGroup(
            RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RefereeActionsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RefereeActionsPaneLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(UpdateGameTime, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(RefereeActionsPaneLayout.createSequentialGroup()
                        .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(RefereeActionsPaneLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(40, 40, 40)
                                .addComponent(GameID, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(RefereeActionsPaneLayout.createSequentialGroup()
                                .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addGap(33, 33, 33)
                                .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSpinner5)
                                    .addComponent(jSpinner2)
                                    .addComponent(jSpinner4)))
                            .addGroup(RefereeActionsPaneLayout.createSequentialGroup()
                                .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jSpinner6, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                    .addComponent(jSpinner7))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        RefereeActionsPaneLayout.setVerticalGroup(
            RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RefereeActionsPaneLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(GameID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(36, 36, 36)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(UpdateGameTime, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout RefSidePaneLayout = new javax.swing.GroupLayout(RefSidePane);
        RefSidePane.setLayout(RefSidePaneLayout);
        RefSidePaneLayout.setHorizontalGroup(
            RefSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RefereeActionsPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(RefereeInfoPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        RefSidePaneLayout.setVerticalGroup(
            RefSidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RefSidePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RefereeInfoPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RefereeActionsPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        RefereeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        RefereeTablePane.setViewportView(RefereeTable);

        javax.swing.GroupLayout RefereePaneLayout = new javax.swing.GroupLayout(RefereePane);
        RefereePane.setLayout(RefereePaneLayout);
        RefereePaneLayout.setHorizontalGroup(
            RefereePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RefereePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(RefSidePane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(RefereeTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                .addContainerGap())
        );
        RefereePaneLayout.setVerticalGroup(
            RefereePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RefereePaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RefereePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RefSidePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(RefereeTablePane))
                .addContainerGap())
        );

        MainPane.addTab("Referee", RefereePane);

        CoachInfoPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Coach Information"));

        RosterButton.setText("View Roster");
        RosterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RosterButtonActionPerformed(evt);
            }
        });

        jLabel15.setText("Team Name");

        TeamDropdown.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        IDBox.setText("ID");

        NameBox.setText("Name");
        
        PhoneNumberBox.setText("Phone Number");

        AgeBox.setText("Age");

        jLabel8.setText("Select Player Attributes to Display");

        WeightBox.setText("Weight");

        WonBox.setText("Matches Won");

        LostBox.setText("Matches Lost");

        javax.swing.GroupLayout CoachInfoPaneLayout = new javax.swing.GroupLayout(CoachInfoPane);
        CoachInfoPane.setLayout(CoachInfoPaneLayout);
        CoachInfoPaneLayout.setHorizontalGroup(
            CoachInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(RosterButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(CoachInfoPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CoachInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TeamDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(CoachInfoPaneLayout.createSequentialGroup()
                        .addGroup(CoachInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel8)
                            .addComponent(LostBox)
                            .addComponent(WonBox)
                            .addComponent(WeightBox)
                            .addComponent(AgeBox)
                            .addComponent(PhoneNumberBox)
                            .addComponent(NameBox)
                            .addComponent(IDBox))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        CoachInfoPaneLayout.setVerticalGroup(
            CoachInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CoachInfoPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TeamDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(IDBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NameBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PhoneNumberBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AgeBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WeightBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(WonBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LostBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(RosterButton)
                .addContainerGap())
        );

        CoachActionPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Coach Actions"));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel12.setText("Remove Player");

        RemovePlayerButton.setText("Remove");
        RemovePlayerButton.setBorder(null);
        RemovePlayerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemovePlayerButtonActionPerformed(evt);
            }
        });

        jLabel14.setText("Player Name");

        javax.swing.GroupLayout CoachActionPaneLayout = new javax.swing.GroupLayout(CoachActionPane);
        CoachActionPane.setLayout(CoachActionPaneLayout);
        CoachActionPaneLayout.setHorizontalGroup(
            CoachActionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CoachActionPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CoachActionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(CoachActionPaneLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PlayerToBeRemoved))
                    .addGroup(CoachActionPaneLayout.createSequentialGroup()
                        .addGroup(CoachActionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(RemovePlayerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        CoachActionPaneLayout.setVerticalGroup(
            CoachActionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CoachActionPaneLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(CoachActionPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(PlayerToBeRemoved, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(RemovePlayerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CoachActionPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(CoachInfoPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CoachInfoPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(CoachActionPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        CoachTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        CoachTablePane.setViewportView(CoachTable);

        javax.swing.GroupLayout CoachPaneLayout = new javax.swing.GroupLayout(CoachPane);
        CoachPane.setLayout(CoachPaneLayout);
        CoachPaneLayout.setHorizontalGroup(
            CoachPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CoachPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CoachTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 828, Short.MAX_VALUE)
                .addContainerGap())
        );
        CoachPaneLayout.setVerticalGroup(
            CoachPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CoachPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CoachPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CoachPaneLayout.createSequentialGroup()
                        .addComponent(CoachTablePane)
                        .addGap(90, 90, 90)))
                .addContainerGap())
        );

        MainPane.addTab("Coach", CoachPane);

        TeamInfoPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Team Information"));

        jLabel1.setText("Select Team:");

        TeamDropdown2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        

        jLabel9.setText("View team with");

        GetGoalsAverage.setText("Most Goals");
        GetGoalsAverage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetGoalsAverageActionPerformed(evt);
            }
        });

        ViewWinCount.setText("Search");
        ViewWinCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewWinCountActionPerformed(evt);
            }
        });

        GetGoalsAverage1.setText("Fewest Goals");
        GetGoalsAverage1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetGoalsAverage1ActionPerformed(evt);
            }
        });

        jLabel10.setText("Find");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setText("goals for");

        javax.swing.GroupLayout TeamInfoPaneLayout = new javax.swing.GroupLayout(TeamInfoPane);
        TeamInfoPane.setLayout(TeamInfoPaneLayout);
        TeamInfoPaneLayout.setHorizontalGroup(
            TeamInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TeamInfoPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TeamInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TeamInfoPaneLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(TeamInfoPaneLayout.createSequentialGroup()
                        .addGroup(TeamInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(GetGoalsAverage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ViewWinCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GetGoalsAverage1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(TeamInfoPaneLayout.createSequentialGroup()
                                .addGroup(TeamInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addGroup(TeamInfoPaneLayout.createSequentialGroup()
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel13)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(TeamInfoPaneLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(80, 80, 80))
                            .addComponent(TeamDropdown2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        TeamInfoPaneLayout.setVerticalGroup(
            TeamInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TeamInfoPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GetGoalsAverage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GetGoalsAverage1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TeamDropdown2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TeamInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addComponent(ViewWinCount)
                .addGap(12, 12, 12))
        );

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(TeamInfoPane, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(TeamInfoPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 262, Short.MAX_VALUE)))
                .addContainerGap())
        );

        MainPane.addTab("Team", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPane, javax.swing.GroupLayout.PREFERRED_SIZE, 634, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>  

     

	// Button for showing the roster on coach page.
    // DONE 
    private void RosterButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	ArrayList<String> paramlist = new ArrayList<String>();
    	if(IDBox.isSelected()) paramlist.add("player_id");
    	if(NameBox.isSelected()) paramlist.add("name");
    	if(PhoneNumberBox.isSelected()) paramlist.add("p.phone_no");
    	if(AgeBox.isSelected()) paramlist.add("age");
    	if(WeightBox.isSelected()) paramlist.add("weight");
    	if(WonBox.isSelected()) paramlist.add("matches_won");
    	if(LostBox.isSelected()) paramlist.add("matches_lost");
    	
    	String[] params = paramlist.toArray(new String[paramlist.size()]);
        String teamname = (String) TeamDropdown.getSelectedItem();
        ResultSet rs = queries.joinQuery(params, teamname);
        CoachTable.setModel(buildTable(rs));
    	CoachTable.validate();
        
    }                                            
    // Button "View number of wins" on player page.
    // DONE
    private void ViewWinCountActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	String teamname = (String) TeamDropdown2.getSelectedItem();
    	String aggtype = (String) jComboBox1.getSelectedItem();
    	ResultSet rs = queries.aggregationQueryAvgMaxMin(aggtype, teamname);
    	if(aggtype.equals("Avg"))
    		jTextArea1.setText(teamname + " has a goals per game average of:\n");
    	else if(aggtype.equals("Max"))
    		jTextArea1.setText(teamname + " has a max goals per game of:\n");
    	else
    		jTextArea1.setText(teamname + " has a minimum goals per game of:\n");
    	try {
    		rs.first();
			System.out.print(rs.getString(1));
			jTextArea1.append(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }                                            
    
    // Teams with most goals.
    // DONE
    private void GetGoalsAverageActionPerformed(java.awt.event.ActionEvent evt) {                                                

    	ResultSet rs = queries.nestedQueryBiggest();
    	
    	jTextArea1.setText("Team with the most goals scored:\n");
    	try {
    		rs.first();
			System.out.print(rs.getString(1));
			jTextArea1.append(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    }                 
    // Teams with the fewest goals.
    // DONE
    protected void GetGoalsAverage1ActionPerformed(ActionEvent evt) {

    	ResultSet rs = queries.nestedQuerySmallest();
    	jTextArea1.setText("Team with the fewest goals scored:\n");
    	try {
    		rs.first();
			System.out.print(rs.getString(1));
			jTextArea1.append(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

    // View Player info button on player page.
    // DONE
    private void ViewPlayerInfoActionPerformed(java.awt.event.ActionEvent evt) {
    	
    	ArrayList<String> paramlist = new ArrayList<String>();
    	if(IDBox1.isSelected()) paramlist.add("player_id");
    	if(NameBox1.isSelected()) paramlist.add("name");
    	if(PhoneNumberBox1.isSelected()) paramlist.add("phone_no");
    	if(AgeBox1.isSelected()) paramlist.add("age");
    	if(WeightBox1.isSelected()) paramlist.add("weight");
    	if(WonBox1.isSelected()) paramlist.add("matches_won");
    	if(LostBox1.isSelected()) paramlist.add("matches_lost");
    	if(LostBox2.isSelected()) paramlist.add("team_name");
    	
    	String[] params = paramlist.toArray(new String[paramlist.size()]);
        String name = (String) "%"+PlayerName.getText()+"%";
    	
    	
    	ResultSet rs = queries.selectPlayer(params, name);

        PlayerTable.setModel(new DefaultTableModel());
    	PlayerTable.setModel(buildTable(rs));
    	PlayerTable.validate();
    }                                              
                                         

    // Display referees who have refd every game on ref page.
    private void ViewRefereeActionPerformed(java.awt.event.ActionEvent evt) {                                            
        ResultSet rs = queries.divisionQuery();
        String queryString = "Select * from MatchInfo";
        RefereeTable.setModel(buildTable(rs));
    	RefereeTable.validate();
		
    }                                           

    // Change game times on ref page.
    private void UpdateGameTimeActionPerformed(java.awt.event.ActionEvent evt) {                                               
       
        DateFormat df = new SimpleDateFormat("MM-DD-YYYY HH:MM");
        String Date = jSpinner2.getValue().toString();
        Date = Date + "-" + (String) jSpinner4.getValue().toString();
        Date = Date + "-" + (String) jSpinner5.getValue().toString();
        Date = Date + " " + (String) jSpinner6.getValue().toString();
        Date = Date + ":" + (String) jSpinner7.getValue().toString();

        
        
        System.out.println(Date);
        int matchID = (int)GameID.getValue();
        queries.updateQuery(Date, matchID);
		String queryString = "Select * from MatchInfo";
        try {
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = stmt.executeQuery(queryString);
			RefereeTable.setModel(buildTable(rs));
	    	RefereeTable.validate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }                                              

    // Remove player on Coach page.
    // DONE
    private void RemovePlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        if(PlayerToBeRemoved.getText().equals("")) return;
    	String name = "%"+PlayerToBeRemoved.getText()+"%";
        queries.deleteQuery(name);
    }     
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
    	String team = (String) jComboBox2.getSelectedItem();
    	String year = (String) jSpinner1.getValue().toString();
        ResultSet rs =  queries.bonusQuery(team, year);

        PlayerTable.setModel(new DefaultTableModel());
        PlayerTable.setModel(buildTable(rs));
    	PlayerTable.validate();
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        String team = (String) jComboBox2.getSelectedItem();
        String[] result = queries.bonusQuery2(team);

        
        Object[] columns = new Object[3];
        columns[0] = "Longest Streak";
        columns[1] = "Start";
        columns[2] = "End";
    	Object[][] data = new Object[1][3];
    	data[0][0] = result[0];
    	data[0][1] = result[1];
    	data[0][2] = result[2];
    	DefaultTableModel tableModle = new DefaultTableModel(data, columns);
    	PlayerTable.setModel(tableModle);
    	PlayerTable.validate();
    }     

    // Takes in the result set and returns the table model to be displayed
    private DefaultTableModel buildTable(ResultSet rs){
    	ResultSetMetaData rsmd;
    	
    	int height =  0;
    	int columnCount = 0;
		try {
			rsmd = rs.getMetaData();
			rs.last();
	    	height = rs.getRow();
	    	columnCount = rsmd.getColumnCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Object[] columns = new Object[columnCount];
    	Object[][] data = new Object[height][columnCount];
    	
		try {
			rsmd = rs.getMetaData();
	    	for(int i = 1; i <= columnCount; i++)
	    		columns[i-1] = rsmd.getColumnName(i);
	    
	    	rs.absolute(1);
	    	for(int i = 1; i <= height; i++){
	    		for( int j = 1; j <= columnCount; j++){
	    			data[i-1][j-1] = rs.getString(j);
	    		}
	    		rs.next();
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	DefaultTableModel tableModel = new DefaultTableModel(data, columns);
    	return tableModel;
    }
    
    
    public static void runUI(String username, String password) {
        // Make it look like a windows program
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI(username, password).setVisible(true);
            }
        });
    }

    private javax.swing.JCheckBox AgeBox;
    private javax.swing.JCheckBox AgeBox1;
    private javax.swing.JPanel CoachActionPane;
    private javax.swing.JPanel CoachInfoPane;
    private javax.swing.JPanel CoachPane;
    private javax.swing.JTable CoachTable;
    private javax.swing.JScrollPane CoachTablePane;
    private javax.swing.JSpinner GameID;
    private javax.swing.JButton GetGoalsAverage;
    private javax.swing.JButton GetGoalsAverage1;
    private javax.swing.JCheckBox IDBox;
    private javax.swing.JCheckBox IDBox1;
    private javax.swing.JCheckBox LostBox;
    private javax.swing.JCheckBox LostBox1;
    private javax.swing.JCheckBox LostBox2;
    private javax.swing.JTabbedPane MainPane;
    private javax.swing.JCheckBox NameBox;
    private javax.swing.JCheckBox NameBox1;
    private javax.swing.JCheckBox PhoneNumberBox;
    private javax.swing.JCheckBox PhoneNumberBox1;
    private javax.swing.JPanel PlayerInfoPane;
    private javax.swing.JTextField PlayerName;
    private javax.swing.JPanel PlayerPane;
    private javax.swing.JTable PlayerTable;
    private javax.swing.JScrollPane PlayerTablePane;
    private javax.swing.JTextField PlayerToBeRemoved;
    private javax.swing.JPanel RefSidePane;
    private javax.swing.JPanel RefereeActionsPane;
    private javax.swing.JPanel RefereeInfoPane;
    private javax.swing.JPanel RefereePane;
    private javax.swing.JTable RefereeTable;
    private javax.swing.JScrollPane RefereeTablePane;
    private javax.swing.JButton RemovePlayerButton;
    private javax.swing.JButton RosterButton;
    private javax.swing.JComboBox<String> TeamDropdown;
    private javax.swing.JComboBox<String> TeamDropdown2;
    private javax.swing.JPanel TeamInfoPane;
    private javax.swing.JButton UpdateGameTime;
    private javax.swing.JButton ViewPlayerInfo;
    private javax.swing.JButton ViewReferee;
    private javax.swing.JButton ViewWinCount;
    private javax.swing.JCheckBox WeightBox;
    private javax.swing.JCheckBox WeightBox1;
    private javax.swing.JCheckBox WonBox;
    private javax.swing.JCheckBox WonBox1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JSpinner jSpinner7;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration              
}

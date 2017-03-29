
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import java.sql.*;


public class UI extends javax.swing.JFrame {

    /**
     * Creates new form UI
     */
	
	// command line reader
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	private Connection con;

	javax.swing.table.DefaultTableModel PlayerTableModel;
	
	
    public UI() {
    	
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

		String username = "ora_l5z8";
		String password = "a28626117";
		
		try {
			con = DriverManager.getConnection(connectURL, username, password);
			System.out.println("\nConnected to Oracle!");
		} catch (SQLException ex) {
			System.out.println("Message: " + ex.getMessage());
		}
        

        TeamDropdown2.removeAllItems();
        TeamDropdown.removeAllItems();
        TeamDropdown2.addItem("ALL");
		 TeamDropdown.addItem("ALL");
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT team_name FROM Team");
			while (rs.next()) {
			  TeamDropdown2.addItem(rs.getString(1));
			  TeamDropdown.addItem(rs.getString(1));
			}
		} catch (SQLException e1) {
			System.out.println("Message: " + e1.getMessage());
		}
		
        
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        MainPane = new javax.swing.JTabbedPane();
        PlayerPane = new javax.swing.JPanel();
        PlayerInfoPane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PlayerName = new javax.swing.JTextField();
        ViewPlayerStats = new javax.swing.JButton();
        ViewPlayerInfo = new javax.swing.JButton();
        TeamInfoPane = new javax.swing.JPanel();
        VeiwRoster2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        TeamDropdown2 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        GetGoalsAverage = new javax.swing.JButton();
        ViewWinCount = new javax.swing.JButton();
        PlayerTablePane = new javax.swing.JScrollPane();
        PlayerTable = new javax.swing.JTable();
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
        jSpinner3 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
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

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PlayerInfoPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Player Information"));

        jLabel2.setText("Player Name:");

        ViewPlayerStats.setText("View Player Stats");
        ViewPlayerStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPlayerStatsActionPerformed(evt);
            }
        });

        ViewPlayerInfo.setText("View Player Info");
        ViewPlayerInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPlayerInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PlayerInfoPaneLayout = new javax.swing.GroupLayout(PlayerInfoPane);
        PlayerInfoPane.setLayout(PlayerInfoPaneLayout);
        PlayerInfoPaneLayout.setHorizontalGroup(
            PlayerInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayerInfoPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PlayerInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ViewPlayerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ViewPlayerStats, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PlayerName)
                    .addGroup(PlayerInfoPaneLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        PlayerInfoPaneLayout.setVerticalGroup(
            PlayerInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PlayerInfoPaneLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ViewPlayerInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ViewPlayerStats)
                .addContainerGap())
        );

        TeamInfoPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Team Information"));

        VeiwRoster2.setText("View Roster");
        VeiwRoster2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VeiwRoster2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Team:");

        TeamDropdown2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        

        jLabel9.setText("Teams with best and worst");

        jLabel10.setText("average goals for");

        GetGoalsAverage.setText("View");
        GetGoalsAverage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GetGoalsAverageActionPerformed(evt);
            }
        });

        ViewWinCount.setText("View Number of Wins");
        ViewWinCount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewWinCountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TeamInfoPaneLayout = new javax.swing.GroupLayout(TeamInfoPane);
        TeamInfoPane.setLayout(TeamInfoPaneLayout);
        TeamInfoPaneLayout.setHorizontalGroup(
            TeamInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TeamInfoPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(TeamInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(VeiwRoster2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TeamDropdown2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(TeamInfoPaneLayout.createSequentialGroup()
                        .addGroup(TeamInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(ViewWinCount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(GetGoalsAverage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        TeamInfoPaneLayout.setVerticalGroup(
            TeamInfoPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TeamInfoPaneLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel9)
                .addGap(2, 2, 2)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(GetGoalsAverage)
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TeamDropdown2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(VeiwRoster2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ViewWinCount)
                .addContainerGap(16, Short.MAX_VALUE))
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

        javax.swing.GroupLayout PlayerPaneLayout = new javax.swing.GroupLayout(PlayerPane);
        PlayerPane.setLayout(PlayerPaneLayout);
        PlayerPaneLayout.setHorizontalGroup(
            PlayerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PlayerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PlayerInfoPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TeamInfoPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(PlayerTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                .addContainerGap())
        );
        PlayerPaneLayout.setVerticalGroup(
            PlayerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PlayerPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PlayerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PlayerTablePane)
                    .addGroup(PlayerPaneLayout.createSequentialGroup()
                        .addComponent(TeamInfoPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PlayerInfoPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 145, Short.MAX_VALUE)))
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

        jSpinner3.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(915213600000L), new java.util.Date(915213600000L), new java.util.Date(915213600000L), java.util.Calendar.DAY_OF_MONTH));
        jSpinner3.setBorder(null);

        jLabel7.setText("Game ID");

        javax.swing.GroupLayout RefereeActionsPaneLayout = new javax.swing.GroupLayout(RefereeActionsPane);
        RefereeActionsPane.setLayout(RefereeActionsPaneLayout);
        RefereeActionsPaneLayout.setHorizontalGroup(
            RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RefereeActionsPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(RefereeActionsPaneLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(40, 40, 40)
                        .addComponent(GameID, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(RefereeActionsPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RefereeActionsPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(UpdateGameTime, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(18, 18, 18)
                .addComponent(jSpinner3, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addGap(18, 18, 18)
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
                .addContainerGap(151, Short.MAX_VALUE))
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
                .addComponent(RefereeTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
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
                .addComponent(CoachTablePane, javax.swing.GroupLayout.DEFAULT_SIZE, 802, Short.MAX_VALUE)
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
    private void RosterButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        
    }                                            
    // Button "View number of wins" on player page.
    private void ViewWinCountActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            
    
    // Teams with best and worst goals, on player page.
    private void GetGoalsAverageActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                                                                     

    // View roster on player page.
    private void VeiwRoster2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    	Statement stmt;
    	ResultSet rs;
    	ResultSetMetaData rsmd;
		String teamName = (String)TeamDropdown2.getSelectedItem();
    	try {
    		
    		
    		
    		
    		
    		
    		if(teamName == "ALL"){
	    		stmt = con.createStatement();
				rs = stmt.executeQuery("Select * from player");
				rsmd = rs.getMetaData();
				while (rs.next()) {
					for(int i = 1; i <= rsmd.getColumnCount(); i++)
						System.out.println(rs.getString(i));
				}
				
				
    		}
    		else{
    			String Query = "Select p.name, p.matches_won from Team cwt, "
    					+ "Player p where cwt.team_name"
    					+ " = p.team_name AND cwt.team_name = '" +teamName+  "'";
    			stmt = con.createStatement();
    			rs = stmt.executeQuery(Query);
    			System.out.println(Query);
    			rsmd = rs.getMetaData();
    			while (rs.next()) {
    			  System.out.println(rs.getString(1) + "  " + rs.getString(2));
    			}
    		}
			
    		
    		DefaultTableModel dtm = new DefaultTableModel(0,0);
    		dtm.setColumnIdentifiers(new String[]{"Player Name", "Games Won"});
    		PlayerTable.setModel(dtm);
    		int columnCount = rsmd.getColumnCount();
    		while (rs.next()) {
    			Object rowData = new Object[columnCount];
				for(int i = 1; i <= columnCount; i++){
					System.out.println(rs.getString(i));
					rowData[i-1] = rs.getString(i);
				}
			}
    		
    		
    		
			
		} catch (SQLException e1) {
			System.out.println("Message: " + e1.getMessage());
		}
    } 
    
    // View Player info button on player page.
    private void ViewPlayerInfoActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              
    
    // View player stats on player page.
    private void ViewPlayerStatsActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
    }                                               

    // Display referees who have refd every game on ref page.
    private void ViewRefereeActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    // Change game times on ref page.
    private void UpdateGameTimeActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    // Remove player on Coach page.
    private void RemovePlayerButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }                                                  

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JCheckBox AgeBox;
    private javax.swing.JPanel CoachActionPane;
    private javax.swing.JPanel CoachInfoPane;
    private javax.swing.JPanel CoachPane;
    private javax.swing.JTable CoachTable;
    private javax.swing.JScrollPane CoachTablePane;
    private javax.swing.JSpinner GameID;
    private javax.swing.JButton GetGoalsAverage;
    private javax.swing.JCheckBox IDBox;
    private javax.swing.JCheckBox LostBox;
    private javax.swing.JTabbedPane MainPane;
    private javax.swing.JCheckBox NameBox;
    private javax.swing.JCheckBox PhoneNumberBox;
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
    private javax.swing.JButton VeiwRoster2;
    private javax.swing.JButton ViewPlayerInfo;
    private javax.swing.JButton ViewPlayerStats;
    private javax.swing.JButton ViewReferee;
    private javax.swing.JButton ViewWinCount;
    private javax.swing.JCheckBox WeightBox;
    private javax.swing.JCheckBox WonBox;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JSpinner jSpinner3;
    // End of variables declaration                   
}

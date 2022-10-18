/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umsl;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
//import java.text.*;
import javax.swing.*;
import javax.swing.event.*;
import org.apache.derby.jdbc.*;

/**
 *
 * @author brilaw
 */
public class Eval extends JFrame implements ActionListener, ItemListener {
//DECLARE THE ELEMENTS OR OBJECTS THAT YOU WILL PUT IN YOUR FRAME
//NOTICE HOW A PANEL IS CREATED FOR EACH ONE THIS WILL MAKE IT EASIER BUILD

    public JLabel teamLabel;
    private JComboBox teamComboBox;
    private JPanel teamPanel;

    private JLabel questionLabel1, questionLabel2, questionLabel3, questionLabel4;
    private JRadioButton rb11, rb12, rb13, rb14, rb15;
    private JRadioButton rb21, rb22, rb23, rb24, rb25;
    private JRadioButton rb31, rb32, rb33, rb34, rb35;
    private JRadioButton rb41, rb42, rb43, rb44, rb45;
//    private JRadioButton rb5;

    private JPanel questionPanell, questionPanel2, questionPanel3, questionPanel4;
    private ButtonGroup questionGroup1, questionGroup2, questionGroup3, questionGroup4;

    private JButton submitButton;
    private JButton clearButton;
    private JPanel buttonPanel;

    private JLabel commentsLabel;
//    private JComboBox commentsComboBox;
    private JPanel commentsPanel;
    private JEditorPane commentsEditorPane;

    private JLabel computedLabel;
//    private JComboBox computedComboBox;
    private JPanel computedPanel;
    private JButton computedButton;
    private JEditorPane computedEditorPane;

    //instance variables to hold our data from the gui object to update the database
    String myteamname;
    // String courseName;
    int q1;
    int q2;
    int q3;
    int q4;
    double teamavg;
    boolean avgcalculated;
    String teamcomments;
    // instance variables used to manipulate database
    private Connection myConnection;
    private Statement myStatement;
    private ResultSet myResultSet;

    //MAIN METHOD: NOTICE WE TAKE IN THE ARGUMENTS THAT ARE
    //PASSED IN AND INSTANTIATE OUR CLASS WITH THEM
    public static void main(String args[]) {

        String databaseDriver = "org.apache.derby.jdbc.ClientDriver";
        //String databaseDriver = "sun.jdbc.odbc.JdbcOdbcDriver";

        String databaseURL = "jdbc:derby://localhost:1527/Eval";

        // create new Eval
        Eval eval = new Eval(databaseDriver, databaseURL);
        //eval.createUserInterface();
        eval.createUserInterface(); // set up GUI

        eval.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    //CONSTRUCTOR: WE SET UP OUR DATABASE HERE THEN MAKE A CALL
    //TO A FUNCTION CALLED CREATEUSERINTERFACE TO BUILD OUR GUI
    public Eval(String databaseDriver, String databaseURL) {
        // establish connection to database
        try {
            //Class.forName( "org.apache.derby.jdbc.ClientDriver");
            DriverManager.registerDriver(new ClientDriver());
            // connect to database
            myConnection = DriverManager.getConnection("jdbc:derby://localhost:1527/Eval");

            // create Statement for executing SQL
            myStatement = myConnection.createStatement();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
//            catch ( ClassNotFoundException exception )
//            {
//              exception.printStackTrace();
//            }       

    }

    private void createUserInterface() {
        // get content pane for attaching GUI components
        Container contentPane = getContentPane();

        // enable explicit positioning of GUI components
        contentPane.setLayout(null);

        // TEAM COMBO BOX SET UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // set up Team Panel
        teamPanel = new JPanel();
        teamPanel.setBounds(40, 20, 320, 48);
        teamPanel.setBorder(BorderFactory.createEtchedBorder());
        teamPanel.setLayout(null);
        contentPane.add(teamPanel);

        // set up Instructor Label
        teamLabel = new JLabel();
        teamLabel.setBounds(25, 15, 100, 20);
        teamLabel.setText("TEAMS:");
        teamPanel.add(teamLabel);

        // set up accountNumberJComboBox
        teamComboBox = new JComboBox();
        teamComboBox.setBounds(150, 15, 150, 25);
        teamComboBox.addItem("");
        teamComboBox.setSelectedIndex(0);
        teamPanel.add(teamComboBox);

        //RADIO BUTTON SET UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // set up Question Panel and Radio Buttons
        questionPanell = new JPanel();
        questionPanell.setBounds(40, 100, 320, 75);
        questionPanell.setBorder(BorderFactory.createEtchedBorder());
        questionPanell.setLayout(null);
        contentPane.add(questionPanell);

        // set up question1 Label
        questionLabel1 = new JLabel();
        questionLabel1.setBounds(10, 15, 320, 20);
        questionLabel1.setText("Q1: Technical?");
        questionPanell.add(questionLabel1);

        // set up the radio buttons for question 1
        rb11 = new JRadioButton("1", false);
        rb11.setBounds(20, 30, 40, 40);
        rb11.setVisible(true);
        rb11.addItemListener(this);

        rb12 = new JRadioButton("2", false);
        rb12.setBounds(80, 30, 40, 40);
        rb12.setVisible(true);
        rb12.addItemListener(this);

        rb13 = new JRadioButton("3", false);
        rb13.setBounds(140, 30, 40, 40);
        rb13.setVisible(true);
        rb13.addItemListener(this);

        rb14 = new JRadioButton("4", false);
        rb14.setBounds(200, 30, 40, 40);
        rb14.setVisible(true);
        rb14.addItemListener(this);

        rb15 = new JRadioButton("5", false);
        rb15.setBounds(260, 30, 40, 40);
        rb15.setVisible(true);
        rb15.addItemListener(this);

        // create logical relationship between JRadioButtons
        questionGroup1 = new ButtonGroup();
        questionGroup1.add(rb11);
        questionGroup1.add(rb12);
        questionGroup1.add(rb13);
        questionGroup1.add(rb14);
        questionGroup1.add(rb15);

        // add radio button to the panel
        questionPanell.add(rb11);
        questionPanell.add(rb12);
        questionPanell.add(rb13);
        questionPanell.add(rb14);
        questionPanell.add(rb15);

// set up Question Panel and Radio Buttons
        questionPanel2 = new JPanel();
        questionPanel2.setBounds(40, 190, 320, 75);
        questionPanel2.setBorder(BorderFactory.createEtchedBorder());
        questionPanel2.setLayout(null);
        contentPane.add(questionPanel2);

        // set up question1 Label
        questionLabel2 = new JLabel();
        questionLabel2.setBounds(10, 15, 320, 20);
        questionLabel2.setText("Q2: Useful?");
        questionPanel2.add(questionLabel2);

        // set up the radio buttons for question 1
        rb21 = new JRadioButton("1", false);
        rb21.setBounds(20, 30, 40, 40);
        rb21.setVisible(true);
        rb21.addItemListener(this);

        rb22 = new JRadioButton("2", false);
        rb22.setBounds(80, 30, 40, 40);
        rb22.setVisible(true);
        rb22.addItemListener(this);

        rb23 = new JRadioButton("3", false);
        rb23.setBounds(140, 30, 40, 40);
        rb23.setVisible(true);
        rb23.addItemListener(this);

        rb24 = new JRadioButton("4", false);
        rb24.setBounds(200, 30, 40, 40);
        rb24.setVisible(true);
        rb24.addItemListener(this);

        rb25 = new JRadioButton("5", false);
        rb25.setBounds(260, 30, 40, 40);
        rb25.setVisible(true);
        rb25.addItemListener(this);

        // create logical relationship between JRadioButtons
        questionGroup2 = new ButtonGroup();
        questionGroup2.add(rb21);
        questionGroup2.add(rb22);
        questionGroup2.add(rb23);
        questionGroup2.add(rb24);
        questionGroup2.add(rb25);

        // add radio button to the panel
        questionPanel2.add(rb21);
        questionPanel2.add(rb22);
        questionPanel2.add(rb23);
        questionPanel2.add(rb24);
        questionPanel2.add(rb25);

// set up Question Panel and Radio Buttons
        questionPanel3 = new JPanel();
        questionPanel3.setBounds(40, 280, 320, 75);
        questionPanel3.setBorder(BorderFactory.createEtchedBorder());
        questionPanel3.setLayout(null);
        contentPane.add(questionPanel3);

        // set up question1 Label
        questionLabel3 = new JLabel();
        questionLabel3.setBounds(10, 15, 320, 20);
        questionLabel3.setText("Q3: Clarity?");
        questionPanel3.add(questionLabel3);

        // set up the radio buttons for question 1
        rb31 = new JRadioButton("1", false);
        rb31.setBounds(20, 30, 40, 40);
        rb31.setVisible(true);
        rb31.addItemListener(this);

        rb32 = new JRadioButton("2", false);
        rb32.setBounds(80, 30, 40, 40);
        rb32.setVisible(true);
        rb32.addItemListener(this);

        rb33 = new JRadioButton("3", false);
        rb33.setBounds(140, 30, 40, 40);
        rb33.setVisible(true);
        rb33.addItemListener(this);

        rb34 = new JRadioButton("4", false);
        rb34.setBounds(200, 30, 40, 40);
        rb34.setVisible(true);
        rb34.addItemListener(this);

        rb35 = new JRadioButton("5", false);
        rb35.setBounds(260, 30, 40, 40);
        rb35.setVisible(true);
        rb35.addItemListener(this);

        // create logical relationship between JRadioButtons
        questionGroup3 = new ButtonGroup();
        questionGroup3.add(rb31);
        questionGroup3.add(rb32);
        questionGroup3.add(rb33);
        questionGroup3.add(rb34);
        questionGroup3.add(rb35);

        // add radio button to the panel
        questionPanel3.add(rb31);
        questionPanel3.add(rb32);
        questionPanel3.add(rb33);
        questionPanel3.add(rb34);
        questionPanel3.add(rb35);

// set up Question Panel and Radio Buttons
        questionPanel4 = new JPanel();
        questionPanel4.setBounds(40, 370, 320, 75);
        questionPanel4.setBorder(BorderFactory.createEtchedBorder());
        questionPanel4.setLayout(null);
        contentPane.add(questionPanel4);

        // set up question1 Label
        questionLabel4 = new JLabel();
        questionLabel4.setBounds(10, 15, 320, 20);
        questionLabel4.setText("Q4: Overall?");
        questionPanel4.add(questionLabel4);

        // set up the radio buttons for question 1
        rb41 = new JRadioButton("1", false);
        rb41.setBounds(20, 30, 40, 40);
        rb41.setVisible(true);
        rb41.addItemListener(this);

        rb42 = new JRadioButton("2", false);
        rb42.setBounds(80, 30, 40, 40);
        rb42.setVisible(true);
        rb42.addItemListener(this);

        rb43 = new JRadioButton("3", false);
        rb43.setBounds(140, 30, 40, 40);
        rb43.setVisible(true);
        rb43.addItemListener(this);

        rb44 = new JRadioButton("4", false);
        rb44.setBounds(200, 30, 40, 40);
        rb44.setVisible(true);
        rb44.addItemListener(this);

        rb45 = new JRadioButton("5", false);
        rb45.setBounds(260, 30, 40, 40);
        rb45.setVisible(true);
        rb45.addItemListener(this);

        // create logical relationship between JRadioButtons
        questionGroup4 = new ButtonGroup();
        questionGroup4.add(rb41);
        questionGroup4.add(rb42);
        questionGroup4.add(rb43);
        questionGroup4.add(rb44);
        questionGroup4.add(rb45);

        // add radio button to the panel
        questionPanel4.add(rb41);
        questionPanel4.add(rb42);
        questionPanel4.add(rb43);
        questionPanel4.add(rb44);
        questionPanel4.add(rb45);

        // set up comments Panel
        commentsPanel = new JPanel();
        commentsPanel.setBounds(40, 460, 320, 75);
        commentsPanel.setBorder(BorderFactory.createEtchedBorder());
        commentsPanel.setLayout(null);
        contentPane.add(commentsPanel);

        commentsLabel = new JLabel();
        commentsLabel.setBounds(10, 15, 100, 20);
        commentsLabel.setText("Comments:");
        commentsPanel.add(commentsLabel);

//        commentsComboBox = new JComboBox();
//        commentsComboBox.setBounds(150, 15, 96, 25);
//        commentsComboBox.addItem("");
//        commentsComboBox.setSelectedIndex(0);
//        commentsPanel.add(commentsComboBox);
        commentsEditorPane = new JEditorPane();
        commentsEditorPane.setBounds(100, 15, 200, 50);
        commentsPanel.add(commentsEditorPane);

// set up computed Panel
        computedPanel = new JPanel();
        computedPanel.setBounds(40, 550, 320, 75);
        computedPanel.setBorder(BorderFactory.createEtchedBorder());
        computedPanel.setLayout(null);
        contentPane.add(computedPanel);

        computedLabel = new JLabel();
        computedLabel.setBounds(10, 5, 350, 20);
        computedLabel.setText("Computed Average from questions above:");
        computedPanel.add(computedLabel);

//        computedComboBox = new JComboBox();
//        computedComboBox.setBounds(150, 15, 96, 25);
//        computedComboBox.addItem("");
//        computedComboBox.setSelectedIndex(0);
//        computedPanel.add(computedComboBox);
        computedEditorPane = new JEditorPane();
        computedEditorPane.setBounds(200, 35, 100, 30);
        computedPanel.add(computedEditorPane);

        computedButton = new JButton("Calc Avg");
        computedButton.setBounds(25, 40, 100, 30);
        computedButton.setVisible(true);
        computedPanel.add(computedButton);
        computedButton.addActionListener(this);

// SUBMIT BUTTON SET UP!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        buttonPanel = new JPanel();
        buttonPanel.setBounds(40, 640, 320, 70);
        buttonPanel.setBorder(BorderFactory.createEtchedBorder());
        buttonPanel.setLayout(null);
        contentPane.add(buttonPanel);

        submitButton = new JButton("SUBMIT");
        submitButton.setBounds(20, 15, 100, 40);
        submitButton.setVisible(true);
        buttonPanel.add(submitButton);
        submitButton.addActionListener(this);

        clearButton = new JButton("CLEAR");
        clearButton.setBounds(200, 15, 100, 40);
        clearButton.setVisible(true);
        buttonPanel.add(clearButton);
        clearButton.addActionListener(this);

        JSlider myslider = new JSlider(JSlider.HORIZONTAL, 1, 5, 1);

        myslider.setBounds(80, 50, 200, 200);
        buttonPanel.add(myslider);
        //read teams from database and
        // place them in teamsJComboBox
//        loadTeams();

        setTitle("EVAL"); // set title bar string
        setSize(375, 410); // set window size
        setVisible(true); // display window
    }

    //OVERRIDING THIS FUNCTION BECAUSE OUR CLASS IMPLEMENTS THE ACTION LISTENER
    @Override
    public void actionPerformed(ActionEvent event) {

        if (event.getSource().equals(submitButton)) {
            myteamname = (String) teamComboBox.getSelectedItem();

            if (rb1.isSelected()) {
                q1 = Integer.parseInt(rb1.getText());
            } else if (rb2.isSelected()) {
                q1 = Integer.parseInt(rb2.getText());
            } else if (rb3.isSelected()) {
                q1 = Integer.parseInt(rb3.getText());
            }

            q2 = 8;
            q3 = 2;
            q4 = 5;
            teamavg = ((q1 + q2 + q3 + q4) / 4);
            teamcomments = "Not a bad presentation not a good one either";

            updateTeams();
        }
        // else if(event.getSource().equals(clearButton))
        // {
        // textavgtextbox.text = "";
        // submitButton.setEnabled(false);
        // }
        // else if(event.getSource().equals(teamavgButton))
        // {
        // int tempval1 = slidertechnical.getValue();
        // int tempval2 = slideruse.getValue();
        // int tempval3 = sliderclarity.getValue();
        // int tempval4 = slideroverall.getValue();
        // teamavg = (double)(tempval1 + teampval2 + tempval3 + tempval4)/4.0
        // teamavgTextBox.text = teamavg;
        // submitButton.setEnabled(true);
        // avgcalculated = true;

    }

    @Override
    public void itemStateChanged(ItemEvent event) {

        if (event.getSource() == rb1 && event.getStateChange() == ItemEvent.SELECTED) {
            q1 = Integer.parseInt(rb1.getText());
        } else if (event.getSource() == rb2 && event.getStateChange() == ItemEvent.SELECTED) {
            q1 = Integer.parseInt(rb2.getText());
        } else if (event.getSource() == rb3 && event.getStateChange() == ItemEvent.SELECTED) {
            q1 = Integer.parseInt(rb3.getText());
        } else if (event.getSource() == rb1 && event.getStateChange() == ItemEvent.DESELECTED) {
            JOptionPane.showMessageDialog(null, "Eggs are not supposed to be green.");
        }
    }

    private void updateTeams() {
        // update teams in database
        try {
            // Below is an example of creating a SQL statement that updated more than a single field in one statement.
            String sql1 = "UPDATE APP.TEAM SET Q1 = " + q1
                    + ", Q2 = " + q2
                    + ", Q3 = " + q3
                    + ", Q4 = " + q4
                    + ", TEAMAVG = " + teamavg
                    + ", COMMENTS = " + "'" + teamcomments
                    + "'" + "WHERE " + "TEAMNAME = " + "'" + myteamname + "'";
            String sql2 = "UPDATE APP.TEAM SET Q2USEFUL = " + q2 + " WHERE " + "TEAMNAME = " + "'" + myteamname + "'";
            // String sql3;
            // String sql4;
            // String sql5;
            // String sql6 = "UPDATE APP.TEAM SET COMMENTS = " + "'" + teamcomments + "'" + " WHERE " + "TEAMS = " + "'" + myteamname + "'";
            myStatement.executeUpdate(sql1);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    } // end method updateBalance

    private void loadTeams() {
        // get all account numbers from database
        try {

            myResultSet = myStatement.executeQuery("SELECT TEAMNAME FROM APP.TEAM");
            // add account numbers to accountNumberJComboBox
            while (myResultSet.next()) {
                teamComboBox.addItem(myResultSet.getString("TEAMNAME"));
            }

            myResultSet.close(); // close myResultSet

        } // end try
        catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

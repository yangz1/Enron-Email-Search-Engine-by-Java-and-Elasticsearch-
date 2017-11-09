import java.awt.EventQueue;
import java.awt.GridLayout;
import java.time.Month;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.json.JSONObject;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.FlowLayout;

public class Main {
	
	private QueryHandler handler;

	private JFrame frame;
	private JTextField searchEntry;
	private JButton basicSearch;
	private JComboBox comboBox;
	private JPanel basicSearchPanel;
	private JPanel advancedSearchPanel;
	private JLabel year;
	private JTextField yearEntry;
	private JComboBox month;
	private JLabel subject;
	private JTextField topicEntry;
	private JComboBox day;
	private JScrollPane resultPanel;
	private JTable resultTable;
	private JButton btnAdvancedSearch;
	private JLabel label;
	private JTable aggregation;
	private JSplitPane sp;
	private JScrollPane aggPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		handler = new QueryHandler();
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.getContentPane().setLayout(new GridLayout(3, 1));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initialBasicSearch();
		initialAdvancedSearch();
		initialResultPanel();
		
		setlisteners();
		
	}
	
	private void initialBasicSearch(){
		basicSearchPanel = new JPanel();
		frame.getContentPane().add(basicSearchPanel);
		
		String[] searchTypes = new String[] {"All", "Sender","Receiver"};
		comboBox = new JComboBox(searchTypes);
		basicSearchPanel.add(comboBox);
		
		searchEntry = new JTextField();
		searchEntry.setColumns(15);
		basicSearchPanel.add(searchEntry);
		
		basicSearch = new JButton();
		basicSearch.setText("Search");
		
		basicSearchPanel.add(basicSearch);
	}
	
	private void initialAdvancedSearch() {
		advancedSearchPanel = new JPanel();
		advancedSearchPanel.setLayout(new GridLayout(3, 3));
		frame.getContentPane().add(advancedSearchPanel);
		
		year = new JLabel("Year");
		year.setHorizontalAlignment(SwingConstants.CENTER);
		advancedSearchPanel.add(year);
		
		yearEntry = new JTextField();
		yearEntry.setColumns(2);
		advancedSearchPanel.add(yearEntry);
		
		btnAdvancedSearch = new JButton("Advanced Search");
		advancedSearchPanel.add(btnAdvancedSearch);
		
		String[] months = new String[] {"Month","Jan", "Feb","Mar",
				"Apr","May","Jun","Jul","Aug","Sep","Oct",
				"Nov","Dec"};
		month = new JComboBox(months); 
		advancedSearchPanel.add(month);
		
		String[] days = new String[] {"Day","1", "2","3","4","5","6","7","8","9",
				"10","11","12","13","14","15","16","17","18","19","20","21",
				"22","23","24","25","26","27","28","29","30","31"};
		day = new JComboBox(days); 
		advancedSearchPanel.add(day);
		
		label = new JLabel("");
		advancedSearchPanel.add(label);
		
		subject = new JLabel("Subject");
		subject.setHorizontalAlignment(SwingConstants.CENTER);
		advancedSearchPanel.add(subject);
		
		topicEntry = new JTextField();
		advancedSearchPanel.add(topicEntry);
		topicEntry.setColumns(10);
		
	}

	private void initialResultPanel(){
		String[] colName = new String[] {"subject","content"};
		Object[][] email = {};
		
		DefaultTableModel model = new DefaultTableModel(email, colName);
		resultTable = new JTable(model){
			public Class getColumnClass(int column){
				return getValueAt(0, column).getClass();
			}
		};
		resultTable.setPreferredScrollableViewportSize(resultTable.getPreferredSize());

		resultPanel = new JScrollPane(resultTable);
		
		//Set aggregation panel
		String[] aggName = new String[] {"Aggregation"};
		Object[][] agg = {};
		
		DefaultTableModel aggModel = new DefaultTableModel(agg, aggName);
		aggregation = new JTable(aggModel){
			public Class getColumnClass(int column){
				return getValueAt(0, column).getClass();
			}
		};
		aggregation.setPreferredScrollableViewportSize(aggregation.getPreferredSize());
		
		aggPanel = new JScrollPane(aggregation);		
		
		sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        sp.setResizeWeight(0.8);
        sp.setEnabled(false);
        sp.setDividerSize(0);

        sp.add(resultPanel);
        sp.add(aggPanel);
		
;		frame.getContentPane().add(sp);
	}
	
	private void setlisteners(){
		basicSearch.addMouseListener(new BasicSearchListener(handler, searchEntry, resultTable, comboBox, aggregation));
		btnAdvancedSearch.addMouseListener(new AdvancedSearchListener(handler, resultTable,month, day, yearEntry, topicEntry));
		resultTable.addMouseListener(new ResultListener(handler));
		aggregation.addMouseListener(new AggregationListener(handler, resultTable, aggregation));
	}
}

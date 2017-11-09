import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.json.JSONObject;

public class AdvancedSearchListener implements MouseListener{
	private QueryHandler handler;
	private JComboBox<String> m;
	private JComboBox<String> d;
	private JTextField y;
	private JTextField t;
	private JTable table;
	
	public AdvancedSearchListener(QueryHandler qh,JTable table, JComboBox<String> month,
			JComboBox<String> day, JTextField yr,JTextField tp) {
		this.handler = qh;
		this.m=month;
		this.d= day;
		this.y=yr;
		this.t=tp;
		this.table=table;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		Map<String, JSONObject> output = handler.advancedQuery(this.y.getText(), 
				(String)this.d.getSelectedItem(),
				(String)this.m.getSelectedItem(), 
				this.t.getText());
		model.setRowCount(0);
		for (String name: output.keySet()){
			try {
				model.addRow(new Object[]{name,output.get(name).getString("body")});
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		d.setSelectedIndex(0);
		m.setSelectedIndex(0);
		t.setText("");
		y.setText("");
			
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

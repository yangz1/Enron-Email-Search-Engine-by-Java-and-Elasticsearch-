import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.json.JSONObject;

public class BasicSearchListener implements MouseListener{
	private QueryHandler qh;
	private JTextField tf;
	private JTable table;
	private JComboBox<String> cb;
	private JTable ta;
	
	public BasicSearchListener(QueryHandler qh, JTextField tf,JTable table, JComboBox<String> combo,JTable ta) {
		this.qh = qh;
		this.tf = tf;
		this.table = table;
		this.cb = combo;
		this.ta = ta;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel(); 
		switch (cb.getSelectedIndex()) {
		case 0:
			Map<String, JSONObject> output = qh.keywordsQuery(tf.getText());
			qh.aggregate(tf.getText(), ta);
			model.setRowCount(0);
			for (String name: output.keySet()){
				try {
					model.addRow(new Object[]{name,output.get(name).getString("body")});
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			tf.setText("");
			break;
			
		case 1:
			Map<String, JSONObject> senderoutput = qh.senderQuery(tf.getText());
			model.setRowCount(0);
			for (String name: senderoutput.keySet()){
				try {
					model.addRow(new Object[]{name,senderoutput.get(name).getString("body")});
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			tf.setText("");
			break;
		case 2:
			Map<String, JSONObject> receiveroutput = qh.receiverQuery(tf.getText());
			model.setRowCount(0);
			for (String name: receiveroutput.keySet()){
				try {
					model.addRow(new Object[]{name,receiveroutput.get(name).getString("body")});
				} catch (JSONException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			tf.setText("");
			break;
		default:
			break;
		}
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

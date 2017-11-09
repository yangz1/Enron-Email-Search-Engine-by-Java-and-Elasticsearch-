import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicToolBarUI.DockingListener;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.json.JSONObject;

public class AggregationListener implements MouseListener {
	private QueryHandler qh;
	private JTable table;
	private JTable ta;

	public AggregationListener(QueryHandler qh, JTable table, JTable ta) {
		this.qh = qh;
		this.table = table;
		this.ta = ta;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		DefaultTableModel model = (DefaultTableModel) this.table.getModel();
		DefaultTableModel aggModel = (DefaultTableModel) this.ta.getModel();
		String keyword = (String) aggModel.getValueAt(ta.getSelectedRow(), 0);
		if (keyword.isEmpty()) {
			return;
		}

		Map<String, JSONObject> output = qh.keywordsQuery(keyword);
		
		qh.aggregate(keyword, ta);

		model.setRowCount(0);
		for (String name : output.keySet()) {
			try {
				model.addRow(new Object[] { name, output.get(name).getString("body") });
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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

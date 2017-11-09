import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import org.json.JSONException;
import org.json.JSONObject;

public class ResultListener implements MouseListener {
	public QueryHandler handler;

	public ResultListener(QueryHandler qh) {
		this.handler = qh;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseclicked");
		JTable table = (JTable) e.getSource();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		int row = table.getSelectedRow();
		int column = table.getSelectedColumn();
		String key = (String) model.getValueAt(row, 0);
		
		createInformationFrame(handler.getCurrentMap().get(key));
		
	}
	
	private void createInformationFrame(JSONObject obj) {
		System.out.println("here");
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		try {
			String content = "Subject: "+obj.getString("subject")+"\n"
					+"From: "+obj.getString("xFrom")+"\n"
					+"To: "+obj.getString("xTo")+"\n"
					+"Date: "+obj.getString("date")+"\n"+"\n"
					+"**************Email**************"+"\n"+"\n"
					+obj.getString("body");
			JTextArea body = new JTextArea(content);
			body.setLineWrap(true);
			body.setWrapStyleWord(true);
			JScrollPane panel = new JScrollPane(body);
			
			frame.add(panel);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

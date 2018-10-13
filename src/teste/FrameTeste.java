package teste;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;

public class FrameTeste extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameTeste frame = new FrameTeste();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameTeste() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 455);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		Box horizontalBox = Box.createHorizontalBox();
		panel_1.add(horizontalBox, BorderLayout.NORTH);
		
		JButton btnNewButton_1 = new JButton("New button");
		horizontalBox.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		horizontalBox.add(btnNewButton_2);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		
		JButton btnNewButton_3 = new JButton("New button");
		horizontalBox.add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("New label");
		horizontalBox.add(lblNewLabel);
		

		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("New button");
		panel_2.add(btnNewButton);
	}

}

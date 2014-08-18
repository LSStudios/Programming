package com.pontus.main;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class TileWindow extends JFrame {

	private JPanel contentPane;

	public static final DefaultListModel listModel = new DefaultListModel();

	public static HashMap<String, Tile> tiles = new HashMap<>();
	
	public static JList list = new JList();
	private JButton btnTest;
	private JScrollPane scrollPane;

	
	
	
	
	/**
	 * Create the frame.
	 */
	public TileWindow(final Component frame) {
		setAutoRequestFocus(false);
		setAlwaysOnTop(true);
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("Tiles");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(frame);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 listModel.addElement("Sprite");
				 list.setModel(listModel);
			}
		});
		btnTest.setBounds(345, 242, 89, 23);
		contentPane.add(btnTest);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 325, 254);
		contentPane.add(scrollPane);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
				Tile tile = tiles.get(list.getSelectedValue().toString());
				
				Main.level.selectedTile = tile;
			
			}
		});
		scrollPane.setViewportView(list);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		final JFileChooser chooser = new JFileChooser();

	}
}

package com.pontus.main;

import java.awt.Component;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.EmptyBorder;
import javax.swing.SpinnerNumberModel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

public class NewLevel extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Create the frame.
	 */
	public NewLevel(final Component frame) {
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("New Level");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(frame);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JSpinner width = new JSpinner();
		width.setModel(new SpinnerNumberModel(new Integer(16), new Integer(4), null, new Integer(1)));
		width.setBounds(66, 11, 94, 20);
		contentPane.add(width);
		
		final JSpinner height = new JSpinner();
		height.setModel(new SpinnerNumberModel(new Integer(16), new Integer(4), null, new Integer(1)));
		height.setBounds(66, 42, 94, 20);
		contentPane.add(height);
		
		JLabel lblWidth = new JLabel("Width:");
		lblWidth.setBounds(10, 11, 46, 14);
		contentPane.add(lblWidth);
		
		JLabel lblHeight = new JLabel("Height:");
		lblHeight.setBounds(10, 42, 46, 14);
		contentPane.add(lblHeight);
		
		JLabel lblTileSize = new JLabel("Tile Size:");
		lblTileSize.setBounds(170, 11, 46, 14);
		contentPane.add(lblTileSize);
		
		final JSpinner tileSize = new JSpinner();
		tileSize.setBounds(215, 11, 94, 20);
		contentPane.add(tileSize);
		
		final JFileChooser chooser = new JFileChooser();
		final JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser.setCurrentDirectory(new File("./res"));
			    int returnVal = chooser.showSaveDialog(frame);
			    
			    
			    if(returnVal == JFileChooser.APPROVE_OPTION) {
			       System.out.println("You chose to open this file: " +
			            chooser.getSelectedFile().getAbsolutePath());
			       
			    }
			    
			  
					textField.setText(chooser.getSelectedFile().getAbsolutePath());
		
			    
				
			}
		});
		btnBrowse.setBounds(345, 70, 89, 23);
		contentPane.add(btnBrowse);
		
		JLabel lblPath = new JLabel("Path:");
		lblPath.setBounds(10, 74, 46, 14);
		contentPane.add(lblPath);
		
		JButton btnDone = new JButton("Done!");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String path = textField.getText();
				int w = (int) width.getValue();
				int h = (int) height.getValue();
				int size = (int) tileSize.getValue();

				FileGenerator.generateFile(w, h, size, path);
				Main.level = new Level(path);

			}
		});
		btnDone.setBounds(345, 242, 89, 23);
		contentPane.add(btnDone);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setBounds(345, 208, 89, 23);
		contentPane.add(btnCancel);
		
		textField = new JTextField();
		textField.setBounds(66, 71, 269, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
	
		setVisible(true);

	}
}

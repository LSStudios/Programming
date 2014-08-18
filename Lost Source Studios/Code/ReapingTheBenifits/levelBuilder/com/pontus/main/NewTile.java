package com.pontus.main;

import java.awt.Component;
import java.awt.MenuItem;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

public class NewTile extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField name;

	/**
	 * Create the frame.
	 */
	public NewTile(final Component frame) {
		setResizable(false);
		setType(Type.UTILITY);
		setTitle("New Tile");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(frame);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTileSize = new JLabel("Tile Size:");
		lblTileSize.setBounds(10, 14, 46, 14);
		contentPane.add(lblTileSize);

		final JSpinner tileSize = new JSpinner();
		tileSize.setModel(new SpinnerNumberModel(new Integer(32), new Integer(8), null, new Integer(1)));
		tileSize.setBounds(66, 11, 94, 20);
		contentPane.add(tileSize);

		final JFileChooser chooser = new JFileChooser();
		final JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser.setCurrentDirectory(new File("./res"));
				int returnVal = chooser.showSaveDialog(frame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());

				}

				textField.setText(chooser.getSelectedFile().getAbsolutePath());

			}
		});
		btnBrowse.setBounds(345, 35, 89, 23);
		contentPane.add(btnBrowse);
		final JCheckBox chckbxCollision = new JCheckBox("Collision");
		chckbxCollision.setBounds(6, 60, 97, 23);
		contentPane.add(chckbxCollision);
		JLabel lblPath = new JLabel("Path:");
		lblPath.setBounds(10, 39, 46, 14);
		contentPane.add(lblPath);

		JButton btnDone = new JButton("Done!");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String path = textField.getText();
				int size = (int) tileSize.getValue();
				String n = name.getText();
				boolean collision = chckbxCollision.isSelected();

				TileWindow.tiles.put(n, new Tile(path, collision, n, size));
				TileWindow.listModel.addElement(n);
				TileWindow.list.setModel(TileWindow.listModel);

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
		textField.setBounds(66, 36, 269, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		

		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(170, 14, 46, 14);
		contentPane.add(lblName);

		name = new JTextField();
		name.setBounds(207, 11, 86, 20);
		contentPane.add(name);
		name.setColumns(10);

		setVisible(true);

	}
}

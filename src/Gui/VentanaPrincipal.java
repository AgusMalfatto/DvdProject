package Gui;
import Productos.*;
import java.awt.Point;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import Connection.Connect;

import java.awt.event.MouseAdapter;
public class VentanaPrincipal extends JFrame implements ActionListener, AncestorListener {

	Connect con;

	private int numDelete = -1;
	private JPanel contentPane;
	
	private JTextField txtTitle, txtDirector, txtTime;
	private JTextField txtSearchTitle, txtSearchDirector;	
	private JComboBox cbGender;
	private JButton btnAdd, btnModify, btnDelete, btnReset;
	private JButton btnSearchTitle, btnSearchDirector;
	private JPanel plwest;

	private JCheckBox cbStock;
	private JTabbedPane tabs;
	
	private JPanel tabDvd, tabCd;
	private JComboBox cbComment;
	private JLabel lblNewLabel;
	
	private JPanel plWestCd, plTitleCd, plArtistCd, plSongsCd, plGenderCd, plCommentCd;
	private JButton btnAddCd, btnModifyCd, btnDeleteCd, btnResetCd;
	private JLabel lblTitleCd, lblArtistCd, lblSongsCd, lblGenderCd, lblCommentCd;
	private JTextField txtTitleCd, txtArtistCd, txtSongsCd;
	private JComboBox cbGenderCd, cbCommentCd;
	
	private JCheckBox cbStockCd;
	private JPanel plSearchCd;
	private JButton btnSearchTitleCd, btnSearchArtistCd;
	private JTextField txtSearchTitleCd, txtSearchArtistCd;
	private JLabel lblCanciones;

	private Dvd newDvd;
	private Catalog dbDvd;

	private DefaultTableModel modelDvd;

	private JTable table;
	private JScrollPane scrollPaneDvd;
	private JTable tableDvd;
	private JTextField txtId;
	private JTextField txtIdCd;
	private JTable tableCd;
	private DefaultTableModel modelCd;
	

	public VentanaPrincipal() 
	{		
		con = new Connect();
		try {
			con.setConnection();
		} catch (Exception e1) {
			System.out.println("Could not connect with DB.");
			e1.printStackTrace();
		}
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		dbDvd = new Catalog();
		initWindow();
		initDvd();
		initButtonsDvd();
		initCd();
		initButtonsCd();
		
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("EL MARCIANITO");
	}	

	private void initWindow() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 728);		
		
		tabs = new JTabbedPane(JTabbedPane.TOP);
		tabs.setBounds(12, 0, 988, 691);
		contentPane.add(tabs);
	}

	private void initDvd() 
	{			
		tabDvd = new JPanel();
		tabDvd.setBackground(new Color(153, 193, 241));
		tabs.addTab("DVD", null, tabDvd, null);
		tabDvd.setLayout(null);
		
		lblNewLabel = new JLabel("FILMS");
		lblNewLabel.setFont(new Font("Courier 10 Pitch", Font.BOLD, 30));
		lblNewLabel.setBounds(516, 0, 175, 38);
		tabDvd.add(lblNewLabel);
		
		plwest = new JPanel();
		plwest.setLayout(null);
		plwest.setBackground(new Color(153, 193, 241));
		plwest.setBounds(0, 0, 239, 651);
		tabDvd.add(plwest);
		
		JPanel plTitle = new JPanel();
		plTitle.setLayout(null);
		plTitle.setBackground(new Color(153, 193, 241));
		plTitle.setBounds(12, 37, 217, 68);
		plwest.add(plTitle);
		
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTitle.setBounds(12, 5, 131, 29);
		plTitle.add(lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(12, 40, 183, 25);
		plTitle.add(txtTitle);
		
		JPanel plDirector = new JPanel();
		plDirector.setLayout(null);
		plDirector.setBackground(new Color(153, 193, 241));
		plDirector.setBounds(12, 106, 217, 68);
		plwest.add(plDirector);
		
		JLabel lblDirector = new JLabel("Director");
		lblDirector.setVerticalAlignment(SwingConstants.BOTTOM);
		lblDirector.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDirector.setBounds(12, 5, 131, 29);
		plDirector.add(lblDirector);
		
		txtDirector = new JTextField();
		txtDirector.setColumns(10);
		txtDirector.setBounds(12, 40, 183, 25);
		plDirector.add(txtDirector);
		
		JPanel plTime = new JPanel();
		plTime.setLayout(null);
		plTime.setBackground(new Color(153, 193, 241));
		plTime.setBounds(12, 175, 217, 68);
		plwest.add(plTime);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTime.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTime.setBounds(12, 5, 131, 29);
		plTime.add(lblTime);
		
		txtTime = new JTextField();
		txtTime.setColumns(10);
		txtTime.setBounds(12, 40, 183, 25);
		plTime.add(txtTime);
		
		JPanel plGender = new JPanel();
		plGender.setLayout(null);
		plGender.setBackground(new Color(153, 193, 241));
		plGender.setBounds(12, 247, 217, 74);
		plwest.add(plGender);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setVerticalAlignment(SwingConstants.BOTTOM);
		lblGender.setFont(new Font("Dialog", Font.BOLD, 15));
		lblGender.setBounds(12, 5, 131, 29);
		plGender.add(lblGender);
		
		cbGender = new JComboBox();
		cbGender.setModel(new DefaultComboBoxModel(new String[] {"Acción", "Animado", "Aventura", "Ciencia Ficción", "Comedia", "Documental", "Drama", "Fantasía", "Infantil", "Terror"}));
		cbGender.setSelectedIndex(0);
		cbGender.setBounds(12, 40, 181, 25);
		plGender.add(cbGender);
		
		JPanel plComment = new JPanel();
		plComment.setLayout(null);
		plComment.setBackground(new Color(153, 193, 241));
		plComment.setBounds(12, 321, 217, 74);
		plwest.add(plComment);
		
		JLabel lblComment = new JLabel("Comment");
		lblComment.setVerticalAlignment(SwingConstants.BOTTOM);
		lblComment.setFont(new Font("Dialog", Font.BOLD, 15));
		lblComment.setBounds(12, 5, 131, 29);
		plComment.add(lblComment);
		
		cbComment = new JComboBox();
		cbComment.setModel(new DefaultComboBoxModel(new String[] {"Excelente", "MuyBuena", "Buena", "Regular", "Mala"}));
		cbComment.setSelectedIndex(0);
		cbComment.setBounds(12, 40, 181, 25);
		plComment.add(cbComment);		

		cbStock = new JCheckBox("Stock");
		cbStock.setFont(new Font("Dialog", Font.BOLD, 15));
		cbStock.setBackground(new Color(153, 193, 241));
		cbStock.setBounds(22, 390, 207, 25);
		plwest.add(cbStock);
		
		
		scrollPaneDvd = new JScrollPane();
		scrollPaneDvd.setBounds(246, 50, 725, 507);
		tabDvd.add(scrollPaneDvd);
		tabDvd.addAncestorListener(new AncestorListener() {

			@Override
			public void ancestorAdded(AncestorEvent event) {
				try {
					fillTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

			@Override
			public void ancestorRemoved(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}			
		});
		
		tableDvd = new JTable();
		tableDvd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableDvd.getSelectedRow();
				if(row >= 0)
				{
					txtId.setText(tableDvd.getValueAt(row, 0).toString());
					txtTitle.setText(tableDvd.getValueAt(row, 1).toString());
					txtDirector.setText(tableDvd.getValueAt(row, 2).toString());
					cbGender.setSelectedItem(tableDvd.getValueAt(row, 3).toString());
					txtTime.setText(tableDvd.getValueAt(row, 4).toString());
					cbComment.setSelectedItem(tableDvd.getValueAt(row, 5).toString());
					if(tableDvd.getValueAt(row, 6).toString().equals("1"))
						cbStock.setSelected(true);
					else
						cbStock.setSelected(false);
				}
			}
		});
		modelDvd = new DefaultTableModel();
		tableDvd.setModel(modelDvd);
		
		modelDvd.addColumn("Id");
		modelDvd.addColumn("Title");
		modelDvd.addColumn("Director");
		modelDvd.addColumn("Gender");
		modelDvd.addColumn("Time");
		modelDvd.addColumn("Comment");
		modelDvd.addColumn("Stock");
		
		scrollPaneDvd.setViewportView(tableDvd);

		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modelDvd);
		tableDvd.setRowSorter(sorter);
	}

	private void initButtonsDvd() 
	{
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Dialog", Font.BOLD, 15));
		btnAdd.setBounds(12, 432, 217, 41);
		plwest.add(btnAdd);
		btnAdd.addActionListener(this);
		
		btnModify = new JButton("Modify");
		btnModify.setFont(new Font("Dialog", Font.BOLD, 15));
		btnModify.setBounds(12, 479, 217, 41);
		plwest.add(btnModify);
		btnModify.addActionListener(this);
		
		btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Dialog", Font.BOLD, 15));
		btnDelete.setBounds(12, 527, 217, 41);
		plwest.add(btnDelete);
		btnDelete.addActionListener(this);
		
		btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Dialog", Font.BOLD, 15));
		btnReset.setBounds(12, 573, 217, 41);
		plwest.add(btnReset);
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Dialog", Font.BOLD, 15));
		lblId.setBounds(26, 11, 28, 25);
		plwest.add(lblId);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(62, 15, 143, 20);
		plwest.add(txtId);
		txtId.setColumns(10);
		btnReset.addActionListener(this);
				
		JPanel plSearch = new JPanel();
		plSearch.setLayout(null);
		plSearch.setBackground(new Color(153, 193, 241));
		plSearch.setBounds(238, 568, 750, 86);
		tabDvd.add(plSearch);
		
		btnSearchTitle = new JButton("Search Title");
		btnSearchTitle.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSearchTitle.setBounds(24, 12, 228, 25);
		plSearch.add(btnSearchTitle);
		btnSearchTitle.addActionListener(this);
		
		btnSearchDirector = new JButton("Search Director");
		btnSearchDirector.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSearchDirector.setBounds(24, 53, 228, 25);
		plSearch.add(btnSearchDirector);
		btnSearchDirector.addActionListener(this);
		
		txtSearchTitle = new JTextField();
		txtSearchTitle.setColumns(10);
		txtSearchTitle.setBounds(264, 12, 474, 25);
		plSearch.add(txtSearchTitle);
		
		txtSearchDirector = new JTextField();
		txtSearchDirector.setColumns(10);
		txtSearchDirector.setBounds(264, 53, 474, 25);
		plSearch.add(txtSearchDirector);
	}
	
	private void initCd() 

	{
		tabCd = new JPanel();
		tabCd.setBackground(new Color(153, 193, 241));
		tabs.addTab("CD", null, tabCd, null);
		tabCd.setLayout(null);
		tabCd.addAncestorListener(new AncestorListener() {

			@Override
			public void ancestorAdded(AncestorEvent event) {
				fillTableCd();
				
			}

			@Override
			public void ancestorRemoved(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		plWestCd = new JPanel();
		plWestCd.setLayout(null);
		plWestCd.setBackground(new Color(153, 193, 241));
		plWestCd.setBounds(0, 0, 239, 651);
		tabCd.add(plWestCd);
		
		plTitleCd = new JPanel();
		plTitleCd.setLayout(null);
		plTitleCd.setBackground(new Color(153, 193, 241));
		plTitleCd.setBounds(12, 37, 217, 68);
		plWestCd.add(plTitleCd);
		
		lblTitleCd = new JLabel("Title");
		lblTitleCd.setVerticalAlignment(SwingConstants.BOTTOM);
		lblTitleCd.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTitleCd.setBounds(12, 5, 131, 29);
		plTitleCd.add(lblTitleCd);
		
		txtTitleCd = new JTextField();
		txtTitleCd.setColumns(10);
		txtTitleCd.setBounds(12, 40, 183, 25);
		plTitleCd.add(txtTitleCd);
		
		plArtistCd = new JPanel();
		plArtistCd.setLayout(null);
		plArtistCd.setBackground(new Color(153, 193, 241));
		plArtistCd.setBounds(12, 106, 217, 68);
		plWestCd.add(plArtistCd);
		
		lblArtistCd = new JLabel("Artist");
		lblArtistCd.setVerticalAlignment(SwingConstants.BOTTOM);
		lblArtistCd.setFont(new Font("Dialog", Font.BOLD, 15));
		lblArtistCd.setBounds(12, 5, 131, 29);
		plArtistCd.add(lblArtistCd);
		
		txtArtistCd = new JTextField();
		txtArtistCd.setColumns(10);
		txtArtistCd.setBounds(12, 40, 183, 25);
		plArtistCd.add(txtArtistCd);
		
		plSongsCd = new JPanel();
		plSongsCd.setLayout(null);
		plSongsCd.setBackground(new Color(153, 193, 241));
		plSongsCd.setBounds(12, 175, 217, 69);
		plWestCd.add(plSongsCd);
		
		lblSongsCd = new JLabel("Songs");
		lblSongsCd.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSongsCd.setFont(new Font("Dialog", Font.BOLD, 15));
		lblSongsCd.setBounds(12, 5, 131, 29);
		plSongsCd.add(lblSongsCd);
		
		txtSongsCd = new JTextField();
		txtSongsCd.setColumns(10);
		txtSongsCd.setBounds(12, 40, 183, 25);
		plSongsCd.add(txtSongsCd);
		
		plGenderCd = new JPanel();
		plGenderCd.setLayout(null);
		plGenderCd.setBackground(new Color(153, 193, 241));
		plGenderCd.setBounds(12, 247, 217, 69);
		plWestCd.add(plGenderCd);
		
		lblGenderCd = new JLabel("Gender");
		lblGenderCd.setVerticalAlignment(SwingConstants.BOTTOM);
		lblGenderCd.setFont(new Font("Dialog", Font.BOLD, 15));
		lblGenderCd.setBounds(12, 5, 131, 29);
		plGenderCd.add(lblGenderCd);
		
		cbGenderCd = new JComboBox();
		cbGenderCd.setModel(new DefaultComboBoxModel(new String[] {"Blues", "Clásica", "Hip Hop", "Metal", "Ópera", "Pop", "Reggaetón", "Rock and Roll", "Salsa"}));
		cbGenderCd.setBounds(12, 40, 181, 25);
		plGenderCd.add(cbGenderCd);
		
		plCommentCd = new JPanel();
		plCommentCd.setLayout(null);
		plCommentCd.setBackground(new Color(153, 193, 241));
		plCommentCd.setBounds(12, 321, 217, 69);
		plWestCd.add(plCommentCd);
		
		lblCommentCd = new JLabel("Comment");
		lblCommentCd.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCommentCd.setFont(new Font("Dialog", Font.BOLD, 15));
		lblCommentCd.setBounds(12, 5, 131, 29);
		plCommentCd.add(lblCommentCd);
		
		cbCommentCd = new JComboBox();
		cbCommentCd.setModel(new DefaultComboBoxModel(new String[] {"Excelente", "MuyBuena", "Buena", "Regular", "Mala"}));
		cbCommentCd.setBounds(12, 40, 181, 25);
		plCommentCd.add(cbCommentCd);
		
		cbStockCd = new JCheckBox("Stock");
		cbStockCd.setFont(new Font("Dialog", Font.BOLD, 15));
		cbStockCd.setBackground(new Color(153, 193, 241));
		cbStockCd.setBounds(22, 390, 207, 25);
		plWestCd.add(cbStockCd);		
	}
	
	private void initButtonsCd() 
	{
		btnAddCd = new JButton("Add");
		btnAddCd.setFont(new Font("Dialog", Font.BOLD, 15));
		btnAddCd.setBounds(12, 432, 217, 41);
		plWestCd.add(btnAddCd);
		btnAddCd.addActionListener(this);
		
		btnModifyCd = new JButton("Modify");
		btnModifyCd.setFont(new Font("Dialog", Font.BOLD, 15));
		btnModifyCd.setBounds(12, 479, 217, 41);
		plWestCd.add(btnModifyCd);
		btnModifyCd.addActionListener(this);
		
		btnDeleteCd = new JButton("Delete");
		btnDeleteCd.setFont(new Font("Dialog", Font.BOLD, 15));
		btnDeleteCd.setBounds(12, 527, 217, 41);
		plWestCd.add(btnDeleteCd);
		btnDeleteCd.addActionListener(this);
		
		btnResetCd = new JButton("Reset");
		btnResetCd.setFont(new Font("Dialog", Font.BOLD, 15));
		btnResetCd.setBounds(12, 573, 217, 41);
		plWestCd.add(btnResetCd);
		btnResetCd.addActionListener(this);
		
		JLabel lblIdCd = new JLabel(" ID");
		lblIdCd.setFont(new Font("Dialog", Font.BOLD, 15));
		lblIdCd.setBounds(22, 11, 28, 25);
		plWestCd.add(lblIdCd);
		
		txtIdCd = new JTextField();
		txtIdCd.setEditable(false);
		txtIdCd.setColumns(10);
		txtIdCd.setBounds(62, 15, 143, 20);
		plWestCd.add(txtIdCd);
				
		plSearchCd = new JPanel();
		plSearchCd.setLayout(null);
		plSearchCd.setBackground(new Color(153, 193, 241));
		plSearchCd.setBounds(238, 568, 750, 86);
		tabCd.add(plSearchCd);
		
		btnSearchTitleCd = new JButton("Search Title");
		btnSearchTitleCd.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSearchTitleCd.setBounds(24, 12, 228, 25);
		plSearchCd.add(btnSearchTitleCd);
		btnSearchTitleCd.addActionListener(this);
		
		btnSearchArtistCd = new JButton("Search Artist");
		btnSearchArtistCd.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSearchArtistCd.setBounds(24, 53, 228, 25);
		plSearchCd.add(btnSearchArtistCd);
		btnSearchArtistCd.addActionListener(this);
		
		txtSearchTitleCd = new JTextField();
		txtSearchTitleCd.setColumns(10);
		txtSearchTitleCd.setBounds(264, 12, 474, 25);
		plSearchCd.add(txtSearchTitleCd);
		
		txtSearchArtistCd = new JTextField();
		txtSearchArtistCd.setColumns(10);
		txtSearchArtistCd.setBounds(264, 53, 474, 25);
		plSearchCd.add(txtSearchArtistCd);
		
		lblCanciones = new JLabel("SONGS");
		lblCanciones.setFont(new Font("Courier 10 Pitch", Font.BOLD, 30));
		lblCanciones.setBounds(516, 0, 175, 38);
		tabCd.add(lblCanciones);
		
		JScrollPane scrollPaneCd = new JScrollPane();
		scrollPaneCd.setBounds(246, 50, 725, 507);
		tabCd.add(scrollPaneCd);
		
		modelCd = new DefaultTableModel();
		tableCd = new JTable(modelCd);
		tableCd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tableCd.getSelectedRow();
				if(row >= 0)
				{
					txtIdCd.setText(tableCd.getValueAt(row, 0).toString());
					txtTitleCd.setText(tableCd.getValueAt(row, 1).toString());
					txtArtistCd.setText(tableCd.getValueAt(row, 2).toString());
					txtSongsCd.setText(tableCd.getValueAt(row, 3).toString());
					cbGenderCd.setSelectedItem(tableCd.getValueAt(row, 4).toString());
					cbCommentCd.setSelectedItem(tableCd.getValueAt(row, 5).toString());
					if(tableCd.getValueAt(row, 6).toString().equals("1"))
						cbStockCd.setSelected(true);
					else
						cbStockCd.setSelected(false);
				}
			}
		});
		
		modelCd.addColumn("Id");
		modelCd.addColumn("Title");
		modelCd.addColumn("Artist");
		modelCd.addColumn("Songs");
		modelCd.addColumn("Gender");
		modelCd.addColumn("Comment");
		modelCd.addColumn("Stock");
		scrollPaneCd.setViewportView(tableCd);
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(modelCd);
		tableCd.setRowSorter(sorter);
	}

	
	

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		switch(tabs.getSelectedIndex())
		{
			case 0:
				
				Dvd dvd = getDvd();				
				
				if(!dvd.getTitle().equals("") && !dvd.getDirector().equals("") && !dvd.getTime().equals("")){		
								
					if(e.getSource() == btnAdd)	{
						dbDvd.setIntoDvd(dvd);
						try {
							fillTable();
							
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
					else if(e.getSource() == btnModify){
						int id = Integer.parseInt(txtId.getText());								
						modifyDvd(dvd, id);
					}
					
					else if(e.getSource() == btnDelete){	
						int id = Integer.parseInt(txtId.getText());		
						dbDvd.deleteFilm("dvd", id);
						setVoidDvd();
						try {
							fillTable();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}					
					}
				}
				
				
				
				if(e.getSource() == btnReset) {
					setVoidDvd();
				}

				else if(e.getSource() == btnSearchTitle) {
					String searchTitle = txtSearchTitle.getText();
					if(!searchTitle.equals("")) {
						searchTitleDvd("dvd", searchTitle);
					}
				}

				else if(e.getSource() == btnSearchDirector) {
					String searchDirector = txtSearchDirector.getText();
					if(!searchDirector.equals("")) {
						searchDirectorDvd("dvd", searchDirector);
					}
				}
				break;
			case 1:
				Cd newCd = getCd();
				if(!newCd.getTitle().equals("") && !newCd.getArtist().equals("") && !newCd.getSongs().equals(""))
				{
					
				    if(e.getSource() == btnAddCd)
				    {
				        addCd(newCd);
				    }
				    else if(e.getSource() == btnModifyCd)
				    {
						int idCd = Integer.parseInt(txtIdCd.getText());
				       try {
						modifyCd(newCd, idCd);
						fillTableCd();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    }
				    else if(e.getSource() == btnDeleteCd)
				    {
						int idCd = Integer.parseInt(txtIdCd.getText());
				        dbDvd.deleteFilm("cd", idCd);
						voidCd();
						fillTableCd();	
				    }
				}
				if(e.getSource() == btnResetCd) {
					voidCd();
				}
				else if(e.getSource() == btnSearchTitleCd) {
					String searchTitle = txtSearchTitleCd.getText();
					searchTitleCd("cd", searchTitle);
				}
				else if(e.getSource() == btnSearchArtistCd) {
					String searchArtist = txtSearchArtistCd.getText();
					searchArtistCd("cd", searchArtist);
				}
		}
				
	}
	
	protected void orderByCd(String column) {
		ResultSet resul = con.getDataTableOrderBy("cd", column);
		CleanTable(modelCd);
		fillTableCd(resul);
	}
	
	private void searchArtistCd(String table, String searchArtist) {
		ResultSet result = con.searchCreator(table, "Artist", searchArtist);
		CleanTable(modelCd);
		fillTableCd(result);
	}

	private void searchTitleCd(String table, String searchTitle) {
		ResultSet resul = con.searchTitle(table, searchTitle);
		CleanTable(modelCd);
		fillTableCd(resul);
	}

	private void fillTableCd(ResultSet result) {
		try{
			while(result.next()){
				Object[] row = new Object[7];
				
				row[0] = result.getString("Id");
				row[1] = result.getString("Title");
				row[2] = result.getString("Artist");
				row[3] = result.getString("Gender");
				row[4] = result.getString("Songs");
				row[5] = result.getString("Comment");
				row[6] = result.getString("Stock");
				
				modelCd.addRow(row);
			}		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchDirectorDvd(String string, String searchDirector) {
		ResultSet result = con.searchCreator(string, "Director", searchDirector);
		CleanTable(modelDvd);
		fillTable(result);
	}

	private void fillTable(ResultSet result) {
		try{
			while(result.next()){
				Object[] row = new Object[7];
				
				row[0] = result.getString("Id");
				row[1] = result.getString("Title");
				row[2] = result.getString("Director");
				row[3] = result.getString("Gender");
				row[4] = result.getString("Time");
				row[5] = result.getString("Comment");
				row[6] = result.getString("Stock");
				
				modelDvd.addRow(row);
			}		
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchTitleDvd(String table, String searchTitle) {
		ResultSet result = con.searchTitle(table, searchTitle);
		CleanTable(modelDvd);
		fillTable(result);		
	}

	public void modifyDvd(Dvd dvdAux, int id){
		if(dvdAux != null) {
			try {
				con.modifyDBDvd(dvdAux.getTitle(), dvdAux.getDirector(), dvdAux.getGender(), dvdAux.getTime(), dvdAux.getComment(), dvdAux.getStock(), id);
				CleanTable(modelDvd);
				fillTable();
				JOptionPane.showMessageDialog(null, "Data changed successfully.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void modifyCd(Cd cd, int idCd) throws SQLException {
		if(cd != null) {
			//public void modifyDBDvd(String title, String creator, String genre, String count, String comment, int stock, int id)
			con.modifyDBCd(cd.getTitle(), cd.getArtist(), cd.getGender(), cd.getSongs(), cd.getComment(), cd.getStock(), idCd);
			CleanTable(modelCd);
			fillTableCd();
			JOptionPane.showMessageDialog(null, "Data changed successfully.");
		}
	}

	public void fillTable()throws SQLException
	{
		try {
			con.setConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet data = con.getDataTable("dvd");

		CleanTable(modelDvd);
		try{
			if(data != null){
				while(data.next()){
					Object[] row = new Object[7];
					
					row[0] = data.getString("Id");
					row[1] = data.getString("Title");
					row[2] = data.getString("Director");
					row[3] = data.getString("Gender");
					row[4] = data.getString("Time");
					row[5] = data.getString("Comment");
					row[6] = data.getString("Stock");
					
					modelDvd.addRow(row);
				}
			}
		}catch(SQLException ex){
			System.out.println("SQL is badd!! " + ex.getMessage());
		}	
	}
	
	public void CleanTable(DefaultTableModel model)
	{
		int rows = model.getRowCount();
		if(rows > 0)
		{
			for(int i = 0; i < rows; i++)
			{
				model.removeRow(0);
				
			}			
		}

	}
	
	public void setVoidDvd()
	{
		txtId.setText("");
		txtTitle.setText("");
		txtDirector.setText("");
		txtTime.setText("");
		cbGender.setSelectedIndex(0);
		cbComment.setSelectedIndex(0);
		cbStock.setSelected(false);
		txtSearchTitle.setText("");
		txtSearchDirector.setText("");
		try {
			fillTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Cd getCd()
	{
	    String title = txtTitleCd.getText();
		String artist = txtArtistCd.getText();
		String gender = (String) cbGenderCd.getSelectedItem();
		String songs = txtSongsCd.getText();
		String comment = (String) cbCommentCd.getSelectedItem();
		int stockCd = 0;
		
		if(cbStockCd.isSelected())
			stockCd = 1;
			
		Cd newCd = new Cd(title, gender, comment, stockCd, artist, songs);
		
		return newCd;
	}

	private Dvd getDvd() {
		String title = txtTitle.getText();
		String director = txtDirector.getText();
		String gender = (String) cbGender.getSelectedItem();
		String time = txtTime.getText();
		String comment = (String) cbComment.getSelectedItem();
		int stock = 0;
		
		if(this.cbStock.isSelected())
			stock = 1;
		
		Dvd dvd = new Dvd(title, director, gender, time, comment, stock);

		return dvd;
	}

	public void addCd(Cd newCd) {
	    dbDvd.insertIntoCd(newCd);
	    fillTableCd();

	    voidCd();
	}
	
	private void voidCd() {
		txtIdCd.setText("");
		txtTitleCd.setText("");
		txtArtistCd.setText("");
		txtSongsCd.setText("");
		cbGenderCd.setSelectedIndex(0);
		cbCommentCd.setSelectedIndex(0);
		cbStockCd.setSelected(false);
		txtSearchTitleCd.setText("");
		txtSearchArtistCd.setText("");

		fillTableCd();
		
	}

	public void fillTableCd()
	{
		ResultSet data = con.getDataTable("cd");
		
		CleanTable(modelCd);

		if(data != null){
			try {
				while(data.next()){
					Object[] row = new Object[7];
					row[0] = data.getString("Id");
					row[1] = data.getString("Title");
					row[2] = data.getString("Artist");
					row[3] = data.getString("Songs");
					row[4] = data.getString("Gender");
					row[5] = data.getString("Comment");
					row[6] = data.getString("Stock");
					
					modelCd.addRow(row);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
		
		
	}

	@Override
	public void ancestorAdded(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorRemoved(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ancestorMoved(AncestorEvent event) {
		// TODO Auto-generated method stub
		
	}
}

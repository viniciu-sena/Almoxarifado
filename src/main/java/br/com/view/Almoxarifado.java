package br.com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import br.com.dao.GenericDao;
import br.com.model.Pessoa;
import br.com.model.Produto;
import br.com.model.Usuario;
import br.com.model.table.ProdutoTable;
import java.awt.Toolkit;

public class Almoxarifado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableProduto;
	private JButton btnEmprestar;

	public Almoxarifado(Pessoa pessoa) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Almoxarifado.class.getResource("/imagens/weg-logo.png")));

		setTitle("WEG SA");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 488);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 81, 634, 378);
		contentPane.add(scrollPane);

		// TABLE
		tableProduto = new JTable();
		ProdutoTable produtoTable = new ProdutoTable();
		scrollPane.setViewportView(tableProduto);
		tableProduto.setModel(produtoTable);

		btnEmprestar = new JButton("Alugar");
		btnEmprestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				GenericDao genericDao = new GenericDao();
				Produto produto = new Produto();
				@SuppressWarnings("unchecked")
				List<Produto> produtos = (List<Produto>) genericDao.select(produto);

				produto = produtos.get(tableProduto.getSelectedRow());
				produto.setEmprestado(true);
				genericDao.update(produto);

			}
		});
		btnEmprestar.setForeground(Color.WHITE);
		btnEmprestar.setBackground(Color.BLACK);
		btnEmprestar.setFont(new Font("3ds", Font.BOLD, 15));
		btnEmprestar.setBounds(10, 47, 114, 23);
		contentPane.add(btnEmprestar);

	}
}

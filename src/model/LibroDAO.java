
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LibroDAO {

	public int countdoRetrieveAll(){
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT COUNT(*) FROM libro");
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Libro> doRetrieveAll(int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT isbn, tipo, anno_pubblicazione, numero_pagine,prezzo,numero_disponibili,descrizione,autore,titolo,copertina FROM libro  LIMIT ?, ?");
			ps.setInt(1, offset);
			ps.setInt(2, limit);
			List<Libro> libri = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Libro p = new Libro();
				p.setIsbn(rs.getString(1));
				p.setTipo(rs.getString(2));
				p.setAnno_pubblicazione(rs.getInt(3));
				p.setNumero_pagine(rs.getInt(4));
				p.setPrezzo(rs.getInt(5));
				p.setNumero_disponibili(rs.getInt(6));
				p.setDescrizione(rs.getString(7));
				p.setAutore(rs.getString(8));
				p.setTitolo(rs.getString(9));
				p.setPath(rs.getString(10));
				p.setCategorie(getCategorie(con,p.getIsbn()));
				libri.add(p);
			}
			return libri;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public Libro doRetrieveByIsbn(String isbn) {
		if(isbn.indexOf("-")<0){
			String a = isbn.substring(0,3);
			String b = isbn.substring(3);
			isbn = a+"-"+ b;
		}
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT isbn, tipo, anno_pubblicazione, numero_pagine,prezzo,numero_disponibili,descrizione,autore,titolo,copertina,acquisti FROM libro WHERE isbn=?");
			ps.setString(1, isbn);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Libro p = new Libro();
				p.setIsbn(rs.getString(1));
				p.setTipo(rs.getString(2));
				p.setAnno_pubblicazione(rs.getInt(3));
				p.setNumero_pagine(rs.getInt(4));
				p.setPrezzo(rs.getInt(5));
				p.setNumero_disponibili(rs.getInt(6));
				p.setDescrizione(rs.getString(7));
				p.setAutore(rs.getString(8));
				p.setTitolo(rs.getString(9));
				p.setPath(rs.getString(10));
				p.setAcquisti(rs.getInt(11));
				p.setCategorie(getCategorie(con,p.getIsbn()));
				return p;
			}
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Libro> doRetrieveByCategoria(int categoria, int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT libro.isbn, tipo, anno_pubblicazione, numero_pagine,prezzo,numero_disponibili,descrizione,autore,titolo,copertina FROM libro LEFT JOIN libro_categoria ON libro.isbn=libro_categoria.isbn WHERE id=? LIMIT ?, ?");
			ps.setInt(1, categoria);
			ps.setInt(2, offset);
			ps.setInt(3, limit);
			ArrayList<Libro> libri = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Libro p = new Libro();
				p.setIsbn(rs.getString(1));
				p.setTipo(rs.getString(2));
				p.setAnno_pubblicazione(rs.getInt(3));
				p.setNumero_pagine(rs.getInt(4));
				p.setPrezzo(rs.getInt(5));
				p.setNumero_disponibili(rs.getInt(6));
				p.setDescrizione(rs.getString(7));
				p.setAutore(rs.getString(8));
				p.setTitolo(rs.getString(9));
				p.setPath(rs.getString(10));
				p.setCategorie(getCategorie(con,p.getIsbn()));
				libri.add(p);
			}
			return libri;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Libro> doRetrieveByNomeOrDescrizione(String against, int offset, int limit) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement(
					"SELECT isbn, tipo, anno_pubblicazione, numero_pagine,prezzo,numero_disponibili,descrizione,autore,titolo,copertina FROM libro WHERE MATCH(titolo, descrizione) AGAINST(?) LIMIT ?,?");
			ps.setString(1,against);
			ps.setInt(2,offset);
			ps.setInt(3,limit);
			ArrayList<Libro> libri = new ArrayList<>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Libro p = new Libro();
				p.setIsbn(rs.getString(1));
				p.setTipo(rs.getString(2));
				p.setAnno_pubblicazione(rs.getInt(3));
				p.setNumero_pagine(rs.getInt(4));
				p.setPrezzo(rs.getInt(5));
				p.setNumero_disponibili(rs.getInt(6));
				p.setDescrizione(rs.getString(7));
				p.setAutore(rs.getString(8));
				p.setTitolo(rs.getString(9));
				p.setPath(rs.getString(10));
				p.setCategorie(getCategorie(con,p.getIsbn()));
				libri.add(p);
			}
			return libri;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int countByCategoria(int categoria) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con
					.prepareStatement("SELECT COUNT(*) FROM libro LEFT JOIN libro_categoria ON libro.ISBN=libro_categoria.ISBN WHERE libro_categoria.id=?");
			ps.setInt(1, categoria);
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doSave(Libro libro) {

		try (Connection con = ConPool.getConnection()) {
			if(libro.getTipo().equals("cartaceo")){
			PreparedStatement ps = con.prepareStatement(
					"INSERT INTO libro(tipo,anno_pubblicazione,numero_pagine,prezzo,numero_disponibili,descrizione,autore,titolo,copertina,isbn) VALUES (?,?,?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, libro.getTipo());
			ps.setInt(2, libro.getAnno_pubblicazione());
			ps.setInt(3, libro.getNumero_pagine());
			ps.setInt(4, libro.getPrezzo());
			ps.setInt(5, libro.getNumero_disponibili());
			ps.setString(6, libro.getDescrizione());
			ps.setString(7, libro.getAutore());
			ps.setString(8, libro.getTitolo());
			ps.setString(9, libro.getPath());
			ps.setString(10, libro.getIsbn());
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("INSERT error.");
			}
		}else{
				PreparedStatement ps = con.prepareStatement(
						"INSERT INTO libro(tipo,anno_pubblicazione,numero_pagine,prezzo,descrizione,autore,titolo,copertina,isbn) VALUES (?,?,?,?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, libro.getTipo());
				ps.setInt(2, libro.getAnno_pubblicazione());
				ps.setInt(3, libro.getNumero_pagine());
				ps.setInt(4, libro.getPrezzo());
				ps.setString(5, libro.getDescrizione());
				ps.setString(6, libro.getAutore());
				ps.setString(7, libro.getTitolo());
				ps.setString(8, libro.getPath());
				ps.setString(9, libro.getIsbn());
				if (ps.executeUpdate() != 1) {
					throw new RuntimeException("INSERT error.");
				}
			}

			PreparedStatement psCa = con
					.prepareStatement("INSERT INTO libro_categoria (isbn, id) VALUES (?, ?)");
			for (Categoria c : libro.getCategorie()) {
				psCa.setString(1, libro.getIsbn());
				psCa.setInt(2, c.getId());
				psCa.addBatch();
			}
			psCa.executeBatch();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void doUpdate(Libro libro) {
		try (Connection con = ConPool.getConnection()) {
			if(libro.getTipo().equals("cartaceo")){
				PreparedStatement ps = con.prepareStatement(
						"UPDATE  libro SET tipo=?, anno_pubblicazione=?,numero_pagine=?,prezzo=?,numero_disponibili=?,descrizione=?,autore=?,titolo=?,copertina=?,acquisti=? WHERE ISBN=?",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, libro.getTipo());
				ps.setInt(2, libro.getAnno_pubblicazione());
				ps.setInt(3, libro.getNumero_pagine());
				ps.setInt(4, libro.getPrezzo());
				ps.setInt(5, libro.getNumero_disponibili());
				ps.setString(6, libro.getDescrizione());
				ps.setString(7, libro.getAutore());
				ps.setString(8, libro.getTitolo());
				ps.setString(9, libro.getPath());
				ps.setInt(10,libro.getAcquisti());
				ps.setString(11, libro.getIsbn());
				if (ps.executeUpdate() != 1) {
					throw new RuntimeException("INSERT error.");
				}
			}else{
				PreparedStatement ps = con.prepareStatement(
						"UPDATE  libro SET tipo=?, anno_pubblicazione=?,numero_pagine=?,prezzo=?,descrizione=?,autore=?,titolo=?,copertina=? WHERE ISBN=?",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, libro.getTipo());
				ps.setInt(2, libro.getAnno_pubblicazione());
				ps.setInt(3, libro.getNumero_pagine());
				ps.setInt(4, libro.getPrezzo());
				ps.setString(5, libro.getDescrizione());
				ps.setString(6, libro.getAutore());
				ps.setString(7, libro.getTitolo());
				ps.setString(8, libro.getPath());
				ps.setString(9, libro.getIsbn());
				if (ps.executeUpdate() != 1) {
					throw new RuntimeException("INSERT error.");
				}
			}

			PreparedStatement psCa2 = con
					.prepareStatement("DELETE FROM libro_categoria WHERE isbn=?");
			psCa2.setString(1, libro.getIsbn());
			psCa2.executeUpdate();

			PreparedStatement psCa = con
					.prepareStatement("INSERT INTO libro_categoria (isbn, id) VALUES (?, ?)");
			for (Categoria c : libro.getCategorie()) {
				psCa.setString(1, libro.getIsbn());
				psCa.setInt(2, c.getId());
				psCa.addBatch();
			}
			psCa.executeBatch();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void doDelete(String isbn) {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("DELETE FROM libro WHERE isbn=?");
			ps.setString(1, isbn);
			if (ps.executeUpdate() != 1) {
				throw new RuntimeException("DELETE error.");
			}
			PreparedStatement psCa = con.prepareStatement("DELETE FROM libro_categoria WHERE isbn=?");
			psCa.setString(1, isbn);
			psCa.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static ArrayList<Categoria> getCategorie(Connection con, String isbn) throws SQLException {
		PreparedStatement ps = con.prepareStatement(
				"SELECT categoria.id, nome, descrizione FROM categoria,libro_categoria where libro_categoria.id=categoria.id and isbn=?");
		ps.setString(1, isbn);
		ArrayList<Categoria> categorie = new ArrayList<>();
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Categoria c = new Categoria();
			c.setId(rs.getInt(1));
			c.setNome(rs.getString(2));
			c.setDescrizione(rs.getString(3));
			categorie.add(c);
		}
		return categorie;
	}

	public ArrayList<Libro> getListOrderBook() {
		try (Connection con = ConPool.getConnection()) {
			PreparedStatement ps = con.prepareStatement("SELECT isbn, tipo, anno_pubblicazione, numero_pagine,prezzo,numero_disponibili,descrizione,autore,titolo,copertina FROM libro where tipo=\"cartaceo\" order by acquisti desc LIMIT 0, 15");
			ArrayList<Libro> libri = new ArrayList<Libro>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Libro p = new Libro();
				p.setIsbn(rs.getString(1));
				p.setTipo(rs.getString(2));
				p.setAnno_pubblicazione(rs.getInt(3));
				p.setNumero_pagine(rs.getInt(4));
				p.setPrezzo(rs.getInt(5));
				p.setNumero_disponibili(rs.getInt(6));
				p.setDescrizione(rs.getString(7));
				p.setAutore(rs.getString(8));
				p.setTitolo(rs.getString(9));
				p.setPath(rs.getString(10));
				libri.add(p);
			}
			return libri;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

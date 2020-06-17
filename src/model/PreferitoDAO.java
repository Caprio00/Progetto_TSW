package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreferitoDAO {

    public List<Libro> doRetrieveByUserId(Utente y) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT libro.isbn, tipo, anno_pubblicazione, numero_pagine,prezzo,numero_disponibili,descrizione,autore,titolo,copertina FROM libro,libro_preferito,utente WHERE libro.isbn=libro_preferito.isbn and utente.id=libro_preferito.id and utente.id=?");
            ps.setInt(1,y.getId());
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

    public Libro doRetrieveByUserIdandIsbn(Utente y, Libro i) {
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "SELECT libro.isbn, tipo, anno_pubblicazione, numero_pagine,prezzo,numero_disponibili,descrizione,autore,titolo,copertina FROM libro,libro_preferito,utente WHERE libro.isbn=libro_preferito.isbn and utente.id=libro_preferito.id and utente.id=? and libro.isbn=?");
            ps.setInt(1,y.getId());
            ps.setString(2,i.getIsbn());
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
                return p;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(Preferito p){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO libro_preferito (ISBN,id) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,p.getLibro());
            ps.setInt(2,p.getUtente());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
    } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void remove(Preferito p){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM libro_preferito WHERE isbn=? and id=?;",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,p.getLibro());
            ps.setInt(2,p.getUtente());
            if (ps.executeUpdate() != 1) {
                throw new RuntimeException("INSERT error.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Categoria> getCategorie(Connection con, String isbn) throws SQLException {
        PreparedStatement ps = con.prepareStatement(
                "SELECT categoria.id, nome, descrizione FROM categoria LEFT JOIN libro_categoria ON libro_categoria.id=categoria.id WHERE isbn=?");
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
}

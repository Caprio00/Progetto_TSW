package model;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import controller.MyServletException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;

public class OrdiniDAO {

    public void doSave(Ordine ordini, Utente utente) throws MyServletException {
        try (Connection con = ConPool.getConnection()) {
            String sql = "Insert into ordini (oradiordine,id_utente,isbn,tipo,anno_pubblicazione,numero_pagine,prezzo,descrizione,autore,titolo,copertina,quantita,totale) values";
            for (int i = 0; i < ordini.getLibri().size(); i++) {
                Libro l = ordini.getLibri().get(i);
                sql = sql + "(\"" + ordini.getOraordine() + "\"," + utente.getId() + ",\"" + l.getIsbn() + "\",\"" + l.getTipo() + "\"," + l.getAnno_pubblicazione() + "," + l.getNumero_pagine() + "," + l.getPrezzo() + ",\"" + l.getDescrizione() + "\",\"" + l.getAutore() + "\",\"" + l.getTitolo() + "\",\"" + l.getPath() + "\"," + ordini.getQuantita() + "," + ordini.getTotale() + ")";
                if (i != ordini.getLibri().size() - 1) {
                    sql = sql + ",";
                }
            }
            sql = sql + ";";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Ordine> doRetrieveByUserId(int idutente) {
        ArrayList<Ordine> o = new ArrayList<Ordine>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT oradiordine,quantita,totale,isbn, tipo, anno_pubblicazione,numero_pagine,prezzo,descrizione,autore,titolo,copertina FROM ordini WHERE id_utente=?  order by oradiordine");
            ps.setInt(1, idutente);
            ResultSet rs = ps.executeQuery();
            String next = "0", prec = "0";
            int i = 0;
            Ordine temp = null;
            if(rs.next() == false){return null;}
            while (rs.next()) {
                next = rs.getString(1);
                if (prec.equals(next)) {
                    Libro ltemp = new Libro();
                    ltemp.setIsbn(rs.getString(4));
                    ltemp.setTipo(rs.getString(5));
                    ltemp.setAnno_pubblicazione(rs.getInt(6));
                    ltemp.setNumero_pagine(rs.getInt(7));
                    ltemp.setPrezzo(rs.getInt(8));
                    ltemp.setDescrizione(rs.getString(9));
                    ltemp.setAutore(rs.getString(10));
                    ltemp.setTitolo(rs.getString(11));
                    ltemp.setPath(rs.getString(12));
                    temp.addLibro(ltemp);
                } else {
                    if(i==0) {i++;}else{o.add(temp);}
                    prec = next;
                    temp = new Ordine();
                    temp.setOraordine(rs.getString(1));
                    temp.setQuantita(rs.getInt(2));
                    temp.setTotale(rs.getInt(3));
                    Libro ltemp = new Libro();
                    ltemp.setIsbn(rs.getString(4));
                    ltemp.setTipo(rs.getString(5));
                    ltemp.setAnno_pubblicazione(rs.getInt(6));
                    ltemp.setNumero_pagine(rs.getInt(7));
                    ltemp.setPrezzo(rs.getInt(8));
                    ltemp.setDescrizione(rs.getString(9));
                    ltemp.setAutore(rs.getString(10));
                    ltemp.setTitolo(rs.getString(11));
                    ltemp.setPath(rs.getString(12));
                    temp.addLibro(ltemp);
                }
            }
            o.add(temp);
            return o;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

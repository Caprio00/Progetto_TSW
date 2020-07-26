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
            String sql = "Insert into ordini (oradiordine,quantitalibro,id_utente,isbn,tipo,anno_pubblicazione,numero_pagine,prezzo,descrizione,autore,titolo,copertina,quantita,totale) values";
            for (int i = 0; i < ordini.getLibri().size(); i++) {
                Libro l = ordini.getLibri().get(i);
                sql = sql + "(\"" + ordini.getOraordine() + "\","+ l.getQuantitaCarrello() +"," + utente.getId() + ",\"" + l.getIsbn() + "\",\"" + l.getTipo() + "\"," + l.getAnno_pubblicazione() + "," + l.getNumero_pagine() + "," + l.getPrezzo() + ",\"" + l.getDescrizione() + "\",\"" + l.getAutore() + "\",\"" + l.getTitolo() + "\",\"" + l.getPath() + "\"," + ordini.getQuantita() + "," + ordini.getTotale() + ")";
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

    public ArrayList<Ordine> doRetrieveAll() {
        ArrayList<Ordine> o = new ArrayList<Ordine>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT oradiordine,quantita,totale,id_utente,username FROM ordini,utente where ordini.id_utente=utente.id order by oradiordine");
            ResultSet rs = ps.executeQuery();
            String next = "0", prec = "0";
            int i = 0;
            Ordine temp = null;
            while (rs.next()) {
                next = rs.getString(1);
                if (prec.equals(next)) {
                } else {
                    if(i==0) {i++;}else{o.add(temp);}
                    prec = next;
                    temp = new Ordine();
                    temp.setOraordine(rs.getString(1));
                    temp.setQuantita(rs.getInt(2));
                    temp.setTotale(rs.getInt(3));
                    temp.setIdutente(rs.getInt(4));
                    temp.setNomeutente(rs.getString(5));
                }
            }
            if(temp != null) o.add(temp);
            return o;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Ordine> doRetrieveByUserId(int idutente) {
        ArrayList<Ordine> o = new ArrayList<Ordine>();
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT oradiordine,quantita,totale,isbn, tipo, anno_pubblicazione,numero_pagine,prezzo,descrizione,autore,titolo,copertina,quantitalibro FROM ordini WHERE id_utente=?  order by oradiordine");
            ps.setInt(1, idutente);
            ResultSet rs = ps.executeQuery();
            String next = "0", prec = "0";
            int i = 0;
            Ordine temp = null;
            while (rs.next()) {
                next = rs.getString(1);
                if (prec.equals(next)) {
                    Libro ltemp = new Libro();
                    ltemp.setQuantitaCarrello(rs.getInt(13));
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
            if(temp != null) o.add(temp);
            return o;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ordine doRetrievebyUserIdAndOra(String ora,int idutente){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT oradiordine,quantita,totale,isbn, tipo, anno_pubblicazione,numero_pagine,prezzo,descrizione,autore,titolo,copertina,quantitalibro FROM ordini WHERE id_utente=? and oradiordine=?  order by oradiordine");
            ps.setInt(1, idutente);
            ps.setString(2, ora);
            Ordine o = new Ordine();
            ResultSet rs = ps.executeQuery();
            Boolean b = true;
            while (rs.next()){
                if(b){
                    o.setOraordine(rs.getString(1));
                    o.setQuantita(rs.getInt(2));
                    o.setTotale(rs.getInt(3));
                    b=false;
                }
                Libro temp = new Libro();
                temp.setIsbn(rs.getString(4));
                temp.setTipo(rs.getString(5));
                temp.setAnno_pubblicazione(rs.getInt(6));
                temp.setNumero_pagine(rs.getInt(7));
                temp.setPrezzo(rs.getInt(8));
                temp.setDescrizione(rs.getString(9));
                temp.setAutore(rs.getString(10));
                temp.setTitolo(rs.getString(11));
                temp.setPath(rs.getString(12));
                temp.setQuantitaCarrello(rs.getInt(13));
                o.addLibro(temp);
            }
            if(o.getLibri().size() > 0){
                return o;
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean ceckIfExistbyIsbnAndUserID(String isbn,int idutente){
        try (Connection con = ConPool.getConnection()) {
            PreparedStatement ps = con
                    .prepareStatement("SELECT * FROM ordini WHERE isbn=? and id_utente = ?");
            ps.setString(1,isbn);
            ps.setInt(2, idutente);
            ResultSet s = ps.executeQuery();

            if(s.next()){
                return true;
            }
            return  false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

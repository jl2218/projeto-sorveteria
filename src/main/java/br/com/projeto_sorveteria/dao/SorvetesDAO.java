/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_sorveteria.dao;

import br.com.projeto_sorveteria.jdbc.ConnectionFactory;
import br.com.projeto_sorveteria.model.Sorvetes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Lara
 */
public class SorvetesDAO {
    
    private Connection con;
    
    public SorvetesDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void cadastrarSabor(Sorvetes obj){
        try {
            
            String sql = "insert into tb_sabores (sabor, quantidade) values (?,?)";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getSabor());
            stmt.setInt(2, obj.getQuantidade());
            
            stmt.execute();
            stmt.close();;

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            
        }
    }
    
    public List<Sorvetes> listarSabores(){
        try {
            //CRIAR A LISTA
            List<Sorvetes> lista = new ArrayList<>();
            
            //CRIAR O SQL, ORGANIZAR E EXECUTAR
            String sql = "select * from tb_sabores";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Sorvetes obj = new Sorvetes();
                
                obj.setSabor(rs.getString("sabor"));
                obj.setQuantidade(rs.getInt("quantidade"));
                
                lista.add(obj);
            }
            return lista;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro" + erro);
            return null;            
        }
    }
    
}

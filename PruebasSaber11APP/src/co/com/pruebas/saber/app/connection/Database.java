package co.com.pruebas.saber.app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {
    
    public boolean validarUsuario(String username,String password) throws SQLException{
    	System.out.println(username);
    	System.out.println(password);
    Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean result = false;
        try {
            
        connection = getConnection();
       
        
        String sql= "Select * from usuario Where usuario=? AND contraseña=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, username);
         preparedStatement.setString(2, password);
        
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet .next()){
                result=true;            
            }          
    }catch (SQLException ex) {
        Logger.getLogger(Database.class.getName()).log(Level.SEVERE,null,ex);
    }finally{            
            preparedStatement.close();
            connection.close();        
        }
        System.out.println(result);
        return result;
    }

    
    
    private Connection getConnection(){
        System.out.println("Oracle testing");
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("jdbc driver Exception");
            e.printStackTrace();
            return null;
        }
        System.out.println("Oracle registered");
        Connection connection = null;
        
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "PRUEBASSABER11","millonarios10");
            
        } catch (SQLException e) {
             System.out.println("jdbc driver Exception");
            e.printStackTrace();
            return null;
        }
    
        if(connection != null){
            System.out.println("Conexion Exitosa!!!");
            
        }else{
            System.out.println("Conexion Fallida!!!");
        }
        return connection;
    }

}

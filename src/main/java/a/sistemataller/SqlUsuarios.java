
package a.sistemataller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SqlUsuarios extends Conexion{
    
    public boolean registrar(Usuarios usr){
        //Inserción a sql
        PreparedStatement ps = null;
        Connection con = getConexion();
        
        String sql = "INSERT INTO usuarios (usuario, password, correo, telefono) VALUES(?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usr.getUsuario());
            ps.setString(2, usr.getPassword());
            ps.setString(3, usr.getCorreo());
            ps.setString(4, usr.getTelefono());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SqlUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
}

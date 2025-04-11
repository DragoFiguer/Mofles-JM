package Clases;

import a.sistemataller.Conexion;
import a.sistemataller.hash;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class Consultas {

    public void accesoUsuario(String user, String pass) {
    Conexion db = new Conexion();
    try {
        Connection cn = db.getConexion();
        // Consulta con filtro WHERE para obtener sólo el usuario solicitado
        PreparedStatement pst = cn.prepareStatement("SELECT password FROM usuarios WHERE usuario = ?");
        pst.setString(1, user); // Asignar el valor del usuario
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            
            String passCorrecto = rs.getString(1); // Obtener la contraseña desde la base
            
            String passIngreso = hash.sha1(pass); // Cambia a sha1 si usas ese método
            
            
            // Comparar las contraseñas ingresadas con las almacenadas
            if (passIngreso.equals(passCorrecto)) {
                JOptionPane.showMessageDialog(null, "Login correcto, bienvenido " + user);
            } else if(pass.equals(passCorrecto)){
                JOptionPane.showMessageDialog(null, "Login correcto, usando sha1 " + user);
            }else{
                
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
        } else {
            // Si el usuario no existe
            JOptionPane.showMessageDialog(null, "Usuario no encontrado");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}


}

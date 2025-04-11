package a.sistemataller;

public class SistemaTaller {

    public static void main(String[] args){
        /*Conexion bc = new Conexion();
        bc.getConexion();*/
        java.awt.EventQueue.invokeLater(() -> {
            new Inicio().setVisible(true);
        });
    }

}

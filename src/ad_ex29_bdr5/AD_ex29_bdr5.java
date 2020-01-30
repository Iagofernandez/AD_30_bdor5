
package ad_ex29_bdr5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AD_ex29_bdr5 {

     public static Connection conexion = null;
     /*
     desenvolver un proxecto java chmado bdor5 que cree os seguintes metodos traballando cas taboas o exercicio bdorexemploalumnos  

a)metodo insireLinea que insira unha linea de pedido pasandolle como minimo os seguintes datos :
ordnum, linum,item,cantidad,descuento

probar a inserir unha linea de pedido para o pedido de numero de orden 4001 cos seguintes datos:
linum: 48
item : 2004
cantidad: 20
descuento: 10

b)
metodo modificaLinea  que modifique o nome dun cliente pasandolle como minimo o numero do cliente. 
probar a modificar o nome del cliente 5 para que pase a chamarse'Alvaro Luna'

c) metodo borraLinea que pasandolle como minimo o numero de orde dun pedido e un numero de linea, borre dita linea de dito pedido  
probar a borrar a linea de pedido (linum)  48 do pedido cuxo ordnum e igual a 4001
     */
   public static Connection getConexion() throws SQLException{
        String usuario = "hr";
        String password = "hr";
        String host = "localhost"; 
        String puerto = "1521";
        String sid = "orcl";
        String ulrjdbc = "jdbc:oracle:thin:" + usuario + "/" + password + "@" + host + ":" + puerto + ":" + sid;
        
         conexion = DriverManager.getConnection(ulrjdbc);
            return conexion;
        
    }
    public static void closeConexion() throws SQLException {
      conexion.close();
      }
    
    public void insertarLinea() {
       
        try {
            PreparedStatement ps
                    = conexion.prepareStatement("insert into the (select a.pedido from pedido_tab a where a.ordnum = ?)select ?,ref(b),?,? from item_tab b where b.itemnum = ?");

            ps.setInt(1, 4001);
            ps.setInt(2, 48);
            ps.setInt(3, 20);
            ps.setInt(4, 10);
            ps.setInt(5, 2004);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AD_ex29_bdr5.class.getName()).log(Level.SEVERE, null, ex);
        }

}
    public void modificarDatos(){
        try{
            PreparedStatement ps 
                    = conexion.prepareStatement("update cliente_tab set clinomb = ? where clinum = ? ");
            
            ps.setString(1, "Alvaro Peña");
            ps.setInt(2, 5);
            
            ps.executeUpdate();
        }catch(SQLException ex){
            Logger.getLogger(AD_ex29_bdr5.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    
    public void borraLinea(){
        try{
            PreparedStatement ps
                    = conexion.prepareStatement("delete from the (select c.pedido from pedido_tab c where c.ordnum = ?)where linum = ?");
            
            ps.setInt(1, 4001);
            ps.setInt(2, 48);
        }catch(SQLException ex){
            Logger.getLogger(AD_ex29_bdr5.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }

    
    
    
    public static void main(String[] args) {
        AD_ex29_bdr5 obj = new AD_ex29_bdr5();

        try {
            obj.getConexion();

            obj.insertarLinea();
            
            obj.modificarDatos();
            
            obj.borraLinea();

            obj.closeConexion();
        } catch (SQLException ex) {
            Logger.getLogger(AD_ex29_bdr5.class.getName()).log(Level.SEVERE, null, ex);
}
    }
    
}

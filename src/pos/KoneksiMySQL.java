/*
 * Copyright(c) 2010
 * pizaini.wordpress.com
 */

package pos;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Pizaini
 */
public class KoneksiMySQL {
    private static Connection connection;

    public static Connection getConnection() {
        if(connection == null){
            MysqlDataSource dataSource = null;
            try{
                dataSource = new MysqlDataSource();
                dataSource.setUrl("jdbc:mysql://localhost:3306/db_pos");
                dataSource.setUser("root");
                dataSource.setPassword("");
                connection = dataSource.getConnection();
            }catch(SQLException exc){
                JOptionPane.showMessageDialog(null, exc.getMessage(), "Error Koneksi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return connection;
    }

}

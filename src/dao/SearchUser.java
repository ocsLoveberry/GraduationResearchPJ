
package dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SearchUser {
     public boolean searchuser(String SEKI_NO,char[] PASSWORD){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String s_password = new String(PASSWORD);
         // DBManagerのインスタンスを生成
         Accesser manager = new Accesser();

         try {
             // 接続する
            conn = Accesser.getConnection();
            st = conn.createStatement();

            String sql = "SELECT * FROM STUDENT_TBL";
            rs = st.executeQuery(sql);
            //レコードの値を取得
            while (rs.next()) {
            	String LocalSeki_no = rs.getString("SEKI_NO");
            	String LocalPassword = rs.getString("PASSWORD");
            	if(SEKI_NO.equals(LocalSeki_no) && s_password.equals(LocalPassword)) {
            		return true;
            	}
             }
         } catch (SQLException e) {
             System.err.println("Oracleエラーコード:" + e.getErrorCode());
             System.err.println("SQLStateコード:" + e.getSQLState());
             System.err.println("エラーメッセージ:" + e.getMessage());
             e.printStackTrace();

         } finally {
             // 切断処理
             manager.close(conn);
         }
         return false;
     }
 }
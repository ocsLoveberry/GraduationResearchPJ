package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLExecuter {
	//	AESで復号化するためのキー
	static String decryptKey = "loveberry";

	public boolean searchuser(String SEKI_NO, char[] PASSWORD) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		//        passwordをchar[]からStringに変換
		String s_password = new String(PASSWORD);
		//          DBManagerのインスタンスを生成
		Accesser manager = new Accesser();

		try {
			//          	接続する
			conn = Accesser.getRaspConnection();
			st = conn.createStatement();
			String sql = "SELECT SEKI_NO, AES_DECRYPT(UNHEX(PASSWORD), '" + decryptKey + "') AS PASSWORD FROM STUDENT_TBL";
			rs = st.executeQuery(sql);
			//
			//             レコードの値を取得
			while (rs.next()) {
				String LocalSeki_no = rs.getString("SEKI_NO");
				String LocalPassword = rs.getString("PASSWORD");
				if (SEKI_NO.equals(LocalSeki_no) && s_password.equals(LocalPassword)) {
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

	public boolean searchRaspUser(String SEKI_NO, char[] PASSWORD) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		//        passwordをchar[]からStringに変換
		String s_password = new String(PASSWORD);
		//          DBManagerのインスタンスを生成
		Accesser manager = new Accesser();

		try {
			//          	接続する
			conn = Accesser.getRaspConnection();
			st = conn.createStatement();

//			String sql = "SELECT SEKI_NO, AES_DECRYPT(UNHEX(PASSWORD), '" + decryptKey + "') AS PASSWORD FROM OCS_JOHO_TBL";
			String sql = "SELECT SEKI_NO,PASSWORD FROM OCS_JOHO_TBL";

			//			SQL実行
			rs = st.executeQuery(sql);

			//
			//             レコードの値を取得
			while (rs.next()) {
				//				ログイン処理
				String LocalSeki_no = rs.getString("SEKI_NO");
				String LocalPassword = rs.getString("PASSWORD");
				if (SEKI_NO.equals(LocalSeki_no) && s_password.equals(LocalPassword)) {
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

	public List<String[]> searchStuPunch(String seki_no,String formEntryDate, String formClassroom) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		String sql = null;
		List<String[]> resultStuPunch = new ArrayList<>();
		System.out.println("searchStuPunch seki_no:"+seki_no);
		System.out.println("searchStuPunch formEntryDate:"+formEntryDate);
		System.out.println("searchStuPunch formClassroom:"+formClassroom);
//
//		String sql = "SELECT SEKI_NO, ENTRY_DATE, LOBE_ID FROM TIME_TBL WHERE SEKI_NO = " + seki_no;
//		String sql = "SELECT SEKI_NO, ENTRY_DATE, LOBE_ID FROM TIME_TBL WHERE SEKI_NO = " + seki_no  + " AND LOBE_ID = " + formClassroom;
//		String sql = "SELECT SEKI_NO, ENTRY_DATE, LOBE_ID FROM TIME_TBL WHERE SEKI_NO = " + seki_no  + " AND DATE_FORMAT(ENTRY_DATE, '%Y-%m-%d') = " + formDate + " AND LOBE_ID = " + formClassroom;

//		String sql = "SELECT SEKI_NO, ENTRY_DATE, LOBE_ID FROM TIME_TBL WHERE DATE_FORMAT(ENTRY_DATE, '%Y-%m-%d') = " + formEntryDate;
		if(formClassroom.equals("all")) {
			sql = "SELECT * FROM TIME_TBL WHERE  SEKI_NO = " + seki_no  + " AND DATE_FORMAT(ENTRY_DATE, '%Y-%m-%d') = '"+ formEntryDate +"' ";
		}else {
			sql = "SELECT * FROM TIME_TBL WHERE  SEKI_NO = " + seki_no  + " AND DATE_FORMAT(ENTRY_DATE, '%Y-%m-%d') = '"+ formEntryDate +"' AND LOBE_ID = " + formClassroom;
		}

		try {
			conn = Accesser.getRaspConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
			String l_seki_no = rs.getString("SEKI_NO");
			String l_entry_date = rs.getString("ENTRY_DATE");
			String l_lobe_id = rs.getString("LOBE_ID");
			String[] dataLow = {l_seki_no,l_entry_date,l_lobe_id};
			resultStuPunch.add(dataLow);
//			System.out.println("l_sekino:"+l_seki_no+" l_entry_date:"+l_entry_date+" l_lobe_id:"+l_lobe_id);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resultStuPunch;
	}
}

// INSERT INTO STUDENT_TBL (SEKI_NO, PASSWORD) VALUES ('ID',HEX(AES_ENCRYPT('PASS','loveberry')));

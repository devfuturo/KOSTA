import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.my.sql.MyConnection;

public class BatchTest {

    public static void main(String[] args) {
        Connection con = null;
        PreparedStatement pstmt = null;  //resultset은 여기서 필요없음 

        String insertSQL = "INSERT INTO a_tbl VALUES (?)";     //한번 보내고~ 
        try {
            con = MyConnection.getConnection();
            pstmt = con.prepareStatement(insertSQL);
//            for(int i = 100; i<=200; i++) {                  //100번 보내기 
//                pstmt.setInt(1,  i);
//                pstmt.executeUpdate();
//            }
            // 일괄처리로 바꾸기
            for(int i =101; i<=200; i++) {
                pstmt.setInt(1,  i);
                pstmt.addBatch();      //일괄처리위해 메모리에 미리 쌓아둠
            }
            pstmt.executeBatch();         //여기서 모아놓은것 한번에 보냄 , update인 경우 int배열로 반환함 
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            MyConnection.close(pstmt, con);
        }


    }

}

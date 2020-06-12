package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Comment;

public class CommentDao {

	private static final String url = "jdbc:mysql://localhost:3306/BBS?serverTimezone=JST";
	private static final String user = "root";
	private static final String pw = "Paitan45tenTei@*";

	public static ArrayList<Comment> allComment(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pw);

			String sql = "SELECT * FROM Comment";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			ArrayList<Comment> list = new ArrayList<Comment>();

			while( rs.next() ){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String comment = rs.getString("comment");
				String dateTime = rs.getString("datetime");
				Comment result = new Comment(id,name,email,comment,dateTime);
				list.add(result);
			}

			return list;

		}  catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}

			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}

		return null;
	}

	public static void insertComment(Comment comment){
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pw);

			String sql = "insert into Comment(name,email,comment,datetime) values (?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, comment.getName());
			pstmt.setString(2, comment.getEmail());
			pstmt.setString(3, comment.getComment());
			pstmt.setString(4, comment.getdateTime());

			int result = pstmt.executeUpdate();

			System.out.println(result + "件登録しました。");

		}catch (SQLException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			System.out.println("DBに失敗しました。");
			e.printStackTrace();
		}finally {
			try {
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}

			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}

	public static void deleteComment(int id){
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pw);

			String sql = "DELETE FROM Comment WHERE id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, id);

			int result = pstmt.executeUpdate();
			System.out.println(result + "件削除されました。");

		}  catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}

			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}

	public static void updateComment(Comment comment){
		Connection con = null;
		PreparedStatement pstmt = null;

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");

			con = DriverManager.getConnection(url, user, pw);

			String sql = "UPDATE comment SET name = ?, comment = ?,datetime = ? WHERE id = ?";

			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, comment.getName());
			pstmt.setString(2, comment.getComment());
			pstmt.setString(3, comment.getdateTime());
			pstmt.setInt(4, comment.getId());

			int result = pstmt.executeUpdate();
			System.out.println(result + "件更新されました。");

		}  catch (SQLException e){
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("DBアクセスに失敗しました。");
			e.printStackTrace();
		} finally {
			try {
				if( pstmt != null){
					pstmt.close();
				}
			} catch(SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}

			try {
				if( con != null){
					con.close();
				}
			} catch (SQLException e){
				System.out.println("DB切断時にエラーが発生しました。");
				e.printStackTrace();
			}
		}
	}
}


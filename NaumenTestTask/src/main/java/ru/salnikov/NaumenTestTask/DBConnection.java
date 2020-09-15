package ru.salnikov.NaumenTestTask;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DBConnection {
		private static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/DBNaumen";
		private static String USER = "postgres";
		private static String PASS = "admin";		
		public static List<String> selectResult = new ArrayList<>();
		public static Map<Integer, String> headingResult = new HashMap<>();
		public static Map<Integer, String> noteResult = new HashMap<>();
		public static String selected = "";

		public boolean connectJDBC() {
			try {
				Class.forName("org.postgresql.Driver");
				System.out.println("PostgreSQL JDBC Driver найден!");
				return true;
			} catch (ClassNotFoundException e) {
				System.out.println("PostgreSQL JDBC Driver не найден!");
				e.printStackTrace();
				return false;
			}
		}
		
		public boolean selectAll(String query) {
			connectJDBC();
			if (!query.trim().split(" ")[0].toUpperCase().equals("SELECT"))
				return false;
			try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
				System.out.println("PostgreSQL JDBC Driver успешный коннект к базе!");
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				if (!rs.isBeforeFirst()) {
					System.out.println("SELECT ничего не вернул");
					return false;
				}
				while (rs.next()) {
					String tableRow = "";
					if(rs.getString("heading").equals(""))
						tableRow = "<tr>" + 
								"<td onclick=\"showNote(" + rs.getInt("id") + ")\">" + rs.getString("note") + "</td>" + 
								"<td><button onclick=\"deleteNote(" + rs.getInt("id") + ")\">Удалить</button></td>" + 
							"</tr>";
					else
						tableRow = "<tr>" + 
										"<td onclick=\"showNote(" + rs.getInt("id") + ")\">" + rs.getString("heading") + "</td>" +
										"<td><button onclick=\"deleteNote(" + rs.getInt("id") + ")\">Удалить</button></td>" + 
									"</tr>";;
					selectResult.add(tableRow);
				}
				rs.close();

			} catch (SQLException e) {
				System.out.println("PostgreSQL JDBC Driver не удалось подключиться к базе!");
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		public boolean selectForSearch(String query) {
			connectJDBC();
			if (!query.trim().split(" ")[0].toUpperCase().equals("SELECT"))
				return false;
			try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
				System.out.println("PostgreSQL JDBC Driver успешный коннект к базе!");
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				if (!rs.isBeforeFirst()) {
					System.out.println("SELECT ничего не вернул");
					return false;
				}
				while (rs.next()) {
					headingResult.put(rs.getInt("id"), rs.getString("heading"));
					noteResult.put(rs.getInt("id"), rs.getString("note"));
				}
				rs.close();

			} catch (SQLException e) {
				System.out.println("PostgreSQL JDBC Driver не удалось подключиться к базе!");
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		public boolean select(String query) {
			connectJDBC();
			if (!query.trim().split(" ")[0].toUpperCase().equals("SELECT"))
				return false;
			try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
				System.out.println("PostgreSQL JDBC Driver успешный коннект к базе!");
				Statement statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				if (!rs.isBeforeFirst()) {
					System.out.println("SELECT ничего не вернул");
					return false;
				}
				while (rs.next()) {
					selected = "<h1>" + rs.getString("heading") + "</h1><br><p>" + rs.getString("note") + "</p>";
				}
				rs.close();

			} catch (SQLException e) {
				System.out.println("PostgreSQL JDBC Driver не удалось подключиться к базе!");
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		public boolean update(String query) {
			connectJDBC();
			if (!query.trim().split(" ")[0].toUpperCase().equals("UPDATE"))
				return false;
			try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
				System.out.println("PostgreSQL JDBC Driver успешный коннект к базе!");
				Statement statement = connection.createStatement();
				int rs = statement.executeUpdate(query);
				if (rs <= 0) {
					System.out.println("UPDATE ничего не вернул");
					return false;
				}
			} catch (SQLException e) {
				System.out.println("PostgreSQL JDBC Driver не удалось подключиться к базе!");
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		public boolean delete(String query) {
			connectJDBC();
			if (!query.trim().split(" ")[0].toUpperCase().equals("DELETE"))
				return false;
			try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
				System.out.println("PostgreSQL JDBC Driver успешный коннект к базе!");
				Statement statement = connection.createStatement();
				int rs = statement.executeUpdate(query);
				if (rs <= 0) {
					System.out.println("DELETE ничего не вернул");
					return false;
				}
			} catch (SQLException e) {
				System.out.println("PostgreSQL JDBC Driver не удалось подключиться к базе!");
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		public boolean insert(String query) {
			connectJDBC();
			if (!query.trim().split(" ")[0].toUpperCase().equals("INSERT"))
				return false;
			try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)){
				System.out.println("PostgreSQL JDBC Driver успешный коннект к базе!");
				Statement statement = connection.createStatement();
				int rs = statement.executeUpdate(query);
				if (rs <= 0) {
					System.out.println("INSERT ничего не вернул");
					return false;
				}
			} catch (SQLException e) {
				System.out.println("PostgreSQL JDBC Driver не удалось подключиться к базе!");
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		
		public static String getDB_URL() {
			return DB_URL;
		}

		public static void setDB_URL(String dB_URL) {
			DB_URL = dB_URL;
		}

		public static String getUSER() {
			return USER;
		}

		public static void setUSER(String uSER) {
			USER = uSER;
		}

		public static String getPASS() {
			return PASS;
		}

		public static void setPASS(String pASS) {
			PASS = pASS;
		}

}

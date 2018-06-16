import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Exercice 1
		
		try {
			Class.forName( "oracle.jdbc.OracleDriver" );
			// Class.forName("oracle.jdbc.OracleDriver") ;
		} 
		catch ( ClassNotFoundException e ) {
			e.printStackTrace();
		}
		String url = "jdbc:oracle:thin:isep/isep@127.0.0.1:1521:XE";
		String user = "isep";
		String pass = "isep";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection( url, user, pass );
			/* Requests to bdd will be here */
			System.out.println("Bdd Connected");
			displayDepartment(connexion);
		} 
		catch ( SQLException e ) {
			e.printStackTrace();
		} 
		finally {
			if ( connexion != null )
				try {
					connexion.close();
				} catch ( SQLException ignore ) {
					ignore.printStackTrace();
				}
		}
		
		//Exercice 2
		
		/*Scanner sc = new Scanner(System.in);
		System.out.println("Empno :");
		int empno = sc.nextInt();
		System.out.println("newDeptno :");
		int newDeptno = sc.nextInt();
		moveDepartment(empno,newDeptno);*/
		
		//Exercice 3
		
		displayTable("emp");

	}

	public static void displayDepartment(Connection connexion) throws SQLException {
		Statement statement = connexion.createStatement();
		ResultSet resultat = statement.executeQuery( "SELECT deptno, dname, loc FROM dept" );
		while ( resultat.next() ) {
			int deptno = resultat.getInt( "deptno");
			String dname = resultat.getString( "dname" );
			String loc = resultat.getString( "loc" );
			System.out.println("Department " + deptno + " is for " + dname + " and located in " + loc);
		}
		resultat.close();
	}

	public static void moveDepartment(int empno, int newDeptno){
		String url = "jdbc:oracle:thin:isep/isep@127.0.0.1:1521:XE";
		String user = "isep";
		String pass = "isep";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection( url, user, pass );
			PreparedStatement preparedStatement = connexion.prepareStatement("UPDATE emp SET deptno=? WHERE empno=?");
			preparedStatement.setInt( 1, newDeptno );
			preparedStatement.setInt( 2, empno );
			ResultSet results = preparedStatement.executeQuery();
		} 
		catch ( SQLException e ) {
			e.printStackTrace();
		} 
		finally {
			if ( connexion != null )
				try {
					connexion.close();
				} catch ( SQLException ignore ) {
					ignore.printStackTrace();
				}
		}
	}

	public static void displayTable(String tableName){
		String url = "jdbc:oracle:thin:isep/isep@127.0.0.1:1521:XE";
		String user = "isep";
		String pass = "isep";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection( url, user, pass );
			PreparedStatement preparedStatement = connexion.prepareStatement( "SELECT * FROM ?");
			preparedStatement.setString( 1, tableName);
			ResultSet resultat = preparedStatement.executeQuery();
			ResultSetMetaData rsmd = resultat.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			String firstColumnName = rsmd.getColumnName(1);
			for(int i=1;i<=columnsNumber;i++){
				System.out.print(rsmd.getColumnName(i)+" | ");
			}
			System.out.println("");
			while ( resultat.next() ) {
				for(int i=1;i<=columnsNumber;i++){
					String res = resultat.getString(i);
					System.out.print(res+" | ");
				}
				System.out.println("");
			}
			resultat.close();
		} 
		catch ( SQLException e ) {
			e.printStackTrace();
		} 
		finally {
			if ( connexion != null )
				try {
					connexion.close();
				} catch ( SQLException ignore ) {
					ignore.printStackTrace();
				}
		}
	}
	
	public static void displayTableP(String tableName){
		String url = "jdbc:oracle:thin:isep/isep@127.0.0.1:1521:XE";
		String user = "isep";
		String pass = "isep";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection( url, user, pass );
			Statement statement = connexion.createStatement();
			ResultSet resultat = statement.executeQuery( "SELECT * FROM "+tableName );
			ResultSetMetaData rsmd = resultat.getMetaData();
			int columnsNumber = rsmd.getColumnCount();
			String firstColumnName = rsmd.getColumnName(1);
			for(int i=1;i<=columnsNumber;i++){
				System.out.print(rsmd.getColumnName(i)+" | ");
			}
			System.out.println("");
			while ( resultat.next() ) {
				for(int i=1;i<=columnsNumber;i++){
					String res = resultat.getString(i);
					System.out.print(res+" | ");
				}
				System.out.println("");
			}
			resultat.close();
		} 
		catch ( SQLException e ) {
			e.printStackTrace();
		} 
		finally {
			if ( connexion != null )
				try {
					connexion.close();
				} catch ( SQLException ignore ) {
					ignore.printStackTrace();
				}
		}
	}
}

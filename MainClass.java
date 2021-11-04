import java.io.*;
import java.util.*;
import java.sql.*;
public class MainClass {
	public static void main(String[] args) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection con=DriverManager.getConnection("jdbc:sqlite:movies.db");
			if(con!=null) {
				System.out.println("Established");
				Statement stmt=con.createStatement();
				int n=stmt.executeUpdate("create table Movies(movie text,actor text,actress text,director text,year int)");
				PreparedStatement ps=con.prepareStatement("insert into Movies values(?,?,?,?,?)");
				System.out.println("Created");
				Scanner sc=new Scanner(System.in);
				String m,ac,act,dir;
				int y,tot;
				int rec=sc.nextInt();
				for(int i=0;i<rec;i++) {
					System.out.println("Enter movie name");
					m=sc.next();
					System.out.println("Enter Actor name");
					ac=sc.next();
					System.out.println("Enter Actress name");
					act=sc.next();
					System.out.println("Enter Director name");
					dir=sc.next();
					System.out.println("Enter year of release");
					y=sc.nextInt();
					ps.setString(1,m);
					ps.setString(2,ac);
					ps.setString(3,act);
					ps.setString(4, dir);
					ps.setInt(5,y);
					tot=ps.executeUpdate();
					
				}
				System.out.println("MOVIE\tACTOR\tACTRESS\tDIRECTOR\tYEAR OF RELEASE");
				ps=con.prepareStatement("select * from Movies");
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getInt(5));
					
				}
				ps=con.prepareStatement("select * from Movies where actor='mahesh'");
				rs=ps.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getInt(5));
					
				}
				
				
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}

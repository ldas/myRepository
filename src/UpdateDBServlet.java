import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csvreader.CsvReader;

public class UpdateDBServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public UpdateDBServlet() {
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			String filename = (String) getServletContext().getAttribute("fileName");
			out.println((new StringBuilder()).append("FileName : ").append(filename).toString());
			CsvReader counterparties = null;
			if (filename.endsWith(".csv")) {
				counterparties = new CsvReader(filename);
				counterparties.readHeaders();
			}
			if (filename.endsWith(".txt")) {
				Scanner sc = new Scanner(System.in);
				System.out.println("Please enter the delimiter character:");
				char delimiter = sc.next().charAt(0);
				if (sc != null) {
					sc.close();
				}
				counterparties = new CsvReader(new FileReader(filename),delimiter);
				counterparties.readHeaders();
			}
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres",
					"root");
			Statement st = con.createStatement();
			String query;
			while (counterparties.readRecord()) {
				String counterpartyId = counterparties.get("counterparty_id");
				String counterpartyName = counterparties
						.get("counterparty_name");
				String counterpartyType = counterparties
						.get("counterparty_type");
				String counterpartyCompanyNumber = counterparties
						.get("counterparty_company_number");
				String counterpartyBranchName = counterparties
						.get("counterparty-branch-name");
				String counterpartyJurisdiction = counterparties
						.get("counterparty_jurisdiction");
				String leiCode = counterparties.get("lei_code");
				String city = counterparties.get("city");
				String country = counterparties.get("country");
				String address = counterparties.get("address");
				query = "insert into public.cards_counterparty values (";
				query = (new StringBuilder()).append(query)
						.append(counterpartyId).append(", '").toString();
				query = (new StringBuilder()).append(query)
						.append(counterpartyName).append("', '").toString();
				query = (new StringBuilder()).append(query)
						.append(counterpartyType).append("', '").toString();
				query = (new StringBuilder()).append(query)
						.append(counterpartyCompanyNumber).append("', '")
						.toString();
				query = (new StringBuilder()).append(query)
						.append(counterpartyBranchName).append("', '")
						.toString();
				query = (new StringBuilder()).append(query)
						.append(counterpartyJurisdiction).append("', '")
						.toString();
				query = (new StringBuilder()).append(query).append(leiCode)
						.append("', '").toString();
				query = (new StringBuilder()).append(query).append(city)
						.append("', '").toString();
				query = (new StringBuilder()).append(query).append(country)
						.append("', '").toString();
				query = (new StringBuilder()).append(query).append(address)
						.append("')").toString();
				out.println((new StringBuilder()).append("Query : ")
						.append(query).toString());
				st.executeUpdate(query);
			}

			out.println("Data inserted...");
			counterparties.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest httpservletrequest,
			HttpServletResponse httpservletresponse) throws ServletException,
			IOException {
	}
}

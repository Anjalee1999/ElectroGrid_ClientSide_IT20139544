package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Consumption {
	// A common method to connect to the DB
	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/electrogridclient", "root", "");

			// For testing
			System.out.print("Successfully connected");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String readConsumption() {
		String output = "";

		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}

			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Customer ID</th>" + "<th>Present Reading</th><th>Previous Reading</th>"
					+ "<th>Consumption Unit</th>" + "<th>Update</th><th>Remove</th></tr>";

			String query = "select * from consumption";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			// iterate through the rows in the result set
			while (rs.next()) {

				String consumptionID = Integer.toString(rs.getInt("consumptionID"));
				String customerID = rs.getString("customerID");
				String presentReading = rs.getString("presentReading");
				String previousReading = rs.getString("previousReading");
				String consumptionUnit = rs.getString("consumptionUnit");

				// Add into the html table

				output += "<tr><td><input id='hidcustomerIDUpdate' name='hidcustomerIDUpdate' type='hidden' value='"
						+ consumptionID + "'>" + customerID + "</td>";

				output += "<td>" + presentReading+ "</td>";
				output += "<td>" + previousReading + "</td>";
				output += "<td>" + consumptionUnit + "</td>";

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-consumptionID='"
						+ consumptionID + "'>" + "</td></tr>";

			}

			con.close();

			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Consumption Details.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Insert Consumption
	public String insertConsumption(String customerID, String presentReading, String previousReading,
			String consumptionUnit) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement
			String query = " insert into consumption (`consumptionID`,`customerID`,`presentReading`,`previousReading`,`consumptionUnit`)"
					+ " values (?, ?, ?, ?, ?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerID);
			preparedStmt.setString(3, presentReading);
			preparedStmt.setString(4, previousReading);
			preparedStmt.setString(5, consumptionUnit);

			// execute the statement
			preparedStmt.execute();
			con.close();

			// Create JSON Object to show successful msg.
			String newConsumption = readConsumption();
			output = "{\"status\":\"success\", \"data\": \"" + newConsumption + "\"}";
		} catch (Exception e) {
			// Create JSON Object to show Error msg.
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting Customer.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	// Update Customer
	public String updateConsumption(String consumptionID, String customerID, String presentReading, String previousReading,
			String consumptionUnit) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE consumption SET customerID=?,presentReading=?,previousReading=?,consumptionUnit=? WHERE consumptionID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, customerID);
			preparedStmt.setString(2, presentReading);
			preparedStmt.setString(3, previousReading);
			preparedStmt.setString(4, consumptionUnit);
			preparedStmt.setInt(5, Integer.parseInt(consumptionID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON object to show successful msg
			String newConsumption = readConsumption();
			output = "{\"status\":\"success\", \"data\": \"" + newConsumption + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while Updating Consumption Details.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteConsumption(String consumptionID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "DELETE FROM consumption WHERE consumptionID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(consumptionID));
			// execute the statement
			preparedStmt.execute();
			con.close();

			// create JSON Object
			String newConsumption = readConsumption();
			output = "{\"status\":\"success\", \"data\": \"" + newConsumption + "\"}";
		} catch (Exception e) {
			// Create JSON object
			output = "{\"status\":\"error\", \"data\": \"Error while Deleting Consumption.\"}";
			System.err.println(e.getMessage());

		}

		return output;
	}

}

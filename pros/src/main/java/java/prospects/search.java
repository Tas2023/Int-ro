package java.prospects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class search {

    public static void main(String[] args) {
        String dbUrl = "jdbc:mysql://localhost:3307/prospects_db";
        String dbUser = "root";
        String dbPassword = "";
        String searchName = "";

        try {

            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);

            String sql = "SELECT * FROM prospects WHERE company_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, searchName);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Process results
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String companyName = resultSet.getString("company_name");
                String contactEmail = resultSet.getString("contact_email");

                System.out.println("ID du prospect : " + id);
                System.out.println("Nom de l'entreprise : " + companyName);
                System.out.println("E-mail de contact : " + contactEmail);

                System.out.println("------------------------");
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

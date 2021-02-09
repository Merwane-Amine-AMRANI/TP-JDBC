package org.amrani;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommuneDBServiceImpl implements CommuneDBService {

	private Connection connection;

	public CommuneDBServiceImpl(Connection connection) throws SQLException {
		this.connection = connection;
	}

	@Override
	public void writeCommune(Commune commune) {
		String sql = "INSERT INTO communes(codeINSEE, nomCommune, codePostal, libelleAcheminement) VALUES (?,?,?,?) ;";
		try (PreparedStatement prepareStatement = connection.prepareStatement(sql);) {

			prepareStatement.setString(1, commune.getCodeINSEE());
			prepareStatement.setString(2, commune.getNomCommune());
			prepareStatement.setLong(3, commune.getCodePostal());
			prepareStatement.setString(4, commune.getLibelleAcheminement());
			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Commune getCommuneById(String id) {
		String sql = "SELECT codeINSEE, nomCommune, codePostal, libelleAcheminement FROM communes WHERE id = ?;";
		try (PreparedStatement prepareStatement = connection.prepareStatement(sql);) {

			prepareStatement.setInt(1, Integer.parseInt(id));
			ResultSet executeQuery = prepareStatement.executeQuery();
			while (executeQuery.next()) {
				Commune commune = new Commune(executeQuery.getString("codeINSEE"), executeQuery.getString("nomCommune"),
						executeQuery.getInt("codePostal"), executeQuery.getString("libelleAcheminement"));
				return commune;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Commune getCommuneByName(String name) {
		String sql = "SELECT codeINSEE, nomCommune, codePostal, libelleAcheminement FROM communes WHERE nomCommune = ?;";
		try (PreparedStatement prepareStatement = connection.prepareStatement(sql);) {

			prepareStatement.setString(1, name);
			ResultSet executeQuery = prepareStatement.executeQuery();
			while (executeQuery.next()) {
				String codeINSEE = executeQuery.getString("codeINSEE");
				String nomCommune = executeQuery.getString("nomCommune");
				int codePostal = executeQuery.getInt("codePostal");
				String libelleAcheminement = executeQuery.getString("libelleAcheminement");

				Commune commune = new Commune(codeINSEE, nomCommune, codePostal, libelleAcheminement);
				return commune;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int countCommuneByCP(String codePostal) {
		String sql = "select count(*) from communes where codePostal like ?;";
		try (PreparedStatement prepareStatement = connection.prepareStatement(sql);) {

			prepareStatement.setString(1, codePostal + "%");
			ResultSet executeQuery = prepareStatement.executeQuery();
			while (executeQuery.next()) {
				return executeQuery.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Connection getConnection() {
		return connection;
	}
}

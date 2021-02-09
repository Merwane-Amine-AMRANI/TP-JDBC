package org.amrani;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommuneCSVImpoter implements CommuneImporter {

	private static int commit = 0;

	@Override
	public void importCommunes(String pathName) throws SQLException {
		String url = "jdbc:mysql://127.0.0.1:3306/tp_jdbc?useSSL=false";
		String user = "user_jdbc";
		String password = "root";

		Path path = Path.of(pathName);

		try (Stream<String> lines = Files.lines(path);) {

			Connection connection = DriverManager.getConnection(url, user, password);
			CommuneDBService communeService = new CommuneDBServiceImpl(connection);
			
			connection.setAutoCommit(false);
			
			List<String> listeLine = lines.skip(1).collect(Collectors.toList());
			
			List<Commune> collect = listeLine.stream().map(ligneToCommune).collect(Collectors.toList());
			
			for (Commune commune : collect) {
				communeService.writeCommune(commune);
				commit++;
				if (commit % 10000 == 0) {
					connection.commit();
					System.out.println("# commited : " + commit);
				}
			}
			connection.commit();
			connection.close();
			System.out.println("# commited : " + commit);
			System.out.println(communeService.getCommuneById(String.valueOf(1)));
			System.out.println(communeService.getCommuneByName("VOIRES"));
			System.out.println("# nombre de commune commençant par 33 : " + communeService.countCommuneByCP("33"));

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	Function<String, Commune> ligneToCommune = str -> {
		String[] split = str.split(";");
		return new Commune(split[0], split[1], Integer.parseInt(split[2]), split[4]);
	};
}

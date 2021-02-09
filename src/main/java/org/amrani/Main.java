package org.amrani;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		Path path = Path.of("files/laposte_hexasmal.csv");
		try(Stream<String> lines = Files.lines(path);) {
			
			List<String> listeLine = lines.collect(Collectors.toList());
			
			System.out.println("# nombre de ligne(s) : " + listeLine.size());
			System.out.println("# nombre de colonne(s) : " + listeLine.get(0).split(";").length);
			for (int i = 0; i < listeLine.get(0).split(";").length; i++) {
				System.out.println(listeLine.get(0).split(";")[i]);
			}
			
			Set<Entry<String, Long>> entrySet = listeLine.stream()
					.map(line -> line.split(";")[0])
					.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
					.entrySet();

			entrySet.removeIf(entry -> entry.getValue() == 1);
			System.out.println("# peut-on utiliser la colonne codeINSEE comme PRIMARY KEY ? " + (entrySet.toArray()[0] == ""));
			
			
			CommuneImporter communeImporter = new CommuneCSVImpoter();
			communeImporter.importCommunes("files/laposte_hexasmal.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

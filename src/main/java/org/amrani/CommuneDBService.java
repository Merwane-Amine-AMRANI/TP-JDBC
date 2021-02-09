package org.amrani;

public interface CommuneDBService {

	/**
	 * @param commmune
	 * @return 
	 */
	void writeCommune(Commune commmune);

	/**
	 * @param id
	 * @return
	 */
	Commune getCommuneById(String id);

	/**
	 * @param name
	 * @return
	 */
	Commune getCommuneByName(String name);

	/**
	 * @param codePostal
	 * @return
	 */
	int countCommuneByCP(String codePostal);

}

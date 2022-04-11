package it.polito.tdp.ruzzle.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	
	public List<Pos> trovaParola (String parola, Board board){
		for(Pos p : board.getPositions()) {
			// la lettera in pos è == alla prima lettera della 'parola'
			if(board.getCellValueProperty(p).get().charAt(0) == parola.charAt(0)) {
				// Posso far partire la ricorsione
				List<Pos> parziale = new ArrayList<Pos>();
				parziale.add(p);
				if (cerca(parola, parziale, 1, board))
					return parziale;
			}
		}
		
		return null;
	}
	
	public boolean cerca(String parola, List<Pos> percorso, int livello, Board board) {
		
		// Caso terminale
		if(livello == parola.length()) {
			return true;
		}
		
		Pos ultima = percorso.get(percorso.size()-1);
		List<Pos> adiacenti = board.getAdjacencies(ultima);
		for(Pos a : adiacenti) {
			if(board.getCellValueProperty(a).get().charAt(0) == parola.charAt(livello)) {
				// posso 'continuare' il mio percorso facendo attenzione a
				percorso.add(a);
				
				// Uscita rapida dalla ricorsione
				if(cerca(parola, percorso, livello +1, board))
					return true;
				percorso.remove(percorso.size() -1);
			}
		}
		
		return false;
	}
}

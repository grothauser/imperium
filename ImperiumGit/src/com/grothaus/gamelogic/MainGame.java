package com.grothaus.gamelogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class MainGame {
	static String[] ressources = { "Horse", "Iron", "Food", "Wood", "Nothing" };
	static RessourceField[][] fields = new RessourceField[5][4];
	static ArrayList<Player> players = new ArrayList<Player>();
	static Player player1 = new Player("Gwenny");
	static Player player2 = new Player("Joshy");
	static Map<String, Integer> ressourcesForColony; 
	static Map<String, Integer>  ressourcesForStreet;
	static Map<String, Integer> ressourcesForCastle; 

	public static void GameLoop(Player player1, Player player2) {
		players.add(player1);
		players.add(player2);

		//initializing fields
		initField();
		System.out.println(getPentagonPoints(new Pair(1,0), 3));
		
		// Mapping of random numbers
		double random, temp;
		for (int i = 0; i < fields.length; i++) {
			for (int j = 0; j < fields[0].length; j++) {
				random = Math.random(); // number between 0 and 1
				temp = (random * 10);
				temp = Math.round(temp);
				fields[i][j].setLuckyNumber((int) temp);
			}

		}

		printField(fields);

		Scanner scanIn  = new Scanner(System.in); ;
		boolean notFinished = true;
		
		// GameLoop
		while(notFinished && scanIn.hasNextLine()){
			System.out.println("Bitte würfeln ");

			String dice = scanIn.nextLine();
			int nr = Integer.parseInt(dice);

			if (getWinners(nr, fields)) {
				notFinished = false;
				break;
			}

			for (Player player : players) {
				System.out.println(" Was möchtest du tun, " + player.getName()
						+ " ? Position kaufen: [x,y], c: colony, ca: castle, nichts: -");
				
				String order = scanIn.nextLine();
				if(!(order.startsWith("-"))){
					int posX = Integer.parseInt(order.substring(1, 2));
					int posY = Integer.parseInt(order.substring(3, 4));
									
					if (order.startsWith("[") && order.endsWith("c")) {
						if(canBuyColony(player.getRessources())){
							System.out.println(player.getName() + " kann Kolonie kaufen ");
							fields[posX][posY].addPlayer(player, "colony");
						}else{
							System.out.println(ressourcesForColony);
							System.out.println(player.getRessources());
							System.out.println(player.getName() + " kann Kolonie NICHT kaufen ");
						}
						
					}
					else if(order.startsWith("[") && order.endsWith("ca")) {
						if(canBuyCastle(player.getRessources())){
							System.out.println(player.getName() + " kann Burg kaufen ");
							fields[posX][posY].addPlayer(player, "castle");
						}else{
							System.out.println(player.getName() + " kann Burg NICHT kaufen ");
						}
					}
				}
			}
			printField(fields);
		}
		scanIn.close();
	}

	private static boolean canBuyCastle(Map<String, Integer> ressources) {
		for(Entry<String, Integer> neededRessource : ressourcesForCastle.entrySet()){
			int value = ressources.get(neededRessource.getKey()) == null ? 0 : ressources.get(neededRessource.getKey());
			if( value != 0 && value >= neededRessource.getValue()){
				return true;
			}
		}
		return false;
	}

	private static boolean canBuyColony(Map<String, Integer> ressources) {
		//check if every needed ressource is in players ressources
		for(Entry<String, Integer> neededRessource : ressourcesForColony.entrySet()){
			int value = ressources.get(neededRessource.getKey()) == null ? 0 : ressources.get(neededRessource.getKey());
			if( value != 0 && value >= neededRessource.getValue()){
				return true;
			}
		}
		return false;
	}

	private static boolean getWinners(int nr, RessourceField[][] fields) {
		for (RessourceField[] ressourceFieldArray : fields){
			for( RessourceField ressourceField : ressourceFieldArray){
				if(ressourceField.getLuckyNumber() == nr){
					if(ressourceField.getRessourceName()!= "Nothing"){
						
					for(Entry<Player, String> fieldEntry : ressourceField.getPlayers().entrySet()){
						fieldEntry.getKey().addRessource(ressourceField.getRessourceName());
						System.out.println(ressourceField.getRessourceName() + " for " + fieldEntry.getKey().getName());
						
					}
					
					}
					
				}
			}
		}
		System.out.println("Statistics Player 1 (Gwenny) :");
		for(Entry<String, Integer> s : player1.getRessources().entrySet()){
			System.out.println(s.getValue() + ":" + s.getKey());
		}
		
		System.out.println("Statistics Player 2 (Joshy) :");
		for(Entry<String, Integer> s : player2.getRessources().entrySet()){
			System.out.println(s.getValue() + ":" + s.getKey());
		}
		
		if(player1.getRessources().size()> 5 || player2.getRessources().size() > 5){
			System.out.println("Spiel zu Ende");
			return true;
		}
		return false;
	}

	private static void printField(RessourceField[][] field) {
		for (int i = 0; i < field.length; i++) {

			for (int j = 0; j < field[0].length; j++) {
				Map<Player, String> players = field[i][j].getPlayers();
				System.out.print("Position: " + i + "," + j + " "
						+ field[i][j].getRessourceName() + " with "
								+ field[i][j].getLuckyNumber() );
				for(Entry<Player, String> player : players.entrySet()){
					System.out.print("( " + player.getKey().getName() + " ) " );
				}
				System.out.println();
			}

		}
	}
	
	public static void main(String[] args){
		GameLoop(player1, player2);
	}

	public static void initField() {
				ressourcesForColony = new HashMap<>();
				ressourcesForColony.put("Gold", 2); //{"Iron","Holz","Food","Gold","Gold"};
				ressourcesForColony.put("Food", 1);
				ressourcesForColony.put("Iron", 1);
				
				ressourcesForStreet = new HashMap<>();
				ressourcesForStreet.put("Wood", 1);
				ressourcesForStreet.put("Horse", 1);
				
				ressourcesForCastle = new HashMap<>();
				ressourcesForCastle.put("Horse", 2);
				ressourcesForCastle.put("Wood", 2);
				ressourcesForCastle.put("Iron", 2);
				ressourcesForCastle.put("Food", 2);
				ressourcesForCastle.put("Gold", 4);
			
				for(Entry<String, Integer> ress : ressourcesForColony.entrySet()){
					for(int i = 0; i < ress.getValue(); i++){
						player2.addRessource(ress.getKey());
					}
					
				}
				
				
				// Filling in Fieldset 1
				fields[0][0] = new RessourceField(ressources[4], 0, new HashMap<Player, String>());
				fields[0][1] = new RessourceField(ressources[2], 0, new HashMap<Player, String>());
				fields[0][2] = new RessourceField(ressources[1], 0, new HashMap<Player, String>());
				fields[0][3] = new RessourceField(ressources[0], 0, new HashMap<Player, String>());
				fields[1][0] = new RessourceField(ressources[3], 0, new HashMap<Player, String>());
				fields[1][1] = new RessourceField(ressources[0], 0, new HashMap<Player, String>());
				fields[1][2] = new RessourceField(ressources[2], 0, new HashMap<Player, String>());
				fields[1][3] = new RessourceField(ressources[4], 0, new HashMap<Player, String>());
				fields[2][0] = new RessourceField(ressources[3], 0, new HashMap<Player, String>());
				fields[2][1] = new RessourceField(ressources[2], 0, new HashMap<Player, String>());
				fields[2][2] = new RessourceField(ressources[0], 0, new HashMap<Player, String>());
				fields[2][3] = new RessourceField(ressources[1], 0, new HashMap<Player, String>());
				fields[3][0] = new RessourceField(ressources[2], 0, new HashMap<Player, String>());
				fields[3][1] = new RessourceField(ressources[4], 0, new HashMap<Player, String>());
				fields[3][2] = new RessourceField(ressources[0], 0, new HashMap<Player, String>());
				fields[3][3] = new RessourceField(ressources[1], 0, new HashMap<Player, String>());
				fields[4][0] = new RessourceField(ressources[3], 0, new HashMap<Player, String>());
				fields[4][1] = new RessourceField(ressources[4], 0, new HashMap<Player, String>());
				fields[4][2] = new RessourceField(ressources[3], 0, new HashMap<Player, String>());
				fields[4][3] = new RessourceField(ressources[2], 0, new HashMap<Player, String>());
		
	}
	
	public static ArrayList<Pair> getPentagonPoints(Pair startingPoint, int size){
		ArrayList<Pair> points = new ArrayList<>();
		//add starting point
		points.add(new Pair(startingPoint.getX(),startingPoint.getY()));
		int x = startingPoint.getX();
		int y = startingPoint.getY();
		
		for(int i = 0; i < size; i++){
			System.out.println(points.size());
			points.add(new Pair(x+1, y+1));
			points.add(new Pair(x+1, y+2));
			points.add(new Pair(x, y+3));
			points.add(new Pair(x-1, y+2));
			points.add(new Pair(x-1, y+1));
			x= x + 2;
			
		}
		return points;
		
	}

}

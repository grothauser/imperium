package com.grothaus.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.grothaus.GamePlay.MainGame;
import com.grothaus.GamePlay.Player;
import com.grothaus.GamePlay.RessourceField;

public class GameTest {

	@Test
	public void test() {
		String[] ressources = { "Horse", "Iron", "Food", "Wood", "Nothing" };
		RessourceField[][] field = new RessourceField[5][4];
		ArrayList<Player> players = new ArrayList<Player>();
		Player player1 = new Player("Gwenny");
		Player player2 = new Player("Joshy");
		
		//MainGame.GameLoop(player1, player2);
		MainGame.initField();
		assertEquals(field[0][0].getRessourceName(), "Nothing");
	}

}

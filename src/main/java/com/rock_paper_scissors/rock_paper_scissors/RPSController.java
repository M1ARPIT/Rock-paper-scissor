package com.rock_paper_scissors.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Random;

@RestController
@RequestMapping("/game")
public class RPSController {
	private static final String[] MOVES = {"r", "p", "s"};
	private final Random random = new Random();

	@GetMapping("/play")
	public String playGame(@RequestParam String playerMove) {
		if (!isValidMove(playerMove)) {
			return "Invalid move! Use 'r' for Rock, 'p' for Paper, or 's' for Scissors.";
		}

		String aiMove = MOVES[random.nextInt(MOVES.length)];
		String result = determineWinner(playerMove, aiMove);

		return "Player: " + playerMove + " | AI: " + aiMove + " | Result: " + result;
	}

	private boolean isValidMove(String move) {
		return move.equals("r") || move.equals("p") || move.equals("s");
	}

	private String determineWinner(String playerMove, String aiMove) {
		if (playerMove.equals(aiMove)) {
			return "It's a tie!";
		} else if ((playerMove.equals("r") && aiMove.equals("s")) ||
				(playerMove.equals("p") && aiMove.equals("r")) ||
				(playerMove.equals("s") && aiMove.equals("p"))) {
			return "You won!";
		} else {
			return "You lost!";
		}
	}
}

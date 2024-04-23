package main.consola;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import preguntas.Letras;
import util.Consts;

public class Partida {
	public static void main(String[] args) throws IOException {
		
		Files.delete(Consts.RANKING_PATH);
		Files.createFile(Consts.RANKING_PATH);
		for (int i = 0; i < 101; i++) {
			Files.writeString(Consts.RANKING_PATH,String.valueOf(i)+"\n",StandardOpenOption.APPEND);		
		}
		
		Letras a = new Letras();
		System.out.println(a.getEnunciado());
		System.out.println(a.getSolucion());
	}
}


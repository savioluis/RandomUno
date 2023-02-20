import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Result {

	public static void main(String[] args) {
		File f1 = new File("Winner.txt");
		
		try {
			Scanner scanner = new Scanner(f1);
			while(scanner.hasNext()) {
				String namePlayer = scanner.next();
				System.out.println(namePlayer + " WIN THE GAME!!!!!!!!!");
			}
		}catch(FileNotFoundException e) {
			System.out.println("ERRO DE LEITURA");
		}
	}

}

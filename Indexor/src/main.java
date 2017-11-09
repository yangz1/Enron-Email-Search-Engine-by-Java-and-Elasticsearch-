import java.io.File;
import java.util.function.LongConsumer;

public class main {

	public static void main(String[] args) {
		Reader reader = new Reader();
		Poster poster = new Poster();

		File enron = new File("test");
		File[] people = enron.listFiles();
		for (int i = 1; i < people.length; i++) {
			File[] folders = people[i].listFiles();
			for (int j = 0; j < folders.length; j++) {
				if (!folders[j].getName().equals(".DS_Store")) {
					System.out.println(folders[j].getName());
					File[] emails = folders[j].listFiles();
					for (File f : emails) {
						poster.postToES(reader.readFile(
								"test/" + people[i].getName() + "/" + folders[j].getName() + "/" + f.getName()));
					}
				}

			}
		}
		System.out.println("DONE");

	}

}

package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", 		"dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };


	public static void main(String[] args) throws FileNotFoundException {

		long t0 = System.nanoTime();

		// skapa och fyll en mängd med ord som ska skippas.
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while (scan.hasNext()) {
			String word = scan.next().toLowerCase();
			stopwords.add(word);
		}
		scan.close();

		// skapa samtliga Textprocessors
		List<TextProcessor> processors = new LinkedList<TextProcessor>();
		processors.add(new SingleWordCounter("nils"));
		processors.add(new SingleWordCounter("norge"));
		processors.add(new MultiWordCounter(REGIONS));
		processors.add(new GeneralWordCounter(stopwords));

		// skanna in texten och låt varje processor köra sin process-metod.
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");
		while (s.hasNext()) {
			String word = s.next().toLowerCase();

			for (TextProcessor tp : processors) {
				tp.process(word);
			}
		}
		s.close();

		// Låt alla processors rapportera.
		for (TextProcessor tp : processors) {
			tp.report();
		}

		long t1 = System.nanoTime();
		System.out.println((t1-t0)/1000000); 
	}
}

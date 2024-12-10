package editor.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

public class Document {

	private static StringBuilder text = new StringBuilder();

	public String getText() {
		return text.toString();
	}

	public void open(String filename) throws IOException {
		BufferedReader input = new BufferedReader(new FileReader(filename));
		String line;
		text = new StringBuilder();

		while ((line = input.readLine()) != null)
			text.append(line);

		input.close();
	}

	public void insert(String[] words) {
		for (int i = 0; i < words.length; i++)
			text.append(words[i] + " ");
	}

	public void removeLastWord() {
		int indexOfLastWord = text.toString().trim().lastIndexOf(" ");

		if (indexOfLastWord == -1)
			text = new StringBuilder("");
		else
			text.setLength(indexOfLastWord + 1);
	}

	public void replace(String source, String destination) {
		text = new StringBuilder(text.toString().replaceAll(Pattern.quote(source), destination));
	}
}

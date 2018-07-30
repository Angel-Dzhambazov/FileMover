package encodeDecodeTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RunMain {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String sourceFile = reader.readLine();

		try {
			EncodingDecoding.encode(sourceFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package encodeDecodeTest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

class EncodingDecoding {

	public static void encode(String sourceFile) throws Exception {
		writeEncodedByteArraysToFile(sourceFile);
	}

	public static void decode(String sourceFile, String targetFile) throws Exception {
		writeDecodedByteArraysToFile(sourceFile, targetFile);
	}


	private static void writeEncodedByteArraysToFile(String sourceFile) throws IOException {

		File file = new File(sourceFile);
		File targetFile = new File(sourceFile+"_encoded");

		BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
		BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(targetFile));
		int read = -1;
		byte[] bytez = new byte[300];
		try {
			while ((read = reader.read(bytez)) != -1) {
				byte[] realBuff = Arrays.copyOf(bytez, read);
				byte[] decodedBytes = Base64.getEncoder().encode(realBuff);
				writer.write(decodedBytes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		writer.flush();
		writer.close();
		reader.close();
	}



	public static void writeDecodedByteArraysToFile(String sourceFile, String targetFileArg) throws IOException {
		File file = new File(sourceFile);
		File targetFile = new File(targetFileArg);

		BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));

		BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(targetFile));

		int read = -1;
		byte[] bytez = new byte[300];
		try {
			while ((read = reader.read(bytez)) != -1) {
/*				byte[] data;
				if (read == bytez.length)
					data = bytez;
				else {
					data = new byte[read];
					System.arraycopy(bytez, 0, data, 0, read);
				}

				byte[] decodedBytes = Base64.getDecoder().decode(data);
				writer.write(decodedBytes);*/
				byte[] realBuff = Arrays.copyOf(bytez, read);
				byte[] decodedBytes = Base64.getDecoder().decode(realBuff);
				writer.write(decodedBytes);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		writer.flush();
		writer.close();
		reader.close();

	}


}

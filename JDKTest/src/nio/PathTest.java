package nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {

	public static void main(String args[]) throws IOException{
		Path path = Paths.get("E:\\college\\a.txt");
		
		Charset charset = Charset.forName("UTF-8");
		try(InputStream in = Files.newInputStream(path);
				BufferedReader reader = new BufferedReader(new InputStreamReader(in))){
			
				String line = null;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
		}catch(IOException e) {
			e.printStackTrace();
			 System.err.format("IOException: %s%n", e);
		}
		
		String type = Files.probeContentType(path);
		System.out.println("MIME type is:"+ type);
		
		for (FileStore store: FileSystems.getDefault().getFileStores()){
			System.out.println("file store is:"+ store.name());
		}
		
		String test = "abcdefg";
		int i = 0;
		System.out.println(test.charAt(i++));
		System.out.println(i);
		
	}
}

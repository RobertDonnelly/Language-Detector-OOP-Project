package ie.gmit.sw;

import java.io.*;
import java.sql.DatabaseMetaData;
import java.util.zip.InflaterInputStream;

import javax.security.auth.callback.LanguageCallback;


public class Parser implements Runnable{
	private Database db = null;
	private String file;
	private int k;
	
	public Parser(String file, int k) {
		this.file=file;
		this.k=k;
	}
	
	
	public void setDb(Database db2) {
		this.db=db2;
	}
	
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String line = null;
			
			while((line = br.readLine()) != null) {
				String[] record = line.trim().split("@");
				if (record.length !=2)continue;
				parse(record[0],record[1]);
			}
			br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void parse(String text, String lang, int... ks) {
		Language language = Language.valueOf(lang);
		
		for(int i=0; i <= text.length() - k; i++) {
			CharSequence kmer = text.substring(i, i+k);
			db.add(kmer,language);
		}
	}

public static void main(String[] args) throws InterruptedException {
		Parser p = new Parser("wili-2018-Small-11750-Edited.txt",5);
		
		Database db = new Database();
		p.setDb(db);
		Thread t = new Thread();
		t.start();
		t.join();
		
		db.resize(300);
		
		
	}
	
//analyse query
}
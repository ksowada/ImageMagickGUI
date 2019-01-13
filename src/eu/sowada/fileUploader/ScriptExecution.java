package eu.sowada.fileUploader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScriptExecution {

	private File fileSrc;
	private Script script;

	public ScriptExecution(File fileSrc, Script script) {
		this.fileSrc = fileSrc;
		this.script = script;
	}
	
	public int execute() throws Exception {
		
		ProcessBuilder builder = null;
		if (script.outFile.toLowerCase().equals("no")) {
			builder = new ProcessBuilder(script.cmd, script.operator, fileSrc.toString());
			System.out.println("execute("+script.cmd+" "+script.operator+" "+fileSrc.toString());
		}
		if (builder == null) return -1;
		
		builder.redirectErrorStream(true);
		final Process process = builder.start();

		// Watch the process
		watch(process);
		return 0;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	private static void watch(final Process process) {
	    new Thread() {
	        public void run() {
	            BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            String line = null; 
	            try {
	                while ((line = input.readLine()) != null) {
	                    System.out.println(line);
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }.start();
	}

}

package eu.sowada.fileUploader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ScriptExecution {

	
	public void execute(Script script) throws Exception {
		
		ProcessBuilder builder = new ProcessBuilder(script.cmd, "arg1");
		builder.redirectErrorStream(true);
		final Process process = builder.start();

		// Watch the process
		watch(process);
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

package tests;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;

public class AppiumServer {
	Process process = null;
	Runtime runtime;
	
	public Process startServer() throws InterruptedException {
		runtime = Runtime.getRuntime();
		
		try {
			 process = runtime.exec("cmd.exe /c start cmd.exe /k \"appium -a 0.0.0.0 -p 4723 --session-override -dc \"{\"\"noReset\"\": \"\"false\"\"}\"\"");
			
			Thread.sleep(10000);
			
			if (process != null) {
		        System.out.println("Appium server started");
		    }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return process;
	}
	
	public void stopServer() {
		runtime = Runtime.getRuntime();
		System.out.println("Stopping Appium Server");
		if (process != null) {
		    process.destroy();
		}
		System.out.println("Appium server stop");
		 CommandLine command = new CommandLine("cmd");
	      command.addArgument("/c");
	      command.addArgument("taskkill");
	      command.addArgument("/F");
	      command.addArgument("/IM");
	      command.addArgument("node.exe");

	      DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
	      DefaultExecutor executor = new DefaultExecutor();
	      executor.setExitValue(1);
	      try {
	            executor.execute(command, resultHandler);
	            System.out.println("Stopped appium node ! ");
	      } catch (IOException e) {
	            System.out.println("FAIL => Unable to stop appium server "+e.getMessage());
	            e.printStackTrace();
	      }
	}
	
	public static void main(String[] args) throws InterruptedException {
		AppiumServer appiumServer = new AppiumServer();
		appiumServer.startServer();
		appiumServer.stopServer();
	}
}
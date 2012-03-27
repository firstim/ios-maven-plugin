package com.brewinapps.maven.plugins.ios;

import java.util.List;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;


/**
 * 
 * @author Brewin' Apps AS
 * @goal deploy
 */
public class AutopilotDeployMojo extends AbstractMojo {
	
	/**
	 * TestFlight Api Token
	 * @parameter
	 * 		expression="${ios.apiToken}"
	 */
	private String apiToken;
	
	/**
	 * TestFlight Team Token
	 * @parameter
	 * 		expression="${ios.teamToken}"
	 */
	private String teamToken;
	
	/**
	 * TestFlight Distribution Lists
	 * @parameter
	 */
	private List<String> distributionLists;
	
	/**
	 * TestFlight Release Notes
	 * @parameter
	 * 		expression="${ios.releaseNotes}"
	 * 		default-value="Release generated by ios-maven-plugin"
	 */
	private String releaseNotes;
	
	/**
	 * TestFlight Notification
	 * @parameter
	 * 		expression="${ios.notify}"
	 * 		default-value="false"
	 */
	private boolean notify;
	
	/**
	 * IPA Path
	 * @parameter
	 * 		expression="${autopilot.ipa}"
	 */
	private String ipa;
	
	/**
	 * Replace Earlier TestFlights?
	 * @parameter
	 * 		expression="${autopilot.replace}"
	 * 		default-value="false"
	 */
	private boolean replace;

	/**
	 * 
	 */
	public void execute() throws MojoExecutionException, MojoFailureException {
		try {
			
			// Make sure the ipa is specified
			if (ipa == null) {
				throw new AutopilotException("The 'ipa' parameter must be specified!");
			}
			
			ProjectDeployer.deploy(apiToken, teamToken, distributionLists, releaseNotes, notify, ipa, replace);
		} catch (AutopilotException e) {
			throw new MojoExecutionException(e.getMessage());
		}
	}

}
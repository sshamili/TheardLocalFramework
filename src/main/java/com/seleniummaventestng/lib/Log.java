package com.seleniummaventestng.lib;

import java.io.File;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.RootLoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

public class Log {

	public static void startTestCase(String sTestCaseName) {

		sTestCaseName = sTestCaseName.replaceAll("[^a-zA-Z0-9]", "_");
		sTestCaseName = sTestCaseName.replaceAll("_+", "_");

		startLog(System.getProperty("log.directory"), sTestCaseName);
		info("\n\n************** Execution Started : " + sTestCaseName + " on Thread " + Thread.currentThread().getId()
				+ "**************\n");
	}

	public static void endTestCase(String sTestCaseName) {

		info("\n\n************** Execution End : " + sTestCaseName + " on Thread " + Thread.currentThread().getId()
				+ "**************\n");
	}

	private static void startLog(String dirPath, String testCaseName) {

		int noOfFiles = 0;

		File dir = new File(dirPath);
		if (dir.exists()) {
			int count = 0;
			for (File file : dir.listFiles()) {
				if (file.isFile() && file.getName().endsWith(".log") && file.getName().contains(testCaseName)) {
					count++;
				}
			}

			noOfFiles = count;
		}

		noOfFiles++;
		String logFilePath = dirPath + "/" + testCaseName + "_" + noOfFiles + ".log";

		ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

		LayoutComponentBuilder standard = builder.newLayout("PatternLayout");
		standard.addAttribute("pattern", "%d{HH:mm:ss}  %-5.5p  %m%n");

		AppenderComponentBuilder console = builder.newAppender("stdout", "Console");

		AppenderComponentBuilder file = builder.newAppender("log", "File");
		file.addAttribute("fileName", logFilePath);

		console.add(standard);
		file.add(standard);

		builder.add(console);
		builder.add(file);

		RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.ERROR);
		rootLogger.add(builder.newAppenderRef("stdout"));

		builder.add(rootLogger);

		Configurator.initialize(builder.build());
	}

	public static Logger getCurrentLog() {
		return LogManager.getLogger("SAFLog_" + Thread.currentThread().getId());
	}

	public static String getClassName() {

		return new Throwable().getStackTrace()[3].getClassName();
	}

	public static int getLineNumber() {

		return new Throwable().fillInStackTrace().getStackTrace()[2].getLineNumber();

	}

	public static String getCallInfo() {

		String callInfo;
		String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
		int lineNumber = Thread.currentThread().getStackTrace()[3].getLineNumber();

		callInfo = "T - " + Thread.currentThread().getId() + " " + getClassName() + ":" + methodName + " " + lineNumber
				+ " ";
		return callInfo;

	}

	public static void trace(Object message) {
		getCurrentLog().trace(message);
	}

	public static void trace(Object message, Throwable t) {
		getCurrentLog().trace(message, t);
	}

	public static void debug(Object message) {

		getCurrentLog().debug(getCallInfo() + message);
	}

	public static void debug(Object message, Throwable t) {
		getCurrentLog().debug(getCallInfo() + message, t);
	}

	public static void error(Object message) {

		getCurrentLog().error(getCallInfo() + message);
	}

	public static void error(Object message, Throwable t) {
		getCurrentLog().error(getCallInfo() + message, t);
	}

	public static void fatal(Object message) {
		getCurrentLog().fatal(getCallInfo() + message);
	}

	public static void fatal(Object message, Throwable t) {
		getCurrentLog().fatal(getCallInfo() + message, t);
	}

	public static void info(Object message) {

		getCurrentLog().info(getCallInfo() + message);
	}

	public static void info(Object message, Throwable t) {
		getCurrentLog().info(getCallInfo() + message, t);
	}

	public static void warn(Object message) {
		getCurrentLog().warn(getCallInfo() + message);
	}

	public static void warn(Object message, Throwable t) {
		getCurrentLog().warn(getCallInfo() + message, t);
	}
}
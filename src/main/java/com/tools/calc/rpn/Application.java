package com.tools.calc.rpn;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	BuildProperties buildProperties;
	@Value("${spring.application.name}")
	private String appName;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(appName + " " + buildProperties.getVersion());

		RPNCalculator rPNCalculator = new RPNCalculator();

		Scanner scanner = new Scanner(System.in);
		String input = null;
		System.out.print(" > ");
		while (input == null || (input != null && !input.equals("exit"))) {
			input = scanner.nextLine();
			if (!input.equals("exit")) {
				String result = rPNCalculator.process(input);
				if (rPNCalculator.getError() != null)
					System.out.println(rPNCalculator.getError());
				System.out.print(result + " > ");
			}
		}
		scanner.close();
		System.out.println("exiting");

	}

}

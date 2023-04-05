package com.example.springbootac;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootAcApplication {

	@Bean
	ApplicationRunner conditionEvaluationReportRunner(ConditionEvaluationReport report) {
		return args -> System.out.println(
				"Auto Configured Bean Count = " +
				report.getConditionAndOutcomesBySource().entrySet().stream()
				.filter(co -> co.getValue().isFullMatch())
				.filter(co -> !co.getKey().toLowerCase().contains("jmx"))
				.map(co -> {
					System.out.println(co.getKey());
					co.getValue().forEach(c -> System.out.println("\t" + c.getOutcome() + "\n"));
					return co;
				}).count()
		);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAcApplication.class, args);
	}

}

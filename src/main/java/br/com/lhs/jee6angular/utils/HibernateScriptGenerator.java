package br.com.lhs.jee6angular.utils;

public class HibernateScriptGenerator {
	public static void main(String[] args) {

		HibernateExporter exporter = new HibernateExporter(
				HibernateExporter.DEFAULT_DIALECT,
				HibernateExporter.DEFAULT_MODEL_PACKAGE);
		exporter.exportToConsole();
	}
}

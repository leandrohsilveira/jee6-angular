package br.com.lhs.jee6angular.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.jdbc.internal.FormatStyle;
import org.hibernate.engine.jdbc.internal.Formatter;
import org.jboss.logging.Logger;
import org.reflections.Reflections;

/**
 * This class will create an hibernate {@link Configuration} with the given
 * dialect and will scan provided package for {@link MappedSuperclass} and
 * {@link Entity}. You can then use the export methods to generate your schema
 * DDL.
 *
 * @author Geoffroy Warin (https://github.com/geowarin)
 *
 */
public class HibernateExporter implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8410237358971993924L;

	private static Logger log = Logger.getLogger(HibernateExporter.class);

	public static final String DEFAULT_MODEL_PACKAGE = "br.com.lhs.jee6angular.model";
	public static final String DEFAULT_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";

	private String dialect;
	private String entityPackage;

	private boolean generateCreateQueries = true;
	private boolean generateDropQueries = false;

	private Configuration hibernateConfiguration;

	public HibernateExporter(String dialect, String entityPackage) {
		this.dialect = dialect;
		this.entityPackage = entityPackage;

		hibernateConfiguration = createHibernateConfig();
	}

	@Produces
	public HibernateExporter producesHibernateExporter() {
		return new HibernateExporter(HibernateExporter.DEFAULT_DIALECT, HibernateExporter.DEFAULT_MODEL_PACKAGE);
	}

	public void export(OutputStream out, boolean generateCreateQueries, boolean generateDropQueries) {

		Dialect hibDialect = Dialect.getDialect(hibernateConfiguration.getProperties());
		try (PrintWriter writer = new PrintWriter(out)) {

			if (generateCreateQueries) {
				String[] createSQL = hibernateConfiguration.generateSchemaCreationScript(hibDialect);
				write(writer, createSQL, FormatStyle.DDL.getFormatter());
			}
			if (generateDropQueries) {
				String[] dropSQL = hibernateConfiguration.generateDropSchemaScript(hibDialect);
				write(writer, dropSQL, FormatStyle.DDL.getFormatter());
			}
		}
	}

	public void export(File exportFile) throws FileNotFoundException {

		export(new FileOutputStream(exportFile), generateCreateQueries, generateDropQueries);
	}

	public void exportToConsole() {

		export(System.out, generateCreateQueries, generateDropQueries);
	}

	private void write(PrintWriter writer, String[] lines, Formatter formatter) {

		for (String string : lines)
			writer.println(formatter.format(string) + ";");
	}

	private Configuration createHibernateConfig() {

		hibernateConfiguration = new Configuration();

		final Reflections reflections = new Reflections(entityPackage);
		for (Class<?> cl : reflections.getTypesAnnotatedWith(MappedSuperclass.class)) {
			hibernateConfiguration.addAnnotatedClass(cl);
			HibernateExporter.log.info("Mapped = " + cl.getName());
		}
		for (Class<?> cl : reflections.getTypesAnnotatedWith(Entity.class)) {
			hibernateConfiguration.addAnnotatedClass(cl);
			HibernateExporter.log.info("Mapped = " + cl.getName());
		}
		hibernateConfiguration.setProperty(AvailableSettings.DIALECT, dialect);
		return hibernateConfiguration;
	}

	public boolean isGenerateDropQueries() {
		return generateDropQueries;
	}

	public void setGenerateDropQueries(boolean generateDropQueries) {
		this.generateDropQueries = generateDropQueries;
	}

	public Configuration getHibernateConfiguration() {
		return hibernateConfiguration;
	}

	public void setHibernateConfiguration(Configuration hibernateConfiguration) {
		this.hibernateConfiguration = hibernateConfiguration;
	}
}
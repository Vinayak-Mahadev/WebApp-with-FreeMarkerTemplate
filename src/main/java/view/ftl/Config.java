package view.ftl;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

class Config {

	private static Config config;
	private Configuration configuration;
	private Config(){}

	public static Configuration  getConfig() {
		String location = "/views/ftp";
		
			config = new  Config();
			config.configuration = new Configuration(Configuration.VERSION_2_3_27);
			try {
				config.configuration.setClassForTemplateLoading(Config.class,location);
				config.configuration.setDefaultEncoding("UTF-8");
				config.configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
				config.configuration.setLogTemplateExceptions(false);
				config.configuration.setWrapUncheckedExceptions(true);
				return config.configuration;
			
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
}

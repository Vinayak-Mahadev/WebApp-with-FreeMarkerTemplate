package view.ftl;

import freemarker.template.Template;

public class TemplateLocal {
	
	
	public static Template getTemplate(String ftl) {
			try {
				return Config.getConfig().getTemplate(ftl);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} 
	}
	
}

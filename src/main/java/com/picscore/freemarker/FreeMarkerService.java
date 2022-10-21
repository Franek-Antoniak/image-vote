package com.picscore.freemarker;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FreeMarkerService {

	private final FreeMakerConfigBean configBean;

	public <T> ResponseEntity<String> getResponseEntityHTML(String templateName, String parameterName, T object) {
		return ResponseEntity.ok(getOutputHtml(getDataModel(parameterName, object), getTemplateByName(templateName)));
	}

	private String getOutputHtml(Map<String, Object> dataModel, Template template) {
		String htmlWithTemplate = "";
		try {
			Writer stringWriter = new StringWriter();
			template.process(dataModel, stringWriter);
			htmlWithTemplate = stringWriter.toString();
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}
		return htmlWithTemplate;
	}

	private Map<String, Object> getDataModel(String tag, Object bean) {
		Map<String, Object> dataModel = new HashMap<>();
		dataModel.put(tag, bean);
		return dataModel;
	}

	private Template getTemplateByName(String templateName) {
		Template template;
		try {
			template = configBean.getCfg()
			                     .getTemplate(templateName);
		} catch (IOException e) {
			throw new IllegalStateException(String.format("Template %s not found. %s", templateName, e.getMessage()));
		}
		return template;
	}

	public ResponseEntity<String> getResponseEntityHTML(String templateName, String[] names, Object[] objects) {
		return ResponseEntity.ok(getOutputHtml(getDataModel(names, objects), getTemplateByName(templateName)));
	}

	private Map<String, Object> getDataModel(String[] tags, Object[] beans) {
		Map<String, Object> dataModel = new HashMap<>();
		for (int i = 0; i < tags.length; i++) {
			dataModel.put(tags[i], beans[i]);
		}
		return dataModel;
	}

	public ResponseEntity<String> getResponseEntityHTML(String templateName) {
		return ResponseEntity.ok(getOutputHtml(getTemplateByName(templateName)));
	}

	private String getOutputHtml(Template template) {
		return template.toString();
	}

	public ResponseEntity<String> getResponseEntityHTML(String templateName, HttpStatus httpStatus) {
		return ResponseEntity.status(httpStatus)
		                     .body(getOutputHtml(getTemplateByName(templateName)));
	}
}

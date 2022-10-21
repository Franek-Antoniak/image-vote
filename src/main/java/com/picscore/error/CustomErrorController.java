package com.picscore.error;

import com.picscore.freemarker.FreeMarkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class CustomErrorController implements ErrorController {

	private final FreeMarkerService freeMarkerService;

	@RequestMapping("/error")
	public ResponseEntity<String> handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			int statusCode = Integer.parseInt(status.toString());

			if (statusCode == HttpStatus.FORBIDDEN.value()) {
				return freeMarkerService.getResponseEntityHTML("error-403.ftl");
			}
		}
		return freeMarkerService.getResponseEntityHTML("error-403.ftl");
	}

}

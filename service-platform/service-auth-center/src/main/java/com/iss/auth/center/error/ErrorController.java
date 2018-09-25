/**
 * 
 */
package com.iss.auth.center.error;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.common.ResponseEntity;

/**
 * @author yuanghuangd
 * @date 2018年9月21日 下午1:42:36
 */

@RestController
public class ErrorController {

	@GetMapping(value = "/getError.json")
	public ResponseEntity error() {
		ResponseEntity responseEntity = ResponseEntity.API_SERVICE_OK;
		return responseEntity;
	}
}

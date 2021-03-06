package com.capitalone.dashboard.controller;


import com.capitalone.dashboard.collector.DefaultWhiteSourceClient;
import com.capitalone.dashboard.misc.HygieiaException;
import com.capitalone.dashboard.model.WhiteSourceRequest;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
public class DefaultWhiteSourceController {

    private final DefaultWhiteSourceClient defaultWhiteSourceClient;

    @Autowired
    public DefaultWhiteSourceController(DefaultWhiteSourceClient defaultWhiteSourceClient) {
        this.defaultWhiteSourceClient = defaultWhiteSourceClient;
    }


    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 401, message = "BAD REQUEST"),
            @ApiResponse(code = 403, message = "forbidden( Unauthorized)"),
            @ApiResponse(code = 500, message = "System Internal Error") })
    @RequestMapping(value = "/project-alerts", method = POST,
            consumes = "application/json", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setProjectVitalsAndAlerts(@Valid @RequestBody WhiteSourceRequest request) throws HygieiaException {
        String response = defaultWhiteSourceClient.process(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}

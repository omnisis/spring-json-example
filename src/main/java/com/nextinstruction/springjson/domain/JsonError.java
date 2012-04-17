package com.nextinstruction.springjson.domain;

import com.google.common.collect.ImmutableMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

public class JsonError {
    private int     errCode;
    private String  errMessage;

    public JsonError(int errCode, String errMessage) {
        this.errCode = errCode;
        this.errMessage = errMessage;
    }

    public ModelAndView asModelAndView() {
        MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
        return new ModelAndView(jsonView, ImmutableMap.of("error", errMessage, "message", errMessage));
    }
}

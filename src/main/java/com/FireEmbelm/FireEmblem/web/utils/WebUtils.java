package com.FireEmbelm.FireEmblem.web.utils;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {

    public static String getServerPath(HttpServletRequest httpServletRequest) {

        String path = httpServletRequest.getRequestURL().toString();

        return path.replace(httpServletRequest.getServletPath(), "");
    }

}

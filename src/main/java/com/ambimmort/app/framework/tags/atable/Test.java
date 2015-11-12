package com.ambimmort.app.framework.tags.atable;

import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by hedingwei on 5/29/15.
 */
@Controller
public class Test {


    @RequestMapping(value = "/springPaginationDataTables.do", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String springPaginationDataTables(HttpServletRequest request) throws IOException {

        //Fetch the page number from client
        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart"))
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart"))/10)+1;

        //Fetch search parameter
        String searchParameter = request.getParameter("sSearch");

        //Fetch Page display length
        Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));

        //Add page list data
        List<String> personsList = new XPagination<String>().getDatas(pageNumber,pageDisplayLength);


        JSONObject object = JSONObject.fromObject(personsList);

        TableJsonObject<String> personJsonObject = new TableJsonObject<>();
        //Set Total display record
        personJsonObject.setiTotalDisplayRecords(500);
        //Set Total record
        personJsonObject.setiTotalRecords(500);
        personJsonObject.setAaData(personsList);

        JSONObject gson = JSONObject.fromObject(personJsonObject);
        String json2 = gson.toString(4);

        return json2;
    }


}

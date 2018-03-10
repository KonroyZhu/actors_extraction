package com.konroy.app.web.getActorController;

import com.konroy.app.entity.actor.Actor;
import com.konroy.app.service.getActorService.GetActorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/getActor")
public class getActorController {
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public List<Actor> get(HttpServletRequest request, HttpServletResponse response) throws Exception{
        return  new GetActorService().getActor(request.getParameter("sentence"),request.getParameter("movie"));
    }
}

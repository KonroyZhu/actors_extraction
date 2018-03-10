package com.konroy.app.service.getActorService;

import com.konroy.app.entity.actor.Actor;
import com.konroy.app.utils.getActor1.recongnize.GetActor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetActorService {
    public List<Actor> getActor(String sentence, String movie){
        List<Actor> actorList=new ArrayList<Actor>();
        GetActor getActor=new GetActor();
        String orientation=getActor.getStr(sentence,movie);


        String[] actors=orientation.split(" ");
        for(String a:actors){
            if(a.contains("-")){
                Actor actor=new Actor();
                String realName=a.split("-")[1];
                String roleName=a.split("-")[0];

                actor.setRealName(realName);
                actor.setNickName(roleName);
                actorList.add(actor);
            }

        }
        return actorList;
    }

    public static  void  main(String[] args){
        System.out.println(new GetActorService().getActor("吴京找卢靖姗来当女主品味不错","战狼2(终).txt"));
    }
}

package com.konroy.app.utils.getActor1.parsing;

import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLSentence;
import com.hankcs.hanlp.corpus.dependency.CoNll.CoNLLWord;
import com.hankcs.hanlp.dependency.CRFDependencyParser;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static void  main(String[] args){
        String sentence="吴京是个很好的动作片演员";

        List<String> list=new Parser().parse(sentence);
        System.out.println(list);
    }

    public List<String> parse(String sentence){
        CoNLLSentence result=CRFDependencyParser.compute(sentence);
        List<String> comment=new ArrayList<String>();
        for(CoNLLWord w:result.getWordArray()){
            //when comparing two strings we should use equal() rather than ==
            if(w.POSTAG.equals("v")) comment.add(w.NAME);
            if(w.POSTAG.equals("n")) comment.add(w.NAME);
            if(w.POSTAG.equals("d")) comment.add(w.NAME);
            if(w.POSTAG.equals("b")) comment.add(w.NAME);
            if(w.DEPREL.equals("核心成分")) comment.add(w.NAME);
            //System.out.println(w);

        }
        return comment;
    }
}

# actors_extraction

电影评论影人抽取系统：

功能：对中文电影（以战狼2为主）评论实现影人（演员名）抽取

抽取过程：
    
    主函数在/utils/getActor1/recongnize/GetActor.java下
    
    抽取算法主要分为6个步骤：
       
        1.对评论句子进行分词，借助hanlp抽取出标签为nr（人名），nrf（外国人名）以及nz（专有名词）的词语作为演员名字的候选集合
        
        2.从数据库中抽取该电影的演员名与对应的角色名N，计算N与候选名词各项的jaccard系数（由于编辑距离容易收到名词长度影响，此处采用jaccard系数）
        
        3.将小于一定阈值的归入演员名，并且匹配出jaccard系数最大的作为最后的结果（从候选集合中删去）
        
        4.为剩下的候选集合中的名词w构造百度问句进行网页搜索
       
        5.返回与词语w相距12个字符的文本t
        
        6.从文本t中匹配数据库中的演员名字返回结果
        

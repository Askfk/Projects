package com.bjtu.etransfer.etransfer;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class BusPathSearch {

    private Hashtable<Integer, String> mHashTable1;
    private Hashtable<String, Integer> mHashTable2;
    public GraphEntry graph[];
    public TableEntry table[];
    private int vertexNum;
    private String beginStation;
    private int beginVertex;
    private String endStation;
    private int endVertex;
    private String mResult;
    public ArrayList<Integer> mPath;

    public ArrayList<String> shardPath, mPathString;

    /** construct function */
    public BusPathSearch(){
        initHashTable();
        initGraph();
        makeGraph();
        addGraphDetail();
        initTable();
    }

    /** hash table init */
    public void initHashTable(){
        int i, j, id = 0;

        mHashTable1 = new Hashtable<Integer, String>();
        mHashTable2 = new Hashtable<String, Integer>();

        for (i = 0; i < BusFinalVars.line.length; i++)
        {
            //System.out.println("*********" + (i+1) + "号线***************");
            for (j = 0; j < BusFinalVars.line[i].length; j++){
                if (mHashTable1.contains(BusFinalVars.line[i][j]) == false)
                {
                    //System.out.println(BusFinalVars.lines[i][j]);
                    mHashTable1.put(id, BusFinalVars.line[i][j]);
                    mHashTable2.put(BusFinalVars.line[i][j], id);
                    id++;
                }
            }
        }
        for (i = 0; i < BusFinalVars.linex.length; i++)
        {
            //System.out.println("*********" + (i+1) + "号线***************");
            for (j = 0; j < BusFinalVars.linex[i].length; j++){
                if (mHashTable1.contains(BusFinalVars.linex[i][j]) == false)
                {
                    //System.out.println(BusFinalVars.lines[i][j]);
                    mHashTable1.put(id, BusFinalVars.linex[i][j]);
                    mHashTable2.put(BusFinalVars.linex[i][j], id);
                    id++;
                }
            }
        }
        vertexNum = id;
    }

    /** return id by string */
    public int findIdByKey(String key){
        return mHashTable2.get(key);
    }

    /** return string by id */
    public String findStrById(int id){
        return mHashTable1.get(id);
    }

    /** graph init */
    public void initGraph(){
        int vertexNum = this.getVertexNum();
        graph = new GraphEntry[vertexNum];
        for (int i = 0; i < vertexNum; i++)
        {
            graph[i] = new GraphEntry();
        }

        mPath = new ArrayList<Integer>();
        shardPath = new ArrayList<String>();
        mPathString = new ArrayList<String>();
    }

    /** graph make */
    public void makeGraph(){
        int i, j, id, index;
        for (i = 0; i < BusFinalVars.line.length; i++)
        {
            for (j = 0; j < BusFinalVars.line[i].length - 1; j++)
            {
                id = findIdByKey(BusFinalVars.line[i][j]);
                graph[id].insertItem(findIdByKey(BusFinalVars.line[i][j + 1]));
            }
        }
        for (i = 0; i < BusFinalVars.linex.length; i++)
        {
            for (j = 0; j < BusFinalVars.linex[i].length - 1; j++)
            {
                id = findIdByKey(BusFinalVars.linex[i][j]);
                graph[id].insertItem(findIdByKey(BusFinalVars.linex[i][j + 1]));
            }
        }
    }


    /** add graph detail, line number and other info */
    public void addGraphDetail(){

        int i, j, id, n = 0;
        // add line info
        for (i = 0; i < BusFinalVars.line.length; i++)
        {
            for (j = 0; j < BusFinalVars.line[i].length; j++)
            {
                id = findIdByKey(BusFinalVars.line[i][j]);
                graph[id].setInfo(BusFinalVars.NORMAL);
                graph[id].setfoot(BusFinalVars.NORMAL);
                switch(i){
                    case 0:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE1.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[0]);
                        break;
                    case 1:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE2.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[1]);
                        break;
                    case 2:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE3.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[2]);
                        break;
                    case 3:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE4.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[3]);
                        break;
                    case 4:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE5.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[4]);
                        break;
                    case 5:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE6.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[5]);
                        break;
                    case 6:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE7.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[6]);
                        break;
                    case 7:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE8.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[7]);
                        break;
                    case 8:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE9.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[8]);
                        break;
                    case 9:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE10.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[9]);
                        break;
                    case 10:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE11.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[11]);
                        break;
                    case 11:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE12.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[12]);
                        break;
                    case 12:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE13.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[13]);
                        break;
                    case 13:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE14.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[14]);
                        break;
                    case 14:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE15.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[15]);
                        break;
                    case 15:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE16.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[16]);
                        break;
                    case 16:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE17.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[17]);
                        break;
                    case 17:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE18.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[18]);
                        break;
                    case 18:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE19.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[19]);
                        break;
                    case 19:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE20.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[20]);
                        break;
                    case 20:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE21.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[22]);
                        break;
                    case 21:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE22.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[23]);
                        break;
                    case 22:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE23.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[24]);
                        break;
                    case 23:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE24.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[25]);
                        break;
                    case 24:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE25.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[26]);
                        break;
                    case 25:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE26.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[27]);
                        break;
                    case 26:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE27.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[28]);
                        break;
                    case 27:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE28.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[29]);
                        break;
                    case 28:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE30.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[30]);
                        break;
                    case 29:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE32.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[31]);
                        break;
                    case 30:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE34.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[33]);
                        break;
                    case 31:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE36.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[34]);
                        break;
                    case 32:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE38.getValue());
                        graph[id].insertArrline(BusFinalVars.linename[35]);
                        break;
                }
            }
        }

        for (i = 0; i < BusFinalVars.linex.length; i++)
        {
            for (j = 0; j < BusFinalVars.linex[i].length; j++)
            {
                id = findIdByKey(BusFinalVars.linex[i][j]);
                graph[id].setInfo(BusFinalVars.NORMAL);
                graph[id].setfoot(BusFinalVars.NORMAL);
                switch(i){
                    case 0:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE1.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[0]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[0]);
                        }
                        break;
                    case 1:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE2.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[1]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[1]);
                        }
                        break;
                    case 2:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE3.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[2]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[2]);
                        }
                        break;
                    case 3:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE4.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[3]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[3]);
                        }
                        break;
                    case 4:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE5.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[4]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[4]);
                        }
                        break;
                    case 5:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE6.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[5]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[5]);
                        }
                        break;
                    case 6:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE7.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[6]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[6]);
                        }
                        break;
                    case 7:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE8.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[7]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[7]);
                        }
                        break;
                    case 8:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE9.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[8]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[8]);
                        }
                        break;
                    case 9:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE10.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[10]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[10]);
                        }
                        break;
                    case 10:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE11.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[11]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[11]);
                        }
                        break;
                    case 11:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE12.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[12]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[12]);
                        }
                        break;
                    case 12:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE13.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[13]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[13]);
                        }
                        break;
                    case 13:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE14.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[14]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[14]);
                        }
                        break;
                    case 14:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE15.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[15]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[15]);
                        }
                        break;
                    case 15:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE16.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[16]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[16]);
                        }
                        break;
                    case 16:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE17.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[17]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[17]);
                        }
                        break;
                    case 17:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE18.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[18]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[18]);
                        }
                        break;
                    case 18:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE19.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[19]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[19]);
                        }
                        break;
                    case 19:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE20.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[21]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[21]);
                        }
                        break;
                    case 20:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE21.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[22]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[22]);
                        }
                        break;
                    case 21:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE22.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[23]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[23]);
                        }
                        break;
                    case 22:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE23.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[24]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[24]);
                        }
                        break;
                    case 23:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE24.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[25]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[25]);
                        }
                        break;
                    case 24:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE25.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[26]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[26]);
                        }
                        break;
                    case 25:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE26.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[27]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[27]);
                        }
                        break;
                    case 26:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE27.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[28]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[28]);
                        }
                        break;
                    case 27:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE28.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[29]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[29]);
                        }
                        break;
                    case 28:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE30.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[31]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[31]);
                        }
                        break;
                    case 29:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE32.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[32]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[32]);
                        }
                        break;
                    case 30:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE34.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[33]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[33]);
                        }
                        break;
                    case 31:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE36.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[34]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[34]);
                        }
                        break;
                    case 32:
                        graph[id].addLine(BusFinalVars.LINESINFO.LINE38.getValue());
                        if(graph[id].arrContains(BusFinalVars.linename[35]) == false)
                        {
                            graph[id].insertArrline(BusFinalVars.linename[35]);
                        }
                        break;
                }
            }
        }

        // add transit station info
        for (i = 0; i < BusFinalVars.change_station.length; i++)
        {
            id = findIdByKey(BusFinalVars.change_station[i]);
            graph[id].setInfo(BusFinalVars.TRANSIT);
        }
        for(i = 0;i < BusFinalVars.foot_change.length;i++)
        {
            id = findIdByKey(BusFinalVars.foot_change[i][0]);
            graph[id].setfoot(BusFinalVars.FOOTCHANGE);
        }

        // add metro to bus info
        for(int h = 0;h < BusFinalVars.Metro_to_Bus.length;h++)
        {
            shardPath.add(BusFinalVars.Metro_to_Bus[h][0]);
        }
    }


    /** table init */
    public void initTable(){
        // table为TableEntry类封装的一个一维数组，在初始化定义时未定义其长度，该句代码为其长度的定义
        table = new TableEntry[this.getVertexNum()];
        for (int i = 0; i < table.length; i++)
        {
            table[i] = new TableEntry();
        }
    }


    /** table clear */
    public void clearTable(){
        for (int i = 0; i < table.length; i++)
        {
            table[i].setKnown(false);
            table[i].setDist(BusFinalVars.INFINITE);
            //System.out.println(i + "__" + table[i].getDist()); //这一步表明初始化是全部dist = 65535
            table[i].setPath(BusFinalVars.UNKNOWN);

        /*    table[i].setNextTime(2);
            table[i].setPreTime(1);
            table[i].setAime(10010);  */
        }
    }

    /** set begin vertex by id */
    public boolean setBeginVertex(int id){
        clearTable();
        if (mHashTable1.containsKey(id) == true)
        {
            beginVertex = id;
            beginStation = findStrById(id);
            table[id].setDist(0);
            table[id].setPath(id);
        /*    table[id].setAime(0);
            table[id].timePlus();  */
            return true;
        }
        return false;
    }

    /** set begin vertex by name */
    public boolean setBeginVertex(String name){
        int id;
        clearTable();
        if (mHashTable2.containsKey(name) == true)
        {
            id = findIdByKey(name);
            beginVertex = id;
            beginStation = name;
            table[id].setDist(0);
            table[id].setPath(id);
            return true;
        }
        return false;
    }

    /** get begin vertex in id */
    public int getBeginVertexId(){
        return beginVertex;
    }

    /** get end vertex in name */
    public String getBeginVertexName(){
        return beginStation;
    }

    /** set end vertex by id */
    public boolean setEndVertex(int id){
        if (mHashTable1.containsKey(id) == true)
        {
            endVertex = id;
            endStation = findStrById(id);
            return true;
        }
        return false;
    }

    /** set end vertex by name */
    public boolean setEndVertex(String name){
        if (mHashTable2.containsKey(name) == true)
        {
            endVertex = findIdByKey(name);
            endStation = name;
            return true;
        }
        return false;
    }

    /** get end vertex in id */
    public int getEndVertexId(){
        return endVertex;
    }

    /** get end vertex in name */
    public String getEndVertexName(){
        return endStation;
    }


    /** get Vertex number */
    public int getVertexNum(){
        return vertexNum;
    }

    /** startSearch */
    public void startSearch(){
        int currDist, v, w, i, t, dist;
        for (currDist = 0; currDist < this.getVertexNum() * 7; currDist++)
        {
            for (v = 0; v < this.getVertexNum(); v++)
            {
                if ((table[v].getKnown() == false) && (table[v].getDist() == currDist))
                {
                    table[v].setKnown(true);
                    for (i = 0; i < graph[v].size(); i++)
                    {
                        w = graph[v].getItem(i);

                        if((graph[table[v].getPath()].getLine() & graph[v].getLine() & graph[w].getLine()) != BusFinalVars.CHANGE)
                        {
                            dist = 10;
                        }
                        else dist = 1;

                        if (table[w].getDist() > currDist + dist)
                        {
                            table[w].setDist(currDist + dist);
                            table[w].setPath(v);
                        }
                    }
                    io:
                    if(graph[v].getfoot() == BusFinalVars.FOOTCHANGE)
                    {
                        for(i = 0;i < BusFinalVars.foot_change.length;i++)
                        {
                            if(BusFinalVars.foot_change[i][0] == findStrById(v))
                            {
                                for(int j = 1;j < BusFinalVars.foot_change[i].length;j++)
                                {
                                    w = findIdByKey(BusFinalVars.foot_change[i][j]);
                                    if (table[w].getDist() > currDist + 10)
                                    {
                                        table[w].setDist(currDist + 10);
                                        table[w].setPath(v);
                                        break io;
                                    }
                                }
                            }
                        }  // 当本站与下一站的lineinfo相同，则判定为不换乘，直到出现lineinfo不相等，才去判断是否出现换乘
                    }
                }
            }
        }
    }


    /** find path */
    public void findPath(int id){
        if (table[id].getDist() != 0)
        {
            findPath(table[id].getPath());
        }
        mPath.add(id);
    }

    /** get line info */
    public String getLineInfo(int index){
        String ret = "";
        switch(index)
        {
            case BusFinalVars.LINE1:
                ret += BusFinalVars.linename[0];
                break;
            case BusFinalVars.LINE2:
                ret += BusFinalVars.linename[1];
                break;
            case BusFinalVars.LINE3:
                ret += BusFinalVars.linename[2];
                break;
            case BusFinalVars.LINE4:
                ret += BusFinalVars.linename[3];
                break;
            case BusFinalVars.LINE5:
                ret += BusFinalVars.linename[4];
                break;
            case BusFinalVars.LINE6:
                ret += BusFinalVars.linename[5];
                break;
            case BusFinalVars.LINE7:
                ret += BusFinalVars.linename[6];
                break;
            case BusFinalVars.LINE8:
                ret += BusFinalVars.linename[7];
                break;
            case BusFinalVars.LINE9:
                ret += BusFinalVars.linename[8];
                break;
            case BusFinalVars.LINE10:
                ret += BusFinalVars.linename[9];
                break;
            case BusFinalVars.LINE11:
                ret += BusFinalVars.linename[10];
                break;
            case BusFinalVars.LINE12:
                ret += BusFinalVars.linename[11];
                break;
            case BusFinalVars.LINE13:
                ret += BusFinalVars.linename[12];
                break;
            case BusFinalVars.LINE14:
                ret += BusFinalVars.linename[13];
                break;
            case BusFinalVars.LINE15:
                ret += BusFinalVars.linename[14];
                break;
            case BusFinalVars.LINE16:
                ret += BusFinalVars.linename[15];
                break;
            case BusFinalVars.LINE17:
                ret += BusFinalVars.linename[16];
                break;
            case BusFinalVars.LINE18:
                ret += BusFinalVars.linename[17];
                break;
            case BusFinalVars.LINE19:
                ret += BusFinalVars.linename[18];
                break;
            case BusFinalVars.LINE20:
                ret += BusFinalVars.linename[19];
                break;
            case BusFinalVars.LINE21:
                ret += BusFinalVars.linename[20];
                break;
            case BusFinalVars.LINE22:
                ret += BusFinalVars.linename[21];
                break;
            case BusFinalVars.LINE23:
                ret += BusFinalVars.linename[22];
                break;
            case BusFinalVars.LINE24:
                ret += BusFinalVars.linename[23];
                break;
            case BusFinalVars.LINE25:
                ret += BusFinalVars.linename[24];
                break;
            case BusFinalVars.LINE26:
                ret += BusFinalVars.linename[25];
                break;
            case BusFinalVars.LINE27:
                ret += BusFinalVars.linename[26];
                break;
            case BusFinalVars.LINE28:
                ret += BusFinalVars.linename[27];
                break;
            case BusFinalVars.LINE30:
                ret += BusFinalVars.linename[28];
                break;
            case BusFinalVars.LINE32:
                ret += BusFinalVars.linename[29];
                break;
            case BusFinalVars.LINE34:
                ret += BusFinalVars.linename[30];
                break;
            case BusFinalVars.LINE36:
                ret += BusFinalVars.linename[31];
                break;
            case BusFinalVars.LINE38:
                ret += BusFinalVars.linename[32];
                break;

        }
        return ret;
    }

    /** process path */
    public void makePathPrompt(){
        String ret = "最短路线: ", linenum = "";
        int i, id = beginVertex, previd, nextid, cnt = 0, n = 0;

        // clear path history
        mPath.clear();

        // find the path
        findPath(this.getEndVertexId());

        if(mPath.size() == 1) // beginVertex == endVertex
        {
            ret = "您已经在 " + findStrById(id) + " 了， 无需乘坐地铁";
            mResult = ret;
            return ;
        }

        for (i = 0; i < mPath.size(); i++)
        {
            id = mPath.get(i);
            if (i == 0) // id == beginVertex
            {
                nextid = mPath.get(i + 1);
                linenum = getLineInfo(graph[id].getLine() & graph[nextid].getLine());
                ret += "在 " + findStrById(id) + " 乘坐" + linenum + "(" + findStrById(nextid) + "方向)";
            }
            else if (i == mPath.size() - 1) // id == endVertex
            {
                cnt++;
                ret += "(坐" + cnt + "站)到 " + findStrById(id) + " 下车，共：" + table[id].getDist() + "分钟，途经" + (mPath.size()-1) + "站,换乘" + n + "次";
            }
            else if (graph[id].getInfo() == BusFinalVars.NORMAL)
            {
                cnt++;
            }
            else if (graph[id].getInfo() == BusFinalVars.TRANSIT)
            {
                cnt++;
                if (i + 1 < mPath.size()) // make sure (i + 1) not overflow
                {
                    previd = mPath.get(i - 1);
                    nextid = mPath.get(i + 1);
                    linenum = getLineInfo(graph[id].getLine() & graph[nextid].getLine());

                    if ((graph[previd].getLine() & graph[id].getLine()) != (graph[nextid].getLine() & graph[id].getLine()))
                    {
                        ret += "(坐" + cnt + "站)" + "到 " + findStrById(id) + " 换乘" + linenum + "(" + findStrById(nextid) + "方向)";
                        cnt = 0;
                        n++;
                    }
                }
            }
        }

        mResult = ret;
        for(int p = 0;p < mPath.size();p++){
            mPathString.add(findStrById(mPath.get(p)));
        }
        //System.out.println(mPath.size());
    }

    /** get result */
    public ArrayList<String> getResult(){
        return mPathString;
    }
}

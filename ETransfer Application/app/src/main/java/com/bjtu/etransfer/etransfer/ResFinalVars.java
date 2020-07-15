package com.bjtu.etransfer.etransfer;
final class ResFinalVars{
    public static final int INFINITE = 0xffff;
    public static final int UNKNOWN = -1;
    public static final int TRANSIT = 0;
    public static final int NORMAL = 1;
    public static final int SPECIAL = 100;

    public static enum LINESINFO{
        LINE1(0x01), LINE2(0x02), LINE3(0x04), LINE4(0x08), LINE5(0x10), LINE6(0x20), LINE7(0x40),
        LINE8(0x80), LINE9(0x100), LINE10(0x200), LINE11(0x400), LINE12(0x800),
        LINE13(0x1000), LINE14x(0x2000), LINE14d(0x4000), LINE15(0x8000),LINE16(0x10000),
        LINEBT(0x20000),LINEFS(0x40000), LINECP(0x80000), LINEYZ(0x100000), LINEJC(0x200000);
        private final int value;
        private LINESINFO(int value){
            this.value = value;
        }

        public int getValue(){
            return this.value;
        }
    };

    public static final int LINE1 = 0x01;
    public static final int LINE2 = 0x02;
    public static final int LINE3 = 0x04;
    public static final int LINE4 = 0x08;
    public static final int LINE5 = 0x10;
    public static final int LINE6 = 0x20;
    public static final int LINE7 = 0x40;
    public static final int LINE8 = 0x80;
    public static final int LINE9 = 0x100;
    public static final int LINE10 = 0x200;
    public static final int LINE11 = 0x400;
    public static final int LINE12 = 0x800;
    public static final int LINE13 = 0x1000;
    public static final int LINE14x = 0x2000;
    public static final int LINE14d = 0x4000;
    public static final int LINE15 = 0x8000;
    public static final int LINE16 = 0x10000;
    public static final int LINEBT = 0x20000;
    public static final int LINEFS = 0x40000;
    public static final int LINECP = 0x80000;
    public static final int LINEYZ = 0x100000;
    public static final int LINEJC = 0x200000;



    public static final String[] lines_number = {
            "地铁1号线", "地铁2号线", "地铁3号线", "地铁4号线", "地铁5号线", "地铁6号线", "地铁7号线", "地铁8号线", "地铁9号线",
            "地铁10号线", "地铁11号线", "地铁12号线", "地铁13号线", "地铁14号线(西)", "地铁14号线(东)", "地铁15号线", "地铁16号线",
            "地铁八通线", "地铁房山线", "地铁昌平线", "地铁亦庄线", "机场快轨",
    };

    public static final String line1_stations[] = {
            "苹果园", "古城", "八角游乐园", "八宝山", "玉泉路", "五棵松", "万寿路", "公主坟", "军事博物馆", "木樨地", "南礼士路",
            "复兴门", "西单", "天安门西", "天安门东", "王府井", "东单", "建国门", "永安里", "国贸", "大望路", "四惠", "四惠东",
    };

    public static final String line2_stations[] = {
            "西直门", "车公庄", "阜成门", "复兴门", "长椿街", "宣武门", "和平门", "前门", "崇文门", "北京站", "建国门",
            "朝阳门", "东四十条", "东直门", "雍和宫", "安定门", "鼓楼大街", "积水潭", "西直门"
    };

    public static final String line3_stations[] = {

    };

    public static final String line4_stations[] = {
            "安河桥北", "北宫门", "西苑", "圆明园", "北京大学东门", "中关村", "海淀黄庄", "人民大学", "魏公村", "国家图书馆", "动物园",
            "西直门", "新街口", "平安里", "西四", "灵境胡同", "西单", "宣武门", "菜市口", "陶然亭", "北京南站", "马家堡", "角门西",
            "公益西桥", "新宫", "西红门", "高米店北", "高米店南", "枣园", "清源路", "黄村西大街", "黄村火车站", "义和庄", "生物医药基地", "天宫院"
    };

    public static final String line5_stations[] = {
            "天通苑北", "天通苑", "天通苑南", "立水桥", "立水桥南", "北苑路北", "大屯路东", "惠新西街北口", "惠新西街南口", "和平西桥",
            "和平里北街", "雍和宫", "北新桥", "张自忠路", "东四", "灯市口", "东单", "崇文门", "磁器口", "天坛东门", "蒲黄榆", "刘家窑", "宋家庄",
    };

    public static final String line6_stations[] = {
            "海淀五路居", "慈寿寺", "花园桥", "白石桥南", "车公庄西", "车公庄", "平安里", "北海北", "南锣鼓巷", "东四", "朝阳门", "东大桥",
            "呼家楼", "金台路", "十里堡", "青年路", "褡裢坡", "黄渠", "常营", "草房", "物资学院路", "通州北关", "北运河西", "郝家府", "东夏园", "潞城",
    };

    public static final String line7_stations[] = {
            "北京西站", "湾子", "达官营", "广安门内", "菜市口", "虎坊桥", "珠市口", "桥湾", "磁器口", "广渠门内", "广渠门外", "九龙山",
            "大郊亭", "百子湾", "化工", "南楼梓庄", "欢乐谷景区", "双合", "焦化厂",
    };

    public static final String line8_stations[] = {
            "朱辛庄", "育知路", "平西府", "回龙观东大街", "霍营", "育新", "西小口", "永泰庄", "林萃桥", "森林公园南门", "奥林匹克公园",
            "奥体中心", "北土城", "安华桥", "安德里北街", "鼓楼大街", "什刹海", "南锣鼓巷",
    };

    public static final String line9_stations[] = {
            "国家图书馆", "白石桥南", "白堆子", "军事博物馆", "北京西站", "六里桥东", "六里桥", "七里庄", "丰台东大街", "丰台南路",
            "科怡路", "丰台科技园", "郭公庄",
    };

    public static final String line10_stations[] = {
            "巴沟", "火器营", "长春桥", "车道沟", "慈寿寺", "西钓鱼台", "公主坟", "莲花桥", "六里桥", "西局",
            "泥洼", "丰台站", "首经贸", "纪家庙", "草桥", "角门西", "角门东", "大红门", "石榴庄", "宋家庄", "成寿寺", "分钟寺",
            "十里河", "潘家园", "劲松", "双井", "国贸", "金台夕照", "呼家楼", "团结湖", "农业展览馆", "亮马桥", "三元桥", "太阳宫",
            "芍药居", "惠新西街南口", "安贞门", "北土城", "健德门", "牡丹园", "西土城", "知春路", "知春里", "海淀黄庄","苏州街","巴沟"
    };

    public static final String line11_stations[] = {

    };

    public static final String line12_stations[] = {

    };

    public static final String line13_stations[] = {
            "西直门", "大钟寺", "知春路", "五道口", "上地", "西二旗", "龙泽", "回龙观", "霍营", "立水桥", "北苑", "望京西", "芍药居",
            "光熙门", "柳芳", "东直门",
    };

    public static final String line14x_stations[] = {
            "张郭庄", "园博园", "大瓦窑", "郭庄子", "大井", "七里庄", "西局",
    };

    public static final String line14d_stations[] = {
            "善各庄", "来广营", "东湖渠", "望京", "阜通", "望京南", "将台", "东风北桥", "枣营", "朝阳公园", "金台路", "大望路", "九龙山",
            "北工大西门", "十里河", "方庄", "蒲黄榆", "景泰", "永定门外", "北京南站",
    };

    public static final String line15_stations[] = {
            "清华东路西口", "六道口", "北沙滩", "奥林匹克公园", "安立路", "大屯路东", "关庄", "望京西", "望京", "望京东", "崔各庄", "马泉营",
            "孙河", "国展", "花梨坎", "后沙峪", "南法信", "石门", "顺义", "俸伯",
    };

    public static final String line16_stations[] = {
            "北安河", "温阳路", "稻香湖路", "屯佃", "永丰", "永丰南", "西北旺", "马连洼", "西苑",
    };

    public static final String btline_stations[] = {
            "四惠东", "高碑店", "传媒大学", "双桥", "管庄", "八里桥", "通州北苑", "果园", "九棵树", "梨园", "临河里", "土桥",
    };

    public static final String fsline_stations[] = {
            "苏庄", "良乡南关", "良乡大学城西", "良乡大学城", "良乡大学城北", "广阳城", "篱笆房", "长阳", "稻田", "大葆台", "郭公庄",
    };

    public static final String cpline_stations[] = {
            "昌平西山口", "十三陵景区", "昌平", "昌平东关", "北邵洼", "南邵", "沙河高教园", "沙河", "巩华城", "朱辛庄", "生命科学园", "西二旗",
    };
    public static final String yzline_stations[] = {
            "宋家庄", "肖村", "小红门", "旧宫", "亦庄桥", "亦庄文化园", "万源街", "荣京东街", "荣昌东街", "同济南路", "经海路", "次渠南", "次渠",
    };
    public static final String jcline_stations[] = {
            "东直门", "三元桥", "3号航站楼", "2号航站楼", "三元桥",
    };

    public static final String[] line1_time_s = {
            "4","3","3","2","3","3","2","3","2","2","3","2","2","2","2","2","3","2","2","3","3","4",
    };
    public static final String[] line2_time_s = {
            "2","2","3","3","1","4","2","2","3","2","3","3","2","2","2","2","3","2",
    };
    public static final String[] line3_time_s = {

    };
    public static final String[] line4_time_s = {
            "1","3","2","3","2","3","2","2","2","3","3","1","2","2","2","3","2","1","3","2","3","2","2","4","5","3","2","2","2","2","2","3","3","4"
    };
    public static final String[] line5_time_s = {
            "2","2","2","3","2","4","2","3","2","2","2","2","2","2","2","2","2","2","2","3","2","4"
    };
    public static final String[] line6_time_s = {
            "2","3","2","3","2","3","2","3","4","2","3","2","3","2","3","4","2","3","3","3","3","4","4","3","3"
    };
    public static final String[] line7_time_s = {
            "2","2","3","2","2","2","2","3","2","2","4","2","2","2","2","3","4","3",
    };
    public static final String[] line8_time_s = {
            "3","3","3","3","3","2","3","4","4","2","3","2","2","2","3","2","3",
    };
    public static final String[] line9_time_s = {
            "2","2","4","3","3","3","4","2","2","2","2","4"
    };
    public static final String[] line10_time_s = {
            "3","2","2","2","3","2","3","2","4","2","2","3","4","2","2","3","2","3","2","2","3","2","3","2","2","2","3","1","2","2","2","2","3","2",
            "3","3","2","2","2","2","2","3","1","3","1",
    };
    public static final String[] line11_time_s = {

    };
    public static final String[] line12_time_s = {

    };
    public static final String[] line13_time_s = {
            "4","3","2","6","4","5","2","3","6","3","7","3","2","2","4",
    };
    public static final String[] line14x_time_s = {
            "1","5","3","3","2","3",
    };
    public static final String[] line14d_time_s = {
            "2","2","2","2","2","4","3","3","2","2","2","3","3","4","4","3","2","2","4",
    };
    public static final String[] line15_time_s = {
            "2","2","3","2","3","2","4","3","3","3","3","4","3","3","4","4","4","2","5",
    };
    public static final String[] line16_time_s = {
            "3","4","3","4","3","4","3","7"
    };
    public static final String[] linebt_time_s = {
            "2","3","3","3","2","3","3","3","2","2","2","3",
    };
    public static final String[] linefs_time_s = {
            "3","2","3","2","3","3","3","5","6","2",
    };
    public static final String[] linecp_time_s = {
            "2","4","3","3","3","5","3","3","5","3","8",
    };
    public static final String[] lineyz_time_s = {
            "4","2","3","3","2","2","3","2","3","3","3","4",
    };

    public static final String[] linejc_time_s = {
            "4", "21", "11","33"
    };
    //@@@@@@@@@@@@@@@@@@@@这里是分割线
    public static final String line1_time_n[] = {
            "3","3","2","2","3","2","2","2","2","2","3","1","3","2","3","2","3","3","2","3","3","5"
    };
    public static final String line2_time_n[] = {
            "4","3","2","2","4","2","2","3","2","3","2","3","1","2","3","3","2","3"
    };
    public static final String line3_time_n[] = {

    };
    public static final String line4_time_n[] = {
            "2","3","3","2","2","2","2","2","3","6","5","1","2","2","3","2","3","2","1","2","2","2","3","2","2","3","2","3","1","2","2","3","2","4"
    };
    public static final String line5_time_n[] = {
            "4","3","3","2","3","3","2","3","2","2","3","2","2","2","2","2","3","2","2","3","3","3",
    };
    public static final String line6_time_n[] = {
            "2","2","4","4","4","3","3","4","3","4","3","3","2","2","3","3","4","2","3","2","2","3","2","3","3",
    };
    public static final String line7_time_n[] = {
            "2","4","2","2","2","2","2","4","3","2","2","2","2","2","3","2","2","3",
    };
    public static final String line8_time_n[] = {
            "2","2","2","3","2","2","4","2","3","4","2","3","3","3","3","3","5",
    };
    public static final String line9_time_n[] = {
            "2","2","2","2","3","3","3","3","3","3","2","3",
    };
    public static final String line10_time_n[] = {
            "2","2","2","2","2","2","2","2","3","2","2","3","2","3","2","2","2","2","2","2","3","2","2","2","2",
            "2","3","2","3","2","2","3","2","4","3","2","3","2","4","2","3","3","2","2","2",
    };
    public static final String line11_time_n[] = {

    };
    public static final String line12_time_n[] = {

    };
    public static final String line13_time_n[] = {
            "2","2","3","3","7","4","5","3","3","5","4","6","3","3","5",
    };
    public static final String line14x_time_n[] = {
            "1","2","3","3","4","3",
    };
    public static final String line14d_time_n[] = {
            "3","2","3","2","3","4","4","3","3","2","3","3","2","4","2","2","3","2","4",
    };
    public static final String line15_time_n[] = {
            "3","2","4","5","3","3","4","5","3","3","3","3","3","3","2","3","2","3","3",
    };
    public static final String line16_time_n[] = {
            "6","3","4","3","4","3","4","4",
    };
    public static final String linebt_time_n[] = {
            "1","3","2","2","3","2","4","3","3","3","2","4",
    };
    public static final String linefs_time_n[] = {
            "4","6","5","3","3","3","2","3","2","2"
    };
    public static final String linecp_time_n[] = {
            "5","4","4","3","3","5","3","3","3","4","3",
    };
    public static final String lineyz_time_n[] = {
            "2","3","3","3","2","2","3","2","2","4","2","4",
    };
    public static final String linejc_time_n[] = {
             "100", "100", "100", "4"
    };

    public static final String line1_endTime[] = {
            "22:55:00", "22:59:00", "23:02:00", "23:05:00", "23:07:00", "23:10:00", "23:13:00", "23:15:00", "23:18:00", "23:20:00", "23:22:00", "23:25:00", "23:27:00", "23:29:00", "23:31:00",
            "23:33:00", "23:35:00", "23:28:00", "23:40:00", "23:42:00", "23:45:00", "23:48:00",
    };
    public static final String line2_endTime[] = {
            "23:00:00", "23:02:00", "23:04:00", "23:07:00", "23:09:00", "23:11:00", "23:13:00", "23:15:00", "23:18:00", "23:21:00", "23:23:00", "23:26:00", "23:28:00", "23:30:00", "23:34:00",
            "23:35:00", "23:38:00", "22:56:00"
    };
    public static final String line3_endTime[] = {

    };
    public static final String line4_endTime[] = {
            "22:45:00", "22:46:00", "22:49:00", "22:51:00", "22:54:00", "22:55:00", "22:58:00", "23:00:00", "23:02:00", "23:04:00", "23:07:00", "23:10:00", "23:11:00", "23:13:00", "23:15:00",
            "23:17:00", "23:20:00", "23:22:00", "23:23:00", "23:26:00", "23:28:00", "23:31:00", "23:33:00", "23:10:00", "23:14:00", "23:19:00", "23:22:00", "23:24:00", "23:26:00", "23:28:00",
            "23:30:00", "23:32:00", "23:35:00", "23:38:00",
    };
    public static final String line5_endTime[] = {
            "22:48:00", "22:50:00", "22:52:00", "22:54:00", "22:57:00", "22:59:00", "23:03:00", "23:05:00", "23:08:00", "23:10:00", "23:12:00", "23:14:00", "23:16:00", "23:18:00", "23:20:00",
            "23:22:00", "23:24:00", "23:26:00", "23:28:00", "23:30:00", "23:33:00", "23:35:00",
    };
    public static final String line6_endTime[] = {
            "23:19:00", "23:21:00", "23:24:00", "23:26:00", "23:29:00", "23:31:00", "23:34:00", "23:36:00", "23:39:00", "23:43:00", "23:45:00", "23:48:00", "23:50:00", "23:53:00", "23:55:00",
            "23:58:00", "24:02:00", "24:04:00", "24:07:00", "23:31:00", "23:34:00", "23:37:00", "23:41:00", "23:45:00", "23:48:00",
    };
    public static final String line7_endTime[] = {
            "23:15:00", "23:17:00", "23:19:00", "23:22:00", "23:24:00", "23:26:00", "23:28:00", "23:30:00", "23:33:00", "23:35:00", "23:37:00", "23:41:00", "23:43:00", "23:45:00", "23:47:00",
            "23:49:00", "23:52:00", "23:56:00"
    };
    public static final String line8_endTime[] = {
            "22:05:00", "22:08:00", "22:11:00", "22:14:00", "22:17:00", "22:20:00", "22:22:00", "22:25:00", "22:29:00", "22:33:00", "22:35:00", "22:38:00", "22:40:00", "22:42:00", "22:44:00",
            "22:47:00", "22:49:00"
    };
    public static final String line9_endTime[] = {
            "23:19:00", "23:21:00", "23:23:00", "23:27:00", "23:30:00", "23:32:00", "23:35:00", "23:39:00", "23:41:00", "23:43:00", "23:45:00", "23:47:00",
    };
    public static final String line10_endTime[] = {
            "24:20:00", "24:22:00", "24:24:00", "22:41:00", "22:44:00", "22:46:00", "22:49:00", "22:51:00", "22:55:00", "22:57:00", "22:59:00", "23:02:00", "23:06:00", "23:08:00", "23:10:00",
            "23:14:00", "23:16:00", "23:18:00", "23:20:00", "23:22:00", "23:25:00", "23:27:00", "23:30:00", "23:32:00", "23:34:00", "23:36:00", "23:39:00", "23:41:00", "23:43:00", "23:45:00",
            "23:46:00", "23:48:00", "23:51:00", "23:54:00", "23:56:00", "23:59:00", "24:01:00", "24:03:00", "24:05:00", "24:07:00", "24:10:00", "24:12:00", "24:14:00", "24:16:00", "24:18:00",
    };
    public static final String line13_endTime[] = {
            "23:45:00", "23:49:00", "23:52:00", "23:54:00", "24:00:00", "24:04:00", "24:09:00", "24:11:00", "23:11:00", "23:17:00", "23:20:00", "23:27:00", "23:30:00", "23:33:00", "23:35:00"
    };
    public static final String line14x_endTime[] = {
            "22:10:00", "22:11:00", "22:16:00", "22:19:00", "22:22:00", "22:24:00"
    };
    public static final String line14d_endTime[] = {
            "22:30:00", "22:32:00", "22:34:00", "22:36:00", "22:38:00", "22:40:00", "22:44:00", "22:47:00", "22:50:00", "22:52:00", "22:54:00", "22:57:00", "23:00:00", "23:04:00", "23:08:00",
            "23:11:00", "23:14:00", "23:16:00", "23:18:00"
    };
    public static final String line15_endTime[] = {
            "23:15:00", "23:17:00", "23:19:00", "23:22:00", "23:24:00", "23:27:00", "23:29:00", "23:33:00", "23:36:00", "23:39:00", "23:42:00", "23:45:00", "23:49:00", "23:52:00", "23:55:00",
            "23:59:00", "24:03:00", "24:07:00", "24:09:00"
    };
    public static final String line16_endTime[] = {
            "22:30:00", "22:33:00", "22:37:00", "22:40:00", "22:44:00", "22:47:00", "22:51:00", "22:54:00",
    };
    public static final String linebt_endTime[] = {
            "23:24:00", "23:27:00", "23:30:00", "23:33:00", "23:35:00", "23:38:00", "23:41:00", "23:44:00", "23:46:00", "23:48:00", "23:50:00"
    };
    public static final String linefs_endTime[] = {
            "23:00:00", "23:02:00", "23:08:00", "23:13:00", "23:16:00", "23:19:00", "23:22:00", "23:24:00", "23:27:00", "23:29:00"
    };
    public static final String linecp_endTime[] = {
            "23:05:00", "23:07:00", "23:11:00", "23:14:00", "23:17:00", "23:20:00", "23:25:00", "23:28:00", "23:31:00", "22:51:00", "22:54:00"
    };
    public static final String lineyz_endTime[] = {
            "23:02:00", "23:06:00", "23:08:00", "23:11:00", "23:14:00", "23:16:00", "23:18:00", "23:21:00", "23:23:00", "23:26:00", "23:29:00", "23:32:00"
    };
    public static final String linejc_endTime[] = {
            "18:00:00", "18:00:00", "18:00:00", "18:00:00"
    };



    public static final String line1_endTime_s[] = {
            "23:15:00", "23:18:00", "23:21:00", "23:23:00", "23:25:00", "23:28:00", "23:30:00", "23:32:00", "23:34:00", "23:36:00", "23:38:00", "23:41:00", "23:42:00", "23:45:00", "23:47:00",
            "23:50:00", "23:52:00", "23:55:00", "23:58:00", "24:00:00", "24:03:00", "24:06:00",
    };
    public static final String line2_endTime_s[] = {
            "22:42:00", "22:45:00", "22:48:00", "22:50:00", "22:52:00", "22:56:00", "22:58:00", "23:00:00", "23:03:00", "23:05:00", "23:08:00", "23:10:00", "23:13:00", "23:14:00", "23:16:00",
            "23:19:00", "23:22:00", "23:24:00"
    };
    public static final String line3_endTime_s[] = {

    };
    public static final String line4_endTime_s[] = {
            "22:38:00", "22:40:00", "22:43:00", "22:46:00", "22:48:00", "22:50:00", "22:52:00", "22:54:00", "22:56:00", "22:59:00", "23:05:00", "23:10:00", "23:11:00", "23:13:00", "23:15:00",
            "23:18:00", "23:20:00", "23:23:00", "23:25:00", "23:26:00", "23:28:00", "23:30:00", "23:32:00", "23:35:00", "23:37:00", "23:39:00", "23:42:00", "23:44:00", "23:47:00", "23:48:00",
            "23:50:00", "23:52:00", "23:55:00", "23:57:00"
    };
    public static final String line5_endTime_s[] = {
            "23:11:00", "23:13:00", "23:15:00", "23:18:00", "23:20:00", "23:22:00", "23:24:00", "23:26:00", "23:28:00", "23:30:00", "23:32:00", "23:34:00", "23:36:00", "23:38:00", "23:41:00",
            "23:43:00", "23:45:00", "23:49:00", "23:51:00", "23:54:00", "23:57:00", "23:58:00",
    };
    public static final String line6_endTime_s[] = {
            "22:49:00", "22:51:00", "22:53:00", "22:57:00", "23:01:00", "23:05:00", "23:08:00", "23:10:00", "23:13:00", "23:15:00", "23:19:00", "23:22:00", "23:25:00", "23:27:00", "23:29:00",
            "23:32:00", "23:35:00", "23:39:00", "23:41:00", "23:44:00", "23:46:00", "23:48:00", "23:51:00", "23:53:00", "23:56:00",
    };
    public static final String line7_endTime_s[] = {
            "22:25:00", "22:27:00", "22:31:00", "22:33:00", "22:35:00", "22:37:00", "22:39:00", "22:41:00", "22:45:00", "22:48:00", "22:50:00", "22:52:00", "22:54:00", "22:56:00", "22:58:00",
            "23:01:00", "23:03:00", "23:05:00"
    };
    public static final String line8_endTime_s[] = {
            "23:00:00", "23:02:00", "23:04:00", "23:06:00", "23:09:00", "23:11:00", "23:13:00", "23:17:00", "23:19:00", "23:22:00", "23:26:00", "23:28:00", "23:31:00", "23:34:00", "23:37:00",
            "23:40:00", "23:43:00"
    };

    public static final String line9_endTime_s[] = {
            "22:40:00", "22:42:00", "22:44:00", "22:46:00", "22:48:00",  "22:51:00", "22:54:00", "22:57:00", "23:00:00", "23:03:00", "23:06:00", "23:08:00"
    };
    public static final String line10_endTime_s[] = {
            "22:55:00", "22:58:00", "23:00:00", "23:01:00", "23:04:00", "23:06:00", "23:08:00", "23:10:00", "23:12:00", "23:14:00", "23:17:00", "23:20:00", "23:21:00", "23:24:00", "23:27:00",
            "23:29:00", "23:30:00", "23:33:00", "23:34:00", "23:37:00", "23:39:00", "23:41:00", "23:43:00", "23:45:00", "23:48:00", "23:26:00", "23:29:00", "23:31:00", "23:33:00", "23:35:00",
            "23:37:00", "23:41:00", "23:43:00", "23:46:00", "23:49:00", "23:51:00", "23:53:00", "23:56:00", "23:59:00", "24:02:00", "24:05:00", "24:07:00", "24:09:00", "24:12:00", "24:14:00",
    };
    public static final String line13_endTime_s[] = {
            "23:45:00", "23:49:00", "23:52:00", "23:54:00", "24:00:00", "24:04:00", "24:09:00", "24:11:00", "23:11:00", "23:14:00", "23:19:00", "23:23:00", "23:29:00", "23:32:00", "23:34:00"
    };
    public static final String line14x_endTime_s[] = {
            "22:30:00", "22:31:00", "22:33:00", "22:36:00", "22:19:00", "22:23:00"
    };
    public static final String line14d_endTime_s[] = {
            "22:40:00", "22:43:00", "22:45:00", "22:48:00", "22:50:00", "22:53:00", "22:57:00", "23:01:00", "23:04:00", "23:07:00", "23:09:00", "23:12:00", "23:15:00", "23:17:00", "23:21:00",
            "23:23:00", "23:25:00", "23:28:00", "23:30:00"
    };
    public static final String line15_endTime_s[] = {
            "23:18:00", "23:21:00", "23:23:00", "23:27:00", "23:32:00", "23:35:00", "23:38:00", "23:42:00", "22:40:00", "22:43:00", "22:46:00", "22:49:00", "22:52:00", "22:55:00", "22:58:00",
            "23:00:00", "23:03:00", "23:05:00", "23:08:00"
    };
    public static final String line16_endTime_s[] = {
            "22:55:00", "23:01:00", "23:04:00", "23:08:00", "23:11:00", "23:15:00", "23:18:00", "23:22:00"
    };
    public static final String linebt_endTime_s[] = {
            "22:42:00", "22:43:00", "22:46:00", "22:48:00", "22:50:00", "22:53:00", "22:55:00", "22:59:00", "23:02:00", "23:05:00", "23:08:00", "23:10:00"
    };
    public static final String linefs_endTime_s[] = {
            "23:00:00", "23:02:00", "23:08:00", "23:13:00", "23:16:00", "23:19:00", "23:22:00", "23:24:00", "23:27:00", "23:29:00"
    };
    public static final String linecp_endTime_s[] = {
            "23:05:00", "23:10:00", "23:14:00", "23:18:00", "23:21:00", "23:24:00", "23:29:00", "23:32:00", "23:35:00", "23:38:00", "23:42:00"
    };
    public static final String lineyz_endTime_s[] = {
            "22:25:00", "22:27:00", "22:30:00", "22:33:00", "22:36:00", "22:38:00", "22:40:00", "22:43:00", "22:45:00", "22:47:00", "22:51:00", "22:53:00"
    };
    public static final String linejc_endTime_s[] = {
            "18:00:00", "18:00:00", "18:00:00", "18:00:00",
    };

    public static final String line1_length[] = {
            //苹果园-四惠东 除机场线外上下行公里数一致
            "2606","1921","1953","1479","1810","1778","1313","1172","1166","1291","424","1590","1217","925","852","774","1230","1377","790","1385","1673","1714",
    };
    public static final String line2_length[] = {
            //西直门-车公庄，车公庄-阜成门，……，积水潭-西直门
            "909","960","1832","1234","929","851","1171","1634","1023","945","1763","1027","824","2228","794","1237","1766","1899",
    };
    public static final String line3_length[] = {
            //
    };
    public static final String line4_length[] = {
            //安河桥北-天宫院
            "1363","1251","1672","1295","887","900","1063","1051","1658","1517","1441","1025","1100","1100","869","1011","815","1152","1200","1643","1480","827","989",
            "2798","5102","1810","1128","1096","1200","1214","987","2035","2918","1811",
    };
    public static final String line5_length[] = {
            //天通苑北-宋家庄
            "939","965","1544","1305","1286","3000","1838","1122","1025","1059","1151","866","791","1016","848","945","821","876","1183","1900","905","1670",
    };
    public static final String line6_length[] = {
            //五路-潞城
            "1508","1431","1166","1664","887","1443","1321","1349","1937","1399","1668","845","1450","2036","1282","3999","1238","1854",
            "1405","2115","2557","3011"/*1468+1543*/,"2528"/*1599+929*/,"1346","1194",
    };
    public static final String line7_length[] = {
            //西客站-焦化厂
            "935","734","1874","1374","885","1205","869","1016","1138","1332","2552"/*1241+1331*/,"781","865","903","1464","906","2983"/*1679+1304*/,"1021",
    };
    public static final String line8_length[] = {
            //朱辛庄-锣鼓巷
            "2318","1985","2056","1114","1894","1543","1041","2553","2555","1016","1667","900","1018","1274","1083","1188","902",
    };
    public static final String line9_length[] = {
            //国图-郭公庄
            "1096","943","1912","1398","1170","1309","1778","1325","1585","980","788","1347",
    };
    public static final String line10_length[] = {

            "1495","961","1205","1590","1214","2386","1016","2392","1584","749","954","1717","1143","1547","1688","1254","1130","1244","1269",
            "1677","1058","1804","1097","1021","1006","1759","835","734","1149",
            "853","914","1506","1759","1003","1712","982","1020","1100","973","1330","1101","1058","975","950","1110",
    };
    public static final String line13_length[] = {
            //西直门-东直门
            "2839","1206","1829","4866","2538","3623","1423","2110","4785","2272","6720","2152","1110","1135","1769",
    };
    public static final String line14x_length[] = {
            //张郭庄-西局
            "1345","4073","1236","2044","1579","845",
    };
    public static final String line14d_length[] = {

            "1364","1100","1283","903","1168","676","1171","1600","2173","1221","1085","1602","1780","2025","2423","1618","1486","1025","1119","1950",
    };
    public static final String line15_length[] = {
            //清华东-俸伯
            "1144","1337","1999","1368","938","1087","2071","1758","1652","2295","2008","3309","3386","1615","3354","4576","2712","1331","2441",
    };
    public static final String line16_length[] = {

            "2441","2386","1964","3999","1583","2798","2367","5440",
    };

    public static final String btline_length[] = {
            //四惠-土桥
            "1715","1375","2002","1894","1912","1763","1700","1465","990","1225","1257","776",
    };

    public static final String fsline_length[] = {
            "1330","1332","1738","1188","2003","1474","2150","4041","6646","1405",
    };

    public static final String cpline_length[] = {
            //西山口-西二旗
            "1213","3508","2433","1683","1958","5357","1964","2025","3799","2367","5440",
    };
    public static final String yzline_length[] = {
            //宋家庄-次渠
            "2631","1275","2366","1982","993","1728","1090","1355","2337","2301","2055","1281",
    };
    public static final String jcline_length[] = {
            //东直门-三元桥-T3-T2-三元桥-东直门
            "2999","18329","7239","20619","2999",
    };

    public static final String[][] length = {
            line1_length,
            line2_length,
            //      line3_length,
            line4_length,
            line5_length,
            line6_length,
            line7_length,
            line8_length,
            line9_length,
            line10_length,
            //      line11_length,
            //      line12_length,
            line13_length,
            line14x_length,
            line14d_length,
            line15_length,
            line16_length,
            btline_length,
            fsline_length,
            cpline_length,
            yzline_length,
            jcline_length,
    };

    public static final String[][] lines = {
            line1_stations,
            line2_stations,
            //      line3_stations,
            line4_stations,
            line5_stations,
            line6_stations,
            line7_stations,
            line8_stations,
            line9_stations,
            line10_stations,
            //      line11_stations,
            //      line12_stations,
            line13_stations,
            line14x_stations,
            line14d_stations,
            line15_stations,
            line16_stations,
            btline_stations,
            fsline_stations,
            cpline_stations,
            yzline_stations,
            jcline_stations,
    };

    public static final String[][] times_s = {
            line1_time_s,
            line2_time_s,
            //line3_time_s,
            line4_time_s,
            line5_time_s,
            line6_time_s,
            line7_time_s,
            line8_time_s,
            line9_time_s,
            line10_time_s,
            //line11_time_s,
            //line12_time_s,
            line13_time_s,
            line14x_time_s,
            line14d_time_s,
            line15_time_s,
            line16_time_s,
            linebt_time_s,
            linefs_time_s,
            linecp_time_s,
            lineyz_time_s,
            linejc_time_s,
    };

    public static final String[][] times_n = {
            line1_time_n,
            line2_time_n,
            //line3_time_n,
            line4_time_n,
            line5_time_n,
            line6_time_n,
            line7_time_n,
            line8_time_n,
            line9_time_n,
            line10_time_n,
            //line11_time_n,
            //line12_time_n,
            line13_time_n,
            line14x_time_n,
            line14d_time_n,
            line15_time_n,
            line16_time_n,
            linebt_time_n,
            linefs_time_n,
            linecp_time_n,
            lineyz_time_n,
            linejc_time_n,
    };
    public static final String[][] Endtime = {
            line1_endTime,
            line2_endTime,
            //line3_endTime,
            line4_endTime,
            line5_endTime,
            line6_endTime,
            line7_endTime,
            line8_endTime,
            line9_endTime,
            line10_endTime,
            line13_endTime,
            line14x_endTime,
            line14d_endTime,
            line15_endTime,
            line16_endTime,
            linebt_endTime,
            linefs_endTime,
            linecp_endTime,
            lineyz_endTime,
            linejc_endTime,
    };
    public static final String[][] Endtime_s = {
            line1_endTime_s,
            line2_endTime_s,
            //line3_endTime_s,
            line4_endTime_s,
            line5_endTime_s,
            line6_endTime_s,
            line7_endTime_s,
            line8_endTime_s,
            line9_endTime_s,
            line10_endTime_s,
            line13_endTime_s,
            line14x_endTime_s,
            line14d_endTime_s,
            line15_endTime_s,
            line16_endTime_s,
            linebt_endTime_s,
            linefs_endTime_s,
            linecp_endTime_s,
            lineyz_endTime_s,
            linejc_endTime_s,
    };

    public static final String[] transit_stations = {
            "公主坟", "军事博物馆", "复兴门", "西单", "东单", "建国门", "国贸", "大望路", "四惠东", "西直门", "车公庄",
            "宣武门", "崇文门", "朝阳门", "东直门", "雍和宫", "鼓楼大街", "西苑", "海淀黄庄", "国家图书馆", "平安里",
            "菜市口", "北京南站", "角门西", "立水桥", "大屯路东", "惠新西街南口", "东四", "磁器口", "蒲黄榆", "宋家庄", "慈寿寺",
            "白石桥南", "南锣鼓巷", "呼家楼", "金台路", "北京西站", "九龙山", "朱辛庄", "霍营", "奥林匹克公园", "北土城", "六里桥",
            "七里庄", "郭公庄", "西局", "十里河", "芍药居", "知春路", "西二旗", "望京西", "望京", "三元桥"
    };

    public static final int[] change_time = {
            4,6,3,5,5,3,5,5,3,3,6,7,4,3,4,4,5,4,3,4,4,4,3,2,5,3,3,3,3,6,2,5,3,3,2,3,3,3,2,4,2,3,1,3,2,4,5,3,2,3,2,3,4,4,5,2,6,4,3,
    };
}
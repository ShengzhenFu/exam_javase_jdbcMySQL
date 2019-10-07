CREATE TABLE `t_answers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `answer` char(1) DEFAULT NULL,
  `i_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8


CREATE TABLE `t_items` (
  `id` int(11) DEFAULT NULL,
  `question` varchar(255) DEFAULT NULL,
  `optionA` varchar(255) DEFAULT NULL,
  `optionB` varchar(255) DEFAULT NULL,
  `optionC` varchar(255) DEFAULT NULL,
  `optionD` varchar(255) DEFAULT NULL,
  `answer` char(1) DEFAULT NULL,
  `score` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 

INSERT INTO t_items VALUES (1,'第1题 Java语言是哪年发明的?','A. 1991','B. 1994','C. 1996','D. 1999','A',10),
(2,'第2题 下列关于JDK、JRE和JVM的描述,哪项正确?','A. JDK中包含了JRE,JVM中包含了JRE','B. JDK中包含了JRE,JRE中包含了JVM','C. JRE中包含了JDK,JVM中包含了JRE','D. JRE中包含了JDK,JDK中包含了JVM','B',10),
(3,'第3题 用于生成Java文档的JDK工具是?','A. javac ','B. jdb','C. javadoc','D. junit','C',10),
(4,'第4题 使用JDK工具生成的Java文档的文件格式是?','A. XML格式','B. HTML格式','C. 二进制格式','D. 自定义格式','B',10),
(5,'第5题 以下关于支持Java运行平台的叙述,哪项错误?','B. Java可在Solaris平台上运行','B. Java可在Windows平台上运行','C. Java语言与平台无关。Java程序的运行结果与操作系统无关','D. Java语言与平台无关。Java程序的运行结果依赖于操作系统','D',10),
(6,'第6题 JVM在执行一个Java类时,大致采用以下过程?','D. 执行类中的代码->装载类->校验类','B. 校验类->装载类->执行类中的代码','C. 装载类->执行类中的代码->校验类 ','D. 执行类中的代码->装载类->校验类','A',10),
(7,'第7题 用于编译Java源文件的JDK工具是?','A. javac ','B. jdb','C. java','D. junit','A',10),
(8,'第8题 Java程序的跨平台特征是由以下哪项技术实现的?','A. 系统硬件','B. JVM','C. Java编译器','D. 操作系统','B',10),
(9,'第9题 下列有关类、对象和实例的叙述,正确的是哪一项?','A. 类就是对象,对象就是类,实例是对象的另一个名称,三者没有差别','B. 类是对象的抽象,对象
是类的具体化,实例是对象的另一个名称','C. 对象是类的抽象,类是对象的具体化,实例是对象的另一个名称','D. 类是对象的抽象,对象是
类的具体化,实例是类的另一个名称','B',10),
(10,'第10题 Java源文件的后缀名是?','A. .class','B. .c ','C. .java','D. .txt','C',10);

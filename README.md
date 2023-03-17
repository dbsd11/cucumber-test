**自动测试服务web**

使用：

1. 全量测试：mvn clean verify
2. 增量测试：mvn clean jar:test-jar && java -cp target/*-tests.jar org.junit.runner.JUnitCore xxxClass

Sources:

* rss: https://raw.githubusercontent.com/feeddd/feeds/master/feeds_all_rss.txt

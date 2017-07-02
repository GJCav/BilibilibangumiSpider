# BilibilibangumiSpider
 一个用于统计B站番剧索引中所有番剧的信息的小爬虫 ：）
--

## 使用：

1. 使用`com.jcav.pagedownloader`中的`IndexPageDownloader`及`PageGroup`从B站[番剧索引](http://bangumi.bilibili.com/anime/index)下载索引，每页的索引将存在`pages`文件夹中。
2. 索引下载完成后，使用`InfParserGroup`组织并整理索引中的番剧信息，并从对应的番剧页面获取播放次数，结果将会存在`infs`文件夹中。
3. 最后可以自己写写代码把所有inf.json文件整合一下，信息收集结束。
4. 最后想咋搞事情咋搞事情

## 所用到的包

+ jsoup
+ gson

## 已知问题

1. 由于没事每刻都有人在看番，所以网上数据更新很快，没啥子办法。
2. 有些番剧不存在于索引中，会被忽略。


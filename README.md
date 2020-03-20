# 北邮OJ成绩拉取
spring boot，httpclient，poi，swagger

简单的调用了拉取成绩的接口，并将成绩信息持久化到excel中。

运行项目后，在浏览器输入：http://127.0.0.1:8888/swagger-ui.html

然后调用接口：“/score/getScoreInfoByContestId”（目前csrftoken和sessionid需要教师账号登录oj后从浏览器获取，后面会改成用户名和密码的形式）

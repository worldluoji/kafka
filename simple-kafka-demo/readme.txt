运行时如果出现java.io.IOException: Can't resolve address: luoji-HUAWEI-MateBook-D:9092
则在Kafka客户端所在机器C:\Windows\System32\drivers\etc 的hosts文件中增加. 如果你的客户端也是Linux，则需要在/etc/hosts中添加
192.168.3.27 luoji-HUAWEI-MateBook-D 
前面是你的kafka服务器的IP, 后面实际是你Kafka服务器的名字。

kafka 连接原理
首先连接 192.168.0.27:9092
再连接返回的host.name = luoji-HUAWEI-MateBook-D 
最后继续连接advertised.host.name=luoji-HUAWEI-MateBook-D
## 科大相关实验

-------  
### AAI高级人工智能课程
  + 八数码问题A*算法实现（比较不同启发函数的性能）
  + Alpha剪枝算法

### Java学习  
+ JVM  
  + 获取PID（*jps windows环境 由于权限问题jps会拿不到pid,需要先对java.io.tmpdir目录进行授权。默认目录地址：C:\Users\${User}\AppData\Local\Temp\hsperfdata_${user}*）
  + jstack线程状态分析 `jstack pid | grep -c '关键字' `
    >
  + jstate内存分析 `jstat -gc pid 5000`
    >S0C、S1C、S0U、S1U：Survivor 0/1区容量（Capacity）和使用量（Used）  
     EC、EU：Eden区容量和使用量  
     OC、OU：年老代容量和使用量  
     PC、PU：永久代容量和使用量  
     YGC、YGT：年轻代GC次数和GC耗时  
     FGC、FGCT：Full GC次数和Full GC耗时
     GCT：GC总耗时<br><br>
### Go学习  
+ 语法训练
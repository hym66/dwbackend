获取最新代码到本地(本地当前分支为[branch]，获取的远端的分支为[origin/branch])
# git fetch origin master
查看版本差异(Q退出)[示例1:查看本地master与远端origin/master的版本差异]
# git log -p master ..origin/master
# 合并最新代码到本地分支[示例1:合并远端分支origin/master到当前分支]
git merge origin/master
# 添加当前目录下的所有文件到缓冲区
git add .
# 本地提交（单引号内的内容为提交时的备注信息)
git commit -m "Your comments"
# 推送到远程
git push origin master


# 直接下拉
git pull origin master

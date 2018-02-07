<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <title>Dashboard Template for Bootstrap</title>

    <script src="http://code.jquery.com/jquery-latest.js"></script>

</head>

<h2>任务信息</h2>
<#list jobList as job>
<div>'${job.beanName}'.'${job.methodName}'.'${job.cronParam}'
    <input type="button" onclick="executeNow('${job.beanName}','${job.methodName}')" value="执行"/>
    <input type="button" onclick="executeNow('${job.beanName}')" value="查看执行历史"/>
    <input type="button" onclick="modifyCron()" value="修改执行时间"/>
</div>
</#list>

<script>
    function executeNow(jobId, executeParam) {
        $.ajax({
            type: "POST",
            url: "/executeNow",
            data: {jobId: jobId, executeParam: executeParam},
            dataType: "text",
            async: true,
            success: function (data) {
                alert(data);
            },
            error: function (data) {
                alert(data);
            }
        });
    }


    function modifyCron() {

    }
</script>
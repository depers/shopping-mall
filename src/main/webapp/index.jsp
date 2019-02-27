<%@ page language="java"  contentType="text/html; charset=UTF-8" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>mall</title>
    <style type="text/css">
        .center-div{
            width: 50%;
            margin: 0 auto;
            margin-top: 10%;
            border-style: solid;
            border-width: 1px;
            border-radius: 5px;
            padding: 1% 3% 3% 3%;
            background-color: #ececec;
        }
        .center-div h1{
            text-align: center;
            margin-bottom: 7%;
        }
    </style>
</head>
<body>
<div class="center-div">
    <h1>服务器：Tomcat1</h1>
    <h2>普通图片上传</h2>
    <form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
        <input type="file" name="upload_file" />
        <input type="submit" value="springmvc上传文件" />
    </form>

    <br>
    <h2>富文本图片上传文件</h2>
    <form name="form2" action="/manage/product/richText_img_upload.do" method="post" enctype="multipart/form-data">
        <input type="file" name="upload_file" />
        <input type="submit" value="富文本图片上传文件" />
    </form>

</div>
</body>
</html>
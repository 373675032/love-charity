/**
 * 上传封面图片
 */
function uploadCover() {
    var formdata=new FormData();
    formdata.append("image",$("#cover").get(0).files[0]);
    $.ajax({
        async: false,
        type: "POST",
        url: "image/upload",
        dataType: "json",
        data: formdata,
        contentType:false,//ajax上传图片需要添加
        processData:false,//ajax上传图片需要添加
        success: function (data) {
            if (data['code']== 200){
                $('#cover_img').attr("src",data['data']);
                $('#imgPath').val(data['data']);
                alert("上传成功！");
            }else {
                alert("文件过大，请上传小于1M的图片");
            }
        },
        error: function (e) {
        }
    });
}

/**
 * 发布文章
 */
function publishArticle() {
    let title = $("#title").val().trim();
    let content = $("#content").val().trim();
    let imgPath = $("#imgPath").val().trim();
    let activityId = $('#activityId').find('option:selected').val();

    if (title == ''){
        alert("请输入标题！");
        return;
    }
    if (content == ''){
        alert("请输入内容！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "publishArticle",
        data: {imgPath:imgPath,title:title,content:content,activityId:activityId},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                alert("发布成功，等待管理员审核！");
                window.location.href = "/love-charity/my-articles?checked=0";
            }else {
                alert("服务器发生了一个错误");
            }
        }
    });
}

/**
 * 发布活动
 */
function publishActivity() {
    let title = $("#title").val().trim();
    let content = $("#content").val().trim();
    let imgPath = $("#imgPath").val().trim();

    if (title == ''){
        alert("请输入标题！");
        return;
    }
    if (content == ''){
        alert("请输入内容！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "publishActivity",
        data: {imgPath:imgPath,title:title,content:content},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                alert("发布成功！");
                window.location.reload();
            }else {
                alert("服务器发生了一个错误");
            }
        }
    });
}

/**
 * 更新文章
 */
function updateArticle() {
    let title = $("#title").val().trim();
    let id = $("#id").val().trim();
    let content = $("#content").val().trim();
    let imgPath = $("#imgPath").val().trim();
    let activityId = $('#activityId').find('option:selected').val();

    if (title == ''){
        alert("请输入标题！");
        return;
    }
    if (content == ''){
        alert("请输入内容！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "updateArticle",
        data: {id:id,imgPath:imgPath,title:title,content:content,activityId:activityId},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                alert("更新成功，等待管理员审核！");
                window.location.href = "/love-charity/my-articles?checked=0";
            }else {
                alert("服务器发生了一个错误");
            }
        }
    });
}

/**
 * 更新活动
 */
function updateActivity() {
    let title = $("#title").val().trim();
    let id = $("#id").val().trim();
    let content = $("#content").val().trim();
    let imgPath = $("#imgPath").val().trim();

    if (title == ''){
        alert("请输入标题！");
        return;
    }
    if (content == ''){
        alert("请输入内容！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "updateActivity",
        data: {id:id,imgPath:imgPath,title:title,content:content},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                alert("更新成功！");
                window.location.reload();
            }else {
                alert("服务器发生了一个错误");
            }
        }
    });
}
/**
 * 发布公益项目
 */
function publishProject() {
    let title = $("#title").val().trim();
    let content = $("#content").val().trim();
    let background = $("#background").val().trim();
    let target = $("#target").val().trim();
    let theme = $("#theme").val().trim();
    let sponsor = $("#sponsor").val().trim();
    let imgPath = $("#imgPath").val().trim();

    if (title == ''){
        alert("请输入标题！");
        return;
    }
    if (content == ''){
        alert("请输入内容！");
        return;
    }
    if (background == ''){
        alert("请输入背景！");
        return;
    }
    if (target == ''){
        alert("请输入目的！");
        return;
    }
    if (theme == ''){
        alert("请输入主题！");
        return;
    }
    if (sponsor == ''){
        alert("请输入赞助商！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "publishProject",
        data: {imgPath:imgPath,name:title,content:content,background:background,target:target,theme:theme,sponsor:sponsor},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                alert("发布成功！");
                window.location.href = "/love-charity/admin-project";
            }else {
                alert("服务器发生了一个错误");
            }
        }
    });
}

/**
 * 修改公益项目
 */
function updateProject() {
    let id = $("#id").val().trim();
    let title = $("#title").val().trim();
    let content = $("#content").val().trim();
    let background = $("#background").val().trim();
    let target = $("#target").val().trim();
    let theme = $("#theme").val().trim();
    let sponsor = $("#sponsor").val().trim();
    let imgPath = $("#imgPath").val().trim();

    if (title == ''){
        alert("请输入标题！");
        return;
    }
    if (content == ''){
        alert("请输入内容！");
        return;
    }
    if (background == ''){
        alert("请输入背景！");
        return;
    }
    if (target == ''){
        alert("请输入目的！");
        return;
    }
    if (theme == ''){
        alert("请输入主题！");
        return;
    }
    if (sponsor == ''){
        alert("请输入赞助商！");
        return;
    }

    $.ajax({
        type: "POST",
        url: "updateProject",
        data: {id:id,imgPath:imgPath,name:title,content:content,background:background,target:target,theme:theme,sponsor:sponsor},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                alert("修改成功！");
                window.location.href = "/love-charity/admin-project";
            }else {
                alert("服务器发生了一个错误");
            }
        }
    });
}

/**
 * 删除公益项目
 * @param id
 */
function deleteProject(id) {
    $.ajax({
        type: "GET",
        url: "deleteProject",
        data: {id:id},
        dataType: "json",
        success: function(data){
            if (data['code']== 200){
                alert("删除成功！");
                window.location.reload();
            }else {
                alert("服务器发生了一个错误");
            }
        }
    });
}